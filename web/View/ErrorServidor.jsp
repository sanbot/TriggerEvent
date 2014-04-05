<%-- 
    Document   : ErrorServidor
    Created on : 4/04/2014, 10:38:35 PM
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(request.getParameter("Error")!= null)
    {
        String Error = request.getParameter("Error");
        if(Error.equals("404"))
        {
            session.setAttribute("Mensaje", "La página a la que está tratando de acceder no ha sido encontrada, verifíquelo e inténtelo de nuevo.");
            session.setAttribute("TipoMensaje", "NODio");
        }
        else if(Error.equals("500"))
        {
            session.setAttribute("Mensaje", "Ocurrió un error inesperado en el sistema, espere unos segundos e inténtelo de nuevo.");
            session.setAttribute("TipoMensaje", "NODio");
        }
        response.sendRedirect("index.jsp");
    }
    else
    {
        response.sendRedirect("index.jsp");
    }
%>
