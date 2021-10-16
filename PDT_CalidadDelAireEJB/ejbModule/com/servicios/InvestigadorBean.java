package com.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Investigador;
import com.entities.Usuario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class InvestigadorBean
 */
@Stateless
public class InvestigadorBean implements InvestigadorBeanRemote {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public InvestigadorBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void crear(Investigador investigador) throws ServiciosException {
		try {
			em.persist(investigador);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException("No se pudo crear el investigador");
		}
		
	}

	@Override
	public void actualizar(Investigador investigador) throws ServiciosException {
		try {
			em.merge(investigador);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException("No se pudo actualizar el usuario");
		}
		
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try {
			Investigador investigador = em.find(Investigador.class, id);
			em.remove(investigador);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo borrrar el investigador");
		}
	}

	@Override
	public List<Investigador> obtenerTodos() {
		TypedQuery<Investigador> query = em.createQuery("SELECT i FROM Investigador i", Investigador.class);
		return query.getResultList();
		
	}

	@Override
	public List<Investigador> obtenerTodos(String filtro) {
		TypedQuery<Investigador> query = em.createQuery("SELECT i FROM Usuario i WHERE i.nombre LIKE :nombre", Investigador.class)
				.setParameter("nombre", filtro);
		return query.getResultList();
	}

}
