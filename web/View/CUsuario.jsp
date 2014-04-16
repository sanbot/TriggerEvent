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
String codigoUsuario = "";
if(request.getParameter("Codigo")!= null)
{
    codigoUsuario = request.getParameter("Codigo");
}
else
{
    response.sendRedirect("ConsultaUsuario.jsp");
}

String[] DatosUsuario = usu.BuscarDatosUsuario(codigoUsuario);
if(DatosUsuario[0] == null)
{
    response.sendRedirect("ConsultaUsuario.jsp");
}
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
        <%@include file="../WEB-INF/jspf/EstilosCSS.jspf" %>
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
                                    <a href="index.jsp">Inicio</a> <span class="glyphicon glyphicon-share-alt"></span> <a href="ConsultaUsuario.jsp">Registro y Consulta de Usuario </a><span class="glyphicon glyphicon-share-alt"> </span> Consulta de Usuario
				</div>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-12">
				<br/>
				<h1 class="Center">Consulta de Usuario</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">

				<form data-validate="parsley" method="post" action="/TriggerEvent/Contr_Usuarios">

					<div class="row">
                                                 <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Codigo">Codigo</label>
                                                                <input name="Codigo" type="text" class="form-control" id="nombre" data-rangelength="[3,100]" data-notblank="true" data-required="true" value="<%=DatosUsuario[0]%>" readonly/>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Nombre">Nombre</label>
								<input name="Nombre" type="text" class="form-control" id="nombre" data-rangelength="[3,100]" data-notblank="true" data-required="true" value="<%=DatosUsuario[5]%>" readonly/>
							</div>
						</div>
                                                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Tipo">Tipo Usuario</label>
								<select name="Tipo" id="Tipos" class="form-control" data-required="true">
                                                                        <option value="<%=DatosUsuario[1]%>"><%=DatosUsuario[2]%></option>
								</select>
							</div>
						</div>
						
					</div>
					<div class="row">

                                                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Tipo_Documento">Tipo Documento</label>
								<select name="Tipo_Documento" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                                                        <option value="<%=DatosUsuario[3]%>"><%=DatosUsuario[3]%></option>
								</select>
							</div>
						</div>
                                                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

							<div class="form-group">
								<label  for="Nombre">N&uacute;mero de Documento</label>
                                                                <input name="No_Documento" type="text" class="form-control" id="docum" data-rangelength="[6,30]" data-notblank="true" data-required="true" value="<%=DatosUsuario[4]%>" readonly/>
							</div>
						</div>
                                                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

							<div class="form-group">
								<label for="Telefono">Tel&eacute;fono</label>
                                                                <input name="Telefono" class="form-control" type="text" placeholder="000 00 00" data-type="phone" data-notblank="true" data-rangelength="[9,9]" data-required="true" value="<%=DatosUsuario[6]%>" readonly>
							</div> 
						</div>
						

						

					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Nombre">Celular</label>
                                                                <input name="Celular" type="text" class="form-control" id="Hora" placeholder="000 000 0000" data-rangelength="[12,14]" data-type="cellphone" data-notblank="true" data-required="true" value="<%=DatosUsuario[7]%>" readonly/>
							</div>
						</div>
                                                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Nombre">Correo</label>
                                                                <input name="Correo" type="text" class="form-control" id="Direccion" placeholder="example@service.com" data-required="true" data-notblank="true" data-type="email" data-rangelength="[10,100]" value="<%=DatosUsuario[8]%>" readonly/>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

							<div class="form-group">
								<label for="Direccion">Direcci&oacute;n</label>
								<div class="form-group">
                                                                    <input name="Direccion" class="form-control" type="text" data-notblank="true" data-rangelength="[10,100]" data-required="true" value="<%=DatosUsuario[9]%>" readonly/>
								</div>
							</div>

						</div>
					</div>
                                        
                                        <div class="row">
                                                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4"></div>
                                                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

							<div class="form-group">
								<label for="Estado">Estado</label>
								<div class="form-group">
                                                                    <select name="Estado" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                                                        <option value="<%=DatosUsuario[10]%>"><%=DatosUsuario[10]%></option>
								</select>
								</div>
							</div>

						</div> 
                                                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4"></div>
                                        </div>
				</form>
			</div>
		</div>
			<div class="container marketing">
				<hr class="featurette-divider">
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
    <script type="text/javascript" src="../Libs/Customs/DataTables/js/jquery.dataTables.js" charset="UTF-8"></script>
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

