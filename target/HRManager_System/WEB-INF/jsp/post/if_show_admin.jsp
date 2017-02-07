<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>条件显示投递信息管理员页面</title>
</head>
<body>
<%--内容开始--%>
<div class="am-u-sm-12">
    <form class="am-form">
        <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
            <tr>
                <th>ID</th>
                <th>投递者</th>
                <th>性别</th>
                <th>工作经验</th>
                <th>投递部门</th>
                <th>投递职位</th>
                <th>阅读标记</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty requestScope.postInfoList}">
                <c:forEach var="pil" items="${requestScope.postInfoList}">
                    <tr>
                        <td>${pil.id}</td>
                        <td>${pil.resume.realName}</td>
                        <td>${pil.resume.sex}</td>
                        <td>${pil.resume.experience}</td>
                        <td>${pil.hireInfo.department.dName}</td>
                        <td>${pil.hireInfo.position.pName}</td>
                        <td>${pil.remark}</td>
                        <td>
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <a href="javascript:checkResumeByAdmin(${pil.id})"
                                       class="am-btn am-btn-primary am-btn-xs">
                                        <span class="am-icon-eye"></span> 查看简历
                                    </a>
                                </div>
                                <c:if test="${pil.remark eq '未读'}">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="javascript:toCreateFaceNotice(${pil.id})"
                                           class="am-btn am-btn-success am-btn-xs">
                                            <span class="am-icon-send-o"></span> 通知面试
                                        </a>
                                    </div>
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="javascript:remarkReaded(${pil.id})"
                                           class="am-btn am-btn-warning am-btn-xs">
                                            <span class="am-icon-check-circle-o"></span> 标记已阅
                                        </a>
                                    </div>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <div class="am-cf">
            共 ${requestScope.postInfoListCount} 条记录
        </div>
        <hr/>
    </form>
</div>
<%--内容 结束--%>

</body>
</html>
