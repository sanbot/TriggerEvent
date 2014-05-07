<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionAdministrador.jspf" %>
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
        <link type="text/css" rel="stylesheet" href="../Libs/Customs/DataTables/css/datatables.css" media="all">

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
                    <div class="form-group">
                        <ol class="breadcrumb">
                            <li><a href="index.jsp">Inicio</a></li>
                            <li><a href="ConsultaEvento.jsp">Eventos</a></li>
                            <li class="active">Consultar eventos pendientes</a></li>
                        </ol>
                    </div>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-xs-12">
                    <h1 class="Center">Consultar eventos Pendientes</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div id="contenido-tabla" class="table-responsive">
                        <table id="table1" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>Imagen</th>
                                    <th>Nombre</th>
                                    <th>Fecha</th>
                                    <th>Hora</th>
                                    <th>Creador</th>
                                    <th>Ciudad</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody id="contenido-eventos-pendientes">
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <form data-validate="parsley" enctype="multipart/form-data" method="post" action="/TriggerEvent/Contr_Evento">
                        <div class="col-xs-12">
                            <div class="modal fade" id="modal-container-Desactivar" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                            <h4 class="modal-title" id="myModalLabel">
                                                <center>Desactivar el evento: <span id="NombreEvento"></span></center>
                                            </h4>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="col-xs-8 col-xs-offset-2">
                                                    <div class="form-group">
                                                        <input id="CodigoEvento" name="Codigo" type="hidden" readonly>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-xs-10 col-xs-offset-1">
                                                    <div class="form-group">
                                                        <label for="Motivo">Motivo</label>
                                                        <textarea id="Motivo" name="Motivo" class="form-control" data-rangelength="[6,250]" rows="7" data-required="true"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                                            <button name="DesactivarEvento" type="submit" class="btn defecto">Desaprobar</button> 
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- FOOTER -->
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
        <script src="../Libs/Bootstrap/js/holder.js"></script>
        <!--Parsley-->
        <script src="../Libs/Customs/js/Parsley.js"></script>    
        <script src="../Libs/Customs/js/classie.js"></script>
        <script src="../Libs/Customs/js/gnmenu.js"></script>
        <script type="text/javascript" src="../Libs/Customs/DataTables/js/jquery.dataTables.js" charset="UTF-8"></script>
        <script type="text/javascript" src="../Libs/Customs/DataTables/js/datatables.js" charset="UTF-8"></script>
        <script>
            new gnMenu(document.getElementById('gn-menu'));
        </script>

        <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
        <script type="text/javascript">
            function eventospendientes() {
                $("#contenido-tabla").html('<tr><td colspan="9"><center><img class="img-loading" src="../Libs/Customs/images/loading.gif" alt="cargando"/></center></td><tr>');
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Help',
                    data: {"accion": 'geteventospendientes'},
                    success: function(data) {
                        var datos = jQuery.parseJSON(data);
                        var items = [];
                        items.push('<table id="table1" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered">');
                        items.push('<thead>');
                        items.push('<tr>');
                        items.push('<th>Imagen</th>');
                        items.push('<th>Nombre</th>');
                        items.push('<th>Fecha</th>');
                        items.push('<th>Hora</th>');
                        items.push('<th>Creador</th>');
                        items.push('<th>Ciudad</th>');
                        items.push('<th></th>');
                        items.push('<th></th>');
                        items.push('<th></th>');
                        items.push('</tr>');
                        items.push('</thead>');
                        items.push('<tbody id="contenido-eventos-pendientes">');
                        $.each(datos, function(key, val) {
                            items.push('<tr>');
                            items.push('<td><img src="ImagenEvento.jsp?Codigo=' + val.codigo + '" class="img-responsive imgseleccion"/></td>');
                            items.push('<td>' + val.nombre + '</td>');
                            items.push('<td>' + val.fecha + '</td>');
                            items.push('<td>' + val.hora + '</td>');
                            items.push('<td>' + val.creador + '</td>');
                            items.push('<td>' + val.ciudad + '</td>');
                            items.push('<td><center><a title="Desaprobar" class="modal-desactivarevento" href="#modal-container-Desactivar" role="button" data-toggle="modal" data-id="' + val.codigo + '" data-nombre="' + val.nombre + '" data-creador="' + val.creador + '"><span class="glyphicon glyphicon-remove"></span></a></center></td>');
                            items.push('<td><center><a class="aprobarevento" title="Aprobar" data-id=' + val.codigo + '><span class="glyphicon glyphicon-ok"></span></a></center></td>');
                            items.push('<td><center><a title="Ver m&aacute;s" href="DetalleEvento.jsp?CodigoEvento=' + val.codigo + '&Pendiente=true"><span class="glyphicon glyphicon-log-in"></span></a><center></td>');
                            items.push('</tr>');
                        });
                        items.push('</tbody>');
                        items.push('</table>');
                        $("#contenido-tabla").html(items.join(""));
                    }
                }).done(function() {
                    $(".aprobarevento").click(function() {
                        var Id = $(this).data('id');
                        document.body.style.cursor = 'wait';
                        $.ajax({
                            type: 'POST',
                            url: '/TriggerEvent/Contr_Help',
                            data: {"accion": 'aprobarevento', "codigoevento": Id},
                            success: function(data) {
                                var datos = jQuery.parseJSON(data);
                                $.each(datos, function(key, val) {
                                    if (val.tipomensaje === "NODio")
                                    {
                                        alertify.error(val.mensaje);
                                    }
                                    else if (val.tipomensaje === "Dio")
                                    {
                                        alertify.success(val.mensaje);
                                    }
                                });
                            }
                        }).done(function() {
                            document.body.style.cursor = 'default';
                            eventospendientes();
                        });
                    });
                    $(".modal-desactivarevento").click(function() {
                        var Id = $(this).data('id');
                        var Name = $(this).data('nombre');
                        $(".modal-body #CodigoEvento").val(Id);
                        $(".modal-title #NombreEvento").text(Name);
                    });
                    $('#table1').dataTable({
                        "sPaginationType": "bs_normal",
                        // "sPaginationType": "bs_four_button"
                        // "sPaginationType": "bs_full"
                        // "sPaginationType": "bs_two_button"
                        "bRetrieve": true
                    });
                    $('#table1').each(function() {
                        var datatable = $(this);
                        // SEARCH - Add the placeholder for Search and Turn this into in-line form control
                        var search_input = datatable.closest('.dataTables_wrapper').find('div[id$=_filter] input');
                        search_input.attr('placeholder', 'Buscar');
                        search_input.addClass('form-control input-sm');
                        // LENGTH - Inline-Form control
                        var length_sel = datatable.closest('.dataTables_wrapper').find('div[id$=_length] select');
                        length_sel.addClass('form-control input-sm');
                    });

                });
            }

            $(document).ready(function() {
                eventospendientes();
            });
        </script>
        <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
        <%session.setAttribute("Mensaje", "");%>
    </body>
</html>


