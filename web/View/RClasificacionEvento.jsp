<%-- 
    Document   : RClasificacionEvento
    Created on : 09-abr-2014, 14:15:47
    Author     : ADSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar" import="java.io.OutputStream"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionAdministradorEmpresa.jspf" %>
<%
    if(request.getParameter("CodigoEvento")== null)
    {
        response.sendRedirect("ConsultaEvento.jsp");
    }
    String Codigo = request.getParameter("CodigoEvento");
    Contr_Consultar usu = new Contr_Consultar();
    String[][] ListaGustosNuevos  = usu.getClasifiacionNuevos(Codigo);
    boolean b = usu.getComprobacionRegistroEvento(Codigo);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <title>Trigger Event</title>
    <meta name="description" content="Eventos musicales" />
    <meta name="keywords" content="Eventos, musical, Trigger Event" />
    <meta name="author" content="Sanser Soft" />
    <link rel="shortcut icon" href="../Libs/Customs/images/logoteazul.ico">
    <%@include file="../WEB-INF/jspf/EstilosCSS.jspf" %>
    <link type="text/css" rel="stylesheet" href="../Libs/Customs/DataTables/css/datatables.css" media="all">
    <script src="../Libs/Customs/js/modernizr.custom.js"></script>
</head>
<body>
    
    <%
    if(Rol.equals("Administrador"))
    {%>
    <%@include file="../WEB-INF/jspf/MenuAdministrador.jspf" %>
    <%
    }else if(Rol.equals("Cliente"))
    {%>
    <%@include file="../WEB-INF/jspf/MenuCliente.jspf" %>
    <%}else if(Rol.equals("Empresa")){%>
    <%@include file="../WEB-INF/jspf/MenuEmpresa.jspf" %>
    <%}%>
	
    <div class="container">
        <br/>
	<br/>
	<br/>
        <div class="row clearfix">
            <div class="col-xs-12">
                <ol class="breadcrumb">
                    <li><a href="index.jsp">Inicio</a></li>
                    <li><a href="ConsultaEventos.jsp">Eventos</a></li>
                    <li class="active">Clasificaci&oacute;n Evento</a></li>
                </ol>
            </div>
        </div>
        <div class="row clearfix">
            <div class="col-xs-12">
                <h1 class="Center">Clasificar Evento</h1>
                <%if(!b){%>
                <h5 class="Center"><i>Nota: Seleccione al menos un gusto y un ambiente para completar el registro</i></h5>
                <%}%>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1">
                <div class="table-responsive">
                    <table id="table" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Tipo</th>
                                <th>Imagen</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                            for(String[] Row : ListaGustosNuevos){%>
                            <tr>
                                <td><%=Row[1]%></td>
                                <td><%=Row[2]%></td>
                                <td><img src="Imagen.jsp?Codigo=<%=Row[0]%>" class="img-responsive imgseleccion"></td>
                                <td><center><a href="ClasificarEvento.jsp?CodigoSeleccion=<%=Row[0]%>&Accion=Nuevo&CodigoEvento=<%=Codigo%>"><span class="glyphicon glyphicon-ok"></span><center></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4 col-xs-offset-4 col-sm-2 col-sm-offset-5 col-md-offset-5 col-md-2">
                <%if(b){%>
                    <a href="ConsultaEvento.jsp" class="btn btn-block defecto">Finalizar</a>
                <%}%>
            </div>
        </div>
        <footer>
            <div class="row">
                <div class="col-xs-12">
                    <hr class="featurette-divider">
                    <p><center>&copy; 2014 Sergio Rivera Ballesteros, Santiago Botero Ru&iacute;z. Aprendices Tecn&oacute;logos en An&aacute;lisis y Desarrollo de Sistemas de Informaci&oacute;n, SENA CESGE regional Antioquia</center></p>
                </div>
            </div>
        </footer>
    </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!--Bootstrap-->
    <script src="../Libs/Bootstrap/js/jquery-1.10.2.min.js"></script>    
    <script src="../Libs/Bootstrap/js/bootstrap.min.js"></script>
    <script src="../Libs/Bootstrap/js/holder.js"></script>
    <!--Parsley-->
    <script src="../Libs/Customs/js/Parsley.js"></script>    
    <script src="../Libs/Customs/js/classie.js"></script>
    <script src="../Libs/Customs/js/gnmenu.js"></script>
    <script type="text/javascript" src="../Libs/Customs/DataTables/js/jquery.dataTables.js" charset="UTF-8"></script>
    <script type="text/javascript" src="../Libs/Customs/DataTables/js/datatables.js" charset="UTF-8"></script>
    <script>
    	new gnMenu( document.getElementById( 'gn-menu' ) );
    </script>
    <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
    <script type="text/javascript">
    	$(document).ready(function() {
            
            $('#table').dataTable({
                "sPaginationType": "bs_normal"
                // "sPaginationType": "bs_four_button"
                // "sPaginationType": "bs_full"
                // "sPaginationType": "bs_two_button"
            }); 
            $('#table').each(function(){
                var datatable = $(this);
                // SEARCH - Add the placeholder for Search and Turn this into in-line form control
                var search_input = datatable.closest('.dataTables_wrapper').find('div[id$=_filter] input');
                search_input.attr('placeholder', 'Buscar');
                search_input.addClass('form-control input-sm');
                // LENGTH - Inline-Form control
                var length_sel = datatable.closest('.dataTables_wrapper').find('div[id$=_length] select');
                length_sel.addClass('form-control input-sm');
            });
        });
</script>
<%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
<%session.setAttribute("Mensaje", "");%>
</body>
</html>