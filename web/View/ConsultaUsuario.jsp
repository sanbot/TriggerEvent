<%-- 
Document   : ConsultaUsuario
Created on : 18-mar-2014, 14:17:00
Author     : ADSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionAdministrador.jspf" %>
<%
Contr_Consultar usu = new Contr_Consultar();
String[][] ListaUsuario = usu.BuscarDatosUsuariosPenditeTodos();
String Cantidad = usu.getCantidadPendientes();
if(session.getAttribute("TipoMensaje").equals("Aprobar"))
{
    session.setAttribute("TipoMensaje", "Dio");
}
else if (session.getAttribute("TipoMensaje").equals("AprobarNO"))
{
    session.setAttribute("TipoMensaje", "NODio");
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
    <br/>
    <br/>
    <br/>
    <div class="container">
        <div class="row clearfix">
            <div class="col-xs-12">
                <ol class="breadcrumb">
                    <li><a href="index.jsp">Inicio</a></li>
                    <li class="active">Registrar y Consultar Usuarios</a></li>
                </ol>
            </div>
        </div>
        <div class="row clearfix">
            <div class="col-md-12">
                <h1 class="Center">Registrar y Consultar Usuarios</h1>
            </div>
        </div>
        <%if(Cantidad!= null && !Cantidad.equals("0"))
        {%>
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 col-sm-4 col-sm-offset-2 col-md-offset-4 col-md-2">
                    <div class="form-group">
                        <a href="CUsuariosPendientes.jsp"class="btn btn-block defecto">Empresas pendientes <span class="badge pull-right animacion-bell"><%=Cantidad%></span></a>
                    </div>
                </div>
                <div class="col-xs-6 col-xs-offset-3 col-sm-4 col-sm-offset-0 col-md-offset-0 col-md-2">
                    <div class="form-group">
                        <a href="RUsuario.jsp"class="btn btn-block defecto">Registrar usuario</a>
                    </div>
                </div>
            </div>
        <%}else{%>
        <div class="row">
            <div class="col-xs-6 col-xs-offset-3 col-sm-4 col-sm-offset-4 col-md-offset-4 col-md-4">
                <div class="form-group">
                    <a href="RUsuario.jsp"class="btn btn-block defecto">Registrar usuario</a>
                </div>
            </div>
        </div>
        <%}%>
        <div class="row">
            <div class="col-xs-12">
                <div class="table-responsive">
                    <table id="table1" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>NOMBRE</th>
                                    <th>TIPO USUARIO</th>
                                    <th>TIPO DOCUMENTO</th>
                                    <th>NÚMERO DOCUMENTO</th>
                                    <th>ESTADO</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%for(String[] Row : ListaUsuario){%>
                                    <tr>
                                        <td><%=Row[4]%></td>
                                        <td><%=Row[1]%></td>
                                        <td><%=Row[2]%></td>
                                        <td><%=Row[3]%></td>
                                        <td><%=Row[10]%></td>
                                        <td><center><a href="MUsuario.jsp?Codigo=<%=Row[0]%>"><span class="glyphicon glyphicon-edit"></span></center></td>
                                        <td><center><a title="Desaprobar" class="estadousuario" data-id="<%=Row[0]%>" data-estado="Desaprobado"><span class="glyphicon glyphicon-remove"></span></a></center></td>
                                        <td><center><a title="Aprobar" class="estadousuario" data-id="<%=Row[0]%>" data-estado="Aprobado"><span class="glyphicon glyphicon-ok"></span></a></center></td>
                                        <td><center><a href="CUsuario.jsp?Codigo=<%=Row[0]%>"><span class="glyphicon glyphicon-log-in"></span><center></td>
                                    </tr>
                                <%}%>
                            </tbody>
                    </table>
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
        $(".estadousuario").click(function(){
            var codigo = $(this).data('id');
            var estado = $(this).data('estado');
            document.body.style.cursor='wait';
            $.ajax({
                type: 'POST',
                url: '/TriggerEvent/Contr_Usuarios',
                data: {"accion": 'cambiarestadousuario',"codigousuario": codigo , "estadousuario": estado},
                success: function(data){
                    
                    location.reload();
                }
            });
        });
    });
    </script>
    <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
    <%session.setAttribute("Mensaje", "");%>
</body>
</html>

