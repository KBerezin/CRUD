<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
</head>
<body>
<div>
    <p class="message">Fill the form to add new user</p>
    <div>
        <form class="login-form" action="${pageContext.request.contextPath}/admin/addUser" method="post">
            <input type="text" name="login" placeholder="login" required>
            <input type="password" name="pass" placeholder="password" required>
            <input type="text" name="name" placeholder="name" required>
            <button>Create new user</button>
        </form>
    </div>
</div>
</body>
</html>
