<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>招聘系统主页</title>
    <base href="<%=basePath%>"/>
    <link rel="icon" type="image/png" href="assets/i/cai.png">
    <link rel="stylesheet" href="assets/css/amazeui.min.css">
    <link rel="stylesheet" href="assets/css/admin.css">
    <link rel="stylesheet" href="assets/css/app.css"/>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/amazeui.min.js"></script>
    <script src="assets/js/app.js"></script>
    <style type="text/css">
        #container2, #sliders {
            min-width: 310px;
            max-width: 800px;
            margin: 5% auto;
        }
        #container2 {
            height: 400px;
        }
    </style>
    <script type="text/javascript">
        // 查看个人信息
        function showUserInfo() {
            $.ajax({
                url: "user/userInfo.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }

        // 编辑个人信息
        function toEditUserInfo() {
            $.ajax({
                url: "user/toEditUserInfo.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }

        // 获得空白简历
        function showBlankResume() {
            $.ajax({
                url: "resume/getBlank.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }

        // 查看简历
        function showResumeSelf() {
            $.ajax({
                url: "resume/showResumeSelf.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }
        // 修改简历
        function toEditResume() {
            $.ajax({
                url: "resume/toEditResume.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }

        // 查看招聘信息
        function showHireInfo() {
            $.ajax({
                url: "hire/showHIByUser.do", success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }

        // 查看所有关于自己的面试通知
        function showFNByUser() {
            $.ajax({
                url: "face/showFNByUser.do?uid=${sessionScope.user.id}",
                success: function (result) {
                    $("#mainShow").html(result);
                }
            });
        }
        // 查看所有关于自己的面试情况(成功或失败)
        function showFIByUSer() {
            $.ajax({
                url: "face/showFIByUSer.do?uid=${sessionScope.user.id}",
                success: function (result) {
                    $("#contentShow").html(result);
                    $("#contentTips").modal();
                }
            });
        }

        // 用户注销
        function logout() {
            $('#confirmTips').modal({
                onConfirm: function () {
                    window.location.replace("user/logout.do");
                }
            });
        }
        //图表生成
        $(function () {
            var chart = new Highcharts.Chart({
                chart: {
                    renderTo: 'container2',
                    type: 'column',
                    options3d: {
                        enabled: true,
                        alpha: 15,
                        beta: 15,
                        depth: 50,
                        viewDistance: 25
                    }
                },
                title: {
                    text: '招聘信息图表'
                },
                plotOptions: {
                    column: {
                        depth: 25
                    }
                },
                xAxis: {
                    categories: ['人事部-助理','财务部-经理','管理部-助理','管理部主管','技术部-工程师','人事部经理','测试部1-经理']
                },
                series: [{
                    data:[
                        ['(人数:2)', 2],
                        ['(人数:1)', 1],
                        ['(人数:4)', 4],
                        ['(人数:7)', 7],
                        ['(人数:10)', 10],
                        ['(人数:6)', 6],
                        ['(人数:3)', 3]
                    ]
                }]
            });

            function showValues() {
                $('#alpha-value').html(chart.options.chart.options3d.alpha);
                $('#beta-value').html(chart.options.chart.options3d.beta);
                $('#depth-value').html(chart.options.chart.options3d.depth);
            }

            $('#sliders input').on('input change', function () {
                chart.options.chart.options3d[this.id] = this.value;
                showValues();
                chart.redraw(false);
            });

            showValues();
        });

    </script>
</head>
<body>

<%--导航栏: 网站名+面试通知+用户工具+开启全屏--%>
<header class="am-topbar am-topbar-inverse admin-header" style="background-color: forestgreen">
    <div class="am-topbar-brand">
        <strong>浮云梦影-招聘系统</strong>
        <small>Hire System</small>
    </div>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <li>
                <a href="javascript:showFNByUser();"><span class="am-icon-envelope-o"></span> 面试通知
                    <span class="am-badge am-badge-warning">${sessionScope.faceNoticeListCount}</span>
                </a>
            </li>
            <li>
                <a href="javascript:showFIByUSer();"><span class="am-icon-envelope-o"></span> 录用通知
                    <span class="am-badge am-badge-danger">${sessionScope.faceInfoListCount}</span>
                </a>
            </li>
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:void(0);">
                    <span class="am-icon-user"></span> 用户个人信息
                    <span class="am-icon-caret-down"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li><a href="javascript:showUserInfo()"><span class="am-icon-eye am-icon-fw"></span> 查 看</a></li>
                    <li><a href="javascript:toEditUserInfo()"><span class="am-icon-pencil am-icon-fw"></span> 编 辑</a>
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

                <li><a href="main/goUserMain.do"><span class="am-icon-home am-icon-fw"></span> 首 页</a></li>

                <c:if test="${empty sessionScope.user.resume}">
                    <li>
                        <a href="javascript:showBlankResume()" class="am-cf">
                            <span class="am-icon-pencil am-icon-fw"></span> 填写 个人简历
                        </a>
                    </li>
                </c:if>
                <c:if test="${not empty sessionScope.user.resume}">
                    <li>
                        <a href="javascript:showResumeSelf()" class="am-cf">
                            <span class="am-icon-file am-icon-fw"></span> 查看 个人简历
                        </a>
                    </li>
                    <li>
                        <a href="javascript:toEditResume()" class="am-cf">
                            <span class="am-icon-edit am-icon-fw"></span> 修改 个人简历
                        </a>
                    </li>
                    <li>
                        <a href="javascript:showHireInfo()">
                            <span class="am-icon-th-list am-icon-fw"></span> 招聘信息
                            <span class="am-badge am-badge-secondary am-margin-right am-fr"
                                  id="hireInfoCount">${sessionScope.hireInfoListCount}</span>
                        </a>
                    </li>
                </c:if>
                <li><a href="javascript:logout()"><span class="am-icon-sign-out am-icon-fw"></span> 注 销</a></li>
            </ul>

            <div class="am-panel am-panel-default admin-sidebar-panel">
                <div class="am-panel-bd">
                    <p><span class="am-icon-bookmark am-icon-fw"></span> 登陆信息</p>
                    <p>
                        ${sessionScope.user.email}
                        <c:if test="${empty sessionScope.user.email}">
                            ${sessionScope.user.phone}
                        </c:if>用户<br>登陆成功!
                    </p>
                </div>
            </div>

        </div>

    </div>

    <%--主要显示区--%>
    <div class="admin-content">

        <div class="admin-content-body" id="mainShow" style="padding: 1em">
            <p>欢迎登陆浮云梦影招聘系统! 提示:如果你是新注册的用户,请及时在填写简历前把信息补全</p>
            <div id="container2"></div>
            <div id="sliders">
                <table>
                    <tr>
                        <td>Alpha Angle</td>
                        <td><input id="alpha" type="range" min="0" max="45" value="15"/> <span id="alpha-value"
                                                                                               class="value"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>Beta Angle</td>
                        <td><input id="beta" type="range" min="-45" max="45" value="15"/> <span id="beta-value"
                                                                                                class="value"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>Depth</td>
                        <td><input id="depth" type="range" min="20" max="100" value="50"/> <span id="depth-value"
                                                                                                 class="value"></span>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

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
<script src="code/highcharts.js"></script>
<script src="code/highcharts-3d.js"></script>
<script src="code/modules/exporting.js"></script>
</body>
</html>
