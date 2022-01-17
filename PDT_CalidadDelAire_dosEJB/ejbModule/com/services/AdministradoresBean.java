package com.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Administrador;
import com.entities.Ciudad;
import com.entities.Formulario;
import com.entities.Investigador;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class AdministradoresBean
 */

@Stateless
public class AdministradoresBean implements AdministradoresBeanRemote {



	@PersistenceContext
	private EntityManager em;

    public AdministradoresBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Administrador crear(Administrador administrador) throws ServiciosException {
		try {
			em.persist(administrador);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo crear el administrador: " + administrador.getNombre() +" "+ e.getMessage());
			
		}
		return administrador;
		
	}

	@Override
	public void actualizar(Administrador administrador) throws ServiciosException {
		try {
			em.merge(administrador);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo actualizar el administrador: " + administrador.getNombre());
		}
		
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try {
			Administrador administrador = em.find(Administrador.class, id);
			em.remove(administrador);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo borrar el administrador");
		}
	}

	@Override
	public List<Administrador> obtenerTodos() {
		TypedQuery<Administrador>query = em.createNamedQuery("Administrador.obtenerTodos", Administrador.class);
		return query.getResultList();
	}

	@Override
	public List<Administrador> obtenerTodos(String filtro) {
		TypedQuery<Administrador>query = em.createQuery("SELECT a FROM Administrador a WHERE a.nombre LIKE :nombre", Administrador.class)
				.setParameter("nombre",filtro);
		return query.getResultList();
	}

	@Override
	public List<Administrador> obtenerPorFormulario(Long idFormulario) {
		Administrador administrador = em.find(Administrador.class, idFormulario);
			TypedQuery<Administrador>query = em.createQuery("SELECT a FROM Administrador a WHERE a.id LIKE :id", Administrador.class)
					.setParameter("id",administrador.getId());
			return query.getResultList();
	}

	@Override
	public void asignarFormulario(Long idAdministrador, Long idFormulario) throws ServiciosException {
		try {
			Administrador administrador = em.find(Administrador.class, idFormulario);
			Formulario formulario = em.find(Formulario.class, idFormulario);
			administrador.getFormularios().add(formulario);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException ("No se pudo asignar el formulario al administrador");
		}
	}

	@Override
	public void asignarCiudad(Long idAdministrador, Long idCiudad) throws ServiciosException {
		try {
			Administrador administrador = em.find(Administrador.class, idCiudad);
			Ciudad ciudad = em.find(Ciudad.class, idCiudad);
			administrador.setCiudad(ciudad);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException ("No se pudo asignar la ciudad al administrador");
		}
	}
	

}
