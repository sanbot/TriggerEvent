<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    //esta pagina solo organiza el mensaje y redirecciona al index en el cual se mostrará el mensaje de error
    if (request.getParameter("Error") != null) {
        //si nos mandan una variable error por metodo get entra acá
        //creamos una variable tipo string para almacenar este parametro enviado por metodo get
        String Error = request.getParameter("Error");
        if (Error.equals("500")) {
            //si es el error 500 guardamos el error que mostrará
            session.setAttribute("Mensaje", "Ocurrió un error inesperado en el sistema, espere unos segundos e inténtelo de nuevo.");
        }
        //para los mensajes, ponemos un tipo de mensaje para el color de la alerta
        session.setAttribute("TipoMensaje", "NODio");
        //redireccionamos al index.jsp
        response.sendRedirect("index.jsp");
    } else {
        //en caso de no tener contenido el error redireccionamos al index.jsp simplemente
        response.sendRedirect("index.jsp");
    }
%>
