package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.model.UsuarioDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 * Data Access Object (DAO) for managing Usuario entities.
 * Implements CRUD operations for UsuarioDTO objects.
 * Handles database interactions using JPA (Jakarta Persistence API).
 * 
 * Note: Some operations like eliminar and actualizar are not implemented yet.
 * 
 * @author InnovaCore
 */
public class UsuarioDAO implements CRUDOperation<UsuarioDTO> {

    /** EntityManagerFactory for JPA persistence */
    public EntityManagerFactory emf;

    /** EntityManager for interacting with the database */
    public EntityManager em;

    /**
     * Default constructor.
     * Initializes EntityManagerFactory and EntityManager.
     */
    public UsuarioDAO() {
        emf = PersistenceConfig.createEntityManagerFactory("papeleriaPU");
        em = emf.createEntityManager();
    }

    /**
     * Ensures the EntityManagerFactory and EntityManager are open.
     * Reopens them if they are closed.
     */
    public void open() {
        if (!emf.isOpen() || !em.isOpen()) {
            emf = PersistenceConfig.createEntityManagerFactory("papeleriaPU");
            em = emf.createEntityManager();
        }
    }

    /**
     * Creates a new Usuario entity in the database.
     * 
     * @param data UsuarioDTO object to be persisted
     */
    @Override
    public void crear(UsuarioDTO data) {
        open();
        try {
            em.getTransaction().begin();
            em.persist(DataMapper.usuarioDTOAusuario(data));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
    }

    /**
     * Deletes a Usuario entity by its ID.
     * Currently not implemented.
     * 
     * @param id ID of the Usuario to delete
     * @return null
     */
    @Override
    public Boolean eliminar(int id) {
        return null;
    }

    /**
     * Updates an existing Usuario entity with new data.
     * Currently not implemented.
     * 
     * @param id ID of the Usuario to update
     * @param newData UsuarioDTO containing updated information
     * @return null
     */
    @Override
    public Boolean actualizar(int id, UsuarioDTO newData) {
        return null;
    }

    /**
     * Retrieves all Usuario entities from the database.
     * 
     * @return list of UsuarioDTO objects
     */
    @Override
    public ArrayList<UsuarioDTO> findAll() {
        open();
        try {
            @SuppressWarnings("unchecked")
            ArrayList<Usuario> entityList = (ArrayList<Usuario>) em.createQuery("SELECT u FROM Usuario u")
                    .getResultList();
            return DataMapper.listaUsuarioAlistaUsuarioDTO(entityList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
        return new ArrayList<UsuarioDTO>();
    }

    /**
     * Finds a Usuario entity by its ID.
     * 
     * @param id ID of the Usuario to find
     * @return UsuarioDTO object or null if not found
     */
    @Override
    public UsuarioDTO findOne(int id) {
        open();
        try {
            return DataMapper.usuarioAusuarioDTO(em.find(Usuario.class, id));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
        return null;
    }

    /**
     * Deletes multiple Usuario entities by their IDs.
     * Currently not implemented.
     * 
     * @param ids List of Usuario IDs to delete
     */
    @Override
    public void eliminarTodos(List<Integer> ids) {
        // Not implemented
    }

    /**
     * Finds a Usuario entity by its username.
     * 
     * @param nombreUsuario Username of the Usuario
     * @return UsuarioDTO object or null if not found
     */
    public UsuarioDTO findOneByUsuario(String nombreUsuario) {
        open();
        try {
            Usuario u = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :u", Usuario.class)
                    .setParameter("u", nombreUsuario)
                    .getSingleResult();
            return DataMapper.usuarioAusuarioDTO(u);
        } catch (Exception e) {
            return null;
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}
