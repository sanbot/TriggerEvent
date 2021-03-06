<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionAdministradorEmpresa.jspf" %>
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
        <link type="text/css" rel="stylesheet" href="../Libs/Customs/DatePicker/css/bootstrap-datetimepicker.css">
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

        <div class="container">
            <br/>
            <br/>
            <br/>
            <div class="row clearfix">
                <div class="col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="EventoRecomendado.jsp">Inicio</a></li>
                        <li><a href="ConsultaEvento.jsp">Eventos</a></li>
                        <li class="active">Registrar evento</li>
                    </ol>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-xs-12">
                    <h1 class="Center">Registrar evento</h1>
                </div>
            </div>
            <form accept-charset="ISO-8859-1" id="search" data-validate="parsley" enctype="multipart/form-data" method="post" action="/TriggerEvent/Contr_Evento">
                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="Creador">Creador del evento</label>

                            <select id="lista_empresas" name="Creador" tabindex="1" data-placeholder="" class="form-control" data-required="true" >
                                <option value="<%=Codigo%>"><%=Nombre%></option>
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="Nombre">Nombre del evento</label>
                            <input name="Nombre" type="text" class="form-control" id="NombreEvento" data-rangelength="[3,50]" data-notblank="true" data-required="true" />
                        </div>
                    </div>
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-2 col-md-offset-0 col-md-2 col-lg-2">
                        <div class="form-group">
                            <label for="Rango">Precio m&iacute;nimo</label>
                            <input name="Rango" type="text" class="form-control" id="RangoEvento" placeholder="10000" data-type="number" data-required="true" data-notblank="true" data-rangelength="[4,5]" />
                        </div>
                    </div>
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-2 col-md-offset-0 col-md-2 col-lg-2">
                        <div class="form-group">
                            <label for="Rango">Precio m&aacute;ximo</label>
                            <input name="Rangomaximo" type="text" class="form-control" id="RangoEventomaximo" placeholder="5000000" data-type="number" data-required="true" data-notblank="true" data-rangelength="[4,7]" />
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-4 col-lg-4">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label for="Imagen">Imagen</label>
                                    <input name="Imagen" type="file" accept="image/*" data-required="true"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <label for="Fecha">Fecha y hora</label>
                                <div class='input-group date' id='datetimepicker1' data-date-format="MM/DD/YYYY hh:mm A">
                                    <input type='text' name="Fecha" id="FechaEvento" class="form-control" data-required="true" data-notblank="true" readonly/>
                                    <span class="input-group-addon"><span title="Fecha y Hora" class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-8 col-md-8 col-lg-8">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label for="Descripcion">Descripci&oacute;n</label>
                                    <textarea id="DescripcionEvento" name="Descripcion" class="form-control" data-rangelength="[8,500]" data-required="true" data-notblank="true" rows="4"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="Departamento">Departamento</label>
                            <select id="departamentoevento" name="Departametno" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="Ciudad">Ciudad</label>
                            <select id="ciudadevento" name="Ciudad" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                        <label for="Direccion">Direcci&oacute;n o nombre del lugar</label>
                        <div class="input-group">
                            <input name="Direccion" class="form-control" data-required="true" data-notblank="true" data-rangelength="[8,100]"/>
                            <a id="PopOverMapa" title="Agregar ubicaci&oacute;n" class="input-group-addon" ><span class="glyphicon glyphicon-screenshot"></span></a>
                        </div>
                    </div>
                </div>
                <div class="row hide">
                    <div class="col-xs-12 col-sm-6">
                        <div class="form-group">
                            <input type="text" class="form-control" id="txtlat" name="Latitud"/>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <div class="form-group">
                            <input type="text" class="form-control" id="txtlng" name="Longitud"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <center><label for="Nota" style="font-size: 12px;"><i>Nota: Aseg&uacute;rese de tener todos los datos correctos antes de registrar el evento. No podr&aacute; modificarlos m&aacute;s adelante. </i></label></center>
                        <center><label for="Nota" style="font-size: 12px;"><i>Adem&aacute;s, aseg&uacute;rese agregar la ubicaci&oacute;n, y una direcci&oacute;n o nombre de lugar del evento para poder registrar un evento. </i></label></center>
                    </div>
                </div>
                <div id="btn-registrar" class="row">
                    <div class="col-xs-4 col-xs-offset-4 col-sm-2 col-sm-offset-5 col-md-offset-5 col-md-2">
                        <div class="form-group">
                            <button name="RegistrarEvento" type="submit" disabled="true" class="btn btn-block defecto" id="RegistrarEvento">Registrar</button>
                        </div>
                    </div>
                </div>
            </form>
            <div class="modal" id="modal-container-361414" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <div id="map"></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                            <button id="btn-agregar-ubicacion" type="button" class="btn btn-primary" data-dismiss="modal">Agregar ubicaci&oacute;n</button>
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
        <script src="../Libs/Bootstrap/js/jquery-1.10.2.min.js"></script>    
        <script src="../Libs/Bootstrap/js/bootstrap.min.js"></script>
        <!--Parsley-->
        <script src="../Libs/Customs/js/Parsley.js"></script>    
        <script src="../Libs/Customs/js/classie.js"></script>
        <script src="../Libs/Customs/js/gnmenu.js"></script>
        <script src="../Libs/Customs/DatePicker/js/moment.min.js" type="text/javascript"></script>
        <script src="../Libs/Customs/DatePicker/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
        <script src="../Libs/Customs/DatePicker/js/bootstrap-datetimepicker.es.js" type="text/javascript"></script>
        <script src="../Libs/Customs/js/leaflet.js" type="text/javascript"></script>
        <script src="../Libs/Customs/js/leaflet.draw.js" type="text/javascript"></script>
        <script src="../Libs/Customs/js/funcionmapa.js" type="text/javascript"></script>
        <script>
            new gnMenu(document.getElementById('gn-menu'));
        </script>
        <script>
            function getdepartamentos() {
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Help',
                    data: {"accion": 'getdepartamentos'},
                    success: function(data) {
                        var opcion = [];
                        opcion.push('<option value=""><\/option>');
                        var datos = jQuery.parseJSON(data);
                        $.each(datos, function(key, val) {
                            opcion.push('<option value="' + val.codigo + '">' + val.departamento + '<\/option>');
                        });
                        $("select#departamentoevento").html(opcion.join(""));
                    }
                });
            }
            function getciudades(index)
            {
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Help',
                    data: {"accion": 'getciudad', "codigodepartamento": index},
                    success: function(data) {
                        var opcionciudad = [];
                        var datos = jQuery.parseJSON(data);

                        $.each(datos, function(key, val) {
                            opcionciudad.push('<option value="' + val.codigo + '">' + val.ciudad + '<\/option>');
                        });
                        $("select#ciudadevento").html(opcionciudad.join(""));
                    }
                });
            }
            <%if (Rol.equals("Administrador")) {%>
            var Listar_Empresas = function() {
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Usuarios',
                    data: {"accion": "listar_empresas"},
                    success: function(data) {
                        var items = [];
                        var datos = jQuery.parseJSON(data);

                        $.each(datos, function(key, value) {
                            items.push('<option value="' + value.codigo + '">' + value.nombre + '</option>');
                        });
                        $("#lista_empresas").html(items.join(""));
                    }
                });
            }
            <%}%>
            $(document).ready(function() {
                getdepartamentos();
            <%if (Rol.equals("Administrador")) {%>
                Listar_Empresas();
            <%}%>
                $("select#departamentoevento", this).change(function() {
                    var index = $(this).val();
                    getciudades(index);
                });
                $("#PopOverMapa").click(function() {
                    $("#modal-container-361414").modal('show');
                    crearmapa();
                });

            });
            $(function() {
                $('#datetimepicker1').datetimepicker({language: 'es'});
            });
            $("input, select, textarea").change(function() {
                $(this).parsley('validate');
            });
        </script>
        <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
        <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
        <%session.setAttribute("Mensaje", "");%>
    </body>
</html>