package servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entidades.CuentaApp;
import entidades.DetalleObjetivoProgramado;
import entidades.DetalleObjetivoPuntual;
import entidades.Objetivo;
import entidades.Transferencia;
import entidades.Usuario;

public class ServicioCopiaSeguridad {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ahorrappVico");

    private static final String RUTA = "copiaSeguridad"
            + File.separator
            + "Hacer Copia Seguridad";
    private static final File CARPETA_COPIAS = new File("copiaSeguridad"
            + File.separator
            + "Hacer Copia Seguridad");

    private static final File CARPETA_CARGA = new File("copiaSeguridad"
            + File.separator
            + "Cargar Copia Seguridad");

    /*
     * hacer copia de seguridad
     */
    public static void hacerCopiaSeguridad() {

        EntityManager em = emf.createEntityManager();

        try {

            if (!CARPETA_COPIAS.exists()) {
                CARPETA_COPIAS.mkdirs();
            }

            String fecha = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            File carpeta = new File(CARPETA_COPIAS, fecha);
            carpeta.mkdir();

            guardarUsuarios(em, carpeta);
            guardarCuentas(em, carpeta);
            guardarTransferencias(em, carpeta);
            guardarObjetivos(em, carpeta);
            guardarDetallesProgramados(em, carpeta);
            guardarDetallesPuntuales(em, carpeta);

        } catch (Exception e) {

            throw new RuntimeException("Error al crear copia de seguridad", e);

        } finally {

            em.close();
        }
    }

    private static void guardarUsuarios(EntityManager em, File carpeta) throws Exception {

        List<Usuario> lista = em.createQuery("SELECT u FROM Usuario u", Usuario.class)
                .getResultList();

        PrintWriter pw = new PrintWriter(new File(carpeta, "Usuarios.csv"));

        for (Usuario u : lista) {

            pw.println(
                    u.getCodUsuarios() + ";"
                            + u.getNombreUsu() + ";"
                            + u.getApe1Usu() + ";"
                            + u.getApe2Usu() + ";"
                            + u.getPass() + ";"
                            + u.getEmailUsu() + ";"
                            + u.getDniUsu() + ";"
                            + u.getDirUsu() + ";"
                            + u.getTlfUsu());
        }

        pw.close();
    }

    private static void guardarCuentas(EntityManager em, File carpeta) throws Exception {

        List<CuentaApp> lista = em.createQuery("SELECT c FROM CuentaApp c", CuentaApp.class)
                .getResultList();

        PrintWriter pw = new PrintWriter(new File(carpeta, "CuentasApp.csv"));

        for (CuentaApp c : lista) {

            pw.println(
                    c.getCodCuentasApp() + ";"
                            + c.getCodUsuariosCuentasapp().getCodUsuarios() + ";"
                            + c.getSaldoCuentas() + ";"
                            + c.getFechaCuenta() + ";"
                            + c.getCuentaBancoExterna());
        }

        pw.close();
    }

    private static void guardarTransferencias(EntityManager em, File carpeta) throws Exception {

        List<Transferencia> lista = em.createQuery("SELECT t FROM Transferencia t", Transferencia.class)
                .getResultList();

        PrintWriter pw = new PrintWriter(new File(carpeta, "Transferencia.csv"));

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        for (Transferencia t : lista) {

            pw.println(
                    t.getCodTransferencia() + ";"
                            + formato.format(t.getFechaTrans()) + ";"
                            + t.getTipoTrans() + ";"
                            + t.getCantidadTrans() + ";"
                            + t.getConceptoTrans() + ";"
                            + t.getCodigoTransaccion() + ";"
                            + t.getCodCuentasTrans().getCodCuentasApp());
        }

        pw.close();
    }

    private static void guardarObjetivos(EntityManager em, File carpeta) throws Exception {

        List<Objetivo> lista = em.createQuery("SELECT o FROM Objetivo o", Objetivo.class)
                .getResultList();

        PrintWriter pw = new PrintWriter(new File(carpeta, "Objetivos.csv"));

        for (Objetivo o : lista) {

            pw.println(
                    o.getCodObjetivos() + ";"
                            + o.getNombreObj() + ";"
                            + o.getCantidadObj() + ";"
                            + o.getDescipObj() + ";"
                            + o.getCantConseObj() + ";"
                            + o.getCodUsuariosObj().getCodUsuarios());
        }

        pw.close();
    }

