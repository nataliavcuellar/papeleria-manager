package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.CuentaPorCobrar;
import co.edu.unbosque.model.CuentaPorCobrarDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 * DAO class for managing accounts receivable (CuentaPorCobrar) in the system.
 * <p>
 * Provides CRUD operations and handles the communication between the database
 * and the business layer using JPA EntityManager.
 * </p>
 * 
 * @author InnovaCore
 */
public class CuentaPorCobrarDAO implements CRUDOperation<CuentaPorCobrarDTO> {

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
    public CuentaPorCobrarDAO() {
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
     * Persists a new CuentaPorCobrar entity in the database.
     * 
     * @param data the DTO containing the data to persist
     */
    @Override
    public void crear(CuentaPorCobrarDTO data) {
        open();
        try {
            em.getTransaction().begin();
            em.persist(DataMapper.cuentaPorCobrarDTOACuentaPorCobrar(data));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
    }

    /**
     * Deletes a CuentaPorCobrar entity by its ID.
     * 
     * @param id the ID of the entity to delete
     * @return true if deletion was successful, false otherwise
     */
    @Override
    public Boolean eliminar(int id) {
        open();
        try {
            em.getTransaction().begin();
            CuentaPorCobrar entity = em.find(CuentaPorCobrar.class, id);
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
     * Updates an existing CuentaPorCobrar entity with new data.
     * 
     * @param id      the ID of the entity to update
     * @param newData the DTO containing the new data
     * @return true if update was successful, false otherwise
     */
    @Override
    public Boolean actualizar(int id, CuentaPorCobrarDTO newData) {
        open();
        try {
            em.getTransaction().begin();
            CuentaPorCobrar entity = em.find(CuentaPorCobrar.class, id);
            if (entity != null) {
                entity.setCliente(DataMapper.clienteDTOAcliente(newData.getCliente()));
                entity.setEstado(newData.getEstado());
                entity.setFechaVenta(newData.getFechaVenta());
                entity.setFechaVencimiento(newData.getFechaVencimiento());
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
     * Retrieves all CuentaPorCobrar entities from the database.
     * 
     * @return a list of CuentaPorCobrarDTO
     */
    @Override
    public ArrayList<CuentaPorCobrarDTO> findAll() {
        open();
        try {
            @SuppressWarnings("unchecked")
            ArrayList<CuentaPorCobrar> entityList = (ArrayList<CuentaPorCobrar>) em
                    .createQuery("SELECT c FROM CuentaPorCobrar c")
                    .getResultList();

            return (ArrayList<CuentaPorCobrarDTO>) DataMapper
                    .listaCuentaPorCobrarAlistaCuentaPorCobrarDTO(entityList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
        return new ArrayList<>();
    }

    /**
     * Finds a CuentaPorCobrar entity by its ID.
     * 
     * @param id the ID of the entity
     * @return the corresponding CuentaPorCobrarDTO or null if not found
     */
    @Override
    public CuentaPorCobrarDTO findOne(int id) {
        open();
        try {
            return DataMapper.cuentaPorCobrarACuentaPorCobrarDTO(em.find(CuentaPorCobrar.class, id));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
        return null;
    }

    /**
     * Deletes multiple CuentaPorCobrar entities given a list of IDs.
     * 
     * @param ids the list of entity IDs to delete
     */
    @Override
    public void eliminarTodos(List<Integer> ids) {
        // TODO: implement batch deletion if needed
    }
}
