<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工复议奖惩页面</title>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-primary am-text-lg">提交复议确认</strong>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-12">
        <form id="reviewContent" class="am-form am-form-horizontal">
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">奖惩记录ID</label>
                <div class="am-u-sm-6">
                    <input type="text" name="id" value="${blid}" readonly>
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">复议说明</label>
                <div class="am-u-sm-6">
                    <textarea class="text" rows="5" name="status" placeholder="输入复议理由"></textarea>
                </div>
            </div>
            <div class="am-form-group">
                <div class="am-u-sm-6 am-u-sm-push-4">
                    <a href="javascript:reviewConfirm(${sessionScope.employee.id})" class="am-btn am-btn-success am-btn-xs">
                        <span class="am-icon-check"></span> 确 定
                    </a>
                </div>
            </div>
        </form>
    </div>
</div>
<%--内容 结束--%>
<script type="text/javascript">
    <%--复议--%>
    function reviewConfirm(eid) {
        $.ajax({
            url: 'check/reviewConfirm.do',
            data: $('#reviewContent').serialize(),
            type: "post",
            success: function (result) {
                if(result == 'ok') {
                    showMyBonus(eid);
                    $('#contentTips').modal('close');
                }
            }
        });
    }
</script>
</body>
</html>
