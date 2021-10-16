package com.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Usuario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class UsuariosBean
 */


@Stateless
public class UsuariosBean implements UsuariosBeanRemote {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
	
	
    public UsuariosBean() {
       
    }

    
	@Override
	public void crear(Usuario usuario) throws ServiciosException {
		try {
			em.persist(usuario);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException("No se pudo crear el usuario");
		}
		
	}

	@Override
	public void actualizar(Usuario usuario) throws ServiciosException {
		try {
			em.merge(usuario);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException("No se pudo actualizar el usuario");
		}
		
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try {
			Usuario usuario = em.find(Usuario.class, id);
			em.remove(usuario);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo borrrar el usuario");
		}
		
	}

	@Override
	public List<Usuario> obtenerTodos() {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
		return query.getResultList();
	}

	@Override
	public List<Usuario> obtenerTodos(String filtro) {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.nombre LIKE :nombre", Usuario.class)
				.setParameter("nombre", filtro);
		return query.getResultList();
	}

}
