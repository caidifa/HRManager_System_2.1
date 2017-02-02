<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改职位管理员页面</title>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-secondary am-text-lg">修改职位</strong> /
        <small>Edit Position</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-6 am-u-md-pull-12">
        <form id="posiEditForm" class="am-form am-form-horizontal">
            <br>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">职位名称</label>
                <div class="am-u-sm-6">
                    <input type="text" name="pName" value="${position.pName}">
                </div>
            </div>
            <br>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">所属部门</label>
                <div class="am-u-sm-6">
                    <select data-am-selected="{btnSize: 'sm'}" name="did">
                        <option value="0">--选择部门--</option>
                        <c:forEach var="dl" items="${requestScope.departmentList}">
                            <option value="${dl.id}">${dl.dName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <br>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">职位起薪</label>
                <div class="am-u-sm-6">
                    <input type="number" name="basicSalary" value="${position.basicSalary}">
                </div>
            </div>
            <br>
            <div class="am-form-group">
                <div class="am-u-sm-6 am-u-sm-push-6">
                    <a href="javascript:editPosiConfirm(${position.id})" class="am-btn am-btn-success">确认修改</a>
                </div>
            </div>
        </form>

    </div>
</div>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--修改职位确认--%>
    function editPosiConfirm(pid) {
        $.ajax({
            url: 'posi/editPosiConfirm.do?pid=' + pid,
            data: $('#posiEditForm').serialize(),
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("修改成功!");
                    showAllPosi();
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
