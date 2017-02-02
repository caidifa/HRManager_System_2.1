package com.cai.domain;

import java.io.Serializable;

/**
 * Created by caibaolong on 2017/1/15.
 * 投递信息
 */
public class PostInfo implements Serializable {
    private int id;
    private String remark;//阅读标记
    private String status;//状态

    private Resume resume;//简历
    private HireInfo hireInfo;//招聘信息

    public PostInfo() {
    }

    public PostInfo(int id, String remark, String status, Resume resume, HireInfo hireInfo) {

        this.id = id;
        this.remark = remark;
        this.status = status;
        this.resume = resume;
        this.hireInfo = hireInfo;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public HireInfo getHireInfo() {
        return hireInfo;
    }

    public void setHireInfo(HireInfo hireInfo) {
        this.hireInfo = hireInfo;
    }

    @Override
    public String toString() {
        return "PostInfo{" +
                "id=" + id +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", \nresume=" + resume +
                ", \nhireInfo=" + hireInfo +
                '}';
    }
}
