package com.cai.web.controller;

import com.cai.domain.FaceInfo;
import com.cai.domain.FaceNotice;
import com.cai.domain.HireInfo;
import com.cai.domain.User;
import com.cai.service.*;
import com.cai.utils.MailUtil;
import com.cai.utils.MatchUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 用户操作控制
 */
@Controller
@RequestMapping("/user")
public class UserController {
    //<editor-fold desc="所需的业务接口">
    @Resource
    private UserService userService;
    @Resource
    private HireInfoService hireInfoService;
    @Resource
    private FaceNoticeService faceNoticeService;
    @Resource
    private FaceInfoService faceInfoService;
    //</editor-fold>

    //<editor-fold desc="用户登陆页面的操作(含注册)">

    /**
     * 用户登陆操作 → 验证(reg) → 数据库匹配user信息(service) →
     * 成功则存入所需对象到session → 返回信息给页面(ajax) → 关闭资源
     * OK
     *
     * @param loginMethod 登陆方式
     * @param loginName   登录名
     * @param user        封装用户输入的信息
     * @param session     存放登陆成功后所需对象的域对象
     * @param response    响应结果
     * @throws IOException 打印流的异常
     */
    @RequestMapping(value = "/login.do")
    public void login(@RequestParam("loginMethod") String loginMethod,
                      @RequestParam("loginName") String loginName,
                      User user, HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        // 1.判断在对应登陆方式下用户填入的登陆名
        if ("emailLogin".equals(loginMethod)) {
            if ("".equals(loginName)) {
                out.print("邮箱未填写!");
                out.close();
                return;
            } else if (!MatchUtil.isMail(loginName)) {
                out.print("邮箱格式不对!");
                out.close();
                return;
            }
            user.setEmail(loginName);
        } else {
            if ("".equals(loginName)) {
                out.print("手机号未填写!");
                out.close();
                return;
            }
            // if (!MatchUtil.isPhone(loginName)) {
            //     out.print("手机号格式不对!");
            //     out.close();
            //     return;
            // }
            user.setPhone(loginName);
        }

        // 2.判断用户输入的密码
        if ("".equals(user.getPassword())) {
            out.print("登陆密码未填写!");
            out.close();
            return;
        }

        // 3.把用户输入的信息交给service层处理
        Map<String, Object> map = userService.findByLogin(user);

        // 4.把结果返回给页面,让其通过ajax判断
        if (map.containsKey("success")) {

            // session放入所需的对象

            // ⑴ 登陆的当前user
            User u = (User) map.get("success");
            session.setAttribute("user", u);

            // ⑵ 在"招聘中"的所有招聘信息及其数量
            map.clear();
            map.put("status", "招聘中");
            List<HireInfo> hireInfoList = hireInfoService.findByMap(map);
            session.setAttribute("hireInfoList", hireInfoList);
            session.setAttribute("hireInfoListCount", hireInfoList.size());

            // ⑶ 有关自己的"通知中"的面试通知及其数量
            List<FaceNotice> faceNoticeList = faceNoticeService.findByUser(u, "通知中");
            session.setAttribute("faceNoticeList", faceNoticeList);
            session.setAttribute("faceNoticeListCount", faceNoticeList.size());

            // ⑷ 有关自己的面试情况通知(非"等通知")
            List<FaceInfo> faceInfoList = faceInfoService.findByUser(u, "成功");
            faceInfoList.addAll(faceInfoService.findByUser(u, "失败"));
            session.setAttribute("faceInfoList", faceInfoList);
            session.setAttribute("faceInfoListCount", faceInfoList.size());

            out.print("ok");
        } else {
            out.print(map.get("fail"));
        }

        // 5.关闭资源
        out.flush();
        out.close();

    }

