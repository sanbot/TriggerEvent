<%-- 
    Document   : ModificarEvento
    Created on : 10-abr-2014, 15:23:33
    Author     : ADSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%
    if(request.getParameter("CodigoEvento")!=null && request.getParameter("Aprobar")!=null)
    {
        Contr_Consultar sel = new Contr_Consultar();
        String CodigoEvento = request.getParameter("CodigoEvento");
        String Aprobar = request.getParameter("Aprobar");
        if(!Aprobar.equals("true") && ! Aprobar.equals("false"))
        {
            response.sendRedirect("CEventoPendiente.jsp");
        }
        else
        {
            boolean b = sel.AprobarDesaprobarEvento(CodigoEvento, Aprobar);
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