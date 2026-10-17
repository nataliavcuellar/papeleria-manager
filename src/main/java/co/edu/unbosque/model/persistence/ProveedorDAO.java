package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.Proveedor;
import co.edu.unbosque.model.ProveedorDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 * Data Access Object (DAO) for managing Proveedor entities.
 * Implements CRUD operations for ProveedorDTO objects.
 * Handles database interactions using JPA (Jakarta Persistence API).
 * 
 * @author InnovaCore
 */
public class ProveedorDAO implements CRUDOperation<ProveedorDTO> {

    /** EntityManagerFactory for JPA persistence */
    public EntityManagerFactory emf;

    /** EntityManager for interacting with the database */
    public EntityManager em;

    /**
     * Default constructor.
     * Initializes EntityManagerFactory and EntityManager.
     */
    public ProveedorDAO() {
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
     * Creates a new Proveedor entity in the database.
     * 
     * @param data ProveedorDTO object to be persisted
     */
    @Override
    public void crear(ProveedorDTO data) {
        open();
        try {
            em.getTransaction().begin();
            em.persist(DataMapper.proveedorDTOAproveedor(data));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }

    /**
     * Deletes a Proveedor entity by its ID.
     * 
     * @param id ID of the Proveedor to delete
     * @return true if deletion was successful, false otherwise
     */
    @Override
    public Boolean eliminar(int id) {
        open();
        try {
            em.getTransaction().begin();
            Proveedor entity = em.find(Proveedor.class, id);
            if (entity != null) {
                em.remove(entity);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }

    /**
     * Updates an existing Proveedor entity with new data.
     * 
     * @param id ID of the Proveedor to update
     * @param newData ProveedorDTO containing updated information
     * @return true if update was successful, false otherwise
     */
    @Override
    public Boolean actualizar(int id, ProveedorDTO newData) {
        open();
        try {
            em.getTransaction().begin();
            Proveedor entity = em.find(Proveedor.class, id);
            if (entity != null) {
                entity.setNombre(newData.getNombre());
                entity.setApellido(newData.getApellido());
                entity.setCorreo(newData.getCorreo());
                entity.setTelefono(newData.getTelefono());
                entity.setTiempo_entrega(newData.getTiempo_entrega());
                em.merge(entity);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }

    /**
     * Retrieves all Proveedor entities from the database.
     * 
     * @return list of ProveedorDTO objects
     */
    @Override
    public ArrayList<ProveedorDTO> findAll() {
        open();
        try {
            @SuppressWarnings("unchecked")
            ArrayList<Proveedor> entityList = (ArrayList<Proveedor>) em.createQuery("SELECT p FROM Proveedor p")
                    .getResultList();
            return DataMapper.listaProveedorAlistaProveedorDTO(entityList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
        return new ArrayList<ProveedorDTO>();
    }

    /**
     * Finds a Proveedor entity by its ID.
     * 
     * @param id ID of the Proveedor to find
     * @return ProveedorDTO object or null if not found
     */
    @Override
    public ProveedorDTO findOne(int id) {
        open();
        try {
            return DataMapper.proveedorAproveedorDTO(em.find(Proveedor.class, id));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
        return null;
    }

    /**
     * Deletes multiple Proveedor entities by their IDs.
     * Currently not implemented.
     * 
     * @param ids List of Proveedor IDs to delete
     */
    @Override
    public void eliminarTodos(List<Integer> ids) {
        // Not implemented
    }

    /**
     * Finds a Proveedor entity by its first and last name.
     * 
     * @param nombre First name of the Proveedor
     * @param apellido Last name of the Proveedor
     * @return ProveedorDTO object or null if not found
     */
    public ProveedorDTO findOneByNameAndLastName(String nombre, String apellido) {
        open();
        try {
            String nombreParam = nombre == null ? "" : nombre.trim().toLowerCase();
            String apellidoParam = apellido == null ? "" : apellido.trim().toLowerCase();

            String jpql = "SELECT p FROM Proveedor p WHERE LOWER(p.nombre) = :nombre AND LOWER(p.apellido) = :apellido";
            Proveedor proveedor = em.createQuery(jpql, Proveedor.class)
                    .setParameter("nombre", nombreParam)
                    .setParameter("apellido", apellidoParam)
                    .setMaxResults(1)
                    .getSingleResult();

            return DataMapper.proveedorAproveedorDTO(proveedor);
        } catch (Exception e) {
            return null;
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}
