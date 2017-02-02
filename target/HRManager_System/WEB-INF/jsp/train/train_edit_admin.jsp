<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>编辑培训管理员页面</title>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-secondary am-text-lg">修改培训</strong> /
        <small>Edit Training</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-6 am-u-md-pull-12">
        <form id="editTrainForm" class="am-form am-form-horizontal">
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">培训老师</label>
                <div class="am-u-sm-6">
                    <select data-am-selected="{btnSize: 'sm'}" name="eid">
                        <option value="${trainingInfo.teacher.id}">${trainingInfo.teacher.resume.realName}</option>
                        <c:forEach var="es" items="${requestScope.employees}">
                            <option value="${es.id}">${es.resume.realName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">培训内容</label>
                <div class="am-u-sm-6">
                    <input type="text" name="content" value="${trainingInfo.content}">
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">培训地点</label>
                <div class="am-u-sm-6">
                    <input type="text" name="place" value="${trainingInfo.place}">
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">开始日期</label>
                <div class="am-u-sm-6 am-form-icon">
                    <input type="date" name="startDate" value="${trainingInfo.startDate}">
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">结束日期</label>
                <div class="am-u-sm-6 am-form-icon">
                    <input type="date" name="endDate" value="${trainingInfo.endDate}">
                </div>
            </div>
            <div class="am-form-group">
                <div class="am-u-sm-6 am-u-sm-push-6">
                    <a href="javascript:editTrainConfirm(${trainingInfo.id})" class="am-btn am-btn-success">确认修改</a>
                </div>
            </div>
        </form>
    </div>
</div>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--修改培训确认--%>
    function editTrainConfirm(tiid) {
        $.ajax({
            url: 'train/editTrainConfirm.do?tiid='+tiid,
            data: $('#editTrainForm').serialize(),
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("修改成功!");
                    showAllTrain();
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
