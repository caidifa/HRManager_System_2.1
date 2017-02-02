<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>面试通知用户页面</title>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">面试通知</strong> /
        <small>FaceNotice</small>
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
                    <th>面试官姓名</th>
                    <th>投递信息ID</th>
                    <th>申请部门</th>
                    <th>申请职位</th>
                    <th>面试时间</th>
                    <th>地点</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${not empty sessionScope.faceNoticeList}">
                    <c:forEach var="fn" items="${sessionScope.faceNoticeList}">
                        <tr>
                            <td>${fn.id}</td>
                            <td>${fn.employee.resume.realName}</td>
                            <td>${fn.postInfo.id}</td>
                            <td>${fn.postInfo.hireInfo.department.dName}</td>
                            <td>${fn.postInfo.hireInfo.position.pName}</td>
                            <td>${fn.faceTime}</td>
                            <td>${fn.location}</td>
                            <td>${fn.status}</td>
                            <td>
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="javascript:goFace(${fn.id})" class="am-btn am-btn-success am-btn-xs"><span class="am-icon-facebook"></span> 去面试
                                        </a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <div class="am-cf">
                共 ${sessionScope.faceNoticeListCount} 条记录
            </div>
            <hr/>
        </form>
    </div>
</div>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--去面试--%>
    function goFace(fnid) {
        $.ajax({
            url: 'face/goFace.do?fnid='+fnid,
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    startFace(fnid);
                }else{
                    $("#messageContent").html('现在不是面试时间');
                    $("#messageTips").modal();
                }
            }
        });
    }
    <%--开始面试--%>
    function startFace(fnid) {
        $.ajax({
            url: 'face/startFace.do?fnid='+fnid,
            type: "post",
            success: function (result) {
                $("#mainShow").html(result);
            }
        });
    }
</script>

</body>
</html>
