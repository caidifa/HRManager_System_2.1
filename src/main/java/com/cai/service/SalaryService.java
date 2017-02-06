package com.cai.service;

import com.cai.domain.Salary;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/20.
 * <p>
 * 薪资的业务基本接口
 */
public interface SalaryService extends BaseService<Salary> {
    @Override
    boolean add(Salary salary);

    @Override
    boolean remove(Salary salary);

    @Override
    boolean update(Salary salary);

    @Override
    List<Salary> findAll();

    @Override
    List<Salary> findByIf(String ifName, String content, int id);

    List<Salary> findByMap(Map<String, Object> map);

    /**
     * 结算薪资
     *
     * @param eid    要结算的员工id
     * @param yMonth 要结算的年月
     * @return 结算情况
     */
    Map<String, Object> addSalary(int eid, String yMonth);
}
