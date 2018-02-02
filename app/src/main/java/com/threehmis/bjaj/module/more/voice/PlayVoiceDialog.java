package com.threehmis.bjaj.module.more.voice;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.utils.ScreenUtil;


/**
 * @author CH
 */
public class PlayVoiceDialog extends Dialog implements View.OnClickListener {

    Context context;

    String audiourl;
    Player player;

    public PlayVoiceDialog(Context context, String audiourl) {
        this(context, R.style.enterDialog);
        this.context = context;
        this.audiourl = audiourl;
    }

    public PlayVoiceDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_voice_dialog);
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.alpha = 1.0f;
        wl.gravity = Gravity.CENTER;
        wl.width = ScreenUtil.getScreenWidth(context);
        window.setAttributes(wl);
        setCancelable(true);
        setCanceledOnTouchOutside(true);

        this.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                if (player != null) {
                    player.stop();
                    player = null;
                }
            }
        });

        TextView timeend = (TextView) findViewById(R.id.time_end);
        SeekBar progress = (SeekBar) findViewById(R.id.progress);
        TextView timestr = (TextView) findViewById(R.id.time_str);
        TextView stop = (TextView) findViewById(R.id.stop); //暂停按钮
        TextView play = (TextView) findViewById(R.id.play); //播放按钮

        stop.setOnClickListener(this);
        play.setOnClickListener(this);

        player = new Player(progress, timestr, timeend, play, stop);
        progress.setOnSeekBarChangeListener(new SeekBarChangeEvent());

        Log.d("CD","audiourl="+audiourl);

        new Thread(new Runnable() {
            @Override
            public void run() {
                player.playUrl(audiourl);
            }
        }).start();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.stop:
                if (player.mediaPlayer.isPlaying()) {
                    player.pause();
                }
                break;

            case R.id.play:
                player.play();
                break;
        }
    }

    // voice
    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        int progress;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            this.progress = progress * player.mediaPlayer.getDuration() / seekBar.getMax();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            player.mediaPlayer.seekTo(progress);
        }
    }


}
