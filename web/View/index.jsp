<%-- 
    Document   : index
    Created on : 28-feb-2014, 15:10:24
    Author     : Sanser
    --%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar" %>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%
    Contr_Consultar usu = new Contr_Consultar();
    String[][] ListaEventos = usu.getBuscarDatosEventosProximos();
    String[][] ListaEventosDestacados = usu.getBuscarDatosEventosDestacados();
    String[][] ListaEventosComentados = usu.getBuscarDatosEventosComentado();
    String[][] Comentarios = usu.getBuscarComentariosAleatorios();
%>
<!DOCTYPE html>
<html lang="en" class="no-js">
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
    <link rel="stylesheet" type="text/css" href="../Libs/Customs/css/style.css" />
    <link rel="stylesheet" type="text/css" href="../Libs/Customs/css/guidely.css" />
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
    <%}else if(Rol.equals("") || Rol.equals(null)){%>
    <%@include file="../WEB-INF/jspf/Menu.jspf" %>
    <%}%>
    <%if(Rol == "" || Rol == null){%>
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
                                <label for="Correo" class="col-sm-3 control-label" >
                                    Correo
                                </label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="txtCorreoInicio1" name="correo" placeholder="e-mail" data-notblank="true" data-required="true" data-maxlength="100" autofocus>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="Contrasenia" class="col-sm-3 control-label">
                                    Contrase&ntilde;a
                                </label>
                                <div class="col-sm-9" id="target-2">
                                    <input type="password" class="form-control" id="txtPassWordInicio" name="contrasenia" placeholder="password" data-notblank="true" data-required="true" data-rangelength="[8,50]">
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
                                    <button type="submit" name="login" class="btn btn-success btn-sm" >
                                        Ingresar
                                    </button>
                                    <button type="reset" class="btn btn-default btn-sm" id="target-3">
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
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <span class="glyphicon glyphicon-lock"></span> Inicio de sesi&oacute;n
                    </div>
                    <div class="panel-body">
                        <form class="form-horizontal" data-validate="parsley" method="post" action="/TriggerEvent/Contr_Usuarios">
                            <div class="form-group">
                                <label for="Correo" class="col-sm-3 control-label" >
                                    Correo
                                </label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="txtCorreoInicio1" name="correo" placeholder="e-mail" data-notblank="true" data-required="true" data-maxlength="100" autofocus>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="Contrasenia" class="col-sm-3 control-label">
                                    Contrase&ntilde;a
                                </label>
                                <div class="col-sm-9" id="target-2">
                                    <input type="password" class="form-control" id="txtPassWordInicio" name="contrasenia" placeholder="password" data-notblank="true" data-required="true" data-rangelength="[8,50]">
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
                                    <button type="submit" name="login" class="btn btn-success btn-sm" >
                                        Ingresar
                                    </button>
                                    <button type="reset" class="btn btn-default btn-sm" id="target-3">
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
                <center><img id="imagen" class="img-responsive" src="../Libs/Customs/images/logoteblanco.png"/></center>
            </div>
            <div class="col-sm-8 col-sm-offset-2 col-md-offset-0 col-md-4">
                <label id="imagen1" class="control-label Justify">
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
    <%}%>
    <div class="container">
        <br/>
        <%if(Rol != "" && Rol != null){%>
        
        <br/>
        <br/>
        <%}%>
        <div class="row clearfix" >
            <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-0">
                <div class="panel panel-primary" id="target-4">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            LO M&Aacute;S DESTACADO
                        </h3>
                    </div>
                </div>
                <%
                for(String[] Row : ListaEventosDestacados)
                {%>
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <%=Row[1]%>
                                <%if(!Rol.equals(null)&&!Rol.equals("")){%><a href="DetalleEvento.jsp?CodigoEvento=<%=Row[0]%>"><span class="glyphicon glyphicon-log-in close aligncerar"></span></a><%}%>
                            </h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <center>
                                        <img src="ImagenEvento.jsp?Codigo=<%=Row[0]%>" class="img-thumbnail imgevento"/>
                                    </center>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <label for="Creador">Creador: <%=Row[3]%></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <label for="Ciudad">Ciudad: <%=Row[4]%></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <label for="Fecha">Fecha: <%=Row[2]%></label> <label for="Hora">Hora: <%=Row[6]%></label>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <label for="Calificacion">Calificaci&oacute;n: <%if(Row[5]==null){%>0<%}else{%><%=Row[5]%><%}%></label>
                        </div>
                    </div>
                <%}%>
            </div>
            <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-0">
                <div class="panel panel-primary" id="target-5">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            LOS M&Aacute;S COMENTADOS
                        </h3>
                    </div>
                </div>
                <%
                for(String[] Row : ListaEventosComentados)
                {%>
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <%=Row[1]%>
                                <%if(!Rol.equals(null)&&!Rol.equals("")){%><a href="DetalleEvento.jsp?CodigoEvento=<%=Row[0]%>"><span class="glyphicon glyphicon-log-in close  aligncerar"></span></a><%}%>
                            </h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <center><img src="ImagenEvento.jsp?Codigo=<%=Row[0]%>" class="img-thumbnail imgevento"/></center>
                                </div>
                            </div>
                             <div class="row">
                                <div class="col-md-12">
                                    <label for="Creador">Creador: <%=Row[3]%></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <label for="Ciudad">Ciudad: <%=Row[4]%></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <label for="Fecha">Fecha: <%=Row[2]%></label> <label for="Hora">Hora: <%=Row[6]%></label>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <label for="Calificacion">Comentarios: <%=Row[5]%></label>
                        </div>
                    </div>
                <%}%>
            </div>
            <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-0">
                <div class="panel panel-primary" id="target-6">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            PR&Oacute;XIMOS EVENTOS
                        </h3>
                    </div>
                </div>
                <%
                for(String[] Row :ListaEventos)
                {%>
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <%=Row[1]%>
                                <%if(!Rol.equals(null)&&!Rol.equals("")){%><a href="DetalleEvento.jsp?CodigoEvento=<%=Row[0]%>"><span class="glyphicon glyphicon-log-in close  aligncerar"></span></a><%}%>
                            </h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <center>
                                        <img src="ImagenEvento.jsp?Codigo=<%=Row[0]%>" class="img-thumbnail imgevento"/>
                                    </center>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <label for="Creador">Creador: <%=Row[3]%></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <label for="Ciudad">Ciudad: <%=Row[4]%></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <label for="Hora">Hora: <%=Row[5]%></label>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <label for="Fecha">Fecha: <%=Row[2]%></label>
                        </div>
                    </div>
                <%}%>
            </div>
        </div>
    </div>
    <div class="container" style="width: 100%; margin-top: 2%;">
        <div class="row clearfix">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2">
                <!-- /widget -->
                <div class="widget">
                    <div class="widget-header" id="target-7">
                        <h3>Algunos comentarios de nuestros usuarios</h3>
                    </div>
                    <!-- /widget-header -->
                    <div class="widget-content">
                        <ul class="messages_layout">
                            <%for (String[] Row : Comentarios)
                            {%>
                                <li class="from_user left">
                                    <div class="message_wrap"> 
                                        <span class="arrow"></span>
                                        <div class="info"> 
                                            <span class="name">Usuario: <%=Row[0]%>, Evento: <%=Row[1]%></span>
                                        </div>
                                        <div class="text"> 
                                            <%=Row[2]%>
                                        </div>
                                    </div>
                                </li>
                            <%}%>
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
                            Olvid&eacute; la contrase&ntilde;a.
                        </h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" data-validate="parsley" method="post" action="/TriggerEvent/Contr_Usuarios">
                            <div class="row">
                                <div class="col-xs-offset-2 col-xs-8">
                                    <div class="form-group">
                                        <label for="CorreoRecordar" class="col-sm-3 col-xs-4" >
                                            Correo
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
        <footer>
            <div class="row">
                <div class="col-xs-12">
                    <hr class="featurette-divider">
                    <p><center>&copy; 2014 Sergio Rivera Ballesteros, Santiago Botero Ru&iacute;z. Aprendices Tecn&oacute;logos en An&aacute;lisis y Desarrollo de Sistemas de Informaci&oacute;n, SENA CESGE regional Antioquia</center></p>
                </div>
            </div>
        </footer>
    </div>
