<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>招聘信息管理员页面</title>
    <script src="assets/js/amazeui.min.js"></script>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">招聘信息</strong> /
        <small>HireInfo</small>
    </div>
</div>
<hr>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12">
        <form class="am-form">
            <table class="am-table am-table-striped am-table-hover table-main">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>部门</th>
                    <th>职位</th>
                    <th>招聘人数</th>
                    <th>起薪</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${not empty hireInfoList}">
                    <c:forEach var="hi" items="${hireInfoList}">
                        <tr>
                            <td>${hi.id}</td>
                            <td>${hi.department.dName}</td>
                            <td>${hi.position.pName}</td>
                            <td>${hi.needNumber}</td>
                            <td>${hi.position.basicSalary}</td>
                            <td>${hi.status}</td>
                            <td>
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <c:if test="${hi.status eq '招聘中'}">
                                            <a href="javascript:stopHireInfo(${hi.id})" class="am-btn am-btn-danger am-btn-xs">
                                                <span class="am-icon-arrow-down"></span> 停止发布
                                            </a>
                                        </c:if>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <div class="am-cf">
                共 ${requestScope.hireInfoListCount} 条记录
            </div>
            <hr/>
        </form>
    </div>
</div>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--停止发布--%>
    function stopHireInfo(hid) {
        $.ajax({
            url: 'hire/stopHireInfo.do?hid='+ hid,
            type: "post",
            success: function (result) {
                $('#messageContent').html(result);
                $('#messageTips').modal();
                showHIByAdmin();
            }
        });
    }
</script>

</body>
</html>
