<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>录用通知页面</title>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">录用通知</strong> /
        <small>FaceInfo</small>
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
                    <th>申请部门</th>
                    <th>申请职位</th>
                    <th>笔试成绩</th>
                    <th>结果</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${not empty sessionScope.faceInfoList}">
                    <c:forEach var="fil" items="${sessionScope.faceInfoList}">
                        <tr>
                            <td>${fil.id}</td>
                            <td>${fil.faceNotice.postInfo.hireInfo.department.dName}</td>
                            <td>${fil.faceNotice.postInfo.hireInfo.position.pName}</td>
                            <td>${fil.penScores}</td>
                            <td>${fil.status}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <div class="am-cf">共 ${sessionScope.faceInfoListCount} 条记录</div>
            <hr/>
        </form>
    </div>
</div>
<%--内容 结束--%>

</body>
</html>
