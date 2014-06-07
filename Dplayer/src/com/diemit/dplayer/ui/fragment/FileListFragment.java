package com.diemit.dplayer.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.diemit.dplayer.activity.R;
import com.diemit.dplayer.adapter.FileAdapter;
import com.diemit.dplayer.db.SQLHelper;
import com.diemit.dplayer.service.PlayService;
import com.diemit.dplayer.toos.FindFile;
import com.diemit.dplayer.toos.ViewItem;

/**
 * Created by Diemit on 14-2-22.
 * 文件列表Fragment
 */
public class FileListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ListView listView = null;
    private SQLHelper sqlHelper = null;
    private FileAdapter adapter = null;
    private ViewItem viewItem = null;
    private SwipeRefreshLayout swipeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.file_list_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeLayout.setOnRefreshListener(this);
        // 顶部刷新的样式
        swipeLayout.setColorScheme(android.R.color.holo_red_light, android.R.color.holo_green_light,
                android.R.color.holo_blue_bright, android.R.color.holo_orange_light);

        if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(getActivity())){
            System.out.println("没有解压解码包");
            return;
        }

        //文件列表数据获取
        sqlHelper = new SQLHelper(getActivity());

        listView = (ListView) view.findViewById(R.id.file_list);
        //自定义文件迭代器
        adapter = new FileAdapter(getActivity());
        listView.setAdapter(adapter);

        if (sqlHelper.isListEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setIcon(android.R.drawable.ic_dialog_info);
            builder.setTitle("提示");
            builder.setMessage("你的列表中没有歌曲，是否立即添加歌曲？");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    FindFile findFile = new FindFile(getActivity());
                    findFile.execute();
                    System.out.println("扫描状态" + findFile.getStatus());
                    adapter.notifyDataSetChanged();
                }
            });

            builder.setNegativeButton("取消", null);
            AlertDialog dialog = builder.create();
            dialog.show();

        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = (TextView)view.findViewById(R.id.fileName);

                viewItem = (ViewItem)view.getTag();

                Toast.makeText(getActivity(), viewItem.fileUrl, Toast.LENGTH_SHORT).show();

                System.out.println(tv.getText() + "参数 " + i + "__" + l);
                System.out.println(viewItem.fileUrl);

                Intent intent = new Intent();

                intent.setClass(getActivity(), PlayService.class);
                Bundle bundle  = new Bundle();
                bundle.putString("url",viewItem.fileUrl);
                intent.putExtras(bundle);
                getActivity().startService(intent);


            }
        });


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("文件列表碎片创建");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        System.out.println("文件列表onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        System.out.println("文件列表onStop");
        super.onStop();
    }

    @Override
    public void onStart() {
        System.out.println("文件列表碎片创建");
        super.onStart();
    }

    @Override
    public void onResume() {
        System.out.println("文件列表onStart");
        super.onResume();
    }

    @Override
    public void onDestroy() {
        System.out.println("文件列表onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        System.out.println("文件列表onDetach");
        super.onDetach();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                swipeLayout.setRefreshing(false);

                adapter.notifyDataSetChanged();
            }
        }, 500);
    }
}
