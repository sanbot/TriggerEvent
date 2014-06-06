<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionGeneral.jspf" %>
<%
    String CodigoEvento = "";
    String CodigoUsuario = (String) session.getAttribute("Codigo");
    if (request.getParameter("CodigoEvento") != null) {
        CodigoEvento = request.getParameter("CodigoEvento");
    } else {
        response.sendRedirect("ConsultarEventos.jsp");
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
        <link rel="shortcut icon" href="../Libs/Customs/images/logoteazul.ico">
        <%@include file="../WEB-INF/jspf/EstilosCSS.jspf" %>
        <link rel="stylesheet" type="text/css" href="../Libs/Customs/css/style.css" />
        <link href="../Libs/Customs/css/leaflet.css" rel="stylesheet" type="text/css"/>
        <link href="../Libs/Customs/css/leaflet.draw.css" rel="stylesheet" type="text/css"/>
        <script src="../Libs/Customs/js/modernizr.custom.js"></script>
    </head>
    <body>
        <%if (Rol.equals("Administrador")) {%>
        <%@include file="../WEB-INF/jspf/MenuAdministrador.jspf" %>
        <%} else if (Rol.equals("Cliente")) {%>
        <%@include file="../WEB-INF/jspf/MenuCliente.jspf" %>
        <%} else if (Rol.equals("Empresa")) {%>
        <%@include file="../WEB-INF/jspf/MenuEmpresa.jspf" %>
        <%}%>
        <br/>
        <br/>
        <br/>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <ol class="breadcrumb">
                        <li><a href="EventoRecomendado.jsp">Inicio</a></li>
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
                                            <img id="img_evento" class="imgevento icon-animated-bell" alt="Nombre de la imagen">
                                        </center>
                                    </div>
                                </div>    
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <h3>
                                        <center>
                                            <div class="tituloevento">
                                                <h3 id="lbl_nombre_evento"></h3>
                                            </div>
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
                                        <label id="lbl_empresa_evento" for="NombreEmpresa"></label>
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
                                        <label id="lbl_rango_evento" for="RangoPreciosEvento"></label>
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
                                        <label id="lbl_departamento_evento" for="DepartamentoEvento"></label>
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
                                        <label id="lbl_ciudad_evento" for="CiudadEvento"></label>
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
                                        <label id="lbl_direccion_evento" for="DireccionEvento">
                                            <span title="Ubicaci&oacute;n" id="mostrarubicacion" class="glyphicon glyphicon-screenshot"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-6 contenido_uno">
                                    <div class="contenido">
                                        <label  for="Fecha">Fecha:</label>
                                    </div>
                                </div>
                                <div class="col-xs-6 contenido_dos">
                                    <div class="contenido">
                                        <label id="lbl_fecha_evento" for="FechaEvento"></label>
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
                                        <label id="lbl_hora_evento" for="HoraEvento"></label>
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
                                    <br id="lbl_descripcion_evento"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <center><label>Calificaci&oacute;n</label></center>
                                    <div class="row">
                                        <div class="col-md-1"></div>
                                        <div class="col-sm-4 contenido-2">
                                            <div id="puntuacionevento" class="rating-evento">
                                                <span class="glyphicon glyphicon-star star-rating five-star"></span>5 Votos: <p id="txtcalicinco"></p>
                                                <br/>
                                                <span class="glyphicon glyphicon-star star-rating four-star"></span>4 Votos: <p id="txtcalicuatro"></p>
                                                <br/>
                                                <span class="glyphicon glyphicon-star star-rating three-star"></span>3 Votos: <p id="txtcalitres"></p>
                                                <br/>
                                                <span class="glyphicon glyphicon-star star-rating two-star"></span>2 Votos: <p id="txtcalidos"></p>
                                                <br/>
                                                <span class="glyphicon glyphicon-star star-rating one-star"></span>1 Votos: <p id="txtcaliuno"></p>
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
                        </div>
                    </div>
                </div>
                <div class="col-xs-1"></div>
            </div>
            <br/>
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
                                    <div class="col-xs-10 col-sm-10 col-sm-offset-1 col-md-offset-0 col-md-1 col-lg-1"></div>
                                    <div class="col-xs-10 col-sm-10 col-xs-offset-1 col-md-5 col-lg-5">
                                        <div class="form-group">
                                            <label for="ComentarioOpinion">Opini&oacute;n</label>
                                        </div>
                                    </div>

                                    <div class="col-xs-10 col-sm-10 col-xs-offset-1 col-md-offset-0 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <label for="CalificacionOpinion">Puntuaci&oacute;n</label>
                                            <div id="puntuacionsatisfaccionevento">
                                                <div id="stars" class="starrr rating" data-rating="3"></div>
                                                <span id="countname">Regular</span>
                                                <input type="hidden" name="Rating" id="count" value="3"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row contenido-opinion">
                                    <div class="col-xs-10 col-sm-10 col-sm-offset-1 col-md-offset-0 col-md-3 col-lg-3">
                                    </div>
                                    <div class="col-xs-10 col-sm-10 col-sm-offset-1 col-md-offset-0 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <button name="RegistrarSatisfaccion" type="submit" class="btn btn-default pull-right" id="btnComentario">Enviar crítica</button>
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
            </div>
            <div class="modal" id="modal-container-361414" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <div id="map"></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
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
        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <!--Bootstrap-->   
        <script src="../Libs/Bootstrap/js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="../Libs/Bootstrap/js/bootstrap.min.js"></script>
        <!--Parsley-->
        <script src="../Libs/Customs/js/Parsley.js"></script>    
        <script src="../Libs/Customs/js/classie.js"></script>
        <script src="../Libs/Customs/js/gnmenu.js"></script>
        <script>
            new gnMenu(document.getElementById('gn-menu'));
        </script>

        <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
        <script type="text/javascript" src="../Libs/Customs/js/Rating.js" ></script>
        <script type="text/javascript" src="../Libs/Customs/js/graficos.js" ></script>
        <script src="../Libs/Customs/js/leaflet.js" type="text/javascript"></script>
        <script src="../Libs/Customs/js/leaflet.draw.js" type="text/javascript"></script>
        <script src="../Libs/Customs/js/funcionmapa.js" type="text/javascript"></script>
        <script>
            function mascomentarios(cantidad, limite)
            {
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Help',
                    data: {"accion": 'vermas', "cantidad": cantidad, "codigoevento": '<%=CodigoEvento%>', "limite": limite},
                    success: function(data) {
                        var datos = jQuery.parseJSON(data);
                        var items = [];
                        $.each(datos, function(key, val) {

                            items.push('<li class="from_user left">');
                            items.push('<div class="message_wrap"> ');
                            items.push('<span class="arrow"></span>');
                            items.push('<div class="info"> ');
                            items.push('<span class="name">Usuario: ' + val.usuario + ', Evento: ' + val.empresa + '</span>');
                            items.push('</div>');
                            items.push('<div class="text"> ');
                            items.push(val.comentario);
                            items.push('</div>');
                            items.push('</div>');
                            items.push('</li>');
                        });

                        $("#contenedor-mensajes").append(items.join(""));

                        if ($(".widget-content").height() >= 600)
                        {
                            $("#contenido").addClass("scrollcomentarios");
                        }
                        var total;
                        $.ajax({
                            type: 'POST',
                            url: '/TriggerEvent/Contr_Help',
                            data: {"accion": 'total', "codigoevento": '<%=CodigoEvento%>'},
                            success: function(data) {
                                total = data;
                                var cant = 0;
                                $("li.from_user").each(function(index) {
                                    cant++;
                                });
                                if (cant > total)
                                {
                                    $("#vermas").addClass("hidden");
                                }
                            }
                        });
                    }
                });
            }
            $(".vermascomentarios").click(function() {
                var i = 0;
                $("li.from_user").each(function(index) {
                    i++;
                });
                mascomentarios("5", i - 1);
            });
            var llenargrafica = function() {
                var idevento = "<%=CodigoEvento%>";
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Satisfaccion',
                    data: {'accion': 'calificacion_evento', 'id_evento': idevento},
                    success: function(data) {
                        var datos = jQuery.parseJSON(data);
                        $.each(datos, function(key, val) {
                            var data = [];
                            $("#txtcalicinco").html(val.uno);
                            $("#txtcalicuatro").html(val.dos);
                            $("#txtcalitres").html(val.tres);
                            $("#txtcalidos").html(val.cuatro);
                            $("#txtcaliuno").html(val.cinco);
                            data.push({value: val.uno, color: "#88B131"});
                            data.push({value: val.dos, color: "#9C0"});
                            data.push({value: val.tres, color: "#FFCF02"});
                            data.push({value: val.cuatro, color: "#ff9f02"});
                            data.push({value: val.cinco, color: "#FF6F31"});
                            //Get context with jQuery - using jQuery's .get() method.
                            var ctx = $("#myChart").get(0).getContext("2d");
                            //This will get the first returned node in the jQuery collection.
                            var myNewChart = new Chart(ctx).Doughnut(data);
                        });
                    }
                });
            }

            var abrirmodal = function(latitud, longitud, nombre) {
                $("#mostrarubicacion").click(function() {
                    $("#modal-container-361414").modal('show');
                    mostrarmapa(latitud, longitud, nombre);
                });
            }

            var datos_evento_detalle = function() {
                var idevento = "<%=CodigoEvento%>";
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Help',
                    data: {'accion': 'datos_evento_detalle', 'idevento': idevento},
                    success: function(data) {
                        var datos = jQuery.parseJSON(data);
                        $.each(datos, function(key, val) {
                            if (val.evento != null) {
                                $("#img_evento").attr("src", "../Libs/Customs/images/Evento/" + val.imagen);
                                $("#lbl_nombre_evento").html(val.evento);
                                $("#lbl_empresa_evento").html(val.empresa);
                                $("#lbl_rango_evento").html(val.rango);
                                $("#lbl_departamento_evento").html(val.nombre_departamento);
                                $("#lbl_ciudad_evento").html(val.nombre_ciudad);
                                $("#lbl_direccion_evento").prepend(val.direccion);
                                $("#lbl_fecha_evento").html(val.fecha);
                                $("#lbl_hora_evento").html(val.hora);
                                $("#lbl_descripcion_evento").after(val.descripcion);
                                abrirmodal(val.latitud, val.longitud, val.evento);
                            } else {
                                window.location.replace("/TriggerEvent/View/ConsultaEvento.jsp");
                            }
                        });
                    }
                });
            }
            <%if (Rol.equals("Cliente") || Rol.equals("Administrador")) {%>
            var satisfaccion_evento_usuario = function() {

                var idevento = "<%=CodigoEvento%>";
                var idusuario = "<%=CodigoUsuario%>";
                var elemento;
                var cant = 0;
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Satisfaccion',
                    data: {'accion': 'consulta_calificar_evento_android', 'codigoevento': idevento, "codigousuario": idusuario},
                    success: function(data) {
                        console.log(data);
                        if (data == "Comente") {
                            $("label [for=ComentarioOpinion]").html("hola");
                        } else {
                            var datos = jQuery.parseJSON(data);
                            $.each(datos, function(key, val) {
                                if (key == "Comentario") {
                                    if (val == null) {
                                        elemento = '<textarea name="comentario" class="form-control" rows="4" placeholder="Dejar un comentario es opcional." data-notblank="true" data-rangelength="[10,250]"></textarea>';
                                        $("label[for=ComentarioOpinion]").after(elemento);
                                    }
                                    else {
                                        $("label[for=ComentarioOpinion]").after("</br>"+val);
                                        cant ++;
                                    }
                                } else {
                                    if (val != null) {
                                        $("#puntuacionsatisfaccionevento").remove();
                                        elemento = '</br><span class="glyphicon glyphicon-star"></span>' + val + ' puntos de calificaci&oacute;n';
                                        $("label[for=CalificacionOpinion]").after(elemento);
                                        cant ++;
                                    }
                                }
                            });
                            if(cant == 2){
                                $("#btnComentario").remove();
                            }
                        }
                    }
                });
            }
            <%}%>
            $(document).ready(function() {
                datos_evento_detalle();
                llenargrafica();
                mascomentarios("5", "0");
            <%if (Rol.equals("Cliente") || Rol.equals("Administrador")) {%>satisfaccion_evento_usuario();<%}%>
                });
        </script>
        <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
        <%session.setAttribute("Mensaje", "");%>
    </body>
</html>