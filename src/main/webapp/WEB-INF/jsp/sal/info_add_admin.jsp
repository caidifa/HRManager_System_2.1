<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>所有员工信息管理员页面</title>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-primary am-text-lg">工资结算</strong> /
        <small>Salary Account</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12">

        <form id="posiCheckedForm" class="am-form">
            <table class="am-table am-table-striped am-table-hover table-main">
                <thead>
                <tr>
                    <th>员工编号</th>
                    <th>所属部门</th>
                    <th>所属职位</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="el" items="${requestScope.employeeList}">
                    <tr>
                        <td>${el.empNumber}</td>
                        <td>${el.department.dName}</td>
                        <td>${el.position.pName}</td>
                        <td>
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <a href="javascript:salaryCount(${el.id})"
                                       class="am-btn am-btn-success am-btn-xs">
                                        <span class="am-icon-calculator"></span> 工资 结算
                                    </a>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="am-cf">
                共 ${requestScope.employeeListCount} 条记录
            </div>
            <hr/>
        </form>
    </div>
</div>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--工资结算--%>
    function salaryCount(eid) {
        $.ajax({
            url: 'sal/salaryCount.do?eid='+eid,
            type: "post",
            success: function (result) {
                $("#contentShow").html(result);
                $("#contentTips").modal();
            }
        });
    }

</script>

</body>
</html>
