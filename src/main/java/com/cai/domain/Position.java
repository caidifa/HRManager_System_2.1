package com.cai.domain;

import java.io.Serializable;

/**
 * Created by caibaolong on 2017/1/14.
 * 职位
 */
public class Position implements Serializable {
    private int id;
    private String pName;//职位名
    private double basicSalary;//起薪
    private String createTime;//创建时间
    private String status;//状态

    private Department department;//部门

    public Position() {
    }

    public Position(int id, String pName, double basicSalary, String createTime, String status, Department department) {
        this.id = id;
        this.pName = pName;
        this.basicSalary = basicSalary;
        this.createTime = createTime;
        this.status = status;
        this.department = department;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", pName='" + pName + '\'' +
                ", basicSalary=" + basicSalary +
                ", createTime='" + createTime + '\'' +
                ", status='" + status + '\'' +
                ", \ndepartment=" + department +
                '}';
    }
}
