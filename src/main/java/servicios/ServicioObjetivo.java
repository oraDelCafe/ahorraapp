package servicios;

import java.util.ArrayList;

import controladores.ObjetivoController;
import entidades.Objetivo;
import entidades.Usuario;

public class ServicioObjetivo {

    private static final ObjetivoController objetivoController = new ObjetivoController();

    public static void insertarObjetivosEjemplo() {

        var lista = new ArrayList<Objetivo>();

        Usuario usuario = new Usuario();
        usuario.setCodUsuarios(1);

        lista.add(new Objetivo(
                "Comprar coche",
                12000.0,
                "Ahorrar para comprar un coche",
                1500.0,
                usuario));

        lista.add(new Objetivo(
                "Viaje verano",
                2000.0,
                "Ahorrar para las vacaciones",
                300.0,
                usuario));

        for (Objetivo objetivo : lista) {
            objetivoController.create(objetivo);
        }

        System.out.println("Objetivos insertados");
    }

    public static void crearObjetivo(Objetivo objetivo) {
        objetivoController.create(objetivo);
    }

    public static Objetivo buscarObjetivo(Integer id) {
        return objetivoController.findById(id);
    }

    public static void actualizarObjetivo(Objetivo objetivo) {
        objetivoController.update(objetivo);
    }

    public static void borrarObjetivo(Integer id) {
        objetivoController.delete(id);
    }

    public static void mostrarTodosObjetivos() {
        objetivoController.findAll().forEach(System.out::println);
        System.out.println("-------------------------------------------------");
    }

    public static void borrarTodosObjetivos() {
        objetivoController.deleteAll();
    }
}