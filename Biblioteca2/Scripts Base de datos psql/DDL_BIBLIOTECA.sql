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

