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
                    <ol class="breadcrumb">
                        <li><a href="EventoRecomendado.jsp">Inicio</a></li>
                        <li class="active">Registrar/Consultar gustos y ambientes</a></li>
                    </ol>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-xs-12">
                    <h1 class="Center">Registrar/Consultar gustos y ambientes</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-6 col-xs-offset-3 col-sm-4 col-sm-offset-4 col-md-offset-5 col-md-2">
                    <div class="form-group">
                        <a id="modal-Registrar" href="#modal-container-Registrar" role="button" class="btn btn-block defecto" data-toggle="modal">Registrar</a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1">
                    <div id="contenido-tabla" class="table-responsive">
                        <table id="table1" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>Imagen</th>
                                    <th>Nombre</th>
                                    <th>Tipo</th>
                                    <th>Estado</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody id="contenido-selecciones">
                            </tbody>
                        </table>
                    </div>			
                </div>
            </div>
            <div class="row">
                <form accept-charset="ISO-8859-1" data-validate="parsley" method="post" enctype="multipart/form-data" action="/TriggerEvent/Contr_Seleccion">
                    <div class="col-xs-12">
                        <div class="modal fade" id="modal-container-Registrar" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                        <h4 class="modal-title" id="myModalLabel">
                                            <center>Registrar una gusto o ambiente</center>
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2">
                                                <div class="form-group">
                                                    <label for="Nombre">Nombre</label>
                                                    <input id="Nombre" name="Nombre" class="form-control" type="text" data-notblank="true" data-rangelength="[3,30]" data-required="true">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2">
                                                <div class="form-group">
                                                    <label for="Tipo">Tipo</label>
                                                    <select name="Tipo" id="Tipos" class="form-control" data-required="true">
                                                        <option value="Gusto">Gusto</option>
                                                        <option value="Ambiente">Ambiente</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2">
                                                <div class="form-group">
                                                    <label for="Imagen">Imagen</label>
                                                    <input id="archivo" type="file" name="Imagen" data-required="true" accept="image/*"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                                        <button name="RegistrarSeleccion" type="submit" class="btn defecto">Registrar</button> 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="row">
                <form accept-charset="ISO-8859-1" data-validate="parsley" method="post" enctype="multipart/form-data" action="/TriggerEvent/Contr_Seleccion">
                    <div class="col-md-12">
                        <div class="modal fade" id="modal-container-Modificar" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                        <h4 class="modal-title" id="myModalLabel">
                                            <center>Modificar</center>
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2">
                                                <div class="form-group">
                                                    <input id="CodigoSeleccion" name="Codigo" class="form-control" type="hidden" readonly>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2">
                                                <div class="form-group">
                                                    <label for="Nombre">Nombre</label>
                                                    <input id="NombreSeleccion" name="Nombre" class="form-control" type="text" data-notblank="true" data-rangelength="[3,30]" data-required="true">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2">
                                                <div class="form-group">
                                                    <label for="Tipo">Tipo</label>
                                                    <select name="Tipo" id="TipoSeleccion" class="form-control" data-required="true">
                                                        <option value="Gusto">Gusto</option>
                                                        <option value="Ambiente">Ambiente</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2">
                                                <div class="form-group">
                                                    <label for="Estado">Estado</label>
                                                    <select name="Estado" id="EstadoSeleccion" class="form-control" data-required="true">
                                                        <option value="Aprobado">Aprobado</option>
                                                        <option value="Desaprobado">Desaprobado</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2">
                                                <div class="form-group">
                                                    <label for="Imagen">Imagen</label>
                                                    <input id="archivo" type="file" name="Imagen" data-required="true" accept="image/*"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2">
                                                <div class="form-group">
                                                    <label for="ImagenActual">Imagen Actual</label>
                                                    <img id="ImgActual" class="img-responsive imgseleccion" src="">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                                        <button name="ModificarSeleccion" type="submit" class="btn defecto">Modificar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
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
            function getseleccion()
            {
                $("#contenido-selecciones").html('<tr><td colspan="7"><center><img class="img-loading" src="../Libs/Customs/images/loading.gif" alt="cargando"/></center></td><tr>');
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Help',
                    data: {"accion": 'getseleccion'},
                    success: function(data) {
                        var datos = jQuery.parseJSON(data);
                        var items = [];
                        items.push('<table id="table1" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered">');
                        items.push('<thead>');
                        items.push('<tr>');
                        items.push('<th>Imagen</th>');
                        items.push('<th>Nombre</th>');
                        items.push('<th>Tipo</th>');
                        items.push('<th>Estado</th>');
                        items.push('<th></th>');
                        items.push('<th></th>');
                        items.push('<th></th>');
                        items.push('</tr>');
                        items.push('</thead>');
                        items.push('<tbody id="contenido-selecciones">');
                        $.each(datos, function(key, val) {
                            items.push('<tr>');
                            items.push('<td><img src="../Libs/Customs/images/Seleccion/' + val.image + '" class="img-responsive imgseleccion"></td>');
                            items.push('<td>' + val.nombre + '</td>');
                            items.push('<td>' + val.tipo + '</td>');
                            items.push('<td>' + val.estado + '</td>');
                            items.push('<td><center><a title="Modificar" class="modal-Modifica" href="#modal-container-Modificar" data-toggle="modal" data-id="' + val.codigo + '" data-image="' + val.image + '" data-nombre="' + val.nombre + '" data-tipo="' + val.tipo + '" data-estado="' + val.estado + '"><span class="glyphicon glyphicon-edit"></span><center></td>');
                            items.push('<td><center><a class="desaprobarseleccion" data-id="' + val.codigo + '" title="Desaprobar"><span class="glyphicon glyphicon-remove"></span></a></center></td>');
                            items.push('<td><center><a class="aprobarseleccion" data-id="' + val.codigo + '" title="Aprobar"><span class="glyphicon glyphicon-ok"></span></a></center></td>');
                            items.push('</tr>');
                        });
                        items.push('</tbody>');
                        items.push('</table>');
                        $("#contenido-tabla").html(items.join(""));
                    }
                }).done(function() {

                    $(".desaprobarseleccion").click(function() {
                        var Codigo = $(this).data('id');
                        $.ajax({
                            type: 'POST',
                            url: '/TriggerEvent/Contr_Help',
                            data: {"accion": 'desaprobarseleccion', "idseleccion": Codigo},
                            success: function(data) {
                                var datos = jQuery.parseJSON(data);
                                $.each(datos, function(key, val) {
                                    if (key === "1")
                                    {
                                        alertify.success(val);
                                        getseleccion();
                                    }
                                    else {
                                        alertify.error(val);
                                    }
                                });
                                document.body.style.cursor = 'default';
                            }
                        });
                    });
                    $(".aprobarseleccion").click(function() {
                        var Codigo = $(this).data('id');
                        $.ajax({
                            type: 'POST',
                            url: '/TriggerEvent/Contr_Help',
                            data: {"accion": 'aprobarseleccion', "idseleccion": Codigo},
                            success: function(data) {
                                var datos = jQuery.parseJSON(data);
                                $.each(datos, function(key, val) {
                                    if (key === "1")
                                    {
                                        alertify.success(val);
                                        getseleccion();
                                    }
                                    else {
                                        alertify.error(val);
                                    }
                                });
                                document.body.style.cursor = 'default';
                            }
                        });
                    });
                    $(".modal-Modifica").click(function() {
                        var Id = $(this).data('id');
                        var Name = $(this).data('nombre');
                        var Tipo = $(this).data('tipo');
                        var Estado = $(this).data('estado');
                        var Image = $(this).data('image');
                        $(".modal-body #CodigoSeleccion").val(Id);
                        $(".modal-body #NombreSeleccion").val(Name);
                        $(".modal-body #TipoSeleccion").val(Tipo);
                        $(".modal-body #ImgActual").attr("src", "../Libs/Customs/images/Seleccion/" + Image);
                        $('#EstadoSeleccion [value=' + Estado + ']').prop('selected', true);
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
                getseleccion();
                $("input, select").change(function() {
                    $(this).parsley('validate');
                });
            });
        </script>
        <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
        <%session.setAttribute("Mensaje", "");%>
    </body>
</html>

