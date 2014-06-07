package com.diemit.dplayer.toos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import com.diemit.dplayer.activity.R;
import com.diemit.dplayer.db.SQLHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Diemit on 14-2-23.
 */
public class FindFile extends AsyncTask<Void,Integer,ArrayList<FileUtil>> {
    /**
     * 遍历指定文件夹下的资源文件
     *
     * @param folder 文件
     */
    private ProgressDialog pd;
    private static ArrayList<FileUtil> fileUtilArrayList = new ArrayList<FileUtil>();
    private Context context = null;
    SQLHelper sql;
    public FindFile(Context context){
        this.context = context;

    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(context);
        sql = new SQLHelper(context);
        pd.setTitle("正在扫描媒体文件...");
        pd.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<FileUtil> list) {
        super.onPostExecute(list);
        pd.dismiss();
        Toast.makeText(context, "--扫描完成--", Toast.LENGTH_SHORT).show();

    }

    public ArrayList<FileUtil> simpleScanning(File folder) {
        //指定正则表达式
        Pattern mPattern = Pattern.compile("([^\\.]*)\\.([^\\.]*)");
        // 当前目录下的所有文件
        final String[] filenames = folder.list();
        // 当前目录的名称
        //final String folderName = folder.getName();
        // 当前目录的绝对路径
        //final String folderPath = folder.getAbsolutePath();
        if (filenames != null) {
            // 遍历当前目录下的所有文件
            for (String name : filenames) {
                File file = new File(folder, name);
                // 如果是文件夹则继续递归当前方法
                if (file.isDirectory()) {
                    //忽略.开头的文件夹系统文件夹
                    if (!file.getAbsolutePath().startsWith(".") && file.getAbsolutePath().indexOf("data") == -1) {
                        simpleScanning(file);
                    }
                }
                // 如果是文件则对文件进行相关操作
                else if((file.length()/1048576) > 2) {
                    Matcher matcher = mPattern.matcher(name);
                    if (matcher.matches()) {
                        // 文件名称
                        String fileName = matcher.group(1);
                        // 文件后缀
                        String fileExtension = matcher.group(2);
                        // 文件路径
                        String filePath = file.getAbsolutePath();

                        if (FindFile.isMusic(fileExtension)) {
                            // 初始化音乐文件......................
//                            System.out.println("This file is Music File,fileName=" + fileName + "."
//                                    + fileExtension + ",filePath=" + filePath);
                            FileUtil fileUtil = new FileUtil();
                            fileUtil.setFileName(fileName + "." + fileExtension);
                            fileUtil.setFileImg(R.drawable.music_icon);
                            fileUtil.setFileUrl(filePath);
                            fileUtilArrayList.add(fileUtil);

                        }

                        if (FindFile.isVideo(fileExtension)) {
                            // 初始化视频文件......................
//                            System.out.println("This file is Video File,fileName=" + fileName + "."
//                                    + fileExtension + ",filePath=" + filePath);
                            FileUtil fileUtil = new FileUtil();
                            fileUtil.setFileName(fileName + "." + fileExtension);
                            fileUtil.setFileImg(R.drawable.video_icon);
                            fileUtil.setFileUrl(filePath);
                            fileUtilArrayList.add(fileUtil);


                        }
                    }
                }
            }
        }
        return fileUtilArrayList;
    }

    /**
     * 判断是否是音乐文件
     *
     * @param extension 后缀名
     * @return
     */
    public static boolean isMusic(String extension) {
        if (extension == null)
            return false;

        final String ext = extension.toLowerCase();
        if (ext.equals("mp3") || ext.equals("m4a") || ext.equals("wav") ||
                ext.equals("aac") || ext.equals("flac") || ext.equals("wma") || ext.equals("ape")) {
            return true;
        }
        return false;
    }


    /**
     * 判断是否是视频文件
     *
     * @param extension 后缀名
     * @return
     */
    public static boolean isVideo(String extension) {
        if (extension == null)
            return false;

        final String ext = extension.toLowerCase();


        if (ext.equals("mpeg") || ext.equals("mp4") || ext.equals("mov") || ext.equals("m4v") ||
                ext.equals("3gp") || ext.equals("3gpp") || ext.equals("3g2") ||
                ext.equals("3gpp2") || ext.equals("avi") || ext.equals("divx") ||
                ext.equals("wmv") || ext.equals("asf") || ext.equals("flv") ||
                ext.equals("mkv") || ext.equals("mpg") || ext.equals("rmvb") ||
                ext.equals("rm") || ext.equals("vob") || ext.equals("f4v")) {
            return true;
        }
        return false;
    }

    @Override
    protected ArrayList<FileUtil> doInBackground(Void... voids) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            System.out.println("储存卡可用");
        }
        simpleScanning(Environment.getExternalStorageDirectory());
        sql.saveList(fileUtilArrayList);
        return null;
    }

}
