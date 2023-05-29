<%--
  Created by IntelliJ IDEA.
  User: iw
  Date: 28/05/2023
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="emailId">Email:
        <input type="text" name="email" value="${param.email}" id="emailId" required>
    </label>
    <br>
    <label for="passwordId">Password:
        <input type="password" name="password" id="passwordId" required>
    </label>
    <br>
    <button type="submit">Login</button>
    <br>
    <a href="${pageContext.request.contextPath}/registration">
        <button type="button">Register</button>
    </a>
</form>

<c:if test="${param.error != null}">
    <div style="color: brown">
        <span>Login or password is not correct</span>
    </div>
</c:if>

</body>
</html>
