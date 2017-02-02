<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>所有员工信息员工页面</title>
    <script type="text/javascript">
        $(function () {
            $('#employeeAll').DataTable();
        });
    </script>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-secondary am-text-lg">所有员工信息</strong> /
        <small>All Employee Information</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12">
        <table class="am-table am-table-striped am-table-bordered am-table-compact am-table-hover" id="employeeAll">
            <thead>
            <tr>
                <th>ID</th>
                <th>员工名</th>
                <th>所属部门</th>
                <th>在职职位</th>
                <th>员工编号</th>
                <th>薪水</th>
                <th>等级</th>
                <th>状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="el" items="${requestScope.employeeList}">
                <tr>
                    <td>${el.id}</td>
                    <td>${el.resume.realName}</td>
                    <td>${el.department.dName}</td>
                    <td>${el.position.pName}</td>
                    <td>${el.empNumber}</td>
                    <td>${el.salary}</td>
                    <td>
                        <c:if test="${el.level == 0}">非管理员</c:if>
                        <c:if test="${el.level == 1}">管理员</c:if>
                    </td>
                    <td>${el.status}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%--内容 结束--%>

</body>
</html>
