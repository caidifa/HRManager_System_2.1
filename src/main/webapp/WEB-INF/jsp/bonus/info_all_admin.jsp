<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>管理员查看员工奖惩页面</title>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-primary am-text-lg">奖惩信息</strong> /
        <small>BonusPenalty Info</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12">
        <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-xs">
                <a href="javascript:addBP();" class="am-btn am-btn-success am-btn-xs">
                    <span class="am-icon-plus"></span> 添加奖惩
                </a>
            </div>
        </div>
        <hr>
        <table class="am-table am-table-striped am-table-bordered am-table-compact am-table-hover" id="bonusAll">
            <thead>
            <tr>
                <th>ID</th>
                <th>员工编号</th>
                <th>原因</th>
                <th>时间</th>
                <th>奖惩金</th>
                <th>类型</th>
                <th>复议状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="bl" items="${requestScope.bonusPenaltyList}">
                <tr>
                    <td>${bl.id}</td>
                    <td>${bl.employee.empNumber}</td>
                    <td>${bl.reason}</td>
                    <td>${bl.time}</td>
                    <td>${bl.money}元</td>
                    <td>${bl.type}</td>
                    <td>${bl.status}<c:if test="${empty bl.status}">无</c:if></td>
                    <td>
                        <c:if test="${not empty bl.status and bl.status ne '已处理' and bl.status ne '已驳回'}">
                            <div class="am-btn-group am-btn-group-xs">
                                <a href="javascript:handleReview(${bl.id})" class="am-btn am-btn-success am-btn-xs">
                                    <span class="am-icon-check"></span> 接受并处理
                                </a>
                            </div>
                            <div class="am-btn-group am-btn-group-xs">
                                <a href="javascript:cancelReview(${bl.id})" class="am-btn am-btn-danger am-btn-xs">
                                    <span class="am-icon-close"></span> 驳回并取消
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
        $('#bonusAll').DataTable({
            "ordering": false
        });
    });
    <%--添加奖惩--%>
    function addBP() {
        $.ajax({
            url: 'check/addBP.do',
            type: "post",
            success: function (result) {
                $("#mainShow").html(result);
            }
        });
    }
    <%--接受并处理复议--%>
    function handleReview(blid) {
        $.ajax({
            url: 'check/handleReview.do?blid=' + blid,
            type: "post",
            success: function (result) {
                $('#contentShow').html(result);
                $('#contentTips').modal();
                showAllBonus();
            }
        });
    }
    <%--驳回并取消复议--%>
    function cancelReview(blid) {
        $.ajax({
            url: 'check/cancelReview.do?blid=' + blid,
            type: "post",
            success: function (result) {
                $('#contentShow').html(result);
                $('#contentTips').modal();
                showAllBonus();
            }
        });
    }
</script>
</body>
</html>
