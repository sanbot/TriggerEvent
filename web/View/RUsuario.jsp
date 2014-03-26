<%-- 
    Document   : RegistrarUsuario
    Created on : 11/03/2014, 08:28:24 AM
    Author     : santi_000
    --%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%
    String mensaje = "";
    if(session.getAttribute("Mensaje") != null)
    	{
    mensaje = (String)session.getAttribute("Mensaje");
}
if(session.getAttribute("Rol") != null)
	{
response.sendRedirect("index.jsp");
}
String Nombre = (String)session.getAttribute("Nombre");
String Rol = (String)session.getAttribute("Rol");
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
	
	<script src="../Libs/Customs/js/modernizr.custom.js"></script>
	
</head>
<body>
	<div class="container">
		<ul id="gn-menu" class="gn-menu-main">
			<li class="gn-trigger">
				<a class="gn-icon gn-icon-menu"><span>Menu</span></a>
				<nav class="gn-menu-wrapper">
					<div class="gn-scroller">
						<ul class="gn-menu">
							<li class="gn-search-item">
								<a href="index1.html" class="gn-icon gn-icon-download"><div class="negro">Inicio</div></a>
							</li>
							<li><a class="gn-icon gn-icon-earth"><div class="negro">Qui&eacute;nes Somos</div></a></li>
							<li><a class="gn-icon gn-icon-photoshop"><div class="negro">Mapa del Sitio</div></a></li>
							<li><a class="gn-icon gn-icon-cog"><div class="negro">Ayuda en l&iacute;nea</div></a></li>
							<li><a class="gn-icon gn-icon-help"><div class="negro">Cont&aacute;ctenos</div></a></li>
						</ul>
					</div><!-- /gn-scroller -->
				</nav>
			</li>
			<li><a href="index1.html">Trigger Event</a></li>
                        <%if(Rol!=null){%>
			<li class="pull-right"><a href="Salir.jsp">Cerrar Sesi&oacute;n</a></li>
			<%}%>
		</ul>
	</div><!-- /container -->
	<div class="container">
            <br/>
            <br/>
            <br/>
            
		<div class="row clearfix">
			<div class="col-md-12">
				<div class="form-group">
					<a href="index1.html">Inicio</a> <span class="glyphicon glyphicon-share-alt"></span>Registro Usuario
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12">

				<h1 class="Center">REGISTRARSE</h1>
				
				<form data-validate="parsley" method="post" action="/TriggerEvent/Contr_Usuarios">

					

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
									<option value="Cliente">Cliente</option>
									<option value="Empresa">Empresa</option>
									<option value="Administrador">Administrador</option>
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
						<div class=" col-xs-offset-2 col-xs-12 col-sm-12 col-md-4 col-lg-4">

							<div class="form-group">
								<label for="Telefono">Tel&eacute;fono</label>
								<input name="Telefono" class="form-control" type="text" placeholder="000 00 00" data-type="phone" data-notblank="true" data-rangelength="[9,9]" data-required="true">
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
						<div class="col-md-offset-2 col-md-4">
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
				<div class="container marketing">
					<hr class="featurette-divider">
				</div>
				<!-- FOOTER -->
				<footer>
					<p>&copy; 2013 Trigger Event, Inc.</p>
				</footer>

			</div>
		</div>
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
    <script>
    new gnMenu( document.getElementById( 'gn-menu' ) );
    </script>
    
    <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
    <%if(mensaje.equals("RegistroDio")){%>
    <script type="text/javascript">
    $(document).ready(function(){
    	alertify.success("Los datos del usuario han sido registrados correctamente.");
    });
    </script>
    <%}%>
    <%if(mensaje.equals("RegistroNoDio")){%>
    <script type="text/javascript">
    $(document).ready(function(){
    	alertify.error("Ocurrió un problema inesperado al tratar de insertar los datos del usuario, por favor, inténtelo de nuevo.");
    });
    </script>
    <%}%>
    <%if(mensaje.equals("ErrorCodigo")){%>
    <script type="text/javascript">
    $(document).ready(function(){
    	alertify.error("El código de verificación no coincide con el enviado, por favor inténtelo de nuevo.");
    });
    </script>
    <%}%>
    <%if(mensaje.equals("ErroPass")){%>
    <script type="text/javascript">
    $(document).ready(function(){
    	alertify.error("Las Contraseñas no coinciden.");
    });
    </script>
    <%}
    session.setAttribute("Mensaje", "");%>
</body>
</html>

