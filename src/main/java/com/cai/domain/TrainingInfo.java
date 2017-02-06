package com.cai.domain;

import java.io.Serializable;

/**
 * Created by caibaolong on 2017/1/14.
 * <p>
 * 培训详情
 * <p>
 * 创建时的 SQL语句
 * CREATE TABLE `traininginfo` (
 * `id` int(11) NOT NULL AUTO_INCREMENT,
 * `teacherID` int(11) DEFAULT NULL,
 * `content` varchar(255) DEFAULT NULL,
 * `startDate` date DEFAULT NULL,
 * `endDate` date DEFAULT NULL,
 * `place` varchar(200) DEFAULT NULL,
 * `status` varchar(20) DEFAULT NULL,
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
 */
public class TrainingInfo implements Serializable {
    private int id;
    private String content;     //培训内容
    private String startDate;   //培训开始时间
    private String endDate;     //培训结束时间
    private String place;       //培训地点
    private String status;      //状态

    private Employee teacher;   //培训老师

    public TrainingInfo() {
    }

    public TrainingInfo(int id, String content, String startDate, String endDate, String place, String status, Employee teacher) {
        this.id = id;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place;
        this.status = status;
        this.teacher = teacher;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Employee getTeacher() {
        return teacher;
    }

    public void setTeacher(Employee teacher) {
        this.teacher = teacher;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "<--TrainingInfo{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", place='" + place + '\'' +
                ", status='" + status + '\'' +
                ", \n-->teacher=" + teacher +
                '}';
    }
}
