package com.diemit.dplayer.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.diemit.dplayer.activity.MainActivity;
import com.diemit.dplayer.activity.R;
import com.diemit.dplayer.service.PlayService;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by Diemit on 14-2-21.
 */
public class PlayFragment extends android.support.v4.app.Fragment {

    SurfaceView surfaceView;//声明一个临时的surfaceview保存此碎片的surfaceView


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.play_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Button button = (Button)view.findViewById(R.id.playbtn);
        surfaceView = (SurfaceView)view.findViewById(R.id.videoSurface1);//获取surfaceView

        MainActivity.surfaceView = surfaceView;//把surfaceView传到主活动

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        super.onViewCreated(view, savedInstanceState);

    }
}