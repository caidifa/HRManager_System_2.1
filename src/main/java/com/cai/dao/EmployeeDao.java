package com.cai.dao;

import com.cai.domain.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/13.
 * <p>
 * 员工数据库操作接口
 */
@Repository
public interface EmployeeDao extends BaseDao<Employee> {
    @Override
    int add(Employee employee);

    @Override
    int remove(Employee employee);

    @Override
    int update(Employee employee);

    @Override
    List<Employee> find(Map map);
}
