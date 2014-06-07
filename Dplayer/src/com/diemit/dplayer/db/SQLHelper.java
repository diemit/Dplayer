package com.diemit.dplayer.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.diemit.dplayer.toos.FileUtil;

import java.util.ArrayList;

/**
 * Created by Diemit on 14-2-25.
 */
public class SQLHelper extends SQLiteOpenHelper {
    private static final String NAME = "DPlayer"; //数据库名称
    private static final int VERSION = 1;
    private static final String CREATE_TABLE = "create table playlist(file_id integer primary key autoincrement," +
            "file_name varchar(64),file_type int(20),file_url varchar(255))";


    public SQLHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    //第一次创建数据库的时候建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }
    //删除久数据库，新建新数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("drop table if exists playlist");
        onCreate(db);
    }
    public int getListSize(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from playlist",null);

        int counts = cursor.getCount();
        return counts;
    }
    //检测列表数据是否为空
    public boolean isListEmpty() {
        boolean result = false;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from playlist",null);

        int counts = cursor.getCount();
        System.out.println("列表是否为空 列表数据大小为：" + counts);
        if (counts == 0){
            result = true;
        }

        closeDb(db, cursor);

        return result;
    }
    //搜索文件列表
    public ArrayList<FileUtil> getList(){
        ArrayList<FileUtil> result = new ArrayList<FileUtil>();
        FileUtil fileUtil = null;

        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from playlist";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()){
            fileUtil = new FileUtil();
            fileUtil.setFileId(cursor.getInt(0));

//            System.out.println("id的index" + cursor.getColumnIndex("file_id") +
//                    cursor.getColumnIndex("file_name") + cursor.getColumnIndex("file_type") +
//                    cursor.getColumnIndex("file_url"));
                System.out.println("数据库取出文件名：" + cursor.getString(1));

            fileUtil.setFileName(cursor.getString(1));
            fileUtil.setFileImg(cursor.getInt(2));
            fileUtil.setFileUrl(cursor.getString(3));

            result.add(fileUtil);
        }

        System.out.println("查询列表数据大小为：" + result.size());
        closeDb(db, null);
        return result;
    }
    //保存文件列表
    public void saveList(ArrayList<FileUtil> list){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("drop table if exists playlist");
        onCreate(db);
        String sql = "insert into playlist(file_name,file_type,file_url) values(?,?,?)";
        for(FileUtil f : list){
            db.execSQL(sql,new Object[]{f.getFileName(),f.getFileImg(),f.getFileUrl()});
        }
        System.out.println("保存文件列表——————————");
        closeDb(db, null);
    }
    //保存单文件
    public void saveFile(FileUtil f){
        SQLiteDatabase db = getWritableDatabase();

    }


    //关闭数据库
    public void closeDb(SQLiteDatabase db, Cursor c) { 
        if (c != null) {
            c.close();
        }
        if (db != null) {
            db.close();
        }
        System.out.println("数据库库关闭————————————");
        close();
    }


}
