<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加培训员工-管理员页面</title>
    <script src="<%=request.getContextPath()%>assets/js/amazeui.min.js"></script>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-secondary am-text-lg">添加培训员工</strong> /
        <small>Add Training Employee</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-6 am-u-md-pull-12">
        <form id="trainEmpForm" class="am-form am-form-horizontal">
            <c:forEach var="d" items="${departmentList}">
                <div class="am-form-group">
                    <label class="am-u-sm-6 am-form-label">${d.dName}</label>
                    <div class="am-u-sm-6">
                        <select multiple data-am-selected name="eids">
                            <c:forEach var="es" items="${requestScope.employees}">
                                <c:if test="${trainingInfo.teacher.id ne es.id}">
                                    <c:if test="${d.id eq es.department.id}">
                                        <option value="${es.id}">${es.resume.realName}</option>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </c:forEach>
            <div class="am-form-group">
                <div class="am-u-sm-6 am-u-sm-push-6">
                    <a href="javascript:addTrainEmpConfirm(${trainingInfo.id})" class="am-btn am-btn-success">确认添加</a>
                </div>
            </div>
        </form>
    </div>
</div>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--添加培训员工确认--%>
    function addTrainEmpConfirm(tiid) {
        $.ajax({
            url: 'train/addTrainEmpConfirm.do?tiid='+tiid,
            data: $('#trainEmpForm').serialize(),
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("添加成功!");
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
