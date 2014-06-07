/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
new gnMenu(document.getElementById('gn-menu'));

$("#guia").click(function() {
    guidely.init({welcome: true, startTrigger: false});
});
$(function() {

    guidely.add({
        attachTo: '#target-1'
        , anchor: 'bottom-left'
        , title: 'Al iniciar sesión'
        , text: 'A continuación se le muestran los eventos recomendados por el sistema dependiendo de los gustos y ambientes agregados.'
    });

    guidely.add({
        attachTo: '#target-2'
        , anchor: 'top-right'
        , title: 'Ver más'
        , text: 'Este ícono le permite profundizar la información de un evento.'
    });

    guidely.add({
        attachTo: '#target-3'
        , anchor: 'top-left'
        , title: 'Ver más'
        , text: 'El panel muestra la información esencial de cada evento.'
    });

    guidely.add({
        attachTo: '#target-4'
        , anchor: 'bottom-right'
        , title: 'Ver más'
        , text: 'Estos íconos le permiten ver los comentarios y la calificación de un evento.'
    });
});

