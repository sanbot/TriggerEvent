<%-- 
Document   : ConsultaUsuario
Created on : 18-mar-2014, 14:17:00
Author     : ADSI
--%>

<%@page import="java.io.OutputStream"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionAdministrador.jspf" %>
<%
Contr_Consultar usu = new Contr_Consultar();
String[][] ListaSeleccion = usu.BuscarDatosSelccion();
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
	<link rel="stylesheet" type="text/css" href="../Libs/Bootstrap/css/bootstrap-theme.min.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/Default.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/demo.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/component.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/alertify.core.css" />
	<link rel="stylesheet" type="text/css" href="../Libs/Customs/css/alertify.default.css" />
	<link type="text/css" rel="stylesheet" href="../Libs/Customs/DataTables/css/datatables.css" media="all">

	<script src="../Libs/Customs/js/modernizr.custom.js"></script>

</head>
<body>
	<%@include file="../WEB-INF/jspf/MenuAdministrador.jspf" %>
	<br/>
	<br/>
	<br/>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12">
				<div class="form-group">
					<a href="index1.html">Inicio</a> <span class="glyphicon glyphicon-share-alt"></span>Registro y Consulta de Selecci&oacute;n
				</div>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-12">
				<br/>
				<h1 class="Center">Registro y Consulta de Departamentos</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
			</div>
			<div class="col-md-4">
				<a id="modal-Registrar" href="#modal-container-Registrar" role="button" class="btn btn-block btn-default" data-toggle="modal">Registrar</a>
			</div>
			<div class="col-md-4">
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				
				<br/>
				<div class="table-responsive">
					<table id="table1" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered">
						<thead>
							<tr>
								<th>C&Oacute;DIGO</th>
								<th>NOMBRE</th>
                                                                <th>TIPO</th>
                                                                <th>IMAGEN</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<%
                                                        int i = 0;
                                                        for(String[] Row : ListaSeleccion){%>
							<tr>
								<td><%=Row[0]%></td>
								<td><%=Row[1]%></td>
                                                                <td><%=Row[2]%></td>
                                                                <td><img src="Imagen.jsp?Codigo=<%=Row[0]%>" class="img-responsive imgseleccion"></td>
								<td><center><a class="modal-Modifica" href="#modal-container-Modificar" data-toggle="modal" data-id="<%=Row[0]%>" data-nombre="<%=Row[1]%>"><span class="glyphicon glyphicon-edit"></span><center></td>
							</tr>
							<%
                                                        i++;
                                                        }%>
						</tbody>
					</table>
				</div>			</div>
				<div class="container marketing">
					<hr class="featurette-divider">
				</div>
			</div>
			<div class="row">
                            <form data-validate="parsley" method="post" enctype="multipart/form-data" action="/TriggerEvent/Contr_Seleccion">
					<div class="col-md-12">
						<div class="modal fade" id="modal-container-Registrar" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
										<h4 class="modal-title" id="myModalLabel">
                                                                                    <center>Registrar una Selecci&oacute;n</center>
										</h4>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="col-md-2"></div>
											<div class="col-md-8">
												<div class="form-group">
													<label for="Nombre">Nombre</label>
													<input id="Nombre" name="Nombre" class="form-control" type="text" data-notblank="true" data-rangelength="[3,30]" data-required="true">
												</div>
											</div>
                                                                                        <div class="col-md-2">
                                                                                            
                                                                                        </div>
										</div>
                                                                                <div class="row">
                                                                                    <div class="col-md-2"></div>
                                                                                    <div class="col-md-8">
                                                                                        <div class="form-group">
                                                                                            <label for="Tipo">Tipo</label>
                                                                                            <select name="Tipo" id="Tipos" class="form-control" data-required="true">
                                                                                                <option value="Gusto">Gusto</option>
                                                                                                <option value="Ambiente">Ambiente</option>
                                                                                            </select>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="col-md-2"></div>
                                                                                </div>
                                                                            <div class="row">
                                                                                    <div class="col-md-2"></div>
                                                                                    <div class="col-md-8">
                                                                                        <div class="form-group">
                                                                                            <label for="Tipo">Tipo</label>
                                                                                            <input id="archivo" type="file" name="Imagen" data-required="true" accept="image/*"/>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="col-md-2"></div>
                                                                                </div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button><button name="RegistrarSeleccion" type="submit" class="btn btn-primary">Registrar</button> 
									</div>
								</div>
								
							</div>
							
						</div>
					</div>
				</form>
			</div>
                        <div class="row">
				<form data-validate="parsley" method="post" action="/TriggerEvent/Contr_Departamento">
					<div class="col-md-12">
						<div class="modal fade" id="modal-container-Modificar" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
										<h4 class="modal-title" id="myModalLabel">
                                                                                    <center>Modificar</center>
										</h4>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="col-md-2"></div>
											<div class="col-md-8">
												<div class="form-group">
                                                                                                        <label for="Codigo">C&oacute;digo</label>
													<input id="ConCodigo" name="Codigo" class="form-control" type="text" data-notblank="true" data-rangelength="[3,30]" data-required="true"readonly>
												</div>
											</div>
											<div class="col-md-2"></div>
										</div>
                                                                                <div class="row">
											<div class="col-md-2"></div>
											<div class="col-md-8">
												<div class="form-group">
													<label for="Nombre">Nombre</label>
													<input id="ConNombre" name="Nombre" class="form-control" type="text" data-notblank="true" data-rangelength="[3,30]" data-required="true">
												</div>
											</div>
											<div class="col-md-2"></div>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                                <button name="ModificarDepartamento" type="submit" class="btn btn-primary">Modificar</button>
									</div>
								</div>
								
							</div>
							
						</div>
					</div>
				</form>
			</div>
			<!-- FOOTER -->
			<footer>
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
    <script type="text/javascript" src="../Libs/Customs/DataTables/js/jquery.dataTables.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="../Libs/Customs/DataTables/js/datatables.js" charset="UTF-8"></script>
    <script>
    	new gnMenu( document.getElementById( 'gn-menu' ) );
    </script>
    <script>
    $(document).ready(function () {
        $(".modal-Modifica").click(function(){
        var Id = $(this).data('id');
        var Name = $(this).data('nombre');
        $(".modal-body #ConCodigo").val( Id );
        $(".modal-body #ConNombre").val( Name );
        });
    });
    </script>
    <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
    <script type="text/javascript">
    	$(document).ready(function() {
            $('input[type=file]').change(function () {
                console.log(this.files[0].mozFullPath);
            });
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

