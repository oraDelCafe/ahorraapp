package servicios;

import java.time.LocalDateTime;

import controladores.CuentaAppController;
import controladores.DetalleObjetivoPuntualController;
import controladores.ObjetivoController;
import entidades.CuentaApp;
import entidades.DetalleObjetivoPuntual;
import entidades.Objetivo;

public class ServicioDetalleObjetivoPuntual {

    private static final DetalleObjetivoPuntualController detalleController = new DetalleObjetivoPuntualController();
    private static final CuentaAppController cuentaController = new CuentaAppController();
    private static final ObjetivoController objetivoController = new ObjetivoController();

    public static void insertarDetalleObjetivoPuntualEjemplo() {

        CuentaApp cuenta = cuentaController.findById(1);
        Objetivo objetivo = objetivoController.findById(1);

        DetalleObjetivoPuntual detalle = new DetalleObjetivoPuntual(
                500.0,
                LocalDateTime.now(),
                cuenta,
                objetivo);

        cuenta.addDetalleObjetivoPuntual(detalle);
        objetivo.addDetalleObjetivoPuntual(detalle);

        detalleController.create(detalle);

        System.out.println("Insertando detalle puntual");

    }

    public static void crearDetalleObjetivoPuntual(
            DetalleObjetivoPuntual detalle) {

        detalleController.create(detalle);
    }

    public static DetalleObjetivoPuntual buscarDetalleObjetivoPuntual(
            Integer id) {

        return detalleController.findById(id);
    }

    public static void actualizarDetalleObjetivoPuntual(
            DetalleObjetivoPuntual detalle) {

        detalleController.update(detalle);
    }

    public static void borrarDetalleObjetivoPuntual(Integer id) {
        detalleController.delete(id);
    }

    public static void mostrarTodosDetallesObjetivoPuntual() {
        detalleController.findAll().forEach(System.out::println);
        System.out.println("-------------------------------------------------");
    }

    public static void borrarTodosDetallesObjetivoPuntual() {
        detalleController.deleteAll();
    }
}
