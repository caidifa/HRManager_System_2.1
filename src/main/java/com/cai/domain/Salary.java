package com.cai.domain;

import java.io.Serializable;

/**
 * Created by caibaolong on 2017/1/14.
 * <p>
 * 创建时的 SQL语句
 * <p>
 * 薪资
 * <p>
 * CREATE TABLE `salary` (
 * `id` int(11) NOT NULL AUTO_INCREMENT,
 * `empID` int(11) DEFAULT NULL,
 * `yMonth` varchar(10) DEFAULT NULL,
 * `bCost` double(10,2) DEFAULT NULL,
 * `pCost` double(10,2) DEFAULT NULL,
 * `sCost` double(10,2) DEFAULT NULL,
 * `total` double(10,2) DEFAULT NULL,
 * `status` varchar(20) DEFAULT NULL,
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
 */
public class Salary implements Serializable {
    private int id;
    private String yMonth;      //年月份
    private double bCost;       //奖励费用
    private double pCost;       //惩罚费用
    private double sCost;       //社保扣除
    private double total;       //结算工资
    private String status;      //状态

    private Employee employee;  //员工

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
