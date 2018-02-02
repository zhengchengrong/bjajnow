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
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.adapter.ShowAddGridPicAdapter;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.GetInfoRegistReq;
import com.threehmis.bjaj.api.bean.request.GetInfoRegistSearchReq;
import com.threehmis.bjaj.api.bean.request.SetInfoRegistSaveReq;
import com.threehmis.bjaj.api.bean.respon.CheckQuestionStateSuccessRsp;
import com.threehmis.bjaj.api.bean.respon.GetInfoRigistRsp;
import com.threehmis.bjaj.api.bean.respon.NameKeyRsp;
import com.threehmis.bjaj.dialog.ChoicePictureDialog;
import com.threehmis.bjaj.dialog.StrListDialog;
import com.threehmis.bjaj.utils.EmojiEditText;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

//行为 实体登记
public class ActionRegistActivity extends AppCompatActivity {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.other)
    TextView other;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.project)
    TextView project;
    @BindView(R.id.save)
    TextView save;
    @BindView(R.id.no)
    CheckedTextView no;
    @BindView(R.id.yes)
    CheckedTextView yes;

    @BindView(R.id.mygridView)
    NoScrollGridView mygridView;
    @BindView(R.id.suggest)
    EmojiEditText suggest;

    public static String STARTVOICE = "startvoice"; //广播

    public static List<NameKeyRsp> pathimages;  //给gridview 的图片
    BaseBeanRsp<GetInfoRigistRsp> getInfoRigistRsp;
    BaseBeanRsp<CheckQuestionStateSuccessRsp> stateSuccessRsp;
    //单位类型数据
    List<GetInfoRigistRsp.TitleListBean> liststr = new ArrayList<>();
    List<GetInfoRigistRsp.CheckListBean> ckecklist = new ArrayList<>();
    private boolean isentity;
    private boolean isquality; //判断是否是质量监督的
    private String inspectionId;
    private String projectType;
    private boolean isconten;
    private ShowAddGridPicAdapter adapter;
    String baseItemPK;
    String pk;
    String questionId="";
    private Boolean isimages=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_regist);
        ButterKnife.bind(this);

        Bundle bundle = this.getIntent().getExtras();
        projectType = bundle.getString("projectType");
        isentity = bundle.getBoolean("isentity");//实体
        inspectionId = bundle.getString("pk");
        isquality = bundle.getBoolean("isquality", false);

        title.setText(isentity ? "实体登记" : "行为登记");
        other.setText("记录");
        other.setVisibility(View.VISIBLE);
        pathimages = new ArrayList<>();
        adapter = new ShowAddGridPicAdapter();
        mygridView.setAdapter(adapter);
        mygridView.setOnItemClickListener(new itemClickListener());

        getdate();
    }

    private void getdate() {

        GetInfoRegistReq req = new GetInfoRegistReq();

        req.superviseType = isquality ? "质量" : "安全";
        req.projectType = projectType;
        req.itemType = isentity ? "实体抽查" : "行为管理";
        req.inspectionId = inspectionId;
        req.pageno = 1 + "";
        req.pagesize = 20 + "";

        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL + "supervise/getItem", req, new okhttpcall());


    }

    class okhttpcall implements Callback {


        @Override
        public void onFailure(Call call, IOException e) {

            mHandler.sendEmptyMessage(RetrofitFactory.MSG_FAIL);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            String body = response.body().string();

            Log.d("CD", "DDDDDDDDDD=" + body);

            getInfoRigistRsp = JSON.parseObject(body, new TypeReference<BaseBeanRsp<GetInfoRigistRsp>>() {
            });
            mHandler.sendEmptyMessage(getInfoRigistRsp.verification ? RetrofitFactory.MSG_SUCESS : RetrofitFactory.MSG_FAIL);

        }
    }

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case RetrofitFactory.MSG_SUCESS:

                    if (isconten) {
                        ckecklist = getInfoRigistRsp.projectList.get(0).checkList;
                        content.setText(ckecklist.get(0).itemName);
                        baseItemPK = ckecklist.get(0).baseItemPk;
                        pk = ckecklist.get(0).pk;
                        adapter.clear();//跟换了抽查项目后
                        pathimages.clear();
                        questionId="";
                        if (ckecklist.get(0).checkFile != null && ckecklist.get(0).checkFile.size() > 0) {
                            updateGridPicAdapter2(ckecklist.get(0).checkFile);
                        }
                    } else {
                        liststr = getInfoRigistRsp.projectList.get(0).titleList;
                        project.setText(getInfoRigistRsp.projectList.get(0).checkList.get(0).type);
                        content.setText(getInfoRigistRsp.projectList.get(0).checkList.get(0).itemName);
                        baseItemPK = getInfoRigistRsp.projectList.get(0).checkList.get(0).baseItemPk;
                        pk = getInfoRigistRsp.projectList.get(0).checkList.get(0).pk;
                        if(getInfoRigistRsp.projectList.get(0).checkList.get(0).checkFile!=null&&getInfoRigistRsp.projectList.get(0).checkList.get(0).checkFile.size()>0)
                        updateGridPicAdapter2(getInfoRigistRsp.projectList.get(0).checkList.get(0).checkFile);
                    }

                    break;
                case RetrofitFactory.MSG_FAIL:
                    Toast.makeText(ActionRegistActivity.this, "获取数据失败!", Toast.LENGTH_SHORT).show();
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
        mHandler2.removeCallbacksAndMessages(null);
        unregisterReceiver(receiver);
    }

    @OnClick(R.id.project_layout)
    void projectlayout() {

        if (liststr.size() > 0) {
            StrListDialog dialog = new StrListDialog(this, liststr, null, new lisenter());
            dialog.show();
        }
    }

    @OnClick(R.id.content_layout)
    void contentlayout() {

        if (ckecklist.size() > 0) {
            StrListDialog dialog = new StrListDialog(this, null, ckecklist, new lisenter());
            dialog.show();
        }
    }


    @OnClick(R.id.no)
    void no() {

            no.setChecked(true);
            yes.setChecked(false);
    }

    @OnClick(R.id.yes)
    void yes() {
            yes.setChecked(true);
            no.setChecked(false);
    }

    @OnClick(R.id.save)
    void save() {//保存

        isimages=false;
        savedate();
    }

    @OnClick(R.id.other)
    void other() {//记录

        finish();
        Intent intent2 =new Intent(this, ActionRegistHistoryActivity.class);
        Bundle bundle2 = new Bundle();
        bundle2.putString("pk",inspectionId);
        bundle2.putBoolean("isentity",isentity);
        intent2.putExtras(bundle2);
        startActivity(intent2);

    }

    @OnClick(R.id.titleback)
    void titleback() {
        finish();
    }

    //监听获取点击后返回的登记内容
    class lisenter implements StrListDialog.GetProjectListener {

        @Override
        public void Getdate(String str1, String str2, List<GetInfoRigistRsp.CheckListBean.CheckFileBean> files, int i) {
            if (i == 1) {
                project.setText(str1);
                getSearchdate(str2);
            } else {
                content.setText(str1);
                baseItemPK = str2;
                adapter.clear();//跟换了抽查项目后
                pathimages.clear();
                questionId="";
                if (files != null && files.size() > 0) {
                    updateGridPicAdapter2(files);
                }
            }

        }

    }

    private void getSearchdate(String type) {

        GetInfoRegistSearchReq req = new GetInfoRegistSearchReq();

        req.type = type;
        req.inspectionId = inspectionId;
        req.pageno = 1 + "";
        req.pagesize = 100 + "";
        req.superviseType = isquality ? "质量" : "安全";
        req.projectType = projectType;
        req.itemType = isentity ? "实体抽查" : "行为管理";

        isconten = true;
        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL + "supervise/getItemByType", req, new okhttpcall());

    }

    private class itemClickListener implements AdapterView.OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            if (TextUtils.isEmpty(questionId)) {
                Toast.makeText(ActionRegistActivity.this, "请先报存此问题！", Toast.LENGTH_SHORT).show();
                return;
            }

            if (position == adapter.getCount() - 1) {

                if (pathimages.size() < 4) {

                    //拍照录音录像dialog
                    new ChoicePictureDialog(ActionRegistActivity.this, questionId,true).show();

                } else
                    Toast.makeText(ActionRegistActivity.this, "最多只能上传4项信息！", Toast.LENGTH_SHORT).show();

            }

        }
    }


    private void savedate() {

        if (!yes.isChecked() && !no.isChecked()) {
            Toast.makeText(ActionRegistActivity.this, "请选择抽查结论！", Toast.LENGTH_SHORT).show();
            return;
        }

        SetInfoRegistSaveReq req = new SetInfoRegistSaveReq();
        req.inspectionPK = inspectionId;
        //req.pk = pk;
        req.remark = suggest.getText().toString();
        req.checkType = isentity ? "实体抽查" : "行为管理";
        req.checkItemType = project.getText().toString();
        req.checkItem = content.getText().toString();

        if (yes.isChecked())
            req.checkResult = "符合";
        if (no.isChecked())
            req.checkResult = "不符合";
        if (!yes.isChecked() && !no.isChecked())
            req.checkResult = "";

        req.userId = AndroidApplication.getInstance().getuserId();
        req.baseItemPK = baseItemPK;

        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL + "supervise/saveInspectionCheck", req, new okhttpcall2());

    }

    class okhttpcall2 implements Callback {


        @Override
        public void onFailure(Call call, IOException e) {

            mHandler2.sendEmptyMessage(RetrofitFactory.MSG_FAIL);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            String body = response.body().string();

            Log.d("CD", "DDDDDDDDDD=" + body);

            stateSuccessRsp = JSON.parseObject(body, new TypeReference<BaseBeanRsp<CheckQuestionStateSuccessRsp>>() {
            });
            mHandler2.sendEmptyMessage(stateSuccessRsp.verification ? RetrofitFactory.MSG_SUCESS : RetrofitFactory.MSG_FAIL);
        }
    }

    protected Handler mHandler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case RetrofitFactory.MSG_SUCESS:


                    if (isimages) {//保存图片成功
                        dialog.cancel();

                        updateGridPicAdapter(stateSuccessRsp.projectList.get(0).files);

                    } else {

                        if (questionId.length()>0&&questionId!=stateSuccessRsp.projectList.get(0).pk){
                            adapter.clear();//跟换了抽查项目后
                            pathimages.clear();
                        }

                        questionId = stateSuccessRsp.projectList.get(0).pk;
//                        save.setVisibility(View.GONE);

                    }
                    Toast.makeText(ActionRegistActivity.this, "保存成功!", Toast.LENGTH_SHORT).show();

                    break;
                case RetrofitFactory.MSG_FAIL:
                    dialog.cancel();
                    Toast.makeText(ActionRegistActivity.this, "保存失败!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private int REQUEST_CODE = 0x08; // 请求码
    private ProgressDialog dialog;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE
                && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            Toast.makeText(ActionRegistActivity.this, "请开启相机权限", Toast.LENGTH_SHORT).show();
            return;
        } else if (requestCode == REQUEST_CODE
                && resultCode == PermissionsActivity.PERMISSIONS_GRANTED) {

            return;
        }

        String path = null;

        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    Uri fileUri = Uri
                            .fromFile(ChoicePictureDialog.mCurrentFile);
                    path = Uri2Path.getPath(ActionRegistActivity.this, fileUri);

                    Log.d("CD", "path=" + path);
                    //获取图片路径
                    if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(questionId)) {

                        dialog = new ProgressDialog(this);

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
                        AndroidApplication.getInstance().doPostAsyncimage(RetrofitFactory.BASE_URL + "attachment/savePhoto.action", file, questionId, "photo", new okhttpcall2());

                    }

                }

                break;

            case 2:

                if (resultCode == Activity.RESULT_OK && data != null) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumns = {MediaStore.Images.Media.DATA};
                    Cursor c = this.getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePathColumns[0]);
                    path = c.getString(columnIndex);
                    c.close();

                    Log.d("CD", "Xiangcepath=" + path);
                    //获取图片路径
                    if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(questionId)) {

                        dialog = new ProgressDialog(this);

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
                        AndroidApplication.getInstance().doPostAsyncimage(RetrofitFactory.BASE_URL + "attachment/savePhoto.action", file, questionId, "photo", new okhttpcall2());

                    }

                }
                break;
        }




    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            List<CheckQuestionStateSuccessRsp.FilesBean> files = (List<CheckQuestionStateSuccessRsp.FilesBean>) intent.getSerializableExtra("ActionRegistvoice");
            Log.d("CD", "intent=" + JSON.toJSONString(files));
            if (intent != null)
                if (files != null && files.size() > 0)
                    updateGridPicAdapter(files);

        }
    };

    @Override
    public void onStart() {
        super.onStart();

        IntentFilter filter = new IntentFilter();
        filter.addAction(STARTVOICE);
        this.registerReceiver(receiver, filter);
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

    //点击不同的抽查内容  刷新对应的图片
    private void updateGridPicAdapter2(List<GetInfoRigistRsp.CheckListBean.CheckFileBean> files) {

        for (GetInfoRigistRsp.CheckListBean.CheckFileBean obj:files) {
            NameKeyRsp nameKeyRsp = new NameKeyRsp();
            if (obj.url.length() > 0) {
                nameKeyRsp.name = "video";
                nameKeyRsp.key = obj.id;

            } else {
                switch (obj.type) {
                    case "audio":
                        nameKeyRsp.name = "audio";
                        nameKeyRsp.key = obj.id;
                        break;
                    default:
                        nameKeyRsp.name = "photo";
                        nameKeyRsp.key = obj.id;
                        break;

                }
            }

            adapter.adddata(nameKeyRsp);
            pathimages.add(nameKeyRsp);

        }
        adapter.notifyDataSetChanged();

    }

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'HHH'_yyyyMMdd_HHmmss", Locale.getDefault());
        return dateFormat.format(date) + ".jpg";
    }

}
