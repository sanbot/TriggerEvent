<%-- 
    Document   : RegistrarUsuario
    Created on : 11/03/2014, 08:28:24 AM
    Author     : santi_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>    
<%
    if(!Rol.equals("") && !Rol.equals(null))
    {
        response.sendRedirect("index.jsp");
    }

    Contr_Consultar usu = new Contr_Consultar();
    String nombre = "";
    String Correo = "";
    String Celular = "";
    String CodVer = "";

    if(session.getAttribute("Registrar_Nombre")!=null)
    {
        nombre = (String)session.getAttribute("Registrar_Nombre");
        Correo = (String) session.getAttribute("Registrar_Correo");
        Celular = (String) session.getAttribute("Registrar_Celular");
        CodVer = (String) session.getAttribute("Registrar_CodVer");
    }
    String[][] ListaTipoUsuario = usu.BuscarDatosTipoUsuariosTodos();
    String[][] ListaDepartamento = usu.BuscarDatosDepartamentoTodos();
    String[][] ListaCiudad = usu.BuscarDatosCuidadTodos();
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
    <link rel="shortcut icon" href="../favicon.ico">
    <%@include file="../WEB-INF/jspf/EstilosCSS.jspf" %>
    
    <script src="../Libs/Customs/js/modernizr.custom.js"></script>
    
</head>
<body>
    <%@include file="../WEB-INF/jspf/Menu.jspf" %>
    <div class="container">
        <div class="row clearfix">
            <br/>
            <br/>
            <br/>
            <div class="col-md-12">
                <div class="form-group">
                    <a href="index.jsp">Inicio</a> <span class="glyphicon glyphicon-share-alt"></span>Registrarse
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12">
                <h1 class="Center">REGISTRARSE</h1>
                <form data-validate="parsley" data-focus="first" method="post" action="/TriggerEvent/Contr_Usuarios">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            <div class="form-group">
                                <label for="Nombre">Nombre</label>
                                <input name="Nombre" type="text" class="form-control" id="txt_nombre_registro_usuario" data-rangelength="[3,100]" data-notblank="true" data-required="true" value="<%=nombre%>" <%if(!nombre.equals("")){%>readonly<%}%> />
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            <div class="form-group">
                                <label for="Nombre">Celular</label>
                                <input name="Celular" type="text" class="form-control" id="Hora" placeholder="000 000 0000" data-rangelength="[12,14]" data-type="cellphone" data-notblank="true" data-required="true" value="<%=Celular%>" <%if(!Celular.equals("")){%>readonly<%}%>/>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            <div class="form-group">
                                <label for="Nombre">Correo electr&oacute;nico</label>
                                <input name="Correo" type="text" class="form-control" id="Direccion" placeholder="example@service.com" data-required="true" data-notblank="true" data-type="email" data-rangelength="[10,100]" value="<%=Correo%>" <%if(!Correo.equals("")){%>readonly<%}%>/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-5">
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
                            <div class="form-group">
                                <label for="Nota" style="font-size: 10px;"><i>Nota: oprima enviar para solicitar un c&oacute;digo de verificaci&oacute;n</i></label>
                                <%if(!nombre.equals("")){%>
                                    <button name="REnviarCodigoVer" type="submit" class="btn btn-block defecto" id="Reenviar">Reenviar</button>
                                    <button name="LimpiarDatosUsuario" type="submit" class="btn btn-block defecto" id="LimpiarDatosResUsuario">Limpiar Datos</button>
                                <%}else{%>
                                    <button name="EnviarCodigoVer" type="submit" class="btn btn-block defecto" id="Enviar">Enviar</button>
                                <%}%>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
                        </div>
                    </div>
                </form>
                <form id="search" data-validate="parsley" method="post" action="/TriggerEvent/Contr_Usuarios">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            <div class="form-group">
                                <label for="Tipo">Tipo Usuario</label>
                                <select name="Tipo_Usuario" id="Tipos" class="form-control" data-required="true">
                                    <%
                                    for(String[] Row : ListaTipoUsuario){
                                        if(!Row[1].equals("Administrador")){%>
                                            <option value="<%=Row[0]%>"><%=Row[1]%></option>
                                        <%}

                                    }%>
                                </select>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            <div class="form-group">
                                <label for="Imagen">Tipo Documento</label>
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
                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            <div class="form-group">
                                <label  for="Nombre">N&uacute;mero de Documento</label>
                                <input name="No_Documento" type="text" class="form-control" id="docum" data-rangelength="[6,30]" data-notblank="true" data-required="true"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            <div class="form-group">
                                <label for="Codverifi">C&oacute;digo de Verificaci&oacute;n</label>
                                <input name="codver" type="text" class="form-control" id="codver" data-rangelength="[4,4]" data-notblank="true" data-required="true"/>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            <div class="form-group">
                                <label for="Telefono">Tel&eacute;fono</label>
                                <input name="Telefono" class="form-control" type="text" placeholder="000 00 00" data-type="phone" data-notblank="true" data-rangelength="[9,9]" data-required="true">
                            </div> 
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            <div class="form-group">
                                <label for="Direccion">Direcci&oacute;n</label>
                                <div class="form-group">
                                    <input name="Direccion" class="form-control" type="text" data-notblank="true" data-rangelength="[10,100]" data-required="true">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            <div class="form-group">
                                <label for="Departamento">Departamento</label>
                                <select id="departamento" name="Departametno" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                </select>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            <div class="form-group">
                                <label for="Ciudad">Ciudad</label>
                                <select id="ciudad" name="Ciudad" tabindex="1" data-placeholder="" class="form-control" data-required="true">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="Password">Contrase&ntilde;a</label>
                                <input id="txtRegistrarContrasenia" name="Password" class="form-control" type="password" data-notblank="true" data-type="contrasenia" data-rangelength="[6,30]" data-required="true">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="REPassword">Repita la Contrase&ntilde;a</label>
                                <input name="REPassword" class="form-control" type="password" data-notblank="true" data-type="contrasenia" data-rangelength="[6,30]" data-required="true" data-equalto="#txtRegistrarContrasenia">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
                            <div class="form-group">
                                <button name="RegistrarUsuario" type="submit" class="btn btn-block defecto" id="RegistrarUsuario">Registrar</button>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
                            <div class="form-group">
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <footer>
            <div class="row">
                <div class="col-md-12">
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
new gnMenu( document.getElementById( 'gn-menu' ) );
</script>

