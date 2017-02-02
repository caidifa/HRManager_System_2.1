<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>招聘信息用户页面</title>
    <script src="assets/js/amazeui.min.js"></script>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">招聘信息</strong> /
        <small>HireInfo</small>
    </div>
</div>
<hr>

<%--内容 开始--%>
<div class="am-g">

    <%--2级查询菜单(查询用)--%>
    <div class="am-u-sm-12 am-u-md-4">
        <div class="am-form-group">
            <select data-am-selected="{btnSize: 'sm'}" onchange="showHireInfoByDepart(this);" title="部门选择框">
                <option value="0">--所有部门--</option>
                <c:forEach var="depart" items="${requestScope.departmentList}">
                    <option value="${depart.id}">${depart.dName}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <%--搜索框(通过职位名搜索)--%>
    <div class="am-u-sm-12 am-u-md-4">
        <div class="am-input-group am-input-group-sm">
            <input type="text" class="am-form-field" title="搜索框" placeholder="请输入职位名" id="posiFromLike"/>
            <span class="am-input-group-btn">
            <a href="javascript:showHireInfoByPName()" class="am-btn am-btn-default" type="button">搜索</a>
          </span>
        </div>
    </div>
    <div class="am-u-sm-12 am-u-md-4"></div>
</div>

<div class="am-g" id="hireInfoContent">
    <div class="am-u-sm-12">
        <form class="am-form">
            <table class="am-table am-table-striped am-table-hover table-main">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>部门</th>
                    <th>职位</th>
                    <th>招聘人数</th>
                    <th>起薪</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${not empty sessionScope.hireInfoList}">
                    <c:forEach var="hi" items="${sessionScope.hireInfoList}">
                        <tr>
                            <td>${hi.id}</td>
                            <td>${hi.department.dName}</td>
                            <td>${hi.position.pName}</td>
                            <td>${hi.needNumber}</td>
                            <td>${hi.position.basicSalary}</td>
                            <td>${hi.status}</td>
                            <td>
                                <c:if test="${not empty sessionScope.user.resume}">
                                    <c:if test="${empty sessionScope.user.resume.status}">
                                        <div class="am-btn-toolbar">
                                            <div class="am-btn-group am-btn-group-xs">
                                                <a href="javascript:postResume(${hi.id})"
                                                   class="am-btn am-btn-success am-btn-xs"><span
                                                        class="am-icon-send"></span> 投递
                                                </a>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <div class="am-cf">
                共 ${sessionScope.hireInfoListCount} 条记录
            </div>
            <hr/>
        </form>
    </div>
</div>
<%--内容 结束--%>

<%--js代码 ajax--%>
<script type="text/javascript">
    <%--根据所选部门显示招聘信息--%>
    function showHireInfoByDepart(did) {
        $.ajax({
            url: 'hire/showHireInfoByDepart.do?did=' + did.value,
            type: "post",
            success: function (result) {
                $('#hireInfoContent').html(result);
            }
        });
    }
    <%--根据职位名显示招聘信息--%>
    function showHireInfoByPName() {
        $.ajax({
            url: 'hire/showHireInfoByPName.do?pName=' + $('#posiFromLike').val(),
            type: "post",
            success: function (result) {
                $('#hireInfoContent').html(result);
            }
        });
    }
    <%--投递简历--%>
    function postResume(hid) {
        $.ajax({
            url: 'post/postResume.do?hid=' + hid + '&rid=${sessionScope.user.resume.id}',
            type: "post",
            success: function (result) {
                if (result == 'ok') {
                    $('#messageContent').html("投递成功!请等待通知!");
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
