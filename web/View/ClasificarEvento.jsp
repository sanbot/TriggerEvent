<%-- 
    Document   : ClasificarEvento
    Created on : 09-abr-2014, 14:48:05
    Author     : ADSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%
    if(request.getParameter("CodigoSeleccion")!=null && request.getParameter("Accion")!=null && request.getParameter("CodigoEvento") != null)
    {
        Contr_Consultar sel = new Contr_Consultar();
        String CodigoSeleccion = (String) request.getParameter("CodigoSeleccion");
        String CodigoEvento = (String) request.getParameter("CodigoEvento");
        String Accion = (String) request.getParameter("Accion");
        if(!Accion.equals("Nuevo"))
        {
            response.sendRedirect("RClasificacionEvento.jsp?CodigoEvento="+CodigoEvento);
        }
        else
        {
            boolean b = sel.AddClasificacionEvento(CodigoSeleccion, Accion, CodigoEvento);
            if(b)
            {
                session.setAttribute("TipoMensaje", "Dio");
            }
            else
            {
                session.setAttribute("TipoMensaje", "NODio");
            }
            session.setAttribute("Mensaje", sel.getMensaje());
            
            response.sendRedirect("RClasificacionEvento.jsp?CodigoEvento="+CodigoEvento);
        }
    }
    else
    {
        response.sendRedirect("RClasificacionEvento.jsp?CodigoEvento="+request.getParameter("CodigoEvento"));
    }
%>
