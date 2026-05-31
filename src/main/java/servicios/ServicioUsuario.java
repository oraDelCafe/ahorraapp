package servicios;


import controladores.UsuarioController;
import entidades.Usuario;
import java.util.ArrayList;


public class ServicioUsuario {

    private static final UsuarioController usuarioController = new UsuarioController();

    public static void insertarUsuariosEjemplo() {

        var lista = new ArrayList<Usuario>();

        lista.add(new Usuario(
                "Juan",
                "Pérez",
                "García",
                "juan@gmail.com",
                "12345678A",
                "Calle Mayor 1",
                "600111222"));

        lista.add(new Usuario(
                "María",
                "López",
                "Martín",
                "maria@gmail.com",
                "87654321B",
                "Avenida Sol 10",
                "600333444"));

        lista.add(new Usuario(
                "Pedro",
                "Ruiz",
                "Sánchez",
                "pedro@gmail.com",
                "11223344C",
                "Calle Luna 5",
                "600555666"));

        for (Usuario usuario : lista) {
            usuarioController.create(usuario);
        }

        System.out.println("--- > Usuarios de ejemplo insertados");
    }

    public static void crearUsuario(Usuario usuario) {
        usuarioController.create(usuario);
    }

    public static Usuario buscarUsuario(Integer id) {
        return usuarioController.findById(id);
    }

    public static void actualizarUsuario(Usuario usuario) {
        usuarioController.update(usuario);
    }

    public static void borrarUsuario(Integer id) {
        usuarioController.delete(id);
    }

    public static void mostrarTodosUsuarios() {
        usuarioController.findAll().forEach(System.out::println);
        System.out.println("-------------------------------------------------");
    }

    public static void borrarTodosUsuarios() {
        usuarioController.deleteAll();
    }

}
