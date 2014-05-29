<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionEmpresa.jspf" %>
<%
    String nit = (String) session.getAttribute("No_Documento");
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
                    <ol class="breadcrumb">
                        <li><a href="EventoRecomendado.jsp">Inicio</a></li>
                        <li class="active">Eventos</a></li>
                    </ol>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-xs-12">
                    <h1 class="Center">Eventos</h1>
                </div>
            </div>
            <div id="unbtn" class="row">
                <div class="col-xs-6 col-xs-offset-3 col-sm-4 col-sm-offset-4 col-md-offset-4 col-md-4">
                    <div class="form-group">
                        <a href="RegistrarEvento.jsp" role="button" class="btn btn-block defecto">Registrar Evento</a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div id="contenido-tabla" class="table-responsive">
                    </div>			
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
                                            <center>Cancelar evento: <span id="NombreMis_Evento"></span></center>
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2">
                                                <div class="form-group">
                                                    <input id="CodigoMis_Evento" name="Codigo" type="hidden" readonly>
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
                                        <button name="DesactivarEventoEmpresa" type="submit" class="btn defecto">Cancelar</button> 
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
            var getmiseventos = function() {
                $("#contenido-tabla").html('<center><img class="img-loading" src="../Libs/Customs/images/loading.gif" alt="cargando"/></center>');
                var Codigo = '<%=nit%>';
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Help',
                    data: {"accion": 'getmiseventos', "nit": Codigo},
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
                        items.push('<th>Eestado</th>');
                        items.push('<th></th>');
                        items.push('<th></th>');
                        items.push('<th></th>');
                        items.push('</tr>');
                        items.push('</thead>');
                        items.push('<tbody id="contenido-mis-eventos">');
                        $.each(datos, function(key, val) {
                            items.push('<tr>');
                            items.push('<td><img src="ImagenEvento.jsp?Codigo=' + val.codigo + '" class="img-responsive imgseleccion"/></td>');
                            items.push('<td>' + val.nombre + '</td>');
                            items.push('<td>' + val.fecha + '</td>');
                            items.push('<td>' + val.hora + '</td>');
                            items.push('<td>' + val.creador + '</td>');
                            items.push('<td>' + val.ciudad + '</td>');
                            items.push('<td>' + val.estado + '</td>');
                            if(val.estado !== "Aprobado")
                            {
                                items.push('<td><center><a title="Ver m&aacute;s" href="DetalleEventoPendiente.jsp?CodigoEvento=' + val.codigo + '"><span class="glyphicon glyphicon-log-in"></span></a><center></td>');
                            }
                            else
                            {
                                items.push('<td><center><a title="Ver m&aacute;s" href="DetalleEvento.jsp?CodigoEvento=' + val.codigo + '"><span class="glyphicon glyphicon-log-in"></span></a><center></td>');
                            }
                            
                            items.push('<td><center><a title="Clasificar" href="RClasificacionEvento.jsp?CodigoEvento=' + val.codigo + '"><span class="glyphicon glyphicon-list"></span></a><center></td>');
                            items.push('<td><center><a title="Cancelar" class="modal-desactivarevento" href="#modal-container-Desactivar" role="button" data-toggle="modal" data-id="' + val.codigo + '" data-nombre="' + val.nombre + '" data-creador="' + val.creador + '"><span class="glyphicon glyphicon-remove"></span></a></center></td>');
                            items.push('</tr>');
                        });
                        items.push('</tbody>');
                        items.push('<table>');
                        $("#contenido-tabla").html(items.join(""));
                    }
                }).done(function() {
                    $(".modal-desactivarevento").click(function() {
                        var Id = $(this).data('id');
                        var Name = $(this).data('nombre');
                        $(".modal-body #CodigoMis_Evento").val(Id);
                        $(".modal-title #NombreMis_Evento").text(Name);
                    });
                    $('#table1').dataTable({
                        "sPaginationType": "bs_normal"
                                // "sPaginationType": "bs_four_button"
                                // "sPaginationType": "bs_full"
                                // "sPaginationType": "bs_two_button"
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
            };
            var cantidadincompletos = function() {
                var Codigo = '<%=nit%>';
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Help',
                    data: {"accion": 'getcantidadeventoincompleto', "nit": Codigo},
                    success: function(data) {
                        var num = parseInt(data);
                        if (num > 0)
                        {
                            $("#dosbtn").removeClass('hide');
                            $("#badge").append('<span  class="badge pull-right animacion-bell">' + num + '</span>');
                        }
                        else
                        {
                            $("#unbtn").removeClass('hide');
                        }
                    }
                });
            };
            $(document).ready(function() {
                getmiseventos();
                cantidadincompletos();
            });
        </script>
        <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
        <%session.setAttribute("Mensaje", "");%>
    </body>
</html>