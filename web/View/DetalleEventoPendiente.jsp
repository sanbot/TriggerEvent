<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionAdministradorEmpresa.jspf" %>
<%
    Contr_Consultar usu = new Contr_Consultar();
    String CodigoEvento = "";
    String CodigoUsuario = (String) session.getAttribute("Codigo");
    if (request.getParameter("CodigoEvento") != null) {
        CodigoEvento = request.getParameter("CodigoEvento");
    } else {
        response.sendRedirect("ConsultarEventos.jsp");
    }

    String Datos[] = usu.getBuscarDatosDetalladosEventoPendiente(CodigoEvento);
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
        <link rel="stylesheet" type="text/css" href="../Libs/Customs/css/style.css" />
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
        <br/>
        <br/>
        <br/>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <ol class="breadcrumb">
                        <li><a href="EventoRecomendado.jsp">Inicio</a></li>
                        <li><a href="ConsultaEvento.jsp">Eventos</a></li>
                        <li class="active">Detalle Evento</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-1"></div>
                <div class="col-xs-10">
                    <div class="row">
                        <div class="col-md-5 eventoborde contenidoborde">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="margen-img"> 
                                        <center>
                                            <img src="ImagenEvento.jsp?Codigo=<%=CodigoEvento%>" class="imgevento icon-animated-bell" alt="Nombre de la imagen">
                                        </center>
                                    </div>
                                </div>    
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <h3>
                                        <center>
                                            <div class="tituloevento"><h3><%=Datos[1]%></h3></div>
                                        </center>
                                    </h3>
                                </div>    
                            </div>
                            <div class="row">
                                <div class="col-xs-6 contenido_uno">
                                    <div class="contenido">
                                        <label for="Creador">Creador:</label>
                                    </div>
                                </div>
                                <div class="col-xs-6 contenido_dos">
                                    <div class="contenido">
                                        <label for="NombreEmpresa"><%=Datos[0]%></label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-6 contenido_uno">
                                    <div class="contenido">
                                        <label for="RangoPrecios">Rango de precios:</label>
                                    </div>
                                </div>
                                <div class="col-xs-6 contenido_dos">
                                    <div class="contenido">
                                        <label for="RangoPreciosEvento"><%=Datos[2]%></label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-6 contenido_uno">
                                    <div class="contenido">
                                        <label for="Departametno">Departamento:</label>
                                    </div>
                                </div>
                                <div class="col-xs-6 contenido_dos">
                                    <div class="contenido">
                                        <label for="DepartamentoEvento"><%=Datos[4]%></label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-6 contenido_uno">
                                    <div class="contenido">
                                        <label for="Ciudad">Ciudad:</label>
                                    </div>
                                </div>
                                <div class="col-xs-6 contenido_dos">
                                    <div class="contenido">
                                        <label for="CiudadEvento"><%=Datos[6]%></label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-6 contenido_uno">
                                    <div class="contenido">
                                        <label for="Direccion">Direcci&oacute;n:</label>
                                    </div>
                                </div>
                                <div class="col-xs-6 contenido_dos">
                                    <div class="contenido">
                                        <label for="DireccionEvento">
                                            <%=Datos[7]%>
                                            <span id="mostrarubicacion" class="glyphicon glyphicon-screenshot"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-6 contenido_uno">
                                    <div class="contenido">
                                        <label for="Fecha">Fecha:</label>
                                    </div>
                                </div>
                                <div class="col-xs-6 contenido_dos">
                                    <div class="contenido">
                                        <label for="FechaEvento"><%=Datos[8]%></label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-6 contenido_uno">
                                    <div class="contenido">
                                        <label for="Hora">Hora:</label>
                                    </div>
                                </div>
                                <div class="col-xs-6 contenido_dos">
                                    <div class="contenido">
                                        <label for="HoraEvento"><%=Datos[10]%></label>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-xs-12 contenido_medio">
                                    <div class="contenido">
                                        <label for="Clasificacion"><a class="linkevento" href="DetalleClasificacionEvento.jsp?CodigoEvento=<%=CodigoEvento%>">Ver clasificaci&oacute;n </a></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-7 eventoborde">
                            <div class="row">
                                <div class="col-md-12 descripciontamanio descripcion-nombre">
                                    <label for="Descripcion">Descripci&oacute;n:</label>
                                    <br/>
                                    <%=Datos[9]%>
                                    <br/>
                                    <br/>
                                    <label for="Estado">Estado: <%=Datos[12]%></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12 descripciontamanio descripcion-nombre">
                                    <%if (Datos[11] != null) {%>

                                    <label for="Motivo">Motivo</label>
                                    <br/>
                                    <%=Datos[11]%>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-1"></div>
            </div>
            <div class="modal" id="modal-container-361414" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <div id="map"></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
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
        <script src="../Libs/Customs/js/classie.js"></script>
        <script src="../Libs/Customs/js/gnmenu.js"></script>
        <script src="../Libs/Customs/js/leaflet.js" type="text/javascript"></script>
        <script src="../Libs/Customs/js/leaflet.draw.js" type="text/javascript"></script>
        <script src="../Libs/Customs/js/funcionmapa.js" type="text/javascript"></script>
        <script>
            new gnMenu(document.getElementById('gn-menu'));
            $(document).ready(function(){
                $("#mostrarubicacion").click(function() {
                    $("#modal-container-361414").modal('show');
                    var latitud = '<%=Datos[13]%>';
                    var longitud = '<%=Datos[14]%>';
                    var nombre = '<%=Datos[1]%>';
                    mostrarmapa(latitud, longitud, nombre);
                });
            });
        </script>
        <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
        <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
        <%session.setAttribute("Mensaje", "");%>
    </body>
</html>