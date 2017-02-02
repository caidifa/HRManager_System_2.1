package com.cai.web.controller;

import com.cai.domain.Department;
import com.cai.domain.HireInfo;
import com.cai.domain.Position;
import com.cai.service.DepartmentService;
import com.cai.service.HireInfoService;
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
 * 招聘信息操作控制
 */
@Controller
@RequestMapping("/hire")
public class HireInfoController {
    @Resource
    private DepartmentService departmentService;

    @Resource
    private HireInfoService hireInfoService;

    //用户 查看招聘信息
    @RequestMapping(value = "/showHIByUser.do")
    public String showHIByUser(Model model) throws IOException {
        List<Department> departmentList = departmentService.findAll();
        model.addAttribute("departmentList", departmentList);
        return "hire/info_show_user";
    }

    //管理员 新建并发布招聘
    @RequestMapping(value = "/createHireInfo.do")
    public String createHireInfo(Model model) {
        List<Department> departmentList = departmentService.findAll();
        model.addAttribute("departmentList", departmentList);
        return "hire/info_create_admin";
    }

    //根据部门显示招聘信息
    @RequestMapping(value = "/showHireInfoByDepart.do")
    public String showHireInfoByDepart(int did, Model model) {
        Map map = new HashMap();
        if (did != 0) {
            map.put("did", did);
        }
        map.put("status", "招聘中");
        List<HireInfo> hireInfoList = hireInfoService.findByMap(map);
        model.addAttribute("hireInfoList", hireInfoList);
        model.addAttribute("hireInfoListCount", hireInfoList.size());
        return "hire/if_show_user";
    }

    //根据职位名显示招聘信息
    @RequestMapping(value = "/showHireInfoByPName.do")
    public String showHireInfoByPName(String pName, Model model) {
        Map map = new HashMap();
        if (!"".equals(pName)) {
            map.put("pName", "%" + pName + "%");
        }
        map.put("status", "招聘中");
        List<HireInfo> hireInfoList = hireInfoService.findByMap(map);
        model.addAttribute("hireInfoList", hireInfoList);
        model.addAttribute("hireInfoListCount", hireInfoList.size());
        return "hire/if_show_user";
    }


    //管理员 查看所有状态(招聘中,已招满)的招聘信息页面
    @RequestMapping(value = "/showHIByAdmin.do")
    public String showHIByAdmin(Model model) {
        List<HireInfo> hireInfoList = hireInfoService.findAll();
        model.addAttribute("hireInfoList", hireInfoList);
        model.addAttribute("hireInfoListCount", hireInfoList.size());
        return "hire/info_show_admin";
    }

    //管理员 发布招聘信息操作
    @RequestMapping(value = "/postHireInfo.do")
    public void postHireInfo(int did, int pid, HireInfo hireInfo, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        if (did == 0 || pid == 0) {
            out.print("请选择要发布的部门和职位!");
            out.close();
            return;
        }

        if (hireInfo.getNeedNumber() <= 0) {
            out.print("招聘人数有误!");
            out.close();
            return;
        }

        Department dep = new Department();
        dep.setId(did);
        Position pos = new Position();
        pos.setId(pid);
        hireInfo.setDepartment(dep);
        hireInfo.setPosition(pos);
        hireInfo.setStatus("招聘中");

        Map<String, Object> map = hireInfoService.addByCreate(hireInfo);

        if (map.containsKey("success")) {
            out.print("ok");
        } else {
            out.print(map.get("fail"));
        }

        out.flush();
        out.close();
    }

    //管理员 停止招聘信息操作
    @RequestMapping(value = "/stopHireInfo.do")
    public void stopHireInfo(int hid, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        HireInfo hireInfo = hireInfoService.findByIf("id", null, hid).get(0);
        hireInfo.setStatus("已停止");
        hireInfoService.update(hireInfo);
        String dName = hireInfo.getDepartment().getdName();
        String pName = hireInfo.getPosition().getpName();
        out.print("已停止" + dName + "部门下的" + pName + "职位的招聘!");
        out.flush();
        out.close();
    }

}
