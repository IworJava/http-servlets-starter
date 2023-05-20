<%--
  Created by IntelliJ IDEA.
  User: iw
  Date: 19/05/2023
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%@ include file="header.jsp" %>

<div>
    <span>CONTENT. Контент</span>
    <p>${requestScope.flights.get(1)}</p>   <%-- not null safe --%>
    <p>${requestScope.flights[1]}</p>       <%-- null safe --%>
    <p>ID: ${requestScope.flights[1].id}</p>
    <p>DESC: ${requestScope.flights[1].description}</p>
    <p>JSESSIONID: ${cookie.JSESSIONID.value}</p>
    <p>Param id: ${param.id}</p>
    <p>Param test: ${param.test}</p>
    <p>Flights not empty: ${not empty requestScope.flights}</p>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>
