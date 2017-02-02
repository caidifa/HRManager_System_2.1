<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>职位信息管理员页面</title>
    <script type="text/javascript">
        $(function () {
            $('#posiAll').DataTable({
                "ordering": false
            });
        });
    </script>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-primary am-text-lg">职位信息</strong> /
        <small>Position Info</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12">
        <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-xs">
                <a href="javascript:addPosi();" class="am-btn am-btn-success am-btn-xs">
                    <span class="am-icon-plus"></span> 添加新职位
                </a>
            </div>
            <div class="am-btn-group am-btn-group-xs">
                <a href="javascript:removeManyPosi();" class="am-btn am-btn-danger am-btn-xs">
                    <span class="am-icon-trash-o"></span> 批量删除
                </a>
            </div>
        </div>
        <hr>
        <form id="posiCheckedForm">
            <table class="am-table am-table-striped am-table-bordered am-table-compact am-table-hover" id="posiAll">
                <thead>
                <tr onclick="allSelect(this)">
                    <th class="table-check"><input type="checkbox"/></th>
                    <th>ID</th>
                    <th>职位名</th>
                    <th>所属部门</th>
                    <th>起薪</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="pl" items="${requestScope.positionList}">
                    <tr onclick="oneSelect(this)">
                        <td><input type="checkbox" name="ids" value="${pl.id}"/></td>
                        <td>${pl.id}</td>
                        <td>${pl.pName}</td>
                        <td>${pl.department.dName}</td>
                        <td>${pl.basicSalary}元</td>
                        <td>${pl.createTime}</td>
                        <td>
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <a href="javascript:editPosi(${pl.id})"
                                       class="am-btn am-btn-secondary am-btn-xs">
                                        <span class="am-icon-pencil-square-o"></span> 编 辑
                                    </a>
                                </div>
                                <div class="am-btn-group am-btn-group-xs">
                                    <a href="javascript:removePosi(${pl.id})"
                                       class="am-btn am-btn-danger am-btn-xs">
                                        <span class="am-icon-trash-o"></span> 删 除
                                    </a>
                                </div>
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
    <%--添加职位--%>
    function addPosi() {
        $.ajax({
            url: 'posi/addPosi.do',
            type: "post",
            success: function (result) {
                $("#mainShow").html(result);
            }
        });
    }
    <%--批量删除职位--%>
    function removeManyPosi() {
        $.ajax({
            url: 'posi/removeManyPosi.do',
            data: $('#posiCheckedForm').serialize(),
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("批量删除成功!");
                    showAllPosi();
                } else {
                    $('#messageContent').html(result);
                }
                $('#messageTips').modal();
            }
        });
    }
    <%--编辑职位--%>
    function editPosi(pid) {
        $.ajax({
            url: 'posi/editPosi.do?pid=' + pid,
            type: "post",
            success: function (result) {
                $("#mainShow").html(result);
            }
        });
    }
    <%--删除职位--%>
    function removePosi(pid) {
        $.ajax({
            url: 'posi/removePosi.do?pid=' + pid,
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("删除成功!");
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
