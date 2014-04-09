<%-- 
    Document   : RUsuario
    Created on : 28-feb-2014, 15:14:34
    Author     : Sanser
    --%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
    <%@include file="../WEB-INF/jspf/ValidacionGeneral.jspf" %>
    <%
String Tipo = (String)  session.getAttribute("Tipo_Documento");
String Documento = (String)session.getAttribute("No_Documento");
String Telefono = (String) session.getAttribute("Telefono");
String Celular = (String) session.getAttribute("Celular");
String Correo = (String) session.getAttribute("Correo");
String Direccion = (String) session.getAttribute("Direccion");
String Ciudad = (String) session.getAttribute("Ciudad");
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
                                                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Ciudad">Ciudad</label>
								<input type="text" class="form-control" id="nombre" data-rangelength="[3,100]" data-notblank="true" data-required="true" value="<%=Ciudad%>" readonly/>
							</div>
						</div>
						<div class=" col-xs-12 col-sm-12 col-md-4 col-lg-4">

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
                                                    <div class="form-group">
							<a href="ModificarPerfil.jsp" class="btn btn-primary btn-block">Modificar Perfil</a>
                                                    </div>
						</div>
					</div>
				

			</form>
                        </div>
                        <div class="row">
                                <div class="col-md-4">
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <a id="modal-Modificar" href="#modal-container-Modificar" role="button" class="btn btn-block btn-primary" data-toggle="modal">Cambiar contrase&ntilde;a</a>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                </div>
                        </div>
                        <div class="row">
				<form data-validate="parsley" method="post" action="/TriggerEvent/Contr_Usuarios">
					<div class="col-md-12">
						<div class="modal fade" id="modal-container-Modificar" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
										<h4 class="modal-title" id="myModalLabel">
                                                                                    <center>Cambiar Contraseña</center>
										</h4>
									</div>
									<div class="modal-body">
                                                                                <div class="row">
											<div class="col-md-2"></div>
											<div class="col-md-8">
												<div class="form-group">
                                                                                                    <label for="ContraseniaActual">Contrase&ntilde;a actual</label>
													<input id="ContraseniaActual" name="ContraseniaActual" class="form-control" type="password" data-notblank="true" data-rangelength="[6,30]" data-required="true" data-type="contrasenia">
												</div>
											</div>
                                                                                        <div class="col-md-2">
                                                                                            
                                                                                        </div>
										</div>
										<div class="row">
											<div class="col-md-2"></div>
											<div class="col-md-8">
												<div class="form-group">
                                                                                                    <label for="ContraseniaNueva">Contrase&ntilde;a nueva</label>
													<input id="ContraseniaNueva" name="ContraseniaNueva" class="form-control" type="password" data-notblank="true" data-rangelength="[6,30]" data-required="true" data-type="contrasenia">
												</div>
											</div>
                                                                                        <div class="col-md-2">
                                                                                            
                                                                                        </div>
										</div>
                                                                                <div class="row">
                                                                                    <div class="col-md-2"></div>
                                                                                    <div class="col-md-8">
                                                                                        <div class="form-group">
                                                                                            <div class="form-group">
                                                                                                <label for="ContraseniaRepetir">Repetir contrase&ntilde;a nueva</label>
                                                                                                    <input id="ContraseniaRepetir" name="ContraseniaRepetir" class="form-control" type="password" data-notblank="true" data-rangelength="[6,30]" data-required="true" data-type="contrasenia">
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="col-md-2"></div>
                                                                                </div>
                                                                            
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                                                                                <button name="CambiarContrasenia" type="submit" class="btn btn-primary">Cambiar</button>
									</div>
								</div>
								
							</div>
							
						</div>
					</div>
				</form>
			</div>
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
      <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
      <%session.setAttribute("Mensaje", "");%>
  </body>
  </html>
