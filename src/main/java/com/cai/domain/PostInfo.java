package com.cai.domain;

import java.io.Serializable;

/**
 * Created by caibaolong on 2017/1/15.
 * <p>
 * 投递信息
 * <p>
 * 创建时的 SQL语句
 * CREATE TABLE `postinfo` (
 * `id` int(11) NOT NULL AUTO_INCREMENT,
 * `resumeID` int(11) DEFAULT NULL,
 * `hireInfoID` int(11) DEFAULT NULL,
 * `remark` varchar(20) DEFAULT NULL,
 * `status` varchar(20) DEFAULT NULL,
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
 */
public class PostInfo implements Serializable {
    private int id;
    private String remark;      //阅读标记
    private String status;      //状态

    private Resume resume;      //简历
    private HireInfo hireInfo;  //招聘信息

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
        return "<--PostInfo{" +
                "id=" + id +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", \n-->resume=" + resume +
                ", \n-->hireInfo=" + hireInfo +
                '}';
    }
}
