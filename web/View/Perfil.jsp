<%-- 
    Document   : RUsuario
    Created on : 28-feb-2014, 15:14:34
    Author     : Sanser
    --%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
String Departamento = (String) session.getAttribute("Departamento");
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
	<div class="container">
            <br/>
            <br/>
            <br/>
            <div class="row">
            <div class="col-xs-12">
                <ol class="breadcrumb">
                    <li><a href="index.jsp">Inicio</a></li>
                    <li class="active">Mi perfil</a></li>
                </ol>
            </div>
        </div>
            <div class="row clearfix">
                <div class="col-md-12">
                        <h1 class="Center">Mi perfil</h1>
                </div>
            </div>
	
            <div class="row">
                <div class="col-xs-1"></div>
                <div class="col-xs-10">
                    <div class="row perfil-contenido contenido-borde">

                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            <center><img src="../Libs/Customs/images/userapplication.png" class="img-perfil"/></center>
                            <h3 class="nombre-usuario"><%=Nombre%></h3>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8 datos-personales">
                            <div class="row primer-dato">
                                <div class="col-xs-10 col-xs-offset-1 col-sm-5">
                                    <div class="datos-perfil">
                                        <label for="Tipo_Documento">Tipo de documento: </label>
                                    </div>
                                </div>
                                <div class="col-xs-10 col-xs-offset-1 col-sm-5 contenido-dos">
                                    <div class="datos-perfil">
                                        <label for="Tipo_Documento_Usuario"><%=Tipo%></label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1 col-sm-5">
                                    <div class="datos-perfil">
                                        <label for="No_Documento">N&uacute;mero de documento: </label>
                                    </div>
                                </div>
                                <div class="col-xs-10 col-xs-offset-1 col-sm-5 contenido-dos">
                                    <div class="datos-perfil">
                                        <label for="No_Documento_Usuario"><%=Documento%></label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1 col-sm-5">
                                    <div class="datos-perfil">
                                        <label for="Tipo_USuario">Tipo de usuario: </label>
                                    </div>
                                </div>
                                <div class="col-xs-10 col-xs-offset-1 col-sm-5 contenido-dos">
                                    <div class="datos-perfil">
                                        <label for="Tipo_USuario_Usuario"><%=Rol%></label>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1 col-sm-5">
                                    <div class="datos-perfil">
                                        <label for="No_Celular">N&uacute;mero de Celular: </label>
                                    </div>
                                </div>
                                <div class="col-xs-10 col-xs-offset-1 col-sm-5 contenido-dos">
                                    <div class="datos-perfil">
                                        <label for="No_Celular_USuario"><%=Celular%></label>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1 col-sm-5">
                                    <div class="datos-perfil">
                                        <label for="No_Telefono">N&uacute;mero de tel&eacute;fono: </label>
                                    </div>
                                </div>
                                <div class="col-xs-10 col-xs-offset-1 col-sm-5 contenido-dos">
                                    <div class="datos-perfil">
                                        <label for="No_Telefono_Usuario"><%=Telefono%></label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1 col-sm-5">
                                    <div class="datos-perfil">
                                        <label for="Correo_Electronico">Correo electr&oacute;nico</label>
                                    </div>
                                </div>
                                <div class="col-xs-10 col-xs-offset-1 col-sm-5 contenido-dos">
                                    <div class="datos-perfil">
                                        <label for="Correo_Electronico_Usuario"><%=Correo%></label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1 col-sm-5">
                                    <div class="datos-perfil">
                                        <label for="Departamento">Departamento: </label>
                                    </div>
                                </div>
                                <div class="col-xs-10 col-xs-offset-1 col-sm-5 contenido-dos">
                                    <div class="datos-perfil">
                                        <label for="Departamento_USuario"><%=Departamento%></label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1 col-sm-5">
                                    <div class="datos-perfil">
                                        <label for="Ciudad">Ciudad: </label>
                                    </div>
                                </div>
                                <div class="col-xs-10 col-xs-offset-1 col-sm-5 contenido-dos">
                                    <div class="datos-perfil">
                                        <label for="Ciudad_USuario"><%=Ciudad%></label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1 col-sm-5">
                                    <div class="datos-perfil">
                                        <label for="Direccion">Direcci&oacute;n: </label>
                                    </div>
                                </div>
                                <div class="col-xs-10 col-xs-offset-1 col-sm-5 contenido-dos">
                                    <div class="datos-perfil">
                                        <label for="Direccion_USuario"><%=Direccion%></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-1"></div>
                    </div>
                </div>
                <div class="col-xs-1"></div>
            </div>
            <br/>
            <div class="row">
                <div class="col-xs-10 col-xs-offset-1 col-sm-4 col-sm-offset-2 col-md-3 col-md-offset-3">
                    <div class="form-group">
                        <a id="modal-Modificar" href="#modal-container-Modificar" role="button" class="btn btn-block defecto pull-right" data-toggle="modal">Cambiar contrase&ntilde;a</a>
                    </div>
                </div>
                <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-3">
                    <div class="form-group">
                        <a href="ModificarPerfil.jsp" class="btn defecto btn-block">Modificar Perfil</a>
                    </div>
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
                                            <center>Cambiar contraseña</center>
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2">
                                                <div class="form-group">
                                                    <label for="ContraseniaActual">Contrase&ntilde;a actual</label>
                                                    <input id="ContraseniaActual" name="ContraseniaActual" class="form-control" type="password" data-notblank="true" data-rangelength="[6,30]" data-required="true" data-type="contrasenia">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2">
                                                <div class="form-group">
                                                    <label for="ContraseniaNueva">Contrase&ntilde;a nueva</label>
                                                    <input id="ContraseniaNueva" name="ContraseniaNueva" class="form-control" type="password" data-notblank="true" data-rangelength="[6,30]" data-required="true" data-type="contrasenia">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2">
                                                <div class="form-group">
                                                    <div class="form-group">
                                                        <label for="ContraseniaRepetir">Repetir contrase&ntilde;a nueva</label>
                                                        <input id="ContraseniaRepetir" name="ContraseniaRepetir" class="form-control" type="password" data-notblank="true" data-rangelength="[6,30]" data-required="true" data-type="contrasenia">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                                            <button name="CambiarContrasenia" type="submit" class="btn defecto">Cambiar</button>
                                        </div>
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
      <script>
      new gnMenu( document.getElementById( 'gn-menu' ) );
      </script>
      <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
      <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
      <%session.setAttribute("Mensaje", "");%>
  </body>
  </html>
