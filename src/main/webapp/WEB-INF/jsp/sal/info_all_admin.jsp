<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工考勤页面</title>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-secondary am-text-lg">员工薪资</strong> /
        <small>Salary Information</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>

<div class="am-g">
    <div class="am-u-sm-12">
        <table class="am-table am-table-striped am-table-bordered am-table-compact am-table-hover" id="salAll">
            <thead>
            <tr>
                <th>ID</th>
                <th>员工名</th>
                <th>部门名</th>
                <th>职位名</th>
                <th>年月份</th>
                <th>奖励费用</th>
                <th>惩罚费用</th>
                <th>社保扣除</th>
                <th>结算工资</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="sl" items="${requestScope.salaryList}">
                <tr>
                    <td>${sl.id}</td>
                    <td>${sl.employee.resume.realName}</td>
                    <td>${sl.employee.department.dName}</td>
                    <td>${sl.employee.position.pName}</td>
                    <td>${sl.yMonth}</td>
                    <td>${sl.bCost}</td>
                    <td>${sl.pCost}</td>
                    <td>${sl.sCost}</td>
                    <td>${sl.total}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%--内容 结束--%>

<script type="text/javascript">
    <%--初始化表格--%>
    $(function () {
        $('#salAll').DataTable();
    });
</script>
</body>
</html>
