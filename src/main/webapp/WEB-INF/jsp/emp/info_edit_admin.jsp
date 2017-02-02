<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改员工_管理员页面</title>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-secondary am-text-lg">修改员工</strong> /
        <small>Edit Employee</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-6 am-u-md-pull-12">
        <form id="empEditForm" class="am-form am-form-horizontal">
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">员工ID</label>
                <div class="am-u-sm-6">
                    <input type="text" value="${employee.id}" readonly>
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">所属部门</label>
                <div class="am-u-sm-6">
                    <select data-am-selected="{btnSize: 'sm'}" onchange="departToPosition(this);" name="did">
                        <option value="${employee.department.id}">${employee.department.dName}</option>
                        <c:forEach var="depart" items="${requestScope.departmentList}">
                            <option value="${depart.id}">${depart.dName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">在职职位</label>
                <div class="am-u-sm-6">
                    <select data-am-selected="{btnSize: 'sm'}" id="positionSelect" name="pid">
                        <option value="${employee.position.id}">${employee.position.pName}</option>
                    </select>
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">员工编号</label>
                <div class="am-u-sm-6">
                    <input type="text" value="${employee.empNumber}" readonly>
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">员工登陆密码</label>
                <div class="am-u-sm-6">
                    <input type="text" value="${employee.password}" name="password">
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">员工薪水</label>
                <div class="am-u-sm-6">
                    <input type="number" value="${employee.salary}" name="salary"></td>
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">员工等级</label>
                <div class="am-u-sm-6">
                    <c:if test="${employee.level == 0}">
                        <select data-am-selected="{btnSize: 'sm'}" name="level">
                            <option value="0">非管理员</option>
                            <option value="1">管理员</option>
                        </select>
                    </c:if>
                    <c:if test="${employee.level == 1}">
                        <select data-am-selected="{btnSize: 'sm'}" name="level">
                            <option value="1">管理员</option>
                            <option value="0">非管理员</option>
                        </select>
                    </c:if>
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-6 am-form-label">员工状态</label>
                <div class="am-u-sm-6">
                    <input type="text" value="${employee.status}" readonly>
                </div>
            </div>
            <div class="am-form-group">
                <div class="am-u-sm-6 am-u-sm-push-6">
                    <a href="javascript:editEmpConfirm(${employee.id})" class="am-btn am-btn-success">确认修改</a>
                </div>
            </div>
        </form>
    </div>
</div>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--部门职位二级联动--%>
    function departToPosition(did) {
        $.ajax({
            url: '/dep/posiByDepId.do?did='+did.value,
            type: "post",
            success: function (result) {
                $('#positionSelect').html(result);
            }
        });
    }
    <%--修改员工确认--%>
    function editEmpConfirm(eid) {
        $.ajax({
            url: 'emp/editEmpConfirm.do?eid='+eid,
            data: $('#empEditForm').serialize(),
            type: "post",
            success: function (result) {
                if (result=='ok') {
                    $('#messageContent').html("修改成功!");
                    showAllEmp();
                }else {
                    $('#messageContent').html(result);
                }
                $('#messageTips').modal();
            }
        });
    }
</script>

</body>
</html>
