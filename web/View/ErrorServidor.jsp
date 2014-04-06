<%-- 
    Document   : ErrorServidor
    Created on : 4/04/2014, 10:38:35 PM
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //esta pagina solo organiza el mensaje y redirecciona al index en el cual se mostrará el mensaje de error
    if(request.getParameter("Error")!= null)
    {
        //si nos mandan una variable error por metodo get entra acá
        //creamos una variable tipo string para almacenar este parametro enviado por metodo get
        String Error = request.getParameter("Error");
        //evaluamos el contenido de la variable error
        if(Error.equals("404"))
        {
            //si es error 404 guardamos el mensaje que se mostrará
            session.setAttribute("Mensaje", "La página a la que está tratando de acceder no ha sido encontrada, verifíquelo e inténtelo de nuevo.");
        }
        else if(Error.equals("500"))
        {
            //si es el error 500 guardamos el error que mostrará
            session.setAttribute("Mensaje", "Ocurrió un error inesperado en el sistema, espere unos segundos e inténtelo de nuevo.");
        }
        //para los mensajes, ponemos un tipo de mensaje para el color de la alerta
        session.setAttribute("TipoMensaje", "NODio");
        //redireccionamos al index.jsp
        response.sendRedirect("index.jsp");
    }
    else
    {
        //en caso de no tener contenido el error redireccionamos al index.jsp simplemente
        response.sendRedirect("index.jsp");
    }
%>
