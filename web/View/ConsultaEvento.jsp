<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionGeneral.jspf" %>
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
        <link href="../Libs/Customs/css/guidely.css" rel="stylesheet" type="text/css"/>
        <script src="../Libs/Customs/js/modernizr.custom.js"></script>
    </head>
    <body>
        <%if (Rol.equals("Administrador")) {%>
        <%@include file="../WEB-INF/jspf/MenuAdministradorCustom.jspf" %>
        <%} else if (Rol.equals("Cliente")) {%>
        <%@include file="../WEB-INF/jspf/MenuClienteCustom.jspf" %>
        <%} else if (Rol.equals("Empresa")) {%>
        <%@include file="../WEB-INF/jspf/MenuEmpresaCustom.jspf" %>
        <%}%>

        <div class="container">
            <br/>
            <br/>
            <br/>
            <div class="row clearfix">
                <div class="col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="EventoRecomendado.jsp">Inicio</a></li>
                        <li class="active">Eventos</a></li>
                    </ol>
                </div>
            </div>
            <div class="row" >
                <div class="col-xs-12" id="target-1">
                    <div class="panel panel-primary">
                        <div class="panel-heading" id="target-2">
                            <h3 class="panel-title">
                                Eventos 
                                <%if (Rol.equals("Administrador")) {%>
                                <a id="link_todos_eventos" title="Todos los eventos" href="ConsultaTodosEventos.jsp" class="pull-right"><span class="glyphicon glyphicon-book aligncerar"></span></a>
                                    <%}%>
                                <a title="Ubicaci&oacute;n de eventos del mes" href="UbicacionEventos.jsp" class="pull-right"><span class="glyphicon glyphicon-screenshot aligncerar"></span></a>
                            </h3>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div id="contenido-eventos" class="col-xs-12">

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
        <script src="../Libs/Bootstrap/js/jquery-1.10.2.min.js"></script>    
        <script src="../Libs/Bootstrap/js/bootstrap.min.js"></script>
        <!--Parsley-->
        <script src="../Libs/Customs/js/classie.js"></script>
        <script src="../Libs/Customs/js/gnmenu.js"></script>
        <script>
            new gnMenu(document.getElementById('gn-menu'));
        </script>
        <script src="../Libs/Customs/js/guidely.min.js" type="text/javascript"></script>

        <script>
            $("#guia").click(function() {
                guidely.init({welcome: true, startTrigger: false});
            });
            var eventos = 0;
            var cantidad = 0;
            var estado = "cargando";
            var geteventos = function(limite, cantidad) {
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Help',
                    data: {"accion": 'eventodatosprincipales', "limite": limite, "cantidad": cantidad},
                    success: function(data) {
                        var datos = jQuery.parseJSON(data);
                        var items = [];
                        var fila = 0;
                        $.each(datos, function(key, val) {
                            fila++;
                            if (fila == 1)
                            {
                                items.push('<div class="row">');
                                items.push('<div class="col-xs-10 col-xs-offset-1 col-sm-12 col-sm-offset-0">');
                                items.push('<div class="row">');
                            }
                            if (fila === 3) {
                                items.push('<div class="soy-un-evento col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0 col-md-4">');
                            } else {
                                items.push('<div id="target-3" class="soy-un-evento col-xs-12 col-sm-6 col-sm-offset-0 col-md-4">');
                            }
                            items.push('<div class="panel panel-primary">');
                            items.push('<div class="panel-heading">');
                            items.push('<h3 class="panel-title">');
                            items.push(val.nombre);
                            items.push('<a title="Ver m&aacute;s" href="DetalleEvento.jsp?CodigoEvento=' + val.codigo + '"><span class="glyphicon glyphicon-log-in close aligncerar"></span></a>');
                            items.push('</h3>');
                            items.push('</div>');
                            items.push('<div class="panel-body">');
                            items.push('<div class="row">');
                            items.push('<div id="target-4" class="col-md-12">');
                            items.push('<center><img src="../Libs/Customs/images/Evento/' + val.image + '" class="img-thumbnail imgevento"/></center>');
                            items.push('</div>');
                            items.push('</div>');
                            items.push('<div class="row">');
                            items.push('<div class="col-md-12">');
                            items.push('<label for="Creador">Creador: ' + val.creador + '</label>');
                            items.push('</div>');
                            items.push('</div>');
                            items.push('<div class="row">');
                            items.push('<div class="col-md-12">');
                            items.push('<label for="Ciudad">Ciudad: ' + val.ciudad + '</label>');
                            items.push('</div>');
                            items.push('</div>');
                            items.push('<div class="row">');
                            items.push('<div class="col-md-12">');
                            items.push('<label for="Hora">Hora: ' + val.hora + '</label>');
                            items.push('</div>');
                            items.push('</div>');
                            items.push('<div class="row">');
                            items.push('<div class="col-md-12">');
                            items.push('<label for="Fecha">Fecha: ' + val.fecha + '</label>');
                            items.push('</div>');
                            items.push('</div>');
                            items.push('</div>');
                            items.push('<div id="target-5" class="panel-footer">');
                            items.push('<label for="Espacio"> </label>');
                            items.push('<p class="pull-right calificacionevento" data-id="' + val.codigo + '">');
                            var comentario, calificacion;
                            val.comentario == null ? comentario = 0 : comentario = val.comentario;
                            val.calificacion == null ? calificacion = 0 : calificacion = val.calificacion;
                            items.push('<span title="Calificaci&oacute;n" class="glyphicon glyphicon-star">' + calificacion + '</span>');
                            items.push('<span title="Comentarios" class="glyphicon glyphicon-comment">' + comentario + '</span>');
                            items.push('</p>');
                            items.push('</div>');
                            items.push('</div>');
                            items.push('</div>');

                            if (fila == 3)
                            {
                                fila = 0;
                                items.push('</div>');
                                items.push('</div>');
                                items.push('</div>');
                            }
                        });
                        if (fila < 3 && fila > 0)
                        {
                            for (var i = fila; i < 3; i++)
                            {
                                items.push('<div class="col-xs-12 col-sm-6 col-sm-offset-0 col-md-4">');
                                items.push('</div>');
                            }
                            items.push('</div>');
                            items.push('</div>');
                        }
                        $("#contenido-eventos").append(items.join(""));
                    }
                }).done(function() {
                    $(".calificacionevento").click(function() {
                        var Id = $(this).data('id');
                        window.location.replace("/TriggerEvent/View/DetalleEvento.jsp?CodigoEvento=" + Id + "#titulo-opinion");
                    });
                    var i = 0;
                    $(".soy-un-evento").each(function() {
                        i++;
                    });
                    eventos = i;
                    estado = "listo";
                });
            };
            $(window).scroll(function() {
                if ($(window).scrollTop() >= ($(document).height() - $(window).height()) * 0.3) {
                    if (estado === "listo")
                    {
                        if (eventos < cantidad)
                        {
                            estado = "cargando";
                            geteventos(eventos, "9");
                        }
                    }
                }
            });
            var totalevento = function() {
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Help',
                    data: {"accion": 'totalevento'},
                    success: function(data) {
                        cantidad = parseInt(data);
                    }
                });
            }
            <%if (Rol.equals("Administrador")) {%>
            var getCantidadEventosPendiente = function() {

                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Help',
                    data: {"accion": 'cantidad_evento_pendiente'},
                    success: function(data) {
                        var eve_cantidad = parseInt(data);
                        if (eve_cantidad > 0) {
                            var items = [];
                            items.push('<a title="Eventos pendientes" href="CEventoPendiente.jsp" class="pull-right" >');
                            items.push('<span class="glyphicon glyphicon-bell aligncerar animacion-bell" title="Eventos pendientes">');
                            items.push('<span class="badge">' + eve_cantidad + '</span></span>  </a>');
                            $("#link_todos_eventos").after(items.join(""));
                        }
                    }
                });
            }
            <%}%>
            $(document).ready(function() {
                geteventos("0", "9");
                totalevento();
            <%if (Rol.equals("Administrador")) {%> getCantidadEventosPendiente();<%}%>
            });
        </script>
        <script>
            <%if (Rol.equals("Administrador")) {%>
            var mensajeguia = "El ícono a la izquierda le permite conocer la ubicación geográfica de los eventos del mes, mientras que el ícono derecho le permite consultar todos los eventos registrados hasta el momento. Nota: en caso de haber eventos pendientes aparecerá otro ícono junto con un número que indica la cantidad de eventos por aprobación.";
            <%} else {%>
            var mensajeguia = "El ícono le permite conocer la ubicación geográfica de los eventos del mes.";
            <%}%>

            $(function() {

                guidely.add({
                    attachTo: '#target-1'
                    , anchor: 'bottom-left'
                    , title: 'Todos los eventos'
                    , text: 'A continuación se le muestran todos los eventos registrados. Estos eventos aparecen sin importar los gustos y ambientes musicales que haya escogido.'
                });

                guidely.add({
                    attachTo: '#target-2'
                    , anchor: 'bottom-right'
                    , title: 'Ubicación y Todos los eventos'
                    , text: mensajeguia
                });

                guidely.add({
                    attachTo: '#target-3'
                    , anchor: 'top-right'
                    , title: 'Ver más'
                    , text: 'Este ícono le permite profundizar la información de un evento.'
                });

                guidely.add({
                    attachTo: '#target-4'
                    , anchor: 'top-left'
                    , title: 'Ver más'
                    , text: 'El panel muestra la información esencial de cada evento.'
                });

                guidely.add({
                    attachTo: '#target-5'
                    , anchor: 'bottom-right'
                    , title: 'Ver más'
                    , text: 'Estos íconos le permiten ver los comentarios y la calificación de un evento.'
                });
            });
        </script>
        <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
        <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
        <%session.setAttribute("Mensaje", "");%>
    </body>
</html>

