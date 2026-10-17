package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.Producto;
import co.edu.unbosque.model.ProductoDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 * Data Access Object (DAO) for managing Producto entities.
 * Implements CRUD operations for ProductoDTO objects.
 * Handles database interactions using JPA (Jakarta Persistence API).
 * 
 * @author InnovaCore
 */
public class ProductoDAO implements CRUDOperation<ProductoDTO> {

    /** EntityManagerFactory for JPA persistence */
    public EntityManagerFactory emf;

    /** EntityManager for interacting with the database */
    public EntityManager em;

    /**
     * Default constructor.
     * Initializes EntityManagerFactory and EntityManager.
     */
    public ProductoDAO() {
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
     * Creates a new Producto entity in the database.
     * 
     * @param data ProductoDTO object to be persisted
     */
    @Override
    public void crear(ProductoDTO data) {
        open();
        try {
            em.getTransaction().begin();
            em.persist(DataMapper.productoDTOAproducto(data));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }

    /**
     * Deletes a Producto entity by its ID.
     * 
     * @param id ID of the Producto to delete
     * @return true if deletion was successful, false otherwise
     */
    @Override
    public Boolean eliminar(int id) {
        open();
        try {
            em.getTransaction().begin();
            Producto entity = em.find(Producto.class, id);
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
     * Updates an existing Producto entity with new data.
     * 
     * @param id ID of the Producto to update
     * @param newData ProductoDTO containing updated information
     * @return true if update was successful, false otherwise
     */
    @Override
    public Boolean actualizar(int id, ProductoDTO newData) {
        open();
        try {
            em.getTransaction().begin();
            Producto entity = em.find(Producto.class, id);
            if (entity != null) {
                entity.setCodigo(newData.getCodigo());
                entity.setNombre(newData.getNombre());
                entity.setPrecio_compra(newData.getPrecio_compra());
                entity.setPrecio_venta(newData.getPrecio_venta());
                entity.setStock(newData.getStock());
                entity.setStock_minimo(newData.getStock_minimo());
                entity.setProveedor(DataMapper.proveedorDTOAproveedor(newData.getProveedor()));
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
     * Retrieves all Producto entities from the database.
     * 
     * @return list of ProductoDTO objects
     */
    @Override
    public ArrayList<ProductoDTO> findAll() {
        open();
        try {
            @SuppressWarnings("unchecked")
            ArrayList<Producto> entityList = (ArrayList<Producto>) em.createQuery("SELECT p FROM Producto p")
                    .getResultList();
            return DataMapper.listaProductoAlistaProductoDTO(entityList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
        return new ArrayList<ProductoDTO>();
    }

    /**
     * Finds a Producto entity by its ID.
     * 
     * @param id ID of the Producto to find
     * @return ProductoDTO object or null if not found
     */
    @Override
    public ProductoDTO findOne(int id) {
        open();
        try {
            return DataMapper.productoAproductoDTO(em.find(Producto.class, id));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
        return null;
    }

    /**
     * Deletes multiple Producto entities by their IDs.
     * Currently not implemented.
     * 
     * @param ids List of Producto IDs to delete
     */
    @Override
    public void eliminarTodos(List<Integer> ids) {
        // Not implemented
    }

    /**
     * Finds a Producto entity by its unique code.
     * 
     * @param codigo The unique code of the Producto
     * @return ProductoDTO object or null if not found
     */
    public ProductoDTO findOneByCodigo(String codigo) {
        open();
        try {
            Producto p = em.createQuery("SELECT p FROM Producto p WHERE p.codigo = :codigo", Producto.class)
                    .setParameter("codigo", codigo)
                    .getSingleResult();
            return DataMapper.productoAproductoDTO(p);
        } catch (Exception e) {
            return null;
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}
