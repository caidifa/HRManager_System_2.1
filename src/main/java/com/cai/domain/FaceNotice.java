package com.cai.domain;

import java.io.Serializable;

/**
 * Created by caibaolong on 2017/1/14.
 * 创建时的 SQL语句
 * CREATE TABLE `facenotice` (
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `empID` int(11) DEFAULT NULL,
     `postInfoID` int(11) DEFAULT NULL,
     `faceTime` datetime DEFAULT NULL,
     `location` varchar(200) DEFAULT NULL,
     `status` varchar(20) DEFAULT NULL,
     PRIMARY KEY (`id`)
   ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
 * 面试通知
 */
public class FaceNotice implements Serializable {
    private int id;
    private String faceTime;//面试时间
    private String location;//面试地点
    private String status;//状态

    private Employee employee;//面试员
    private PostInfo postInfo;//相关的投递信息

    public FaceNotice() {
    }

    public FaceNotice(int id, String faceTime, String location, String status, Employee employee, PostInfo postInfo) {
        this.id = id;
        this.faceTime = faceTime;
        this.location = location;
        this.status = status;
        this.employee = employee;
        this.postInfo = postInfo;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFaceTime() {
        return faceTime;
    }

    public void setFaceTime(String faceTime) {
        this.faceTime = faceTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PostInfo getPostInfo() {
        return postInfo;
    }

    public void setPostInfo(PostInfo postInfo) {
        this.postInfo = postInfo;
    }

    @Override
    public String toString() {
        return "FaceNotice{" +
                "id=" + id +
                ", faceTime='" + faceTime + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", \nemployee=" + employee +
                ", \npostInfo=" + postInfo +
                '}';
    }
}
