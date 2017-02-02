<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>部门信息管理员页面</title>
    <script type="text/javascript">
        $(function () {
            $('#departAll').DataTable({
                "ordering": false
            });
        });
    </script>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-primary am-text-lg">部门信息</strong> /
        <small>Department Info</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12">
        <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-xs">
                <a href="javascript:addDepart();" class="am-btn am-btn-success am-btn-xs">
                    <span class="am-icon-plus"></span> 添加新部门
                </a>
            </div>
            <div class="am-btn-group am-btn-group-xs">
                <a href="javascript:removeManyDepart();" class="am-btn am-btn-danger am-btn-xs">
                    <span class="am-icon-trash-o"></span> 批量删除
                </a>
            </div>
        </div>
        <hr>
        <form id="departCheckedForm">
            <table class="am-table am-table-striped am-table-bordered am-table-compact am-table-hover" id="departAll">
                <thead>
                <tr onclick="allSelect(this)">
                    <th class="table-check"><input type="checkbox"/></th>
                    <th>ID</th>
                    <th>部门名</th>
                    <th>创建时间</th>
                    <th>职位数量</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="dl" items="${requestScope.departmentList}">
                    <tr onclick="oneSelect(this)">
                        <td><input type="checkbox" name="ids" value="${dl.id}"/></td>
                        <td>${dl.id}</td>
                        <td>${dl.dName}</td>
                        <td>${dl.createTime}</td>
                        <td>${fn:length(dl.positionList)}</td>
                        <td>
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <a href="javascript:editDepart(${dl.id})"
                                       class="am-btn am-btn-secondary am-btn-xs">
                                        <span class="am-icon-pencil-square-o"></span> 编 辑
                                    </a>
                                </div>
                                <div class="am-btn-group am-btn-group-xs">
                                    <a href="javascript:removeDepart(${dl.id})"
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
<hr>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--添加部门--%>
    function addDepart() {
        $.ajax({
            url: 'dep/addDepart.do',
            type: "post",
            success: function (result) {
                $("#mainShow").html(result);
            }
        });
    }
    <%--批量删除部门--%>
    function removeManyDepart() {
        $.ajax({
            url: 'dep/removeManyDepart.do',
            data: $('#departCheckedForm').serialize(),
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("批量删除成功!");
                    showAllDepart();
                } else {
                    $('#messageContent').html(result);
                }
                $('#messageTips').modal();
            }
        });
    }
    <%--编辑部门--%>
    function editDepart(did) {
        $.ajax({
            url: 'dep/editDepart.do?did=' + did,
            type: "post",
            success: function (result) {
                $("#mainShow").html(result);
            }
        });
    }
    <%--删除部门--%>
    function removeDepart(did) {
        $.ajax({
            url: 'dep/removeDepart.do?did=' + did,
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("删除成功!");
                    showAllDepart();
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
