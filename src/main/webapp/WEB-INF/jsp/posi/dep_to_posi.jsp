<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>通过部门找职位</title>
</head>
<body>
<option value="0">--选择职位--</option>
<c:forEach var="posi" items="${requestScope.positions}">
    <option value="${posi.id}">${posi.pName}</option>
</c:forEach>
</body>
</html>
