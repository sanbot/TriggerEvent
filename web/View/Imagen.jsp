<%-- 
    Document   : Imagen
    Created on : 3/04/2014, 08:44:03 PM
    Author     : santiago
--%>

<%@page import="java.io.OutputStream"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar" import="java.sql.Blob"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Contr_Consultar img = new Contr_Consultar();
            String CodigoImg = request.getParameter("Codigo");
            Blob imagen = null;
            imagen = img.BuscarImagenes(CodigoImg);
            byte[] imgData = imagen.getBytes(1,(int)imagen.length());
            response.setContentType("image/gif");

            OutputStream o = response.getOutputStream();
            o.write(imgData);
            o.flush();
            o.close();
        %>
    </body>
</html>
