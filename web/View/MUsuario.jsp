<%-- 
Document   : ConsultaUsuario
Created on : 18-mar-2014, 14:17:00
Author     : ADSI
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
    if(request.getParameter("Aprobar")!=null)
    {
        boolean b;
        if(request.getParameter("Aprobar").equals("true"))
        {
             b = usu.setAprobarUsaurio(codigoUsuario);
            if(b)
            {
                session.setAttribute("TipoMensaje","Aprobar");
                session.setAttribute("Mensaje", "El usuario ha sido aprobado satisfactoriamente.");
            }
            else
            {
                session.setAttribute("TipoMensaje","AprobarNO");
                session.setAttribute("Mensaje", "Ocurrió un error al tratar de aprobar el usuario, por favor intentelo de nuevo.");
            }
        }
        else if(request.getParameter("Aprobar").equals("false"))
        {
            b = usu.setDesaprobarUsaurio(codigoUsuario);
            if(b)
            {
                session.setAttribute("TipoMensaje", "Aprobar");
                session.setAttribute("Mensaje", "El usuario ha sido desaprobado existosamente.");
            }
            else
            {
                session.setAttribute("TipoMensaje", "AprobarNO");
                session.setAttribute("Mensaje", "Ocurrió un error al tratar de desactivar el usuario, por favor intentelo de nuevo.");
            }
        }
        response.sendRedirect("ConsultaUsuario.jsp");
    }
}
else
{
    response.sendRedirect("ConsultaUsuario.jsp");
}

String[][] ListaTipoUsuario = usu.BuscarDatosTipoUsuariosTodos();
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
            <%@include file="../WEB-INF/jspf/MenuAdministrador.jspf" %>
                <div class="container">
                    <br/>
                    <br/>
                    <br/>
                    
		<div class="row clearfix">
			<div class="col-md-12">
				<div class="form-group">
                                    <a href="index.jsp">Inicio</a> <span class="glyphicon glyphicon-share-alt"></span> <a href="ConsultaUsuario.jsp">Registro y Consulta de Usuario</a> <span class="glyphicon glyphicon-share-alt"></span> Modificar Usuario
				</div>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-12">
				<br/>
				<h1 class="Center">Modificar Usuario</h1>
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
								<input name="Nombre" type="text" class="form-control" id="nombre" data-rangelength="[3,100]" data-notblank="true" data-required="true" value="<%=DatosUsuario[5]%>"/>
							</div>
						</div>
                                                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Tipo">Tipo Usuario</label>
								<select name="Tipo" id="Tipos" class="form-control" data-required="true">
                                                                        <option value="<%=DatosUsuario[1]%>"><%=DatosUsuario[2]%></option>
									<%for(String[] Row : ListaTipoUsuario){%>
                                                                        <option value="<%=Row[0]%>"><%=Row[1]%></option>
                                                                        <%}%>
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
                                                                <input name="No_Documento" type="text" class="form-control" id="docum" data-rangelength="[6,30]" data-notblank="true" data-required="true" value="<%=DatosUsuario[4]%>"/>
							</div>
						</div>
                                                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

							<div class="form-group">
								<label for="Telefono">Tel&eacute;fono</label>
                                                                <input name="Telefono" class="form-control" type="text" placeholder="000 00 00" data-type="phone" data-notblank="true" data-rangelength="[9,9]" data-required="true" value="<%=DatosUsuario[6]%>">
							</div> 
						</div>
						

						

					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Nombre">Celular</label>
                                                                <input name="Celular" type="text" class="form-control" id="Hora" placeholder="000 000 0000" data-rangelength="[12,14]" data-type="cellphone" data-notblank="true" data-required="true" value="<%=DatosUsuario[7]%>" />
							</div>
						</div>
                                                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Nombre">Correo</label>
                                                                <input name="Correo" type="text" class="form-control" id="Direccion" placeholder="example@service.com" data-required="true" data-notblank="true" data-type="email" data-rangelength="[10,100]" value="<%=DatosUsuario[8]%>"/>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

							<div class="form-group">
								<label for="Direccion">Direcci&oacute;n</label>
								<div class="form-group">
                                                                    <input name="Direccion" class="form-control" type="text" data-notblank="true" data-rangelength="[10,100]" data-required="true" value="<%=DatosUsuario[9]%>">
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
									<option value="Aprobado">Aprobado</option>
									<option value="Desaprobado">Desaprobado</option>
								</select>
								</div>
							</div>

						</div> 
                                                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4"></div>
                                        </div>

					<div class="row">
						<div class="col-md-4">

						</div>
						<div class="col-md-4">
							<div class="form-group">

								<button name="ModificarUsuarioTodos" type="submit" class="btn btn-block defecto" id="ModificarUsuarioTodos">Modificar</button>

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
    <script>
    new gnMenu( document.getElementById( 'gn-menu' ) );
    </script>
    
    <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
    <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
    
    <%
    if(!session.getAttribute("TipoMensaje").equals("Aprobar") && !session.getAttribute("TipoMensaje").equals("AprobarNO"))
    {
        session.setAttribute("Mensaje", "");
    }%>
</body>
</html>

