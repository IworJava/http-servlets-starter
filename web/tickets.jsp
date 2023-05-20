<%--
  Created by IntelliJ IDEA.
  User: iw
  Date: 19/05/2023
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.dmdev.http.service.TicketService" %>
<%@ page import="com.dmdev.http.dto.TicketDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h1>Купленные билеты:</h1>
<ul>
  <%
    Long flightId = Long.valueOf(request.getParameter("flightId"));
    List<TicketDto> tickets = TicketService.getInstance().findTicketsByFlightId(flightId);
    for (TicketDto ticket : tickets) {
      out.write(String.format("<li>%s</li>", ticket.getSeatNo()));
    }
  %>
</ul>
</body>
</html>

<%!
  public void jspInit() {
    System.out.println("Hello world!");
  }
%>