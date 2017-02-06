<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>员工系统主页</title>
    <base href="<%=basePath%>"/>
    <link rel="icon" type="image/png" href="assets/i/cai.png">
    <link rel="stylesheet" href="assets/css/amazeui.min.css">
    <link rel="stylesheet" href="dist/amazeui.datatables.css"/>
    <link rel="stylesheet" href="assets/css/admin.css">
    <link rel="stylesheet" href="assets/css/app.css"/>
    <script src="assets/js/jquery.min.js"></script>
    <script src="dist/amazeui.datatables.js"></script>
    <script src="assets/js/amazeui.min.js"></script>
    <script src="assets/js/app.js"></script>
    <script type="text/javascript">
        // 查看自己的培训通知
        function showTrainNotice(ticount) {
            if(ticount==0) {
                return;
            }
            $.ajax({
                url: "train/showTrainNotice.do",
                success: function (result) {
                    $("#contentShow").html(result);
                    $("#contentTips").modal();
                }
            });
        }
        // 查看自己的培训信息
        function showMyTrainInfo(eid) {
            $.ajax({
                url: "train/showMyTrainInfo.do?eid="+eid,
                success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }
        // 查看自己的奖惩信息
        function showMyBonus(eid) {
            $.ajax({
                url: "check/showMyBonus.do?eid="+eid,
                success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }
        // 查看自己的考勤信息
        function showMyChecking(eid) {
            $.ajax({
                url: "check/showMyChecking.do?eid="+eid,
                success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }
        // 打卡操作
        function startChecking() {
            $.ajax({
                url: "check/startChecking.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }
        // 查看部门职位员工信息
        function showDPEmp() {
            $.ajax({
                url: "emp/showDPEmp.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }
        // 查看个人薪资信息
        function showMySalary(eid) {
            $.ajax({
                url: "sal/showMySalary.do?eid="+eid, success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }
        // 员工修改密码
        function editPassword(eid) {
            $.ajax({
                url: "emp/editPassword.do?eid=${sessionScope.employee.id}",
                success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }
        // 员工注销
        function logout() {
            $('#confirmContent').html('你确定要注销吗?');
            $('#confirmTips').modal({
                onConfirm: function () {
                    window.location.replace("emp/logout.do");
                }
            });
        }
    </script>
</head>
<body>

<%--导航栏: 网站名+投递通知+下拉工具+开启全屏--%>
<header class="am-topbar am-topbar-inverse admin-header" style="background-color: rebeccapurple">
    <div class="am-topbar-brand">
        <strong>浮云梦影-员工个人系统</strong>
        <small>Employee System</small>
    </div>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <li>
                <a href="javascript:showTrainNotice(${sessionScope.trainingInfoListCount})">
                    <span class="am-icon-envelope-o"></span> 培训通知
                    <span class="am-badge am-badge-warning">${sessionScope.trainingInfoListCount}</span>
                </a>
            </li>
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:void(0);">
                    <span class="am-icon-user"></span> 个人基本信息
                    <span class="am-icon-caret-down"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li><a href="javascript:editPassword();">
                        <span class="am-icon-pencil am-icon-fw"></span> 修改密码</a>
                    </li>
                    <li><a href="javascript:logout()"><span class="am-icon-sign-out am-icon-fw"></span> 注 销</a></li>
                </ul>
            </li>
            <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span
                    class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
        </ul>
    </div>
</header>

<%--导航栏下内容--%>
<div class="am-cf admin-main">

    <%--左侧工具栏+信息栏--%>
    <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">

        <div class="am-offcanvas-bar admin-offcanvas-bar">
            <ul class="am-list admin-sidebar-list">

                <li><a href="main/goEmpMain.do"><span class="am-icon-home am-icon-fw"></span> 首 页</a></li>

                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}">
                        <span class="am-icon-user am-icon-fw"></span> 个 人 相 关
                        <span class="am-icon-angle-right am-fr am-margin-right"></span>
                    </a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-open" id="collapse-nav">
                        <li>
                            <a href="javascript:showMyTrainInfo(${sessionScope.employee.id})" class="am-cf">
                                <span class="am-icon-empire am-icon-fw"></span> 个 人 培 训
                                <span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:showMySalary(${sessionScope.employee.id})" class="am-cf">
                                <span class="am-icon-money am-icon-fw"></span> 个 人 薪 资
                                <span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:showMyBonus(${sessionScope.employee.id})" class="am-cf">
                                <span class="am-icon-gift am-icon-fw"></span> 个 人 奖 惩
                                <span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:showMyChecking(${sessionScope.employee.id})" class="am-cf">
                                <span class="am-icon-check am-icon-fw"></span> 个 人 考 勤
                                <span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:showDPEmp()">
                        <span class="am-icon-th-list am-icon-fw"></span> 查 看 员 工
                    </a>
                </li>
                <li>
                    <a href="javascript:startChecking()">
                        <span class="am-icon-credit-card am-icon-fw"></span> 打 卡 操 作
                    </a>
                </li>
                <li><a href="javascript:logout()"><span class="am-icon-sign-out am-icon-fw"></span> 注 销</a></li>
            </ul>

            <div class="am-panel am-panel-default admin-sidebar-panel">
                <div class="am-panel-bd">
                    <p><span class="am-icon-bookmark"></span> 登陆信息</p>
                    <p>
                        员工登陆成功!<br>
                        编号:${sessionScope.employee.empNumber}<br>
                        真实姓名:${sessionScope.employee.resume.realName}<br>
                        所属部门:${sessionScope.employee.department.dName}<br>
                        在职职位:${sessionScope.employee.position.pName}<br>
                        工资卡余额:${sessionScope.employee.balance}<br>
                    </p>
                </div>
            </div>

        </div>

    </div>

    <%--主要显示区--%>
    <div class="admin-content">

        <div class="admin-content-body" id="mainShow">
            欢迎登陆来到浮云梦影-员工个人系统
        </div>

        <footer class="admin-content-footer">
            <hr>
        </footer>

    </div>

</div>

<%--模态提示框--%>
<div id="cai-tips">

    <!--消息提示框-->
    <div class="am-modal am-modal-alert" tabindex="-1" id="messageTips">
        <div class="am-modal-dialog" style="width: 20%">
            <div class="am-modal-bd" id="messageContent">
                消息提示框
            </div>
            <div class="am-modal-footer">
                <span class="am-modal-btn">关闭</span>
            </div>
        </div>
    </div>

    <!--内容显示框-->
    <div class="am-modal am-modal-alert" tabindex="-1" id="contentTips">
        <div class="am-modal-dialog" style="width: 70%">
            <div class="am-modal-bd" id="contentShow">
                内容显示框
            </div>
            <div class="am-modal-footer">
                <span class="am-modal-btn">关闭</span>
            </div>
        </div>
    </div>

    <!--确认提示框-->
    <div class="am-modal am-modal-confirm" tabindex="-1" id="confirmTips">
        <div class="am-modal-dialog" style="width: 20%">
            <div class="am-modal-bd" id="confirmContent">
                你确定要注销吗?
            </div>
            <div class="am-modal-footer">
                <span class="am-modal-btn" data-am-modal-confirm>确认</span>
                <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            </div>
        </div>
    </div>

</div>

</body>
</html>
