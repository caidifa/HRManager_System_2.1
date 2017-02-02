package com.cai.web.controller;

import com.cai.domain.Employee;
import com.cai.domain.Training;
import com.cai.domain.TrainingInfo;
import com.cai.service.DepartmentService;
import com.cai.service.EmployeeService;
import com.cai.service.TrainingInfoService;
import com.cai.service.TrainingService;
import com.cai.utils.TimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/17.
 * 培训相关的页面控制
 */
@Controller
@RequestMapping("/train")
public class TrainingController {
    @Resource
    private TrainingService trainingService;
    @Resource
    private TrainingInfoService trainingInfoService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private DepartmentService departmentService;

    //<editor-fold desc="管理员进行培训相关的操作">
    // 管理员 查看所有培训信息
    @RequestMapping(value = "/showAllTrain")
    public String showAllTrain(Model model) {
        List<TrainingInfo> trainingInfoList = trainingInfoService.findAll();
        for (TrainingInfo ti : trainingInfoList) {
            int eid = ti.getTeacher().getId();
            Employee e = employeeService.findByIf("id", null, eid).get(0);
            ti.setTeacher(e);
        }
        model.addAttribute("trainingInfoList", trainingInfoList);
        return "train/info_show_admin";
    }

    // 管理员 添加培训
    @RequestMapping(value = "/addTrain")
    public String addTrain(Model model) {
        //培训老师
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "train/train_add_admin";
    }

