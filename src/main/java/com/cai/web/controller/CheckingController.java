package com.cai.web.controller;

import com.cai.domain.Bonuspenalty;
import com.cai.domain.Checking;
import com.cai.domain.Employee;
import com.cai.service.BonuspenaltyService;
import com.cai.service.CheckingService;
import com.cai.service.EmployeeService;
import com.cai.utils.TimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * 考勤/奖惩操作页面控制
 */
@Controller
@RequestMapping("/check")
public class CheckingController {
    @Resource
    private EmployeeService employeeService;
    @Resource
    private CheckingService checkingService;
    @Resource
    private BonuspenaltyService bonuspenaltyService;

    //<editor-fold desc="员工考勤相关操作">
    //员工 查看自己的考勤记录
    @RequestMapping(value = "/showMyChecking.do")
    public String showMyChecking(int eid, Model model) {
        List<Checking> checkingList = checkingService.findByIf("eid", null, eid);
        model.addAttribute("checkingList", checkingList);
        return "check/info_self_emp";
    }

    //员工 进入打卡操作页面
    @RequestMapping(value = "/startChecking.do")
    public String startChecking() {
        return "check/checkin_out_emp";
    }

    //员工 上班打卡操作checkIn
    @RequestMapping(value = "/checkIn.do")
    public void checkIn(int eid, String inTime, HttpServletResponse response) throws IOException, ParseException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        // if ("".equals(inTime)) {
        //     out.print("没有设置模拟当前时间");
        //     out.close();
        //     return;
        // }
        Map<String, Object> map = checkingService.addInTime(eid, TimeUtil.nowForYMDHMS());
        if (!map.containsKey("fail")) {
            out.println("打卡成功!");
            if (map.containsKey("ok")) {
                out.println(map.get("ok"));
            } else {
                out.println("迟到了" + map.get("late") + "小时");
            }
        } else {
            out.println(map.get("fail"));
        }

        out.flush();
        out.close();

    }

    //员工 下班打卡操作checkOut
    @RequestMapping(value = "/checkOut.do")
    public void checkOut(int eid, String outTime, HttpServletResponse response) throws IOException, ParseException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        // if ("".equals(outTime)) {
        //     out.print("没有设置模拟当前时间");
        //     out.close();
        //     return;
        // }
        Map<String, Object> map = checkingService.addOutTime(eid, TimeUtil.nowForYMDHMS());
        if (map.containsKey("ok")) {
            out.println(map.get("ok"));
        } else {
            out.println(map.get("fail"));
        }
        out.flush();
        out.close();
    }
    //</editor-fold>


    //管理员 查看所有考勤记录
    @RequestMapping(value = "/showAllChecking.do")
    public String showAllChecking(Model model) {
        List<Checking> checkingList = checkingService.findAll();
        model.addAttribute("checkingList", checkingList);
        return "check/info_all_admin";
    }

    //<editor-fold desc="管理员处理奖惩">
    //管理员 查看所有奖惩记录
    @RequestMapping(value = "/showAllBonus.do")
    public String showAllBonus(Model model) {
        List<Bonuspenalty> bonuspenaltyList = bonuspenaltyService.findAll();
        model.addAttribute("bonuspenaltyList", bonuspenaltyList);
        return "bonus/info_all_admin";
    }

    //管理员 接受并处理复议
    @RequestMapping(value = "/handleReview.do")
    public void handleReview(int blid, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        Bonuspenalty b = bonuspenaltyService.findByIf("id", null, blid).get(0);
        b.setStatus("已处理");
        bonuspenaltyService.update(b);
        b.setReason("对奖惩ID为" + blid + "的复议处理");
        if ("惩罚".equals(b.getType())) {
            b.setType("奖励");
        } else {
            b.setType("惩罚");
        }
        b.setStatus(null);
        bonuspenaltyService.add(b);
        out.print("已处理");
        out.close();
    }

    //管理员 驳回并取消复议
    @RequestMapping(value = "/cancelReview.do")
    public void cancelReview(int blid, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        Bonuspenalty b = bonuspenaltyService.findByIf("id", null, blid).get(0);
        b.setStatus("已驳回");
        bonuspenaltyService.update(b);
        out.print("已驳回");
        out.close();
    }
    //</editor-fold>

    //员工 查看自己的奖惩记录
    @RequestMapping(value = "/showMyBonus.do")
    public String showMyBonus(int eid, Model model) {
        List<Bonuspenalty> bonuspenaltyList = bonuspenaltyService.findByIf("eid", null, eid);
        model.addAttribute("bonuspenaltyList", bonuspenaltyList);
        return "bonus/info_show_emp";
    }

    //员工 对一条奖惩记录进行复议
    @RequestMapping(value = "/review.do")
    public String review(int blid, Model model) {
        // 要复议的奖惩id
        model.addAttribute("blid", blid);
        return "bonus/review_update_emp";
    }

    //员工 复议确认
    @RequestMapping(value = "/reviewConfirm.do")
    public void reviewConfirm(Bonuspenalty bonuspenalty, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        if ("".equals(bonuspenalty.getStatus())) {
            out.print("复议内容不能为空!");
            out.close();
            return;
        }
        Bonuspenalty bp = bonuspenaltyService.findByIf("id", null, bonuspenalty.getId()).get(0);
        bp.setStatus(bonuspenalty.getStatus());
        bonuspenaltyService.update(bp);
        out.print("ok");
        out.close();
    }

    //管理员 奖惩的添加页面
    @RequestMapping(value = "/addBP.do")
    public String addBP(Model model) {
        List<Employee> employeeList = employeeService.findByIf("status", "在职", 0);
        model.addAttribute("employeeList", employeeList);
        return "bonus/info_add_admin";
    }

    //管理员 奖惩的添加确认
    @RequestMapping(value = "/addBPConfirm.do")
    public void addBPConfirm(Bonuspenalty bp, int eid, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        if (eid == 0) {
            out.print("请选择员工!");
            out.close();
            return;
        }
        if ("".equals(bp.getReason())) {
            out.print("原因不能为空!");
            out.close();
            return;
        }
        if (bp.getMoney() <= 0) {
            out.print("奖惩金不能小于等于0!");
            out.close();
            return;
        }
        Employee e = new Employee();
        e.setId(eid);
        bp.setEmployee(e);
        bp.setTime(TimeUtil.nowForYMD());
        bonuspenaltyService.add(bp);
        out.print("ok");
        out.close();
    }


}
