package com.cai.domain;

import java.io.Serializable;

/**
 * Created by caibaolong on 2017/1/13.
 *
 * 创建时的 SQL语句
 * CREATE TABLE `resume` (
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `userID` int(11) DEFAULT NULL,
     `realName` varchar(20) DEFAULT NULL,
     `idCard` varchar(18) DEFAULT NULL,
     `sex` varchar(4) DEFAULT NULL,
     `birthday` date DEFAULT NULL,
     `address` varchar(255) DEFAULT NULL,
     `married` varchar(4) DEFAULT NULL,
     `hobby` varchar(255) DEFAULT NULL,
     `major` varchar(20) DEFAULT NULL,
     `education` varchar(20) DEFAULT NULL,
     `experience` varchar(255) DEFAULT NULL,
     `selfIntroduce` varchar(255) DEFAULT NULL,
     `status` varchar(20) DEFAULT NULL,
     PRIMARY KEY (`id`)
   ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
 *
 * 简历
 */
public class Resume implements Serializable {
    private int id;
    private String realName;//真实姓名
    private String idCard;//身份证号
    private String sex;//性别
    private String birthday;//出生年月
    private String address;//住址
    private String married;//婚姻状况
    private String hobby;//爱好
    private String major;//专业
    private String education;//教育程度
    private String experience;//工作经验
    private String selfIntroduce;//自我介绍
    private String status;//状态

    private User user;//用户

    public Resume() {
    }

    public Resume(int id, String realName, String idCard, String sex, String birthday, String address, String married, String hobby, String major, String education, String experience, String selfIntroduce, String status, User user) {

        this.id = id;
        this.realName = realName;
        this.idCard = idCard;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
        this.married = married;
        this.hobby = hobby;
        this.major = major;
        this.education = education;
        this.experience = experience;
        this.selfIntroduce = selfIntroduce;
        this.status = status;
        this.user = user;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSelfIntroduce() {
        return selfIntroduce;
    }

    public void setSelfIntroduce(String selfIntroduce) {
        this.selfIntroduce = selfIntroduce;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", realName='" + realName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", married='" + married + '\'' +
                ", hobby='" + hobby + '\'' +
                ", major='" + major + '\'' +
                ", education='" + education + '\'' +
                ", experience='" + experience + '\'' +
                ", selfIntroduce='" + selfIntroduce + '\'' +
                ", status='" + status + '\'' +
                ", \nuser=" + user +
                '}';
    }
}
