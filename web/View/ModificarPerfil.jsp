<%-- 
    Document   : RUsuario
    Created on : 28-feb-2014, 15:14:34
    Author     : Sanser
    --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
    <%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
    <%@include file="../WEB-INF/jspf/ValidacionGeneral.jspf" %>
    <%
String Codigo = (String)  session.getAttribute("Codigo");
Contr_Consultar usu = new Contr_Consultar();
String[] DatosUsuario = usu.BuscarDatosUsuario(Codigo);
String[][] ListaDepartamento = usu.BuscarDatosDepartamentoTodos();
String[][] ListaCiudad = usu.BuscarDatosCuidadTodos();
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
        
	<div class="container">
                <br/>
                <br/>
                <br/>
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
				
				<form id="search" data-validate="parsley" role="form" method="post" action="/TriggerEvent/Contr_Usuarios">

					<div class="row">

						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Tipo">Tipo Usuario</label>
								<input name="TipoUsuario" type="text" class="form-control" id="docum" data-rangelength="[6,30]" data-notblank="true" data-required="true" value="<%=Rol%>" readonly/>
							</div>
						</div>

						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Tipo_Documento">Tipo Documento</label>
								<input name="Tipo_Documento" type="text" class="form-control" id="docum" data-rangelength="[6,30]" data-notblank="true" data-required="true" value="<%=DatosUsuario[3]%>" readonly/>
							</div>
						</div>

						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

							<div class="form-group">
								<label for="Numero_Documento">N&uacute;mero de Documento</label>
								<input name="No_Documento" type="text" class="form-control" id="docum" data-rangelength="[6,30]" data-notblank="true" data-required="true" value="<%=DatosUsuario[4]%>" readonly/> 
							</div>
						</div>

					</div>

					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Nombre">Nombre</label>
								<input name="Nombre" type="text" class="form-control" id="nombre" data-rangelength="[3,100]" data-notblank="true" data-required="true" value="<%=Nombre%>"/>
							</div>
						</div>

						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Celular">Celular</label>
								<input name="Celular" type="text" class="form-control" id="Hora" placeholder="000 000 0000" data-rangelength="[12,14]" data-type="cellphone" data-notblank="true" data-required="true" value="<%=DatosUsuario[7]%>" readonly/>
							</div>
						</div>

						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<div class="form-group">
								<label for="Correo">Correo</label>
								<input name="Correo" type="text" class="form-control" id="Direccion" placeholder="example@service.com" data-required="true" data-notblank="true" data-type="email" data-rangelength="[10,100]" value="<%=DatosUsuario[8]%>" readonly/>
							</div>
						</div>

					</div>

					<div class="row">
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
                                                                    <option value='<%=DatosUsuario[13]%>'><%=DatosUsuario[11]%></option>
                                                                </select>
                                                        </div>

                                                </div>
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

							<div class="form-group">
								<label for="Tipo">Tel&eacute;fono</label>
								<input name="Telefono" class="form-control" type="text" placeholder="000 00 00" data-type="phone" data-notblank="true" data-rangelength="[9,9]" data-required="true" value="<%=DatosUsuario[6]%>"/>
							</div> 
						</div>

						

					</div>
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4"></div>
                                            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

                                                <div class="form-group">
                                                        <label for="Imagen">Direcci&oacute;n</label>
                                                        <div class="form-group">
                                                                <input name="Direccion" class="form-control" type="text" data-notblank="true" data-rangelength="[10,100]" data-required="true" value="<%=DatosUsuario[9]%>"/>
                                                        </div>
                                                </div>

                                            </div>
                                            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4"></div>
                                        </div>
					<div class="row">
						<div class="col-md-offset-4 col-md-4">
							<button type="submit" name="ModificarPerfil" class="btn btn-primary btn-block">Guardar</button>
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
                    var options_departments;
                    <%
                    for(String[] Row: ListaDepartamento)
                    {
                        if(Row[0].equals(DatosUsuario[12]))
                        {
                            %>options_departments = "<option value='<%=Row[0]%>'><%=Row[1]%></option>"; <%
                        }
                    }
                    %>
                    
                    $.each(datadep, function(i,d){
                            options_departments += '<option value="' + d.codigo + '">' + d.nombre + '<\/option>';
                    });
                    $("select#departamento", this).html(options_departments);
                    $("select#departamento", this).change(function(){
                    var index = $(this).val();
                    var options = "";
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
$(document).ready(function(){$("form#search").changeType();});
    </script>
      <script>
      new gnMenu( document.getElementById( 'gn-menu' ) );
      </script>
      <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
      <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
    <%session.setAttribute("Mensaje", "");%>
  </body>
  </html>