<script src="../Libs/Bootstrap/js/jquery-1.10.2.min.js"></script>
<script src="../Libs/Bootstrap/js/bootstrap.min.js"></script>
<script src="../Libs/Bootstrap/js/holder.js"></script>
<script src="../Libs/Customs/js/classie.js"></script>
<script src="../Libs/Customs/js/gnmenu.js"></script>
<script>
   new gnMenu( document.getElementById( 'gn-menu' ) );
</script>
<!--Pines Notify -->
<script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>



<script src="../Libs/Customs/js/guidely.min.js"></script>
<script>

    
    $(function () {

        <%if(Rol.equals("") || Rol.equals(null)){%>
            guidely.add ({
                attachTo: '#target-2'
                , anchor: 'bottom-left'
                , title: 'Correo y contraseña'
                , text: 'Debe diligenciar completa y correctamente los campos que se muestran.'
            });
            
            guidely.add ({
                attachTo: '#target-3'
                , anchor: 'bottom-left'
                , title: 'Iniciar sesión o limpiar campos'
                , text: 'Puede, ya sea ingresar al aplicativo o limpiar los campos diligenciados.'
            });
            
            guidely.add ({
                attachTo: '#target-4'
                , anchor: 'bottom-left'
                , title: 'Lo más destacado'
                , text: 'Estos son los eventos con mejor calificación (dada por nuestros usuarios).'
            });
            
            guidely.add ({
                attachTo: '#target-5'
                , anchor: 'bottom-left'
                , title: 'Los más comentados'
                , text: 'Estos son los eventos más polémicos. Le damos la oportunidad a nuestros usuarios de expresar su opinión.'
            });
            
            guidely.add ({
                attachTo: '#target-6'
                , anchor: 'bottom-left'
                , title: 'Próximos eventos'
                , text: 'Estos son los eventos más cercanos con respecto al día actual.'
            });
            
            guidely.add ({
                attachTo: '#target-7'
                , anchor: 'bottom-right'
                , title: 'Algunos comentarios'
                , text: 'En Trigger Event, tenemos en cuenta los comentarios de nuestros usuarios. Estos son algunos de ellos.'
            });
            
        <%}else{%>
            guidely.add ({
                attachTo: '#target-4'
                , anchor: 'bottom-left'
                , title: 'Lo más destacado'
                , text: 'Estos son los eventos con mejor calificación (dada por nuestros usuarios).'
            });
            
            guidely.add ({
                attachTo: '#target-5'
                , anchor: 'bottom-left'
                , title: 'Los más comentados'
                , text: 'Estos son los eventos más polémicos. Le damos la oportunidad a nuestros usuarios de expresar su opinión.'
            });
            
            guidely.add ({
                attachTo: '#target-6'
                , anchor: 'bottom-left'
                , title: 'Próximos eventos'
                , text: 'Estos son los eventos más cercanos con respecto al día actual.'
            });
            
            guidely.add ({
                attachTo: '#target-7'
                , anchor: 'bottom-right'
                , title: 'Algunos comentarios'
                , text: 'En Trigger Event, tenemos en cuenta los comentarios de nuestros usuarios. Estos son algunos de ellos.'
            });
        <%}%>
        if(!localStorage.getItem("welcome"))
        {
            guidely.init ({ welcome: true, startTrigger: false });
            localStorage.setItem("welcome", true);
        }
    });

</script>
<script>
    $("#guia").click(function(){
        localStorage.removeItem("welcome");
        location.reload();
    });
</script>

<%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
<%session.setAttribute("Mensaje", "");%>
</body>
</html>