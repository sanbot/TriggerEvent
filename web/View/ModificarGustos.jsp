<%-- 
    Document   : ModificarGustos
    Created on : 5/04/2014, 11:02:37 AM
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%@include file="../WEB-INF/jspf/ValidacionGeneral.jspf" %>
<%
    if(request.getParameter("Codigo")!=null && request.getParameter("Accion")!=null)
    {
        Contr_Consultar sel = new Contr_Consultar();
        String Codigo = (String) request.getParameter("Codigo");
        String Accion = (String) request.getParameter("Accion");
        String CodigoUsuario = (String) session.getAttribute("Codigo");
        if(!Accion.equals("Nuevo") && ! Accion.equals("Quitar"))
        {
            response.sendRedirect("MisGustos.jsp");
        }
        else
        {
            boolean b = sel.AddRemoveGustos(Codigo, CodigoUsuario, Accion);
            if(b)
            {
                session.setAttribute("TipoMensaje", "Dio");
            }
            else
            {
                session.setAttribute("TipoMensaje", "NODio");
            }
            session.setAttribute("Mensaje", sel.getMensaje());
            if(Accion.equals("Quitar"))
            {
                response.sendRedirect("MisGustos.jsp");
            }
            else
            {
                response.sendRedirect("RegistrarGustos.jsp");
            }
        }
    }
    else
    {
        response.sendRedirect("MisGustos.jsp");
    }
%>
