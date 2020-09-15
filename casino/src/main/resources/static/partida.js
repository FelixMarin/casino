$(document).ready(function() {

    var juego = JSON.parse(window.localStorage.getItem('juego'));
    var jugador = JSON.parse(window.localStorage.getItem('jugador'));
    var partida = JSON.parse(window.localStorage.getItem('partida'));

    $('#titulo-juego').append('<h1>Partida: ' + juego.nombre + '</h1>');

    $("#user-name").append('<p id="info"><b>Jugador:</b> ' + jugador.fullName 
            + '&emsp;|&emsp;<b>Email:</b> ' + jugador.email
            + '&emsp;|&emsp;<b>token id:</b> ' + jugador.token
            + '&emsp;|&emsp;<b>Saldo: </b> &euro;' + jugador.balanceTotal
            + '&emsp;|&emsp;<b>Balance partida: </b> &euro;' + partida.balancePartida
            + '&emsp;|&emsp;<b>Fin partida: </b> ' + partida.fechaFinPartida.split('T')[1]
            + '&emsp;|&emsp;<b>Premio: </b> ' + juego.premio + '</p>');

    $('#btn-salir').click(function(e){ 
        
        e.preventDefault();
        if (window.confirm("Está seguro de abandonar la partida?")) {
            location.href = 'list.html';
        }
    
    });

    
    $('#btn-jugada').click(function(e){ 
        
        e.preventDefault();

        let apuesta = document.getElementById('apuesta').value;

        let partidaRec = JSON.parse(window.localStorage.getItem('partida'));

        if (isNaN(apuesta) || apuesta < partidaRec.juegoEntity.apuestaMinima || 
                apuesta > partidaRec.juegoEntity.apuestaMaxima) {
            alert('Valor No permitido');
            return
          } else if(partidaRec.jugadorEntity.balanceTotal <= 0 || 
                partidaRec.jugadorEntity.balanceTotal <= apuesta) {

                    partidaRec.jugadorEntity.balanceTotal = 0;
                    jugador.balanceTotal = 0;
                    window.localStorage.setItem('partida', JSON.stringify(partidaRec));
                    window.localStorage.setItem('jugador', JSON.stringify(jugador));

            $("#user-name").children('#info').remove();
            $("#user-name").append('<p id="info"><b>Jugador:</b> ' + jugador.fullName 
                    + '&emsp;|&emsp;<b>Email:</b> ' + jugador.email
                    + '&emsp;|&emsp;<b>token id:</b> ' + jugador.token
                    + '&emsp;|&emsp;<b>Saldo: </b> &euro;' + jugador.balanceTotal
                    + '&emsp;|&emsp;<b>Balance partida: </b> &euro;' + partidaRec.balancePartida
                    + '&emsp;|&emsp;<b>Fin partida: </b> ' + partidaRec.fechaFinPartida.split('T')[1]
                    + '&emsp;|&emsp;<b>Premio: </b> ' + juego.premio + '</p>');

            alert('No tiene saldo suficiente');
            return
          }

        let partida = JSON.stringify({         
            "id": partidaRec.id,
            "jugadorEntity": {
               "id": partidaRec.jugadorEntity.id,
               "idProveedor": partidaRec.jugadorEntity.idProveedor,
               "username": partidaRec.jugadorEntity.username,
               "fullName": partidaRec.jugadorEntity.fullName,
               "email": partidaRec.jugadorEntity.email,
               "token": partidaRec.jugadorEntity.token,
               "apuesta": apuesta,
               "totalPartidas": partidaRec.jugadorEntity.totalPartidas,
               "balanceTotal": partidaRec.jugadorEntity.balanceTotal,
               "totalTiempo": partidaRec.jugadorEntity.totalTiempo
            },
            "juegoEntity": {
               "id": partidaRec.juegoEntity.id,
               "nombre":partidaRec.juegoEntity.nombre,
               "tiempoPermitido":partidaRec.juegoEntity.tiempoPermitido,
               "probabilidadAcierto":partidaRec.juegoEntity.probabilidadAcierto,
               "apuestaMaxima": partidaRec.juegoEntity.apuestaMaxima,
               "apuestaMinima": partidaRec.juegoEntity.apuestaMinima,
               "premio": partidaRec.juegoEntity.premio,
               "tipo":partidaRec.juegoEntity.tipo
            },
            "balancePartida": partidaRec.balancePartida,
            "fechaCreacion": partidaRec.fechaCreacion,                
            "fechaFinPartida":partidaRec.fechaFinPartida,
            "apuesta": apuesta,
            "resultadoJugada":partidaRec.resultadoJugada
        });

        $.ajax({
            contentType: 'application/json',
            dataType: 'json',
            data: partida,
            success: function(data) {

                let timer = data.balancePartida;

                let resultadoApuesta = 'Apuesta perdida';

                if(data.resultadoJugada == 1) {
                    resultadoApuesta = 'Apuesta ganada!';
                }

                $('#resultado-jugada').children('#msg-resultado').remove();
                $('#resultado-jugada').append('<p class="mt-3 badge badge-primary text-wrap" ' 
                        + 'id="msg-resultado">' + resultadoApuesta + '</p>')

                if(timer == '-1') {
                    alert('Tiempo finalizado');
                    location.href='http://localhost:8081/list.html';	
                }

                modificarSaldoJugador(JSON.stringify(data));

                $("#user-name").children('#info').remove();
                $("#user-name").append('<p id="info"><b>Jugador:</b> ' + jugador.fullName 
                        + '&emsp;|&emsp;<b>Email:</b> ' + jugador.email
                        + '&emsp;|&emsp;<b>token id:</b> ' + jugador.token
                        + '&emsp;|&emsp;<b>Saldo: </b> &euro;' + data.jugadorEntity.balanceTotal
                        + '&emsp;|&emsp;<b>Balance partida: </b> &euro;' + data.balancePartida
                        + '&emsp;|&emsp;<b>Fin partida: </b> ' + data.fechaFinPartida.split('T')[1]
                        + '&emsp;|&emsp;<b>Premio: </b> ' + data.juegoEntity.premio + '</p>');

            },
            error: function(xhr, textStatus, error){
                console.log(xhr.responseText);
                console.log(xhr.statusText);
                console.log(textStatus);
                console.log(error);
            },
            processData: false,
            url: '/partida/jugada',
            type: 'POST',
            beforeSend: function(xhr) {
                xhr.setRequestHeader('Authorization','Bearer ' + window.localStorage.getItem('access_token'));
            },
        });
    
    });

    $('#btn-cargar-saldo').click(function(e){ 

		let nuevoSaldo = document.getElementById('cargar-saldo-campo').value;
        let jugador = JSON.parse(window.localStorage.getItem('jugador'));
        let partida = JSON.parse(window.localStorage.getItem('partida'));
	
        jugador.balanceTotal = Number(jugador.balanceTotal) + Number(nuevoSaldo);
	
		$.ajax({
			contentType: 'application/json',
			data: JSON.stringify(jugador),
			success: function(data) {
				window.localStorage.setItem('jugador', JSON.stringify(data));

				$("#user-name").children('#info').remove();
				$("#user-name").append('<p id="info"><b>Jugador: </b> ' + jugador.fullName 
				+ '&emsp;|&emsp;<b>Email: </b> ' + jugador.email 
				+ '&emsp;|&emsp;<b>token id: </b> ' + jugador.token
                + '&emsp;|&emsp;<b>Saldo: </b> &euro;' + jugador.balanceTotal
                + '&emsp;|&emsp;<b>Balance partida: </b> &euro;' + partida.balancePartida
                + '&emsp;|&emsp;<b>Fin partida: </b> ' + partida.fechaFinPartida.split('T')[1]
                + '&emsp;|&emsp;<b>Premio: </b> ' + juego.premio + '</p>');   

                partida.jugadorEntity.balanceTotal = jugador.balanceTotal;
                window.localStorage.setItem('partida', JSON.stringify(partida));

                
                document.getElementById('cargar-saldo-campo').value = '';

			},
			error: function(xhr, textStatus, error){
				console.log(xhr.responseText);
				console.log(xhr.statusText);
				console.log(textStatus);
				console.log(error);
			},
			processData: false,
			url: '/jugador/cargarsaldo',
			type: 'POST',
			beforeSend: function(xhr) {
				xhr.setRequestHeader('Authorization','Bearer ' + window.localStorage.getItem('access_token'));
			},
		});
	});
	
	$('#btn-salir-fin').click(function(e) {
		window.localStorage.setItem('access_token', null);
		window.location.href="http://localhost:8081/login.html";
	});
});

const modificarSaldoJugador = function(partida) {

    let partidaObject = JSON.parse(partida);
    let jugadorEntityObject = partidaObject.jugadorEntity;

    $.ajax({
		contentType: 'application/json',
		data: JSON.stringify(jugadorEntityObject),
		success: function(data) {

            partidaObject.jugadorEntity = data;
            window.localStorage.setItem('jugador', JSON.stringify(data));
            window.localStorage.setItem('partida', JSON.stringify(partidaObject));
		},
		error: function(xhr, textStatus, error){
            console.log(xhr.responseText);
            console.log(xhr.statusText);
            console.log(textStatus);
            console.log(error);
		},
		processData: false,
		url: '/jugador/modificarsaldo',
		type: 'POST',
		beforeSend: function(xhr) {
			xhr.setRequestHeader('Authorization','Bearer ' + window.localStorage.getItem('access_token'));
		},
	});
};
