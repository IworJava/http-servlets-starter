<%--
  Created by IntelliJ IDEA.
  User: iw
  Date: 29/05/2023
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <form action="${pageContext.request.contextPath}/logout"
                  method="post">
                <button type="submit">Log out</button>
            </form>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/login">
                <button type="button">Log in</button>
            </a>
        </c:otherwise>
    </c:choose>
</div>
