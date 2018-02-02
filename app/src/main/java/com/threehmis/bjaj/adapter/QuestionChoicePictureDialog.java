package com.threehmis.bjaj.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.threehmis.bjaj.R;
import com.threehmis.bjaj.module.more.CheckQuestionFragment;
import com.threehmis.bjaj.module.more.video.RecordActivity;
import com.threehmis.bjaj.module.more.voice.SourceVoiceActivity;
import com.threehmis.bjaj.utils.ScreenUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author CD
 *         拍照录音录像dialog
 *         回调在ActionCheckActivity
 */
public class QuestionChoicePictureDialog extends Dialog implements
        View.OnClickListener {

    Activity context;
    private String questionId;

    public static File mCurrentFile;


    public QuestionChoicePictureDialog(Activity context, String questionId) {
        this(context, R.style.enterDialog);
        this.context = context;
        this.questionId = questionId;
    }

    public QuestionChoicePictureDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_choice_picture_dialog);
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.alpha = 1.0f;
        wl.gravity = Gravity.BOTTOM;
        wl.width = ScreenUtil.getScreenWidth(context);
        window.setAttributes(wl);
        setCancelable(true);
        setCanceledOnTouchOutside(true);

        findViewById(R.id.camera).setOnClickListener(this);
        findViewById(R.id.xiangce).setOnClickListener(this);
        findViewById(R.id.vedio).setOnClickListener(this);
        findViewById(R.id.voice).setOnClickListener(this);
        findViewById(R.id.cancer).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        cancel();
        switch (v.getId()) {
            case R.id.camera:

                //发广播调用camera
                Intent intentcamera = new Intent(CheckQuestionFragment.STARTCAMERA);
                context.sendBroadcast(intentcamera);

//                Intent intentCamera = new Intent("android.media.action.IMAGE_CAPTURE");
//                mCurrentFile = new File("mnt/sdcard/DCIM/Camera/",
//                        getPhotoFileName());
//                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentFile));
//                context.startActivityForResult(intentCamera, 1);
                break;
            case R.id.xiangce://相册
                    //发广播调用相册
                Intent intentxaingce = new Intent(CheckQuestionFragment.STARTCAMERA);
                intentxaingce.putExtra("xiangce", "xiangce");
                context.sendBroadcast(intentxaingce);

                break;
            case R.id.vedio:

                Intent intent = new Intent(context, RecordActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", questionId);
                bundle.putBoolean("isquestion", true);
                intent.putExtras(bundle);
                context.startActivity(intent);

                break;
            case R.id.voice:

                Intent intent2 = new Intent(context, SourceVoiceActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("id", questionId);
                bundle2.putBoolean("isquestion", true);
                intent2.putExtras(bundle2);
                context.startActivity(intent2);

                break;
        }
    }

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'HHH'_yyyyMMdd_HHmmss", Locale.getDefault());
        return dateFormat.format(date) + ".jpg";
    }


}
