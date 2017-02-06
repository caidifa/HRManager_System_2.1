package com.cai.web.controller;

import com.cai.domain.*;
import com.cai.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 员工(含管理员)操作页面控制
 */
@Controller
@RequestMapping("/emp")
public class EmployeeController {
    //<editor-fold desc="所需的业务接口">
    @Resource
    private EmployeeService employeeService;
    @Resource
    private HireInfoService hireInfoService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private PostInfoService postInfoService;
    @Resource
    private ResumeService resumeService;
    @Resource
    private FaceNoticeService faceNoticeService;
    @Resource
    private FaceInfoService faceInfoService;
    @Resource
    private PositionService positionService;
    @Resource
    private TrainingService trainingService;
    //</editor-fold>

    /**
     * 员工登陆确认
     *
     * @param loginMethod 登陆方式
     * @param employee    员工信息
     * @param session     域对象
     * @param response    响应
     * @throws IOException 打印流异常
     */
    @RequestMapping(value = "/login.do")
    public void login(@RequestParam("loginMethod") String loginMethod,
                      Employee employee, HttpSession session, HttpServletResponse response) throws IOException, ParseException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        if ("".equals(employee.getEmpNumber())) {
            out.print("编号未填写!");
            out.close();
            return;
        }
        if ("".equals(employee.getPassword())) {
            out.print("密码未填写!");
            out.close();
            return;
        }
        //先判登陆方式 empLogin:非管理员方式 否则是管理员方式
        employee.setLevel("empLogin".equals(loginMethod) ? "0" : "1");
        // 把员工输入的信息交给service层处理
        Map<String, Object> map = employeeService.findByLogin(employee);
        if (map.containsKey("success")) {
            // 成功则session加入emp
            Employee e = (Employee) map.get("success");
            session.setAttribute("employee", e);
            // 如果是管理员登录则把投递和面试信息带入session
            if ("1".equals(employee.getLevel())) {
                // 投递信息的数量(未读)
                List<PostInfo> postInfoUnRead = postInfoService.findByIf("remark", "未读", 0);
                session.setAttribute("postInfoUnReadCount", postInfoUnRead.size());
                // 自己要去面试员工的通知(通知中),empID = admin.id
                map.clear();
                map.put("eid", e.getId());
                map.put("status", "通知中");
                List<FaceNotice> toFaceUser = faceNoticeService.findByMap(map);
                session.setAttribute("toFaceUser", toFaceUser);
                session.setAttribute("toFaceUserCount", toFaceUser.size());
                // "等通知"的面试情况的数量
                List<FaceInfo> faceInfoList = faceInfoService.findByIf("status", "等通知", 0);
                session.setAttribute("faceInfoListCount", faceInfoList.size());
            } else {
                // 非管理员登陆则把培训信息带入session
                int eid = e.getId();
                List<TrainingInfo> trainingInfoList = trainingService.findNowByEid(eid);
                for (TrainingInfo ti : trainingInfoList) {
                    int id = ti.getTeacher().getId();
                    Employee teacher = employeeService.findByIf("id", null, id).get(0);
                    ti.setTeacher(teacher);
                }
                session.setAttribute("trainingInfoList", trainingInfoList);
                session.setAttribute("trainingInfoListCount", trainingInfoList.size());
            }
            // 根据登陆方式可以分别进入管理员和员工系统
            out.print("empLogin".equals(loginMethod) ? "okemp" : "okadmin");
        } else {
            // 失败则返回提示
            out.print(map.get("fail"));
        }
        // 关闭资源
        out.flush();
        out.close();
    }

    // 员工登出 ajax跳出提示框
    @RequestMapping(value = "/logout.do")
    public String logout(HttpSession session) {
        session.invalidate();
        return "emp/emp_login";
    }

    //<editor-fold desc="管理员Admin功能">
    // 管理员雇用面试通过者为员工
    @RequestMapping(value = "/employ.do")
    public void employ(int postid, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        PostInfo postInfo = postInfoService.findByIf("id", null, postid).get(0);
        //在对应的招聘信息里的人数-1
        int hid = postInfo.getHireInfo().getId();
        HireInfo hireInfo = hireInfoService.findByIf("id", null, hid).get(0);
        int needNumber = hireInfo.getNeedNumber();
        if (needNumber == 1) {
            hireInfo.setStatus("已停止");
        } else if (needNumber == 0) {
            out.print("fail");
            out.close();
            return;
        }
        hireInfo.setNeedNumber(needNumber - 1);
        hireInfoService.update(hireInfo);
        Department department = hireInfo.getDepartment();
        Position position = hireInfo.getPosition();

        //面试情况表改成'成功',
        FaceNotice faceNotice = faceNoticeService.findByIf("postid", null, postid).get(0);
        FaceInfo faceInfo = faceInfoService.findByIf("fnid", null, faceNotice.getId()).get(0);
        faceInfo.setStatus("成功");
        faceInfoService.update(faceInfo);

        //用postid获得简历,部门,职位 在简历里状态设置为pass
        Resume resume = postInfo.getResume();
        int rid = resume.getId();
        Resume re = resumeService.findByIf("id", null, rid).get(0);
        User user = re.getUser();
        re.setStatus("pass");
        resumeService.update(re);

        // 添加新的员工,并附上对应的信息
        Employee emp = new Employee();
        emp.setResume(resume);
        emp.setDepartment(department);
        emp.setPosition(position);
        emp.setEmpNumber(user.getPhone());
        emp.setPassword(user.getPassword());
        emp.setSalary(position.getBasicSalary());
        emp.setLevel("0");
        emp.setStatus("在职");
        employeeService.add(emp);

        out.print("ok");
        out.flush();
        out.close();
    }

    // 管理员拒绝面试后的用户
    @RequestMapping(value = "/reject.do")
    public void reject(int postid, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //面试情况表改成'失败',
        FaceNotice faceNotice = faceNoticeService.findByIf("postid", null, postid).get(0);
        FaceInfo faceInfo = faceInfoService.findByIf("fnid", null, faceNotice.getId()).get(0);
        faceInfo.setStatus("失败");
        faceInfoService.update(faceInfo);
        out.print(postid);
        out.close();
    }

    // 管理员查看所有员工信息
    @RequestMapping(value = "/showAllEmp.do")
    public String showAllEmp(Model model) {
        List<Employee> employeeList = employeeService.findAll();
        model.addAttribute("employeeList", employeeList);
        return "emp/all_show_admin";
    }

    // 管理员编辑所选员工信息的页面
    @RequestMapping(value = "/editEmp.do")
    public String editEmp(int eid, Model model) {
        Employee employee = employeeService.findByIf("id", null, eid).get(0);
        model.addAttribute("employee", employee);
        List<Department> departmentList = departmentService.findAll();
        model.addAttribute("departmentList", departmentList);
        return "emp/info_edit_admin";
    }

    // 管理员确认修改员工信息
    @RequestMapping(value = "/editEmpConfirm.do")
    public void editEmpConfirm(int eid, int pid, Employee employee, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        System.out.println("pid=" + pid);
        if (pid == 0) {
            out.print("请选择职位!");
            out.close();
            return;
        }
        if ("".equals(employee.getPassword())) {
            out.print("密码不能为空!");
            out.close();
            return;
        }
        if (employee.getSalary() == 0) {
            out.print("薪水不能为0!");
            out.close();
            return;
        }
        employee.setId(eid);
        Position p = positionService.findByIf("id", null, pid).get(0);
        employee.setDepartment(p.getDepartment());
        employee.setPosition(p);
        employeeService.update(employee);
        out.print("ok");
        out.flush();
        out.close();
    }

    // 管理员开除员工
    @RequestMapping(value = "/dismissEmp.do")
    public void dismissEmp(int eid, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        Employee e = employeeService.findByIf("id", null, eid).get(0);
        if ("1".equals(e.getLevel())) {
            out.print("无权开除管理员!");
            out.close();
            return;
        }
        e.setStatus("离职");
        employeeService.update(e);
        out.print("ok");
        out.flush();
        out.close();
    }

    // 管理员批量开除员工
    @RequestMapping(value = "/dismissManyEmp.do")
    public void dismissManyEmp(int[] eids, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        for (int eid : eids) {
            Employee e = employeeService.findByIf("id", null, eid).get(0);
            if ("1".equals(e.getLevel())) {
                out.print("无权开除管理员!");
                out.close();
                return;
            }
            if ("离职".equals(e.getStatus())) {
                out.print("无法开除离职员工!");
                out.close();
                return;
            }
        }
        for (int eid : eids) {
            Employee e = employeeService.findByIf("id", null, eid).get(0);
            e.setStatus("离职");
            employeeService.update(e);
        }
        out.print("ok");
        out.flush();
        out.close();
    }
    //</editor-fold>

    //员工查看 所有部门下所有职位的所有员工
    @RequestMapping(value = "/showDPEmp.do")
    public String showDPEmp(Model model) {
        List<Employee> employeeList = employeeService.findAll();
        model.addAttribute("employeeList", employeeList);
        return "emp/all_show_emp";
    }

    //员工修改密码
    @RequestMapping(value = "/editPassword.do")
    public String editPassword() {
        return "emp/pswd_edit_emp";
    }

    //员工修改密码
    @RequestMapping(value = "/changePassword.do")
    public void changePassword(int eid, String password1, String password2, String password3, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        Employee e = employeeService.findByIf("id", null, eid).get(0);
        String password = e.getPassword();
        if ("".equals(password1) || "".equals(password2) || "".equals(password2)) {
            out.print("请全部输入!");
            out.close();
            return;
        }
        if (!password1.equals(password)) {
            out.print("原密码有误!");
            out.close();
            return;
        }
        if (!password2.equals(password3)) {
            out.print("2次密码不一致!");
            out.close();
            return;
        }
        if (password1.equals(password2)) {
            out.print("新密码不能与原密码一样!");
            out.close();
            return;
        }
        e.setPassword(password2);
        employeeService.update(e);
        out.print("ok");
        out.flush();
        out.close();
    }


}
