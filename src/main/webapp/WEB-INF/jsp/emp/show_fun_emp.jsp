<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>个性功能页面</title>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-secondary am-text-lg">个性功能</strong> /
        <small>如果你是普通员工可以进行购买升级为管理员操作</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-6 am-u-md-pull-12">
        <form id="funForm" class="am-form am-form-horizontal">
            <br>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">工资卡余额:</label>
                <c:if test="${employee.level == 0}">
                    <div class="am-u-sm-3">
                        <input type="text" value="${employee.balance}元" readonly>
                    </div>
                    <div class="am-u-sm-3">
                        <a href="javascript:levelUp(${employee.id})" class="am-btn am-btn-primary">升级管理员</a>
                    </div>
                </c:if>
                <c:if test="${employee.level == 1}">
                    <div class="am-u-sm-6">
                        <input type="text" value="${employee.balance}元" readonly>
                    </div>
                </c:if>
            </div>
            <br>
            <hr>
            <br>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">需要取出或存进的金额:</label>
                <div class="am-u-sm-6">
                    <input type="number" name="money" value="0" placeholder="输入金额数">
                </div>
            </div>
            <br>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">操作:</label>
                <div class="am-u-sm-3">
                    <a href="javascript:saveMoney(${employee.id})" class="am-btn am-btn-success">存入</a>
                </div>
                <div class="am-u-sm-3">
                    <a href="javascript:getMoney(${employee.id})" class="am-btn am-btn-danger">取出</a>
                </div>
            </div>
            <br>
            <hr>
        </form>
    </div>
</div>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--升级为管理员--%>
    function levelUp(eid) {
        $.ajax({
            url: 'emp/levelUp.do?eid=' + eid,
            data: $('#funForm').serialize(),
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("升级成功!");
                    showFun();
                } else {
                    $('#messageContent').html(result);
                }
                $('#messageTips').modal();
            }
        });
    }
    <%--存入--%>
    function saveMoney(eid) {
        $.ajax({
            url: 'emp/saveMoney.do?eid=' + eid,
            data: $('#funForm').serialize(),
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("存入成功!");
                    showFun();
                } else {
                    $('#messageContent').html(result);
                }
                $('#messageTips').modal();
            }
        });
    }
    <%--取出--%>
    function getMoney(eid) {
        $.ajax({
            url: 'emp/getMoney.do?eid=' + eid,
            data: $('#funForm').serialize(),
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("取出成功!");
                    showFun();
                } else {
                    $('#messageContent').html(result);
                }
                $('#messageTips').modal();
            }
        });
    }
</script>

</body>
</html>
