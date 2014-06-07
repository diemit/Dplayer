package com.diemit.dplayer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.diemit.dplayer.ui.fragment.FileListFragment;
import com.diemit.dplayer.ui.fragment.LyricDisplayFragment;
import com.diemit.dplayer.ui.fragment.PlayFragment;

/**
 * Created by Diemit on 14-2-22.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    PlayFragment playFragment = new PlayFragment();
    FileListFragment fileListFrament = new FileListFragment();
    LyricDisplayFragment lyricDisplayFragment = new LyricDisplayFragment();

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        System.out.println("position " + position);
        switch (position){
            case 0: return fileListFrament;
            case 2: return lyricDisplayFragment;
            case 1:
            default: return playFragment;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

}
