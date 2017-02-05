<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加奖惩管理员页面</title>
    <script src="assets/js/amazeui.min.js"></script>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-secondary am-text-lg">添加奖惩</strong> /
        <small>Add Bonuspenalty</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-6 am-u-md-pull-12">
        <form id="newBPForm" class="am-form am-form-horizontal">
            <br>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">员工</label>
                <div class="am-u-sm-6">
                    <select data-am-selected="{btnSize: 'sm'}" name="eid">
                        <option value="0">--选择员工--</option>
                        <c:forEach var="el" items="${requestScope.employeeList}">
                            <option value="${el.id}">${el.resume.realName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <br>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">奖惩原因</label>
                <div class="am-u-sm-6">
                    <input type="text" name="reason">
                </div>
            </div>
            <br>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">奖惩金</label>
                <div class="am-u-sm-6">
                    <input type="number" name="money" value="0">
                </div>
            </div>
            <br>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">类型</label>
                <div class="am-u-sm-6">
                    <select data-am-selected="{btnSize: 'sm'}" name="type">
                        <option value="奖励">奖励</option>
                        <option value="惩罚">惩罚</option>
                    </select>
                </div>
            </div>
            <br>
            <div class="am-form-group">
                <div class="am-u-sm-6 am-u-sm-push-6">
                    <a href="javascript:addBPConfirm()" class="am-btn am-btn-success">确认添加</a>
                </div>
            </div>
        </form>
    </div>
</div>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--添加奖惩确认--%>
    function addBPConfirm() {
        $.ajax({
            url: 'check/addBPConfirm.do',
            data:$('#newBPForm').serialize(),
            type: "post",
            success: function (result) {
                if (result=='ok') {
                    $('#messageContent').html("添加成功!");
                    showAllBonus();
                }else {
                    $('#messageContent').html(result);
                }
                $('#messageTips').modal();
            }
        });
    }
</script>

</body>
</html>
