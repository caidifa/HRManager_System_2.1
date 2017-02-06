package com.cai.web.controller;

import com.cai.domain.Resume;
import com.cai.domain.User;
import com.cai.service.ResumeService;
import com.cai.service.UserService;
import com.cai.utils.MatchUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 简历操作控制
 */
@Controller
@RequestMapping("/resume")
public class ResumeController {
    @Resource
    private ResumeService resumeService;
    @Resource
    private UserService userService;

    //获得空白简历页面 ajax
    @RequestMapping(value = "/getBlank.do")
    public String getBlank() {
        return "resume/blank_create_user";
    }

    /**
     * 用户填写好简历后保存 → 验证输入的信息 → service进行添加 → 回馈信息给页面
     *
     * @param resume   用户在页面输入的简历信息
     * @param session  域对象更新
     * @param response 响应结果
     * @throws IOException 输出异常
     */
    @RequestMapping(value = "/saveResume.do")
    public void saveResume(Resume resume, HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        User user = (User) session.getAttribute("user");
        resume.setUser(user);

        if ("".equals(resume.getRealName())) {
            out.print("请填写真实姓名!");
            out.close();
            return;
        }

        String idCard = null;
        if ("".equals(resume.getIdCard())) {
            out.print("请填写身份证号!");
            out.close();
            return;
        } else {
            idCard = MatchUtil.isIdCard(resume.getIdCard());
            if (idCard == null) {
                out.print("身份证号有误!");
                out.close();
                return;
            }
        }

        if ("".equals(resume.getBirthday())) {
            out.print("请填写出生年月!");
            out.close();
            return;
        } else if (!idCard.equals(resume.getBirthday())) {
            out.print("出生年月和身份证号不符!");
            out.close();
            return;
        }

        if ("".equals(resume.getAddress())) {
            out.print("请填写你的住址!");
            out.close();
            return;
        }

        if ("".equals(resume.getHobby())) {
            out.print("请填写你的爱好!");
            out.close();
            return;
        }

        if ("".equals(resume.getMajor())) {
            out.print("请填写你的专业!");
            out.close();
            return;
        }

        if ("".equals(resume.getSelfIntroduce())) {
            out.print("请填写自我介绍!");
            out.close();
            return;
        }

        Map<String, Object> map = resumeService.addByCreate(resume);

        if (map.containsKey("success")) {
            User u = userService.findByIf("id", null, user.getId()).get(0);
            session.setAttribute("user", u);
            out.print("ok");
        } else {
            out.print(map.get("fail"));
        }

        out.flush();
        out.close();
    }

    //用户查看自己的简历
    @RequestMapping(value = "/showResumeSelf.do")
    public String showResumeSelf() throws IOException {
        return "resume/info_show_user";
    }

    //去修改简历的页面
    @RequestMapping(value = "/toEditResume.do")
    public String toEditResume() throws IOException {
        return "resume/info_edit_user";
    }

    /**
     * 用户修改好简历后保存 → 验证输入的信息 → service进行修改 → 回馈信息给页面
     *
     * @param resume   用户在页面修改的简历信息
     * @param session  域对象更新
     * @param response 响应结果
     * @throws IOException 输出异常
     */
    @RequestMapping(value = "/editResume.do")
    public void editResume(Resume resume, HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        User user = (User) session.getAttribute("user");
        resume.setUser(user);

        if ("".equals(resume.getRealName())) {
            out.print("真实姓名不能为空!");
            out.close();
            return;
        }

        String idCard = null;
        if ("".equals(resume.getIdCard())) {
            out.print("身份证号不能为空!");
            out.close();
            return;
        } else {
            idCard = MatchUtil.isIdCard(resume.getIdCard());
            if (idCard == null) {
                out.print("身份证号有误!");
                out.close();
                return;
            }
        }

        if ("".equals(resume.getBirthday())) {
            out.print("出生年月不能为空!");
            out.close();
            return;
        } else if (!idCard.equals(resume.getBirthday())) {
            out.print("出生年月和身份证号不符!");
            out.close();
            return;
        }

        if ("".equals(resume.getAddress())) {
            out.print("住址不能为空!");
            out.close();
            return;
        }

        if ("".equals(resume.getHobby())) {
            out.print("爱好不能为空!");
            out.close();
            return;
        }

        if ("".equals(resume.getMajor())) {
            out.print("专业不能为空!");
            out.close();
            return;
        }

        if ("".equals(resume.getSelfIntroduce())) {
            out.print("自我介绍不能为空!");
            out.close();
            return;
        }

        Map<String, Object> map = resumeService.updateByEdit(resume);

        if (map.containsKey("success")) {
            session.setAttribute("user", userService.findByIf("id", null, user.getId()).get(0));
            out.print("ok");
        } else {
            out.print(map.get("fail"));
        }

        out.flush();
        out.close();
    }


}
