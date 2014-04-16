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
    	<link rel="stylesheet" type="text/css" href="../Libs/Bootstrap/css/bootstrap-theme.min.css" />
    	<link rel="stylesheet" type="text/css" href="../Libs/Bootstrap/css/bootstrap.min.css" />
    	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/Default.css" />
    	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/normalize.css" />
    	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/demo.css" />
    	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/component.css" />
    	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/style.css" />
    	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/guidely.css" />
    	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/alertify.core.css" />
    	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/alertify.default.css" />
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
    <div id="body" class="container" style="width: 100%;">
    	<br/>
    	<br/>
    	<br/>
    	<div class="row">
    		<%if(Rol == ""){%>        
    		<div class="col-md-4">
    			<label id="imagen1" class="control-label Justify">
    				<h1 class="Center">EVENTOS AL ALCANCE DE TU MANO</h1>
    				Tendencias musicales en dispositivos móviles Android, informaci&oacute;n de eventos con gran antelaci&oacute;n, promociona tu empresa aqu&iacute;.
    				Ahora disponible en Google Play Store.
    			</label>
    		</div>
    		<div class="col-xs-offset-2 col-sm-offset-3 col-md-offset-0 col-md-4">
    			<img id="imagen" class="img-responsive" src="../Libs/Customs/images/logoteblanco.png">
    		</div>
    		<div class="col-md-4">
    			<div class="panel panel-default">
    				<div class="panel-heading">
    					<span class="glyphicon glyphicon-lock"></span> Login
    				</div>
    				<div class="panel-body">
    					<form class="form-horizontal" data-validate="parsley" method="post" action="/TriggerEvent/Contr_Usuarios">
    						<div class="form-group">
    							<label for="inputEmail3" class="col-sm-3 control-label" >
    								Email
    							</label>
    							<div class="col-sm-9">
    								<input type="email" class="form-control" id="inputEmail3" name="correo" placeholder="Email" data-notblank="true" data-required="true" data-maxlength="100" autofocus>
    							</div>
    						</div>
    						<div class="form-group" id="target-1">
    							<label for="inputPassword3" class="col-sm-3 control-label">
    								Contraseña
    							</label>
    							<div class="col-sm-9">
    								<input type="password" class="form-control" id="inputPassword3" name="contrasenia" placeholder="Contraseña" data-notblank="true" data-required="true" data-rangelength="[8,50]">
    							</div>
    						</div>
    						<div class="form-group" id="target-2">
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
    					¿No estás registrado? 
    					<a href="RegistrarUsuario.jsp">Regístrate aquí</a>
    				</div>
    			</div>
    		</div>
    		<%}%>
    	</div>
    </div>
    <div class="container">
    	<br/>
    	<div class="row clearfix">
    		<div class="col-md-4 column">
    			<div class="panel panel-primary">
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
    						<a href="DetalleEvento.jsp?CodigoEvento=<%=Row[0]%>"><span class="glyphicon glyphicon-log-in close"></span></a>
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
    							<label for="Fecha">Fecha: <%=Row[2]%></label>
    						</div>
    					</div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                    <label for="Hora">Hora: <%=Row[6]%></label>
                                            </div>
    					</div>
    				</div>
    				<div class="panel-footer">
    					<label for="Calificacion">Calificaci&oacute;n: <%if(Row[5]==null){%>0<%}else{%><%=Row[5]%><%}%></label>
    				</div>
    			</div>
    			<%}
    			%>
    		</div>
    		<div class="col-md-4 column">
    			<div class="panel panel-primary">
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
    						<a href="DetalleEvento.jsp?CodigoEvento=<%=Row[0]%>"><span class="glyphicon glyphicon-log-in close"></span></a>
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
    							<label for="Fecha">Fecha: <%=Row[2]%></label>
    						</div>
    					</div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                    <label for="Hora">Hora: <%=Row[6]%></label>
                                            </div>
    					</div>
    				</div>
    				<div class="panel-footer">
    					<label for="Calificacion">Comentarios: <%=Row[5]%></label>
    				</div>
    			</div>
    			<%}
    			%>
    		</div>
    		<div class="col-md-4 column">
    			<div class="panel panel-primary">
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
    						<a href="DetalleEvento.jsp?CodigoEvento=<%=Row[0]%>"><span class="glyphicon glyphicon-log-in close"></span></a>
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
    							<label for="Hora">Hora: <%=Row[5]%></label>
    						</div>
    					</div>
    				</div>
    				<div class="panel-footer">
    					<label for="Fecha">Fecha: <%=Row[2]%></label>
    				</div>
    			</div>
    			<%}
    			%>
    		</div>
    	</div>
    </div>
    <div class="container" style="width: 100%; margin-top: 2%;">
    	<div class="row clearfix">
    		<div class="col-md-12 column">
    			<h1 class="text-center" style="font-size: 3em;">
    				Algunos comentarios de nuestros usuarios
    			</h1>
    		</div>
    	</div>
    </div>
    <div class="container" style="width: 100%; margin-top: 2%;">
    	<div class="row clearfix">
    		<div class="col-md-2">
    		</div>
    		<div class="col-md-8">
    			<!-- /widget -->
    			<div class="widget">
    				<div class="widget-header">
    					<h3>Contenido</h3>
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
    		<div class="col-md-2">
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
    							<div class="col-md-offset-4 col-md-4">
    								<div class="form-group">
    									<label for="Email" class="col-sm-3 control-label" >
    										Email
    									</label>
    								</div>
    							</div>
    						</div>
    						<div class="row">
    							<div class="col-md-offset-2 col-md-8">
    								<div class="form-group">
    									<input type="email" class="form-control" id="inputEmail3" name="correo" placeholder="Email" data-notblank="true" data-required="true" data-maxlength="100" autofocus>
    								</div>
    							</div>
    						</div>
    						<div class="row">
    							<div class="form-group">
    								<div class="col-md-offset-4 col-md-4">
    									
    									<button type="submit" name="recucontrasenia" class="btn btn-primary btn-block" >
    										Enviar Contrase&ntilde;a
    									</button>
    								</div>
    							</div>
    						</div>
    					</form>
    				</div>
    			</div>
    		</div>
    	</div>
    </div>
    <div class="container" style="width: 100%; margin-top: 2%;">
    	<div class="row clearfix">

    	</div>
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

    		guidely.add ({
    			attachTo: '#target-1'
    			, anchor: 'top-middle'
    			, title: 'Ingreso de E-mail'
    			, text: 'En este campo ingresas el correo electrónico con el que te registraste.'
    		});

    		guidely.add ({
    			attachTo: '#target-2'
    			, anchor: 'top-middle'
    			, title: 'Ingreso de contraseña'
    			, text: 'En este campo ingresas la contraseña con la que te registraste.'
    		});

    		guidely.add ({
    			attachTo: '#target-3'
    			, anchor: 'bottom-left'
    			, title: 'Inicio de sesión o Limpieza de campos'
    			, text: 'Puedes, ya sea ingresar al aplicativo o, limpiar los campos anteriores.'
    		});
    		if(!localStorage.getItem("welcome"))
    		{
    			guidely.init ({ welcome: true, startTrigger: false });
    			localStorage.setItem("welcome", true)
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