package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.CuentaPorPagar;
import co.edu.unbosque.model.CuentaPorPagarDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 * DAO class for managing accounts payable (CuentaPorPagar) in the system.
 * <p>
 * Provides CRUD operations and handles the communication between the database
 * and the business layer using JPA EntityManager.
 * </p>
 * 
 * @author InnovaCore
 */
public class CuentasPorPagarDAO implements CRUDOperation<CuentaPorPagarDTO> {

    /**
     * EntityManagerFactory for managing the persistence unit.
     */
    public EntityManagerFactory emf;

    /**
     * EntityManager used for database operations.
     */
    public EntityManager em;

    /**
     * Default constructor.
     * <p>
     * Initializes the EntityManagerFactory and EntityManager.
     * </p>
     */
    public CuentasPorPagarDAO() {
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
     * Persists a new CuentaPorPagar entity in the database.
     * 
     * @param data the DTO containing the data to persist
     */
    @Override
    public void crear(CuentaPorPagarDTO data) {
        open();
        try {
            em.getTransaction().begin();
            em.merge(DataMapper.cuentaPorPagarDTOACuentaPorPagar(data));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
    }

    /**
     * Deletes a CuentaPorPagar entity by its ID.
     * 
     * @param id the ID of the entity to delete
     * @return true if deletion was successful, false otherwise
     */
    @Override
    public Boolean eliminar(int id) {
        open();
        try {
            em.getTransaction().begin();
            CuentaPorPagar entity = em.find(CuentaPorPagar.class, id);
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
     * Updates an existing CuentaPorPagar entity with new data.
     * 
     * @param id      the ID of the entity to update
     * @param newData the DTO containing the new data
     * @return true if update was successful, false otherwise
     */
    @Override
    public Boolean actualizar(int id, CuentaPorPagarDTO newData) {
        open();
        try {
            em.getTransaction().begin();
            CuentaPorPagar entity = em.find(CuentaPorPagar.class, id);
            if (entity != null) {
                entity.setEstado(newData.getEstado());
                entity.setFechaCompra(newData.getFechaCompra());
                entity.setFechaVencimiento(newData.getFechaVencimiento());
                entity.setListaCuentaProducto(
                        DataMapper.ListaCuentaProductoDTOAlistaCuentaProducto(newData.getListaCuentaProducto()));
                entity.setIdCuenta(newData.getIdCuenta());
                entity.setMonto(newData.getMonto());
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
     * Retrieves all CuentaPorPagar entities from the database.
     * 
     * @return a list of CuentaPorPagarDTO
     */
    @Override
    public ArrayList<CuentaPorPagarDTO> findAll() {
        open();
        try {
            @SuppressWarnings("unchecked")
            ArrayList<CuentaPorPagar> entityList = (ArrayList<CuentaPorPagar>) em
                    .createQuery("SELECT c FROM CuentaPorPagar c").getResultList();

            return (ArrayList<CuentaPorPagarDTO>) DataMapper
                    .listaCuentaPorPagarAlistaCuentaPorPagarDTO(entityList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
        return new ArrayList<>();
    }

    /**
     * Finds a CuentaPorPagar entity by its ID.
     * 
     * @param id the ID of the entity
     * @return the corresponding CuentaPorPagarDTO or null if not found
     */
    @Override
    public CuentaPorPagarDTO findOne(int id) {
        open();
        try {
            return DataMapper.cuentaPorPagarACuentaPorPagarDTO(em.find(CuentaPorPagar.class, id));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
        return null;
    }

    /**
     * Deletes multiple CuentaPorPagar entities given a list of IDs.
     * 
     * @param ids the list of entity IDs to delete
     */
    @Override
    public void eliminarTodos(List<Integer> ids) {
        // TODO: implement batch deletion if needed
    }
}
