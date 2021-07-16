create schema biblioteca authorization postgres;
set search_path to biblioteca;

create table libro
(	isbn int primary key,
	titulo varchar (100) not null,
	editorial varchar (60),
	pais varchar(40),
	anio int
);

create table autor
(	id_autor int primary key,
	nombre varchar (80),
	nacionalidad varchar (30),
	especialidad varchar (30),
	fecha_nacimiento date
);

create table libro_autor
(	isbn int references libro (isbn),
	id_autor int references autor (id_autor)
);

create table ejemplar
(	id_ejemplar int primary key,
	isbn int references libro (isbn),
	estado_conservacion varchar check (estado_conservacion='x' or estado_conservacion='b' or estado_conservacion='r' or estado_conservacion='m'),
	prestado boolean default false,
	observaciones text
);

create table usuario
(	no_usuario int primary key,
	nombre varchar (80),
	domicilio varchar (100)
);

create table telefono_usuario
(	no_usuario int references usuario(no_usuario),
	no_tel varchar(15),
	tipo_tel varchar (20)
);

create table profesor
(	no_usuario int unique references usuario(no_usuario),
	no_tarjeta int unique,
	depto_ad varchar (50),
	titulo varchar (15)
);

create table alumno
(	no_usuario int unique references usuario(no_usuario),
	no_control varchar (8) unique,
	carrera varchar (20)
);

create table bibliotecario 
(	id_biblio int primary key,
	nombre varchar (80),
	turno varchar (10),
	horario varchar (20)
);

create table prestamo
(	folio_prestamo int primary key,
	fecha date,
	no_usuario int references usuario (no_usuario),
	id_biblio int references bibliotecario
);

create table det_prestamo
(	folio_prestamo int references prestamo (folio_prestamo),
	id_ejemplar int references ejemplar (id_ejemplar),
	observ text
);

create table devolucion
(
	folio_prestamo int references prestamo (folio_prestamo),
	fecha_dev date,
	id_ejemplar int references ejemplar(id_ejemplar),
	observ text
);

/* Comandos de configuracion*/

set search_path to biblioteca;
set datestyle to sql,dmy;


/* Comandos de inserción de datos */

insert into libro values 	(123456,'fundamentos de bases de datos','mc graw hill','mexico',2010),
				(789123,'introduccion a la programacion','prentice hall','españa',2008),
				(970154,'diseño de base de datos relacionales','alfaomega','mexico',2000),
				(844817,'transmision de datos y redes de comunicaciones','mc graw hill','argentina',2001),
				(131088,'como programar en java','pearson educacion mexico','mexico',2004),
				(849647,'introduccion a la ingenieria del software','delta publicaciones','españa',2005),
				(913456,'sistemas de bases de datos: diseño, implementación y administración','cengage learning editores', 'estados unidos',2003),
				(567123,'procesamiento de bases de datos: fundamentos, diseño e implementacion','pearson educacion', 'chile',2003),
				(690370,'introducción a los sistemas de bases de datos','pearson educación', 'mexico',2001),
				(968186,'tecnologías y redes de transmisión de datos','editorial limusa', 'mexico',2003),
				(601622,'redes de computadoras','pearson educación', 'argentina',2003),
				(842053,'una panorámica de las telecomunicaciones','pearson educación','mexico', 2002);

insert into autor values 	(100,'aníbal r. figueiras vidal','españa','telecomunicaciones','04-01-1963'),
				(200,'antonio artés rodríguez','españa','telecomunicaciones','19-04-1970'),
				(300,'enrique herrera perez','mexico','redes de transmisión','27-05-1967'),
				(400,'abraham silberschatz','usa','base de datos','15-07-55'),
				(500,'henry korth','usa','base de datos','23-02-60'),
				(600,'s. sudarshan','usa','base de datos','17-05-58'),
				(700,'jesús j. rodríguez sala','españa','programación','20-04-70'),
				(800,'adoracion de miguel castaño','españa','base de datos','19-08-53'),
				(900,'mario gerardo piattini velthuis','españa','base de datos','12-09-75'),
				(1000,'esperanza marcos gutierrez','españa','base de datos','25-11-65'),
				(1100,'behrouz a. forouzan','india','redes de comunicacion','05-01-68'),
				(1200,'harvey m. deitel','usa','programacion','07-10-65'),
				(1300,'paul j. deitel','usa','programacion','14-06-68'),
				(1400,'f. alonso amo', 'españa','ingenieria de software','16-02-57'),
				(1500,'loïc a. martínez normand','españa','ingenieria de software','18-03-63'),
				(1600,'francisco javier segovia pérez','españa','ingenieria de software','26-08-56'),
				(1700,'peter rob','usa','base de datos','18-12-59'),
				(1800,'carlos coronel','usa','base de datos','12-11-64'),
				(1900,'david m. kroenke','usa','base de datos','02-05-50'),
				(2000,'c. j. date','inglaterra','base de datos','01-04-41'),
				(2100,'enrique herrera perez','mexico','redes de transmision','05-05-48'),
				(2200,'andrew s. tanenbaum','usa','redes de computadoras','16-03-44'),
				(2300,'anibal r. figueiras vidal','mexico','telecomunicaciones','18-08-45'),
				(2400,'antonio artés rodríguez','mexico','telecomunicaciones','18-08-45');
				

				
