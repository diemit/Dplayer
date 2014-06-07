package com.diemit.dplayer.toos;

import java.io.Serializable;

/**
 * Created by Diemit on 14-2-24.
 */
 public class FileUtil implements Serializable {

    private int fileId;
    private String fileName;
    private int fileImg;
    private String fileUrl;
    private String fileSize;

    public int getFileId() {
        return fileId;
    }
    public void setFileId(int fileId) {
        this.fileId = fileId;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileImg() {
        return fileImg;
    }

    public void setFileImg(int fileImg) {
        this.fileImg = fileImg;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "id" + fileId + " 文件名" + fileName + " 路径" + fileUrl;
    }
}
