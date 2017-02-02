<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息显示页面</title>
</head>
<body>
<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-primary am-text-lg">个人信息</strong> /
        <small>Personal Information</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-6 am-u-md-pull-12">
        <form id="userChangeform" class="am-form am-form-horizontal">
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">邮箱</label>
                <div class="am-u-sm-6">
                    <input type="email" name="email" value="${sessionScope.user.email}" placeholder="未绑定邮箱" disabled>
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">手机号</label>
                <div class="am-u-sm-6">
                    <input type="tel" name="phone" value="${sessionScope.user.phone}" placeholder="未绑定手机号" disabled>
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">登陆密码</label>
                <div class="am-u-sm-6">
                    <input type="text" name="password" value="${sessionScope.user.password}" placeholder="未设置" disabled>
                </div>
            </div>
        </form>
    </div>
</div>
<%--内容 结束--%>
</body>
</html>
