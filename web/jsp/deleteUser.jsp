<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Delete user</title>
</head>
<body>
<p>Are you sure you want to delete user ${requestScope.userForDelete.login}?</p>
<form action="${pageContext.request.contextPath}/admin/deleteUser" method="post">
    <input type="hidden" name="idForDelete" value="${requestScope.userForDelete.id}">
    <input type="submit" value="Delete">
</form>
</body>
</html>