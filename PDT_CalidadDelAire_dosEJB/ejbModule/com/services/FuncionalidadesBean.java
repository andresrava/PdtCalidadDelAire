package com.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Funcionalidad;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class FuncionalidadesBean
 */
@Stateless
public class FuncionalidadesBean implements FuncionalidadesBeanRemote {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public FuncionalidadesBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void crear(Funcionalidad funcionalidad) throws ServiciosException {
		try {
			em.persist(funcionalidad);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo crear la funcionalidad: " + funcionalidad.getNombre());
		}
		
	}
	

	@Override
	public void actualizar(Funcionalidad funcionalidad) throws ServiciosException {
		try {
			em.merge(funcionalidad);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo actualizar la estacion: " + funcionalidad.getNombre());
		}
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try {
			Funcionalidad funcionalidad = em.find(Funcionalidad.class, id);
			em.remove(funcionalidad);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo borrar la funcionalidad");
		}
	}

	@Override
	public List<Funcionalidad> obtenerTodos() {
		TypedQuery<Funcionalidad>query = em.createNamedQuery("Funcionalidad.obtenerTodos", Funcionalidad.class);
		return query.getResultList();
	}

	@Override
	public List<Funcionalidad> obtenerTodos(String filtro) {
		TypedQuery<Funcionalidad>query = em.createQuery("SELECT f FROM Funcionalidad f WHERE f.nombre LIKE :nombre", Funcionalidad.class)
				.setParameter("nombre",filtro);
		return query.getResultList();
	}

//	@Override
//	public List<Funcionalidad> obtenerPorUsuario(Long idUsuario) {
//		Usuario usuario = em.find(Usuario.class, idUsuario);
//		if (usuario instanceof Administrador) {
//			TypedQuery<Funcionalidad>query = em.createNamedQuery("Funcionalidad.obtenerAdministrador", Funcionalidad.class);
//					return query.getResultList();		
//		}
//		else if (usuario instanceof Investigador) {
//			TypedQuery<Funcionalidad>query = em.createNamedQuery("Funcionalidad.obtenerInvestigador", Funcionalidad.class);
//			return query.getResultList();		
//		}
//		else {
//			TypedQuery<Funcionalidad>query = em.createNamedQuery("Funcionalidad.obtenerAficionado", Funcionalidad.class);
//			return query.getResultList();
//		}
//	}
//
//	@Override
//	public void asignarUsuario(Long idFuncionalidad, Long idUsuario) throws ServiciosException {
//		try {
//			Funcionalidad funcionalidad = em.find(Funcionalidad.class, idFuncionalidad);
//			Usuario usuario = em.find(Usuario.class, idUsuario);
//			funcionalidad.getUsuarios().add(usuario);
//			em.flush();
//		} catch(PersistenceException e) {
//			throw new ServiciosException ("No se pudo asignar el Usuario a la Funcionalidad");
//		}
//		
//	}

}
