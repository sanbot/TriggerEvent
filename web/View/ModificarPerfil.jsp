<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionGeneral.jspf" %>
<%
    String Codigo = (String) session.getAttribute("Codigo");
    Contr_Consultar usu = new Contr_Consultar();
    String[] DatosUsuario = usu.BuscarDatosUsuario(Codigo);
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
        <title>Trigger Event</title>

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
            <div class="row clearfix">
                <div class="col-xs-12">
                    <div class="form-group">
                        <ol class="breadcrumb">
                            <li><a href="EventoRecomendado.jsp">Inicio</a></li>
                            <li class="active">Mi perfil</a></li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row clearfix">
                <div class="col-xs-12">
                    <h1 class="Center">Modificar perfil</h1>
                    <form id="search" data-validate="parsley" role="form" method="post" action="/TriggerEvent/Contr_Usuarios">
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Tipo">Tipo de usuario</label>
                                    <input name="TipoUsuario" type="text" class="form-control" id="Tipo_Usuario_MPerfil"value="<%=Rol%>" readonly/>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Tipo_Documento">Tipo de documento</label>
                                    <input name="Tipo_Documento" type="text" class="form-control" id="Tipo_Documento_MPerfil" value="<%=DatosUsuario[3]%>" readonly/>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Numero_Documento">N&uacute;mero de documento</label>
                                    <input name="No_Documento" type="text" class="form-control" id="Numero_Documento_MPerfil" value="<%=DatosUsuario[4]%>" readonly/> 
                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Nombre">Nombre de usuario</label>
                                    <input name="Nombre" type="text" class="form-control" id="Nombre_Documento_MPerfil" value="<%=Nombre%>" readonly/>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Celular">N&uacute;mero de celular</label>
                                    <input name="Celular" type="text" class="form-control" id="Celular_MPerfil" value="<%=DatosUsuario[7]%>" readonly/>
                                </div>
                            </div>

                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Correo">Correo electr&oacute;nico</label>
                                    <input name="Correo" type="text" class="form-control" id="Correo_MPerfil" value="<%=DatosUsuario[8]%>" readonly/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Departamento">Departamento</label>
                                    <select id="departamentoperfil" name="Departametno" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Ciudad">Ciudad</label>
                                    <select id="ciudadperfil" name="Ciudad" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                        <option value='<%=DatosUsuario[13]%>'><%=DatosUsuario[11]%></option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Tipo">N&uacute;mero de tel&eacute;fono</label>
                                    <input name="Telefono" class="form-control" type="text" placeholder="000 00 00" data-type="phone" data-notblank="true" data-rangelength="[9,9]" data-required="true" value="<%=DatosUsuario[6]%>"/>
                                </div> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-offset-4 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label for="Imagen">Direcci&oacute;n</label>
                                    <div class="form-group">
                                        <input name="Direccion" class="form-control" type="text" data-notblank="true" data-rangelength="[10,100]" data-required="true" value="<%=DatosUsuario[9]%>"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-4 col-xs-offset-4 col-sm-2 col-sm-offset-5 col-md-offset-5 col-md-2">
                                <button type="submit" name="ModificarPerfil" class="btn defecto btn-block">Guardar</button>
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
                        $("select#departamentoperfil").html(opcion.join(""));
                    }
                }).done(function() {
                    $("select#departamentoperfil [value='<%=DatosUsuario[12]%>']").prop("selected", true);
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
                        $("select#ciudadperfil").html(opcionciudad.join(""));
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
                        $("select#ciudadperfil").html(opcionciudad.join(""));
                    }
                }).done(function() {
                    $("select#ciudadperfil [value='<%=DatosUsuario[14]%>']").prop("selected", true);
                });

                $("select#departamentoperfil", this).change(function() {
                    var index = $(this).val();
                    getciudades(index);
                });
            });
        </script>
        <script>
            new gnMenu(document.getElementById('gn-menu'));
        </script>
        <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
        <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
        <%session.setAttribute("Mensaje", "");%>
    </body>
</html>