    private static void guardarDetallesProgramados(EntityManager em, File carpeta) throws Exception {

        List<DetalleObjetivoProgramado> lista = em.createQuery("SELECT d FROM DetalleObjetivoProgramado d",
                DetalleObjetivoProgramado.class)
                .getResultList();

        PrintWriter pw = new PrintWriter(new File(carpeta, "detalleObjetivoProgramados.csv"));

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        for (DetalleObjetivoProgramado d : lista) {

            pw.println(
                    d.getCodDetalleObjetivosDetprog() + ";"
                            + d.getCodCuentasAppDetprog().getCodCuentasApp() + ";"
                            + d.getCodObjetivosDetprog().getCodObjetivos() + ";"
                            + formato.format(d.getFechaIniDetprog()) + ";"
                            + d.getCantidadFijaDetprog() + ";"
                            + (d.getFechaInactividadDetprog() == null
                                    ? ""
                                    : formato.format(d.getFechaInactividadDetprog()))
                            + ";"
                            + formato.format(d.getFechaFinDetprog()) + ";"
                            + d.getPeriodicidadDetprog());
        }
        pw.close();
    }

    private static void guardarDetallesPuntuales(EntityManager em, File carpeta) throws Exception {

        List<DetalleObjetivoPuntual> lista = em.createQuery("SELECT d FROM DetalleObjetivoPuntual d",
                DetalleObjetivoPuntual.class)
                .getResultList();

        PrintWriter pw = new PrintWriter(new File(carpeta, "detalleObjetivoPuntual.csv"));

        for (DetalleObjetivoPuntual d : lista) {

            pw.println(
                    d.getCodDetalleObjetivoPuntual() + ";"
                            + d.getCodObjetivosDetpunt().getCodObjetivos() + ";"
                            + d.getCodCuentasAppDetpunt().getCodCuentasApp() + ";"
                            + d.getCantidadDetpunt() + ";"
                            + d.getFechaDetpunt());
        }

        pw.close();
    }

    /* Restaurar */

    private static void restaurarUsuarios(File carpeta) throws Exception {
        restaurarArchivo(carpeta, "Usuarios.csv", "Usuarios");
    }

    private static void restaurarCuentas(File carpeta) throws Exception {
        restaurarArchivo(carpeta, "CuentasApp.csv", "CuentasApp");
    }

    private static void restaurarObjetivos(File carpeta) throws Exception {
        restaurarArchivo(carpeta, "Objetivos.csv", "Objetivos");
    }

    private static void restaurarTransferencias(File carpeta) throws Exception {
        restaurarArchivo(carpeta, "Transferencia.csv", "Transferencia");
    }

    private static void restaurarDetallesProgramados(File carpeta) throws Exception {
        restaurarArchivo(carpeta, "detalleObjetivoProgramados.csv", "detalleObjetivoProgramados");
    }

    private static void restaurarDetallesPuntuales(File carpeta) throws Exception {
        restaurarArchivo(carpeta, "detalleObjetivoPuntual.csv", "detalleObjetivoPuntual");
    }

    private static void restaurarArchivo(File carpeta, String nombreArchivo, String tabla)
            throws Exception {

        File archivo = new File(carpeta, nombreArchivo);

        if (!archivo.exists()) {
            return;
        }

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

            tx.begin();

            BufferedReader br = new BufferedReader(new FileReader(archivo));

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";", -1);

                String sql = crearInsert(tabla, datos);

                em.createNativeQuery(sql).executeUpdate();
            }

            br.close();

            tx.commit();

        } catch (Exception e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            throw e;

        } finally {

            em.close();
        }
    }

    private static String crearInsert(String tabla, String[] datos) {

        String sql = "insert into " + tabla + " values (";

        for (int i = 0; i < datos.length; i++) {

            sql += valor(datos[i]);

            if (i < datos.length - 1) {
                sql += ",";
            }
        }

        sql += ")";

        return sql;
    }

    private static String valor(String dato) {

        if (dato == null || dato.equals("null") || dato.isEmpty()) {

            return "null";

        } else {

            return "'" + dato.replace("'", "''") + "'";
        }
    }

    public static void restaurarUltimaCopia() {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

            File carpetaPrincipal = CARPETA_CARGA;

            File[] copias = carpetaPrincipal.listFiles(File::isDirectory);

            if (copias == null || copias.length == 0) {
                throw new IllegalArgumentException("No hay copias de seguridad");
            }

            File ultima = copias[0];

            for (File copia : copias) {

                if (copia.getName().compareTo(ultima.getName()) > 0) {
                    ultima = copia;
                }
            }

            tx.begin();

            em.createQuery("DELETE FROM DetalleObjetivoPuntual").executeUpdate();
            em.createQuery("DELETE FROM DetalleObjetivoProgramado").executeUpdate();
            em.createQuery("DELETE FROM Transferencia").executeUpdate();
            em.createQuery("DELETE FROM Objetivo").executeUpdate();
            em.createQuery("DELETE FROM CuentaApp").executeUpdate();
            em.createQuery("DELETE FROM Usuario").executeUpdate();

            tx.commit();

            restaurarUsuarios(ultima);
            restaurarCuentas(ultima);
            restaurarObjetivos(ultima);
            restaurarTransferencias(ultima);
            restaurarDetallesProgramados(ultima);
            restaurarDetallesPuntuales(ultima);

        } catch (Exception e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            throw new RuntimeException("Error al restaurar copia de seguridad", e);

        } finally {

            em.close();
        }
    }

}
