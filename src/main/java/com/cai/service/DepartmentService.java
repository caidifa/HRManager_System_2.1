package com.cai.service;

import com.cai.domain.Department;
import com.cai.domain.HireInfo;

import java.util.List;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 部门的业务基本接口
 */
public interface DepartmentService extends BaseService<Department> {
    @Override
    boolean add(Department department);

    @Override
    boolean remove(Department department);

    @Override
    boolean update(Department department);

    @Override
    List<Department> findAll();

    @Override
    List<Department> findByIf(String ifName, String content, int id);

}
