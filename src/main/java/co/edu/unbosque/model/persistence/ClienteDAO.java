package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.ClienteDTO;
import co.edu.unbosque.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 * Data Access Object (DAO) class for managing {@link Cliente} entities in the database.
 * <p>
 * This class implements the {@link CRUDOperation} interface for {@link ClienteDTO}
 * and handles the persistence operations such as creating, reading, updating,
 * and deleting client records using JPA.
 * </p>
 * 
 * <p>
 * The class uses {@link DataMapper} to convert between {@link Cliente} entities
 * and {@link ClienteDTO} objects for data transfer between layers.
 * </p>
 * 
 * @author InnovaCore
 */
public class ClienteDAO implements CRUDOperation<ClienteDTO> {

	/**
	 * The factory used to create {@link EntityManager} instances.
	 */
	public EntityManagerFactory emf;

	/**
	 * The {@link EntityManager} instance used for database operations.
	 */
	public EntityManager em;

	/**
	 * Default constructor.
	 * <p>
	 * Initializes the EntityManagerFactory and EntityManager using
	 * the defined persistence unit.
	 * </p>
	 */
	public ClienteDAO() {
		emf = PersistenceConfig.createEntityManagerFactory("papeleriaPU");
		em = emf.createEntityManager();
	}

	/**
	 * Ensures that both {@link EntityManagerFactory} and {@link EntityManager} 
	 * are open before performing any operation.
	 */
	public void open() {
		if (!emf.isOpen() || !em.isOpen()) {
			emf = PersistenceConfig.createEntityManagerFactory("papeleriaPU");
			em = emf.createEntityManager();
		}
	}

	/**
	 * Creates a new client record in the database.
	 * 
	 * @param data the client data to persist
	 */
	@Override
	public void crear(ClienteDTO data) {
		open();
		try {
			em.getTransaction().begin();
			em.persist(DataMapper.clienteDTOAcliente(data));
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Deletes a client record by its ID.
	 * 
	 * @param id the ID of the client to delete
	 * @return {@code true} if the client was deleted, {@code false} otherwise
	 */
	@Override
	public Boolean eliminar(int id) {
		open();
		try {
			em.getTransaction().begin();
			Cliente entity = em.find(Cliente.class, id);
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
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Updates the information of an existing client.
	 * 
	 * @param id      the ID of the client to update
	 * @param newData the new client data
	 * @return {@code true} if the client was updated successfully, {@code false} otherwise
	 */
	@Override
	public Boolean actualizar(int id, ClienteDTO newData) {
		open();
		try {
			em.getTransaction().begin();
			Cliente entity = em.find(Cliente.class, id);
			if (entity != null) {
				entity.setCedula(newData.getCedula());
				entity.setNombre(newData.getNombre());
				entity.setApellido(newData.getApellido());
				entity.setTelefono(newData.getTelefono());
				entity.setDireccion(newData.getDireccion());
				em.merge(entity);
				em.getTransaction().commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Retrieves all client records from the database.
	 * 
	 * @return a list of {@link ClienteDTO} objects representing all clients
	 */
	@Override
	public ArrayList<ClienteDTO> findAll() {
		open();
		try {
			@SuppressWarnings("unchecked")
			ArrayList<Cliente> entityList = (ArrayList<Cliente>) em.createQuery("SELECT c FROM Cliente c")
					.getResultList();

			return DataMapper.listaClienteAlistaClienteDTO(entityList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
		return new ArrayList<ClienteDTO>();
	}

	/**
	 * Finds a client by its ID.
	 * 
	 * @param id the ID of the client
	 * @return a {@link ClienteDTO} representing the found client, or {@code null} if not found
	 */
	@Override
	public ClienteDTO findOne(int id) {
		open();
		try {
			return DataMapper.clienteAclienteDTO(em.find(Cliente.class, id));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
		return null;
	}

	/**
	 * Deletes multiple clients by their IDs.
	 * <p>
	 * Currently not implemented.
	 * </p>
	 * 
	 * @param ids the list of client IDs to delete
	 */
	@Override
	public void eliminarTodos(List<Integer> ids) {
		// TODO Auto-generated method stub
	}

	/**
	 * Finds a client by its identification number (cedula).
	 * 
	 * @param cedula the client's identification number
	 * @return a {@link ClienteDTO} representing the found client, or {@code null} if not found
	 */
	public ClienteDTO findOneByCedula(long cedula) {
		open();
		try {
			Cliente c = em.createQuery("SELECT c FROM Cliente c WHERE c.cedula = :cedula", Cliente.class)
					.setParameter("cedula", cedula)
					.getSingleResult();

			return DataMapper.clienteAclienteDTO(c);
		} catch (Exception e) {
			return null;
		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
	}
}
