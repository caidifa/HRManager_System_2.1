package com.cai.domain;

import java.io.Serializable;

/**
 * Created by caibaolong on 2017/1/12.
 * 用户类
 */
public class User implements Serializable {
    private int id;
    private String email;//注册邮箱
    private String phone;//注册手机号
    private String password;//登陆密码
    private String status;//状态

    private Resume resume;//简历

    public User() {
    }

    public User(int id, String email, String phone, String password, String status, Resume resume) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.status = status;
        this.resume = resume;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", \nresume=" + resume +
                '}';
    }
}
