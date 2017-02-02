<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>面试通知管理员页面</title>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">面试通知</strong> /
        <small>FaceNotice</small>
    </div>
</div>
<hr>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12">
        <form class="am-form">
            <table class="am-table am-table-striped am-table-hover table-main">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>面试时间</th>
                    <th>面试地址</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="fu" items="${sessionScope.toFaceUser}">
                    <tr>
                        <td>${fu.id}</td>
                        <td>${fu.faceTime}</td>
                        <td>${fu.location}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="am-cf">共 ${sessionScope.toFaceUserCount} 条记录</div>
            <hr/>
        </form>
    </div>
</div>
<%--内容 结束--%>

</body>
</html>
