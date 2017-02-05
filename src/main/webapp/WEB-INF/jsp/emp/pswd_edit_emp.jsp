<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工修改密码页面</title>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-primary am-text-lg">修改个人密码</strong> /
        <small>Edit Personal Password</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-6 am-u-md-pull-12">
        <form id="passwordform" class="am-form am-form-horizontal">
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">原密码</label>
                <div class="am-u-sm-6">
                    <input type="text" name="password1" placeholder="输入原密码">
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">新密码</label>
                <div class="am-u-sm-6">
                    <input type="text" name="password2" placeholder="输入新密码">
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">重复密码</label>
                <div class="am-u-sm-6">
                    <input type="text" name="password3" placeholder="重复密码">
                </div>
            </div>
            <div class="am-form-group">
                <div class="am-u-sm-6 am-u-sm-push-6">
                    <a href="javascript:changePassword()" class="am-btn am-btn-primary">保存修改</a>
                </div>
            </div>
        </form>

    </div>
</div>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    //员工修改密码
    function changePassword() {
        $.ajax({
            url: 'emp/changePassword.do?eid=${sessionScope.employee.id}',
            data: $('#passwordform').serialize(),
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html('修改成功!');
                } else {
                    $('#messageContent').html(result);
                }
                $('#messageTips').modal();
            }
        });
    }
</script>

</body>
</html>
