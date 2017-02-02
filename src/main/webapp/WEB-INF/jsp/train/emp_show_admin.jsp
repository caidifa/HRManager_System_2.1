<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>参加培训的员工信息</title>
</head>
<body>

<%--内容 开始--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-primary am-text-lg">参加此次培训的员工信息</strong> /
        <small>Info Of Employee In This Training</small>
    </div>
</div>
<hr/>
<div class="am-g">
    <div class="am-u-sm-12">
        <form id="trainCheckedForm" class="am-form">
            <table class="am-table am-table-striped am-table-hover table-main">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>员工姓名</th>
                    <th>员工编号</th>
                    <th>部门</th>
                    <th>职位</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="e" items="${requestScope.addedEmp}">
                    <tr>
                        <td>${e.id}</td>
                        <td>${e.resume.realName}</td>
                        <td>${e.empNumber}</td>
                        <td>${e.department.dName}</td>
                        <td>${e.position.pName}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <hr/>
        </form>
    </div>
</div>
<%--内容 结束--%>

</body>
</html>
