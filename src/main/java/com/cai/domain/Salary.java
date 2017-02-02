package com.cai.domain;

import java.io.Serializable;

/**
 * Created by caibaolong on 2017/1/14.
 * 薪资
 */
public class Salary implements Serializable {
    private int id;
    private String yMonth;//年月份
    private double bCost;//奖励费用
    private double pCost;//惩罚费用
    private double sCost;//社保扣除
    private double total;//结算工资
    private String status;//状态

    private Employee employee;//员工

    public Salary() {
    }

    public Salary(int id, String yMonth, double bCost, double pCost, double sCost, double total, String status, Employee employee) {
        this.id = id;
        this.yMonth = yMonth;
        this.bCost = bCost;
        this.pCost = pCost;
        this.sCost = sCost;
        this.total = total;
        this.status = status;
        this.employee = employee;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getyMonth() {
        return yMonth;
    }

    public void setyMonth(String yMonth) {
        this.yMonth = yMonth;
    }

    public double getbCost() {
        return bCost;
    }

    public void setbCost(double bCost) {
        this.bCost = bCost;
    }

    public double getpCost() {
        return pCost;
    }

    public void setpCost(double pCost) {
        this.pCost = pCost;
    }

    public double getsCost() {
        return sCost;
    }

    public void setsCost(double sCost) {
        this.sCost = sCost;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "<--Salary{" +
                "id=" + id +
                ", yMonth='" + yMonth + '\'' +
                ", bCost='" + bCost + '\'' +
                ", pCost='" + pCost + '\'' +
                ", sCost='" + sCost + '\'' +
                ", total='" + total + '\'' +
                ", status='" + status + '\'' +
                ", \n-->employee=" + employee +
                '}';
    }
}
