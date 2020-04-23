<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Userlist</title>
</head>
<body>
<div align="center">
    <a href="${pageContext.request.contextPath}/logout">Logout</a> <br>
    <h2>Userlist</h2>
    <h3><a href="${pageContext.request.contextPath}/admin/addUser">New User</a></h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Password</th>
            <th>Name</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${requestScope.allUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.name}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/updateUser?id=${user.id}">Edit</a>
                    <a href="${pageContext.request.contextPath}/admin/deleteUser?id=${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/">Back to Home Page</a>
</div>
</body>
</html>
