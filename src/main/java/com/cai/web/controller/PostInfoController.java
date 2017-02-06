package com.cai.web.controller;

import com.cai.domain.HireInfo;
import com.cai.domain.PostInfo;
import com.cai.domain.Resume;
import com.cai.domain.User;
import com.cai.service.HireInfoService;
import com.cai.service.PostInfoService;
import com.cai.service.ResumeService;
import com.cai.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 投递信息操作控制
 */
@Controller
@RequestMapping("/post")
public class PostInfoController {
    //<editor-fold desc="所需的业务处理对象">
    @Resource
    private PostInfoService postInfoService;
    @Resource
    private HireInfoService hireInfoService;
    @Resource
    private ResumeService resumeService;
    @Resource
    private UserService userService;
    //</editor-fold>

    //用户 投递简历操作
    @RequestMapping(value = "/postResume.do")
    public void postResume(int hid, int rid, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        Resume resume = resumeService.findByIf("id", null, rid).get(0);
        HireInfo hireInfo = hireInfoService.findByIf("id", null, hid).get(0);
        Map<String, Object> map = postInfoService.addByPostResume(resume, hireInfo);
        if (map.containsKey("success")) {
            out.print("ok");
        } else {
            out.print(map.get("fail"));
        }

        out.flush();
        out.close();
    }

    //管理员 查看投递信息(未读,已读)
    @RequestMapping(value = "/showPostInfoByAdmin.do")
    public String showPostInfoByAdmin(Model model) {
        List<PostInfo> all = postInfoService.findAll();
        List<PostInfo> postInfoList = new ArrayList<>();
        for (PostInfo postInfo : all) {
            postInfoList.add(postInfoService.findDetail(postInfo));
        }
        model.addAttribute("postInfoList", postInfoList);
        model.addAttribute("postInfoListCount", postInfoList.size());
        return "post/info_show_admin";
    }

    //管理员 根据所选标记条件显示招聘信息
    @RequestMapping(value = "/showPostInfoByRemark.do")
    public String showPostInfoByRemark(String remark, Model model) {
        List<PostInfo> all = postInfoService.findAll();
        if (!"all".equals(remark)) {
            if ("unread".equals(remark)) {
                all = postInfoService.findByIf("remark", "未读", 0);
            } else {
                all = postInfoService.findByIf("remark", "已阅", 0);
            }
        }
        List<PostInfo> postInfoList = new ArrayList<>();
        for (PostInfo postInfo : all) {
            postInfoList.add(postInfoService.findDetail(postInfo));
        }
        model.addAttribute("postInfoList", postInfoList);
        model.addAttribute("postInfoListCount", postInfoList.size());
        return "post/if_show_admin";
    }

    //管理员 查看投递信息里的简历
    @RequestMapping(value = "/checkResumeByAdmin.do")
    public String checkResumeByAdmin(int piid, HttpSession session) {
        // 通过得到的投递信息id找到简历id
        // 通过用户id找到user并放入session,管理员查看用户简历的页面使用获取此user信息并显示
        // 弹出该投递者的简历详情
        int rid = postInfoService.findByIf("id", null, piid).get(0).getResume().getId();
        int uid = resumeService.findByIf("id", null, rid).get(0).getUser().getId();
        User user = userService.findByIf("id", null, uid).get(0);
        session.setAttribute("user", user);
        return "resume/info_show_user";
    }

    //管理员 标记一条投递信息为已阅
    @RequestMapping(value = "/remarkReaded.do")
    public void remarkReaded(int piid, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        PostInfo postInfo = postInfoService.findByIf("id", null, piid).get(0);
        postInfo.setRemark("已阅");
        postInfoService.update(postInfo);

        out.print("ok");
        out.flush();
        out.close();
    }

}
