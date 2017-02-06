package com.cai.dao;

import com.cai.domain.Department;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 部门数据库操作接口
 */
@Repository
public interface DepartmentDao extends BaseDao<Department> {
    @Override
    int add(Department department);

    @Override
    int remove(Department department);

    @Override
    int update(Department department);

    @Override
    List<Department> find(Map map);

}
