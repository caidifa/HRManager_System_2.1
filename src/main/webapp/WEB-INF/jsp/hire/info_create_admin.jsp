<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加招聘管理员页面</title>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-primary am-text-lg">发布招聘信息</strong> /
        <small>Post a Job</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-6 am-u-md-pull-12">
        <form id="postHireInfoform" class="am-form am-form-horizontal">
            <%--2级联动 部门 + 职位 --%>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">部门选择</label>
                <div class="am-u-sm-6">
                    <select data-am-selected="{btnSize: 'sm'}" onchange="departToPosition(this);" name="did">
                        <option value="0">--选择部门--</option>
                        <c:forEach var="depart" items="${requestScope.departmentList}">
                            <option value="${depart.id}">${depart.dName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <br>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">职位选择</label>
                <div class="am-u-sm-6">
                    <select data-am-selected="{btnSize: 'sm'}" id="positionSelect" name="pid">
                        <option value="0">--选择职位--</option>
                    </select>
                </div>
            </div>
            <br>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">招聘人数</label>
                <div class="am-u-sm-3">
                    <input type="number" name="needNumber" value="0">
                </div>
                <div class="am-u-sm-3"></div>
            </div>
            <br>
            <div class="am-form-group">
                <div class="am-u-sm-6 am-u-sm-push-6">
                    <a href="javascript:postHireInfo()" class="am-btn am-btn-primary">发布招聘</a>
                </div>
            </div>
        </form>

    </div>
</div>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--部门职位二级联动--%>
    function departToPosition(did) {
        $.ajax({
            url: 'dep/posiByDepId.do?did='+did.value,
            type: "post",
            success: function (result) {
                $('#positionSelect').html(result);
            }
        });
    }
    <%--发布招聘--%>
    function postHireInfo() {
        $.ajax({
            url: 'hire/postHireInfo.do',
            data: $('#postHireInfoform').serialize(),
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("发布成功!");
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
