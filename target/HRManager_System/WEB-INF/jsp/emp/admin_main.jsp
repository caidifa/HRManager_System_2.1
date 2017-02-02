<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>管理员系统主页</title>
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
        <%--全选--%>
        function allSelect(all) {
            var checkbox  = $(all).find("input[type='checkbox']");
            var checkboxs  =$("tr:gt(0)").find("input[type='checkbox']");
            if (checkbox.is(":checked")) {
                checkboxs.prop("checked",true);
            } else {
                checkboxs.prop("checked",false);
            }
        }
        <%--行内单选--%>
        function oneSelect(one) {
            var checkbox  = $(one).find("input[type='checkbox']");
            if (checkbox.is(":checked")) {
                checkbox.prop("checked",false);
            } else {
                checkbox.prop("checked",true);
            }
        }
        // 管理员查看招聘信息
        function showHIByAdmin() {
            $.ajax({
                url: "hire/showHIByAdmin.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }
        // 新建并发布招聘
        function createHireInfo() {
            $.ajax({
                url: "hire/createHireInfo.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }
        // 显示所有投递信息
        function showPostInfoByAdmin() {
            $.ajax({
                url: "post/showPostInfoByAdmin.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }
        // 显示要去面试投递者的通知
        function showFaceNoticeAboutAdmin() {
            $.ajax({
                url: "face/showFaceNoticeAboutAdmin.do", success: function (result) {
                    $("#contentShow").html(result);
                    $("#contentTips").modal();
                }
            });
        }
        // 显示面试情况
        function showFaceInfoByAdmin() {
            $.ajax({
                url: "face/showFaceInfoByAdmin.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }
        // 显示所有部门
        function showAllDepart() {
            $.ajax({
                url: "dep/showAllDep.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }
        // 显示所有职位
        function showAllPosi() {
            $.ajax({
                url: "posi/showAllPosi.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }
        // 显示所有培训信息
        function showAllTrain() {
            $.ajax({
                url: "train/showAllTrain.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }
        // 显示所有员工信息
        function showAllEmp() {
            $.ajax({
                url: "emp/showAllEmp.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }

        // 显示所有员工的薪资信息
        function showAllSal() {
            $.ajax({
                url: "sal/showAllSal.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }
        // 显示所有员工的奖惩信息
        function showAllBonus() {
            $.ajax({
                url: "check/showAllBonus.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }
        // 显示所有员工的考勤信息
        function showAllChecking() {
            $.ajax({
                url: "check/showAllChecking.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }

        // 显示薪资结算操作页面
        function toSalaryCount() {
            $.ajax({
                url: "sal/toSalaryCount.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }

        // 管理员注销
        function logout() {
            $('#confirmTips').modal({
                onConfirm: function () {
                    window.location.replace("emp/logout.do");
                }
            });
        }
    </script>
</head>
<body>

<%--导航栏: 网站名+面试通知+用户工具+开启全屏--%>
<header class="am-topbar am-topbar-inverse admin-header">
    <div class="am-topbar-brand">
        <strong>浮云梦影-管理员系统</strong>
        <small>Admin System</small>
    </div>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <li>
                <a href="javascript:showPostInfoByAdmin();"><span class="am-icon-envelope-o"></span> 投递处理
                    <span class="am-badge am-badge-warning">${sessionScope.postInfoUnReadCount}</span>
                </a>
            </li>
            <li>
                <a href="javascript:showFaceNoticeAboutAdmin();"><span class="am-icon-envelope-o"></span> 面试投递者
                    <span class="am-badge am-badge-success">${sessionScope.toFaceUserCount}</span>
                </a>
            </li>
            <li>
                <a href="javascript:showFaceInfoByAdmin();"><span class="am-icon-envelope-o"></span> 面试处理
                    <span class="am-badge am-badge-danger">${sessionScope.faceInfoListCount}</span>
                </a>
            </li>
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:void(0);">
                    <span class="am-icon-user"></span> 管理员个人信息
                    <span class="am-icon-caret-down"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li><a href="javascript:showAdminInfo()"><span class="am-icon-eye"></span> 查 看</a></li>
                    <li><a href="javascript:editAdminInfo()"><span class="am-icon-pencil"></span> 编 辑</a></li>
                    <li><a href="javascript:logout()"><span class="am-icon-sign-out"></span> 注 销</a></li>
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

                <li><a href="main/goAdminMain.do"><span class="am-icon-home am-icon-fw"></span> 首 页</a></li>

                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}">
                        <span class="am-icon-outdent am-icon-fw"></span> 外部信息
                    </a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-open" id="collapse-nav">
                        <li>
                            <a href="javascript:createHireInfo()" class="am-cf">
                                <span class="am-icon-plus am-icon-fw"></span> 发布招聘
                            </a>
                        </li>
                        <li>
                            <a href="javascript:showHIByAdmin()" class="am-cf">
                                <span class="am-icon-list-alt am-icon-fw"></span> 招聘管理
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav1'}">
                        <span class="am-icon-indent am-icon-fw"></span> 内部信息
                    </a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav1">
                        <li>
                            <a href="javascript:showAllEmp()">
                                <span class="am-icon-users am-icon-fw"></span> 所有员工
                            </a>
                        </li>
                        <li>
                            <a href="javascript:showAllSal()">
                                <span class="am-icon-money am-icon-fw"></span> 所有薪资
                            </a>
                        </li>
                        <li>
                            <a href="javascript:showAllBonus()">
                                <span class="am-icon-gift am-icon-fw"></span> 所有奖惩
                            </a>
                        </li>
                        <li>
                            <a href="javascript:showAllChecking()">
                                <span class="am-icon-calendar-check-o am-icon-fw"></span> 所有考勤
                            </a>
                        </li>
                        <li>
                            <a href="javascript:showAllTrain()">
                                <span class="am-icon-tasks am-icon-fw"></span> 所有培训
                            </a>
                        </li>
                        <li>
                            <a href="javascript:showAllDepart();">
                                <span class="am-icon-delicious am-icon-fw"></span> 所有部门
                            </a>
                        </li>
                        <li>
                            <a href="javascript:showAllPosi();">
                                <span class="am-icon-get-pocket am-icon-fw"></span> 所有职位
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:toSalaryCount();">
                        <span class="am-icon-calculator am-icon-fw"></span> 薪资 结算
                    </a>
                </li>
                <li><a href="javascript:logout()"><span class="am-icon-sign-out am-icon-fw"></span> 注 销</a></li>
            </ul>

            <div class="am-panel am-panel-default admin-sidebar-panel">
                <div class="am-panel-bd">
                    <p><span class="am-icon-bookmark am-icon-fw"></span> 登陆信息</p>
                    <p>
                        管理员登陆成功!<br>
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
            欢迎登陆浮云梦影管理员系统
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
