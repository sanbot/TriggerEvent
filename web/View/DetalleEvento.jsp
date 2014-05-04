
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
String CodigoEvento = "";
String CodigoUsuario = (String) session.getAttribute("Codigo");
if(request.getParameter("CodigoEvento")!=null)
	{
CodigoEvento = request.getParameter("CodigoEvento");
}
else
	{
response.sendRedirect("ConsultarEventos.jsp");
}

boolean pendiente = false;
try{
    if(request.getParameter("Pendiente")!=null)
    {
        pendiente = Boolean.parseBoolean((String)request.getParameter("Pendiente"));
    }
}catch(Exception ex){
    response.sendRedirect("ConsultarEventos.jsp");    
}

String Datos[] = usu.getBuscarDatosDetalladosEvento(CodigoEvento);
int Calificacion[] = usu.getCalificacionEvento(CodigoEvento);
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
    <link rel="shortcut icon" href="../Libs/Customs/images/logoteazul.ico">
    <%@include file="../WEB-INF/jspf/EstilosCSS.jspf" %>
    <link rel="stylesheet" type="text/css" href="../Libs/Customs/css/style.css" />
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
        <div class="row">
            <div class="col-md-12">
                <ol class="breadcrumb">
                    <li><a href="index.jsp">Inicio</a></li>
                    <li><a href="ConsultaEvento.jsp">Eventos</a></li>
                    <li class="active">Detalle Evento</li>
                </ol>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-1"></div>
            <div class="col-xs-10">
                <div class="row">
                    <div class="col-md-5 eventoborde contenidoborde">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="margen-img"> 
                                    <center>
                                        <img src="ImagenEvento.jsp?Codigo=<%=CodigoEvento%>" class="imgevento icon-animated-bell" alt="Nombre de la imagen">
                                    </center>
                                </div>
                            </div>    
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <h3>
                                    <center>
                                    <div class="tituloevento"><h3><%=Datos[1]%></h3></div>
                                    </center>
                                </h3>
                            </div>    
                        </div>
                        <div class="row">
                            <div class="col-xs-6 contenido_uno">
                                <div class="contenido">
                                    <label for="Creador">Creador:</label>
                                </div>
                            </div>
                            <div class="col-xs-6 contenido_dos">
                                <div class="contenido">
                                    <label for="NombreEmpresa"><%=Datos[0]%></label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 contenido_uno">
                                <div class="contenido">
                                    <label for="RangoPrecios">Rango de precios:</label>
                                </div>
                            </div>
                            <div class="col-xs-6 contenido_dos">
                                <div class="contenido">
                                    <label for="RangoPreciosEvento"><%=Datos[2]%></label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 contenido_uno">
                                <div class="contenido">
                                    <label for="Departametno">Departamento:</label>
                                </div>
                            </div>
                            <div class="col-xs-6 contenido_dos">
                                <div class="contenido">
                                    <label for="DepartamentoEvento"><%=Datos[4]%></label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 contenido_uno">
                                <div class="contenido">
                                    <label for="Ciudad">Ciudad:</label>
                                </div>
                            </div>
                            <div class="col-xs-6 contenido_dos">
                                <div class="contenido">
                                    <label for="CiudadEvento"><%=Datos[6]%></label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 contenido_uno">
                                <div class="contenido">
                                    <label for="Direccion">Direcci&oacute;n:</label>
                                </div>
                            </div>
                            <div class="col-xs-6 contenido_dos">
                                <div class="contenido">
                                    <label for="DireccionEvento"><%=Datos[7]%></label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 contenido_uno">
                                <div class="contenido">
                                    <label for="Fecha">Fecha:</label>
                                </div>
                            </div>
                            <div class="col-xs-6 contenido_dos">
                                <div class="contenido">
                                    <label for="FechaEvento"><%=Datos[8]%></label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 contenido_uno">
                                <div class="contenido">
                                    <label for="Hora">Hora:</label>
                                </div>
                            </div>
                            <div class="col-xs-6 contenido_dos">
                                <div class="contenido">
                                    <label for="HoraEvento"><%=Datos[10]%></label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 contenido_medio">
                                <div class="contenido">
                                    <label for="Clasificacion"><a class="linkevento" href="DetalleClasificacionEvento.jsp?CodigoEvento=<%=CodigoEvento%>">Ver clasificaci&oacute;n </a></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-7 eventoborde">
                        <div class="row">
                            <div class="col-md-12 descripciontamanio descripcion-nombre">
                                <label for="Descripcion">Descripci&oacute;n:</label>
                                <br/>
                                <%=Datos[9]%>
                            </div>
                        </div>
                        <%if(!pendiente){%>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="row">
                                        <div class="col-md-1"></div>
                                        <div class="col-sm-4 contenido-2">
                                            <div class="rating-evento">
                                                    <span class="glyphicon glyphicon-star star-rating five-star"></span>5 Votos: <%=Calificacion[0]%>
                                                    <br/>
                                                    <span class="glyphicon glyphicon-star star-rating four-star"></span>4 Votos: <%=Calificacion[1]%>
                                                    <br/>
                                                    <span class="glyphicon glyphicon-star star-rating three-star"></span>3 Votos: <%=Calificacion[2]%>
                                                    <br/>
                                                    <span class="glyphicon glyphicon-star star-rating two-star"></span>2 Votos: <%=Calificacion[3]%>
                                                    <br/>
                                                    <span class="glyphicon glyphicon-star star-rating one-star"></span>1 Votos: <%=Calificacion[4]%>
                                            </div>
                                        </div>
                                        <div class="col-sm-6 contenido-2 visible-sm visible-md visible-lg contenido-2">
                                            <center>
                                                <canvas id="myChart" height="240"></canvas>
                                            </center>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <%}%>
                    </div>
                </div>
            </div>
            <div class="col-xs-1"></div>
        </div>
                                                
        <br/>
        <%if(!pendiente){%>
        <%if((Rol.equals("Cliente") || Rol.equals("Administrador")) && !pendiente)
        { 
        if(usu.getComprobacionCalificacionYComentario(CodigoEvento, CodigoUsuario, "Comentario") && usu.getComprobacionCalificacionYComentario(CodigoEvento, CodigoUsuario, "Calificacion"))
        {%>
            <div class="row">
                <div class="col-xs-1"></div>
                <div class="col-xs-10">
                    <div class="row">
                        <div class="col-xs-12 opinion-div contenidoborde">
                            <div class="row">
                                <div class="col-xs-12">
                                    <h3 class="titulo-opinion" id="titulo-opinion"><center>DAR UNA OPINI&Oacute;N</center></h3>
                                </div>
                            </div>
                            <form data-validate="parsley" role="form" method="post" action="/TriggerEvent/Contr_Satisfaccion">

                                <div class="row">
                                    <div class="col-xs-10 col-sm-10 col-sm-offset-1 col-md-offset-0 col-md-3 col-lg-3">
                                        <div class="form-group">

                                        </div>
                                    </div>
                                    <div class="col-xs-10 col-sm-10 col-xs-offset-1 col-md-offset-0 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="Comentario">Comentario</label>
                                            <textarea name="comentario" class="form-control" rows="4" placeholder="Dejar un comentario es opcional." data-notblank="true" data-rangelength="[10,250]"></textarea>
                                        </div>
                                    </div>

                                    <div class="col-xs-10 col-sm-10 col-xs-offset-1 col-md-offset-0 col-md-3 col-lg-3">
                                        <div class="form-group">
                                            <label for="Imagen">Puntuaci&oacute;n</label>
                                            <div id="stars" class="starrr rating" data-rating="3">
                                            </div>
                                            <span id="countname">Regular</span>
                                            <input type="hidden" name="Rating" id="count" value="3"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row contenido-opinion">
                                    <div class="col-xs-10 col-sm-10 col-sm-offset-1 col-md-offset-0 col-md-3 col-lg-3">
                                    </div>
                                    <div class="col-xs-10 col-sm-10 col-sm-offset-1 col-md-offset-0 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <button name="RegistrarSatisfaccion" type="submit" class="btn btn-default pull-right" id="Comentario">Enviar crítica</button>
                                        </div>
                                    </div>
                                    <div class="ccol-xs-10 col-sm-10 col-sm-offset-1 col-md-offset-0 col-md-3 col-lg-3">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <input type="hidden" name="CodigoEvento" value="<%=CodigoEvento%>" />
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-xs-1"></div>
            </div>
        <%} else
            {%>
                <div class="row">
                    <div class="col-xs-1"></div>
                    <div class="col-xs-10">
                        <div class="row">
                            <div class="col-xs-12 opinion-div contenidoborde">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <h3 class="titulo-opinion" id="titulo-opinion"><center>DAR UNA OPINI&Oacute;N</center></h3>
                                    </div>
                                </div>
                                <form data-validate="parsley" role="form" method="post" action="/TriggerEvent/Contr_Satisfaccion">

                                    <div class="row">
                                        <div class="col-xs-10 col-sm-10 col-xs-offset-1 col-md-5 col-lg-5">
                                            <div class="form-group texto-justificado">
                                                <label for="Comentario">Comentario</label>
                                                <br/>
                                                <%if(!usu.getComprobacionCalificacionYComentario(CodigoEvento, CodigoUsuario, "Comentario")){%>
                                                <%=usu.getComentarioOCalificacion("Comentario", CodigoUsuario)%>
                                                <%}else {%>
                                                    <textarea name="comentario" class="form-control" rows="4" placeholder="Dejar un comentario es opcional." data-notblank="true" data-rangelength="[10,250]"></textarea>
                                                <%}%>
                                            </div>
                                        </div>

                                        <div class="col-xs-10 col-sm-10 col-xs-offset-1 col-md-offset-0 col-md-5 col-lg-5">
                                            <div class="form-group">
                                                <label for="Imagen">Puntuaci&oacute;n</label>
                                                <%if(!usu.getComprobacionCalificacionYComentario(CodigoEvento, CodigoUsuario, "Calificacion")){%>
                                                    <br/>
                                                    <span class="glyphicon glyphicon-star"></span> <%=usu.getComentarioOCalificacion("Calificacion", CodigoUsuario)%> puntos de calificaci&oacute;n
                                                <%}else {%>
                                                    <div id="stars" class="starrr rating" data-rating="3">
                                                    </div>
                                                    <span id="countname">Regular</span>
                                                    <input type="hidden" name="Rating" id="count" value="3"/>
                                                <%}%>

                                            </div>
                                        </div>
                                        <div class="col-xs-1"></div>
                                    </div>
                                    <div class="row contenido-opinion">
                                        <div class="col-xs-10 col-sm-10 col-xs-offset-1 col-md-5 col-lg-5">
                                            <div class="form-group">
                                                <%if (usu.getComprobacionCalificacionYComentario(CodigoEvento, CodigoUsuario, "Comentario")){%>
                                                    <button name="RegistrarSatisfaccion" type="submit" class="btn btn-default pull-right" id="Comentario">Enviar crítica</button>
                                                <%}%>
                                            </div>
                                        </div>
                                        <div class="col-xs-10 col-sm-10 col-xs-offset-1 col-md-offset-0 col-md-5 col-lg-5">
                                            <div class="form-group">
                                                <%if(usu.getComprobacionCalificacionYComentario(CodigoEvento, CodigoUsuario, "Calificacion")){%>
                                                <button name="RegistrarSatisfaccion" type="submit" class="btn btn-default pull-left" id="Comentario">Enviar crítica</button>
                                                <%}%>
                                            </div>
                                        </div>
                                        <div class="col-xs-1"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <input type="hidden" name="CodigoEvento" value="<%=CodigoEvento%>" />
                                        </div>
                                    </div>
                                    <div class="col-xs-1"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-1"></div>
                </div>
            <%}%>
        <%}%>
        <br/>
	<div class="row clearfix">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2">
                <!-- /widget -->
                <div  class="widget">
                    <div class="widget-header">
                        <h3>Opiniones de nuestros usuarios</h3>
                    </div>
                    <!-- /widget-header -->
                    <div class="widget-content">
                        <ul id="contenido" class="messages_layout">
                            <div id="contenedor-mensajes">
                                
                            </div>
                            
                            <li id="vermas" class="from_user left">
                                <div class="message_wrap"> 
                                    <span class="arrow"></span>
                                    <div class="info"> 
                                        <span class="name">Hay m&aacute;s comentarios de este evento</span>
                                    </div>
                                    <div class="text"> 
                                        <a class="vermascomentarios"><span class="name">Ver m&aacute;s comentarios</span></a>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <!-- /widget-content --> 
                </div>
                <!-- /widget --> 
            </div>
        <%}%>
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
    <script type="text/javascript" src="../Libs/Customs/js/graficos.js" ></script>
    <script>
        function mascomentarios(cantidad, limite)
        {
            $.ajax({
                type: 'POST',
                url: '/TriggerEvent/Contr_Help',
                data: {"accion": 'vermas', "cantidad": cantidad, "codigoevento": '<%=CodigoEvento%>', "limite": limite},
                success: function(data){
                    var datos = jQuery.parseJSON(data);
                    var items = [];
                    $.each(datos, function(key, val){
                        
                        items.push('<li class="from_user left">');
                        items.push('<div class="message_wrap"> ');
                        items.push('<span class="arrow"></span>');
                        items.push('<div class="info"> ');
                        items.push('<span class="name">Usuario: '+ val.usuario +', Evento: '+ val.empresa+'</span>');
                        items.push('</div>');
                        items.push('<div class="text"> ');
                        items.push(val.comentario);
                        items.push('</div>');
                        items.push('</div>');
                        items.push('</li>');
                    });
                    
                    $("#contenedor-mensajes").append(items.join(""));
                    var cant= 0;
                    $( "li.from_user" ).each(function( index ) {
                        cant++;
                    });
                    if(cant>5)
                    {
                        $("#contenido").addClass("scrollcomentarios");
                    }
                    var total;
                    $.ajax({
                        type: 'POST',
                        url: '/TriggerEvent/Contr_Help',
                        data: {"accion": 'total',"codigoevento": '<%=CodigoEvento%>'},
                        success: function(data){
                            total = data;
                            if(cant>total )
                            {
                                $("#vermas").addClass("hidden");
                            }
                        }
                    });
                }
            });
        }
        $(".vermascomentarios").click(function(){
            var i= 0;
            $( "li.from_user" ).each(function( index ) {
             i++;
            });
            mascomentarios("5",i-1);
            
            
        });
        var data = [
	{
		value: <%=Calificacion[0]%>,
		color:"#88B131"
	},
	{
		value : <%=Calificacion[1]%>,
		color : "#9C0"
	},
	{
		value : <%=Calificacion[2]%>,
		color : "#FFCF02"
	},
	{
		value : <%=Calificacion[3]%>,
		color : "#FF0F02"
	},
	{
		value : <%=Calificacion[4]%>,
		color : "#FF6F31"
	}

        ];
        //Get context with jQuery - using jQuery's .get() method.
        var ctx = $("#myChart").get(0).getContext("2d");
        //This will get the first returned node in the jQuery collection.
        var myNewChart = new Chart(ctx).Doughnut(data);
        
        $(document).ready(function(){
            mascomentarios("5", "0");
        });
        
    </script>
    <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
    <%session.setAttribute("Mensaje", "");%>
</body>
</html>