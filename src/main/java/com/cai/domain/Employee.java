package com.cai.domain;

import java.io.Serializable;

/**
 * Created by caibaolong on 2017/1/13.
 */
public class Employee implements Serializable {
    private int id;
    private String empNumber;//员工编号
    private String password;//员工密码
    private double salary;//薪水
    private double balance;//工资卡余额
    private String level;//等级
    private String status;//状态

    private Resume resume;//简历
    private Department department;//部门
    private Position position;//职位

    public Employee() {
    }

    public Employee(int id, String empNumber, String password, double salary, double balance, String level, String status, Resume resume, Department department, Position position) {
        this.id = id;
        this.empNumber = empNumber;
        this.password = password;
        this.salary = salary;
        this.balance = balance;
        this.level = level;
        this.status = status;
        this.resume = resume;
        this.department = department;
        this.position = position;
    }

    public String getEmpNumber() {

        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empNumber='" + empNumber + '\'' +
                ", password='" + password + '\'' +
                ", salary=" + salary +
                ", balance=" + balance +
                ", level='" + level + '\'' +
                ", status='" + status + '\'' +
                ", \nresume=" + resume +
                ", \ndepartment=" + department +
                ", \nposition=" + position +
                '}';
    }
}
