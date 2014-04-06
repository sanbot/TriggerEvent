<%-- 
    Document   : Salir
    Created on : 10/03/2014, 10:05:35 PM
    Author     : santi_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.invalidate();
    response.sendRedirect("index.jsp");
%>
