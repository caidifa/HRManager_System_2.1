<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加部门管理员页面</title>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-secondary am-text-lg">添加部门</strong> /
        <small>Add Department</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-6 am-u-md-pull-12">
        <form id="dNameForm" class="am-form am-form-horizontal">
            <br>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">部门名称</label>
                <div class="am-u-sm-6">
                    <input type="text" name="dName">
                </div>
            </div>
            <br>
            <div class="am-form-group">
                <div class="am-u-sm-6 am-u-sm-push-6">
                    <a href="javascript:addDepartConfirm()" class="am-btn am-btn-success">确认添加</a>
                </div>
            </div>
        </form>

    </div>
</div>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--添加部门确认--%>
    function addDepartConfirm() {
        $.ajax({
            url: 'dep/addDepartConfirm.do',
            data:$('#dNameForm').serialize(),
            type: "post",
            success: function (result) {
                if (result=='ok') {
                    $('#messageContent').html("添加成功!");
                    showAllDepart();
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
