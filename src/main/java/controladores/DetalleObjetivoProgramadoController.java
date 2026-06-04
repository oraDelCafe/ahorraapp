package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entidades.DetalleObjetivoProgramado;

public class DetalleObjetivoProgramadoController {

    private final EntityManagerFactory emf;

    public DetalleObjetivoProgramadoController() {
        this.emf = Persistence.createEntityManagerFactory("ahorrappVico");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Crea un nuevo detalle de objetivo programado.
     */
    public void create(DetalleObjetivoProgramado detalle) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(detalle);
            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al crear un detalle  programado", ex);

        } finally {
            em.close();
        }
    }

    /**
     * Busca un detalle por ID.
     */
    public DetalleObjetivoProgramado findById(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(DetalleObjetivoProgramado.class, id);

        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todos los detalles.
     */
    public List<DetalleObjetivoProgramado> findAll() {
        EntityManager em = getEntityManager();

        try {
            return em.createNamedQuery(
                    "DetalleObjetivoProgramado.findAll",
                    DetalleObjetivoProgramado.class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    /**
     * Actualiza un detalle.
     */
    public void update(DetalleObjetivoProgramado detalle) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(detalle);
            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al actualizar el detalle  programado", ex);

        } finally {
            em.close();
        }
    }

    public DetalleObjetivoProgramado findDetallePorObjetivo(Integer idObjetivo) {

        EntityManager em = getEntityManager();

        try {

            List<DetalleObjetivoProgramado> lista = em.createQuery(
                    "SELECT d FROM DetalleObjetivoProgramado d "
                            + "WHERE d.codObjetivosDetprog.codObjetivos = :idObjetivo",
                    DetalleObjetivoProgramado.class)
                    .setParameter("idObjetivo", idObjetivo)
                    .getResultList();

            if (lista.isEmpty()) {

                return null;

            } else {

                return lista.get(0);
            }

        } finally {

            em.close();
        }
    }

    /**
     * Elimina un detalle.
     */
    public void delete(Integer id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            DetalleObjetivoProgramado detalle = em.find(DetalleObjetivoProgramado.class, id);

            if (detalle != null) {
                em.remove(detalle);
            }

            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar el detalle  programado", ex);

        } finally {
            em.close();
        }
    }

    /**
     * Elimina todos los detalles.
     */
    public void deleteAll() {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.createNativeQuery("delete from detalleObjetivoProgramados")
                    .executeUpdate();

            em.createNativeQuery(
                    "alter table ahorrappVico.detalleObjetivoProgramados AUTO_INCREMENT = 1")
                    .executeUpdate();

            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(
                    "Error al eliminar todos los detalles  programados",
                    ex);

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