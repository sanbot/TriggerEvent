<%-- 
    Document   : RUsuario
    Created on : 28-feb-2014, 15:14:34
    Author     : Sanser
    --%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%
    String mensaje = "";
    if(session.getAttribute("Mensaje") != null)
    	{
    mensaje = (String)session.getAttribute("Mensaje");
}
if(session.getAttribute("Rol") == null)
	{
response.sendRedirect("index.jsp");
}
String Nombre = (String)session.getAttribute("Nombre");
String Rol = (String)session.getAttribute("Rol");
String Tipo = (String)  session.getAttribute("Tipo_Documento");
String Documento = (String)session.getAttribute("No_Documento");
String Telefono = (String) session.getAttribute("Telefono");
String Celular = (String) session.getAttribute("Celular");
String Correo = (String) session.getAttribute("Correo");
String Direccion = (String) session.getAttribute("Direccion");
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
	<title>Trigger Event</title>
	
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
								<a href="index.jsp" class="gn-icon gn-icon-download">
									<div class="negro">Inicio</div>
								</a>
							</li>
							<%if(Rol!=null){%>
							<li>
								<a href="Perfil.jsp" class="gn-icon gn-icon-earth">
									<div class="negro"><%=Nombre%></div>
								</a>
							</li>
							<%}%>
							<%if(Rol =="Administrador"){%>
							<li>
								<a href="ConsultaUsuario.jsp" class="gn-icon gn-icon-earth">
									<div class="negro">Registrar y Consultar Usuario</div>
								</a>
							</li>
							<%}%>
							<%}%>
							<li>
								<a href="Nosotros.jsp" class="gn-icon gn-icon-earth">
									<div class="negro">
										Qui&eacute;nes Somos
									</div>
								</a>
							</li>
							<li>
								<a href="M_Sitio.jsp" class="gn-icon gn-icon-photoshop">
									<div class="negro">
										Mapa del Sitio
									</div>
								</a>
							</li>
							<li>
								<a href="#" class="gn-icon gn-icon-cog">
									<div class="negro">
										Ayuda en l&iacute;nea
									</div>
								</a>
							</li>
							<li>
								<a href="Contactenos.jsp" class="gn-icon gn-icon-help">
									<div class="negro">
										Cont&aacute;ctenos
									</div>
								</a>
							</li>
						</ul>
					</div><!-- /gn-scroller -->
				</nav>
			</li>
			<li><a href="index.jsp">Trigger Event</a></li>
			<%if(Rol!=null){%>
			<li class="pull-right"><a href="Salir.jsp">Cerrar Sesi&oacute;n</a></li>
			<%}%>
		</ul>
	</div><!-- /container -->
	<div class="container" style="margin-top: 5%;">
		<div class="row clearfix">
			<div class="col-md-12">
				<div class="form-group">
					<a href="index.jsp">Inicio</a> <span class="glyphicon glyphicon-share-alt"></span>Mi perfil
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12">

				<h1 class="Center">Mi perfil</h1>
				
				<form data-validate="parsley" role="form">

					<div class="row">

						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Tipo">Tipo Usuario</label>
								<input type="text" class="form-control" id="docum" data-rangelength="[6,30]" data-notblank="true" data-required="true" readonly value="<%=Rol%>"/>
							</div>
						</div>

						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Imagen">Tipo Documento</label>
								<input type="text" class="form-control" id="docum" data-rangelength="[6,30]" data-notblank="true" data-required="true" readonly value="<%=Tipo%>"/>
							</div>
						</div>

						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

							<div class="form-group">
								<label for="Nombre">N&uacute;mero de Documento</label>
								<input type="text" class="form-control" id="docum" data-rangelength="[6,30]" data-notblank="true" data-required="true" value="<%=Documento%>" readonly/>
							</div>
						</div>

					</div>

					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Nombre">Nombre</label>
								<input type="text" class="form-control" id="nombre" data-rangelength="[3,100]" data-notblank="true" data-required="true" value="<%=Nombre%>" readonly/>
							</div>
						</div>

						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Nombre">Celular</label>
								<input type="text" class="form-control" id="Hora" placeholder="000 000 0000" data-rangelength="[13,15]" data-type="cellphone" data-notblank="true" data-required="true" value="<%=Celular%>" readonly/>
							</div>
						</div>

						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Nombre">Correo</label>
								<input type="text" class="form-control" id="Direccion" placeholder="example@service.com" data-required="true" data-notblank="true" data-type="email" data-rangelength="[10,100]" value="<%=Correo%>" readonly/>
							</div>
						</div>

					</div>

				</form>

				<form data-validate="parsley" role="form">

					<div class="row">

						<div class="col-xs-offset-3 col-xs-12 col-sm-12 col-md-4 col-lg-4">

							<div class="form-group">
								<label for="Tipo">Tel&eacute;fono</label>
								<input class="form-control" type="text" placeholder="000 00 00" data-type="phone" data-notblank="true" data-rangelength="[9,9]" data-required="true" value="<%=Telefono%>" readonly/>
							</div> 
						</div>

						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

							<div class="form-group">
								<label for="Imagen">Direcci&oacute;n</label>
								<div class="form-group">
									<input class="form-control" type="text" data-notblank="true" data-rangelength="[10,100]" data-required="true" value="<%=Direccion%>" readonly/>
								</div>
							</div>

						</div>

					</div>
					<div class="row">
						<div class="col-md-offset-4 col-md-4">
							<a href="ModificarPerfil.jsp" class="btn btn-primary btn-block">Modificar Perfil</a>
						</div>
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
      <%if(mensaje.equals("PerfilModificado")){%>
      <script type="text/javascript">
      $(document).ready(function(){
      	alertify.success("Sus datos han sido modificados correctamente.");
      });
      </script>
      <%} 
      session.setAttribute("Mensaje", "");%>
  </body>
  </html>
