package com.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Ciudad;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class CiudadesBean
 */
@Stateless
public class CiudadesBean implements CiudadesBeanRemote {

@PersistenceContext
 private EntityManager em;
 
    public CiudadesBean() {
        // TODO Auto-generated constructor stub
    }

	public void crear(Ciudad ciudad) throws ServiciosException {
		try {
			em.persist(ciudad);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo crear la Ciudad: " + ciudad.getNombre());
		}
	}

	@Override
	public void actualizar(Ciudad ciudad) throws ServiciosException {
		try {
			em.merge(ciudad);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo actualizar la Ciudad: " + ciudad.getNombre());
		}
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try {
			Ciudad ciudad = em.find(Ciudad.class, id);
			em.remove(ciudad);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo borrar la ciudad");
		}
	}

	@Override
	public List<Ciudad> obtenerTodos() {
		TypedQuery<Ciudad>query = em.createNamedQuery("Ciudad.obtenerTodos", Ciudad.class);
		return query.getResultList();
	}

	@Override
	public List<Ciudad> obtenerTodos(String filtro) {
		TypedQuery<Ciudad>query = em.createQuery("SELECT c FROM Ciudad c WHERE c.nombre LIKE :nombre", Ciudad.class)
				.setParameter("nombre",filtro);
		return query.getResultList();
	}

}
