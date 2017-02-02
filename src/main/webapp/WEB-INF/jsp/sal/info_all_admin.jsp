<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工考勤页面</title>
    <script src="assets/js/amazeui.min.js"></script>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-secondary am-text-lg">员工薪资</strong> /
        <small>Salary Information</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>

<div class="am-g">

    <div class="am-u-sm-6 am-u-md-3">
        <select data-am-selected="{btnSize: 'sm'}" name="whichYear">
            <option value="0">年份查询</option>
            <option value="2014">2014</option>
            <option value="2015">2015</option>
            <option value="2016">2016</option>
            <option value="2017">2017</option>
        </select>
    </div>
    <div class="am-u-sm-6 am-u-md-3">
        <select data-am-selected="{btnSize: 'sm'}" name="whichMonth">
            <option value="0">月份查询</option>
            <option value="1">1月</option>
            <option value="2">2月</option>
            <option value="3">3月</option>
            <option value="4">4月</option>
            <option value="5">5月</option>
            <option value="6">6月</option>
            <option value="7">7月</option>
            <option value="8">8月</option>
            <option value="9">9月</option>
            <option value="10">10月</option>
            <option value="11">11月</option>
            <option value="12">11月</option>
        </select>
    </div>
    <div class="am-u-sm-12 am-u-md-2">
    </div>
    <div class="am-u-sm-12 am-u-md-4">
        <div class="am-input-group am-input-group-sm">
            <input type="text" class="am-form-field" placeholder="搜索条件...">
            <span class="am-input-group-btn">
            <button class="am-btn am-btn-default" type="button" onclick="void(0)">搜索</button>
          </span>
        </div>
    </div>

</div>

<div class="am-g">
    <div class="am-u-sm-12">

        <form class="am-form">
            <table class="am-table am-table-striped am-table-hover table-main">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>员工名</th>
                    <th>部门名</th>
                    <th>职位名</th>
                    <th>年月份</th>
                    <th>奖励费用</th>
                    <th>惩罚费用</th>
                    <th>社保扣除</th>
                    <th>结算工资</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${not empty requestScope.salaryList}">
                    <c:forEach var="sl" items="${requestScope.salaryList}">
                        <tr>
                            <td>${sl.id}</td>
                            <td>${sl.employee.resume.realName}</td>
                            <td>${sl.employee.department.dName}</td>
                            <td>${sl.employee.position.pName}</td>
                            <td>${sl.yMonth}</td>
                            <td>${sl.bCost}</td>
                            <td>${sl.pCost}</td>
                            <td>${sl.sCost}</td>
                            <td>${sl.total}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </form>
    </div>
</div>

<%--内容 结束--%>

</body>
</html>
