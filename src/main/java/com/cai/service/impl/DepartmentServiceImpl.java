package com.cai.service.impl;

import com.cai.dao.DepartmentDao;
import com.cai.domain.Department;
import com.cai.service.DepartmentService;
import com.cai.utils.TimeUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/15.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentDao departmentDao;

    //添加部门时 保持部门名唯一
    @Override
    public boolean add(Department department) {
        if (findByIf("dName", department.getdName(), 0).size() > 0) {
            return false;
        }
        //设置创建时间为当前日
        department.setCreateTime(TimeUtil.nowForYMD());
        return departmentDao.add(department) > 0;
    }

    @Override
    public boolean remove(Department department) {
        return departmentDao.remove(department) > 0;
    }

    //修改部门时 保持部门名唯一
    @Override
    public boolean update(Department department) {
        String dName = department.getdName();
        if (findByIf("dName", dName, 0).size() > 0) {
            return false;
        }
        Department dep = findByIf("id", null, department.getId()).get(0);
        dep.setdName(dName);
        return departmentDao.update(dep) > 0;
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.find(null);
    }

    @Override
    public List<Department> findByIf(String ifName, String content, int id) {
        Map<String, Object> map = new HashedMap();
        if (id != 0) {
            map.put(ifName, id);
        } else {
            map.put(ifName, content);
        }
        List<Department> list = departmentDao.find(map);
        return list;
    }

}
