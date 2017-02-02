package com.cai.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by caibaolong on 2017/1/14.
 * 部门类
 */
public class Department implements Serializable {
    private int id;
    private String dName;//部门名
    private String createTime;//创建时间
    private String status;//状态
    private List<Position> positionList;//该部门下的所有职位

    public Department() {
    }

    public Department(int id, String dName, String createTime, String status, List<Position> positionList) {
        this.id = id;
        this.dName = dName;
        this.createTime = createTime;
        this.status = status;
        this.positionList = positionList;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
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

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", dName='" + dName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status='" + status + '\'' +
                ", \npositionList=" + positionList +
                '}';
    }
}
