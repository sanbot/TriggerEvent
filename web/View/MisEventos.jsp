<%-- 
Document   : ConsultaUsuario
Created on : 18-mar-2014, 14:17:00
Author     : ADSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionEmpresa.jspf" %>
<%
Contr_Consultar usu = new Contr_Consultar();
String nit = (String) session.getAttribute("No_Documento");
String[][] ListaEventos = usu.getBuscarDatosMisEventos(nit);
int Cantidad = usu.getCantidadEventosPendientes();
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
                    <li class="active">Eventos</a></li>
                </ol>
            </div>
        </div>
        <div class="row clearfix">
            <div class="col-xs-12">
                <h1 class="Center">Eventos</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4 col-xs-offset-4 col-sm-2 col-sm-offset-5 col-md-offset-5 col-md-2">
                <div class="form-group">
                    <a href="RegistrarEvento.jsp" role="button" class="btn btn-block defecto">Registrar Evento</a>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="table-responsive">
                    <table id="table1" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Imagen</th>
                                <th>Nombre</th>
                                <th>Fecha</th>
                                <th>Hora</th>
                                <th>Creador</th>
                                <th>Ciudad</th>
                                <th>Eestado</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for(String[] Row : ListaEventos){%>
                                <tr>
                                    <td><img src="ImagenEvento.jsp?Codigo=<%=Row[0]%>" class="img-responsive imgseleccion"/></td>
                                    <td><%=Row[1]%></td>
                                    <td><%=Row[2]%></td>
                                    <td><%=Row[6]%></td>
                                    <td><%=Row[3]%></td>
                                    <td><%=Row[4]%></td>
                                    <td><%=Row[5]%></td>
                                    <td><center><a href="DetalleEvento.jsp?CodigoEvento=<%=Row[0]%>&Pendiente=true"><span class="glyphicon glyphicon-log-in"></span></a><center></td>
                                    <td><center><a class="modal-desactivarevento" href="#modal-container-Desactivar" role="button" data-toggle="modal" data-id="<%=Row[0]%>" data-nombre="<%=Row[1]%>" data-creador="<%=Row[3]%>"><span class="glyphicon glyphicon-remove"></span></a></center></td>
                                </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>			
            </div>
        </div>
        <div class="row">
            <form data-validate="parsley" enctype="multipart/form-data" method="post" action="/TriggerEvent/Contr_Evento">
                <div class="col-xs-12">
                    <div class="modal fade" id="modal-container-Desactivar" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <h4 class="modal-title" id="myModalLabel">
                                        <center>Desactivar el evento: <span id="NombreMis_Evento"></span></center>
                                    </h4>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-xs-8 col-xs-offset-2">
                                            <div class="form-group">
                                                    <input id="CodigoMis_Evento" name="Codigo" type="hidden" readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-10 col-xs-offset-1">
                                            <div class="form-group">
                                                <label for="Motivo">Motivo</label>
                                                <textarea id="Motivo" name="Motivo" class="form-control" data-rangelength="[6,250]" rows="7" data-required="true"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                                    <button name="DesactivarEvento" type="submit" class="btn defecto">Desactivar</button> 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
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
    <script>
    $(document).ready(function () {
        $(".modal-desactivarevento").click(function(){
            var Id = $(this).data('id');
            var Name = $(this).data('nombre');
            $(".modal-body #CodigoMis_Evento").val( Id );
            $(".modal-title #NombreMis_Evento").text( Name );
        });
    });
    </script>
    <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
    <script type="text/javascript">
    	$(document).ready(function() {
    		$('#table1').dataTable({
    			"sPaginationType": "bs_normal"
                // "sPaginationType": "bs_four_button"
                // "sPaginationType": "bs_full"
                // "sPaginationType": "bs_two_button"
            }); 
    		$('#table1').each(function(){
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