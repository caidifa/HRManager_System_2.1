package com.cai.domain;

import java.io.Serializable;

/**
 * Created by caibaolong on 2017/1/14.
 * <p>
 * 招聘信息
 * <p>
 * 创建时的 SQL语句
 * CREATE TABLE `hireinfo` (
 * `id` int(11) NOT NULL AUTO_INCREMENT,
 * `departID` int(11) DEFAULT NULL,
 * `positionID` int(11) DEFAULT NULL,
 * `needNumber` int(11) DEFAULT NULL,
 * `status` varchar(20) DEFAULT NULL,
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
 */
public class HireInfo implements Serializable {
    private int id;
    private int needNumber;         //招聘人数
    private String status;          //状态

    private Department department;  //部门
    private Position position;      //职位

    public HireInfo() {
    }

    public HireInfo(int id, int needNumber, String status, Department department, Position position) {

        this.id = id;
        this.needNumber = needNumber;
        this.status = status;
        this.department = department;
        this.position = position;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNeedNumber() {
        return needNumber;
    }

    public void setNeedNumber(int needNumber) {
        this.needNumber = needNumber;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "<--HireInfo{" +
                "id=" + id +
                ", needNumber=" + needNumber +
                ", status='" + status + '\'' +
                ", \n-->department=" + department +
                ", \n-->position=" + position +
                '}';
    }
}
