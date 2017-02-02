package com.cai.dao;

import com.cai.domain.Salary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/19.
 * 对薪资表的数据库操作
 */
@Repository
public interface SalaryDao extends BaseDao<Salary> {
    @Override
    int add(Salary salary);

    @Override
    int remove(Salary salary);

    @Override
    int update(Salary salary);

    @Override
    List<Salary> find(Map map);
}
