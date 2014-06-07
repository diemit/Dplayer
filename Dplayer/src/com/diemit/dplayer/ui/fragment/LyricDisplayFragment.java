package com.diemit.dplayer.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.diemit.dplayer.activity.MainActivity;
import com.diemit.dplayer.activity.R;

/**
 * Created by Diemit on 14-2-23.
 */
public class LyricDisplayFragment extends Fragment {
    TextView showsql;
    Button exsql;
    EditText editsql;
    public static SurfaceView surfaceView;//声明一个临时的surfaceview保存此碎片的surfaceView

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.lyric_display_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        surfaceView = (SurfaceView)view.findViewById(R.id.videoSurface2);//获取surfaceView

        //MainActivity.surfaceView = surfaceView;//把surfaceView传到主活动

        super.onViewCreated(view, savedInstanceState);
    }
}
