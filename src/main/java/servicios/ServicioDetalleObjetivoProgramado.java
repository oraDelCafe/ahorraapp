package servicios;

import java.time.LocalDate;
import java.util.ArrayList;

import controladores.CuentaAppController;
import controladores.DetalleObjetivoProgramadoController;
import controladores.ObjetivoController;
import entidades.CuentaApp;
import entidades.DetalleObjetivoProgramado;
import entidades.Objetivo;
import entidades.TipoPeriodicidad;

public class ServicioDetalleObjetivoProgramado {

    private static final DetalleObjetivoProgramadoController detalleController = new DetalleObjetivoProgramadoController();
    private static final CuentaAppController cuentaController = new CuentaAppController();
    private static final ObjetivoController objetivoController = new ObjetivoController();

    public static void insertarDetallesObjetivoProgramadoEjemplo() {

        var lista = new ArrayList<DetalleObjetivoProgramado>();

        CuentaApp cuenta = cuentaController.findById(1);
        Objetivo objetivo = objetivoController.findById(1);

        lista.add(new DetalleObjetivoProgramado(
                LocalDate.now(),
                50.0,
                LocalDate.now().plusMonths(6),
                TipoPeriodicidad.mensual,
                cuenta,
                objetivo));

        for (DetalleObjetivoProgramado detalle : lista) {
            detalleController.create(detalle);
        }

        System.out.println("Detalles programados insertados");
    }

    public static void crearDetalleObjetivoProgramado(DetalleObjetivoProgramado detalle) {
        detalleController.create(detalle);
    }

    public static DetalleObjetivoProgramado buscarDetalleObjetivoProgramado(Integer id) {
        return detalleController.findById(id);
    }

    public static DetalleObjetivoProgramado buscarDetallePorObjetivo(Integer idObjetivo) {
        return detalleController.findDetallePorObjetivo(idObjetivo);
    }

    public static void actualizarDetalleObjetivoProgramado(DetalleObjetivoProgramado detalle) {
        detalleController.update(detalle);
    }

    public static void borrarDetalleObjetivoProgramado(Integer id) {
        detalleController.delete(id);
    }

    public static void mostrarTodosDetallesObjetivoProgramado() {
        detalleController.findAll().forEach(System.out::println);
        System.out.println("-------------------------------------------------");
    }

    public static void borrarTodosDetallesObjetivoProgramado() {
        detalleController.deleteAll();
    }
}