<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionGeneral.jspf" %>
<%
    String Codigo = (String)session.getAttribute("Codigo");
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

        <div class="container">
            <br/>
            <br/>
            <br/>
            <div class="row clearfix">
                <div class="col-xs-12">
                    <ol class="breadcrumb">
                        <li class="active" >Inicio</li>
                    </ol>
                </div>
            </div>
            <div class="row" >
                <div class="col-xs-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                Eventos recomendados
                            </h3>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div id="contenido-eventos-recomendados" class="col-xs-12">

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
        <script>
            var eventos = 0;
            var cantidad = 0;
            var estado = "cargando";
            var geteventos = function(limite, cantidad) {
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Help',
                    data: {"accion": 'eventodatosrecomendados', "idusuario": '<%=Codigo%>', "limite": limite, "cantidad": cantidad},
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
                                items.push('<div class="soy-un-evento col-xs-12 col-sm-6 col-sm-offset-0 col-md-4">');
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
                            items.push('<div class="col-md-12">');
                            items.push('<center><img src="ImagenEvento.jsp?Codigo=' + val.codigo + '" class="img-thumbnail imgevento"/></center>');
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
                            items.push('<div class="panel-footer">');
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
                        $("#contenido-eventos-recomendados").append(items.join(""));
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
                    data: {"accion": 'totaleventorecomendado', "idusuario": '<%=Codigo%>'},
                    success: function(data) {
                        cantidad = parseInt(data);
                    }
                });
            };
            $(document).ready(function() {
                geteventos("0", "9");
                totalevento();
            });
        </script>
        <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
        <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
        <%session.setAttribute("Mensaje", "");%>
    </body>
</html>


