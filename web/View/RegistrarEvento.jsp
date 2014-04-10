<%-- 
Document   : RegistrarUsuario
Created on : 11/03/2014, 08:28:24 AM
Author     : santi_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionAdministradorEmpresa.jspf" %>
<%
    Contr_Consultar usu = new Contr_Consultar();
    String Codigo = (String) session.getAttribute("No_Documento");
    String [][]ListaEmpresa = null;
    if(Rol.equals("Administrador"))
    {
        ListaEmpresa = usu.BuscarDatosEmpresa();
    }
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
        <link type="text/css" rel="stylesheet" href="../Libs/Customs/DatePicker/css/bootstrap-datetimepicker.css">

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
        <%}%>
        <br/>
        <br/>
        <br/>
        <div class="container">
	<div class="row clearfix">
		<div class="col-md-12">
			<div class="form-group">
				<a href="index.jsp">Inicio</a> <span class="glyphicon glyphicon-share-alt"></span>Registro de Eventos
			</div>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12">
			<br/>
			<h1 class="Center">Registro de Eventos</h1>
		</div>
	</div>
        <form id="search" data-validate="parsley" enctype="multipart/form-data" method="post" action="/TriggerEvent/Contr_Evento">



                <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Creador">Creador del evento</label>
                                    <%if(Rol.equals("Empresa"))
                                    {%>
                                        <select name="Creador" tabindex="1" data-placeholder="" class="form-control" data-required="true" >
                                                <option value="<%=Codigo%>"><%=Nombre%></option>
                                        </select>
                                    <%}
                                    else if(Rol.equals("Administrador"))
                                    {%>
                                    <select name="Creador" tabindex="1" data-placeholder="" class="form-control" data-required="true" >
                                        <%for(String Row[] : ListaEmpresa)
                                        {%>
                                            <option value="<%=Row[0]%>"><%=Row[1]%></option>
                                        <%}
                                        %></select>
                                    <%}%>
                                </div>
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                                <div class="form-group">
                                        <label for="Nombre">Nombre</label>
                                        <input name="Nombre" type="text" class="form-control" id="NombreEvento" data-rangelength="[3,30]" data-notblank="true" data-required="true" />
                                </div>
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                                <div class="form-group">
                                        <label for="Rango">Rango</label>
                                        <input name="Rango" type="text" class="form-control" id="RangoEvento" placeholder="10000-2000000" data-required="true" data-notblank="true" data-rangelength="[13,13]" />
                                </div>
                        </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

                        <div class="form-group">
                                <label for="Departamento">Departamento</label>
                                <select id="departamentoevento" name="Departametno" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                </select>
                        </div>

                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

                        <div class="form-group">
                                <label for="Ciudad">Ciudad</label>
                                <select id="ciudadevento" name="Ciudad" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                </select>
                        </div>

                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

                        <div class="form-group">
                                <label for="Direccion">Direcci&oacute;n</label>
                                <input name="Direccion" class="form-control" data-required="true" data-notblank="true" data-rangelength="[8,100]"/>
                        </div>

                    </div>
                </div>
                <div class="row">

                        <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                                <div class="form-group">
                                        <label for="Imagen">Imagen</label>
                                        <input name="Imagen" type="file" accept="image/*" data-required="true"/>
                                </div>
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            <label for="Fecha">Fecha</label>
                            <div class='input-group date' id='datetimepicker1' data-date-format="MM/DD/YYYY hh:ii">
                                <input type='text' name="Fecha" id="FechaEvento" class="form-control" data-required="true" data-notblank="true" data-rangelength="[18,19]" readonly/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </div>
                    
                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                        </div>

                </div>
                <div class="row">
                        <div class=" col-xs-12 col-sm-12 col-md-2 col-lg-2">
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">

                                <div class="form-group">
                                        <label for="Descripcion">Descripci&oacute;n</label>
                                        <textarea id="DescripcionEvento" name="Descripcion" class="form-control" data-rangelength="[8,150]" rows="4"></textarea>
                                </div>

                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
                        </div>
                </div>

                <div class="row">
                        <div class="col-md-4">
                        </div>
                        <div class="col-md-4">
                                <div class="form-group">
                                        <button name="RegistrarEvento" type="submit" class="btn btn-block defecto" id="RegistrarEvento">Registrar</button>
                                </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
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





    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!--Bootstrap-->
    <script src="../Libs/Bootstrap/js/jquery-1.10.2.min.js"></script>    
    <script src="../Libs/Bootstrap/js/bootstrap.min.js"></script>
    <!--Parsley-->
    <script src="../Libs/Customs/js/Parsley.js"></script>    
    <script src="../Libs/Customs/js/classie.js"></script>
    <script src="../Libs/Customs/js/gnmenu.js"></script>
    <script src="../Libs/Customs/DatePicker/js/moment.min.js"></script>
    <script src="../Libs/Customs/DatePicker/js/bootstrap-datetimepicker.es.js.js"></script>
    
    <script src="../Libs/Customs/DatePicker/js/bootstrap-datetimepicker.js"></script>
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
                    var options_departments = "<option value=''></option>";
                    $.each(datadep, function(i,d){
                            options_departments += '<option value="' + d.codigo + '">' + d.nombre + '<\/option>';
                    });
                    $("select#departamentoevento", this).html(options_departments);
                    $("select#departamentoevento", this).change(function(){
                    var index = $(this).val();
                    var options = '';
                    $.each(data, function(i,c){
                        if(c.codigo_departamento === index)
                        {
                            options += '<option value="' + c.codigo + '">' + c.nombre + '<\/option>';
                        }
                    });
                    $("select#ciudadevento").html(options);
		});
    };
    })(jQuery);
    $(document).ready(function() {
        $("form#search").changeType();
    });
    </script>
    <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#datetimepicker1').datetimepicker();
        });
    </script>
    <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
    <%session.setAttribute("Mensaje", "");%>
</body>
</html>