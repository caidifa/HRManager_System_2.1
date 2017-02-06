package com.cai.service;

import com.cai.domain.Employee;
import com.cai.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 用户业务接口
 */
public interface EmployeeService extends BaseService<Employee> {

    @Override
    boolean add(Employee employee);

    @Override
    boolean remove(Employee employee);

    @Override
    boolean update(Employee employee);

    @Override
    List<Employee> findAll();

    @Override
    List<Employee> findByIf(String ifName, String content, int id);


    /**
     * 员工登陆 方式: ⑴普通员工level:"0" ⑵管理员level:"1"
     *
     * @param employee 用户登录时输入的信息
     * @return 回馈登陆判断的信息
     */
    Map<String, Object> findByLogin(Employee employee);

    /**
     * 获得日工资
     *
     * @param eid 员工id
     * @return 该员工的日工资
     */
    double getDaySalary(int eid);

    /**
     * 获得小时工资
     *
     * @param eid 员工id
     * @return 该员工的时工资
     */
    double getHourSalary(int eid);
}
