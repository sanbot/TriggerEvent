<%-- 
Document   : ConsultaUsuario
Created on : 18-mar-2014, 14:17:00
Author     : ADSI
--%>

<%-- 
Document   : RegistrarUsuario
Created on : 11/03/2014, 08:28:24 AM
Author     : santi_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionAdministrador.jspf" %>
<%
Contr_Consultar usu = new Contr_Consultar();
String[][] ListaUsuario = usu.BuscarDatosUsuariosPendientes();
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
	<link rel="shortcut icon" href="../favicon.ico">
	<link rel="stylesheet" type="text/css" href="../Libs/Bootstrap/css/bootstrap-theme.min.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/Default.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/demo.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/component.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/alertify.core.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/alertify.default.css" />
	<link type="text/css" rel="stylesheet" href="../Libs/Customs/DataTables/css/datatables.css" media="all">

	<script src="../Libs/Customs/js/modernizr.custom.js"></script>

</head>
<body>
        <%@include file="../WEB-INF/jspf/MenuAdministrador.jspf" %>
        <br/>
        <br/>
        <br/>
        <div class="container">
	<div class="row clearfix">
		<div class="col-md-12">
			<div class="form-group">
                            <a href="index.jsp">Inicio</a> <span class="glyphicon glyphicon-share-alt"></span><a href="ConsultaUsuario.jsp"> Registro y Consulta de Usuario</a><span class="glyphicon glyphicon-share-alt"></span>
			</div>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12">
			<br/>
			<h1 class="Center">Consulta de Usuarios Pendientes</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<br/>
                        <div class="table-responsive">
                                <table id="table1" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered">
                                        <thead>
                                                <tr>
                                                        <th>C&Oacute;DIGO</th>
                                                        <th>NOMBRE</th>
                                                        <th>TIPO USUARIO</th>
                                                        <th>TIPO DOCUMENTO</th>
                                                        <th>NÚMERO DOCUMENTO</th>
                                                        <th>ESTADO</th>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                </tr>
                                        </thead>
                                        <tbody>
                                                <%for(String[] Row : ListaUsuario){%>
                                                <tr>
                                                        <td><%=Row[0]%></td>
                                                        <td><%=Row[4]%></td>
                                                        <td><%=Row[1]%></td>
                                                        <td><%=Row[2]%></td>
                                                        <td><%=Row[3]%></td>
                                                        <td><%=Row[10]%></td>
                                                        <td><center><a href="MUsuario.jsp?Codigo=<%=Row[0]%>"><span class="glyphicon glyphicon-edit"></span></center></td>
                                                        <td><center><a href="MUsuario.jsp?Codigo=<%=Row[0]%>&Aprobar=false"><span class="glyphicon glyphicon-remove"></span></a></center></td>
                                                        <td><center><a href="MUsuario.jsp?Codigo=<%=Row[0]%>&Aprobar=true"><span class="glyphicon glyphicon-ok"></span></a></center></td>
                                                        <td><center><a href="CUsuario.jsp?Codigo=<%=Row[0]%>"><span class="glyphicon glyphicon-log-in"></span><center></td>
                                                </tr>
                                                <%}%>
                                        </tbody>
                                </table>
                        </div>
		</div>
		<div class="container marketing">
			<hr class="featurette-divider">
		</div>
	</div>
	<!-- FOOTER -->
	<footer>
		<p>&copy; 2013 Trigger Event, Inc.</p>
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
    <script type="text/javascript" src="../Libs/Customs/DataTables/js/jquery.dataTables.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="../Libs/Customs/DataTables/js/datatables.js" charset="UTF-8"></script>
    <script>
    new gnMenu( document.getElementById( 'gn-menu' ) );
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


