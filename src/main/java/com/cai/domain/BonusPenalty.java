package com.cai.domain;

import java.io.Serializable;

/**
 * Created by caibaolong on 2017/1/14.
 * <p>
 * 奖惩
 * <p>
 * 创建时的 SQL语句
 * <p>
 * CREATE TABLE `bonuspenalty` (
 * `id` int(11) NOT NULL AUTO_INCREMENT,
 * `empID` int(11) DEFAULT NULL,
 * `reason` varchar(255) DEFAULT NULL,
 * `time` date DEFAULT NULL,
 * `money` double(10,2) DEFAULT NULL,
 * `type` varchar(20) DEFAULT NULL,
 * `status` varchar(20) DEFAULT NULL,
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
 */
public class BonusPenalty implements Serializable {
    private int id;
    private String reason;      //奖惩理由
    private String time;        //时间
    private double money;       //奖惩金
    private String type;        //奖惩类型
    private String status;      //状态:是否有复议

    private Employee employee;  //奖惩对象

    public BonusPenalty() {
    }

    public BonusPenalty(int id, String reason, String time, double money, String type, String status, Employee employee) {
        this.id = id;
        this.reason = reason;
        this.time = time;
        this.money = money;
        this.type = type;
        this.status = status;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "<--BonusPenalty{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", time='" + time + '\'' +
                ", money=" + money +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", \n-->employee=" + employee +
                '}';
    }
}
