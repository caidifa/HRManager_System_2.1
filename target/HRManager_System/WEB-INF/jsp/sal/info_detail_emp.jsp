<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工薪资详情</title>
    <script src="assets/js/amazeui.min.js"></script>
</head>
<body>

<%--左上角显示--%>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf">
        <strong class="am-text-secondary am-text-lg">我的薪资详情(${salary.yMonth})</strong> /
        <small>上班天数为(${goDays}/22)</small>
    </div>
</div>
<hr/>

<%--内容 开始--%>
<div class="am-g">
    <div class="am-u-sm-12">

        <form class="am-form">
            <table class="am-table am-table-striped am-table-hover table-main">
                <thead>
                <tr>
                    <th>基本月薪</th>
                    <th colspan="2">${salary.employee.salary}元</th>
                </tr>
                <tr>
                    <th>扣除未上班天数的总日薪</th>
                    <th colspan="2">${daySalary}元(日薪)X${notGoDays}天=${allNotGoSalary}元</th>
                </tr>
                <tr>
                    <th>奖励详情</th>
                    <th></th>
                    <th></th>
                </tr>
                <tr>
                    <th>日期</th>
                    <th>原因</th>
                    <th>奖金</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="b" items="${bonusList}">
                    <tr>
                        <td>${b.time}</td>
                        <td>${b.reason}</td>
                        <td>${b.money}元</td>
                    </tr>
                </c:forEach>
                </tbody>
                <thead>
                <tr>
                    <th>惩罚详情</th>
                    <th></th>
                    <th></th>
                </tr>
                <tr>
                    <th>日期</th>
                    <th>原因</th>
                    <th>罚金</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="p" items="${penaltyList}">
                    <tr>
                        <td>${p.time}</td>
                        <td>${p.reason}</td>
                        <td>${p.money}元</td>
                    </tr>
                </c:forEach>
                </tbody>
                <thead>
                <tr>
                    <th>社保扣除(20%)</th>
                    <th colspan="2">${salary.employee.salary / 5}元</th>
                </tr>
                <tr>
                    <th>所得薪资为:</th>
                    <th colspan="2">
                        ${salary.total}元
                    </th>
                </tr>
                </thead>
            </table>

            <hr/>
        </form>
    </div>
</div>
<%--内容 结束--%>

</body>
</html>
