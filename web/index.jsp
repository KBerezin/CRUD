<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<div>
    <div>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <label> Login
                <input type="text" name="login" placeholder="username"/>
            </label>
            <label> Password
                <input type="password" name="pass" placeholder="password"/>
            </label>
            <button>login</button>
        </form>
    </div>
</div>
</body>
</html>

<%--
<p class="message">Not registered? <a href="${pageContext.request.contextPath}/addNewUser">Create an account</a></p>
--%>


<%--<c:if test="${requestScope.wrongLoginPassMessage != null}">
    ${requestScope.wrongLoginPassMessage}
</c:if>--%>
