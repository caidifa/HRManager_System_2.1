package com.cai.web.controller;

import com.cai.domain.*;
import com.cai.service.*;
import com.cai.utils.TimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * 面试操作页面控制
 */
@Controller
@RequestMapping("/face")
public class FaceController {
    @Resource
    private FaceNoticeService faceNoticeService;
    @Resource
    private FaceInfoService faceInfoService;
    @Resource
    private UserService userService;
    @Resource
    private PostInfoService postInfoService;
    @Resource
    private EmployeeService employeeService;

    //管理员 进入新建面试通知页面
    @RequestMapping(value = "/toCreateFaceNotice.do")
    public String toCreateFaceNotice(int piid, Model model) {
        //选择面试官(管理员)
        List<Employee> admins = employeeService.findByIf("level", "1", 0);
        model.addAttribute("admins", admins);
        model.addAttribute("piid", piid);
        return "face/notice_create_admin";
    }

    //管理员 发送面试通知
    @RequestMapping(value = "/sendFaceNotice.do")
    public void sendFaceNotice(int piid, int empID, FaceNotice faceNotice, HttpServletResponse response) throws IOException, ParseException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        if (empID == 0) {
            out.print("请选择面试官!");
            out.close();
            return;
        }
        if ("".equals(faceNotice.getFaceTime())) {
            out.print("请选择面试时间!");
            out.close();
            return;
        }
        if ("".equals(faceNotice.getLocation())) {
            out.print("请填写面试地点!");
            out.close();
            return;
        }
        PostInfo postInfo = postInfoService.findByIf("id", null, piid).get(0);
        faceNotice.setPostInfo(postInfo);
        Employee employee = new Employee();
        employee.setId(empID);
        faceNotice.setEmployee(employee);
        Map<String, Object> map = faceNoticeService.addByCreate(faceNotice);
        if (map.containsKey("success")) {
            postInfo.setRemark("已阅");
            postInfoService.update(postInfo);
            out.print("ok");
        } else {
            out.print(map.get("fail"));
        }
        out.flush();
        out.close();
    }

    //管理员 查看自己要去面试投递者的通知
    @RequestMapping(value = "/showFaceNoticeAboutAdmin.do")
    public String showFaceNoticeAboutAdmin() {
        return "face/info_notice_admin";
    }

    //用户查看自己的面试通知(通知中)
    @RequestMapping(value = "/showFNByUser.do")
    public String showFNByUser(int uid, HttpSession session) {
        User user = userService.findByIf("id", null, uid).get(0);
        List<FaceNotice> faceNotices = faceNoticeService.findByUser(user, "通知中");
        List<FaceNotice> faceNoticeList = new ArrayList<>();
        for (FaceNotice faceNotice : faceNotices) {
            faceNoticeList.add(faceNoticeService.findDetail(faceNotice));
        }
        session.setAttribute("faceNoticeList", faceNoticeList);
        session.setAttribute("faceNoticeListCount", faceNoticeList.size());
        return "face/info_notice_user";
    }

    //去面试
    @RequestMapping(value = "/goFace.do")
    public void goFace(int fnid, HttpServletResponse response) throws IOException, ParseException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        FaceNotice faceNotice = faceNoticeService.findByIf("id", null, fnid).get(0);
        String faceTime = TimeUtil.toFormat(faceNotice.getFaceTime());
        String nowTime = TimeUtil.nowForYMDHMS();
        if (TimeUtil.timeCompare(faceTime, nowTime) == 1 && TimeUtil.timeBalance(nowTime, faceTime) < 2) {
            out.print("ok");
            out.close();
            return;
        }
        out.print("fail");
        out.flush();
        out.close();
    }

    //开始面试
    @RequestMapping(value = "/startFace.do")
    public String startFace(int fnid, HttpSession session) {
        session.setAttribute("fnid", fnid);
        return "face/start_face_user";
    }

    //结束面试
    @RequestMapping(value = "/endFace.do")
    public void endFace(String answer, int fnid, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        //用户结束面试后生成面试情况,让管理员去处理
        FaceNotice faceNotice = new FaceNotice();
        faceNotice.setId(fnid);
        FaceInfo faceInfo = new FaceInfo();
        faceInfo.setFaceNotice(faceNotice);

        //修改面试通知的状态为: 已面试
        FaceNotice faceNotice1 = faceNoticeService.findByIf("id", null, fnid).get(0);
        faceNotice1.setStatus("已面试");
        faceNoticeService.update(faceNotice1);

        if ("yes".equals(answer)) {
            faceInfo.setPenScores(100);
        } else {
            faceInfo.setPenScores(0);
        }
        faceInfo.setStatus("等通知");
        faceInfoService.add(faceInfo);
        out.print("面试结束!请等待通知!");
    }

    //用户查看自己的面试情况(成功/失败)
    @RequestMapping(value = "/showFIByUSer.do")
    public String showFIByUSer(int uid, HttpSession session) {
        User user = userService.findByIf("id", null, uid).get(0);
        List<FaceInfo> faceInfoList = faceInfoService.findByUser(user, "成功");
        faceInfoList.addAll(faceInfoService.findByUser(user, "失败"));
        for (FaceInfo faceInfo : faceInfoList) {
            FaceNotice faceNotice = faceNoticeService.findByIf("id", null, faceInfo.getFaceNotice().getId()).get(0);
            FaceNotice detail = faceNoticeService.findDetail(faceNotice);
            faceInfo.setFaceNotice(detail);
        }
        session.setAttribute("faceInfoList", faceInfoList);
        session.setAttribute("faceInfoListCount", faceInfoList.size());
        return "face/info_result_user";
    }

    // 查看所有的用户的面试情况
    @RequestMapping(value = "/showFaceInfoByAdmin.do")
    public String showFaceInfoByAdmin(Model model) {
        // 找到所有面试情况
        List<FaceInfo> faceInfoList = faceInfoService.findAll();
        for (FaceInfo info : faceInfoList) {
            FaceNotice faceNotice = faceNoticeService.findByIf("id", null, info.getFaceNotice().getId()).get(0);
            info.setFaceNotice(faceNoticeService.findDetail(faceNotice));
        }
        model.addAttribute("faceInfoList", faceInfoList);
        model.addAttribute("faceInfoListCount", faceInfoList.size());
        return "face/info_show_admin";
    }


}
