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

<html>
<head>
  <title>Title</title>
</head>
<body>

<%@ include file="header.jsp" %>

<h1>Список перелётов:</h1>
<ul>
  <c:forEach var="flight" items="${requestScope.flights}">
    <%--@elvariable id="flight" type="com.dmdev.http.dto.FlightDto"--%>
    <li>
      <a href="${pageContext.request.contextPath}/tickets?flightId=${flight.id}">${flight.description}</a>
    </li>
  </c:forEach>
</ul>
</body>
</html>

<%!
  public void jspInit() {
    System.out.println("Hello world!");
  }
%>
