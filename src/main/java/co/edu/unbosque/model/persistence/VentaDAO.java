package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.Venta;
import co.edu.unbosque.model.VentaDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 * Data Access Object (DAO) for managing Venta entities.
 * Implements CRUD operations for VentaDTO objects and integrates with FileHandler
 * to generate XML invoices automatically upon creation.
 * 
 * @author InnovaCore
 */
public class VentaDAO implements CRUDOperation<VentaDTO> {

    /** EntityManagerFactory for JPA persistence */
    public EntityManagerFactory emf;

    /** EntityManager for interacting with the database */
    public EntityManager em;

    /** File handler used for generating XML invoices */
    private FileHandler fh;

    /**
     * Default constructor.
     * Initializes EntityManagerFactory, EntityManager, and FileHandler.
     */
    public VentaDAO() {
        emf = PersistenceConfig.createEntityManagerFactory("papeleriaPU");
        em = emf.createEntityManager();
        fh = new FileHandler();
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
     * Creates a new Venta entity in the database and generates its XML invoice.
     * 
     * @param data VentaDTO object to persist
     */
    @Override
    public void crear(VentaDTO data) {
        open();
        try {
            em.getTransaction().begin();
            em.persist(DataMapper.ventaDTOAVenta(data));
            em.getTransaction().commit();

            // Generate XML invoice after successful persistence
            fh.generarFacturaXML(data.getRutaFactura().split("/")[1], data);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
    }

    /**
     * Deletes a Venta entity by its ID.
     * 
     * @param id ID of the Venta to delete
     * @return true if deleted successfully, false otherwise
     */
    @Override
    public Boolean eliminar(int id) {
        open();
        try {
            em.getTransaction().begin();
            Venta entity = em.find(Venta.class, id);
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
     * Updates an existing Venta entity with new data.
     * 
     * @param id ID of the Venta to update
     * @param newData VentaDTO containing updated information
     * @return true if updated successfully, false otherwise
     */
    @Override
    public Boolean actualizar(int id, VentaDTO newData) {
        open();
        try {
            em.getTransaction().begin();
            Venta entity = em.find(Venta.class, id);
            if (entity != null) {
                entity.setCliente(DataMapper.clienteDTOAcliente(newData.getCliente()));
                entity.setEstado(newData.getEstado());
                entity.setFecha(newData.getFecha());
                entity.setIdVenta(newData.getIdVenta());
                entity.setRutaFactura(newData.getRutaFactura());
                entity.setTotal(newData.getTotal());
                entity.setListaDetallesVenta(
                        DataMapper.listaDetalleVentaDTOAlistaDetalleVenta(newData.getListaDetallesVenta()));
                entity.setTipoVenta(newData.getTipoVenta());
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
     * Retrieves all Venta entities from the database.
     * 
     * @return list of VentaDTO objects
     */
    @Override
    public ArrayList<VentaDTO> findAll() {
        open();
        try {
            @SuppressWarnings("unchecked")
            ArrayList<Venta> entityList = (ArrayList<Venta>) em.createQuery("SELECT v FROM Venta v")
                    .getResultList();
            return DataMapper.listaVentaAlistaVentaDTO(entityList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
        return new ArrayList<VentaDTO>();
    }

    /**
     * Finds a Venta entity by its ID.
     * 
     * @param id ID of the Venta to find
     * @return VentaDTO object or null if not found
     */
    @Override
    public VentaDTO findOne(int id) {
        open();
        try {
            return DataMapper.ventaAVentaDTO(em.find(Venta.class, id));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null) emf.close();
            if (em != null) em.close();
        }
        return null;
    }

    /**
     * Deletes multiple Venta entities by their IDs.
     * Currently not implemented.
     * 
     * @param ids List of Venta IDs to delete
     */
    @Override
    public void eliminarTodos(List<Integer> ids) {
        // Not implemented
    }

    /**
     * Finds a Venta entity by the invoice path.
     * 
     * @param ruta XML invoice path
     * @return VentaDTO object or null if not found
     */
    public VentaDTO findByRutaFactura(String ruta) {
        open();
        try {
            Venta v = em.createQuery("SELECT v FROM Venta v WHERE v.rutaFactura=:ruta", Venta.class)
                    .setParameter("ruta", ruta)
                    .getSingleResult();
            return DataMapper.ventaAVentaDTO(v);
        } catch (Exception e) {
            return null;
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}
