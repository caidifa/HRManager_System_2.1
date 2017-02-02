<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta charset="UTF-8">
    <title>浮云梦影主页面</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="format-detection" content="telephone=no">
    <meta name="renderer" content="webkit">
    <link rel="alternate icon" type="image/png" href="assets/i/cai.png">
    <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
    <style>
        .get {
            background: #1E5B94;
            color: #fff;
            text-align: center;
            padding: 100px 0;
            height: 650px;
        }

        .get-title {
            font-size: 200%;
            border: 2px solid #fff;
            padding: 20px;
            display: inline-block;
        }

        .footer p {
            color: #7f8c8d;
            margin: 0;
            padding: 15px 0;
            text-align: center;
            background: #2d3e50;
        }
    </style>
</head>
<body>

<header class="am-topbar am-topbar-fixed-top">
    <div class="am-container">
        <h1 class="am-topbar-brand">浮云梦影 <small>company</small></h1>

        <div class="am-collapse am-topbar-collapse" id="collapse-head">
            <ul class="am-nav am-nav-pills am-topbar-nav">
                <li class="am-active"><a href="#">首页</a></li>
            </ul>
        </div>
    </div>
</header>

<div class="get">
    <div class="am-g">
        <div class="am-u-lg-12">
            <h1 class="get-title">人力资源管理系统 JavaEE项目</h1>
            <p>[学校]: 海同科技</p>
            <p>[班级]: HT1636&ensp;</p>
            <p>[作者]: 蔡宝龙&emsp;</p>
            <p>
                <a href="main/goUserLogin.do" class="am-btn am-btn-success am-round">
                    <i class="am-icon-user am-icon-fw"></i> 招聘系统
                </a>
            </p>
            <p>
                <a href="main/goEmpLogin.do" class="am-btn am-btn-secondary am-round">
                    <i class="am-icon-users am-icon-fw"></i> 员工系统
                </a>
            </p>
        </div>
    </div>
</div>

<footer class="footer">
    <p>© 2017 Iotek HT1636 CaiBaoLong JavaEE Project Human Resource Manager System</p>
</footer>

<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>

</body>
</html>
