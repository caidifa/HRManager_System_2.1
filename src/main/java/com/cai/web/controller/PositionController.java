package com.cai.web.controller;

import com.cai.domain.Department;
import com.cai.domain.Employee;
import com.cai.domain.Position;
import com.cai.service.DepartmentService;
import com.cai.service.EmployeeService;
import com.cai.service.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 职位操作页面控制
 */
@Controller
@RequestMapping("/posi")
public class PositionController {
    @Resource
    private PositionService positionService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private EmployeeService employeeService;

    //引入页面: 显示所有职位
    @RequestMapping(value = "/showAllPosi.do")
    public String showAllPosi(Model model) {
        List<Position> positionList = positionService.findAll();
        model.addAttribute("positionList", positionList);
        return "posi/info_show_admin";
    }

    //引入页面: 添加职位
    @RequestMapping(value = "/addPosi.do")
    public String addPosi(Model model) {
        List<Department> departmentList = departmentService.findAll();
        model.addAttribute("departmentList", departmentList);
        return "posi/info_add_admin";
    }

    //确认添加职位
    @RequestMapping(value = "/addPosiConfirm.do")
    public void addPosiConfirm(Position position, int did, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        if (did == 0) {
            out.print("未选择所属部门!");
            out.close();
            return;
        }
        if ("".equals(position.getpName())) {
            out.print("职位名不能为空!");
            out.close();
            return;
        }
        if ("".equals(position.getBasicSalary())) {
            out.print("起薪不能为空!");
            out.close();
            return;
        } else if (position.getBasicSalary() < 2000) {
            out.print("起薪不能低于2000!太抠了!");
            out.close();
            return;
        }
        Department department = new Department();
        department.setId(did);
        position.setDepartment(department);
        if (positionService.add(position)) {
            out.print("ok");
        } else {
            out.print("所选部门下已有该职位!");
        }
        out.flush();
        out.close();
    }

    //删除职位
    @RequestMapping(value = "/removePosi.do")
    public void removePosi(int pid, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //判断该职位下是否有员工
        List<Employee> employees = employeeService.findByIf("pid", null, pid);
        if (employees.size() > 0) {
            out.print("该职位下还有员工!");
        } else {
            positionService.remove(positionService.findByIf("id", null, pid).get(0));
            out.print("ok");
        }
        out.flush();
        out.close();
    }

    //批量删除职位
    @RequestMapping(value = "/removeManyPosi.do")
    public void removeManyPosi(int[] ids, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //判断有没有选中职位
        if (ids == null) {
            out.print("没有选中的职位!");
            out.close();
            return;
        }
        //判断每个所选职位下是否有员工
        for (int i = 0; i < ids.length; i++) {
            List<Employee> employees = employeeService.findByIf("id", null, ids[i]);
            if (employees.size() > 0) {
                out.print("所选职位里有含有员工!");
                out.close();
                return;
            }
        }
        //如果每个所选职位都没有员工才进行删除
        for (int i = 0; i < ids.length; i++) {
            positionService.remove(positionService.findByIf("id", null, ids[i]).get(0));
        }
        out.print("ok");
        out.flush();
        out.close();
    }

    //引入页面: 修改职位
    @RequestMapping(value = "/editPosi.do")
    public String editPosi(int pid, Model model) {
        //要修改的职位
        Position position = positionService.findByIf("id", null, pid).get(0);
        model.addAttribute("position", position);
        List<Department> departmentList = departmentService.findAll();
        model.addAttribute("departmentList", departmentList);
        return "posi/info_edit_admin";
    }

    //确认修改职位
    @RequestMapping(value = "/editPosiConfirm.do")
    public void editPosiConfirm(int pid, int did, Position position, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        if (did == 0) {
            out.print("未选择所属部门!");
            out.close();
            return;
        }
        if ("".equals(position.getpName())) {
            out.print("职位名不能为空!");
            out.close();
            return;
        }
        if ("".equals(position.getBasicSalary())) {
            out.print("起薪不能为空!");
            out.close();
            return;
        } else if (position.getBasicSalary() < 2000) {
            out.print("起薪不能低于2000!");
            out.close();
            return;
        }

        Department department = new Department();
        department.setId(did);
        position.setId(pid);
        position.setDepartment(department);

        if (positionService.update(position)) {
            out.print("ok");
        } else {
            out.print("所选部门内已有该职位!");
        }
        out.flush();
        out.close();
    }

}
