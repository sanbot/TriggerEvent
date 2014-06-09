<%-- 
    Document   : index
    Created on : 28-feb-2014, 15:10:24
    Author     : Sanser
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%
    if (!Rol.equals("") && !Rol.equals(null)) {
        response.sendRedirect("EventoRecomendado.jsp");
    }
%>
<!DOCTYPE html>
<html lang="en" class="no-js">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Trigger Event</title>
        <meta name="description" content="Eventos musicales" />
        <meta name="keywords" content="Eventos, musical, Trigger Event" />
        <meta name="author" content="Sanser Soft" />
        <link rel="shortcut icon" href="../Libs/Customs/images/logoteazul.ico"/>
        <%@include file="../WEB-INF/jspf/EstilosCSS.jspf" %>
        <link rel="stylesheet" type="text/css" href="../Libs/Customs/css/style.css" />
        <link rel="stylesheet" type="text/css" href="../Libs/Customs/css/guidely.css" />
        <link href="../Libs/Customs/css/validacionindex.css" rel="stylesheet" type="text/css"/>
        <script src="../Libs/Customs/js/modernizr.custom.js" type="text/javascript"></script>
    </head>
    <body>
        <%@include file="../WEB-INF/jspf/MenuCustom.jspf" %>
        <div id="body" class="container" style="width: 100%;">
            <br/>
            <br/>
            <br/>
            <div class="row visible-md visible-lg">
                <div class="col-xs-8 col-xs-offset-2 col-md-offset-0 col-md-4">
                    <label id="imagen1" class="control-label Justify">
                        <h1 class="Center">EVENTOS AL ALCANCE DE SU MANO</h1>
                        Tendencias musicales en cualquier dispositivo, actualizamos sus eventos diariamente, promocione su "evento" f&aacute;cilmente.
                    </label>
                    <div class="google-play-disponible">
                        <img src="../Libs/Customs/images/google_play_icon.png" alt="Disponible en google play" height="50" />
                    </div>
                </div>
                <div class="col-xs-8 col-xs-offset-2 col-md-offset-0 col-md-4">
                    <center><img id="imagen" class="img-responsive" src="../Libs/Customs/images/logoteblanco.png"/></center>
                </div>
                <div class="col-xs-8 col-xs-offset-2 col-md-offset-0 col-md-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <span class="glyphicon glyphicon-lock"></span> Inicio de sesi&oacute;n 
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" data-validate="parsley" method="post" action="/TriggerEvent/Contr_Usuarios">
                                <div class="form-group">
                                    <label for="IndexCorreo" class="col-sm-3 control-label" >
                                        Correo
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="email" class="form-control" id="txtCorreoInicio1"  name="correo" placeholder="e-mail" data-notblank="true" data-required="true" data-maxlength="100" autofocus>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="IndexContrasenia" class="col-sm-3 control-label">
                                        Contrase&ntilde;a
                                    </label>
                                    <div class="col-sm-9" id="target-1">
                                        <input type="password" class="form-control" id="txtPassWordInicio" name="contrasenia" placeholder="password" data-required="true">
                                    </div>
                                </div>
                                <div class="form-group" >
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox"/>
                                                Recordarme
                                            </label>
                                        </div>
                                        <a id="modal-Olvide" href="#modal-container-Olvide" role="button" data-toggle="modal">Olvid&eacute; la contrase&ntilde;a</a>
                                    </div>
                                </div>
                                <div class="form-group last" >
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <a id="loginingresar" class="btn btn-success btn-sm" >
                                            Ingresar
                                        </a>
                                        <button type="reset" class="btn btn-default btn-sm" id="target-2">
                                            Limpiar
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="panel-footer" >
                            ¿No está registrado? 
                            <a href="RegistrarUsuario.jsp">Regístrese aquí</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row visible-xs visible-sm">
                <div class="col-sm-8 col-sm-offset-2 col-md-offset-0 col-md-4">
                    <div class="panel panel-default panel-sm-xs">
                        <div class="panel-heading">
                            <span class="glyphicon glyphicon-lock"></span> Inicio de sesi&oacute;n
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" data-validate="parsley" method="post" action="/TriggerEvent/Contr_Usuarios">
                                <div class="form-group">
                                    <label for="IndexCorreo" class="col-sm-3 control-label" >
                                        Correo
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="email" class="form-control" id="txtCorreoInicio1-sm" name="correo" placeholder="e-mail" data-notblank="true" data-required="true" data-maxlength="100" autofocus>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="IndexContrasenia" class="col-sm-3 control-label">
                                        Contrase&ntilde;a
                                    </label>
                                    <div class="col-sm-9" id="target-2">
                                        <input type="password" class="form-control" id="txtPassWordInicio-sm" name="contrasenia" placeholder="password" data-notblank="true" data-required="true" data-rangelength="[6,30]">
                                    </div>
                                </div>
                                <div class="form-group" >
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" data-group="signup"/>
                                                Recordarme
                                            </label>
                                        </div>
                                        <a id="modal-Olvide" href="#modal-container-Olvide" role="button" data-toggle="modal">Olvid&eacute; la contrase&ntilde;a</a>
                                    </div>
                                </div>
                                <div class="form-group last" >
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <a id="loginingresarsm" class="btn btn-success btn-sm" >
                                            Ingresar
                                        </a>
                                        <button type="reset" class="btn btn-default btn-sm">
                                            Limpiar
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="panel-footer" >
                            ¿No está registrado? 
                            <a href="RegistrarUsuario.jsp">Regístrese aquí</a>
                        </div>
                    </div>
                </div>            
                <div class="col-sm-8 col-sm-offset-2 col-md-offset-0 col-md-4">
                    <center><img id="imagen-sm-xs" class="img-responsive" src="../Libs/Customs/images/logoteblanco.png"/></center>
                </div>
                <div class="col-sm-8 col-sm-offset-2 col-md-offset-0 col-md-4">
                    <label id="imagen1-sm-xs" class="control-label Justify">
                        <h1 class="Center">EVENTOS AL ALCANCE DE SU MANO</h1>
                        Tendencias musicales en cualquier dispositivo, actualizamos sus eventos diariamente, promocione su "evento" f&aacute;cilmente.
                    </label>
                    <div class="google-play-disponible">
                        <img src="../Libs/Customs/images/google_play_icon.png" alt="Disponible en google play" height="50" />
                    </div>
                    <br/>
                </div>
            </div>
        </div>
        <div class="container">
            <br/>
            <div class="row clearfix" >
                <div id="contenido-eventos-destacados" class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-0">
                    <div class="panel panel-primary" id="target-3">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                LO M&Aacute;S DESTACADO
                            </h3>
                        </div>
                    </div>

                </div>
                <div id="contenido-eventos-comentados"  class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-0">
                    <div class="panel panel-primary" id="target-5">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                LOS M&Aacute;S COMENTADOS
                            </h3>
                        </div>
                    </div>
                </div>
                <div id="contenido-eventos-proximos" class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-0">
                    <div class="panel panel-primary" id="target-6">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                PR&Oacute;XIMOS EVENTOS
                            </h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container" style="width: 100%; margin-top: 2%;">
            <div class="row clearfix">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2">
                    <!-- /widget -->
                    <div class="widget" id="target-7">
                        <div class="widget-header">
                            <h3>Algunos comentarios de nuestros usuarios</h3>
                        </div>
                        <!-- /widget-header -->
                        <div class="widget-content">
                            <ul id="comentarios-aleatorios"  class="messages_layout">

                            </ul>
                        </div>
                        <!-- /widget-content --> 
                    </div>
                    <!-- /widget --> 
                </div>
            </div>
            <div class="modal fade" id="modal-container-Olvide" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title" id="myModalLabel">
                                <center>Olvid&eacute; la contrase&ntilde;a</center>
                            </h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" data-validate="parsley" method="post" action="/TriggerEvent/Contr_Usuarios">
                                <div class="row">
                                    <input type="hidden" id="codigoeventomodal"/>
                                </div>
                                <div class="row">
                                    <div class="col-xs-offset-2 col-xs-8">
                                        <div class="form-group">
                                            <label for="CorreoRecordar">
                                                Correo electr&oacute;nico
                                            </label>
                                            <input type="email" class="form-control" id="txtemailInicio" name="correo" placeholder="correo" data-notblank="true" data-required="true" data-maxlength="100" autofocus>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-xs-offset-4 col-xs-4">
                                            <button type="submit" name="recucontrasenia" class="btn defecto btn-block" >
                                                Enviar
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="Modal-Login" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title" id="myModalLabel">
                                Inicio de session
                            </h4>
                        </div>
                        <div class="modal-body" id="body">
                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1">
                                    <div class="panel panel-default modal-login">
                                        <div class="panel-heading">
                                            <span class="glyphicon glyphicon-lock"></span> Inicio de sesi&oacute;n
                                        </div>
                                        <div class="panel-body">
                                            <form id="formulario-modal-login" class="form-horizontal" data-validate="parsley">
                                                <div class="form-group">
                                                    <label for="Correo" class="col-sm-3 control-label" >
                                                        Correo
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <input type="email" class="form-control" id="txtCorreoModal" name="correo" placeholder="e-mail" data-notblank="true" data-required="true" data-maxlength="100" autofocus>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="Contrasenia" class="col-sm-3 control-label">
                                                        Contrase&ntilde;a
                                                    </label>
                                                    <div class="col-sm-9" id="target-2">
                                                        <input type="password" class="form-control" id="txtPasswordModal" name="contrasenia" placeholder="password" data-required="true" data-rangelength="[3,30]">
                                                    </div>
                                                </div>
                                                <div class="form-group" >
                                                    <div class="col-sm-offset-3 col-sm-9">
                                                        <div class="checkbox">
                                                            <label>
                                                                <input type="checkbox"/>
                                                                Recordarme
                                                            </label>
                                                        </div>
                                                        <a id="modal-oldive" href="#modal-container-Olvide" role="button" data-toggle="modal">Olvid&eacute; la contrase&ntilde;a</a>
                                                    </div>
                                                </div>
                                                <div class="form-group last" >
                                                    <div class="col-sm-offset-3 col-sm-9">
                                                        <a id="btn-login" class="btn btn-success btn-sm" >
                                                            Ingresar
                                                        </a>
                                                        <button type="reset" class="btn btn-default btn-sm">
                                                            Limpiar
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="panel-footer" >
                                            ¿No está registrado? 
                                            <a href="RegistrarUsuario.jsp">Regístrese aquí</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
        <script src="../Libs/Bootstrap/js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="../Libs/Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../Libs/Customs/js/Parsley.js" type="text/javascript"></script>
        <script src="../Libs/Customs/js/classie.js" type="text/javascript"></script>
        <script src="../Libs/Customs/js/gnmenu.js" type="text/javascript"></script>
        <!--Pines Notify -->
        <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
        <script src="../Libs/Customs/js/guidely.min.js" type="text/javascript"></script>
        <script src="../Libs/Customs/js/View/index.js" type="text/javascript"></script>
        <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
        <%session.setAttribute("Mensaje", "");%>
    </body>
</html>