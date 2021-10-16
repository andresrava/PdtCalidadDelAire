package com.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Aficionado;
import com.entities.Usuario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class AficionadoBean
 */
@Stateless
public class AficionadoBean implements AficionadoBeanRemote {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public AficionadoBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void crear(Aficionado aficionado) throws ServiciosException {
		try {
			em.persist(aficionado);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException("No se pudo crear el aficionado");
		}
	}

	@Override
	public void actualizar(Aficionado aficionado) throws ServiciosException {
		try {
			em.merge(aficionado);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException("No se pudo actualizar el aficionado");
		}
		
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try {
			Aficionado aficionado = em.find(Aficionado.class, id);
			em.remove(aficionado);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo borrrar el aficionado");
		}
	}

	@Override
	public List<Aficionado> obtenerTodos() {
		TypedQuery<Aficionado> query = em.createQuery("SELECT a FROM Aficionado a", Aficionado.class);
		return query.getResultList();
	}

	@Override
	public List<Aficionado> obtenerTodos(String filtro) {
		TypedQuery<Aficionado> query = em.createQuery("SELECT a FROM Aficionado a WHERE a.nombre LIKE :nombre", Aficionado.class)
				.setParameter("nombre", filtro);
		return query.getResultList();

	}

}
