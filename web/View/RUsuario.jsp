<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionAdministrador.jspf" %>
<%
    Contr_Consultar usu = new Contr_Consultar();
    String[][] ListaTipoUsuario = usu.BuscarDatosTipoUsuariosTodos();
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
                        <li><a href="EventoRecomendado.jsp">Inicio</a></li>
                        <li class="active">Registrar y consultar usuarios</a></li>
                    </ol>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-xs-12">
                    <h1 class="Center">Registrar y consultar usuarios</h1>
                </div>
            </div>
            <form id="search" data-validate="parsley" method="post" action="/TriggerEvent/Contr_Usuarios">
                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="Nombre">Nombre</label>
                            <input name="Nombre" type="text" class="form-control" id="nombre" data-rangelength="[3,100]" data-notblank="true" data-required="true"/>
                        </div>
                    </div>
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="Numero_Celular">N&uacute;mero de celular</label>
                            <input name="Celular" type="text" class="form-control" id="Hora" placeholder="000 000 0000" data-rangelength="[12,14]" data-type="cellphone" data-notblank="true" data-required="true" />
                        </div>
                    </div>
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="Nombre">Correo electr&oacute;nico</label>
                            <input name="Correo" type="text" class="form-control" id="Direccion" placeholder="example@service.com" data-required="true" data-notblank="true" data-type="email" data-rangelength="[10,100]" />
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="Tipo">Tipo de usuario</label>
                            <select name="Tipo_Usuario" id="Tipos" class="form-control" data-required="true">
                                <%for (String[] Row : ListaTipoUsuario) {%>
                                <option value="<%=Row[0]%>"><%=Row[1]%></option>
                                <%}%>
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="Imagen">Tipo de documento</label>
                            <select name="Tipo_Documento" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                <option value="Cédula de Ciudadanía">C&eacute;dula de Ciudadan&iacute;a</option>
                                <option value="Tarjeta de Identidad">Tarjeta de Identidad</option>
                                <option value="Cédula de Extranjería<">C&eacute;dula de Extranjer&iacute;a</option>
                                <option value="Pasaporte">Pasaporte</option>
                                <option value="DNI">Documento Nacional de Identificaci&oacute;n</option>
                                <option value="NIT">N&uacute;mero de Identificaci&oacute;n Tributaria</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label  for="Nombre">N&uacute;mero de documento</label>
                            <input name="No_Documento" type="text" class="form-control" id="docum" data-rangelength="[6,30]" data-notblank="true" data-required="true"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="Telefono">N&uacute;mero de tel&eacute;fono</label>
                            <input name="Telefono" class="form-control" type="text" placeholder="000 00 00" data-type="phone" data-notblank="true" data-rangelength="[9,9]" data-required="true">
                        </div> 
                    </div>
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="Departamento">Departamento</label>
                            <select id="departamentoregistro" name="Departametno" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="Ciudad">Ciudad</label>
                            <select id="ciudadregistro" name="Ciudad" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="Password">Contrase&ntilde;a</label>
                            <input name="Password" class="form-control" type="password" data-type="contrasenia" data-notblank="true" data-rangelength="[6,30]" data-required="true">
                        </div>
                    </div>
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="REPassword">Repita la Contrase&ntilde;a</label>
                            <input name="REPassword" class="form-control" type="password" data-type="contrasenia" data-notblank="true" data-rangelength="[6,30]" data-required="true">
                        </div>
                    </div>
                    <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                        <div class="form-group">
                            <label for="Direccion">Direcci&oacute;n</label>
                            <div class="form-group">
                                <input name="Direccion" class="form-control" type="text" data-notblank="true" data-rangelength="[10,100]" data-required="true">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-4 col-xs-offset-4 col-sm-offset-4 col-sm-4 col-md-offset-5 col-md-2 col-lg-2">
                        <div class="form-group">
                            <button name="RegistrarTodoUsuario" type="submit" class="btn btn-block defecto" id="RegistrarUsuario">Registrar</button>
                        </div>
                    </div>
                </div>
            </form>
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
        <script type="text/javascript" src="../Libs/Customs/DataTables/js/jquery.dataTables.min.js" charset="UTF-8"></script>
        <script type="text/javascript" src="../Libs/Customs/DataTables/js/datatables.js" charset="UTF-8"></script>
        <script>
            new gnMenu(document.getElementById('gn-menu'));
        </script>

        <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>            
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
                        $("select#departamentoregistro").html(opcion.join(""));
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
                        $("select#ciudadregistro").html(opcionciudad.join(""));
                    }
                });
            }
        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                getdepartamentos();
                $("select#departamentoregistro", this).change(function() {
                    var index = $(this).val();
                    getciudades(index);
                });
            });
        </script>
        <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
        <%session.setAttribute("Mensaje", "");%>
    </body>
</html>

