<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>条件显示招聘信息用户页面</title>
</head>
<body>
<%--内容开始--%>
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
            <c:if test="${not empty requestScope.hireInfoList}">
                <c:forEach var="hi" items="${requestScope.hireInfoList}">
                    <tr>
                        <td>${hi.id}</td>
                        <td>${hi.department.dName}</td>
                        <td>${hi.position.pName}</td>
                        <td>${hi.needNumber}</td>
                        <td>${hi.position.basicSalary}</td>
                        <td>${hi.status}</td>
                        <td>
                            <c:if test="${not empty sessionScope.user.resume}">
                                <c:if test="${empty sessionScope.user.resume.status}">
                                    <div class="am-btn-toolbar">
                                        <div class="am-btn-group am-btn-group-xs">
                                            <a href="javascript:postResume(${hi.id})"
                                               class="am-btn am-btn-success am-btn-xs"><span
                                                    class="am-icon-send"></span> 投递
                                            </a>
                                        </div>
                                    </div>
                                </c:if>
                            </c:if>
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
<%--内容 结束--%>

</body>
</html>
