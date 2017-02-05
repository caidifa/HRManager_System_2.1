<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工打开操作</title>
</head>
<body>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-secondary am-text-lg">模拟打卡</strong> /
        <small>Demo Checking</small>
    </div>
</div>
<hr/>

<div class="am-g">
    <div class="am-u-sm-12 am-u-md-12 am-u-md-pull-12">
        <form id="checkingTimeForm" class="am-form am-form-horizontal">
            <div class="am-form-group">
                <label class="am-u-sm-2 am-form-label">当前时间</label>
                <div class="am-u-sm-6">
                    <h1 id="nowTime">当前时间</h1>
                    通过手动设置当前时间来模拟!(已禁用)
                </div>
                <div class="am-u-sm-4"></div>
            </div>
            <hr>

            <div class="am-form-group">
                <label class="am-u-sm-2 am-form-label">模拟当前时间</label>
                <div class="am-u-sm-3 am-form-icon">
                    <input type="datetime-local" id="inTime" readonly>
                </div>
                <div class="am-u-sm-7">
                    <a href="javascript:checkIn(${sessionScope.employee.id})" class="am-btn am-btn-success">上班打卡</a>
                </div>
            </div>
            <hr>
            <div class="am-form-group">
                <label class="am-u-sm-2 am-form-label">模拟当前时间</label>
                <div class="am-u-sm-3 am-form-icon">
                    <input type="datetime-local" id="outTime" readonly>
                </div>
                <div class="am-u-sm-7">
                    <a href="javascript:checkOut(${sessionScope.employee.id})" class="am-btn am-btn-warning">下班打卡</a>
                </div>
            </div>
        </form>
    </div>
</div>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--上班打卡--%>
    function checkIn(eid) {
        $.ajax({
            url: 'check/checkIn.do?eid='+eid+'&inTime='+$('#inTime').val(),
            type: "post",
            success: function (result) {
                $('#messageContent').html(result+'<br>');
                $('#messageTips').modal();
            }
        });
    }
    <%--下班打卡--%>
    function checkOut(eid) {
        $.ajax({
            url: 'check/checkOut.do?eid='+eid+'&outTime='+$('#outTime').val(),
            type: "post",
            success: function (result) {
                $('#messageContent').html(result+'<br>');
                $('#messageTips').modal();
            }
        });
    }
    // 定义获取和更新时间的函数
    function showTime() {
        var nowTime = new Date();
        nowTime.toLocaleDateString();
        $("#nowTime").html(nowTime.toLocaleString());
        setTimeout("showTime()", 1000);
    }
    // 页面加载完成后执行上面的自定义函数
    $(function(){
        showTime()
    })
</script>

</body>
</html>
