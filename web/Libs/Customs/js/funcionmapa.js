var mapatribution = 'Imagery from <a href="http://giscience.uni-hd.de/">GIScience Research Group @ University of Heidelberg</a> &mdash; Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>';
var mapurl = 'http://openmapsurfer.uni-hd.de/tiles/roads/x={x}&y={y}&z={z}';
var crearmapa = function() {
    var streets = L.tileLayer(mapurl, {attribution: mapatribution});

    var map = L.map('map', {
        center: [04, -74],
        zoom: 5,
        layers: [streets]
    });
    var drawnItems = new L.FeatureGroup();
    map.addLayer(drawnItems);

    var drawControl = new L.Control.Draw({
        draw: {
            rectangle: false,
            polyline: false,
            polygon: false,
            circle: false,
            marker: true
        },
        edit: {
            featureGroup: drawnItems,
            edit: false,
            remove: true
        }
    });

    map.addControl(drawControl);
    map.on('draw:created', function(e) {

        var objectMap = map.getPanes();
        var objMakerPane = objectMap.markerPane;
        var countMaker = objMakerPane['childElementCount'];


        if (countMaker <= 2) {
            var type = e.layerType,
                    layer = e.layer;

            if (type === 'marker') {
                layer.bindPopup('A popup!');
            }
            var layerobj = e.layer;
            $("#txtlat").val(layerobj._latlng['lat']);
            $("#txtlng").val(layerobj._latlng['lng']);
            $("#btn-registrar").removeClass("hide");
            drawnItems.addLayer(layer);
        } else {
            alert('No se puede crear otra ubicación, por favor elimine la anterior');
        }


    });
    map.on('draw:deleted', function() {

        var objectMap = map.getPanes();
        var objMakerPane = objectMap.markerPane;
        var countMaker = objMakerPane['childElementCount'];
        if (countMaker === 0) {
            $("#txtlat").val('');
            $("#txtlng").val('');
            $("#btn-registrar").addClass("hide");
        }
    });
}
var mostrarmapa = function(latitud, longitud, nombre) {
    var streets = L.tileLayer(mapurl, {attribution: mapatribution});

    var lat = parseFloat(latitud);
    var lon = parseFloat(longitud);
    var map = L.map('map', {
        center: [lat, lon],
        zoom: 13,
        layers: [streets]
    });
    var ubicacion = {lat: latitud, lng: longitud};
    L.marker(ubicacion).addTo(map)
            .bindPopup("Evento: " + nombre).openPopup();
}

var mostrarubicacion = function(datos) {
    var streets = L.tileLayer(mapurl, {attribution: mapatribution});

    var map = L.map('map', {
        center: [04, -74],
        zoom: 5,
        layers: [streets]
    });
    var ubicacion;
    $.each(datos, function(key, val) {
        ubicacion = {lat: val.latitud, lng: val.longitud};
        L.marker(ubicacion).addTo(map)
                .bindPopup('<a href="DetalleEvento.jsp?CodigoEvento='+val.codigo+'">Evento: ' + val.nombre + '</a>').openPopup();
    });

}