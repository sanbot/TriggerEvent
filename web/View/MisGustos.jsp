<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionGeneral.jspf" %>
<%
    String Codigo = (String) session.getAttribute("Codigo");
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
        <%
        if (Rol.equals("Administrador")) {%>
        <%@include file="../WEB-INF/jspf/MenuAdministrador.jspf" %>
        <%
    } else if (Rol.equals("Cliente")) {%>
        <%@include file="../WEB-INF/jspf/MenuCliente.jspf" %>
        <%} else if (Rol.equals("Empresa")) {%>
        <%@include file="../WEB-INF/jspf/MenuEmpresa.jspf" %>
        <%}%>
        <div class="container">
            <br/>
            <br/>
            <br/>
            <div class="row">
                <div class="col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="index.jsp">Inicio</a></li>
                        <li class="active">Mis gustos</a></li>
                    </ol>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-xs-12">
                    <h1 class="Center">Mis Gustos</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-6 col-xs-offset-3 col-sm-4 col-sm-offset-4 col-md-offset-5 col-md-2">
                    <div class="form-group">
                        <a href="RegistrarGustos.jsp" class="btn btn-block defecto" >Agregar Gustos</a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1">
                    <div id="contenido-tabla" class="table-responsive">
                        <table id="table" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>Nombre</th>
                                    <th>Tipo</th>
                                    <th>Imagen</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody id="contenido-mis-gustos">
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
            function misgustos()
            {
                $("#contenido-mis-gustos").html('<tr><td colspan="4"><center><img class="img-loading" src="../Libs/Customs/images/loading.gif" alt="cargando"/></center></td><tr>');
                var Codigo = '<%=Codigo%>';
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Help',
                    data: {"accion": 'getgustos', "codigo": Codigo},
                    success: function(data) {
                        var datos = jQuery.parseJSON(data);
                        var items = [];
                        items.push('<table id="table" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered">');
                        items.push('<thead>');
                        items.push('<tr>');
                        items.push('<th>Nombre</th>');
                        items.push('<th>Tipo</th>');
                        items.push('<th>Imagen</th>');
                        items.push('<th></th>');
                        items.push('</tr>');
                        items.push('</thead>');
                        items.push('<tbody id="contenido-mis-gustos">');
                        $.each(datos, function(key, val) {
                            items.push('<tr>');
                            items.push('<td>' + val.nombre + '</td>');
                            items.push('<td>' + val.tipo + '</td>');
                            items.push('<td><img src="Imagen.jsp?Codigo=' + val.codigo + '" class="img-responsive imgseleccion"></td>');
                            items.push('<td><center><a title="Remover" class="removergusto" data-id="' + val.codigo + '"><span class="glyphicon glyphicon-remove"></span><center></td>');
                            items.push('</tr>');
                        });
                        items.push('</tbody>');
                        items.push('</table>');
                        $("#contenido-tabla").html(items.join(""));
                    }
                }).done(function() {
                    $(".removergusto").click(function() {
                        document.body.style.cursor = 'wait';
                        var Id = $(this).data('id');
                        $.ajax({
                            type: 'POST',
                            url: '/TriggerEvent/Contr_Help',
                            data: {"accion": 'removergusto', "codigo": Id, "idusuario": '<%=Codigo%>'},
                            success: function(data) {
                                var datos = jQuery.parseJSON(data);
                                $.each(datos, function(key, val) {
                                    if (key === "1")
                                    {
                                        alertify.success(val);
                                        misgustos();
                                    }
                                    else {
                                        alertify.error(val);
                                    }
                                });
                                document.body.style.cursor = 'default';
                            }
                        });
                    });
                    $('#table').dataTable({
                        "sPaginationType": "bs_normal",
                        // "sPaginationType": "bs_four_button"
                        // "sPaginationType": "bs_full"
                        // "sPaginationType": "bs_two_button"
                        "bRetrieve": true
                    });
                    $('#table').each(function() {
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
                misgustos();
            });
        </script>
        <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
        <%session.setAttribute("Mensaje", "");%>
    </body>
</html>

