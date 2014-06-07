package com.diemit.dplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;

import com.diemit.dplayer.ExitApplication;
import com.diemit.dplayer.adapter.PagerAdapter;
import com.diemit.dplayer.service.PlayService;
import com.diemit.dplayer.toos.FindFile;

public class MainActivity extends ActionBarActivity {
    public static SurfaceView surfaceView = null;
    PagerAdapter pagerAdapter;
    ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ExitApplication.getInstance().addActivity(this);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(1);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_exit) {
            Intent intent = new Intent();
            intent.setClass(this, PlayService.class);
            this.stopService(intent);

            ExitApplication.getInstance().exit();

        }
        if (id == R.id.action_search){
            FindFile findFile = new FindFile(this);
            findFile.execute();

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {

        System.out.println("活动销毁！！");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        System.out.println("活动onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        System.out.println("活动onResume");
        super.onResume();
    }

    @Override
    protected void onStart() {
        System.out.println("活动onStart");
        super.onStart();
    }

}
