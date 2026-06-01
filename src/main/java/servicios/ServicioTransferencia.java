package servicios;

import java.time.LocalDate;
import java.util.ArrayList;

import controladores.TransferenciaController;
import entidades.CuentaApp;
import entidades.TipoTransferencia;
import entidades.Transferencia;

public class ServicioTransferencia {

    private static final TransferenciaController transferenciaController = new TransferenciaController();

    public static void insertarTransferenciasEjemplo() {

        var lista = new ArrayList<Transferencia>();

        CuentaApp cuenta = new CuentaApp();
        cuenta.setCodCuentasApp(1);

        lista.add(new Transferencia(
                LocalDate.now(),
                TipoTransferencia.Ingreso,
                500.0,
                "Ingreso inicial",
                "TRX001",
                cuenta));

        lista.add(new Transferencia(
                LocalDate.now(),
                TipoTransferencia.Gasto,
                120.50,
                "Compra supermercado",
                "TRX002",
                cuenta));

        lista.add(new Transferencia(
                LocalDate.now(),
                TipoTransferencia.Gasto,
                50.0,
                "Gasolina",
                "TRX003",
                cuenta));

        for (Transferencia transferencia : lista) {
            transferenciaController.create(transferencia);
        }

        System.out.println("Tranferencias insertadas");
    }

    public static void crearTransferencia(Transferencia transferencia) {
        transferenciaController.create(transferencia);
    }

    public static Transferencia buscarTransferencia(Integer id) {
        return transferenciaController.findById(id);
    }

    public static void actualizarTransferencia(Transferencia transferencia) {
        transferenciaController.update(transferencia);
    }

    public static void borrarTransferencia(Integer id) {
        transferenciaController.delete(id);
    }

    public static void mostrarTodasTransferencias() {
        transferenciaController.findAll().forEach(System.out::println);
        System.out.println("-------------------------------------------------");
    }

    public static void borrarTodasTransferencias() {
        transferenciaController.deleteAll();
    }
}
