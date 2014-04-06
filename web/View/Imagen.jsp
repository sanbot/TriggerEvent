<%-- 
    Document   : Imagen
    Created on : 3/04/2014, 08:44:03 PM
    Author     : santiago
--%>

<%@page import="java.io.OutputStream"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar" import="java.sql.Blob"%>
<%@include file="../WEB-INF/jspf/ValidacionGeneral.jspf" %>%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trigger Event</title>
    </head>
    <body>
        <%
            //se crea una instancia de la clase consulta
            Contr_Consultar img = new Contr_Consultar();
            //se crea un string y que tome el parametro del codigo para mostrar la imagen
            String CodigoImg = request.getParameter("Codigo");
            //se crea una variable tipo blob para luego almacenar los bits de esta
            Blob imagen = null;
            //buscamos la imagen en la base de datos por medio de la capa logica
            imagen = img.BuscarImagenes(CodigoImg);
            //de el blob sacamos los bits
            byte[] imgData = imagen.getBytes(1,(int)imagen.length());
            //enviamos el tipo de imagen que vamos a mostrar
            response.setContentType("image/gif");            

            //creamos una variable para tipo salida para imprimir la imagen
            OutputStream o = response.getOutputStream();
            //escribimos la imagen y cerramos la variable
            o.write(imgData);
            o.flush();
            o.close();
        %>
    </body>
</html>
