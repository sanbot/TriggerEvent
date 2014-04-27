<%-- 
Document   : ConsultaUsuario
Created on : 18-mar-2014, 14:17:00
Author     : ADSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Controlador.Contr_Consultar"%>
<%@include file="../WEB-INF/jspf/VariablesIniciales.jspf" %>
<%@include file="../WEB-INF/jspf/ValidacionGeneral.jspf" %>
<%
Contr_Consultar usu = new Contr_Consultar();
String[][] ListaEventos = usu.getBuscarDatosPrincipalesEvento();
int Cantidad = usu.getCantidadEventosPendientes();
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
        <%}%>
	<br/>
	<br/>
	<br/>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12">
				<div class="form-group">
                                    <ol class="breadcrumb">
                                        <li><a href="index.jsp">Inicio</a></li>
                                        <li class="active">Eventos</a></li>
                                    </ol>
				</div>
			</div>
		</div>
                
                
                <div class="row" >
                    <div class="col-md-12">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                    <h3 class="panel-title">
                                        Eventos 
                                        <%if(Rol.equals("Administrador") || Rol.equals("Empresa"))
                                        {%>
                                        <a href="RegistrarEvento.jsp" role="button" class="close alignregistrar normalcerrar">Registrar    </a>
                                        <%}%>
                                        <%if(Cantidad!=0 && Rol.equals("Administrador"))
                                        {%>
                                        <a href="CEventoPendiente.jsp" class="pull-right" ><span class="glyphicon glyphicon-bell aligncerar animacion-bell" title="Eventos pendientes"></span><span class="badge"><%=Cantidad%></span></a>
                                        <%}%>
                                    </h3>
                                
                            </div>
    			</div>
                        <%
                            int i = 1;
                            int x = 1;
                            int field = 1;
                            int pag = 1;
                            for(String[] Row : ListaEventos){%>
                                <%if(i ==1) { %>
                                <%if(x==1){%><div id="<%=pag%>" class="row paginacion"><%}%>
                                    <div class="col-md-12"><div class="row">
                                <%}%>
                                    <div class="col-md-4">
                                        <div class="panel panel-primary">
                                            <div class="panel-heading">
                                                    <h3 class="panel-title">
                                                            <%=Row[1]%>
                                                            <a href="DetalleEvento.jsp?CodigoEvento=<%=Row[0]%>"><span class="glyphicon glyphicon-log-in close aligncerar"></span></a>
                                                    </h3>
                                            </div>
                                            <div class="panel-body">
                                                    <div class="row">
                                                            <div class="col-md-12">
                                                                    <center><img src="ImagenEvento.jsp?Codigo=<%=Row[0]%>" class="img-thumbnail imgevento"/></center>
                                                            </div>
                                                    </div>
                                                    <div class="row">
                                                            <div class="col-md-12">
                                                                    <label for="Creador">Creador: <%=Row[3]%></label>
                                                            </div>
                                                    </div>
                                                    <div class="row">
                                                            <div class="col-md-12">
                                                                    <label for="Ciudad">Ciudad: <%=Row[4]%></label>
                                                            </div>
                                                    </div>
                                                            <div class="row">
                                                            <div class="col-md-12">
                                                                    <label for="Hora">Hora: <%=Row[5]%></label>
                                                            </div>
                                                    </div>
                                            </div>
                                            <div class="panel-footer">
                                                    <label for="Fecha">Fecha: <%=Row[2]%></label>
                                            </div>
                                        </div>
                                    </div>
                                    <%
                                    
                                    if(ListaEventos.length==field){
                                        if(ListaEventos.length%3 > 0)
                                        {
                                            for(int j = 1 ; j<= (3-ListaEventos.length%3);j++)
                                            {%>
                                                <div class="col-md-4"></div>
                                                
                                            <%}
                                            %></div></div></div><%
                                        } 
                                    }
                                    field++;
                                   if(i == 3) { 
                                        i=0;
                                        %></div></div><%
                                        if(x==3){%></div><%
                                            x=0;
                                            pag++;
                                        }
                                        x++;
                                        
                                    }
                                    i++;
                                }
                                
                                %>
                                

                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <ul class="pagination">
                                <li><a id="previo">Previo</a></li>
                                <%for(i = 1; i<=pag;i++){%>
                                <li <%if(i==1){%>class="active"<%}%>><a class="pag"><%=i%></a></li>
                                <%}%>
                                <li><a id="siguiente">Siguiente</a></li>
                            </ul>            
                        </div>
                        
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
    <script>
    	new gnMenu( document.getElementById( 'gn-menu' ) );
    </script>
    <script>
    $(document).ready(function () {
        $(".paginacion").addClass("hide");
        $("#1").removeClass("hide");
        
        $("li").nextAll().each( function() {
            if ($(this).hasClass("active")) {
                if($(this).prev().find("a").text()===$("#previo").text())
                {
                    $("#previo").parent("li").addClass("disabled");
                }
                if($(this).next().find("a").text()===$("#siguiente").text()){
                    $("#siguiente").parent("li").addClass("disabled");
                }
            }
        });
        $(".pag").click(function(){
            $("li.active").removeClass("active");
            $(this).parent().addClass("active");
            $(".paginacion").addClass("hide");
            var idpag = "#"+$(this).text();
            $(idpag).removeClass("hide");
            $(".disabled").removeClass("disabled");
            $("li").nextAll().each( function() {
                if ($(this).hasClass("active")) {
                    if($(this).prev().find("a").text()===$("#previo").text())
                    {
                        $("#previo").parent("li").addClass("disabled");
                    }
                    if($(this).next().find("a").text()===$("#siguiente").text()){
                        $("#siguiente").parent("li").addClass("disabled");
                    }
                }
            });
        });
        $("#previo").click(function(){
            if($("li.disabled:first").find("a").text()!== $("#previo").text())
            {
                var activo = $("li.active").prev();
                var textoactivo =$("li.active").prev().find("a").text();
                $("li.active").removeClass("active");
                $(".paginacion").addClass("hide");
                activo.addClass("active");
                $(".disabled").removeClass("disabled");
                $("#"+textoactivo).removeClass("hide");
                $("li").nextAll().each( function() {
                    if ($(this).hasClass("active")) {
                        if($(this).prev().find("a").text()===$("#previo").text())
                        {
                            $("#previo").parent("li").addClass("disabled");
                        }
                        if($(this).next().find("a").text()===$("#siguiente").text()){
                            $("#siguiente").parent("li").addClass("disabled");
                        }
                    }
                });
            }
            
        });
        $("#siguiente").click(function(){
            if($("li.disabled:last").find("a").text()!== $("#siguiente").text())
            {
                var activo = $("li.active").next();
                var textoactivo =$("li.active").next().find("a").text();
                $("li.active").removeClass("active");
                $(".paginacion").addClass("hide");
                activo.addClass("active");
                $("#"+textoactivo).removeClass("hide");
                $(".disabled").removeClass("disabled");
                $("li").nextAll().each( function() {
                    if ($(this).hasClass("active")) {
                        if($(this).prev().find("a").text()===$("#previo").text())
                        {
                            $("#previo").parent("li").addClass("disabled");
                        }
                        if($(this).next().find("a").text()===$("#siguiente").text()){
                            $("#siguiente").parent("li").addClass("disabled");
                        }
                    }
                });
            }
        });
        $(".modal-Modifica").click(function(){
        var Id = $(this).data('id');
        var Name = $(this).data('nombre');
        $(".modal-body #ConCodigo").val( Id );
        $(".modal-body #ConNombre").val( Name );
        });
    });
    </script>
    <script type="text/javascript" src="../Libs/Customs/js/alertify.js"></script>
    <%@include file="../WEB-INF/jspf/NotificacionesyAlertas.jspf" %>
    <%session.setAttribute("Mensaje", "");%>
</body>
</html>

