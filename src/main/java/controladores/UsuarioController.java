package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entidades.Usuario;

public class UsuarioController {

    private final EntityManagerFactory emf;

    public UsuarioController() {
        this.emf = Persistence.createEntityManagerFactory("ahorrappVico");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Crea un nuevo usuario.
     */
    public void create(Usuario usuario) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(usuario);
            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al crear el usuario", ex);

        } finally {
            em.close();
        }
    }

    /**
     * Busca un usuario por ID.
     */
    public Usuario findById(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Usuario.class, id);

        } finally {
            em.close();
        }
    }

    /**
     * Obtiene todos los usuarios.
     */
    public List<Usuario> findAll() {
        EntityManager em = getEntityManager();

        try {
            return em.createNamedQuery("Usuario.findAll", Usuario.class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    /**
     * Actualiza un usuario.
     */
    public void update(Usuario usuario) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(usuario);
            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al actualizar el usuario", ex);

        } finally {
            em.close();
        }
    }

    /**
     * Elimina un usuario por ID.
     */
    public void delete(Integer id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Usuario usuario = em.find(Usuario.class, id);

            if (usuario != null) {
                em.remove(usuario);
            }

            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar el usuario", ex);

        } finally {
            em.close();
        }
    }

    /**
     * Elimina todos los usuarios.
     */
    public void deleteAll() {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.createNativeQuery("delete from Usuarios")
                    .executeUpdate();

            em.createNativeQuery(
                    "alter table ahorrappVico.Usuarios AUTO_INCREMENT = 1")
                    .executeUpdate();

            tx.commit();

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(
                    "Error al eliminar todos los usuarios", ex);

        } finally {
            em.close();
        }
    }

    /**
     * Cierra el EntityManagerFactory.
     */
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public void sincronizar() {
        getEntityManager().flush();
    }
}
