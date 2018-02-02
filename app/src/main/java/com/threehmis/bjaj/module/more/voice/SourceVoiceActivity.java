package com.threehmis.bjaj.module.more.voice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.threehmis.bjaj.AndroidApplication;
import com.threehmis.bjaj.R;
import com.threehmis.bjaj.adapter.InfoRegistAdapter;
import com.threehmis.bjaj.api.RetrofitFactory;
import com.threehmis.bjaj.api.bean.BaseBeanRsp;
import com.threehmis.bjaj.api.bean.respon.CheckQuestionStateSuccessRsp;
import com.threehmis.bjaj.dialog.ExtAudioRecorder;
import com.threehmis.bjaj.module.more.ActionRegistActivity;
import com.threehmis.bjaj.module.more.CheckQuestionFragment;
import com.threehmis.bjaj.module.more.InfoRegistFragment;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SourceVoiceActivity extends AppCompatActivity {


    ImageView voice_animation;

    AnimationDrawable ad;

    long clickDownTime;

    File recorderFile;

    ExtAudioRecorder extAudioRecorder;

    private String id;
    private boolean isquestion, isActionRegist;

    private ProgressDialog dialog;
    BaseBeanRsp<CheckQuestionStateSuccessRsp> stateSuccessRsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_voice);

        Bundle bundle = this.getIntent().getExtras();
        id = bundle.getString("id");
        isquestion = bundle.getBoolean("isquestion", false);
        isActionRegist = bundle.getBoolean("isActionRegist", false);

        extAudioRecorder = ExtAudioRecorder.getInstanse(false); // 未压缩的录音（WAV）

        voice_animation = (ImageView) findViewById(R.id.voice_animation);
        voice_animation.setBackgroundResource(R.drawable.voice_anmation);
        ad = (AnimationDrawable) voice_animation.getBackground();

        findViewById(R.id.record).setOnTouchListener(new recordOnTouch());

    }


    class recordOnTouch implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    clickDownTime = System.currentTimeMillis();
                    recorderFile = recordChat(extAudioRecorder, AdCachesdfg().getAbsolutePath(),
                            String.valueOf(System.currentTimeMillis()) + ".wav");
                    ad.start();
                    break;

                case MotionEvent.ACTION_MOVE:

                    break;
                case MotionEvent.ACTION_UP:
                    if (System.currentTimeMillis() - clickDownTime > 1000) {
                        stopRecord(extAudioRecorder);
                        ad.stop();

                        getInteract(recorderFile.getAbsolutePath());
                        //Toast.makeText(SourceVoiceActivity.this, "音频完成路劲：" + recorderFile.getAbsolutePath(), Toast.LENGTH_LONG).show();

                    } else {
                        recorderFile.delete();
                        Toast.makeText(SourceVoiceActivity.this, "录音时间太短。", Toast.LENGTH_SHORT).show();
                        ad.stop();
                    }
                    break;
            }
            return true;
        }
    }

    /**
     * voice缓存目录
     */
    public File AdCachesdfg() {
        File dir;
        // File.separator 在 windows是\，unix是/
        String catalog = File.separator + "Android" + File.separator + "data" + File.separator
                + this.getPackageName() + File.separator + "cache";

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dir = new File(Environment.getExternalStorageDirectory(), catalog);
        } else {
            dir = new File(this.getCacheDir(), catalog);
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * 录制wav格式文件
     *
     * @param //path : 文件路径
     */
    public static File recordChat(ExtAudioRecorder extAudioRecorder, String savePath, String fileName) {
        File dir = new File(savePath);
        // 如果该目录没有存在，则新建目录
        if (dir.list() == null) {
            dir.mkdirs();
        }
        // 获取录音文件
        File file = new File(savePath + File.separator + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 设置输出文件
        extAudioRecorder.setOutputFile(savePath + File.separator + fileName);
        extAudioRecorder.prepare();
        // 开始录音
        extAudioRecorder.start();
        return file;
    }

    /**
     * 停止录音
     *
     * @param extAudioRecorder
     */
    public static void stopRecord(final ExtAudioRecorder extAudioRecorder) {
        extAudioRecorder.stop();
        extAudioRecorder.release();
    }


    //数据上传

    private void getInteract(String path) {

        File file = new File(path);
        Log.d("CD", "vvvvvvvv=" + file.length());

        if (file.length() < 10) {
            Toast.makeText(SourceVoiceActivity.this, "音频文件太小!", Toast.LENGTH_SHORT).show();
            finish();
        } else {

            dialog = new ProgressDialog(this);

            dialog.setCanceledOnTouchOutside(true);

            dialog.setTitle("正在上传...");

            dialog.show();

            if (isActionRegist)//是行为登记和实体登记
                AndroidApplication.getInstance().doPostAsyncimage(RetrofitFactory.BASE_URL + "attachment/saveAudio.action", file, id, "audio", new okhttpcall());
            else {

                if (isquestion)
                    AndroidApplication.getInstance().doPostAsyncvoice(RetrofitFactory.BASE_URL + "attachment/saveAudio.action", file, "questionId", id, "audio", new okhttpcall());
                else
                    AndroidApplication.getInstance().doPostAsyncvoice(RetrofitFactory.BASE_URL + "attachment/saveAudio.action", file, "checkId", id, "audio", new okhttpcall());

            }
        }
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

                    InfoRegistAdapter.checkId = null;
                    dialog.cancel();
                    //发广播更新数据
                    if (isActionRegist) {
                        Intent intentcamera = new Intent(ActionRegistActivity.STARTVOICE);
                        intentcamera.putExtra("ActionRegistvoice", (Serializable) stateSuccessRsp.projectList.get(0).files);
                        sendBroadcast(intentcamera);
                    } else {
                        if (isquestion) {
                            Intent intentcamera = new Intent(CheckQuestionFragment.STARTCAMERA);
                            intentcamera.putExtra("vedioorvoice", (Serializable) stateSuccessRsp.projectList.get(0).files);
                            sendBroadcast(intentcamera);
                        } else {
                            Intent intent = new Intent(InfoRegistFragment.INFOREGISTREF);
                            sendBroadcast(intent);
                        }
                    }

                    Toast.makeText(SourceVoiceActivity.this, "上传音频成功!", Toast.LENGTH_SHORT).show();
                    finish();

                    break;
                case RetrofitFactory.MSG_FAIL:

                    dialog.cancel();
                    Toast.makeText(SourceVoiceActivity.this, "上传音频失败!", Toast.LENGTH_SHORT).show();
                    finish();

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
    }


}
