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
 * 部门操作控制
 */
@Controller
@RequestMapping("/dep")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @Resource
    private PositionService positionService;

    @Resource
    private EmployeeService employeeService;

    //二级菜单 通过部门找职位
    @RequestMapping(value = "/posiByDepId.do")
    public String posByDid(int did, Model model) {
        if (did != 0) {
            Department dep = departmentService.findByIf("id", null, did).get(0);
            List<Position> positions = dep.getPositionList();
            if (positions.size() > 0) {
                model.addAttribute("positions", positions);
            }
        }
        return "posi/dep_to_posi";
    }

    //引入页面: 显示所有部门
    @RequestMapping(value = "/showAllDep.do")
    public String showAllDep(Model model) {
        List<Department> departmentList = departmentService.findAll();
        model.addAttribute("departmentList", departmentList);
        return "dep/info_show_admin";
    }

    //引入页面: 添加部门
    @RequestMapping(value = "/addDepart.do")
    public String addDepart() {
        return "dep/info_add_admin";
    }

    //确认添加部门
    @RequestMapping(value = "/addDepartConfirm.do")
    public void addDepartConfirm(Department department, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        if ("".equals(department.getdName())) {
            out.print("部门名未填写!");
            return;
        }
        if (departmentService.add(department)) {
            out.print("ok");
        } else {
            out.print("部门名不能重复!");
        }
        out.flush();
        out.close();
    }

    //删除部门
    @RequestMapping(value = "/removeDepart.do")
    public void removeDepart(int did, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //判断该部门下是否有职位
        Department dep = departmentService.findByIf("id", null, did).get(0);
        List<Position> positions = dep.getPositionList();
        if (positions.size() > 0) {
            //有职位的话判断职位下是否有员工
            for (Position p : positions) {
                if (employeeService.findByIf("pid", null, p.getId()).size() > 0) {
                    out.print("该部门下还有员工!");
                    out.close();
                    return;
                }
            }
            //如果该部门下的所有职位都没有员工则全部删除
            for (Position position : positions) {
                positionService.remove(position);
            }
        }
        departmentService.remove(dep);
        out.print("ok");
        out.flush();
        out.close();
    }

    //批量删除部门
    @RequestMapping(value = "/removeManyDepart.do")
    public void removeManyDepart(int[] ids, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //判断有没有选中部门
        if (ids == null) {
            out.print("没有选中的部门!");
            out.close();
            return;
        }
        //判断每个所选部门下的职位下是否有员工
        for (int i : ids) {
            //判断该部门下是否有职位
            Department dep = departmentService.findByIf("id", null, i).get(0);
            List<Position> positions = dep.getPositionList();
            if (positions.size() > 0) {
                //有职位的话判断职位下是否有员工
                for (Position p : positions) {
                    if (employeeService.findByIf("pid", null, p.getId()).size() > 0) {
                        out.print("所选部门里的职位下有员工!");
                        out.close();
                        return;
                    }
                }
            }
        }
        //如果每个所选部门下的所有职位都没有员工才进行删除(先职位后部门)
        for (int i : ids) {
            for (Position p : positionService.findByIf("did", null, i)) {
                positionService.remove(p);
            }
            departmentService.remove(departmentService.findByIf("id", null, i).get(0));
        }
        out.print("ok");
        out.flush();
        out.close();
    }

    //引入页面: 修改部门
    @RequestMapping(value = "/editDepart.do")
    public String editDepart(int did, Model model) {
        Department department = departmentService.findByIf("id", null, did).get(0);
        model.addAttribute("department", department);
        return "dep/info_edit_admin";
    }

    //确认修改部门
    @RequestMapping(value = "/editDepartConfirm.do")
    public void editDepartConfirm(int did, Department department, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        if ("".equals(department.getdName())) {
            out.print("部门名不能为空!");
            out.close();
            return;
        }
        Department dep = departmentService.findByIf("id", null, did).get(0);
        if (dep.getdName().equals(department.getdName())) {
            out.print("未修改部门名!");
            out.close();
            return;
        }
        department.setId(did);
        if (departmentService.update(department)) {
            out.print("ok");
        } else {
            out.print("已有该部门名!");
        }
        out.flush();
        out.close();
    }



}
