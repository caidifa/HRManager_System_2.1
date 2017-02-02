<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>投递信息管理员页面</title>
    <script src="assets/js/amazeui.min.js"></script>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">投递信息</strong> /
        <small>PostInfo</small>
    </div>
</div>
<hr>

<%--内容 开始--%>
<div class="am-g">

    <%--查询条件选择--%>
    <div class="am-u-sm-12 am-u-md-4">
        <div class="am-form-group">
            <select data-am-selected="{btnSize: 'sm'}" onchange="showPostInfoByRemark(this);" title="阅读标记选择框">
                <option value="all">所有投递</option>
                <option value="unread">未读</option>
                <option value="readed">已阅</option>
            </select>
        </div>
    </div>

</div>

<div class="am-g" id="postInfoContent">
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
</div>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--根据所选标记条件显示投递信息--%>
    function showPostInfoByRemark(remark) {
        $.ajax({
            url: 'post/showPostInfoByRemark.do?remark=' + remark.value,
            type: "post",
            success: function (result) {
                $('#postInfoContent').html(result);
            }
        });
    }

    <%--管理员查看投递者的简历--%>
    function checkResumeByAdmin(piid) {
        $.ajax({
            url: 'post/checkResumeByAdmin.do?piid=' + piid,
            type: "post",
            success: function (result) {
                $('#contentShow').html(result);
                $('#contentTips').modal();
            }
        });
    }

    <%--根据投递信息新建面试--%>
    function toCreateFaceNotice(piid) {
        $.ajax({
            url: 'face/toCreateFaceNotice.do?piid=' + piid,
            type: "post",
            success: function (result) {
                $('#mainShow').html(result);
            }
        });
    }

    <%--标记为已阅--%>
    function remarkReaded(piid) {
        $.ajax({
            url: 'post/remarkReaded.do?piid=' + piid,
            type: "post",
            success: function (result) {
                $('#messageContent').html("标记成功!");
                $('#messageTips').modal();
                showPostInfoByAdmin();
            }
        });
    }

</script>

</body>
</html>
