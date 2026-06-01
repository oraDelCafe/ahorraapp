package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entidades.DetalleObjetivoPuntual;

public class DetalleObjetivoPuntualController {

    private final EntityManagerFactory emf;

    public DetalleObjetivoPuntualController() {
        this.emf = Persistence.createEntityManagerFactory("ahorrappVico");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleObjetivoPuntual detalle) {
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
            throw new RuntimeException("Error al crear un detalle  puntual", ex);

        } finally {
            em.close();
        }
    }

    public DetalleObjetivoPuntual findById(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(DetalleObjetivoPuntual.class, id);

        } finally {
            em.close();
        }
    }

    public List<DetalleObjetivoPuntual> findAll() {
        EntityManager em = getEntityManager();

        try {
            return em.createNamedQuery(
                    "DetalleObjetivoPuntual.findAll",
                    DetalleObjetivoPuntual.class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    public void update(DetalleObjetivoPuntual detalle) {
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
            throw new RuntimeException("Error al actualizar  detalles  puntuales", ex);

        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            DetalleObjetivoPuntual detalle = em.find(DetalleObjetivoPuntual.class, id);

            if (detalle != null) {
                em.remove(detalle);
            }

            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar  detalles  puntuales", ex);

        } finally {
            em.close();
        }
    }

    public void deleteAll() {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.createNativeQuery("delete from detalleObjetivoPuntual")
                    .executeUpdate();

            em.createNativeQuery(
                    "alter table ahorrappVico.detalleObjetivoPuntual AUTO_INCREMENT = 1")
                    .executeUpdate();

            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(
                    "Error al eliminar todos los detalles  puntuales", ex);

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
