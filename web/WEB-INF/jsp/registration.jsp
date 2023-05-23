<%--
  Created by IntelliJ IDEA.
  User: iw
  Date: 20/05/2023
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="nameId">Name: </label>
    <input type="text" name="name" id="nameId" required />
    <br><br>
    <label for="birthdayId">Birthday: </label>
    <input type="date" name="birthday" id="birthdayId" required />
    <br><br>
    <label for="emailId">Email: </label>
    <input type="email" name="email" id="emailId" required />
    <br><br>
    <label for="passwordId">Password: </label>
    <input type="password" name="password" id="passwordId" required />
    <br><br>
    <label>Gender:
        <c:forEach var="gender" items="${requestScope.genders}">
            <input type="radio" name="gender" value="${gender}" required />${gender}
        </c:forEach>
    </label>
    <br><br>
    <label for="roleId">Role: </label>
    <select id="roleId" name="role" required>
        <c:forEach var="role" items="${requestScope.roles}">
            <option name="gender" value="${role}" ${role == "USER" ? "selected" : ""}>${role}</option>
        </c:forEach>
    </select>
    <br><br>
    <button type="submit">Send</button>
    <br><br>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: brown">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span><br>
            </c:forEach>
        </div>
    </c:if>
</form>

</body>
</html>
