<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
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
        <script src="../Libs/Customs/js/modernizr.custom.js"></script>

    </head>
    <body>
        <%
            if (Rol.equals("Administrador")) {%>
        <%@include file="../WEB-INF/jspf/MenuAdministrador.jspf" %>
        <%
        } else if (Rol.equals("Cliente")) {%>
        <%@include file="../WEB-INF/jspf/MenuCliente.jspf" %>
        <%
        } else if (Rol.equals("Empresa")) {%>
        <%@include file="../WEB-INF/jspf/MenuEmpresa.jspf" %>
        <%
        } else {%>
        <%@include file="../WEB-INF/jspf/Menu.jspf" %>    
        <%
            }%>
        <br/>
        <br/>
        <br/>
        <div class="container">
            <div class="row clearfix">
                <div class="col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="index.jsp">Inicio</a></li>
                        <li class="active">Cont&aacute;ctenos</a></li>
                    </ol>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-xs-12" id="target-1">
                    <h1 class="Center">Cont&aacute;ctenos</h1>
                </div>
            </div>
            <div class="row">
                <form id="search" data-validate="parsley" method="post" action="/TriggerEvent/Contr_Contacto">
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4" id="target-3">
                            <div class="form-group">
                                <label for="Nombre">Nombre</label>
                                <input name="Nombre" type="text" class="form-control" id="Nombre" data-rangelength="[3,30]" data-notblank="true" data-required="true" <%if (!Rol.equals("") && !Rol.equals(null)) {%> value="<%=Nombre%>" readonly<%}%>/>
                            </div>
                        </div>
                        <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                            <div class="form-group">
                                <label for="Correo">Correo electr&oacute;nico</label>
                                <input name="Correo" type="email" class="form-control" data-type="email" id="Correo" data-maxlength="100" data-notblank="true" data-required="true" <%if (!Rol.equals("") && !Rol.equals(null)) {%> readonly<%}%>/>
                            </div>
                        </div>
                        <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4" id="target-2">
                            <div class="form-group">
                                <label for="Categoria">Categor&iacute;a del tema</label>
                                <select name="Categoria" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                    <option value=""></option>
                                    <option value="Contenido">Contenido</option>
                                    <option value="Problemas ténicos">Problemas T&eacute;cnicos</option>
                                    <option value="Sugerencias y solicitudes">Sugerencias y Solicitudes</option>
                                    <option value="Queja">Queja</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4">
                            <div class="form-group">
                                <label for="Nombre">Asunto</label>
                                <input name="Asunto" type="text" class="form-control" id="Asunto" data-notblank="true" data-rangelength="[3,30]" data-required="true"/>
                            </div>
                        </div>
                        <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4" id="target-3">
                            <div class="form-group" >
                                <label for="Departamento">Departamento</label>
                                <select id="departamentocontactenos" name="Departametno" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                </select>
                            </div>
                        </div>
                        <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-4 col-md-offset-0 col-md-4 col-lg-4" id="target-4">
                            <div class="form-group">
                                <label for="Ciudad">Ciudad</label>
                                <select id="ddlCiudad" name="Ciudad" id="ciudadcontactenos" name="Ciudad" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row" >              
                        <div class="col-xs-10 col-xs-offset-1" >
                            <div class="form-group">
                                <label for="Comentario">Comentario</label>
                                <textarea name="Comentario" class="form-control" rows="7" data-notblank="true" data-rangelength="[10,500]" data-required="true"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4 col-xs-offset-4 col-sm-offset-4 col-sm-4 col-md-offset-5 col-md-2 col-lg-2" id="target-5">
                            <div class="form-group">
                                <button type="submit" class="btn defecto btn-block" id="Contactenos" name="Contactenos">Enviar</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
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
        <script src="../Libs/Bootstrap/js/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="../Libs/Bootstrap/js/bootstrap.min.js"></script>
        <script src="../Libs/Bootstrap/js/holder.js"></script>
        <!--Parsley-->
        <script src="../Libs/Customs/js/Parsley.js"></script>    
        <script src="../Libs/Customs/js/classie.js"></script>
        <script src="../Libs/Customs/js/gnmenu.js"></script>
        <script>
            new gnMenu(document.getElementById('gn-menu'));
        </script>
        <script type="text/javascript" src="../Libs/Customs/DataTables/js/jquery.dataTables.js" charset="UTF-8"></script>
        <script type="text/javascript" src="../Libs/Customs/DataTables/js/datatables.js" charset="UTF-8"></script>

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
                        $("select#departamentocontactenos").html(opcion.join(""));
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
                        $("select#ciudadcontactenos").html(opcionciudad.join(""));
                    }
                });
            }
            <%if (!Rol.equals(null) && !Rol.equals("")) {%>
            var datos_usuarios = function() {
                var idusuario = '<%=Codigo%>';
                $.ajax({
                    type: 'POST',
                    url: '/TriggerEvent/Contr_Usuarios',
                    data: {'accion': 'datos_usuario', 'codigo': idusuario},
                    success: function(data) {
                        var datos = jQuery.parseJSON(data);

                        $.each(datos, function(key, value) {
                            if (value.codigo != null) {
                                $('#Correo').val(value.correo);
                                $("#ddlCiudad").html('<option value=' + value.nombre_ciudad + '>' + value.nombre_ciudad + '</option>');
                                $("select#departamentocontactenos").html("<option value=' " + value.nombre_departamento + " '>" + value.nombre_departamento + "</option>");
                            }
                        });
                    }

                });
            }
            <%}%>
            $(document).ready(function() {

            <%if (!Rol.equals(null) && !Rol.equals("")) {%>
                datos_usuarios();
            <%} else {%>
                getdepartamentos();

                $("select#departamentocontactenos", this).change(function() {
                    var index = $(this).val();
                    getciudades(index);
                });
            <%}%>

            });
        </script>
        <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
        <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
        <%session.setAttribute("Mensaje", "");%>
    </body>
</html>


