package com.services;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.Usuario;
import com.enumerados.BorradoLogico.Estado;
import com.exceptions.ServiciosException;


@Stateless
public class UsuariosBean implements UsuariosBeanRemote {

	@PersistenceContext
	private EntityManager em;
	
	
    public UsuariosBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Usuario crear(Usuario usuario) throws ServiciosException {
		try {
			System.out.println("Entro al try");
			em.persist(usuario);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo crear el usuario");
		}
		return usuario;
		
	}
	
	@Override
	public List<Usuario> validarLogin(String mail, String password) {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.contrasea = :password AND u.mail = :mail", Usuario.class)
				.setParameter("password", password).setParameter("mail", mail);
		return query.getResultList();
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
			Estado estado = usuario.getEstado();
			if ( estado == Estado.HABILITADO)
				usuario.setEstado(Estado.BORRADO);
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo borrar el usuario");
		}
	}

	@Override
	public List<Usuario> obtenerTodos() {
		TypedQuery<Usuario>query = em.createNamedQuery("Usuario.obtenerTodos", Usuario.class);
		List<Usuario> usuariosFiltrados = new LinkedList<Usuario>();
		for (Usuario u : query.getResultList()) {
			if (u.getEstado() == Estado.HABILITADO)
				usuariosFiltrados.add(u);
		}
		return usuariosFiltrados;
	}

	@Override
	public List<Usuario> obtenerTodos(String filtro) {
		TypedQuery<Usuario>query = em.createQuery("SELECT u FROM Usuario u WHERE u.nombre LIKE :nombre", Usuario.class)
				.setParameter("nombre",filtro);
		List<Usuario> usuariosFiltrados = new LinkedList<Usuario>();
		for (Usuario u : query.getResultList()) {
			if (u.getEstado() == Estado.HABILITADO)
				usuariosFiltrados.add(u);
		}
		return usuariosFiltrados;
	}


	
	@Override
	public Usuario obtenerPorId(Long id) throws ServiciosException {
		Usuario usuario = new Usuario();
		try {
			usuario = em.find(Usuario.class, id);
	} catch(PersistenceException e) {
		throw new ServiciosException ("No se pudo recuperar el Usuario");
	}
		if (usuario.getEstado() == Estado.HABILITADO)
			return usuario;
		else
			return null;
	}
	
	@Override
	public List<Usuario> obtenerPorMail(String mail) throws ServiciosException {
		TypedQuery<Usuario>query = em.createQuery("SELECT u FROM Usuario u WHERE u.mail LIKE :mail", Usuario.class)
				.setParameter("mail",mail);
		List<Usuario> usuariosFiltrados = new LinkedList<Usuario>();
		for (Usuario u : query.getResultList()) {
			if (u.getEstado() == Estado.HABILITADO)
				usuariosFiltrados.add(u);
		}
		return usuariosFiltrados;
	}

	@Override
	public Usuario obtenerPorID(Long id) throws ServiciosException {
		try {
			Usuario usuario = em.find(Usuario.class, id);
			if (usuario.getEstado() == Estado.HABILITADO)
				return usuario;
			else
				return null;
		} catch(PersistenceException e) {
			throw new ServiciosException (e.getMessage());
		}
	}

	@Override
	public List<Usuario> obtenerPorNomApe(String nombre, String apellido) throws ServiciosException {
		TypedQuery<Usuario>query = em.createQuery("SELECT u FROM Usuario u WHERE u.nombre LIKE :nombre AND u.apellido LIKE :apellido", Usuario.class)
				.setParameter("nombre",nombre+"%").setParameter("apellido", apellido+"%");
		List<Usuario> usuariosFiltrados = new LinkedList<Usuario>();
		for (Usuario u : query.getResultList()) {
			if (u.getEstado() == Estado.HABILITADO)
				usuariosFiltrados.add(u);
		}
		return usuariosFiltrados;
	}
	

}
