<%-- 
Document   : ConsultaUsuario
Created on : 18-mar-2014, 14:17:00
Author     : ADSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
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
    <%}else{%>
    <%@include file="../WEB-INF/jspf/Menu.jspf" %>    
    <%}%>
        <br/>
        <br/>
        <br/>
        <div class="container">
	<div class="row clearfix">
		<div class="col-md-12">
			<div class="form-group">
                            <a href="index.jsp">Inicio</a> <span class="glyphicon glyphicon-share-alt"></span>Mapa del sitio
			</div>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12">
			<br/>
			<h1 class="Center">Cont&aacute;ctenos</h1>
		</div>
	</div>
	<div class="row">
            <form id="search" data-validate="parsley" method="post" action="/TriggerEvent/Contr_Contacto">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="Nombre">Nombre</label>
                            <input name="Nombre" type="text" class="form-control" id="Nombre" data-rangelength="[3,30]" data-notblank="true" data-required="true" <%if(!Rol.equals("")&&!Rol.equals(null)){%> value="<%=Nombre%>" readonly<%}%>/>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="Correo">Correo</label>
                            <input name="Correo" type="email" class="form-control" data-type="email" id="Correo" data-maxlength="100" data-notblank="true" data-required="true" <%if(!Rol.equals("")&&!Rol.equals(null)){%> value="<%=DatosUsuario[8]%>" readonly<%}%>/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="Categoria">Categoria</label>
                            <select name="Categoria" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                <option value=""></option>
                                <option value="Contenido">Contenido</option>
                                <option value="Problemas ténicos">Problemas T&eacute;cnicos</option>
                                <option value="Sugerencias y solicitudes">Sugerencias y Solicitudes</option>
                                <option value="Queja">Queja</option>
                            </select>
                        </div>
                    </div>

                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="Nombre">Asunto</label>
                            <input name="Asunto" type="text" class="form-control" id="Asunto" data-notblank="true" data-rangelength="[3,30]" data-required="true"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="Departamento">Departamento</label>
                            <select id="departamentocontactenos" name="Departametno" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                            </select>
                        </div>
                    </div>

                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="form-group">
                            <label for="Ciudad">Ciudad</label>
                            <select name="Ciudad" id="ciudadcontactenos" name="Ciudad" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                <%if(!Rol.equals(null) && !Rol.equals("")){%><option value='<%=DatosUsuario[11]%>'><%=DatosUsuario[11]%></option><%}%>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">              
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="form-group">
                            <label for="Comentario">Comentario</label>
                            <textarea name="Comentario" class="form-control" rows="11" data-notblank="true" data-rangelength="[10,500]" data-required="true"></textarea>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-offset-3 col-md-6 col-lg-offset-3 col-lg-6">
                        <div class="form-group">
                            <center>
                                <button type="submit" class="btn btn-primary btn-block" id="Contactenos" name="Contactenos">Enviar</button>
                            </center>
                        </div>
                    </div>
                </div>
            </form>
	</div>
	<!-- FOOTER -->
	<footer>
            <hr class="featurette-divider">
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
    <script type="text/javascript" src="../Libs/Customs/DataTables/js/jquery.dataTables.js" charset="UTF-8"></script>
    <script type="text/javascript" src="../Libs/Customs/DataTables/js/datatables.js" charset="UTF-8"></script>
    <script>
    new gnMenu( document.getElementById( 'gn-menu' ) );
    </script>
    
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
                var options_departments = "";


                $.each(datadep, function(i,d){
                        options_departments += '<option value="' + d.codigo + '">' + d.nombre + '<\/option>';
                });
                $("select#departamentocontactenos", this).html(options_departments);
                <%if(!Rol.equals(null)&& !Rol.equals("")){%>$('#departamentocontactenos [value=<%=DatosUsuario[12]%>]').prop('selected', true);<%}%>
                
                var option = "";
                
                <%if(!Rol.equals(null)&& !Rol.equals("")){%>
                $.each(data, function(i,da){
                    if(da.codigo_departamento === "<%=DatosUsuario[12]%>")
                    {
                        option += '<option value="' + da.nombre + '">' + da.nombre + '<\/option>';
                    }
                });
                $("select#ciudadcontactenos").html(option);
                $('#ciudadcontactenos [value=<%=DatosUsuario[11]%>]').prop('selected', true);<%}%>

                $("select#departamentocontactenos", this).change(function(){
                    var index = $(this).val();
                    var options = "";
                    $.each(data, function(i,da){
                        if(da.codigo_departamento === index)
                        {
                            options += '<option value="' + da.nombre + '">' + da.nombre + '<\/option>';
                        }
                    });
                    $("select#ciudadcontactenos").html(options);
                });
            };
        })(jQuery);
        $(document).ready(function(){$("form#search").changeType();});
    </script>
    <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
    <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
    <%session.setAttribute("Mensaje", "");%>
</body>
</html>


