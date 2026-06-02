package servicios;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import controladores.CuentaAppController;
import entidades.CuentaApp;
import entidades.Usuario;

public class ServicioCuentaApp {

    private static final CuentaAppController cuentaAppController = new CuentaAppController();

    public static void insertarCuentasAppEjemplo() {

        var lista = new ArrayList<CuentaApp>();

        Usuario usuario = new Usuario();
        usuario.setCodUsuarios(1);

        Usuario usuario2 = new Usuario();
        usuario2.setCodUsuarios(2);
        
        lista.add(new CuentaApp(
                usuario,
                1500.0,
                LocalDate.now(),
                "ES1234567890123456789012"));

        lista.add(new CuentaApp(
                usuario2,
                2500.0,
                LocalDate.now(),
                "ES9876543210987654321098"));

        for (CuentaApp cuenta : lista) {
            cuentaAppController.create(cuenta);
        }

        System.out.println("Cuentas insetadas");
    }

    public static void crearCuentaApp(CuentaApp cuentaApp) {
        cuentaAppController.create(cuentaApp);
    }

    public static CuentaApp buscarCuentaApp(Integer id) {
        return cuentaAppController.findById(id);
    }

    public static List<CuentaApp> buscarCuentasUsuarioHastaHoy(Integer idUsuario) {
    return cuentaAppController.findCuentasUsuarioHastaHoy(idUsuario);
}

    public static void actualizarCuentaApp(CuentaApp cuentaApp) {
        cuentaAppController.update(cuentaApp);
    }

    public static void borrarCuentaApp(Integer id) {
        cuentaAppController.delete(id);
    }

    public static void mostrarTodasCuentasApp() {
        cuentaAppController.findAll().forEach(System.out::println);
        System.out.println("-------------------------------------------------");
    }

    public static void borrarTodasCuentasApp() {
        cuentaAppController.deleteAll();
    }
}
