package com.cai.domain;

import java.io.Serializable;

/**
 * Created by caibaolong on 2017/1/19.
 * 考勤类
 */
public class Checking implements Serializable{
    private int id;
    private String checkDate;//日期
    private String inTime;//上班打卡时间
    private String outTime;//下班打卡时间
    private String result;//结果

    private Employee employee;//员工

    public Checking() {
    }

    public Checking(int id, String checkDate, String inTime, String outTime, String result, Employee employee) {

        this.id = id;
        this.checkDate = checkDate;
        this.inTime = inTime;
        this.outTime = outTime;
        this.result = result;
        this.employee = employee;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "<--Checking{" +
                "id=" + id +
                ", checkDate='" + checkDate + '\'' +
                ", inTime='" + inTime + '\'' +
                ", outTime='" + outTime + '\'' +
                ", result='" + result + '\'' +
                ", \n-->employee=" + employee +
                '}';
    }
}
