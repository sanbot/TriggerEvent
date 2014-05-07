<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionAdministrador.jspf" %>
<%
    Contr_Consultar usu = new Contr_Consultar();
    String codigoUsuario = "";
    if (request.getParameter("Codigo") != null) {
        codigoUsuario = request.getParameter("Codigo");
    } else {
        response.sendRedirect("ConsultaUsuario.jsp");
    }

    String[][] ListaTipoUsuario = usu.BuscarDatosTipoUsuariosTodos();
    String[] DatosUsuario = usu.BuscarDatosUsuario(codigoUsuario);
    if (DatosUsuario[0] == null) {
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
                        <li><a href="index.jsp">Inicio</a></li>
                        <li><a href="index.jsp">Registrar y consultar usuarios</a></li>
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
                                    <input name="Codigo" type="hidden" class="form-control" id="Nombre_MUsuario" readonly/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Nombre">Nombre de usuario</label>
                                    <input name="Nombre" type="text" class="form-control" id="nombre" data-rangelength="[3,100]" data-notblank="true" data-required="true" value="<%=DatosUsuario[5]%>"/>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Tipo_Documento">Tipo de documento</label>
                                    <select name="Tipo_Documento" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                        <option value="<%=DatosUsuario[3]%>"><%=DatosUsuario[3]%></option>
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
                                    <input name="No_Documento" type="text" class="form-control" id="docum" data-rangelength="[6,30]" data-notblank="true" data-required="true" value="<%=DatosUsuario[4]%>"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Tipo">Tipo de usuario</label>
                                    <select name="Tipo" id="Tipos" class="form-control" data-required="true">
                                        <option value="<%=DatosUsuario[1]%>"><%=DatosUsuario[2]%></option>
                                        <%for (String[] Row : ListaTipoUsuario) {%>
                                        <option value="<%=Row[0]%>"><%=Row[1]%></option>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Nombre">N&uacute;mero de celular</label>
                                    <input name="Celular" type="text" class="form-control" id="Hora" placeholder="000 000 0000" data-rangelength="[12,14]" data-type="cellphone" data-notblank="true" data-required="true" value="<%=DatosUsuario[7]%>" readonly />
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Telefono">N&uacute;mero de tel&eacute;fono</label>
                                    <input name="Telefono" class="form-control" type="text" placeholder="000 00 00" data-type="phone" data-notblank="true" data-rangelength="[9,9]" data-required="true" value="<%=DatosUsuario[6]%>">
                                </div> 
                            </div>
                        </div>
                        <div class="row">
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
                                        <option value='<%=DatosUsuario[13]%>'><%=DatosUsuario[11]%></option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Direccion">Direcci&oacute;n</label>
                                    <div class="form-group">
                                        <input name="Direccion" class="form-control" type="text" data-notblank="true" data-rangelength="[10,100]" data-required="true" value="<%=DatosUsuario[9]%>">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-2 col-sm-4 col-md-offset-2 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Nombre">Correo electr&oacute;nico</label>
                                    <input name="Correo" type="text" class="form-control" id="Direccion" placeholder="example@service.com" data-required="true" data-notblank="true" data-type="email" data-rangelength="[10,100]" value="<%=DatosUsuario[8]%>" readonly/>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Estado">Estado</label>
                                    <div class="form-group">
                                        <select name="Estado" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                            <option value="<%=DatosUsuario[10]%>"><%=DatosUsuario[10]%></option>
                                        </select>
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
                        $("select#departamento_Musuario").html(opcion.join(""));
                    }
                }).done(function() {
                    $("select#departamento_Musuario [value='<%=DatosUsuario[12]%>']").prop("selected", true);
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
            $(document).ready(function() {

                getdepartamentos();
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Help',
                    data: {"accion": 'getciudad', "codigodepartamento": '<%=DatosUsuario[12]%>'},
                    success: function(data) {
                        var opcionciudad = [];
                        var datos = jQuery.parseJSON(data);

                        $.each(datos, function(key, val) {
                            opcionciudad.push('<option value="' + val.codigo + '">' + val.ciudad + '<\/option>');
                        });
                        $("select#ciudad_Musuario").html(opcionciudad.join(""));
                    }
                }).done(function() {
                    $("select#ciudad_Musuario [value='<%=DatosUsuario[14]%>']").prop("selected", true);
                });

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

