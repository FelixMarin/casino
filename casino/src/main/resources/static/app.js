$(document).ready(function(){

	$.ajax({
		url: '/juego/juegos',
		type: 'GET',
		beforeSend: function(xhr) {
			xhr.setRequestHeader('Authorization','Bearer ' + window.localStorage.getItem('access_token'));			
		},
		success: function(data) {
			let jugador = JSON.parse(window.localStorage.getItem('jugador'));
			let html = "";
			$.each(data, function(index, value){
				html += '<tr onclick="abrirjuego('+ value.id +')">';
				html += '<td>'+ value.nombre+'</td>';
				html += '<td>'+ value.tipo+'</td>';
				html += '<td>'+ value.premio+'</td>';
				html += '<td>'+ value.apuestaMaxima+'</td>';
				html += '<td>'+ value.apuestaMinima+'</td>';
				html += '<td>'+ value.tiempoPermitido+'</td>';
				html += '</tr>';
			});
			$("#productos-table-body").append(html);
			$("#user-name").append('<p><b>Jugador: </b> ' + jugador.fullName 
			+ '&emsp;|&emsp;<b>Email: </b> ' + jugador.email 
			+ '&emsp;|&emsp;<b>token id: </b> ' + jugador.token
			+ '&emsp;|&emsp;<b>Saldo: </b> &euro;' + jugador.balanceTotal + '</p>');
		},
		error: function(xhr, textStatus, error) {
            console.log(xhr.responseText);
            console.log(xhr.statusText);
            console.log(textStatus);
            console.log(error);

          }
	});
});

function abrirjuego(id) {
	$.ajax({
		url: '/juego/juego/' + id,
		type: 'GET',
		beforeSend: function(xhr) {
			xhr.setRequestHeader('Authorization','Bearer ' + window.localStorage.getItem('access_token'));			
		},
		success: function(data) {
			window.localStorage.setItem('juego', JSON.stringify(data));
			window.location.href="http://localhost:8081/juego.html";
		},
		error: function(xhr, textStatus, error) {
            console.log(xhr.responseText);
            console.log(xhr.statusText);
            console.log(textStatus);
            console.log(error);
          }
	});
}