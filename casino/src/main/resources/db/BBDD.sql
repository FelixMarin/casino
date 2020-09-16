CREATE TABLE evento ( 
   id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL, 
   nombre VARCHAR(50) NOT NULL, 
   id_partida BIGINT DEFAULT '0', 
   id_jugador BIGINT DEFAULT '0', 
   fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
   apuesta DOUBLE DEFAULT '0',
   token VARCHAR(40),
   nombre_juego VARCHAR(50)  
);

CREATE TABLE juego (
	id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL, 
	nombre VARCHAR(50) NOT NULL,
	tipo VARCHAR(50) NOT NULL,
	premio VARCHAR(50) NOT NULL,
	apuesta_maxima DOUBLE NOT NULL,
	apuesta_minima DOUBLE NOT NULL,
	tiempo_permitido INT DEFAULT '30',
	probabilidad_acierto DOUBLE NOT NULL,
	fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE jugador (
	id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	id_proveedor BIGINT NOT NULL,
	total_partidas INT DEFAULT '0',
	total_balance DOUBLE DEFAULT '0',
	total_tiempo BIGINT DEFAULT '0',
	token VARCHAR(40) NOT NULL,
	apuesta DOUBLE DEFAULT '0', 
	fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

ALTER TABLE jugador ADD UNIQUE (token);

CREATE TABLE partida (
	id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	id_jugador BIGINT NOT NULL REFERENCES jugador(id),
	id_juego BIGINT NOT NULL REFERENCES juego(id),
	balance_partida DOUBLE DEFAULT '0',
	fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	apuesta DOUBLE DEFAULT '0',
	resultado_jugada INT DEFAULT '0',
	fecha_fin_partida TIMESTAMP
);

insert into juego(id, nombre, tipo, premio, apuesta_maxima, apuesta_minima, tiempo_permitido, probabilidad_acierto) 
values(1, 'VIDEO-BINGO', 'Bingo', 500.00, 100.00, 50.00,20 ,0.02);

insert into juego(id, nombre, tipo, premio, apuesta_maxima, apuesta_minima, tiempo_permitido, probabilidad_acierto) 
values(2, 'SLOT', '5 Rodillos', 500.00, 100.00, 50.00,30 ,0.03);

insert into juego(id, nombre, tipo, premio, apuesta_maxima, apuesta_minima, tiempo_permitido, probabilidad_acierto) 
values(3, 'BLACKJACK', 'Cartas', 500.00, 100.00, 50.00,25 ,0.02);

insert into juego(id, nombre, tipo, premio, apuesta_maxima, apuesta_minima, tiempo_permitido, probabilidad_acierto)
values(4, 'POKER', 'Cartas', 500.00, 100.00, 50.00,20 ,0.01);

insert into juego(id, nombre, tipo, premio, apuesta_maxima, apuesta_minima, tiempo_permitido, probabilidad_acierto)
values(5, 'RULETA', 'Fichas', 500.00, 100.00, 50.00,35 ,0.01);