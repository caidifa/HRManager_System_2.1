package com.cai.service.impl;

import com.cai.dao.EmployeeDao;
import com.cai.dao.PositionDao;
import com.cai.domain.Employee;
import com.cai.domain.Position;
import com.cai.service.EmployeeService;
import com.cai.utils.MoneyUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 用户业务处理接口实现
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeDao employeeDao;

    @Override
    public boolean add(Employee employee) {
        return employeeDao.add(employee) > 0;
    }

    @Override
    public boolean remove(Employee employee) {
        return employeeDao.remove(employee) > 0;
    }

    @Override
    public boolean update(Employee employee) {
        return employeeDao.update(employee) > 0;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.find(null);
    }

    @Override
    public List<Employee> findByIf(String ifName, String content, int id) {
        Map<String, Object> map = new HashedMap();
        map.put(ifName, id != 0 ? id : content);
        return employeeDao.find(map);
    }

    /**
     * 员工登陆 方式: ⑴普通员工level:"0" ⑵管理员level:"1" PS:管理员等级可以登陆员工系统,反之不行
     *
     * @param employee 用户登录时输入的信息
     * @return 回馈登陆判断的信息
     */
    @Override
    public Map<String, Object> findByLogin(Employee employee) {
        //初始化引用参数
        Map<String, Object> map = new HashedMap();
        //员工登陆判断
        List<Employee> list = findByIf("empNumber", employee.getEmpNumber(), 0);
        if (list.size() == 0) {
            map.put("fail", "没有该编号!");
            return map;
        }
        Employee emp = list.get(0);
        if (!emp.getPassword().equals(employee.getPassword())) {
            map.put("fail", "密码错误!");
            return map;
        }
        //权限判断
        if ("0".equals(emp.getLevel())) {
            if ("1".equals(employee.getLevel())) {
                map.put("fail", "你没有管理员等级权限!");
                return map;
            }
        }
        //状态判断
        if ("离职".equals(emp.getStatus())) {
            map.put("fail", "你已离职!");
            return map;
        }
        map.put("success", emp);
        return map;
    }

    /**
     * 获得日工资
     *
     * @param eid 员工id
     * @return 该员工的日工资
     */
    @Override
    public double getDaySalary(int eid) {
        Map map = new HashedMap();
        map.put("id", eid);
        Employee employee = employeeDao.find(map).get(0);
        double d = employee.getSalary() / 22D;
        return MoneyUtil.saveTwoNumber(d);
    }

    /**
     * 获得小时工资
     *
     * @param eid 员工id
     * @return 该员工的时工资
     */
    @Override
    public double getHourSalary(int eid) {
        double d = getDaySalary(eid) / 8D;
        return MoneyUtil.saveTwoNumber(d);
    }


}
