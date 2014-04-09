<%-- 
    Document   : ModificarSeleccion
    Created on : 9/04/2014, 08:42:16 AM
    Author     : santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%
    if(request.getParameter("Codigo")!=null && request.getParameter("Accion")!=null)
    {
        Contr_Consultar sel = new Contr_Consultar();
        String Codigo = (String) request.getParameter("Codigo");
        String Accion = (String) request.getParameter("Accion");
        if(!Accion.equals("Aprobar") && ! Accion.equals("Desaprobar"))
        {
            response.sendRedirect("ConsultaSeleccion.jsp");
        }
        else
        {
            boolean b = sel.AprobarDesaprobarSeleccion(Codigo, Accion);
            if(b)
            {
                session.setAttribute("TipoMensaje", "Dio");
            }
            else
            {
                session.setAttribute("TipoMensaje", "NODio");
            }
            session.setAttribute("Mensaje", sel.getMensaje());
            
            response.sendRedirect("ConsultaSeleccion.jsp");
        }
    }
    else
    {
        response.sendRedirect("ConsultaSeleccion.jsp");
    }
%>
