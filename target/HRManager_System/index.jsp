<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>起始引导页面</title>
</head>
<body>

<%response.sendRedirect(request.getContextPath()+"/main/goMain.do");%>

<%--默认起始页面跳转至主页面(测试用)--%>
<%--<div align="center" style="padding: 100px">
    <a href="main/goUserLogin.do">用户</a>
    <br><br>
    <a href="main/goEmpLogin.do">员工</a>
</div>
<%response.sendRedirect(request.getContextPath()+"/main/goDemo.do");%>
<%response.sendRedirect(request.getContextPath()+"/main/goUserLogin.do");%>--%>

</body>
</html>