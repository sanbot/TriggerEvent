<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionAdministrador.jspf" %>
<%
    String codigoUsuario = "";
    if (request.getParameter("Codigo") != null) {
        codigoUsuario = request.getParameter("Codigo");
    } else {
        response.sendRedirect("ConsultaUsuario.jsp");
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
                        <li><a href="EventoRecomendado.jsp">Inicio</a></li>
                        <li><a href="ConsultaUsuario.jsp">Registrar y consultar usuarios</a></li>
                        <li class="active">Modificar usuario</a></li>
                    </ol>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12">
                    <h1 class="Center">Modificar usuario</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <form id="search" data-validate="parsley" method="post" action="/TriggerEvent/Contr_Usuarios">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <input id="txtcodigo_modificar_usuario" name="Codigo" type="hidden" class="form-control" id="Nombre_MUsuario"  readonly/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Tipo">Tipo de usuario</label>
                                    <select name="Tipo" id="Tipos" class="form-control" data-required="true">
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Nombre">Nombre de usuario</label>
                                    <input name="Nombre" type="text" class="form-control" id="txtnombre_modificar_usuario" data-rangelength="[3,100]" data-notblank="true" data-required="true"/>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Nombre">Correo electr&oacute;nico</label>
                                    <input name="Correo" type="text" class="form-control" id="txtcorreo_modificar_usuario" placeholder="example@service.com" data-required="true" data-notblank="true" data-type="email" data-rangelength="[10,100]" readonly/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Tipo_Documento">Tipo de documento</label>
                                    <select id="ddlTipo_Documento" name="Tipo_Documento" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label  for="Nombre">N&uacute;mero de documento</label>
                                    <input name="No_Documento" type="text" class="form-control" id="txtdocumento_modificar_usuario" data-type="number" data-rangelength="[6,30]" data-notblank="true" data-required="true"/>
                                </div>
                            </div>

                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Nombre">N&uacute;mero de celular</label>
                                    <input name="Celular" type="text" class="form-control" id="txtcelular_modificar_usuario" placeholder="000 000 0000" data-rangelength="[12,14]" data-type="cellphone" data-notblank="true" data-required="true" readonly />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Telefono">N&uacute;mero de tel&eacute;fono</label>
                                    <input id="txttelefono_modificar_usuario" name="Telefono" class="form-control" type="text" placeholder="000 00 00" data-type="phone" data-notblank="true" data-rangelength="[9,9]" data-required="true">
                                </div> 
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Departamento">Departamento</label>
                                    <select id="departamento_Musuario" name="Departametno" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Ciudad">Ciudad</label>
                                    <select id="ciudad_Musuario" name="Ciudad" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-4 col-sm-4 col-md-offset-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Direccion">Direcci&oacute;n</label>
                                    <div class="form-group">
                                        <input id="txtdireccion_modificar_usuario" name="Direccion" class="form-control" type="text" data-notblank="true" data-rangelength="[10,100]" data-required="true" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-4 col-xs-offset-4 col-sm-2 col-sm-offset-5 col-md-offset-5 col-md-2">
                                <div class="form-group">
                                    <button name="ModificarUsuarioTodos" type="submit" class="btn btn-block defecto" id="ModificarUsuarioTodos">Modificar</button>
                                </div>
                            </div>
                        </div>
                    </form>
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
        <script>
            new gnMenu(document.getElementById('gn-menu'));
        </script>
        <script>
            function getdepartamentos(id_departamento) {
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
                        $("select#departamento_Musuario").html(opcion.join(""));
                    }
                }).done(function() {
                    $("select#departamento_Musuario [value='" + id_departamento + "']").prop("selected", true);
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
                        $("select#ciudad_Musuario").html(opcionciudad.join(""));
                    }
                });
            }
            var datos_usuarios = function() {
                var idusuario = '<%=codigoUsuario%>';
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Usuarios',
                    data: {'accion': 'datos_usuario', 'codigo': idusuario},
                    success: function(data) {
                        var datos = jQuery.parseJSON(data);

                        $.each(datos, function(key, value) {
                            if (value.codigo != null) {
                                $('#txtnombre_modificar_usuario').val(value.nombre);
                                $('#txtcodigo_modificar_usuario').val(value.codigo);
                                $('#txtcelular_modificar_usuario').val(value.celular);
                                $('#txtcorreo_modificar_usuario').val(value.correo);
                                $('#txtdireccion_modificar_usuario').val(value.direccion);
                                $('#txtdocumento_modificar_usuario').val(value.no_documento);
                                $('#txttelefono_modificar_usuario').val(value.telefono);
                                $('#ddlTipo_Documento').html('<option value="' + value.tipo_documento + '">' + value.tipo_documento + '</option>');
                                $('#Tipos').html('<option value="' + value.codigo_tipo + '">' + value.tipo + '</option>');

                                var opciones = [];
                                if (value.codigo_tipo == "Tip2")
                                {
                                    opciones.push('<option value="NIT">N&uacute;mero de Identificaci&oacute;n Tributaria</option>');
                                }
                                else {
                                    opciones.push('<option value="Tarjeta de Identidad">Tarjeta de Identidad</option>');
                                    opciones.push('<option value="Cédula de Ciudadanía">C&eacute;dula de Ciudadan&iacute;a</option>');
                                    opciones.push('<option value="Cédula de Extranjería">C&eacute;dula de Extranjer&iacute;a</option>');
                                    opciones.push('<option value="Pasaporte">Pasaporte</option>');
                                }
                                $('#ddlTipo_Documento').html(opciones.join(""));
                                $('#ddlTipo_Documento [value="'+value.tipo_documento+'"]').prop("selected", true);

                                getdepartamentos(value.codigo_departamento);
                                $.ajax({
                                    type: 'POST',
                                    url: '/TriggerEvent/Contr_Help',
                                    data: {"accion": 'getciudad', "codigodepartamento": value.codigo_departamento},
                                    success: function(data) {
                                        var opcionciudad = [];
                                        var datos = jQuery.parseJSON(data);
                                        $.each(datos, function(key, val) {
                                            opcionciudad.push('<option value="' + val.codigo + '">' + val.ciudad + '<\/option>');
                                        });
                                        $("select#ciudad_Musuario").html(opcionciudad.join(""));
                                    }
                                }).done(function() {
                                    $("select#ciudad_Musuario [value='" + value.codigo_ciudad + "']").prop("selected", true);
                                });
                            } else {
                                window.location.replace("/TriggerEvent/View/ConsultaUsuario.jsp");
                            }
                        });
                    }

                });
            }
            $(document).ready(function() {
                datos_usuarios();

                $("select#departamento_Musuario", this).change(function() {
                    var index = $(this).val();
                    getciudades(index);
                });
            });
        </script>
        <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
        <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
        <%session.setAttribute("Mensaje", "");%>
    </body>
</html>

