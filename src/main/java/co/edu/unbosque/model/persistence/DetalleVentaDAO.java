package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.DetalleVenta;
import co.edu.unbosque.model.DetalleVentaDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 * Data Access Object (DAO) for managing DetalleVenta entities.
 * Provides CRUD operations and handles conversion between DetalleVenta and DetalleVentaDTO.
 * 
 * @author InnovaCore
 */
public class DetalleVentaDAO implements CRUDOperation<DetalleVentaDTO> {

    /** Factory for creating EntityManager instances */
    public EntityManagerFactory emf;

    /** EntityManager used for database operations */
    public EntityManager em;

    /**
     * Default constructor. Initializes the EntityManagerFactory and EntityManager.
     */
    public DetalleVentaDAO() {
        emf = PersistenceConfig.createEntityManagerFactory("papeleriaPU");
        em = emf.createEntityManager();
    }

    /**
     * Opens the EntityManager and EntityManagerFactory if they are closed.
     */
    public void open() {
        if (!emf.isOpen() || !em.isOpen()) {
            emf = PersistenceConfig.createEntityManagerFactory("papeleriaPU");
            em = emf.createEntityManager();
        }
    }

    /**
     * Persists a new DetalleVenta entity in the database.
     * 
     * @param data the DetalleVentaDTO to be created
     */
    @Override
    public void crear(DetalleVentaDTO data) {
        open();
        try {
            em.getTransaction().begin();
            em.persist(DataMapper.detalleVentaDTOAdetalleVenta(data));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
    }

    /**
     * Deletes a DetalleVenta entity by its ID.
     * 
     * @param id the ID of the entity to delete
     * @return true if deletion was successful, false otherwise
     */
    @Override
    public Boolean eliminar(int id) {
        open();
        try {
            em.getTransaction().begin();
            DetalleVenta entity = em.find(DetalleVenta.class, id);
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
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
    }

    /**
     * Updates an existing DetalleVenta entity with new data.
     * 
     * @param id the ID of the entity to update
     * @param newData the new data to apply
     * @return true if update was successful, false otherwise
     */
    @Override
    public Boolean actualizar(int id, DetalleVentaDTO newData) {
        open();
        try {
            em.getTransaction().begin();
            DetalleVenta entity = em.find(DetalleVenta.class, id);
            if (entity != null) {
                entity.setCantidad(newData.getCantidad());
                entity.setIdDetalle(newData.getIdDetalle());
                entity.setProducto(DataMapper.productoDTOAproducto(newData.getProducto()));
                entity.setSubtotal(newData.getSubtotal());
                em.merge(entity);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
    }

    /**
     * Retrieves all DetalleVenta entities from the database and converts them to DTOs.
     * 
     * @return a list of DetalleVentaDTO
     */
    @Override
    public ArrayList<DetalleVentaDTO> findAll() {
        open();
        try {
            @SuppressWarnings("unchecked")
            ArrayList<DetalleVenta> entityList = (ArrayList<DetalleVenta>) em
                    .createQuery("SELECT d FROM DetalleVenta d").getResultList();
            return (ArrayList<DetalleVentaDTO>) DataMapper.listaDetalleVentaAlistaDetalleVentaDTO(entityList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
        return new ArrayList<DetalleVentaDTO>();
    }

    /**
     * Finds a DetalleVenta entity by its ID and converts it to a DTO.
     * 
     * @param id the ID of the entity
     * @return the DetalleVentaDTO, or null if not found
     */
    @Override
    public DetalleVentaDTO findOne(int id) {
        open();
        try {
            return DataMapper.detalleVentaAdetalleVentaDTO(em.find(DetalleVenta.class, id));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
        return null;
    }

    /**
     * Deletes multiple DetalleVenta entities by their IDs.
     * Currently not implemented.
     * 
     * @param ids a list of IDs to delete
     */
    @Override
    public void eliminarTodos(List<Integer> ids) {
        // TODO: Implement batch deletion
    }
}
