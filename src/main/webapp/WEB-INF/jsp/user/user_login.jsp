<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>用户登陆界面</title>
    <base href="<%=basePath%>"/>
    <link rel="icon" type="image/png" href="assets/i/cai.png">
    <link rel="stylesheet" href="assets/css/amazeui.min.css">
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/amazeui.min.js"></script>
    <script src="assets/js/app.js"></script>
    <script type="text/javascript">

        //用户登陆 ajx
        function userLogin() {
            $.ajax({
                url: 'user/login.do',
                data: $('#userLoginForm').serialize(),
                type: "post",
                success: function (result) {
                    if (result == 'ok') {
                        window.location.replace("/main/goUserMain.do");
                    } else {
                        $("input[type='password']").val('');
                        $('#userLoginTips').html(result).show();
                    }
                }
            });
        }

        //用户注册
        function userRegister() {
            $.ajax({
                url: 'user/register.do',
                data: $('#userRegisterForm').serialize(),
                type: "post",
                success: function (result) {
                    if (result == 'ok') {
                        $('#userRegisterFail').hide();
                        $('#userRegisterOK').html('注册成功!请登录!').show();
                    } else {
                        $('#userRegisterOK').hide();
                        $("input[type='password']").val('');
                        $('#userRegisterFail').html(result).show();
                    }
                }
            });
        }

    </script>
</head>
<body>

<%--标题--%>
<div class="header" align="center">
    <div class="am-g">
        <h1 style="font-size: 200%;color: green;margin-top: 30px">浮云梦影-招聘系统</h1>
        <p style="font-size: 14px">welcome to here! you can register and login our system!
            <br/>欢迎!你可以注册并登陆我们的招聘系统</p>
    </div>
    <hr/>
</div>

<%--分页显示标签--%>
<div class="am-g">
    <div class="am-u-lg-3 am-u-sm-centered">

        <%--标签整体  开始--%>
        <div class="am-tabs am-margin" data-am-tabs="{noSwipe: 1}">

            <%--标签名--%>
            <ul class="am-tabs-nav am-nav am-nav-tabs">
                <li class="am-active"><a href="#tab1">登陆</a></li>
                <li><a href="#tab2">邮箱/手机注册</a></li>
            </ul>

            <%--标签内容  开始--%>
            <div class="am-tabs-bd">

                <%--标签tab1  登陆--%>
                <div class="am-tab-panel am-fade am-in am-active" id="tab1" align="center" style="padding: 0 3em">
                    <form id="userLoginForm" method="post" class="am-form">
                        <br>
                        <div class="am-form-group">
                            <select data-am-selected="{btnSize: 'sm'}" name="loginMethod">
                                <option value="phoneLogin">手机号登陆</option>
                                <option value="emailLogin">邮箱登陆</option>
                            </select>
                        </div>
                        <input type="email" name="loginName" placeholder="请输入邮箱或手机号">
                        <br>
                        <input type="password" name="password" placeholder="请输入登陆密码">
                        <br/>
                        <div class="am-cf" style="padding: 0 2em 2em">
                            <a href="javascript:userLogin()"
                               class="am-btn am-btn-success am-btn-sm am-fl am-round">登 录</a>
                            <a href="/main/goMain.do"
                               class="am-btn am-btn-danger am-btn-sm am-fr am-round">返 回</a>
                        </div>
                        <div id="userLoginTips" class="am-alert am-alert-warning" hidden></div>
                    </form>
                </div>

                <%--标签tab2  注册--%>
                <div class="am-tab-panel am-fade" id="tab2" align="center" style="padding: 1em 3em">
                    <form id="userRegisterForm" method="post" class="am-form">
                        <br>
                        <div class="am-form-group">
                            <select data-am-selected="{btnSize: 'sm'}" name="registerMethod">
                                <option value="emailRegister">邮箱注册</option>
                                <option value="phoneRegister">手机号注册</option>
                            </select>
                        </div>
                        <input type="email" name="registerName" placeholder="请输入邮箱或手机号">
                        <br>
                        <input type="password" name="password" placeholder="请设置密码">
                        <br/>
                        <input type="password" name="passwordAgain" placeholder="请重复密码">
                        <br/>
                        <div class="am-cf" style="padding: 0 2em 1em">
                            <a href="javascript:userRegister()"
                               class="am-btn am-btn-success am-btn-sm am-fl am-round">注 册</a>
                            <a href="main/goMain.do"
                               class="am-btn am-btn-danger am-btn-sm am-fr am-round">返 回</a>
                        </div>
                        <div id="userRegisterFail" class="am-alert am-alert-warning" hidden></div>
                        <div id="userRegisterOK" class="am-alert am-alert-success" hidden></div>
                    </form>
                </div>

            </div>
            <%--标签内容  结束--%>

        </div>
        <%--标签整体  结束--%>

    </div>
</div>
<%--分页显示标签  结束--%>

</body>
</html>
