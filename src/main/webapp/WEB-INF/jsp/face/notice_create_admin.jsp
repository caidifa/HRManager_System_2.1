<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>面试通知创建管理员页面</title>
</head>
<>

<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-primary am-text-lg">新建面试通知</strong> /
        <small>Face Notice</small>
    </div>
</div>
<hr/>

<div class="am-g">
    <div class="am-u-sm-12 am-u-md-6 am-u-md-pull-12">
        <form id="faceNoticeForm" class="am-form am-form-horizontal">
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">面试员(管理员)</label>
                <div class="am-u-sm-6">
                    <select data-am-selected="{btnSize: 'sm'}" name="empID">
                        <option value="0">--请选择--</option>
                        <c:forEach var="as" items="${requestScope.admins}">
                            <option value="${as.id}">${as.resume.realName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">面试时间</label>
                <div class="am-u-sm-6">
                    <input type="datetime-local" class="am-form-field am-input-sm" name="faceTime">
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">地点</label>
                <div class="am-u-sm-6">
                    <input type="text" name="location" value="">
                </div>
            </div>
            <div class="am-form-group">
                <div class="am-u-sm-6 am-u-sm-push-6">
                    <a href="javascript:sendFaceNotice(${requestScope.piid});" class="am-btn am-btn-primary">发送通知</a>
                </div>
            </div>
        </form>

    </div>
</div>

<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    //发送面试通知
    function sendFaceNotice(piid) {
        $.ajax({
            url: 'face/sendFaceNotice.do?piid='+piid,
            data: $('#faceNoticeForm').serialize(),
            type: "post",
            success: function (result) {
                if (result=='ok') {
                    $('#messageContent').html("发送成功!");
                }else {
                    $('#messageContent').html(result);
                }
                $('#messageTips').modal();
            }
        });
    }
</script>

</body>
</html>
