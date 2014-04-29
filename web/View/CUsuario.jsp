<%-- 
Document   : ConsultaUsuario
Created on : 18-mar-2014, 14:17:00
Author     : ADSI
--%>

<%-- 
Document   : RegistrarUsuario
Created on : 11/03/2014, 08:28:24 AM
Author     : santi_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionAdministrador.jspf" %>
<%
Contr_Consultar usu = new Contr_Consultar();
String codigoUsuario = "";
if(request.getParameter("Codigo")!= null)
{
    codigoUsuario = request.getParameter("Codigo");
}
else
{
    response.sendRedirect("ConsultaUsuario.jsp");
}

String[] DatosUsuario = usu.BuscarDatosUsuario(codigoUsuario);
if(DatosUsuario[0] == null)
{
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
                    <li><a href="ConsultaUsuario.jsp">Registrar y Consultar Usuarios</a></li>
                    <li class="active">Consultar usuarios</a></li>
                </ol>
            </div>
        </div>
        <div class="row clearfix">
            <div class="col-xs-12">
                <h1 class="Center">Consulta de Usuario</h1>
            </div>
        </div>
        
        <div class="row">
            <div class="col-xs-1"></div>
            <div class="col-xs-10">
                <div class="row perfil-contenido contenido-borde">

                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                        <center><img src="../Libs/Customs/images/userapplication.png" class="img-perfil"/></center>
                        <h3 class="nombre-usuario"><%=DatosUsuario[5]%></h3>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8 datos-personales">
                        <div class="row primer-dato">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-5">
                                <div class="datos-perfil">
                                    <label for="Tipo_Documento">Tipo de documento: </label>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-5 contenido-dos">
                                <div class="datos-perfil">
                                    <label for="Tipo_Documento_Usuario"><%=DatosUsuario[3]%></label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-5">
                                <div class="datos-perfil">
                                    <label for="No_Documento">N&uacute;mero de documento: </label>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-5 contenido-dos">
                                <div class="datos-perfil">
                                    <label for="No_Documento_Usuario"><%=DatosUsuario[4]%></label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-5">
                                <div class="datos-perfil">
                                    <label for="Tipo_USuario">Tipo de usuario: </label>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-5 contenido-dos">
                                <div class="datos-perfil">
                                    <label for="Tipo_USuario_Usuario"><%=DatosUsuario[2]%></label>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-5">
                                <div class="datos-perfil">
                                    <label for="No_Celular">N&uacute;mero de Celular: </label>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-5 contenido-dos">
                                <div class="datos-perfil">
                                    <label for="No_Celular_USuario"><%=DatosUsuario[7]%></label>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-5">
                                <div class="datos-perfil">
                                    <label for="No_Telefono">N&uacute;mero de tel&eacute;fono: </label>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-5 contenido-dos">
                                <div class="datos-perfil">
                                    <label for="No_Telefono_Usuario"><%=DatosUsuario[6]%></label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-5">
                                <div class="datos-perfil">
                                    <label for="Correo_Electronico">Correo electr&oacute;nico</label>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-5 contenido-dos">
                                <div class="datos-perfil">
                                    <label for="Correo_Electronico_Usuario"><%=DatosUsuario[8]%></label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-5">
                                <div class="datos-perfil">
                                    <label for="Departamento">Departamento: </label>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-5 contenido-dos">
                                <div class="datos-perfil">
                                    <label for="Departamento_USuario"><%=DatosUsuario[12]%></label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-5">
                                <div class="datos-perfil">
                                    <label for="Ciudad">Ciudad: </label>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-5 contenido-dos">
                                <div class="datos-perfil">
                                    <label for="Ciudad_USuario"><%=DatosUsuario[11]%></label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 col-sm-5">
                                <div class="datos-perfil">
                                    <label for="Direccion">Direcci&oacute;n: </label>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 col-sm-5 contenido-dos">
                                <div class="datos-perfil">
                                    <label for="Direccion_USuario"><%=DatosUsuario[9]%></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-1"></div>
                </div>
            </div>
            <div class="col-xs-1"></div>
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
    
    <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
    	$('#table1').dataTable({
    		"sPaginationType": "bs_normal"
                // "sPaginationType": "bs_four_button"
                // "sPaginationType": "bs_full"
                // "sPaginationType": "bs_two_button"
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
    });
    </script>
    <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
    <%session.setAttribute("Mensaje", "");%>
</body>
</html>