    /**
     * 用户注册操作 → 验证(reg) → 数据库比对是否重复(service) → 返回信息给页面(ajax) → 关闭资源
     * OK
     *
     * @param registerMethod 注册方式
     * @param registerName   注册名
     * @param passwordAgain  重复的密码
     * @param user           封装用户输入的信息
     * @param response       响应结果
     * @throws IOException 打印流的异常
     */
    @RequestMapping(value = "/register.do")
    public void register(@RequestParam("registerMethod") String registerMethod,
                         @RequestParam("registerName") String registerName,
                         @RequestParam("passwordAgain") String passwordAgain,
                         User user, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        // 1.判断在对应注册方式下用户填入的注册名
        if ("emailRegister".equals(registerMethod)) {
            if ("".equals(registerName)) {
                out.print("邮箱未填写!");
                out.close();
                return;
            } else if (!MatchUtil.isMail(registerName)) {
                out.print("错误的邮箱格式!");
                out.close();
                return;
            }
            user.setEmail(registerName);
        } else {
            if ("".equals(registerName)) {
                out.print("手机号未填写!");
                out.close();
                return;
            } else if (!MatchUtil.isPhone(registerName)) {
                out.print("错误的手机号格式!");
                out.close();
                return;
            }
            user.setPhone(registerName);
        }

        // 2.判断用户设置的2次密码
        if ("".equals(user.getPassword())) {
            out.print("密码未填写!");
            out.close();
            return;
        } else if ("".equals(passwordAgain)) {
            out.print("重复密码未填写!");
            out.close();
            return;
        } else if (!user.getPassword().equals(passwordAgain)) {
            out.print("2次密码不同!");
            out.close();
            return;
        }

        // 3.把用户输入的信息交给service层处理
        Map<String, Object> map = userService.addByRegister(user);

        // 4.把结果返回给页面,让其通过ajax判断
        if (map.containsKey("success")) {
            out.print("ok");
        } else {
            out.print(map.get("fail"));
        }

        // 5.关闭资源
        out.flush();
        out.close();

    }

    /**
     * 用户通过邮箱取回密码
     *
     * @param mailAddress 邮箱地址
     * @param response    响应
     * @throws IOException 打印流异常
     */
    @RequestMapping(value = "/postPassword.do")
    public void postPassword(@RequestParam("mailAddress") String mailAddress, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        // 1.判断用户输入的邮箱地址是否存在
        if ("".equals(mailAddress)) {
            out.print("请填写邮箱!");
            out.close();
            return;
        } else if (!MatchUtil.isMail(mailAddress)) {
            out.print("错误的邮箱格式!");
            out.close();
            return;
        }
        List<User> users = userService.findByIf("email", mailAddress, 0);
        if (users.size() == 0) {
            out.print("该邮箱还未注册!");
            out.close();
            return;
        }
        String password = users.get(0).getPassword();
        String object = "你的密码";
        String content = "你在浮云梦影招聘系统注册的账号的密码为:[" + password + "]请妥善保管!";
        try {
            MailUtil.mailForPassword(object, content, mailAddress);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("邮件发送异常!");
        }
        out.print("ok");
        out.flush();
        out.close();
    }

    //</editor-fold>

    //<editor-fold desc="用户个人信息操作">

    /**
     * 用户修改个人信息
     * OK
     *
     * @param user     修改后的信息
     * @param session  域对象
     * @param response 响应
     * @throws IOException 输出异常
     */
    @RequestMapping(value = "/saveChange.do")
    public void saveChange(User user, HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        //密码不能为空
        if ("".equals(user.getPassword())) {
            out.print("密码不能为空!");
            out.close();
            return;
        }

        //判断邮箱和手机格式
        if ("".equals(user.getEmail())) {
            out.print("邮箱不能为空!");
            out.close();
            return;
        } else if (!MatchUtil.isMail(user.getEmail())) {
            out.print("错误的邮箱格式!");
            out.close();
            return;
        }
        if ("".equals(user.getPhone())) {
            out.print("手机号未填写!");
            out.close();
            return;
        } else if (!MatchUtil.isPhone(user.getPhone())) {
            out.print("错误的手机号格式!");
            out.close();
            return;
        }

        //把用户修改的信息交给service层处理
        Map<String, Object> map = userService.updateUser(user);

        //返回结果给页面,让其通过ajax判断
        if (map.containsKey("success")) {
            //成功则更新session里的user
            session.setAttribute("user", userService.findByIf("id", null, user.getId()).get(0));
            out.print("ok");
        } else {
            //失败则request加入fail信息带给刷新登陆界面让信息显示
            out.print(map.get("fail"));
        }

        //释放资源
        out.flush();
        out.close();
    }

    //查看个人信息页面 ajax载入
    @RequestMapping(value = "/userInfo.do")
    public String userInfo() throws IOException {
        return "user/info_show";
    }

    //编辑个人信息页面(ajax)
    @RequestMapping(value = "/toEditUserInfo.do")
    public String toEditUserInfo() {
        return "user/info_edit";
    }
    //</editor-fold>

    //用户登出 清空session
    @RequestMapping(value = "/logout.do")
    public String logout(HttpSession session) {
        session.invalidate();
        return "user/user_login";
    }

}
