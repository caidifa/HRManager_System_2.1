package com.cai.domain;

import java.io.Serializable;

/**
 * Created by caibaolong on 2017/1/14.
 * 培训关联表
 */
public class Training implements Serializable {
    private int id;
    private Employee employee;//参加培训的员工
    private TrainingInfo trainingInfo;//对应的培训详情表

    public Training() {
    }

    public Training(int id, Employee employee, TrainingInfo trainingInfo) {

        this.id = id;
        this.employee = employee;
        this.trainingInfo = trainingInfo;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TrainingInfo getTrainingInfo() {
        return trainingInfo;
    }

    public void setTrainingInfo(TrainingInfo trainingInfo) {
        this.trainingInfo = trainingInfo;
    }

    @Override
    public String toString() {
        return "<--Training{" +
                "id=" + id +
                ", \n-->employee=" + employee +
                ", \n-->trainingInfo=" + trainingInfo +
                '}';
    }
}
