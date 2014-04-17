<%-- 
    Document   : ModificarEvento
    Created on : 10-abr-2014, 15:23:33
    Author     : ADSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionAdministrador.jspf" %>
<%
    if(request.getParameter("CodigoEvento")!=null && request.getParameter("Aprobar")!=null)
    {
        Contr_Consultar sel = new Contr_Consultar();
        String CodigoEvento = request.getParameter("CodigoEvento");
        String Aprobar = request.getParameter("Aprobar");
        if(!Aprobar.equals("true"))
        {
            response.sendRedirect("CEventoPendiente.jsp");
        }
        else
        {
            boolean b = sel.AprobarDesaprobarEvento(CodigoEvento);
            if(b)
            {
                session.setAttribute("TipoMensaje", "Dio");
            }
            else
            {
                session.setAttribute("TipoMensaje", "NODio");
            }
            session.setAttribute("Mensaje", sel.getMensaje());
            response.sendRedirect("CEventoPendiente.jsp");
        }
    }
    else
    {
        response.sendRedirect("CEventoPendiente.jsp");
    }
%>