<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的薪资页面</title>
    <script src="assets/js/amazeui.min.js"></script>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-secondary am-text-lg">我的薪资</strong> /
        <small>My Salary Information</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12">
        <table class="am-table am-table-striped am-table-bordered am-table-compact am-table-hover" id="mySalAll">
            <thead>
            <tr>
                <th>ID</th>
                <th>年月份</th>
                <th>奖励费用</th>
                <th>惩罚费用</th>
                <th>社保扣除</th>
                <th>结算工资</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="sl" items="${requestScope.salaryList}">
                <tr>
                    <td>${sl.id}</td>
                    <td>${sl.yMonth}</td>
                    <td>${sl.bCost}</td>
                    <td>${sl.pCost}</td>
                    <td>${sl.sCost}</td>
                    <td>${sl.total}</td>
                    <td>
                        <c:if test="${empty sl.status}">未复议</c:if>
                        <c:if test="${not empty sl.status}">有复议</c:if>
                    </td>
                    <td>
                        <div class="am-btn-toolbar">
                            <div class="am-btn-group am-btn-group-xs">
                                <a href="javascript:showSalDetail(${sl.id})" class="am-btn am-btn-success am-btn-xs">
                                    <span class="am-icon-eye"></span> 详 情
                                </a>
                            </div>
                            <div class="am-btn-group am-btn-group-xs">
                                <a href="javascript:reviewSal(${sl.id});" class="am-btn am-btn-warning am-btn-xs">
                                    <span class="am-icon-question"></span> 复 议
                                </a>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--初始化表格--%>
    $(function () {
        $('#mySalAll').DataTable();
    });
    <%--显示薪资详情--%>
    function showSalDetail(sid) {
        $.ajax({
            url: 'sal/showSalDetail.do?sid=' + sid,
            type: "post",
            success: function (result) {
                $("#contentShow").html(result);
                $("#contentTips").modal();
            }
        });
    }
    <%--对薪资的复议--%>
    function reviewSal(sid) {
        $.ajax({
            url: 'sal/reviewSal.do?sid=' + sid,
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
