-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');

INSERT INTO users(username,password,enabled) VALUES ('inmmaysal','1nm4',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'inmmaysal','admin');

INSERT INTO users(username,password,enabled) VALUES ('juaruamur','jlrm',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'juaruamur','registeredUser');

INSERT INTO users(username,password,enabled) VALUES ('aleortpag','1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'aleortpag','owner');

INSERT INTO users(username,password,enabled) VALUES ('davgavser','1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'davgavser','admin');

INSERT INTO users(username,password,enabled) VALUES ('jualopvei','1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'jualopvei','admin');

--creando usuarios
INSERT INTO users(username,password,enabled) VALUES ('pepito','pepito',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'pepito','registeredUser');

INSERT INTO users(username,password,enabled) VALUES ('pepita','pepita',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (10,'pepita','registeredUser');

INSERT INTO users(username,password,enabled) VALUES ('maya','maya',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (11,'maya','registeredUser');

INSERT INTO users(username,password,enabled) VALUES ('juan','juan',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (12,'juan','registeredUser');

INSERT INTO users(username,password,enabled) VALUES ('pablo','pablo',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (13,'pablo','registeredUser');

INSERT INTO users(username,password,enabled) VALUES ('olivia','olivia',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (14,'olivia','registeredUser');

INSERT INTO users(username,password,enabled) VALUES ('diego','diego',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (15,'diego','registeredUser');

INSERT INTO users(username,password,enabled) VALUES ('maria','maria',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (16,'maria','registeredUser');

INSERT INTO users(username,password,enabled) VALUES ('hugo','hugo',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (17,'hugo','registeredUser');

INSERT INTO users(username,password,enabled) VALUES ('peter','peter',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (18,'peter','registeredUser');

INSERT INTO vets(id, first_name,last_name) VALUES (1, 'James', 'Carter');
INSERT INTO vets(id, first_name,last_name) VALUES (2, 'Helen', 'Leary');
INSERT INTO vets(id, first_name,last_name) VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets(id, first_name,last_name) VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets(id, first_name,last_name) VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets(id, first_name,last_name) VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');
INSERT INTO types VALUES (7, 'turtle');

INSERT INTO registered_users(id,name,description,email,username) VALUES (1, 'Pepito', 'esto es un ejemplo de descripción', 'pepito@gmail.com', 'pepito');
INSERT INTO registered_users(id,name,description,email,username) VALUES (2, 'Pepita', 'esto es un ejemplo de descripción', 'pepita@gmail.com', 'pepita');
INSERT INTO registered_users(id,name,description,email,username) VALUES (3, 'Juan', 'JUAN', 'juan@gmail.com', 'juan');
INSERT INTO registered_users(id,name,description,email,username) VALUES (4, 'Pablo', 'Pablo pro', 'pablo@gmail.com', 'pablo');
INSERT INTO registered_users(id,name,description,email,username) VALUES (5, 'Olivia', 'olivia pegar una paliza', 'olivia@gmail.com', 'olivia');
INSERT INTO registered_users(id,name,description,email,username) VALUES (6, 'Diego', 'A Diego le gusta el juego', 'diego@gmail.com', 'diego');
INSERT INTO registered_users(id,name,description,email,username) VALUES (7, 'Maria', 'Maria una partida', 'maria@gmail.com', 'maria');
INSERT INTO registered_users(id,name,description,email,username) VALUES (8, 'Hugo', 'the boss', 'hugo@gmail.com', 'hugo');
INSERT INTO registered_users(id,name,description,email,username) VALUES (9, 'Peter', 'la anguila', 'peter@gmail.com', 'peter');



INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');
INSERT INTO owners VALUES (11, 'Inma', 'Mayo', 'Reina Mercedes', 'Sevilla', '625845678', 'inmmaysal');
INSERT INTO owners VALUES (12, 'Juanluis', 'Ruano', 'Reina Mercedes', 'Sevilla', '601286562', 'juaruamur');
INSERT INTO owners VALUES (13, 'Alejandro', 'ortiz', 'Reina Mercedes', 'Sevilla', '626241356', 'aleortpag');
INSERT INTO owners VALUES (14, 'David', 'Gavira', 'Reina Mercedes', 'Sevilla', '722446559', 'davgavser');
INSERT INTO owners VALUES (15, 'JuanCarlos', 'Lopez', 'Reina Mercedes', 'Sevilla', '667496670', 'jualopvei');



INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Juanita', '2022-09-29', 7, 11);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (15, 'Julieta', '2010-06-08', 1, 12);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (16, 'Tina', '2008-08-12', 2, 14);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (17, 'Jojo', '20011-09-12', 2, 15);


INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

INSERT INTO dificultad VALUES (1, 'Facil');
INSERT INTO dificultad VALUES (2, 'Intermedio');
INSERT INTO dificultad VALUES (3, 'Dificil');

INSERT INTO tipos VALUES (1, 'Individual');
INSERT INTO tipos VALUES (2, 'Competitivo');


INSERT INTO partida(id,registered_user_id,tiempo_de_juego,tiempo_de_juego_invitado,dificultad_id,tipo_de_partida_id,id_invitado,privada,contrasenia,resultado,minas_de_jugador) VALUES (1, 1, null,null, 1, 2, 2, FALSE, null,null,null);
INSERT INTO partida(id,registered_user_id,tiempo_de_juego,tiempo_de_juego_invitado,dificultad_id,tipo_de_partida_id,id_invitado,privada,contrasenia,resultado,minas_de_jugador) VALUES (2, 2, null,null, 1, 2, 1, FALSE, null,null,null);
INSERT INTO partida(id,registered_user_id,tiempo_de_juego,tiempo_de_juego_invitado,dificultad_id,tipo_de_partida_id,id_invitado,privada,contrasenia,resultado,minas_de_jugador) VALUES (3, 1, null,null, 1, 2, 2, FALSE, null,null,null);
INSERT INTO partida(id,registered_user_id,tiempo_de_juego,tiempo_de_juego_invitado,dificultad_id,tipo_de_partida_id,id_invitado,privada,contrasenia,resultado,minas_de_jugador) VALUES (4, 1, '15:30:00','5:10:00', 1, 2, 2, TRUE, '12345',TRUE,null);
INSERT INTO partida(id,registered_user_id,tiempo_de_juego,tiempo_de_juego_invitado,dificultad_id,tipo_de_partida_id,id_invitado,privada,contrasenia,resultado,minas_de_jugador) VALUES (5, 2, null,null, 3, 1, null, null, null,null,null);

INSERT INTO historicos(id,registered_user_id,partidas_totales,partidas_ganadas,minas_encontradas,puntuacion,tiempo_total_juego,tiempo_medio_partida) VALUES (1,1,0,0,0,0,'00:00:00','00:00:00');
INSERT INTO historicos(id,registered_user_id,partidas_totales,partidas_ganadas,minas_encontradas,puntuacion,tiempo_total_juego,tiempo_medio_partida) VALUES (2,2,20,2,4,5,'00:30:10','00:01:05');
INSERT INTO historicos(id,registered_user_id,partidas_totales,partidas_ganadas,minas_encontradas,puntuacion,tiempo_total_juego,tiempo_medio_partida) VALUES (3,3,15,2,4,5,'00:22:33','00:01:48');

INSERT INTO condicion(id,name,predicado,comparador) VALUES (1,'Minas mayor que','Minas', '>');
INSERT INTO condicion(id,name,predicado,comparador) VALUES (2,'Minas menor que','Minas', '<');
INSERT INTO condicion(id,name,predicado,comparador) VALUES (3,'Minas igual que','Minas', '=');
INSERT INTO condicion(id,name,predicado,comparador) VALUES (4,'Partidas Ganadas mayor que','Partidas Ganadas', '>');
INSERT INTO condicion(id,name,predicado,comparador) VALUES (5,'Partidas Ganadas menor que','Partidas Ganadas', '<');
INSERT INTO condicion(id,name,predicado,comparador) VALUES (6,'Partidas Ganadas igual que','Partidas Ganadas', '=');
INSERT INTO condicion(id,name,predicado,comparador) VALUES (7,'Partidas Totales mayor que','Partidas Totales', '>');
INSERT INTO condicion(id,name,predicado,comparador) VALUES (8,'Partidas Totales menor que','Partidas Totales', '<');
INSERT INTO condicion(id,name,predicado,comparador) VALUES (9,'Partidas Totales igual que','Partidas Totales', '=');
INSERT INTO condicion(id,name,predicado,comparador) VALUES (10,'Puntuacion mayor que','Puntuacion', '>');
INSERT INTO condicion(id,name,predicado,comparador) VALUES (11,'Puntuacion menor que','Puntuacion', '<');
INSERT INTO condicion(id,name,predicado,comparador) VALUES (12,'Puntuacion igual que','Puntuacion', '=');
INSERT INTO condicion(id,name,predicado,comparador) VALUES (13,'Tiempo Medio mayor que','Tiempo Medio', '>');
INSERT INTO condicion(id,name,predicado,comparador) VALUES (14,'Tiempo Medio menor que','Tiempo Medio', '<');
INSERT INTO condicion(id,name,predicado,comparador) VALUES (15,'Tiempo Medio igual que','Tiempo Medio', '=');
INSERT INTO condicion(id,name,predicado,comparador) VALUES (16,'Tiempo Total mayor que','Tiempo Total', '>');
INSERT INTO condicion(id,name,predicado,comparador) VALUES (17,'Tiempo Total menor que','Tiempo Total', '<');
INSERT INTO condicion(id,name,predicado,comparador) VALUES (18,'Tiempo Total igual que','Tiempo Total', '=');


-- INSERT INTO logros(id,titulo,descripcion,condicion) VALUES (1,'Prueba', 'Prueba', 'Prueba');
-- INSERT INTO logros(id,titulo,descripcion,condicion) VALUES (2,'Prueba', 'Prueba', 'Prueba');
-- INSERT INTO logros(id,titulo,descripcion,condicion) VALUES (3,'Prueba', 'Prueba', 'Prueba');
-- INSERT INTO logros(id,titulo,descripcion,condicion) VALUES (4,'Prueba', 'Prueba', 'Prueba');


-- INSERT INTO logros_id VALUES (1,1);
-- INSERT INTO logros_id VALUES (2,2);
-- INSERT INTO logros_id VALUES (1,3);
