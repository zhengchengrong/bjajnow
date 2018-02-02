package com.threehmis.bjaj.module.more.voice;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Player implements OnBufferingUpdateListener, OnCompletionListener,
        OnPreparedListener {


    public MediaPlayer mediaPlayer; // 媒体播放器
    private SeekBar seekBar; // 拖动条
    private TextView time_str, time_end, play, stop;
    private Timer mTimer = new Timer(); // 计时器
    public boolean isplay = false;

    // 初始化播放器
    public Player(SeekBar seekBar, TextView time_str, TextView time_end, TextView play, TextView stop) {
        super();
        this.seekBar = seekBar;
        this.time_str = time_str;
        this.time_end = time_end;
        this.play = play;
        this.stop = stop;
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnCompletionListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 每一秒触发一次
        mTimer.schedule(timerTask, 0, 1000);
    }

    // 计时器
    TimerTask timerTask = new TimerTask() {

        @Override
        public void run() {
            if (mediaPlayer == null)
                return;

            try {
                if (mediaPlayer.isPlaying() && seekBar.isPressed() == false) {
                    handler.sendEmptyMessage(0); // 发送消息
                }
            } catch (IllegalStateException e) {
                // TODO: handle exception
            }

        }
    };

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int position = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();
            if (duration > 0) {
                long pos = seekBar.getMax() * position / duration;
                String timeStr = TextFormatter.getMusicTime(position + 1);
                String timeEnd = TextFormatter.getMusicTime(duration);

                time_str.setText(timeStr);
                time_end.setText(timeEnd);
                seekBar.setProgress((int) pos + 1);
            }
        }

        ;
    };

    public void play() {
        mediaPlayer.start();
        isplay = true;
        play.setVisibility(View.GONE);
        stop.setVisibility(View.VISIBLE);
        //ib_play.setBackgroundResource(R.drawable.button_pause);
    }

    /**
     * @param url url地址
     */
    public void playUrl(String url) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url); // 设置数据源
            mediaPlayer.prepare(); // prepare自动播放
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 暂停
    public void pause() {
        mediaPlayer.pause();
        isplay = false;
        play.setVisibility(View.VISIBLE);
        stop.setVisibility(View.GONE);
        //ib_play.setBackgroundResource(R.drawable.button_play);
    }

    // 停止
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        Log.e("mediaPlayer", "onPrepared");
        play.setVisibility(View.GONE);
        stop.setVisibility(View.VISIBLE);
        //ib_play.setBackgroundResource(R.drawable.button_pause);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.d("CD", "onCompletion");
        play.setVisibility(View.VISIBLE);
        stop.setVisibility(View.GONE);
        seekBar.setProgress(seekBar.getMax());
    }

    /**
     * 缓冲更新
     */
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        seekBar.setSecondaryProgress(percent);
        if (mediaPlayer.getDuration()!=0) {
            int currentProgress = seekBar.getMax()
                    * mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration();

            Log.e(currentProgress + "% play", percent + " buffer");
        }
    }

}
