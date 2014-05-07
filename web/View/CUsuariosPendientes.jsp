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
        <%@include file="../WEB-INF/jspf/MenuAdministrador.jspf" %>
        <div class="container">
            <br/>
            <br/>
            <br/>
            <div class="row clearfix">
                <div class="col-md-12">
                    <div class="col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="index.jsp">Inicio</a></li>
                            <li><a href="ConsultaUsuario.jsp">Registrar y Consultar Usuarios</a></li>
                            <li class="active">Consultar usuarios pendientes</a></li>
                        </ol>
                    </div>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-xs-12">
                    <h1 class="Center">Consultar usuarios pendientes</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div id="contenido-tabla" class="table-responsive">

                        <table id="table1" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>NOMBRE</th>
                                    <th>TIPO USUARIO</th>
                                    <th>TIPO DOCUMENTO</th>
                                    <th>NÚMERO DOCUMENTO</th>
                                    <th>ESTADO</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody id="contenido-usuarios-pendientes">

                            </tbody>
                        </table>
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
            function usuariospendientes() {
                $("#contenido-usuarios-pendientes").html('<tr><td colspan="8"><center><img class="img-loading" src="../Libs/Customs/images/loading.gif" alt="cargando"/></center></td><tr>');
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Usuarios',
                    data: {"accion": 'usuariospendientes'},
                    success: function(data) {
                        var datos = jQuery.parseJSON(data);
                        var items = [];
                        items.push('<table id="table1" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered">');
                        items.push('<thead>');
                        items.push('<tr>');
                        items.push('<th>NOMBRE</th>');
                        items.push('<th>TIPO USUARIO</th>');
                        items.push('<th>TIPO DOCUMENTO</th>');
                        items.push('<th>NÚMERO DOCUMENTO</th>');
                        items.push('<th>ESTADO</th>');
                        items.push('<th></th>');
                        items.push('<th></th>');
                        items.push('<th></th>');
                        items.push('</tr>');
                        items.push('</thead>');
                        items.push('<tbody id="contenido-usuarios-pendientes">');
                        $.each(datos, function(key, val) {
                            items.push('<tr>');
                            items.push('<td>' + val.nombre + '</td>');
                            items.push('<td>' + val.tipo + '</td>');
                            items.push('<td>' + val.tipodocumento + '</td>');
                            items.push('<td>' + val.numerodocumento + '</td>');
                            items.push('<td>' + val.estado + '</td>');
                            items.push('<td><center><a title="Desaprobar" class="estadousuario" data-id="' + val.codigo + '" data-estado="Desaprobado"><span class="glyphicon glyphicon-remove"></span></a></center></td>');
                            items.push('<td><center><a title="Aprobar" class="estadousuario" data-id="' + val.codigo + '" data-estado="Aprobado"><span class="glyphicon glyphicon-ok"></span></a></center></td>');
                            items.push('<td><center><a href="CUsuario.jsp?Codigo=' + val.codigo + '"><span class="glyphicon glyphicon-log-in"></span><center></td>');
                            items.push('</tr>');
                        });
                        items.push('</tbody>');
                        items.push('</table>');
                        $("#contenido-tabla").html(items.join(""));
                    }
                }).done(function() {
                    $(".estadousuario").click(function() {
                        var codigo = $(this).data('id');
                        var estado = $(this).data('estado');
                        document.body.style.cursor = 'wait';
                        $.ajax({
                            type: 'POST',
                            url: '/TriggerEvent/Contr_Usuarios',
                            data: {"accion": 'cambiarestadousuario', "codigousuario": codigo, "estadousuario": estado},
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
                            usuariospendientes();
                        });
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
                usuariospendientes();
            });
        </script>
        <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
        <%session.setAttribute("Mensaje", "");%>
    </body>
</html>


