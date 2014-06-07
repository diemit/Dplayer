package com.diemit.dplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.diemit.dplayer.activity.R;
import com.diemit.dplayer.db.SQLHelper;
import com.diemit.dplayer.toos.FileUtil;
import com.diemit.dplayer.toos.ViewItem;

import java.util.ArrayList;

/**
 * Created by Diemit on 14-2-23.
 * 自定义文件列表Adapter
 */
public class FileAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    SQLHelper sqlHelper;
    ArrayList<FileUtil> list;

    public FileAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        sqlHelper = new SQLHelper(mContext);
        list = sqlHelper.getList();
    }

    @Override
    public int getCount() {
        return sqlHelper.getListSize();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.file_item, null);

            holder.fileName = (TextView) convertView.findViewById(R.id.fileName);
            holder.fileImage = (ImageView) convertView.findViewById(R.id.fileImage);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.holderId = list.get(i).getFileId();
        holder.fileName.setText(list.get(i).getFileName());
        holder.fileImage.setImageResource(list.get(i).getFileImg());
        holder.fileUrl = list.get(i).getFileUrl();

        return convertView;
    }

    static class ViewHolder extends ViewItem {
        TextView fileName;
        ImageView fileImage;
    }
}