<script>
(function($) {

    $.fn.changeType = function(){
        var data;
        data = [
            <%
            for(String[] Row :ListaCiudad)
            {%>
                {"codigo":"<%=Row[0]%>", "nombre":"<%=Row[1]%>", "codigo_departamento":"<%=Row[2]%>", "departamento":"<%=Row[3]%>"},

            <%}%>
            {"codigo":"", "nombre":"","codigo_departamento":"","departamento":""}
            ];
        var datadep = [
            <%
            for(String[] Row :ListaDepartamento)
            {%>
                {"codigo":"<%=Row[0]%>", "nombre":"<%=Row[1]%>"},

            <%}%>
            {"codigo":"", "nombre":""}
            ];
        var options_departments = "<option value=''></option>";
        $.each(datadep, function(i,d){
            options_departments += '<option value="' + d.codigo + '">' + d.nombre + '<\/option>';
        });
        $("select#departamento", this).html(options_departments);
        $("select#departamento", this).change(function(){
            var index = $(this).val();
            var options = '';
            $.each(data, function(i,c){
                if(c.codigo_departamento === index)
                {
                    options += '<option value="' + c.codigo + '">' + c.nombre + '<\/option>';
                }
            });
            $("select#ciudad").html(options);
        });
    };
    })(jQuery);
    $(document).ready(function() {
        $("form#search").changeType();
    });
    </script>
    <script>
        new gnMenu( document.getElementById( 'gn-menu' ) );
    </script>

    <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
    <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
    <%session.setAttribute("Mensaje", "");%>
</body>
</html>

