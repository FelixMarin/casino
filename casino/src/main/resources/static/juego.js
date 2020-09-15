$(document).ready(function() {

    let juego = JSON.parse(window.localStorage.getItem('juego'));
    let jugador = JSON.parse(window.localStorage.getItem('jugador'));

    $('#titulo-juego').append(juego.nombre);

    $('#productos-table-body').append('<tr>' +
        '<td>'+ juego.tipo +'</td>' +
        '<td>'+ juego.premio +'</td>' +
        '<td>'+ juego.apuestaMaxima +'</td>' +
        '<td>'+ juego.apuestaMinima +'</td>' +
        '<td>'+ juego.tiempoPermitido +'</td>' +
        '</tr>'
    );

    $("#user-name").append('<p><b>Jugador:</b> ' + jugador.fullName
    + '&emsp;|&emsp;<b>Email:</b> ' + jugador.email
    + '&emsp;|&emsp;<b>token id:</b> ' + jugador.token
    + '&emsp;|&emsp;<b>Saldo: </b> &euro;' + jugador.balanceTotal + '</p>');
});

const comenzarPartida = function() {

    let juego = JSON.parse(window.localStorage.getItem('juego'));
    identificarJugador();
    let jugador = JSON.parse(window.localStorage.getItem('jugador'));

    if(jugador.id == null) {
        return
    }
    
    var partida = JSON.stringify({         
        "id": null,
        "jugadorEntity": {
           "id": jugador.id,
           "idProveedor": jugador.idProveedor,
           "username": jugador.username,
           "fullName": jugador.fullName,
           "email": jugador.email,
           "token": jugador.token,
           "apuesta": jugador.apuesta,
           "totalPartidas": jugador.totalPartidas,
           "balanceTotal": jugador.balanceTotal,
           "totalTiempo": jugador.totalTiempo
        },
        "juegoEntity": {
           "id": juego.id,
           "nombre":juego.nombre,
           "tiempoPermitido":juego.tiempoPermitido,
           "probabilidadAcierto":juego.probabilidadAcierto,
           "apuestaMaxima": juego.apuestaMaxima,
           "apuestaMinima": juego.apuestaMinima,
           "premio": juego.premio,
           "tipo": juego.tipo
        },
        "balancePartida": 0,
        "fechaCreacion": null,
        "fechaFinPartida": null       
    });

	$.ajax({
		contentType: 'application/json',
		data: partida,
		success: function(data) {

            let partidaObject = JSON.parse(partida);
            partidaObject.fechaFinPartida = data.fechaFinPartida;

            window.localStorage.setItem('partida', JSON.stringify(partidaObject));
            window.location.href = "partida.html";
		},
		error: function(xhr, textStatus, error){
            console.log(xhr.responseText);
            console.log(xhr.statusText);
            console.log(textStatus);
            console.log(error);
		},
		processData: false,
		url: '/partida/save',
		type: 'POST',
		beforeSend: function(xhr) {
			xhr.setRequestHeader('Authorization','Bearer ' + window.localStorage.getItem('access_token'));
		},
	});
}

const identificarJugador = function () {

    let jugador = JSON.parse(window.localStorage.getItem('jugador'));

    $.ajax({
		contentType: 'application/json',
		data: JSON.stringify(jugador),
		success: function(data) {
            window.localStorage.setItem('jugador', JSON.stringify(data));
		},
		error: function(xhr, textStatus, error){
            console.log(xhr.responseText);
            console.log(xhr.statusText);
            console.log(textStatus);
            console.log(error);
		},
		processData: false,
		url: '/jugador/token',
		type: 'POST',
		beforeSend: function(xhr) {
			xhr.setRequestHeader('Authorization','Bearer ' + window.localStorage.getItem('access_token'));
		},
	});
};