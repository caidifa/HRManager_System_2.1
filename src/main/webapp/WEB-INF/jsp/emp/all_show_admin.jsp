<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>所有员工信息管理员页面</title>
    <script type="text/javascript">
        $(function () {
            $('#empAll').DataTable({
                "ordering": false
            });
        });
    </script>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-primary am-text-lg">员工信息</strong> /
        <small>Employee Info</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>

<div class="am-g">
    <div class="am-u-sm-12">
        <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-xs">
                <a href="javascript:showFaceInfoByAdmin()" class="am-btn am-btn-success am-btn-xs">
                    <span class="am-icon-plus"></span> 添加新员工(面试处理)
                </a>
            </div>
            <div class="am-btn-group am-btn-group-xs">
                <a href="javascript:removeManyEmp();" class="am-btn am-btn-danger am-btn-xs">
                    <span class="am-icon-trash-o"></span> 批量开除
                </a>
            </div>
        </div>
        <hr>
        <form id="empCheckedForm">
            <table class="am-table am-table-striped am-table-bordered am-table-compact am-table-hover" id="empAll">
                <thead>
                <tr onclick="allSelect(this)">
                    <th class="table-check"><input type="checkbox"/></th>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>部门</th>
                    <th>职位</th>
                    <th>编号</th>
                    <th>密码</th>
                    <th>薪水</th>
                    <th>等级</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="el" items="${requestScope.employeeList}">
                    <tr onclick="oneSelect(this)">
                        <td><input type="checkbox" name="eids" value="${el.id}"/></td>
                        <td>${el.id}</td>
                        <td>${el.resume.realName}</td>
                        <td>${el.department.dName}</td>
                        <td>${el.position.pName}</td>
                        <td>${el.empNumber}</td>
                        <td>${el.password}</td>
                        <td>${el.salary}元</td>
                        <c:if test="${el.level == 0}">
                            <td>非管理员</td>
                        </c:if>
                        <c:if test="${el.level == 1}">
                            <td>管理员</td>
                        </c:if>
                        <td>${el.status}</td>
                        <td>
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <a href="javascript:editEmp(${el.id})"
                                       class="am-btn am-btn-secondary am-btn-xs">
                                        <span class="am-icon-pencil-square-o"></span> 编 辑
                                    </a>
                                </div>
                                <c:if test="${el.status eq '在职'}">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <a href="javascript:dismissEmp(${el.id})"
                                           class="am-btn am-btn-danger am-btn-xs">
                                            <span class="am-icon-trash-o"></span> 开 除
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
    <%--批量开除员工--%>
    function removeManyPosi() {
        $.ajax({
            url: 'posi/removeManyPosi.do',
            data: $('#empCheckedForm').serialize(),
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("批量开除成功!");
                    showAllEmp();
                } else {
                    $('#messageContent').html(result);
                }
                $('#messageTips').modal();
            }
        });
    }
    <%--编辑员工--%>
    function editEmp(eid) {
        $.ajax({
            url: 'emp/editEmp.do?eid=' + eid,
            type: "post",
            success: function (result) {
                $("#mainShow").html(result);
            }
        });
    }
    <%--开除员工--%>
    function dismissEmp(eid) {
        $.ajax({
            url: 'emp/dismissEmp.do?eid=' + eid,
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("开除成功!");
                    showAllEmp();
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
