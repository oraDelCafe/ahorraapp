package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entidades.Transferencia;

public class TransferenciaController {

    private final EntityManagerFactory emf;

    public TransferenciaController() {
        this.emf = Persistence.createEntityManagerFactory("ahorrappVico");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Crea una nueva transferencia.
     */
    public void create(Transferencia transferencia) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(transferencia);
            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al crear la transferencia", ex);

        } finally {
            em.close();
        }
    }

    /**
     * Busca una transferencia por ID.
     */
    public Transferencia findById(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Transferencia.class, id);

        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todas las transferencias.
     */
    public List<Transferencia> findAll() {
        EntityManager em = getEntityManager();

        try {
            return em.createNamedQuery("Transferencia.findAll", Transferencia.class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    public List<Transferencia> findTransferenciasCuenta(Integer idCuenta) {

        EntityManager em = getEntityManager();

        try {

            return em.createQuery(
                    "SELECT t FROM Transferencia t "
                            + "WHERE t.codCuentasTrans.codCuentasApp = :idCuenta "
                            + "ORDER BY t.fechaTrans DESC",
                    Transferencia.class)
                    .setParameter("idCuenta", idCuenta)
                    .getResultList();

        } finally {

            em.close();
        }
    }

    /**
     * Actualiza una transferencia.
     */
    public void update(Transferencia transferencia) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(transferencia);
            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al actualizar la transferencia", ex);

        } finally {
            em.close();
        }
    }

    /**
     * Elimina una transferencia.
     */
    public void delete(Integer id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Transferencia transferencia = em.find(Transferencia.class, id);

            if (transferencia != null) {
                em.remove(transferencia);
            }

            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar la transferencia", ex);

        } finally {
            em.close();
        }
    }

    /**
     * Elimina todas las transferencias.
     */
    public void deleteAll() {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.createNativeQuery("delete from Transferencia").executeUpdate();

            em.createNativeQuery(
                    "alter table ahorrappVico.Transferencia AUTO_INCREMENT = 1")
                    .executeUpdate();

            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar todas las transferencias", ex);

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
