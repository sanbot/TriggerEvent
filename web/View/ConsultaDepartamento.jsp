<%-- 
Document   : ConsultaUsuario
Created on : 18-mar-2014, 14:17:00
Author     : ADSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionAdministrador.jspf" %>
<%
Contr_Consultar usu = new Contr_Consultar();
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
    <%@include file="../WEB-INF/jspf/MenuAdministrador.jspf" %>
    <div class="container">
        <br/>
	<br/>
	<br/>
        <div class="row clearfix">
            <div class="col-xs-12">
                <ol class="breadcrumb">
                    <li><a href="index.jsp">Inicio</a></li>
                    <li class="active">Registrar y Consultar departamentos</a></li>
                </ol>
            </div>
        </div>
        <div class="row clearfix">
            <div class="col-xs-12">
                <h1 class="Center">Registrar y Consultar departamentos</h1>
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
                <div class="table-responsive">
                    <table id="table1" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody id="contenido-departamentos">
                        </tbody>
                    </table>
                </div>			
            </div>
        </div>
        <div class="row">
            <form data-validate="parsley" method="post" action="/TriggerEvent/Contr_Departamento">
                <div class="col-xs-12">
                    <div class="modal fade" id="modal-container-Registrar" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <h4 class="modal-title" id="myModalLabel">
                                        <center>Registrar un departamento</center>
                                    </h4>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-xs-8 col-xs-offset-2">
                                            <div class="form-group">
                                                <label for="Nombre">Nombre</label>
                                                <input id="NombreDepartamento" name="Nombre" class="form-control" type="text" data-notblank="true" data-rangelength="[3,30]" data-required="true">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                                    <button name="RegistrarDepartamento" type="submit" class="btn defecto">Registrar</button> 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="row">
            <form data-validate="parsley" method="post" action="/TriggerEvent/Contr_Departamento">
                <div class="col-xs-12">
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
                                                <input id="ConCodigo" name="Codigo" class="form-control" type="hidden" readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-8 col-xs-offset-2">
                                            <div class="form-group">
                                                <label for="Nombre">Nombre</label>
                                                <input id="ConNombre" name="Nombre" class="form-control" type="text" data-notblank="true" data-rangelength="[3,30]" data-required="true">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                                    <button name="ModificarDepartamento" type="submit" class="btn defecto">Modificar</button>
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
    	new gnMenu( document.getElementById( 'gn-menu' ) );
    </script>
    <script>
    function getdepartamentos() {
        $.ajax({
            type: 'POST',
            url: '/TriggerEvent/Contr_Help',
            data: {"accion": 'getdepartamentos'},
            success: function(data) {
                var opcion = [];
                var datos = jQuery.parseJSON(data);
                $.each(datos, function(key, val) {
                    opcion.push('<tr>');
                    opcion.push('<td>'+val.departamento+'</td>');
                    opcion.push('<td><center><a class="modal-Modifica" href="#modal-container-Modificar" data-toggle="modal" data-id="'+val.codigo+'" data-nombre="'+val.departamento+'"><span class="glyphicon glyphicon-edit"></span><center></td>');
                    opcion.push('</tr>');
                });
                $("#contenido-departamentos").html(opcion.join(""));
            }
        }).done(function(){
            $('#table1').dataTable({
                "sPaginationType": "bs_normal"
//                 "sPaginationType": "bs_four_button"
//                 "sPaginationType": "bs_full"
//                 "sPaginationType": "bs_two_button"
            }); 
            $('#table1').each(function(){
                var datatable = $(this);
                // SEARCH - Add the placeholder for Search and Turn this into in-line form control
                var search_input = datatable.closest('.dataTables_wrapper').find('div[id$=_filter] input');
                search_input.attr('placeholder', 'Buscar');
                search_input.addClass('form-control input-sm');
                // LENGTH - Inline-Form control
                var length_sel = datatable.closest('.dataTables_wrapper').find('div[id$=_length] select');
                length_sel.addClass('form-control input-sm');
            });
            $("a.modal-Modifica").click(function(){
                var Id = $(this).data('id');
                var Name = $(this).data('nombre');
                $(".modal-body #ConCodigo").val( Id );
                $(".modal-body #ConNombre").val( Name );
            });
        });
    }
    
    $(document).ready(function () {
        $("#contenido-departamentos").html('<tr><td colspan="2"><center><img class="img-loading" src="../Libs/Customs/images/loading.gif" alt="cargando"/></center></td><tr>');
        getdepartamentos();
        
    });
    </script>
    <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
    <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
    <%session.setAttribute("Mensaje", "");%>
</body>
</html>

