<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
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
        <%} else if (Rol.equals("Empresa")) {%>
        <%@include file="../WEB-INF/jspf/MenuEmpresa.jspf" %>
        <%} else {%>
        <%@include file="../WEB-INF/jspf/Menu.jspf" %>    
        <%}%>
        <div class="container">
            <br/>
            <br/>
            <br/>
            <div class="row clearfix">
                <div class="col-md-12">
                    <ol class="breadcrumb">
                        <li><a href="index.jsp">Inicio</a></li>
                        <li class="active">Equipo desarrollador</a></li>
                    </ol>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12">
                    <h1 class="Center">Equipo desarrollador</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-10 col-xs-offset-1 col-sm-5 col-md-4 col-md-offset-2 col-lg-4">
                    <center>
                        <img class="img-circle" height="180px" width="180px" src="../Libs/Customs/images/Sergio1.jpg" alt="Generic placeholder image">
                    </center>
                    <h2>Sergio Rivera Ballesteros</h2>
                    <p>
                        Desarrollador y aprendiz del SENA en lenguajes de programaci&oacute;n Java, con un fuerte v&iacute;nculo hacia el an&aacute;lisis de software. pruebas de certificaci&oacute;n y bases de datos.
                        <br/>
                        "El ingl&eacute;s es como la programaci&oacute;n, si no lo practicas, se te olvida"
                        <br/>
                        <b>SENA - CESGE</b>
                        <br/>
                        Centro de servicios y gesti&oacute;n empresarial
                        <br/>
                        <b>Medell&iacute;n, Antioquia - Colombia</b>
                    </p>
                </div><!-- /.col-lg-4 -->
                <div class="col-xs-10 col-xs-offset-1 col-sm-offset-0 col-sm-5 col-md-4 col-md-offset-0 col-lg-4">
                    <center>
                        <img class="img-circle" src="../Libs/Customs/images/Santiago1.jpg" alt="Generic placeholder image">
                    </center>
                    <h2>Santiago Botero Ru&iacute;z</h2>
                    <p>
                        Desarrollador y aprendiz del SENA con conocimento en los lenguajes de programaci&oacute;n como Java, PHP, C#, Javascript y con un fuerte v&iacute;nculo hacia las bases de datos.
                        <br/>
                        "jQuery abstrae todo lo duro como incompatibilidades en el DOM, dej&aacute;ndome libre para escribir código bastante b&aacute;sico para lograr lo que necesito."
                        <br/>
                        <b>SENA - CESGE</b>
                        <br/>
                        Centro de servicios y gesti&oacute;n empresarial
                        <br/>
                        <b>Medell&iacute;n, Antioquia - Colombia</b>
                    </p>
                </div><!-- /.col-lg-4 -->
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
        <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
        <%session.setAttribute("Mensaje", "");%>
    </body>
</html>


