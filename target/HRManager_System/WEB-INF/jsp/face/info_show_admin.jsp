<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>面试情况页面</title>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">面试情况</strong> /
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
                    <th>面试通知ID</th>
                    <th>面试人姓名</th>
                    <th>申请部门</th>
                    <th>申请职位</th>
                    <th>笔试成绩</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${not empty faceInfoList}">
                    <c:forEach var="fil" items="${faceInfoList}">
                        <tr>
                            <td>${fil.id}</td>
                            <td>${fil.faceNotice.id}</td>
                            <td>${fil.faceNotice.postInfo.resume.realName}</td>
                            <td>${fil.faceNotice.postInfo.hireInfo.department.dName}</td>
                            <td>${fil.faceNotice.postInfo.hireInfo.position.pName}</td>
                            <td>${fil.penScores}</td>
                            <td>${fil.status}</td>
                            <td>
                                <c:if test="${fil.status eq '等通知'}">
                                    <div class="am-btn-toolbar">
                                        <div class="am-btn-group am-btn-group-xs">
                                            <a href="javascript:employ(${fil.faceNotice.postInfo.id})"
                                               class="am-btn am-btn-success am-btn-xs"><span
                                                    class="am-icon-check"></span> 雇 用
                                            </a>
                                        </div>
                                        <div class="am-btn-group am-btn-group-xs">
                                            <a href="javascript:reject(${fil.faceNotice.postInfo.id})"
                                               class="am-btn am-btn-danger am-btn-xs"><span
                                                    class="am-icon-remove"></span> 拒 绝</a>
                                        </div>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <div class="am-cf">
                共 ${faceInfoListCount} 条记录
            </div>
            <hr/>
        </form>
    </div>
</div>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--雇用--%>
    function employ(postid) {
        $.ajax({
            url: 'emp/employ.do?postid=' + postid,
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("雇用成功!");
                }else{
                    $('#messageContent').html("已满!");
                }
                $('#messageTips').modal();
                showFaceInfoByAdmin();
            }
        });
    }
    <%--拒绝--%>
    function reject(postId) {
        $.ajax({
            url: 'emp/reject.do?postid=' + postId,
            type: "post",
            success: function (result) {
                $('#messageContent').html('拒绝成功!'+result);
                $('#messageTips').modal();
                showFaceInfoByAdmin();
            }
        });
    }
</script>

</body>
</html>
