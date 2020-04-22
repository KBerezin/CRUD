<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>
<div>
    <p>Update user ${requestScope.userForUpdate.login} </p>
    <div>
        <form action="${pageContext.request.contextPath}/updateUser" method="post">
            <input type="hidden" name="id" value="${requestScope.userForUpdate.id}">
            <input type="hidden" name="login" value="${requestScope.userForUpdate.login}">
            <input type="password" name="pass" value="${requestScope.userForUpdate.password}" required>
            <input type="text" name="name" value="${requestScope.userForUpdate.name}" required>
            <button>update</button>
        </form>
    </div>
</div>
</body>
</html>