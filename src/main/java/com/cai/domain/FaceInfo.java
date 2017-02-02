package com.cai.domain;

import java.io.Serializable;

/**
 * Created by caibaolong on 2017/1/16.
 * 面试情况
 */
public class FaceInfo implements Serializable {
    private int id;
    private int penScores;//笔试分数
    private int faceScores;//面试官评分
    private String status;//状态

    private FaceNotice faceNotice;

    public FaceInfo() {
    }

    public FaceInfo(int id, int penScores, int faceScores, String status, FaceNotice faceNotice) {

        this.id = id;
        this.penScores = penScores;
        this.faceScores = faceScores;
        this.status = status;
        this.faceNotice = faceNotice;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPenScores() {
        return penScores;
    }

    public void setPenScores(int penScores) {
        this.penScores = penScores;
    }

    public int getFaceScores() {
        return faceScores;
    }

    public void setFaceScores(int faceScores) {
        this.faceScores = faceScores;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FaceNotice getFaceNotice() {
        return faceNotice;
    }

    public void setFaceNotice(FaceNotice faceNotice) {
        this.faceNotice = faceNotice;
    }

    @Override
    public String toString() {
        return "FaceInfo{" +
                "id=" + id +
                ", penScores=" + penScores +
                ", faceScores=" + faceScores +
                ", status='" + status + '\'' +
                ", faceNotice=" + faceNotice +
                '}';
    }
}
