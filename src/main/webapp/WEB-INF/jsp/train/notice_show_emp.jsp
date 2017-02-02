<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>培训信息员工页面</title>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-primary am-text-lg">我的培训通知</strong> /
        <small>My Training Notice</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12">
        <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
            <tr>
                <th>ID</th>
                <th>培训老师名</th>
                <th>培训内容</th>
                <th>开始日期</th>
                <th>结束日期</th>
                <th>培训地点</th>
                <th>状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="til" items="${sessionScope.trainingInfoList}">
                <tr>
                    <td>${til.id}</td>
                    <td>${til.teacher.resume.realName}</td>
                    <td>${til.content}</td>
                    <td>${til.startDate}</td>
                    <td>${til.endDate}</td>
                    <td>${til.place}</td>
                    <td>${til.status}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%--内容 结束--%>

</body>
</html>
