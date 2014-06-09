/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
new gnMenu(document.getElementById('gn-menu'));
$(function() {

    guidely.add({
        attachTo: '#target-1'
        , anchor: 'bottom-left'
        , title: 'Correo y contraseña'
        , text: 'Debe diligenciar completa y correctamente los campos que se muestran.'
    });
    guidely.add({
        attachTo: '#target-2'
        , anchor: 'bottom-left'
        , title: 'Iniciar sesión o limpiar campos'
        , text: 'Puede, ya sea ingresar al aplicativo o limpiar los campos diligenciados.'
    });
    guidely.add({
        attachTo: '#target-3'
        , anchor: 'bottom-left'
        , title: 'Lo más destacado'
        , text: 'Estos son los eventos con mejor calificación (dada por nuestros usuarios).'
    });
    if ($("#target-4").lenght) {
        guidely.add({
            attachTo: '#target-4'
            , anchor: 'bottom-right'
            , title: 'Comentarios y/o Calificación'
            , text: 'En todos los eventos habrán comentarios y/o calificaciones. Recuerde que para visualizarlos debe iniciar sesión.'
        });
        guidely.add({
            attachTo: '#target-5'
            , anchor: 'bottom-left'
            , title: 'Los más comentados'
            , text: 'Estos son los eventos más polémicos.'
        });
        guidely.add({
            attachTo: '#target-6'
            , anchor: 'bottom-left'
            , title: 'Próximos eventos'
            , text: 'Estos son los eventos más próximos con respecto a la fecha actual.'
        });
    }
    if ($("#target-7").lenght) {
        guidely.add({
            attachTo: '#target-7'
            , anchor: 'top-right'
            , title: 'Algunos comentarios'
            , text: 'Estos son algunos comentarios dados por nuestros usuarios acerca de los eventos.'
        });
    }
    if (!localStorage.getItem("welcome"))
    {
        guidely.init({welcome: true, startTrigger: false});
        localStorage.setItem("welcome", true);
    }
});

