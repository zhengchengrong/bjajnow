package com.threehmis.bjaj.adapter;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.request.SetInfoRegistSaveReq;
import com.threehmis.bjaj.api.bean.respon.GetInfoRigistRsp;
import com.threehmis.bjaj.api.bean.respon.StateSuccessRsp;
import com.threehmis.bjaj.dialog.ChoicePictureDialog;
import com.threehmis.bjaj.utils.ScreenUtil;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class InfoRegistAdapter extends RecyclerView.Adapter<InfoRegistAdapter.ViewHolder> {

    private List<GetInfoRigistRsp> listRsps = new ArrayList<>();

    private int isok=2; //0 符合 1不符合 2其他
    private int cameraposition=-1; //点击拍照时候的tiem位置

    public static String checkId;

    Activity activity;
    String inspectionId;
    Handler mHandler2;
    boolean isentity;
    public InfoRegistAdapter(Activity activity, String inspectionId, Handler mHandler2, boolean isentity) {

        this.activity = activity;
        this.inspectionId = inspectionId;
        this.mHandler2 = mHandler2;
        this.isentity = isentity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_inforegist_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
//        viewHolder.setIsRecyclable(false);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        FrameLayout.LayoutParams pars = (FrameLayout.LayoutParams) holder.pics[0].getLayoutParams();

        pars.width= (int) (ScreenUtil.getScreenWidth(activity)*0.2);
        pars.height= (int) (ScreenUtil.getScreenWidth(activity)*0.13);

        FrameLayout.LayoutParams pars2 = (FrameLayout.LayoutParams) holder.pics[1].getLayoutParams();
        pars2.width= (int) (ScreenUtil.getScreenWidth(activity)*0.2);
        pars2.height= (int) (ScreenUtil.getScreenWidth(activity)*0.13);

        FrameLayout.LayoutParams pars3 = (FrameLayout.LayoutParams) holder.pics[2].getLayoutParams();
        pars3.width= (int) (ScreenUtil.getScreenWidth(activity)*0.2);
        pars3.height= (int) (ScreenUtil.getScreenWidth(activity)*0.13);


        FrameLayout.LayoutParams pars4 = (FrameLayout.LayoutParams) holder.pics[3].getLayoutParams();
        pars4.width= (int) (ScreenUtil.getScreenWidth(activity)*0.2);
        pars4.height= (int) (ScreenUtil.getScreenWidth(activity)*0.13);

        holder.ok.setOnClickListener(new checkedclickListener(holder.ok,holder.nook,position));
        holder.nook.setOnClickListener(new checkedclickListener(holder.ok,holder.nook,position));
        holder.save.setOnClickListener(new checkedclickListener(holder.ok,holder.nook,position));
        holder.camera.setOnClickListener(new checkedclickListener(null,null,position));

        holder.title.setVisibility(View.VISIBLE);
        holder.title.setText(listRsps.get(0).checkList.get(position).type+"");

        if (position != 0&&listRsps.get(0).checkList.get(position).type!=null) {
            holder.title.setVisibility(listRsps.get(0).checkList.get(position).type.equals(listRsps.get(0).checkList.get(position - 1).type) ? View.GONE : View.VISIBLE);
        }

        holder.content.setText(listRsps.get(0).checkList.get(position).itemName);

        Log.d("CD","listRspssdsd=="+JSON.toJSONString(listRsps.get(0).checkList.get(position)));
        switch (listRsps.get(0).checkList.get(position).checkResult){

            case "符合":
                holder.ok.setChecked(true);
                holder.nook.setChecked(false);
                break;
            case "不符合":
                holder.nook.setChecked(true);
                holder.ok.setChecked(false);
                break;
            case "":
                holder.nook.setChecked(false);
                holder.ok.setChecked(false);
                break;
            default:
                break;
        }

        Log.d("CD","JSON+="+JSON.toJSONString(listRsps.get(0).checkList.get(position).checkFile));

        if (listRsps.get(0).checkList.get(position).checkFile!=null&&listRsps.get(0).checkList.get(position).checkFile.size()>0) {
            //图片处理

            holder.layout_pics.setVisibility(View.VISIBLE);
            holder.pics[0].setVisibility(View.INVISIBLE);
            holder.pics[1].setVisibility(View.INVISIBLE);
            holder.pics[2].setVisibility(View.INVISIBLE);
            holder.pics[3].setVisibility(View.INVISIBLE);
            holder.types[0].setVisibility(View.INVISIBLE);
            holder.types[1].setVisibility(View.INVISIBLE);
            holder.types[2].setVisibility(View.INVISIBLE);
            holder.types[3].setVisibility(View.INVISIBLE);


                for (int i = 0; i < (listRsps.get(0).checkList.get(position).checkFile.size()>4?4:listRsps.get(0).checkList.get(position).checkFile.size()); i++) {

                    //视频
                    if (listRsps.get(0).checkList.get(position).checkFile.get(i).url.length()>0){
                        Glide.with(activity).load(RetrofitFactory.BASE_URL + "supervise/lookPhoto/" + listRsps.get(0).checkList.get(position).checkFile.get(i).id).diskCacheStrategy(DiskCacheStrategy.ALL)
                                .override(900, 600).centerCrop().into(holder.pics[i]);
                        holder.types[i].setImageResource(R.drawable.play_btn);
                        holder.pics[i].setVisibility(View.VISIBLE);
                        holder.types[i].setVisibility(View.VISIBLE);

                    }else
                    switch (listRsps.get(0).checkList.get(position).checkFile.get(i).type){
                        //视频
//                        case "video":
//                            //获取视频url第一帧图片
//                            Bitmap bitmap = getBitmapFormUrl(RetrofitFactory.BASE_URL+"supervise/lookPhoto/" + listRsps.get(0).checkList.get(position).checkFile.get(i).id);
//                            //bitmap转uri
//                            Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(activity.getContentResolver(), bitmap, null,null));
//                            Glide.with(activity).load(uri).diskCacheStrategy(DiskCacheStrategy.ALL)
//                                    .override(900, 600).centerCrop().into(holder.pics[i]);
//
//                            holder.types[i].setImageResource(R.drawable.play_btn);
//                            holder.pics[i].setVisibility(View.VISIBLE);
//                            holder.types[i].setVisibility(View.VISIBLE);
//
//                        break;
                        //声音
                        case "audio":

                            holder.types[i].setImageResource(R.drawable.vol);
                            holder.pics[i].setVisibility(View.VISIBLE);
                            holder.types[i].setVisibility(View.VISIBLE);

                        break;

                        default:
                            Glide.with(activity).load(RetrofitFactory.BASE_URL + "supervise/lookPhoto/" + listRsps.get(0).checkList.get(position).checkFile.get(i).id).diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .override(900, 600).centerCrop().into(holder.pics[i]);
                            holder.pics[i].setVisibility(View.VISIBLE);

                        break;


                    }

                }


        }else
            holder.layout_pics.setVisibility(View.GONE);




    }

    @Override
    public int getItemCount() {

        return listRsps.size()>0?listRsps.get(0).checkList.size():0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView content,save,title;
        ImageView[] pics=new ImageView[4];
        ImageView[] types=new ImageView[4];
        TextView camera;
        CheckedTextView ok,nook;
        LinearLayout layout_pics;

        public ViewHolder(View itemView) {
            super(itemView);

            ok = (CheckedTextView) itemView.findViewById(R.id.ok);
            nook = (CheckedTextView) itemView.findViewById(R.id.nook);

            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            layout_pics= (LinearLayout) itemView.findViewById(R.id.layout_pics);
            pics[0]=(ImageView) itemView.findViewById(R.id.pic1);
            pics[1]=(ImageView) itemView.findViewById(R.id.pic2);
            pics[2]=(ImageView) itemView.findViewById(R.id.pic3);
            pics[3]=(ImageView) itemView.findViewById(R.id.pic4);
            types[0]=(ImageView) itemView.findViewById(R.id.type1);
            types[1]=(ImageView) itemView.findViewById(R.id.type2);
            types[2]=(ImageView) itemView.findViewById(R.id.type3);
            types[3]=(ImageView) itemView.findViewById(R.id.type4);
            save=(TextView) itemView.findViewById(R.id.save);
            camera= (TextView) itemView.findViewById(R.id.camera);

        }

    }

    public void notifyData(List<GetInfoRigistRsp> listRsps, int pageIndex) {

        if (pageIndex == 1)
            this.listRsps = listRsps;
        else
            this.listRsps.addAll(listRsps);

        notifyDataSetChanged();

    }


    class checkedclickListener implements View.OnClickListener{

        CheckedTextView ok,nook;
        int position;
        public checkedclickListener(CheckedTextView ok, CheckedTextView nook, int position) {
            this.ok=ok;
            this.nook=nook;
            this.position=position;
        }

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.ok:

                    if (isok==0){
                        ok.setChecked(false);
                        isok=2;
                    }else {
                        isok=0;
                        nook.setChecked(false);
                        ok.setChecked(true);
                    }


                    break;
                case R.id.nook:
                    if (isok==1){
                        nook.setChecked(false);
                        isok=2;
                    }else {
                        isok=1;
                        ok.setChecked(false);
                        nook.setChecked(true);
                    }
                    break;

                case R.id.save://点击保存

                      if (!ok.isChecked()&&!nook.isChecked()){

                       Toast.makeText(activity, "请先判定此条信息是否符合!", Toast.LENGTH_SHORT).show();
                       return;
                      }

                    savedate(ok,nook,position);
                    break;

                case R.id.camera://点击相机

                    Log.d("CD", "checkListpk=" + listRsps.get(0).checkList.get(position).pk+"="+checkId+"=");

                    if (cameraposition==position) {
                        if (TextUtils.isEmpty(listRsps.get(0).checkList.get(position).pk) && TextUtils.isEmpty(checkId)) {
                            Toast.makeText(activity, "请先保存此条信息!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }else {

                        if (TextUtils.isEmpty(listRsps.get(0).checkList.get(position).pk)) {
                            Toast.makeText(activity, "请先保存此条信息!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        cameraposition=position; //保存点击拍照的位置
                    }


                    if (listRsps.get(0).checkList.get(position).checkFile!=null&&listRsps.get(0).checkList.get(position).checkFile.size()>=4){
                        Toast.makeText(activity, "最多上传4项信息！", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!TextUtils.isEmpty(listRsps.get(0).checkList.get(position).pk)){
                        checkId=listRsps.get(0).checkList.get(position).pk;
                    }


                    //拍照录音录像dialog
                    new ChoicePictureDialog(activity,checkId,false).show();

//                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                    mCurrentFile = new File("mnt/sdcard/DCIM/Camera/",
//                            getPhotoFileName());
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentFile));
////                    intent.putExtra("checkId",checkId);
//                    activity.startActivityForResult(intent, 1);
                    break;
            }
        }
    }

//    private String getPhotoFileName() {
//        Date date = new Date(System.currentTimeMillis());
//        SimpleDateFormat dateFormat = new SimpleDateFormat(
//                "'HHH'_yyyyMMdd_HHmmss", Locale.getDefault());
//        return dateFormat.format(date) + ".jpg";
//    }

    private  void savedate(CheckedTextView ok, CheckedTextView nook, int position){
        SetInfoRegistSaveReq req = new SetInfoRegistSaveReq();
        req.inspectionPK=inspectionId;
        //req.pk=listRsps.get(0).checkList.get(position).pk;
        req.checkType=isentity?"实体抽查":"行为管理";
        req.checkItem=listRsps.get(0).checkList.get(position).itemName;

        if (ok.isChecked())
            req.checkResult="符合";
        if (nook.isChecked())
            req.checkResult="不符合";
        if (!ok.isChecked()&&!nook.isChecked())
            req.checkResult="";

//        switch (isok){
//            case 0:
//                req.checkResult="符合";
//                break;
//            case 1:
//                req.checkResult="不符合";
//                break;
//            case 2:
//                req.checkResult="";
//                break;
//        }
        req.userId= AndroidApplication.getInstance().getuserId();
        req.baseItemPK=listRsps.get(0).checkList.get(position).baseItemPk;

        AndroidApplication.getInstance().doPostAsyncfile(RetrofitFactory.BASE_URL+"supervise/saveCheck",req,new okhttpcall());

    }

    class okhttpcall implements Callback {


        @Override
        public void onFailure(Call call, IOException e) {

            mHandler2.sendEmptyMessage(RetrofitFactory.MSG_FAIL);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            String body = response.body().string();

            Log.d("CD", "DDDDDDDDDD=" + body);

            BaseBeanRsp<StateSuccessRsp> stateSuccessRsp = JSON.parseObject(body, new TypeReference<BaseBeanRsp<StateSuccessRsp>>() {
            });
            if (stateSuccessRsp.projectList.get(0).pk.length()>0)
            checkId=stateSuccessRsp.projectList.get(0).pk;
            isok=2;
            mHandler2.sendEmptyMessage(stateSuccessRsp.verification ? RetrofitFactory.MSG_SUCESS : RetrofitFactory.MSG_FAIL);
        }
    }




}
