<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>面试开始</title>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-primary am-text-lg">面试开始</strong> /
        <small>Face Start</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-6 am-u-md-pull-12">
        <form id="faceContent" class="am-form am-form-horizontal">
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">你爱共产党吗?</label>
                <div class="am-u-sm-6">
                    <select data-am-selected="{btnSize: 'sm'}" name="answer">
                        <option value="yes">爱</option>
                        <option value="no">不爱</option>
                    </select>
                </div>
            </div>
            <div class="am-form-group">
                <div class="am-u-sm-6 am-u-sm-push-6">
                    <a href="javascript:endFace(${sessionScope.fnid})" class="am-btn am-btn-success">结束面试</a>
                </div>
            </div>
        </form>
    </div>
</div>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--结束面试--%>
    function endFace(fnid) {
        $.ajax({
            url: 'face/endFace.do?fnid='+fnid,
            data: $('#faceContent').serialize(),
            type: "post",
            success: function (result) {
                $('#messageContent').html(result);
                $('#messageTips').modal();
                showFNByUser();
            }
        });
    }
</script>
</body>
</html>
