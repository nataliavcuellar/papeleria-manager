package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.Reporte;
import co.edu.unbosque.model.ReporteDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ReporteDAO implements CRUDOperation<ReporteDTO> {

	public EntityManagerFactory emf;
	public EntityManager em;

	public ReporteDAO() {
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

	@Override
	public void crear(ReporteDTO data) {

		open();
		try {
			em.getTransaction().begin();
			em.merge(DataMapper.reporteDTOToReporte(data));
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (emf != null)
				emf.close();
			if (em != null)
				em.close();
		}
	}

	@Override
	public Boolean eliminar(int id) {
		return false;
	}

	@Override
	public Boolean actualizar(int id, ReporteDTO newData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ReporteDTO> findAll() {
		open();
		try {
			@SuppressWarnings("unchecked")
			List<Reporte> entityList = em.createQuery("SELECT r FROM Reporte r").getResultList();
			return (ArrayList<ReporteDTO>) DataMapper.listaReporteAlistaReporteDTO(entityList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (emf != null)
				emf.close();
			if (em != null)
				em.close();
		}
		return new ArrayList<>();
	}

	@Override
	public ReporteDTO findOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarTodos(List<Integer> ids) {
		// TODO Auto-generated method stub

	}

}
