<%--
  Created by IntelliJ IDEA.
  User: iw
  Date: 19/05/2023
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
Добавлены 2 библиотеки для использования JSTL:
Jakarta Standard Tag Library API » 2.0.0
Jakarta Standard Tag Library Implementation » 2.0.0 (glassfish)
--%>

<html>
<head>
  <title>Title</title>
</head>
<body>

<%@ include file="header.jsp" %>

<h1>Купленные билеты:</h1>
<ul>
  <c:choose>
    <c:when test="${not empty requestScope.tickets}">
      <c:forEach var="ticket" items="${requestScope.tickets}">
        <%--@elvariable id="ticket" type="com.dmdev.http.dto.TicketDto"--%>
        <li>${fn:toLowerCase(ticket.seatNo)}</li>
      </c:forEach>
    </c:when>
    <c:otherwise>
      <span>билеты не найдены</span>
    </c:otherwise>
  </c:choose>
</ul>
</body>
</html>
