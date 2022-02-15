package com.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Investigador;
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
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.contraseña = :password AND u.mail = :mail", Usuario.class)
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

//	@Override
//	public void asignarActividad(Long idUsuario, Long idActividad) throws ServiciosException {
//		try {
//			Usuario usuario = em.find(Usuario.class, idUsuario);
//			Actividad actividad = em.find(Actividad.class, idActividad);
//			usuario.getActividades().add(actividad);
//			em.flush();
//		} catch(PersistenceException e) {
//			throw new ServiciosException ("No se pudo asignar la Actividad al Usuario");
//		}
//		
//	}

	@Override
	public Usuario obtenerPorId(Long id) throws ServiciosException {
		Usuario usuario = new Usuario();
		try {
			usuario = em.find(Usuario.class, id);
	} catch(PersistenceException e) {
		throw new ServiciosException ("No se pudo recuperar el Usuario");
	}
		return usuario;
	}

	@Override
	public List<Usuario> obtenerPorMail(String mail) throws ServiciosException {
		TypedQuery<Usuario>query = em.createQuery("SELECT u FROM Usuario u WHERE u.mail LIKE :mail", Usuario.class)
				.setParameter("mail",mail);
		return query.getResultList();
	}

	@Override
	public Usuario obtenerPorID(Long id) throws ServiciosException {
		try {
			Usuario usuario = em.find(Usuario.class, id);
			return usuario;
		} catch(PersistenceException e) {
			throw new ServiciosException (e.getMessage());
		}
	}
	

}
