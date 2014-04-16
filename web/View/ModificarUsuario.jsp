<%-- 
    Document   : ModificarUsuario
    Created on : 6/04/2014, 11:56:05 AM
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%@include  file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include  file="../WEB-INF/jspf/ValidacionAdministrador.jspf" %>
<%
    //se instancia la clase controlador consulta de la capa logica
    Contr_Consultar usu = new Contr_Consultar();
    //creamos un string para tomar el valor que se trae por metodo get
    String codigoUsuario = "";
    //evaluamos si se trae un valor por metodo get
    if(request.getParameter("Codigo")!= null)
    {
        //si se trae el valor lo guardamos en la variable anteriormente creada
        codigoUsuario = request.getParameter("Codigo");
        //evaluamos si trae un parametro llamado aprobar
        if(request.getParameter("Aprobar")!=null)
        {
            //si se trae este parametro creamos un boolean
            boolean b;
            //guardamos lo que retorne el metodo de modificacion del estado
            b = usu.setCambiarEstadoUsaurio(codigoUsuario, request.getParameter("Aprobar").toString());
            //evaluamos el contenido del boolean
            if(b)
            {
                //guardamos el mensaje y el tipo de mensaje correspondiente
                session.setAttribute("TipoMensaje","Aprobar");
                session.setAttribute("Mensaje", usu.getMensaje());
            }
            else
            {
                //guardamos el mensaje y el tipo de mensaje correspondiente
                session.setAttribute("TipoMensaje","AprobarNO");
                session.setAttribute("Mensaje", usu.getMensaje());
            }
            //redireccionamos
            response.sendRedirect("CUsuariosPendientes.jsp");
        }
        else
        {
            //si no se trae el aprobar entonces redireccionamos
            response.sendRedirect("ConsultaUsuario.jsp");
        }
    }
    else
    {
        //si no se trae el codigo entonces redireccionamos
        response.sendRedirect("ConsultaUsuario.jsp");
    }
%>
