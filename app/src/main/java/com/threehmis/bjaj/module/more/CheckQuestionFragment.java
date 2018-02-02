package com.threehmis.bjaj.module.more;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.adapter.QuestionChoicePictureDialog;
import com.threehmis.bjaj.adapter.QuestionSpinnerListAdapter;
import com.threehmis.bjaj.adapter.ShowAddGridPicAdapter;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.SetSaveCheckQuestionReq;
import com.threehmis.bjaj.api.bean.respon.CheckQuestionStateSuccessRsp;
import com.threehmis.bjaj.api.bean.respon.NameKeyRsp;
import com.threehmis.bjaj.utils.ImageFactory;
import com.threehmis.bjaj.utils.PermissionsActivity;
import com.threehmis.bjaj.utils.Uri2Path;
import com.threehmis.bjaj.widget.NoScrollGridView;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class CheckQuestionFragment extends Fragment implements View.OnClickListener {

    public static String STARTCAMERA = "startcamera"; //广播相机

    boolean isimages;
    public static List<NameKeyRsp> pathimages;  //给gridview 的图片
    private ProgressDialog dialog;
    private File mCurrentFile;
    private NoScrollGridView mygridView;
    private ShowAddGridPicAdapter adapter;
    private Spinner spinner;
    private LinearLayout layoutquestionclass;


    private BaseBeanRsp<CheckQuestionStateSuccessRsp> stateSuccessRsp;
    private EditText jianyi;
    private TextView questionclass;
    private EditText suggest;

    private boolean isquality; //判断是否是质量监督的
    private String projectType, inspectionId;
    private String questionId;
    private TextView save;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkquestion, container, false);

        Bundle bundle = getActivity().getIntent().getExtras();
        isquality = bundle.getBoolean("isquality", false);
        projectType = bundle.getString("projectType");
        inspectionId = bundle.getString("pk");

        spinner = (Spinner) view.findViewById(R.id.spinner);
        save = (TextView) view.findViewById(R.id.save);
        jianyi = (EditText) view.findViewById(R.id.jianyi);
        suggest = (EditText) view.findViewById(R.id.suggest);
        layoutquestionclass = (LinearLayout) view.findViewById(R.id.layout_questionclass);
        questionclass = (TextView) view.findViewById(R.id.questionclass);

        pathimages = new ArrayList<>();
        mygridView = (NoScrollGridView) view.findViewById(R.id.mygridView);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //适配器
        String[] liststr = {"行为管理", "实体抽查"};
        QuestionSpinnerListAdapter spinneradapter = new QuestionSpinnerListAdapter(liststr);
        spinner.setAdapter(spinneradapter);
        Log.d("CD", "spinner==" + spinner.getSelectedItem().toString());


        adapter = new ShowAddGridPicAdapter();

        mygridView.setAdapter(adapter);

        mygridView.setOnItemClickListener(new itemClickListener());


        layoutquestionclass.setOnClickListener(this);
        save.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.layout_questionclass:

