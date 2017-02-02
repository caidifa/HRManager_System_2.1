<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>培训信息管理员页面</title>
    <script type="text/javascript">
        $(function () {
            $('#trainAll').DataTable({
                "ordering": false
            });
        });
    </script>
</head>
<body>
<%--内容 开始--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-primary am-text-lg">培训信息</strong> /
        <small>Training Info</small>
    </div>
</div>
<hr/>

<div class="am-g">
    <div class="am-u-sm-12">
        <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-xs">
                <a href="javascript:addTrain();" class="am-btn am-btn-success am-btn-xs">
                    <span class="am-icon-plus"></span> 创建新培训
                </a>
            </div>
            <div class="am-btn-group am-btn-group-xs">
                <a href="javascript:postManyTrain();" class="am-btn am-btn-primary am-btn-xs">
                    <span class="am-icon-arrow-up"></span> 批量发布培训
                </a>
            </div>
            <div class="am-btn-group am-btn-group-xs">
                <a href="javascript:cancelManyTrain();" class="am-btn am-btn-danger am-btn-xs">
                    <span class="am-icon-close"></span> 批量取消培训
                </a>
            </div>
        </div>
        <hr>
        <form id="trainCheckedForm">
            <table class="am-table am-table-striped am-table-bordered am-table-compact am-table-hover" id="trainAll">
                <thead>
                <tr onclick="allSelect(this)">
                    <th class="table-check"><input type="checkbox"/></th>
                    <th>ID</th>
                    <th>老师</th>
                    <th>内容</th>
                    <th>起始日</th>
                    <th>结束日</th>
                    <th>地点</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="til" items="${requestScope.trainingInfoList}">
                    <tr onclick="oneSelect(this)">
                        <td><input type="checkbox" name="tiids" value="${til.id}"/></td>
                        <td>${til.id}</td>
                        <td>${til.teacher.resume.realName}&emsp;</td>
                        <td>${til.content}</td>
                        <td>${til.startDate}</td>
                        <td>${til.endDate}</td>
                        <td>${til.place}</td>
                        <td>${til.status}</td>
                        <td>
                            <div class="am-btn-toolbar">
                                <c:if test="${til.status ne '已取消'}">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="javascript:showTrainEmp(${til.id})"
                                           class="am-btn am-btn-warning am-btn-xs">
                                            <span class="am-icon-eye"></span> 已加员工
                                        </a>
                                    </div>
                                </c:if>
                                <c:if test="${til.status eq '未发布'}">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="javascript:addTrainEmp(${til.id})"
                                           class="am-btn am-btn-success am-btn-xs">
                                            <span class="am-icon-plus"></span> 添加员工
                                        </a>
                                    </div>
                                    <%--<div class="am-btn-group am-btn-group-xs">--%>
                                        <%--<a href="javascript:delTrainEmp(${til.id})"--%>
                                           <%--class="am-btn am-btn-warning am-btn-xs">--%>
                                            <%--<span class="am-icon-minus"></span> 员工--%>
                                        <%--</a>--%>
                                    <%--</div>--%>
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="javascript:postTrain(${til.id})"
                                           class="am-btn am-btn-primary am-btn-xs">
                                            <span class="am-icon-arrow-up"></span> 发布
                                        </a>
                                    </div>
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="javascript:toEditTrain(${til.id})"
                                           class="am-btn am-btn-secondary am-btn-xs">
                                            <span class="am-icon-pencil-square-o"></span> 编辑
                                        </a>
                                    </div>
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="javascript:cancelTrain(${til.id})"
                                           class="am-btn am-btn-danger am-btn-xs">
                                            <span class="am-icon-close"></span> 取消
                                        </a>
                                    </div>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
</div>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--添加培训--%>
    function addTrain() {
        $.ajax({
            url: 'train/addTrain.do',
            type: "post",
            success: function (result) {
                $("#mainShow").html(result);
            }
        });
    }
    <%--批量发布培训信息--%>
    function postManyTrain() {
        $.ajax({
            url: 'train/postManyTrain.do',
            data: $('#trainCheckedForm').serialize(),
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("批量发布成功!");
                    showAllTrain();
                } else {
                    $('#messageContent').html(result);
                }
                $('#messageTips').modal();
            }
        });
    }
    <%--批量取消培训--%>
    function cancelManyTrain() {
        $.ajax({
            url: 'train/cancelManyTrain.do',
            data: $('#trainCheckedForm').serialize(),
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("批量取消成功!");
                    showAllTrain();
                } else {
                    $('#messageContent').html(result);
                }
                $('#messageTips').modal();
            }
        });
    }
    <%--查看培训的员工--%>
    function showTrainEmp(tiid) {
        $.ajax({
            url: 'train/showTrainEmp.do?tiid=' + tiid,
            type: "post",
            success: function (result) {
                $('#contentShow').html(result);
                $('#contentTips').modal();
            }
        });
    }
    <%--添加培训员工--%>
    function addTrainEmp(tiid) {
        $.ajax({
            url: 'train/addTrainEmp.do?tiid=' + tiid,
            type: "post",
            success: function (result) {
                $("#mainShow").html(result);
            }
        });
    }
    <%--&lt;%&ndash;减少培训员工&ndash;%&gt;--%>
    <%--function delTrainEmp(tiid) {--%>
        <%--$.ajax({--%>
            <%--url: 'train/delTrainEmp.do?tiid=' + tiid,--%>
            <%--type: "post",--%>
            <%--success: function (result) {--%>
                <%--$("#mainShow").html(result);--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>
    <%--发布培训--%>
    function postTrain(tiid) {
        $.ajax({
            url: 'train/postTrain.do?tiid=' + tiid,
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("发布成功!");
                    showAllTrain();
                } else {
                    $('#messageContent').html(result);
                }
                $('#messageTips').modal();
            }
        });
    }
    <%--编辑培训--%>
    function toEditTrain(tiid) {
        $.ajax({
            url: 'train/toEditTrain.do?tiid=' + tiid,
            type: "post",
            success: function (result) {
                $("#mainShow").html(result);
            }
        });
    }
    <%--取消培训--%>
    function cancelTrain(tiid) {
        $.ajax({
            url: 'train/cancelTrain.do?tiid=' + tiid,
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("取消成功!");
                    showAllTrain();
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
