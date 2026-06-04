/*
Insercion de base de datos Vico
*/

/* 
usuarios
 */
insert into Usuarios
(nombreUsu, Ape1Usu, Ape2Usu, pass, emailUsu, dniUsu, dirUsu, tlfUsu)
values
('prueba', 'Lopez', 'Garcia', 'prueba123', 'prueba12@gmail.com', '12345678A', 'Calle Prueba 1', '600111222'),
('Pedro', 'Ortega', 'Nuñez', 'Pedro123', 'pedro12@gmail.com', '87654321B', 'Calle Luna 5', '600333444'),
('Maria', 'Ruiz', 'Martin', 'Maria123', 'maria12@gmail.com', '11223344C', 'Avenida Sol 10', '600555666');

/* 
cuentas
 */
insert into CuentasApp
(CodUsuarios_cuentasapp, SaldoCuentas, FechaCuenta, CuentaBancoExterna)
values
(1, 1500.00, curdate(), 'ES1234567890123456789012'),
(2, 2500.00, curdate(), 'ES9876543210987654321098'),
(3, 1000.00, curdate(), 'ES1111222233334444555566');

/* 
transferencias
*/
insert into Transferencia
(fechaTrans, tipoTrans, cantidadTrans, conceptoTrans, codigoTransaccion, CodCuentas_trans)
values
(curdate(), 'Ingreso', 1500.00, 'Ingreso inicial', 'INPR1760000000001', 1),
(curdate(), 'Gasto', 100.00, 'Compra supermercado', 'GAPR1760000000002', 1),
(curdate(), 'Ingreso', 2500.00, 'Ingreso inicial', 'INPE1760000000003', 2),
(curdate(), 'Ingreso', 1000.00, 'Ingreso inicial', 'INMA1760000000004', 3);

/* 
objetivos 
*/
insert into Objetivos
(nombreObj, cantidadObj, descipObj, cantConseObj, CodUsuarios_obj)
values
('Coche', 12000.00, 'Comprar coche', 1500.00, 1),
('Viaje', 3000.00, 'Viaje de vacaciones', 500.00, 2),
('Portatil', 1000.00, 'Comprar portatil nuevo', 200.00, 3);

/*
detalles programados 
*/
insert into detalleObjetivoProgramados
(CodCuentasApp_detprog, CodObjetivos_detprog, fechaini_detprog, cantidadFija_detprog,
 fechainactividad_detprog, fechafin_detprog, periodicidad_detprog)
values
(1, 1, curdate(), 300.00, null, date_add(curdate(), interval 35 month), 'mensual'),
(2, 2, curdate(), 150.00, null, date_add(curdate(), interval 17 month), 'mensual'),
(3, 3, curdate(), 100.00, null, date_add(curdate(), interval 8 month), 'mensual');