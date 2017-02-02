<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工奖惩页面</title>
    <script src="assets/js/amazeui.min.js"></script>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-primary am-text-lg">我的奖惩信息</strong> /
        <small>My Bonuspenalty Info</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12">
        <table class="am-table am-table-striped am-table-bordered am-table-compact am-table-hover" id="myBonusAll">
            <thead>
            <tr>
                <th>ID</th>
                <th>员工编号</th>
                <th>原因</th>
                <th>时间</th>
                <th>奖惩额度</th>
                <th>类型</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="bl" items="${requestScope.bonuspenaltyList}">
                <tr>
                    <td>${bl.id}</td>
                    <td>${bl.employee.empNumber}</td>
                    <td>${bl.reason}</td>
                    <td>${bl.time}</td>
                    <td>${bl.money}</td>
                    <td>${bl.type}</td>
                    <td>
                        <c:if test="${empty bl.status}">未复议</c:if>
                        <c:if test="${not empty bl.status}">有复议</c:if>
                    </td>
                    <td>
                        <c:if test="${empty bl.status}">
                            <div class="am-btn-group am-btn-group-xs">
                                <a href="javascript:review(${bl.id})" class="am-btn am-btn-warning am-btn-xs">
                                    <span class="am-icon-question"></span> 复 议
                                </a>
                            </div>
                        </c:if>
                    </td>
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
        $('#myBonusAll').DataTable();
    });
    <%--复议--%>
    function review(blid) {
        $.ajax({
            url: 'check/review.do?blid=' + blid,
            type: "post",
            success: function (result) {
                $('#contentShow').html(result);
                $('#contentTips').modal();
            }
        });
    }
</script>
</body>
</html>
