package com.diemit.dplayer.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.LinearLayout;

import com.diemit.dplayer.activity.MainActivity;
import com.diemit.dplayer.ui.fragment.LyricDisplayFragment;
import com.diemit.dplayer.ui.fragment.PlayFragment;

import java.io.IOException;
import java.util.logging.Logger;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;

/**
 * Created by Diemit on 14-2-28.
 */
public class PlayService extends Service implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback {

    private static final String TAG = "Dplayer";
    static MediaPlayer mediaPlayer = null;
    private SurfaceView surfaceView = MainActivity.surfaceView;
    //private SurfaceView surfaceView2 = LyricDisplayFragment.surfaceView;
    private SurfaceHolder surfaceHolder;
    private MediaController mediaController;
    private int mVideoWidth;
    private int mVideoHeight;
    private boolean mIsVideoSizeKnown = false;
    private boolean mIsVideoReadyToBePlayed = false;

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        //创建播放服务的时候新建播放器
        mediaPlayer = new MediaPlayer(this);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFormat(PixelFormat.RGBA_8888);
        surfaceHolder.setKeepScreenOn(true);
        //媒体播放完成后如何处理
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
            }
        });

        System.out.println("服务创建————");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("服务开始————" + "flags" + flags + " startId" + startId);
        Bundle bundle = intent.getExtras();
        String url = bundle.getString("url");
        System.out.println("播放连接" + url);
        play(url);
        return 1;

    }
    //媒体播放
    public void play(String url) {

        try {
            if (mediaPlayer.isPlaying()){
                mediaPlayer.reset();
            }
            mediaPlayer.setDataSource(url);
            mediaPlayer.setDisplay(surfaceHolder);
            //mediaPlayer.setDisplay(surfaceHolder2);
            mediaPlayer.prepare();
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);


        } catch (IOException e) {
            e.printStackTrace();
        }

        //mediaPlayer.releaseDisplay();
        mediaPlayer.getMetadata();
        //mediaPlayer.start();

        System.out.println("播放————" + url);
    }

    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            try {
                // 在调用stop后如果需要再次通过start进行播放,需要之前调用prepare函数
                mediaPlayer.prepare();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        System.out.println("surface创建 called");
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        System.out.println("surface改变 called");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        System.out.println("surface销毁 called");
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        Log.d(TAG, "onBufferingUpdate percent:" + percent);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.d(TAG, "onCompletion called");
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d(TAG, "onPrepared called");
        mIsVideoReadyToBePlayed = true;
        if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) {
            startVideoPlayback();
        }
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        if (width == 0 || height == 0) {
            Log.e(TAG, "视频的宽(" + width + ") 视频的高(" + height + ")");
            return;
        }
        mIsVideoSizeKnown = true;

//        mVideoWidth = mediaPlayer.getVideoWidth();//视频的宽度
//        mVideoHeight = mediaPlayer.getVideoHeight();//视频的高度

        mVideoWidth = width;
        mVideoHeight = height;

        if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) {
            startVideoPlayback();
        }
    }
    private void startVideoPlayback() {
        Log.v(TAG, "开始播放");

        int displayWith = surfaceView.getWidth();//获取布局的宽度
        int displayHeight = surfaceView.getHeight();//获取布局的高度
/*
        //视频显示高度要重新调整
        if (mVideoWidth > displayWith){

            mVideoHeight = mVideoHeight * displayWith / mVideoWidth;
            mVideoWidth = displayWith;

            System.out.println("布局高度：" + displayHeight);

            System.out.println("视频高度：" + mVideoHeight);
        }
        */
        surfaceHolder.setFixedSize(mVideoWidth, mVideoHeight);
        System.out.println("视频宽度：" + mVideoWidth);


        mediaPlayer.start();
    }
}
