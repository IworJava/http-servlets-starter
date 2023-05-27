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

<img src="${pageContext.request.contextPath}/images/users/01.jpeg" alt="user's photo" width="200">
<%--<img src="https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg" alt="user's photo" width="500">--%>
<br><br>
<form action="${pageContext.request.contextPath}/registration" method="post" enctype="multipart/form-data">
    <label for="nameId">Name: </label>
    <input type="text" name="name" id="nameId" required />
    <br>
    <label for="birthdayId">Birthday: </label>
    <input type="date" name="birthday" id="birthdayId" required />
    <br>
    <label for="imageId">Image: </label>
    <input type="file" name="image" id="imageId" required />
    <br>
    <label for="emailId">Email: </label>
    <input type="email" name="email" id="emailId" required />
    <br>
    <label for="passwordId">Password: </label>
    <input type="password" name="password" id="passwordId" required />
    <br>
    <label>Gender:
        <c:forEach var="gender" items="${requestScope.genders}">
            <input type="radio" name="gender" value="${gender}" required />${gender}
        </c:forEach>
    </label>
    <br>
    <label for="roleId">Role: </label>
    <select id="roleId" name="role" required>
        <c:forEach var="role" items="${requestScope.roles}">
            <option name="gender" value="${role}" ${role == "USER" ? "selected" : ""}>${role}</option>
        </c:forEach>
    </select>
    <br>
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
