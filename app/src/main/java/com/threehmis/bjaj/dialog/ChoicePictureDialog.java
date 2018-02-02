package com.threehmis.bjaj.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.threehmis.bjaj.R;
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
public class ChoicePictureDialog extends Dialog implements
        View.OnClickListener {

    Activity context;
    private String checkid;

    public static File mCurrentFile;

    Boolean isActionRegist = false;

    public ChoicePictureDialog(Activity context, String checkid, Boolean isActionRegist) {
        this(context, R.style.enterDialog);
        this.context = context;
        this.checkid = checkid;
        this.isActionRegist = isActionRegist;
    }

    public ChoicePictureDialog(Context context, int theme) {
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

                Intent intentCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                mCurrentFile = new File("mnt/sdcard/DCIM/Camera/",
                        getPhotoFileName());
                //intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentFile));

/*获取当前系统的android版本号*/
                int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentapiVersion < 24) {
                    intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentFile));
                } else {
                    ContentValues contentValues = new ContentValues(1);
                    contentValues.put(MediaStore.Images.Media.DATA, mCurrentFile.getAbsolutePath());
                    Uri uri = getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                    intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                }

                context.startActivityForResult(intentCamera, 1);
                break;
            case R.id.xiangce: //相册选择
//调用相册
                Intent intentxiangce = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                context.startActivityForResult(intentxiangce, 2);

                break;
            case R.id.vedio:

                Intent intent = new Intent(context, RecordActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", checkid);
                bundle.putBoolean("isActionRegist", isActionRegist);
                intent.putExtras(bundle);
                context.startActivity(intent);

                break;
            case R.id.voice:

                Intent intent2 = new Intent(context, SourceVoiceActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("id", checkid);
                bundle2.putBoolean("isActionRegist", isActionRegist);
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
