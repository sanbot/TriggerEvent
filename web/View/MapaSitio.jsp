<%-- 
Document   : ConsultaUsuario
Created on : 18-mar-2014, 14:17:00
Author     : ADSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
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
	<link rel="shortcut icon" href="../favicon.ico">
	<link rel="stylesheet" type="text/css" href="../Libs/Bootstrap/css/bootstrap-theme.min.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/Default.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/demo.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/component.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/alertify.core.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/alertify.default.css" />

	<script src="../Libs/Customs/js/modernizr.custom.js"></script>

</head>
<body>
    <%
    if(Rol.equals("Administrador"))
    {%>
    <%@include file="../WEB-INF/jspf/MenuAdministrador.jspf" %>
    <%
    }else if(Rol.equals("Cliente"))
    {%>
    <%@include file="../WEB-INF/jspf/MenuCliente.jspf" %>
    <%}else if(Rol.equals("Empresa")){%>
    <%@include file="../WEB-INF/jspf/MenuEmpresa.jspf" %>
    <%}else{%>
    <%@include file="../WEB-INF/jspf/Menu.jspf" %>    
    <%}%>
        <br/>
        <br/>
        <br/>
        <div class="container">
	<div class="row clearfix">
		<div class="col-md-12">
			<div class="form-group">
                            <a href="index.jsp">Inicio</a> <span class="glyphicon glyphicon-share-alt"></span>Mapa del sitio
			</div>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12">
			<br/>
			<h1 class="Center">Mapa del sitio</h1>
		</div>
	</div>
	<div class="row">
            <%if(Rol.equals("")||Rol.equals(null)){%>
		<div class="col-md-12">
                    <div class="form-group">
                        <ul>
                                <li class="lista"><span class="gn-icon gn-icon-download glyphicondos"></span><strong>Inicio</strong></li>
                                <ul>
                                        <li class="lista"><a href="index.jsp"><span class="gn-icon gn-icon-download glyphicondos"></span> Inicio</a></li>
                                        <li class="lista"><a href="Contactenos.html"><span class="gn-icon gn-icon-help"></span>Cont&aacute;ctenos</a></li>
                                </ul>
                                <br/>
                                <li class="lista"><span class="glyphicon glyphicon-globe glyphiconuno"></span><strong>Men&uacute; Corporativo</strong></li>
                                <ul>
                                        <li class="lista"><a href="Nosotros.html"><span class="glyphicon glyphicon-thumbs-up glyphiconuno"></span>Qui&eacute;nes somos</a></li>
                                        <li class="lista"><a href="MapaSitio.jsp"><span class="glyphicon glyphicon-globe glyphiconuno"></span>Mapa del sitio </a></li>
                                        <li class="lista"><a href="#"><span class="gn-icon gn-icon-cog"></span>Ayuda en l&iacute;nea</a></li>
                                </ul>
                        </ul>
                    </div>
                </div>
            <%}%>
            <%if(Rol.equals("Administrador")){%>
                <div class="col-md-4">
                    <div class="form-group">
                        <ul>
                            <li class="lista"><span class="gn-icon gn-icon-download glyphicondos"></span><strong>Inicio</strong></li>
                            <ul>
                                    <li class="lista"><a href="index.jsp"><span class="gn-icon gn-icon-download glyphicondos"></span> Inicio</a></li>
                                    <li class="lista"><a href="Contactenos.html"><span class="gn-icon gn-icon-help"></span>Cont&aacute;ctenos</a></li>
                            </ul>
                            <br/>
                            <li class="lista"><span class="glyphicon glyphicon-user glyphiconuno"></span><strong>Mi perfil</strong></li>
                            <ul>
                                    <li class="lista"><a href="Perfil.jsp"><span class="glyphicon glyphicon-user glyphiconuno"></span>Mi perfil</a></li>
                                    <li class="lista"><a href="MisGustos.jsp"><span class="glyphicon glyphicon-headphones glyphiconuno"></span>Mis gustos</a></li>
                            </ul>
                            <br/>
                            <li class="lista"><span class="glyphicon glyphicon-glass glyphiconuno"></span><strong>Eventos</strong></li>
                            <ul>
                                    <li class="lista"><a href="ConsultaEvento.jsp"><span class="glyphicon glyphicon-glass glyphiconuno"></span>Eventos</a></li>
                                    <li class="lista"><a href="RegistrarEvento.jsp"><span class="glyphicon glyphicon-list-alt glyphiconuno"></span>Registrar Evento</a></li>
                            </ul>
                        </ul>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <ul>
                            <li class="lista"><span class="glyphicon glyphicon-phone glyphiconuno"></span><strong>Usuarios</strong></li>
                            <ul>
                                    <li class="lista"><a href="ConsultaUsuario.jsp"><span class="glyphicon glyphicon-phone glyphiconuno"></span> Usuarios</a></li>
                            </ul>
                            <br/>
                            <li class="lista"><span class="glyphicon glyphicon-music glyphiconuno"></span><strong>Gustos y ambientes</strong></li>
                            <ul>
                                    <li class="lista"><a href="ConsultaSeleccion.jsp"><span class="glyphicon glyphicon-music glyphiconuno"></span>Gustos y ambientes</a></li>
                            </ul>
                            <br/>
                            <li class="lista"><span class="glyphicon glyphicon-tower glyphiconuno"></span><strong>Departamentos</strong></li>
                            <ul>
                                    <li class="lista"><a href="ConsultaDepartamento.jsp"><span class="glyphicon glyphicon-tower glyphiconuno"></span>Departamentos</a></li>
                            </ul>
                            <br/>
                            <li class="lista"><span class="glyphicon glyphicon-tree-deciduous glyphiconuno"></span><strong>Ciudades</strong></li>
                            <ul>
                                    <li class="lista"><a href="ConsultaCiudad.jsp"><span class="glyphicon glyphicon-tree-deciduous glyphiconuno"></span>Ciudades</a></li>
                            </ul>
                        </ul>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <ul>
                            <li class="lista"><span class="glyphicon glyphicon-globe glyphiconuno"></span><strong>Men&uacute; Corporativo</strong></li>
                            <ul>
                                    <li class="lista"><a href="Nosotros.html"><span class="glyphicon glyphicon-thumbs-up glyphiconuno"></span>Qui&eacute;nes somos</a></li>
                                    <li class="lista"><a href="MapaSitio.jsp"><span class="glyphicon glyphicon-globe glyphiconuno"></span>Mapa del sitio </a></li>
                                    <li class="lista"><a href="#"><span class="gn-icon gn-icon-cog"></span>Ayuda en l&iacute;nea</a></li>
                            </ul>
                        </ul>
                    </div>
                </div>
            <%}%>
            <%if(Rol.equals("Empresa")){%>
                <div class="col-md-4">
                    <div class="form-group">
                        <ul>
                            <li class="lista"><span class="gn-icon gn-icon-download glyphicondos"></span><strong>Inicio</strong></li>
                            <ul>
                                    <li class="lista"><a href="index.jsp"><span class="gn-icon gn-icon-download glyphicondos"></span> Inicio</a></li>
                                    <li class="lista"><a href="Contactenos.html"><span class="gn-icon gn-icon-help"></span>Cont&aacute;ctenos</a></li>
                            </ul>
                            <br/>
                            <li class="lista"><span class="glyphicon glyphicon-user glyphiconuno"></span><strong>Mi perfil</strong></li>
                            <ul>
                                    <li class="lista"><a href="Perfil.jsp"><span class="glyphicon glyphicon-user glyphiconuno"></span>Mi perfil</a></li>
                                    <li class="lista"><a href="MisGustos.jsp"><span class="glyphicon glyphicon-headphones glyphiconuno"></span>Mis gustos</a></li>
                            </ul>
                        </ul>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <ul>
                            <li class="lista"><span class="glyphicon glyphicon-glass glyphiconuno"></span><strong>Eventos</strong></li>
                            <ul>
                                    <li class="lista"><a href="ConsultaEvento.jsp"><span class="glyphicon glyphicon-glass glyphiconuno"></span>Eventos</a></li>
                                    <li class="lista"><a href="MisEventos.jsp"><span class="glyphicon glyphicon-list-alt glyphiconuno"></span>Mis eventos</a></li>
                            </ul>
                            <br/>
                            <li class="lista"><span class="glyphicon glyphicon-globe glyphiconuno"></span><strong>Men&uacute; Corporativo</strong></li>
                            <ul>
                                    <li class="lista"><a href="Nosotros.html"><span class="glyphicon glyphicon-thumbs-up glyphiconuno"></span>Qui&eacute;nes somos</a></li>
                                    <li class="lista"><a href="MapaSitio.jsp"><span class="glyphicon glyphicon-globe glyphiconuno"></span>Mapa del sitio </a></li>
                                    <li class="lista"><a href="#"><span class="gn-icon gn-icon-cog"></span>Ayuda en l&iacute;nea</a></li>
                            </ul>
                        </ul>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                    </div>
                </div>
            <%}%>
            <%if(Rol.equals("Cliente")){%>
                <div class="col-md-4">
                    <div class="form-group">
                        <ul>
                            <li class="lista"><span class="gn-icon gn-icon-download glyphicondos"></span><strong>Inicio</strong></li>
                            <ul>
                                    <li class="lista"><a href="index.jsp"><span class="gn-icon gn-icon-download glyphicondos"></span> Inicio</a></li>
                                    <li class="lista"><a href="Contactenos.html"><span class="gn-icon gn-icon-help"></span>Cont&aacute;ctenos</a></li>
                            </ul>
                            <br/>
                            <li class="lista"><span class="glyphicon glyphicon-user glyphiconuno"></span><strong>Mi perfil</strong></li>
                            <ul>
                                    <li class="lista"><a href="Perfil.jsp"><span class="glyphicon glyphicon-user glyphiconuno"></span>Mi perfil</a></li>
                                    <li class="lista"><a href="MisGustos.jsp"><span class="glyphicon glyphicon-headphones glyphiconuno"></span>Mis gustos</a></li>
                            </ul>
                        </ul>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <ul>
                            <li class="lista"><span class="glyphicon glyphicon-glass glyphiconuno"></span><strong>Eventos</strong></li>
                            <ul>
                                    <li class="lista"><a href="ConsultaEvento.jsp"><span class="glyphicon glyphicon-glass glyphiconuno"></span>Eventos</a></li>
                            </ul>
                            <br/>
                            <li class="lista"><span class="glyphicon glyphicon-globe glyphiconuno"></span><strong>Men&uacute; Corporativo</strong></li>
                            <ul>
                                    <li class="lista"><a href="Nosotros.html"><span class="glyphicon glyphicon-thumbs-up glyphiconuno"></span>Qui&eacute;nes somos</a></li>
                                    <li class="lista"><a href="MapaSitio.jsp"><span class="glyphicon glyphicon-globe glyphiconuno"></span>Mapa del sitio </a></li>
                                    <li class="lista"><a href="#"><span class="gn-icon gn-icon-cog"></span>Ayuda en l&iacute;nea</a></li>
                            </ul>
                        </ul>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                    </div>
                </div>
            <%}%>
	</div>
	<!-- FOOTER -->
	<footer>
            <hr class="featurette-divider">
            <p>&copy; 2013 Trigger Event, Inc.</p>
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


