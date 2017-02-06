package com.cai.service.impl;

import com.cai.dao.SalaryDao;
import com.cai.domain.BonusPenalty;
import com.cai.domain.Employee;
import com.cai.domain.Salary;
import com.cai.service.BonusPenaltyService;
import com.cai.service.CheckingService;
import com.cai.service.EmployeeService;
import com.cai.service.SalaryService;
import com.cai.utils.MoneyUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/20.
 * <p>
 * 有关薪资的业务处理接口实现
 */
@Service
public class SalaryServiceImpl implements SalaryService {
    @Resource
    private SalaryDao salaryDao;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private BonusPenaltyService bonusPenaltyService;

    @Resource
    private CheckingService checkingService;

    @Override
    public boolean add(Salary salary) {
        return salaryDao.add(salary) > 0;
    }

    @Override
    public boolean remove(Salary salary) {
        return salaryDao.remove(salary) > 0;
    }

    @Override
    public boolean update(Salary salary) {
        return salaryDao.update(salary) > 0;
    }

    @Override
    public List<Salary> findAll() {
        return salaryDao.find(null);
    }

    @Override
    public List<Salary> findByIf(String ifName, String content, int id) {
        Map<String, Object> map = new HashedMap();
        map.put(ifName, id != 0 ? id : content);
        return salaryDao.find(map);
    }

    @Override
    public List<Salary> findByMap(Map<String, Object> map) {
        return salaryDao.find(map);
    }

    /**
     * 结算薪资 薪资 = 基本工资(一个月22天) + 奖励 - 惩罚 - (基本工资*20%)[社保扣除]
     *
     * @param eid    要结算的员工id
     * @param yMonth 要结算的年月
     * @return 结算情况
     */
    @Override
    public Map<String, Object> addSalary(int eid, String yMonth) {
        //基本工资
        Employee e = employeeService.findByIf("id", null, eid).get(0);
        //扣除保险
        double salary = e.getSalary() * 0.8D;
        //得到该员工一个月内的上班天数 并算出未上班天数应扣除的薪资
        salary -= (checkingService.getDaysByEid(eid, yMonth)) * employeeService.getDaySalary(eid);
        // 计算奖金和罚金
        double more = 0;
        double less = 0;
        // + 奖励 - 惩罚
        Map map = new HashedMap();
        map.put("eid", eid);
        map.put("time", yMonth + "%");
        map.put("type", "奖励");
        List<BonusPenalty> bonuses = bonusPenaltyService.findByMap(map);
        for (BonusPenalty b : bonuses) {
            more += b.getMoney();
        }
        salary += more;
        map.put("type", "惩罚");
        bonuses = bonusPenaltyService.findByMap(map);
        for (BonusPenalty b : bonuses) {
            less += b.getMoney();
        }
        salary -= less;
        //判断这时的薪资是否为负值
        if (salary < 0) {
            salary = 0.0;
        }
        Salary s = new Salary();
        s.setEmployee(e);
        s.setyMonth(yMonth);
        s.setbCost(MoneyUtil.saveTwoNumber(more));
        s.setpCost(MoneyUtil.saveTwoNumber(less));
        s.setsCost(MoneyUtil.saveTwoNumber(e.getSalary() * 0.2D));
        s.setTotal(MoneyUtil.saveTwoNumber(salary));
        add(s);
        map.put("ok", e.getResume().getRealName() + "的" + yMonth + "的薪资结算成功!");
        //打钱给该员工的工资卡内
        e.setBalance(e.getBalance() + s.getTotal());
        employeeService.update(e);
        return map;
    }
}
