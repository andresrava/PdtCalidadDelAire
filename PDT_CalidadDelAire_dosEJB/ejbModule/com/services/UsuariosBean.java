package com.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Usuario;
import com.exceptions.ServiciosException;


@Stateless
public class UsuariosBean implements UsuariosBeanRemote {

	@PersistenceContext
	private EntityManager em;

    public UsuariosBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void crear(Usuario usuario) throws ServiciosException {
		try {
			em.persist(usuario);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo crear el usuario: " + usuario.getNombre());
		}
		
	}

	@Override
	public void actualizar(Usuario usuario) throws ServiciosException {
		try {
			em.merge(usuario);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo actualizar el usuario: " + usuario.getNombre());
		}
		
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try {
			Usuario usuario = em.find(Usuario.class, id);
			em.remove(usuario);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo borrar el usuario");
		}
	}

	@Override
	public List<Usuario> obtenerTodos() {
		TypedQuery<Usuario>query = em.createNamedQuery("Usuario.obtenerTodos", Usuario.class);
		return query.getResultList();
	}

	@Override
	public List<Usuario> obtenerTodos(String filtro) {
		TypedQuery<Usuario>query = em.createQuery("SELECT u FROM Usuario u WHERE u.nombre LIKE :nombre", Usuario.class)
				.setParameter("nombre",filtro);
		return query.getResultList();
	}

}
