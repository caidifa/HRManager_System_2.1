<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>管理员查看所有考勤页面</title>
    <script type="text/javascript">
        $(function () {
            $('#checkingAll').DataTable({
                "ordering": false
            });
        });
    </script>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-secondary am-text-lg">考勤信息</strong> /
        <small>Checking Information</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12">
        <table class="am-table am-table-striped am-table-bordered am-table-compact am-table-hover" id="checkingAll">
            <thead>
            <tr>
                <th>ID</th>
                <th>员工编号</th>
                <th>日期</th>
                <th>上班时间</th>
                <th>下班时间</th>
                <th>结果</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cl" items="${requestScope.checkingList}">
                <tr>
                    <td>${cl.id}</td>
                    <td>${cl.employee.empNumber}</td>
                    <td>${cl.checkDate}</td>
                    <td>${cl.inTime}</td>
                    <td>${cl.outTime}</td>
                    <td>${cl.result}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%--内容 结束--%>

</body>
</html>
