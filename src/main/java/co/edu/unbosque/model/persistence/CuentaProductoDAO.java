package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.CuentaPorPagar;
import co.edu.unbosque.model.CuentaProducto;
import co.edu.unbosque.model.CuentaProductoDTO;
import co.edu.unbosque.model.CuentaProductoId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 * DAO class for managing account-product relationships (CuentaProducto) in the system.
 * <p>
 * Provides CRUD operations and handles the communication between the database
 * and the business layer using JPA EntityManager.
 * </p>
 * 
 * @author InnovaCore
 */
public class CuentaProductoDAO implements CRUDOperation<CuentaProductoDTO> {

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
    public CuentaProductoDAO() {
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
     * Persists a new CuentaProducto entity in the database.
     * 
     * @param data the DTO containing the data to persist
     */
    @Override
    public void crear(CuentaProductoDTO data) {
        open();
        try {
            em.getTransaction().begin();
            em.persist(DataMapper.cuentaProductoDTOAcuentaProducto(data));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
    }

    /**
     * Deletes a CuentaProducto entity by its ID.
     * 
     * @param id the ID of the entity to delete
     * @return true if deletion was successful, false otherwise
     */
    @Override
    public Boolean eliminar(int id) {
        open();
        try {
            em.getTransaction().begin();
            CuentaProducto entity = em.find(CuentaProducto.class, id);
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
     * Updates an existing CuentaProducto entity with new data.
     * 
     * @param id      the ID of the entity to update
     * @param newData the DTO containing the new data
     * @return true if update was successful, false otherwise
     */
    @Override
    public Boolean actualizar(int id, CuentaProductoDTO newData) {
        open();
        try {
            em.getTransaction().begin();
            CuentaProducto entity = em.find(CuentaProducto.class, id);
            if (entity != null) {
                entity.setCuenta(em.find(CuentaPorPagar.class, newData.getIdCuenta()));
                entity.setIdCuentaProducto(newData.getIdCuentaProducto());
                entity.setMonto(newData.getMonto());
                entity.setProducto(DataMapper.productoDTOAproducto(newData.getProducto()));
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
     * Retrieves all CuentaProducto entities from the database.
     * 
     * @return a list of CuentaProductoDTO
     */
    @Override
    public ArrayList<CuentaProductoDTO> findAll() {
        open();
        try {
            @SuppressWarnings("unchecked")
            ArrayList<CuentaProducto> entityList = (ArrayList<CuentaProducto>) em
                    .createQuery("SELECT c FROM CuentaProducto c").getResultList();

            return (ArrayList<CuentaProductoDTO>) DataMapper
                    .ListaCuentaProductoAlistaCuentaProductoDTO(entityList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
        return new ArrayList<>();
    }

    /**
     * Finds a CuentaProducto entity by its integer ID.
     * <p>
     * Note: This method is not implemented because the primary key is composite.
     * </p>
     * 
     * @param id the ID of the entity
     * @return null
     */
    @Override
    public CuentaProductoDTO findOne(int id) {
        return null;
    }

    /**
     * Finds a CuentaProducto entity by its composite ID.
     * 
     * @param id the composite ID of the entity
     * @return the corresponding CuentaProductoDTO or null if not found
     */
    public CuentaProductoDTO findById(CuentaProductoId id) {
        open();
        try {
            return DataMapper.cuentaProductoAcuentaProductoDTO(em.find(CuentaProducto.class, id));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
        return null;
    }

    /**
     * Deletes multiple CuentaProducto entities given a list of IDs.
     * 
     * @param ids the list of entity IDs to delete
     */
    @Override
    public void eliminarTodos(List<Integer> ids) {
        // TODO: implement batch deletion if needed
    }
}
