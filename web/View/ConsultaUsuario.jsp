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
String[][] ListaUsuario = usu.BuscarDatosUsuariosTodos();
String[][] ListaTipoUsuario = usu.BuscarDatosTipoUsuariosTodos();
String[][] ListaDepartamento = usu.BuscarDatosDepartamentoTodos();
String[][] ListaCiudad = usu.BuscarDatosCuidadTodos();
if(session.getAttribute("TipoMensaje").equals("Aprobar"))
{
    session.setAttribute("TipoMensaje", "Dio");
}
else if (session.getAttribute("TipoMensaje").equals("AprobarNO"))
{
    session.setAttribute("TipoMensaje", "NODio");
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
				<a href="index1.html">Inicio</a> <span class="glyphicon glyphicon-share-alt"></span>Registro y Consulta de Usuario
			</div>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12">
			<br/>
			<h1 class="Center">Registro y Consulta de Usuarios</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="col-md-12">
				<div class="tabbable" id="tabs-158836">
					<ul class="nav nav-tabs">
						<li class="active">
							<a href="#panel-Consultar" data-toggle="tab">Consultar</a>
						</li>
						<li >
							<a href="#panel-Registro" data-toggle="tab">Registro</a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-Consultar">
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
						<div class="tab-pane" id="panel-Registro">

							<form id="search" data-validate="parsley" method="post" action="/TriggerEvent/Contr_Usuarios">



								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="form-group">
											<label for="Nombre">Nombre</label>
											<input name="Nombre" type="text" class="form-control" id="nombre" data-rangelength="[3,100]" data-notblank="true" data-required="true"/>
										</div>
									</div>

									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="form-group">
											<label for="Nombre">Celular</label>
											<input name="Celular" type="text" class="form-control" id="Hora" placeholder="000 000 0000" data-rangelength="[12,14]" data-type="cellphone" data-notblank="true" data-required="true" />
										</div>
									</div>

									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="form-group">
											<label for="Nombre">Correo</label>
											<input name="Correo" type="text" class="form-control" id="Direccion" placeholder="example@service.com" data-required="true" data-notblank="true" data-type="email" data-rangelength="[10,100]" />
										</div>
									</div>
								</div>
								<div class="row">

									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="form-group">
											<label for="Tipo">Tipo Usuario</label>
											<select name="Tipo_Usuario" id="Tipos" class="form-control" data-required="true">
												<%for(String[] Row : ListaTipoUsuario){%>
                                                                                                <option value="<%=Row[0]%>"><%=Row[1]%></option>
                                                                                                <%}%>
											</select>
										</div>
									</div>

									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<div class="form-group">
											<label for="Imagen">Tipo Documento</label>
											<select name="Tipo_Documento" tabindex="1" data-placeholder="" class="form-control" data-required="true">
												<option value="Cédula de Ciudadanía">C&eacute;dula de Ciudadan&iacute;a</option>
												<option value="Tarjeta de Identidad">Tarjeta de Identidad</option>
												<option value="Cédula de Extranjería<">C&eacute;dula de Extranjer&iacute;a</option>
												<option value="Pasaporte">Pasaporte</option>
												<option value="DNI">Documento Nacional de Identificaci&oacute;n</option>
												<option value="NIT">N&uacute;mero de Identificaci&oacute;n Tributaria</option>
											</select>
										</div>
									</div>

									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

										<div class="form-group">
											<label  for="Nombre">N&uacute;mero de Documento</label>
											<input name="No_Documento" type="text" class="form-control" id="docum" data-rangelength="[6,30]" data-notblank="true" data-required="true"/>
										</div>
									</div>

								</div>
								<div class="row">
									<div class=" col-xs-12 col-sm-12 col-md-4 col-lg-4">

										<div class="form-group">
											<label for="Telefono">Tel&eacute;fono</label>
											<input name="Telefono" class="form-control" type="text" placeholder="000 00 00" data-type="phone" data-notblank="true" data-rangelength="[9,9]" data-required="true">
										</div> 
									</div>

									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

										<div class="form-group">
											<label for="Departamento">Departamento</label>
											<select id="departamento" name="Departametno" tabindex="1" data-placeholder="" class="form-control" data-required="true">
											</select>
										</div>

									</div>
                                                                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

										<div class="form-group">
											<label for="Ciudad">Ciudad</label>
											<select id="ciudad" name="Ciudad" tabindex="1" data-placeholder="" class="form-control" data-required="true">
											</select>
										</div>

									</div>
								</div>

								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label for="Password">Contrase&ntilde;a</label>
											<input name="Password" class="form-control" type="password" data-notblank="true" data-rangelength="[6,30]" data-required="true">
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label for="REPassword">Repita la Contrase&ntilde;a</label>
											<input name="REPassword" class="form-control" type="password" data-notblank="true" data-rangelength="[6,30]" data-required="true">
										</div>
									</div>
                                                                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

										<div class="form-group">
											<label for="Direccion">Direcci&oacute;n</label>
											<div class="form-group">
												<input name="Direccion" class="form-control" type="text" data-notblank="true" data-rangelength="[10,100]" data-required="true">
											</div>
										</div>

									</div>
								</div>
								<div class="row">
									<div class="col-md-4">

									</div>
									<div class="col-md-4">
										<div class="form-group">

											<button name="RegistrarTodoUsuario" type="submit" class="btn btn-block defecto" id="RegistrarUsuario">Registrar</button>

										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">

										</div>
									</div>
									<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">

									</div>

								</div>
							</form>
						</div>
					</div>
				</div>

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
    <script>
        (function($) {

            $.fn.changeType = function(){
                var data;
            data = [
            <%
                for(String[] Row :ListaCiudad)
                {%>
                    {"codigo":"<%=Row[0]%>", "nombre":"<%=Row[1]%>", "codigo_departamento":"<%=Row[2]%>", "departamento":"<%=Row[3]%>"},
                    
                <%}
            %>
                    {"codigo":"", "nombre":"","codigo_departamento":"","departamento":""}
                    ];
            var datadep = [
            <%
                for(String[] Row :ListaDepartamento)
                {%>
                    {"codigo":"<%=Row[0]%>", "nombre":"<%=Row[1]%>"},
                    
                <%}
            %>
                    {"codigo":"", "nombre":""}
                    ];
                    var options_departments = "<option value=''></option>";
                    $.each(datadep, function(i,d){
                            options_departments += '<option value="' + d.codigo + '">' + d.nombre + '<\/option>';
                    });
                    $("select#departamento", this).html(options_departments);
                    $("select#departamento", this).change(function(){
                    var index = $(this).val();
                    var options = '';
                    $.each(data, function(i,c){
                        if(c.codigo_departamento === index)
                        {
                            options += '<option value="' + c.codigo + '">' + c.nombre + '<\/option>';
                        }
                    });
                    $("select#ciudad").html(options);
		});
};
})(jQuery);
    </script>
    <script type="text/javascript">
    $(document).ready(function() {
        $("form#search").changeType();
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

