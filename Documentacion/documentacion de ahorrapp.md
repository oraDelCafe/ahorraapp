<!--
/**
documentacion de ahorrapp base de datos.
ahorrappVico se ha hizó en windows. 
Puede que al leerlo en linux de problemas 
esta es la base de datos
*/
/*
/* crear base de datos 
drop database ahorrappVico;
*/ 
drop database if exists ahorrappVico;
create database ahorrappVico;
use ahorrappVico;

/* tabla usuarios
drop table if exists Usuarios;
*/

create table Usuarios (

CodUsuarios int auto_increment,
nombreUsu varchar(50) default null,
Ape1Usu varchar(50) default null,
Ape2Usu varchar(50) default null,
emailUsu varchar(100) default null,
dniUsu varchar(9) default null,
dirUsu varchar(150) default null,
tlfUsu varchar(15) default null,
-- pk 
constraint pk_usuarios primary key (CodUsuarios)

);

/* tabla cuentasapp
drop table if exists CuentasApp;
*/

create table CuentasApp (
CodCuentasApp int auto_increment,
CodUsuarios_cuentasapp int default null, -- fk 
SaldoCuentas decimal(10,2) default null,
FechaCuenta date default null,
CuentaBancoExterna varchar(24) default null,
-- pk 
constraint pk_cuentasapp primary key (
CodCuentasApp
),
-- fk 
constraint fk_usuarios_cuentasapp
foreign key (CodUsuarios_cuentasapp)
references Usuarios(CodUsuarios)
on delete no action
on update cascade

);

/* tabla transferencia
drop table if exists Transferencia;
*/

create table Transferencia (
CodTransferencia int auto_increment,
fechaTrans date not null,
tipoTrans enum('Ingreso','Gasto') default null,
cantidadTrans decimal(10,2) default null,
conceptoTrans varchar(300) default null,
codigoTransaccion varchar(100) default null,
CodCuentas_trans int , 
-- pk 
constraint pk_transferencia
primary key (CodTransferencia),

-- fk 

constraint fk_cuentas_cuentasapp
foreign key (CodCuentas_trans)
references CuentasApp(CodCuentasApp)
on delete no action
on update cascade

);

/* tabla objetivos
drop table if exists Objetivos;
*/

create table Objetivos (

CodObjetivos int auto_increment,
nombreObj varchar(100) default null,
cantidadObj decimal(10,2) unsigned default null,
descipObj varchar(300) default null,
cantConseObj decimal(11,2) default null,
CodUsuarios_obj int null default null,
-- pk 
constraint pk_objetivos
primary key (CodObjetivos
),

-- fk 
constraint fk_usuarios_objetivos
foreign key (CodUsuarios_obj)
references Usuarios(CodUsuarios)
on delete no action
on update cascade

);


/* tabla detalleObjetivoProgramados
drop table if exists detalleObjetivoProgramados;
*/

create table detalleObjetivoProgramados (

CodCuentasApp_detprog int not null,
CodObjetivos_detprog int not null,
CodDetalleObjetivos_detprog int not null auto_increment,
fechaini_detprog  date default null,
cantidadFija_detprog  decimal(11,2) unsigned default null,
fechainactividad_detprog  date null default null,
fechafin_detprog  date default null ,
periodicidad_detprog  enum(
'diario' ,'quicena','mensual','trimestral'
) default null,
-- pk 
constraint pk_detalleobjetivoprogramados
primary key (
CodDetalleObjetivos_detprog,
CodCuentasApp_detprog ,
CodObjetivos_detprog
),
-- fk 
constraint fk_cuentasapp_detalleprogramado
foreign key (CodCuentasApp_detprog)
references CuentasApp(CodCuentasApp)
on delete no action
on update cascade,

constraint fk_objetivos_detalleprogramado
foreign key (CodObjetivos_detprog)
references Objetivos(CodObjetivos)
on delete no action
on update cascade

); 

create table detalleObjetivoPuntual (

CodObjetivos_detpunt int auto_increment,
CodCuentasApp_detpunt int not null,
cantidad_detpunt decimal(11,2) unsigned not null, 
fecha_detpunt datetime not null, --  controlo fecha y hora
-- pk 
constraint pk_detalleobjetivopuntual
primary key (
CodObjetivos_detpunt,
CodCuentasApp_detpunt,
fecha_detpunt
),
-- fk 
constraint fk_cuentasapp_detallepuntual
foreign key (CodCuentasApp_detpunt)
references CuentasApp(CodCuentasApp)
on delete cascade
on update cascade,

constraint fk_objetivos_detallepuntual
foreign key (CodObjetivos_detpunt)
references Objetivos(CodObjetivos)
on delete no action
on update cascade

);

*/
-->