package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entidades.Objetivo;

public class ObjetivoController {

    private final EntityManagerFactory emf;

    public ObjetivoController() {
        this.emf = Persistence.createEntityManagerFactory("ahorrappVico");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Crea un nuevo objetivo.
     */
    public void create(Objetivo objetivo) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(objetivo);
            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al crear el objetivo", ex);

        } finally {
            em.close();
        }
    }

    /**
     * Busca un objetivo por ID.
     */
    public Objetivo findById(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Objetivo.class, id);

        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todos los objetivos.
     */
    public List<Objetivo> findAll() {
        EntityManager em = getEntityManager();

        try {
            return em.createNamedQuery("Objetivo.findAll", Objetivo.class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    /**
     * Actualiza un objetivo.
     */
    public void update(Objetivo objetivo) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(objetivo);
            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al actualizar el objetivo", ex);

        } finally {
            em.close();
        }
    }

    /**
     * Elimina un objetivo.
     */
    public void delete(Integer id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Objetivo objetivo = em.find(Objetivo.class, id);

            if (objetivo != null) {
                em.remove(objetivo);
            }

            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar el objetivo", ex);

        } finally {
            em.close();
        }
    }

    /**
     * Elimina todos los objetivos.
     */
    public void deleteAll() {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.createNativeQuery("delete from Objetivos").executeUpdate();

            em.createNativeQuery(
                    "alter table ahorrappVico.Objetivos AUTO_INCREMENT = 1")
                    .executeUpdate();

            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar todos los objetivos", ex);

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
