/*
Insercion de base de datos Vico
*/

use ahorrappVico;

/* usuarios */
insert into Usuarios
(nombreUsu, Ape1Usu, Ape2Usu, pass, emailUsu, dniUsu, dirUsu, tlfUsu)
values
('prueba', 'Lopez', 'Garcia', 'prueba123', 'prueba12@gmail.com', '12345678A', 'Calle Prueba 1', '600111222'),
('Pedro', 'Ortega', 'Nuñez', 'Pedro123', 'pedro12@gmail.com', '87654321B', 'Calle Luna 5', '600333444'),
('Maria', 'Ruiz', 'Martin', 'Maria123', 'maria12@gmail.com', '11223344C', 'Avenida Sol 10', '600555666');

/* cuentas app */
insert into CuentasApp
(CodUsuarios_cuentasapp, SaldoCuentas, FechaCuenta, CuentaBancoExterna)
values
(1, 1500.00, '2026-06-04', 'ES1234567890123456789012'),
(2, 2500.00, '2026-06-04', 'ES9876543210987654321098'),
(3, 1000.00, '2026-06-04', 'ES1111222233334444555566');

/* transferencias */
insert into Transferencia
(fechaTrans, tipoTrans, cantidadTrans, conceptoTrans, codigoTransaccion, CodCuentas_trans)
values
('2026-06-04', 'Ingreso', 1500.00, 'Ingreso inicial', 'INPR1760000000001', 1),
('2026-06-04', 'Gasto', 100.00, 'Compra supermercado', 'GAPR1760000000002', 1),
('2026-06-04', 'Ingreso', 2500.00, 'Ingreso inicial', 'INPE1760000000003', 2),
('2026-06-04', 'Gasto', 200.00, 'Pago factura', 'GAPE1760000004', 2),
('2026-06-04', 'Ingreso', 1000.00, 'Ingreso inicial', 'INMA1760000000005', 3);

/* objetivos */
insert into Objetivos
(nombreObj, cantidadObj, descipObj, cantConseObj, CodUsuarios_obj)
values
('Coche', 12000.00, 'Comprar coche', 1500.00, 1),
('Viaje', 3000.00, 'Viaje de vacaciones', 500.00, 2),
('Portatil', 1000.00, 'Comprar portatil nuevo', 200.00, 3);

/* detalles programados */
insert into detalleObjetivoProgramados
(CodCuentasApp_detprog, CodObjetivos_detprog, fechaini_detprog,
 cantidadFija_detprog, fechainactividad_detprog, fechafin_detprog,
 periodicidad_detprog)
values
(1, 1, '2026-06-04', 300.00, null, '2029-05-04', 'mensual'),
(2, 2, '2026-06-04', 150.00, null, '2028-11-04', 'mensual'),
(3, 3, '2026-06-04', 100.00, null, '2027-02-04', 'mensual');

/* detalles puntuales */
insert into detalleObjetivoPuntual
(CodObjetivos_detpunt, CodCuentasApp_detpunt, cantidad_detpunt, fecha_detpunt)
values
(1, 1, 200.00, '2026-06-04 10:30:00'),
(2, 2, 100.00, '2026-06-04 11:00:00'),
(3, 3, 50.00, '2026-06-04 11:30:00');