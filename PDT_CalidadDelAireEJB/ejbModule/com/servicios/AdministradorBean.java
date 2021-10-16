package com.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Administrador;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class AdministradorBean
 */
@Stateless
public class AdministradorBean implements AdministradorBeanRemote {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public AdministradorBean() {
        // TODO Auto-generated constructor stub
    }
    @Override
	public void crear(Administrador administrador) throws ServiciosException {
		try {
			em.persist(administrador);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException("No se pudo crear el administrador");
		}
		
	}

	@Override
	public void actualizar(Administrador administrador) throws ServiciosException {
		try {
			em.merge(administrador);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException("No se pudo actualizar el administrador");
		}
		
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try {
			Administrador administrador = em.find(Administrador.class, id);
			em.remove(administrador);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo borrrar el administrador");
		}
		
	}

	@Override
	public List<Administrador> obtenerTodos() {
		TypedQuery<Administrador> query = em.createQuery("SELECT a FROM Administrador a", Administrador.class);
		return query.getResultList();
	}

	@Override
	public List<Administrador> obtenerTodos(String filtro) {
		TypedQuery<Administrador> query = em.createQuery("SELECT a FROM Administrador a WHERE a.nombre LIKE :nombre", Administrador.class)
				.setParameter("nombre", filtro);
		return query.getResultList();
	}

}
