package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entidades.CuentaApp;

public class CuentaAppController {

    private final EntityManagerFactory emf;

    public CuentaAppController() {
        this.emf = Persistence.createEntityManagerFactory("ahorrappVico");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Crea una nueva cuenta.
     */
    public void create(CuentaApp cuentaApp) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(cuentaApp);
            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al crear la cuenta", ex);

        } finally {
            em.close();
        }
    }

    /**
     * Busca una cuenta por ID.
     */
    public CuentaApp findById(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(CuentaApp.class, id);

        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todas las cuentas.
     */
    public List<CuentaApp> findAll() {
        EntityManager em = getEntityManager();

        try {
            return em.createNamedQuery("CuentaApp.findAll", CuentaApp.class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    /**
     * Actualiza una cuenta.
     */
    public void update(CuentaApp cuentaApp) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(cuentaApp);
            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al actualizar la cuenta", ex);

        } finally {
            em.close();
        }
    }

    /**
     * Elimina una cuenta.
     */
    public void delete(Integer id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            CuentaApp cuentaApp = em.find(CuentaApp.class, id);

            if (cuentaApp != null) {
                em.remove(cuentaApp);
            }

            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar la cuenta", ex);

        } finally {
            em.close();
        }
    }

    /**
     * Elimina todas las cuentas.
     */
    public void deleteAll() {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.createNativeQuery("delete from CuentasApp").executeUpdate();

            em.createNativeQuery(
                    "alter table ahorrappVico.CuentasApp AUTO_INCREMENT = 1")
                    .executeUpdate();

            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar todas las cuentas", ex);

        } finally {
            em.close();
        }
    }

    public List<CuentaApp> findCuentasUsuarioHastaHoy(Integer idUsuario) {

        EntityManager em = getEntityManager();

        try {

            return em.createQuery(
                    "SELECT c FROM CuentaApp c "
                            + "WHERE c.codUsuariosCuentasapp.codUsuarios = :idUsuario "
                            + "AND c.fechaCuenta <= CURRENT_DATE "
                            + "ORDER BY c.fechaCuenta DESC",
                    CuentaApp.class)
                    .setParameter("idUsuario", idUsuario)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public void sincronizar() {
        getEntityManager().flush();
    }
}