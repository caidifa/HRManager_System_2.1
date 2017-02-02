<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>员工登陆界面</title>
    <base href="<%=basePath%>"/>
    <link rel="icon" type="image/png" href="assets/i/cai.png">
    <link rel="stylesheet" href="assets/css/amazeui.min.css">
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/amazeui.min.js"></script>
    <script src="assets/js/app.js"></script>
    <script type="text/javascript">
        //员工登陆 ajx
        function empLogin() {
            $.ajax({
                url: 'emp/login.do',
                data: $('#empLoginForm').serialize(),
                type: "post",
                success: function (result) {
                    if (result == 'okemp') {
                        window.location.replace("/main/goEmpMain.do");//非管理员操作页面
                    } else if (result == 'okadmin') {
                        window.location.replace("/main/goAdminMain.do");//管理员操作页面
                    } else {
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
        <h1 style="font-size: 200%;color: dodgerblue;margin-top: 30px">浮云梦影-员工/管理员系统</h1>
        <p style="font-size: 14px">welcome to here! you can enter this system if you are employee or admin of our company
            <br/>欢迎!如果你是我们公司的员工或管理员便可进入相应的系统</p>
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
            </ul>

            <%--标签内容  开始--%>
            <div class="am-tabs-bd">

                <%--标签tab1  登陆--%>
                <div class="am-tab-panel am-fade am-in am-active" id="tab1" align="center" style="padding: 0 3em">
                    <form id="empLoginForm" method="post" class="am-form">
                        <br>
                        <div class="am-form-group">
                            <select data-am-selected="{btnSize: 'sm'}" name="loginMethod">
                                <option value="empLogin">员工登陆</option>
                                <option value="adminLogin">管理员登陆</option>
                            </select>
                        </div>
                        <input type="number" name="empNumber" placeholder="请输入员工编号">
                        <br>
                        <input type="password" name="password" placeholder="请输入员工密码">
                        <br/>
                        <div class="am-cf" style="padding: 0 2em 2em">
                            <a href="javascript:empLogin()"
                               class="am-btn am-btn-primary am-btn-sm am-fl am-round">登 录</a>
                            <a href="main/goMain.do"
                               class="am-btn am-btn-danger am-btn-sm am-fr am-round">退 出</a>
                        </div>
                        <div id="userRegisterFail" class="am-alert am-alert-warning" hidden></div>
                    </form>
                </div>

            </div>
            <%--标签内容  结束--%>
        </div>
        <%--标签整体  结束--%>
    </div>
    <hr/>
</div>

</body>
</html>
