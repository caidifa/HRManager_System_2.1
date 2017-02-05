package com.cai.web.controller;

import com.cai.domain.Bonuspenalty;
import com.cai.domain.Employee;
import com.cai.domain.Salary;
import com.cai.service.BonuspenaltyService;
import com.cai.service.CheckingService;
import com.cai.service.EmployeeService;
import com.cai.service.SalaryService;
import com.cai.utils.MoneyUtil;
import com.cai.utils.TimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * 薪资操作控制
 */
@Controller
@RequestMapping("/sal")
public class SalaryController {

    @Resource
    private EmployeeService employeeService;
    @Resource
    private CheckingService checkingService;
    @Resource
    private BonuspenaltyService bonuspenaltyService;
    @Resource
    private SalaryService salaryService;

    //管理员 查看所有薪资记录
    @RequestMapping(value = "/showAllSal.do")
    public String showAllSal(Model model) {
        List<Salary> salaryList = salaryService.findAll();
        for (Salary salary : salaryList) {
            int eid = salary.getEmployee().getId();
            Employee e = employeeService.findByIf("id", null, eid).get(0);
            salary.setEmployee(e);
        }
        model.addAttribute("salaryList", salaryList);
        model.addAttribute("salaryListCount", salaryList.size());
        return "sal/info_all_admin";
    }

    //员工查询自己薪资记录
    @RequestMapping(value = "/showMySalary.do")
    public String showMySalary(int eid, Model model) {
        model.addAttribute("salaryList", salaryService.findByIf("eid", null, eid));
        return "sal/info_all_emp";
    }

    //员工查询自己薪资详情
    @RequestMapping(value = "/showSalDetail.do")
    public String showSalDetail(int sid, Model model) {
        Salary salary = salaryService.findByIf("id", null, sid).get(0);
        int eid = salary.getEmployee().getId();
        Map map = new HashMap();
        map.put("eid", eid);
        map.put("time", salary.getyMonth() + "%");
        map.put("type", "奖励");
        List<Bonuspenalty> bonusList = bonuspenaltyService.findByMap(map);
        map.put("type", "惩罚");
        List<Bonuspenalty> penaltyList = bonuspenaltyService.findByMap(map);
        int notGoDays = checkingService.getDaysByEid(eid, salary.getyMonth());
        model.addAttribute("goDays", 22 - notGoDays);
        model.addAttribute("notGoDays", notGoDays);
        double daySalary = employeeService.getDaySalary(eid);
        model.addAttribute("daySalary", daySalary);
        model.addAttribute("allNotGoSalary", daySalary * notGoDays);


        model.addAttribute("bonusList", bonusList);
        model.addAttribute("penaltyList", penaltyList);
        model.addAttribute("salary", salary);
        return "sal/info_detail_emp";
    }

    //管理员 对指定员工进行薪资结算
    @RequestMapping(value = "/salaryCount.do")
    public void salaryCount(int eid, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //1.判断当前日期是否为结算日(每月15日结算上一个整月的薪资)
        if (!TimeUtil.nowForYMD().endsWith("15")) {
            out.print("今天不是结算日(每月15日)!");
            out.close();
            return;
        }
        //2.得到要结算的年月
        String yMonth = TimeUtil.getCountDate(TimeUtil.nowForYMD().substring(0, 7));
        Map map = new HashMap();
        map.put("eid", eid);
        map.put("yMonth", yMonth);
        if (salaryService.findByMap(map).size() > 0) {
            out.print(yMonth + "已经结算过了!");
        } else {
            //进行结算操作
            map = salaryService.addSalary(eid, yMonth);
            out.print(map.get("ok"));
        }
        out.flush();
        out.close();
    }

}
