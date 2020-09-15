$(document).ready(function(){
	
	
	$.urlParam = function(name){
	    var results = new RegExp('[\?&#]' + name + '=([^&#]*)').exec(window.location.href);
	    if (results==null) {
	       return null;
	    }
	    return decodeURI(results[1]) || 0;
	}
	
	if ($.urlParam('access_token') != null) {
		window.localStorage.setItem('access_token', $.urlParam('access_token'));
		crearJugador();
	}

	$('#submit-button').click(function() {
		$.ajax({
			url:   'http://localhost:8080/oauth/token?grant_type=password',
			type: 'POST',
			data: jQuery.param({ username: $('input[name="username"]').val(), password: $('input[name="password"]').val()}),
			beforeSend: function (xhr) {
				xhr.setRequestHeader('Authorization', 'Basic Y2xpZW50ZToxMjM0NTY=');
			},
			contentType: "application/x-www-form-urlencoded",
			success: function(response) {
				window.localStorage.setItem('access_token', response.access_token);				
				window.location.href='http://localhost:8081/list.html';				
			},
			error: function(xhr, textStatus, error) {
	            console.log(xhr.responseText);
	            console.log(xhr.statusText);
	            console.log(textStatus);
	            console.log(error);

	          }
		});
	});	
});

function crearJugador() {

	$.ajax({
		url: 'http://localhost:8080/user/me',
		type: 'GET',
		async: false,
        cache: false,
        timeout: 30000,
		beforeSend: function(xhr) {
			xhr.setRequestHeader('Authorization','Bearer ' + window.localStorage.getItem('access_token'));
		},
		success: function(data) {
			guardarJugador(data);
			window.location.href='http://localhost:8081/list.html';
		},
		error: function(xhr, textStatus, error) {
            console.log(xhr.responseText);
            console.log(xhr.statusText);
            console.log(textStatus);
            console.log(error);

          }
	});
}

function guardarJugador(data) {

	var jugador = JSON.stringify({ 
		"id":null,
		"idProveedor": data.id,
		"username": data.username,
		"avatar": data.avatar,
		"fullName": data.fullName,
		"email": data.email,
		"proveedor": data.proveedor,
		"balanceTotal": data.saldo,
		"token": window.localStorage.getItem('access_token')
	});

	window.localStorage.setItem('jugador', jugador);

	$.ajax({
		contentType: 'application/json',
		data: jugador,
		async: false,
        cache: false,
        timeout: 30000,
		success: function(data){
			console.log(data)
		},
		error: function(xhr, textStatus, error){
            console.log(xhr.responseText);
            console.log(xhr.statusText);
            console.log(textStatus);
            console.log(error);
		},
		processData: false,
		url: '/jugador/save',
		type: 'POST',
		beforeSend: function(xhr) {
			xhr.setRequestHeader('Authorization','Bearer ' + window.localStorage.getItem('access_token'));
		},
	});
}