var ValidarCamposinical = function() {
    var contra = $("#txtPassWordInicio").parsley('validate');
    var corre = $("#txtCorreoInicio1").parsley('validate');
    if (contra && corre)
    {
        return true;
    }
    return false;
}
var ValidarCamposmodal = function() {
    var contra = $("#txtPasswordModal").parsley('validate');
    var corre = $("#txtCorreoModal").parsley('validate');
    if (contra && corre)
    {
        return true;
    }
    return false;
}
var ValidarCamposinicalSM = function() {
    var contra = $("#txtPassWordInicio-sm").parsley('validate');
    var corre = $("#txtCorreoInicio1-sm").parsley('validate');
    if (contra && corre)
    {
        return true;
    }
    return false;
}
var loginusuario = function(correo, contrasenia, url) {
    $.ajax({
        type: 'POST',
        url: '/TriggerEvent/Contr_Usuarios',
        data: {"accion": 'loginmodal', "correo": correo, "contrasenia": contrasenia},
        success: function(data) {
            var datos = jQuery.parseJSON(data);
            console.log(datos);
            $.each(datos, function(key, val) {
                if (key == "1")
                {
                    window.location.replace(url);
                }
                else
                {
                    alertify.error(val.mensaje);
                }
            });
        }
    });
}
var comentariosusuarios = function() {
    $.ajax({
        type: 'POST',
        url: "/TriggerEvent/Contr_Satisfaccion",
        data: {'accion': 'comentarios_aleatorios'},
        success: function(data) {
            var datos = jQuery.parseJSON(data);
            var items = [];
            $.each(datos, function(key, value) {
                items.push('<li class="from_user left">');
                items.push('<div class="message_wrap"> ');
                items.push('<span class="arrow"></span>');
                items.push('<div class="info"> ');
                items.push('<span class="name">Usuario:' + value.usuario + ', Evento: ' + value.evento + '</span>');
                items.push('</div>');
                items.push('<div class="text"> ');
                items.push(value.comentario);
                items.push('</div>');
                items.push('</div>');
                items.push('</li>');
            });
            $("#comentarios-aleatorios").html(items.join(""));
        }
    });
}
var eventosdestacados = function(urlservidor, accion, contenidoevento) {
    $.ajax({
        type: 'POST',
        url: urlservidor,
        data: {"accion": accion},
        success: function(data) {
            var datos = jQuery.parseJSON(data);
            var items = [];
            $.each(datos, function(key, value) {
                items.push('<div class = "panel panel-primary" >');
                items.push('<div class = "panel-heading" >');
                items.push('<h3 class = "panel-title" >' + value.nombre);
                items.push('</h3>');
                items.push('</div>');
                items.push('<div class = "panel-body" id = "target-4" >');
                items.push('<div class = "row" >');
                items.push('<div class = "col-md-12" >');
                items.push('<center >');
                items.push('<img src = "../Libs/Customs/images/Evento/' + value.imagen + '" class = "img-thumbnail imgevento" />');
                items.push('</center>');
                items.push('</div>');
                items.push('</div>');
                items.push('<div class = "row" >');
                items.push('<div class = "col-md-12" >');
                items.push('<label for = "Creador" > Creador: ' + value.empresa + ' </label>');
                items.push('</div>');
                items.push('</div>');
                items.push('<div class = "row" >');
                items.push('<div class = "col-md-12" >');
                items.push('<label for = "Ciudad" > Ciudad: ' + value.ciudad + ' </label>');
                items.push('</div>');
                items.push('</div>');
                items.push('<div class = "row" >');
                items.push('<div class = "col-md-12" >');
                items.push('<label for = "Fecha" > Fecha: ' + value.fecha + ' </label>');
                items.push('</div>');
                items.push('</div>');
                items.push('<div class = "row" >');
                items.push('<div class = "col-md-12" >');
                items.push('<label for = "Hora" > Hora: ' + value.hora + ' </label>');
                items.push('</div>');
                items.push('</div>');
                items.push('</div>');
                items.push('<div class = "panel-footer" >');
                items.push('<label for = "Espacio" > </label>');
                items.push('<p class = "pull-right calificacionevento" data-id="' + value.codigo + '" >');
                items.push('<span title = "Calificaci&oacute;n" class = "glyphicon glyphicon-star" >');
                items.push(value.calificacion == null ? 0 : value.calificacion);
                items.push('</span>');
                items.push('<span title = "Comentarios" class = "glyphicon glyphicon-comment" >');
                items.push(value.comentario == null ? 0 : value.comentario);
                items.push('</span>');
                items.push('</p>');
                items.push('</div>');
                items.push('</div>');
            });
            $("#" + contenidoevento).append(items.join(""));
        }
    }).done(eventoslistos);
}
$("#guia").click(function() {
    guidely.init({welcome: true, startTrigger: false});
});
var eventoslistos = function() {

    $(".calificacionevento").click(function() {
        var Id = $(this).data('id');
        $("#codigoeventomodal").val(Id);
        $('#Modal-Login').modal('show');
    });
    $("#modal-oldive").click(function() {
        $('#Modal-Login').modal('hide');
    });
}

$("#loginingresar").click(function() {
    if (ValidarCamposinical()) {
        var correo = $("#txtCorreoInicio1").val();
        var contrasenia = $("#txtPassWordInicio").val();
        var url = "/TriggerEvent/View/EventoRecomendado.jsp";
        loginusuario(correo, contrasenia, url);
    }
});

$("#btn-login").click(function() {
    if (ValidarCamposmodal()) {
        var correo = $("#txtCorreoModal").val();
        var contrasenia = $("#txtPasswordModal").val();
        var CodigoEvento = $("#codigoeventomodal").val();
        var url = "/TriggerEvent/View/DetalleEvento.jsp?CodigoEvento=" + CodigoEvento + "#titulo-opinion";
        loginusuario(correo, contrasenia, url);
    }
});

$("#loginingresarsm").click(function() {
    if (ValidarCamposinicalSM()) {
        var correo = $("#txtCorreoInicio1-sm").val();
        var contrasenia = $("#txtPassWordInicio-sm").val();
        var url = "/TriggerEvent/View/EventoRecomendado.jsp";
        loginusuario(correo, contrasenia, url);
    }
});
$(document).ready(function() {
    eventosdestacados('/TriggerEvent/Contr_Help', "eventos_destacados", "contenido-eventos-destacados");
    eventosdestacados('/TriggerEvent/Contr_Help', "eventos_comentados", "contenido-eventos-comentados");
    eventosdestacados('/TriggerEvent/Contr_Help', "eventos_proximos", "contenido-eventos-proximos");
    comentariosusuarios();
});
