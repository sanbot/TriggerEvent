<%-- 
    Document   : DetalleEvento
    Created on : 10-abr-2014, 12:53:19
    Author     : ADSI
--%>

<%-- 
Document   : RegistrarUsuario
Created on : 11/03/2014, 08:28:24 AM
Author     : santi_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionGeneral.jspf" %>
<%
    Contr_Consultar usu = new Contr_Consultar();
    String Codigo = "";
    if(request.getParameter("CodigoEvento")!=null)
    {
        Codigo = request.getParameter("CodigoEvento");
    }
    else
    {
        response.sendRedirect("ConsultarEventos");
    }
    String Datos[] = usu.getBuscarDatosDetalladosEvento(Codigo);
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
                            <a href="index.jsp">Inicio</a> <span class="glyphicon glyphicon-share-alt"></span><a href="ConsultaEvento.jsp">Eventos</a> <span class="glyphicon glyphicon-share-alt"></span> Detalle Evento
			</div>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12">
			<br/>
			<h1 class="Center">Detalle de Evento</h1>
		</div>
	</div>
        <form id="search" data-validate="parsley" enctype="multipart/form-data" method="post" action="/TriggerEvent/Contr_Evento">



                <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            <div class="form-group">
                                <img src="ImagenEvento.jsp?Codigo=<%=Codigo%>" class="img-responsive imgevento"/>
                            </div>
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            
                            <div class="form-group">
                                <label for="Creador">Creador del evento</label>
                                <input name="Creador" type="text" class="form-control" id="NombreEvento" data-rangelength="[3,30]" data-notblank="true" data-required="true" value="<%=Datos[0]%>" readonly/>
                            </div>
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            <div class="form-group">
                                    <label for="Nombre">Nombre</label>
                                    <input name="Nombre" type="text" class="form-control" id="NombreEvento" data-rangelength="[3,30]" data-notblank="true" data-required="true" value="<%=Datos[1]%>" readonly/>
                            </div>
                        </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                        <div class="form-group">
                                <label for="Rango">Rango</label>
                                <input name="Rango" type="text" class="form-control" id="RangoEvento" placeholder="10000-2000000" data-required="true" data-notblank="true" data-rangelength="[13,13]" value="<%=Datos[2]%>" readonly/>
                        </div>
                        

                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

                        <div class="form-group">
                                <label for="Departamento">Departamento</label>
                                <select id="departamentoevento" name="Departametno" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                    <option value="<%=Datos[3]%>"><%=Datos[4]%></option>
                                </select>
                        </div>
                        

                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">

                        <div class="form-group">
                                <label for="Ciudad">Ciudad</label>
                                <select id="ciudadevento" name="Ciudad" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                    <option value="<%=Datos[5]%>"><%=Datos[6]%></option>
                                </select>
                        </div>
                        

                    </div>
                </div>
                <div class="row">

                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            <div class="form-group">
                                    <label for="Direccion">Direcci&oacute;n</label>
                                    <input name="Direccion" class="form-control" data-required="true" data-notblank="true" data-rangelength="[8,100]" readonly value="<%=Datos[7]%>"/>
                            </div>
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                                <div class="form-groupr">
                                    <label for="Fecha">Fecha</label>
                                    <input type='text' name="Fecha" id="FechaEvento" class="form-control" data-required="true" data-notblank="true" data-rangelength="[18,19]" readonly value="<%=Datos[8]%>"/>
                                </div>
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            <div class="form-groupr">
                                <label for="Hora">Hora</label>
                                <input type='text' name="Hora" id="FechaEvento" class="form-control" data-required="true" data-notblank="true" data-rangelength="[6,9]" readonly value="<%=Datos[10]%>"/>
                            </div>
                        </div>
                </div>
                <div class="row">
                        <div class=" col-xs-12 col-sm-12 col-md-2 col-lg-2">
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">

                                <div class="form-group">
                                        <label for="Descripcion">Descripci&oacute;n</label>
                                        <textarea id="DescripcionEvento" name="Descripcion" class="form-control" data-rangelength="[8,150]" rows="4" readonly><%=Datos[9]%></textarea>
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
                                    <a href="DetalleClasificacionEvento.jsp?CodigoEvento=<%=Codigo%>" class="btn btn-block defecto">Ver clasificaci&oacute;n</a>
                                </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                        </div>
                </div>
        </form>
                                
                        <%if(Rol.equals("Cliente"))
                        {%>
                        <div class="row">

                            <legend>CALIFICACIONES</legend>

                            <form data-validate="parsley" role="form" method="post" action="/TriggerEvent/Contr_Satisfaccion">

                                <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
                                    <div class="form-group">

                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label for="Imagen">Comentario</label>
                                        <textarea class="form-control parsley-validated" rows="4" placeholder="Puntúa. Dejar una crítica es opcional" data-notblank="true" data-rangelength="[10,100]" data-required="true"></textarea>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-default pull-right" id="ErrorComentario">Error</button>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-default pull-right" id="Comentario">Enviar crítica</button>
                                    </div>
                                </div>

                                <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
                                    <div class="form-group">
                                        <div class="row lead">
                                            <label for="Imagen">Puntuaci&oacute;n</label>
                                            <div id="stars" class="starrr rating" data-rating="3"><span class="glyphicon .glyphicon-star-empty glyphicon-star"></span><span class="glyphicon .glyphicon-star-empty glyphicon-star"></span><span class="glyphicon .glyphicon-star-empty glyphicon-star"></span><span class="glyphicon .glyphicon-star-empty glyphicon-star"></span><span class="glyphicon .glyphicon-star-empty glyphicon-star"></span></div>
                                            <span id="count" name="Rating">Excelente</span>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <%}%>
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
    
    <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#datetimepicker1').datetimepicker();
        });
    </script>
    <script type="text/javascript" src="../Libs/Customs/js/Rating.js" ></script>
    <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
    <%session.setAttribute("Mensaje", "");%>
</body>
</html>