    // 管理员 确认添加培训
    @RequestMapping(value = "/addTrainConfirm")
    public void addTrainConfirm(int eid, TrainingInfo trainingInfo, HttpServletResponse response) throws IOException, ParseException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        // 先拦截管理员输入的错误信息
        if (eid == 0) {
            out.print("没有选择培训老师");
            out.close();
            return;
        }
        if ("".equals(trainingInfo.getContent()) || "".equals(trainingInfo.getPlace())) {
            out.print("内容和地址不为能空!");
            out.close();
            return;
        }
        //日期比较
        String startDate = trainingInfo.getStartDate();
        String endDate = trainingInfo.getEndDate();
        String now = TimeUtil.nowForYMD();
        if ("".equals(startDate) || "".equals(endDate)) {
            out.print("开始日期和结束日期不为能空!");
            out.close();
            return;
        } else {
            if (TimeUtil.startEndCompare(now, startDate) <= 0) {
                out.print("开始日期必须大于今日!");
                out.close();
                return;
            }
            if (TimeUtil.startEndCompare(startDate, endDate) < 0) {
                out.print("结束日期必须大于开始日期!");
                out.close();
                return;
            }
        }
        Employee e = employeeService.findByIf("id", null, eid).get(0);
        trainingInfo.setTeacher(e);
        Map<String, Object> map = trainingInfoService.addTrainAdmin(trainingInfo);
        if (map.containsKey("success")) {
            out.print("ok");
        } else {
            out.print(map.get("fail"));
        }
        out.flush();
        out.close();
    }

    // 管理员 发布一条培训
    @RequestMapping(value = "/postTrain")
    public void postTrain(int tiid, HttpServletResponse response) throws IOException, ParseException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        TrainingInfo ti = trainingInfoService.findByIf("id", null, tiid).get(0);
        Map<String, Object> map = trainingInfoService.updateByPostOne(ti);
        if (map.containsKey("success")) {
            out.print("ok");
        } else {
            out.print(map.get("fail"));
        }
        out.flush();
        out.close();
    }

    // 管理员 取消一条培训
    @RequestMapping(value = "/cancelTrain")
    public void cancelTrain(int tiid, HttpServletResponse response) throws IOException, ParseException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        TrainingInfo ti = trainingInfoService.findByIf("id", null, tiid).get(0);
        ti.setStatus("已取消");
        trainingInfoService.update(ti);
        out.print("ok");
        out.flush();
        out.close();
    }

    // 管理员 查看所选培训的所有员工
    @RequestMapping(value = "/showTrainEmp")
    public String showTrainEmp(int tiid, Model model) throws IOException {
        List<Training> trainings = trainingService.findByIf("tiid", null, tiid);
        List<Employee> addedEmp1 = new ArrayList<>();
        List<Employee> addedEmp = new ArrayList<>();
        for (Training training : trainings) {
            addedEmp1.add(training.getEmployee());
        }
        for (Employee employee : addedEmp1) {
            addedEmp.add(employeeService.findByIf("id", null, employee.getId()).get(0)) ;
        }
        model.addAttribute("addedEmp", addedEmp);
        return "train/emp_show_admin";
    }

    // 管理员 修改培训页面
    @RequestMapping(value = "/toEditTrain")
    public String toEditTrain(int tiid,Model model) {
        TrainingInfo trainingInfo = trainingInfoService.findByIf("id", null, tiid).get(0);
        int eid = trainingInfo.getTeacher().getId();
        Employee e = employeeService.findByIf("id", null, eid).get(0);
        trainingInfo.setTeacher(e);
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        model.addAttribute("trainingInfo", trainingInfo);
        return "train/train_edit_admin";
    }

    // 管理员 确认修改培训
    @RequestMapping(value = "/editTrainConfirm")
    public void editTrainConfirm(int eid, int tiid, TrainingInfo trainingInfo, HttpServletResponse response) throws IOException, ParseException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        trainingInfo.setId(tiid);
        // 先拦截管理员输入的错误信息
        if ("".equals(trainingInfo.getContent()) || "".equals(trainingInfo.getPlace())) {
            out.print("内容和地址不为能空!");
            out.close();
            return;
        }
        //日期比较
        String startDate = trainingInfo.getStartDate();
        String endDate = trainingInfo.getEndDate();
        String now = TimeUtil.nowForYMD();
        if ("".equals(startDate) || "".equals(endDate)) {
            out.print("开始日期和结束日期不为能空!");
            out.close();
            return;
        } else {
            if (TimeUtil.startEndCompare(now, startDate) <= 0) {
                out.print("开始日期必须大于今日!");
                out.close();
                return;
            }
            if (TimeUtil.startEndCompare(startDate, endDate) < 0) {
                out.print("结束日期必须大于开始日期!");
                out.close();
                return;
            }
        }
        Employee e = employeeService.findByIf("id", null, eid).get(0);
        trainingInfo.setTeacher(e);
        Map<String, Object> map = trainingInfoService.updateByEdit(trainingInfo);
        if (map.containsKey("success")) {
            out.print("ok");
        } else {
            out.print(map.get("fail"));
        }
        out.flush();
        out.close();
    }

    // 管理员 添加培训员工页面
    @RequestMapping(value = "/addTrainEmp")
    public String addTrainEmp(int tiid, Model model) {
        //通过tiid找到的培训详情trainingInfo带入request域对象
        TrainingInfo trainingInfo = trainingInfoService.findByIf("id", null, tiid).get(0);
        model.addAttribute("trainingInfo", trainingInfo);

        //未添加进此培训的员工带入request域对象
        List<Training> trainings = trainingService.findByIf("tiid", null, tiid);
        List<Employee> addedEmp = new ArrayList<>();
        for (Training training : trainings) {
            addedEmp.add(training.getEmployee());
        }
        model.addAttribute("departmentList", departmentService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("addedEmp", addedEmp);
        return "train/emp_add_admin";
    }

    // 管理员 确认添加培训员工
    @RequestMapping(value = "/addTrainEmpConfirm")
    public void addTrainEmpConfirm(int[] eids, int tiid, HttpServletResponse response) throws IOException, ParseException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        if (eids.length == 0) {
            out.print("你没有选择员工!");
            out.close();
            return;
        }
        //得到加入的培训详情
        TrainingInfo ti = trainingInfoService.findByIf("id", null, tiid).get(0);
        String s3 = ti.getStartDate();
        String s4 = ti.getEndDate();
        //判断冲突 同一员工的多个培训不能有时间冲突
        for (int eid : eids) {
            List<Training> trainings = trainingService.findByIf("eid", null, eid);
            for (Training training : trainings) {
                TrainingInfo info = training.getTrainingInfo();
                String s1 = info.getStartDate();
                String s2 = info.getEndDate();
                if (TimeUtil.midCompare(s1, s3, s2) || TimeUtil.midCompare(s1, s4, s2)) {
                    out.print("所选员工中有其他培训的时间冲突!");
                    out.close();
                    return;
                }
            }
        }
        // 进行添加
        Training t = new Training();
        t.setTrainingInfo(ti);
        for (int eid : eids) {
            Employee e = employeeService.findByIf("id", null, eid).get(0);
            t.setEmployee(e);
            trainingService.add(t);
        }
        out.print("ok");
        out.flush();
        out.close();
    }
    //</editor-fold>

    //员工 查看自己的培训通知
    @RequestMapping(value = "/showTrainNotice")
    public String showTrainNotice() throws IOException {
        return "train/notice_show_emp";
    }

    //员工 查看所有有关自己的培训信息
    @RequestMapping(value = "/showMyTrainInfo")
    public String showMyTrainInfo(int eid, Model model) throws IOException {
        List<TrainingInfo> trainingInfoList = trainingService.findAllByEid(eid);
        for (TrainingInfo ti : trainingInfoList) {
            int id = ti.getTeacher().getId();
            Employee teacher = employeeService.findByIf("id", null, id).get(0);
            ti.setTeacher(teacher);
        }
        model.addAttribute("trainingInfoList", trainingInfoList);
        return "train/info_show_emp";
    }


}