insert into libro_autor values 	(123456,400),
				(123456,500),
				(123456,600),
				(789123,700),
				(970154,800),
				(970154,900),
				(970154,1000),
				(844817,100),
				(844817,200),
				(131088,1200),
				(131088,1300),
				(849647,1400),
				(849647,1500),
				(849647,1600),
				(913456,1700),
				(913456,1800),
				(567123,1900),
				(690370,2000),
				(968186,2100),
				(601622,2200),
				(842053,2300),
				(842053,2400);

insert into ejemplar values	(1,123456,'b',false,'no hay observaciones'),
				(2,123456,'r',false,'no hay observaciones'),
				(3,789123,'x',false,'no hay observaciones'),
				(4,789123,'b',false,'no hay observaciones'),
				(5,789123,'m',false,'no hay observaciones'),
				(6,970154,'x',false,'no hay observaciones'),
				(7,844817,'m',false,'no hay observaciones'),
				(8,131088,'b',false,'no hay observaciones'),
				(9,849647,'x',false,'no hay observaciones'),
				(10,913456,'m',false,'no hay observaciones'),
				(11,913456,'b',false,'no hay observaciones'),
				(12,567123,'r',false,'no hay observaciones'),
				(13,690370,'b',false,'no hay observaciones'),
				(14,968186,'x',false,'no hay observaciones'),
				(15,601622,'b',false,'no hay observaciones'),
				(16,842053,'x',false,'no hay observaciones');
				
insert into usuario values	(9000,'pedro juárez','morelos 408 colonia centro'),
				(9001,'juan hernández','independencia 1789 colonia nuevo mexico'),
				(9002,'mayela martinez','av. juarez 215 colonia bugambilias'),
				(9003,'adriana gonzalez','andador luna lote 12 manzana 43 el rosario'),
				(9004,'alejandro siguenza','av. lazaro cardenas 1500 colonia santa lucia'),
				(9005,'julian acevedo','morelos 310 colonia centro'),
				(9006,'alma jimenez','hidalgo 865 colonia centro'),
				(9007,'carolina pantoja','zaachila 500 colonia estado de oaxaca'),
				(9008,'ana maria luna','independencia 13 colonia centro'),
				(9009,'alberto noyola','av. montealban 874 colonia azteca'),
				(9010,'claudia moreno','av. juarez 1289 colonia centro');

insert into telefono_usuario values	(9000,'951-123-44-22','celular'),
					(9000,'513-22-90','fijo'),
					(9001,'951-345-66-11','celular'),
					(9001,'502-45-34','trabajo'),
					(9002,'951-569-12-01','celular'),
					(9003,'515-53-13','fijo'),
					(9004,'513-23-12','fijo'),
					(9004,'951-423-56-45','celular'),
					(9008,'148-55-87','fijo'),
					(9009,'951-249-09-11','celular'),
					(9010,'951-109-44-15','celular');

insert into profesor values		(9000,123,'mecanica','ingeniero'),
					(9003,408,'ciencias basicas','ingeniera'),
					(9006,556,'sistemas y computacion','licenciada'),
					(9008,306,'civil','ingeniera'),
					(9010,210,'electrica','ingeniera');					


insert into alumno values		(9001,'09161234','informatica'),
					(9002,'10160345','electrica'),
					(9004,'10161123','sistemas'),
					(9005,'08161006','ingenieria');

insert into bibliotecario values	(5000,'juan perez','matutino','7-14'),
					(5001,'concepcion alfaro','matutino','7-14'),
					(5003,'adrian urbina','vespertino','14-21'),
					(5004,'arnulfo chavez','vespertino','14-21'),
					(5005,'marcela uribe','matutino','8-15');
					
insert into prestamo values		(8000,'01-01-2013',9003,5001),
					(8001,'01-01-2013',9000,5003),
					(8002,'05-01-2013',9008,5005),
					(8003,'06-01-2013',9007,5001),
					(8004,'12-01-2013',9001,5003),
					(8005,'18-01-2013',9003,5000),
					(8006,'20-01-2013',9003,5001);

insert into det_prestamo values		(8000,5,'sin observaciones'),
					(8000,8,'sin observaciones'),
					(8000,9,'sin observaciones'),
					(8001,3,'sin observaciones'),
					(8001,6,'sin observaciones'),
					(8002,2,'sin observaciones'),
					(8004,10,'sin observaciones'),
					(8005,1,'sin observaciones'),
					(8005,7,'sin observaciones'),
					(8006,4,'sin observaciones'),
					(8006,5,'sin observaciones');

insert into devolucion values		(8000,'10-01-2013',5,'buenas condiciones'),
					(8000,'10-01-2013',8,'buenas condiciones'),
					(8000,'10-01-2013',9,'buenas condiciones'),
					(8001,'03-01-2013',3,'sin observaciones'),
					(8001,'03-01-2013',6,'sin observaciones'),
					(8002,'07-01-2013',2,'mal estado'),
					(8004,'01-02-2013',10,'sin observaciones'),
					(8005,'27-01-2013',1,'sin observaciones'),
					(8005,'27-01-2013',7,'sin observaciones'),
					(8006,'21-01-2013',4,'mal estado'),
					(8006,'21-01-2013',4,'mal estado');