//                QestionClassDialog dialog = new QestionClassDialog(getActivity(), isquality, projectType, new dialoglistener());
//                dialog.show();

                    Intent intent = new Intent(getActivity(), QestionClassDialogActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isquality", isquality);
                    bundle.putString("projectType", projectType);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 3);

                break;
            case R.id.save:
                //点击保存

                saveQuestion();

                break;


        }


    }


    private class itemClickListener implements AdapterView.OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            if (TextUtils.isEmpty(questionId)) {
                Toast.makeText(getActivity(), "请先报存此问题！", Toast.LENGTH_SHORT).show();
                return;
            }

            if (position == adapter.getCount() - 1) {

                if (pathimages.size() < 4) {

                    //调取相机拍照
//                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                    mCurrentFile = new File("mnt/sdcard/DCIM/Camera/",
//                            getPhotoFileName());
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentFile));
//                    startActivityForResult(intent, 1);

                    //拍照录音录像dialog
                    new QuestionChoicePictureDialog(getActivity(), questionId).show();

                } else
                    Toast.makeText(getActivity(), "最多只能上传4项信息！", Toast.LENGTH_SHORT).show();

            }

        }
    }


    private void saveQuestion() {

        if (TextUtils.isEmpty(questionclass.getText().toString())){
            Toast.makeText(getContext(), "请录入问题类型！", Toast.LENGTH_SHORT).show();
            return;
        }

        SetSaveCheckQuestionReq req = new SetSaveCheckQuestionReq();

        req.inspectionPK = inspectionId;
        req.pk = questionId;
        req.checkType = spinner.getSelectedItem().toString();
        req.questionType = questionclass.getText().toString();
        req.questions = suggest.getText().toString();
        req.dealNote = jianyi.getText().toString();
        req.userId = AndroidApplication.getInstance().getuserId();

        isimages = false;
        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL + "question/saveQuestion", req, new okhttpcall());

    }

    class okhttpcall implements Callback {


        @Override
        public void onFailure(Call call, IOException e) {

            mHandler.sendEmptyMessage(RetrofitFactory.MSG_FAIL);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            String body = response.body().string();

            Log.d("CD", "mDDDDDDDDD=" + body);
            stateSuccessRsp = JSON.parseObject(body, new TypeReference<BaseBeanRsp<CheckQuestionStateSuccessRsp>>() {
            });

            mHandler.sendEmptyMessage(stateSuccessRsp.verification ? RetrofitFactory.MSG_SUCESS : RetrofitFactory.MSG_FAIL);

        }
    }

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case RetrofitFactory.MSG_SUCESS:

                    if (isimages) {//保存图片成功
                        dialog.cancel();

                        updateGridPicAdapter(stateSuccessRsp.projectList.get(0).files);

                    } else {
                        questionId = stateSuccessRsp.projectList.get(0).pk;
//                        save.setVisibility(View.GONE);

                        suggest.setFocusable(false);
                        jianyi.setFocusable(false);
                    }

                    Intent intent4 = new Intent();//广播刷新检测问题记录
                    intent4.setAction(CheckQuestionHistoryFragment.DATAREFRESH);
                    getActivity().sendBroadcast(intent4);
                    Toast.makeText(getContext(), "保存成功!", Toast.LENGTH_SHORT).show();


                    break;
                case RetrofitFactory.MSG_FAIL:

                    dialog.cancel();
                    Toast.makeText(getContext(), "保存失败!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();

        mHandler.removeCallbacksAndMessages(null);
        getActivity().unregisterReceiver(receiver);
    }

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'HHH'_yyyyMMdd_HHmmss", Locale.getDefault());
        return dateFormat.format(date) + ".jpg";
    }

    private int REQUEST_CODE = 0x08; // 请求码

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE
                && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            Toast.makeText(getActivity(), "请开启相机权限", Toast.LENGTH_SHORT).show();
            return;
        } else if (requestCode == REQUEST_CODE
                && resultCode == PermissionsActivity.PERMISSIONS_GRANTED) {

            return;
        }

        String path = null;

        switch (requestCode) {
            case 1:  //照相机
                if (resultCode == Activity.RESULT_OK) {
                    Uri fileUri = Uri
                            .fromFile(mCurrentFile);
                    path = Uri2Path.getPath(getActivity(), fileUri);

                    Log.d("CD", "path=" + path);
                    //获取图片路径
                    if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(questionId)) {

                        dialog = new ProgressDialog(getActivity());

                        dialog.setCanceledOnTouchOutside(true);

                        dialog.setTitle("正在上传...");

                        dialog.show();

                        // File file = new File(path);
                        //图片压缩处理
                        File file = null;
                        try {
                            file = ImageFactory.saveFile(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        isimages = true;
                        AndroidApplication.getInstance().doPostAsyncimagetwo(RetrofitFactory.BASE_URL + "attachment/savePhoto.action", file, questionId, new okhttpcall());

                    }

                }
                break;
            case 2:  //相册
                if (resultCode == Activity.RESULT_OK && data != null) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumns = {MediaStore.Images.Media.DATA};
                    Cursor c = getActivity().getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePathColumns[0]);
                    path = c.getString(columnIndex);
                    c.close();

                    Log.d("CD", "Xiangcepath=" + path);
                    //获取图片路径
                    if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(questionId)) {

                        dialog = new ProgressDialog(getActivity());

                        dialog.setCanceledOnTouchOutside(true);

                        dialog.setTitle("正在上传...");

                        dialog.show();

                        // File file = new File(path);
                        //图片压缩处理
                        File file = null;
                        try {
                            file = ImageFactory.saveFile(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        isimages = true;
                        AndroidApplication.getInstance().doPostAsyncimagetwo(RetrofitFactory.BASE_URL + "attachment/savePhoto.action", file, questionId, new okhttpcall());

                    }

                }
                break;

            case 3://问题类型选择后返回的数据

                if (data != null) {
                    //解析QestionClassDialogActivity传来的数据
                    Bundle result = data.getExtras();
                    String date1 = result.getString("date1");
                    String date2 = result.getString("date2");
                    String date3 = result.getString("date3");

                    questionclass.setText(date1);
                    suggest.setText(date2);
                    jianyi.setText(date3);
                }

                break;

        }


    }


    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent != null && intent.getStringExtra("clean")!=null&&intent.getStringExtra("clean").equals("clean")) {
                questionId="";
                jianyi.setText("");
                questionclass.setText("");
                suggest.setText("");
                adapter.clear();
                pathimages.clear();

            } else {

                List<CheckQuestionStateSuccessRsp.FilesBean> files = (List<CheckQuestionStateSuccessRsp.FilesBean>) intent.getSerializableExtra("vedioorvoice");
                Log.d("CD", "intent=" + JSON.toJSONString(files));
                if (intent != null)
                    if (files != null && files.size() > 0) {
                        Intent intent4 = new Intent();//广播刷新检测问题记录
                        intent4.setAction(CheckQuestionHistoryFragment.DATAREFRESH);
                        getActivity().sendBroadcast(intent4);
                        updateGridPicAdapter(files);
                    } else {
                        String xiangce = intent.getStringExtra("xiangce");
                        if (xiangce != null && xiangce.equals("xiangce")) {
                            //调用相册
                            Intent intentxiangce = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intentxiangce, 2);
                        } else {
                            //调取相机拍照
                            Intent intent2 = new Intent("android.media.action.IMAGE_CAPTURE");
                            mCurrentFile = new File("mnt/sdcard/DCIM/Camera/",
                                    getPhotoFileName());
                            intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentFile));
                            startActivityForResult(intent2, 1);
                        }
                    }
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();

        IntentFilter filter = new IntentFilter();
        filter.addAction(STARTCAMERA);
        getActivity().registerReceiver(receiver, filter);
    }

    private void updateGridPicAdapter(List<CheckQuestionStateSuccessRsp.FilesBean> files) {

        NameKeyRsp nameKeyRsp = new NameKeyRsp();

        if (files.get(files.size() - 1).url.length() > 0) {
            nameKeyRsp.name = "video";
            nameKeyRsp.key = files.get(files.size() - 1).id;

        } else {
            switch (files.get(files.size() - 1).type) {
                case "audio":
                    nameKeyRsp.name = "audio";
                    nameKeyRsp.key = files.get(files.size() - 1).id;
                    break;
                default:
                    nameKeyRsp.name = "photo";
                    nameKeyRsp.key = files.get(files.size() - 1).id;
                    break;

            }
        }

        adapter.notifyData(nameKeyRsp);
        pathimages.add(nameKeyRsp);

    }


}
