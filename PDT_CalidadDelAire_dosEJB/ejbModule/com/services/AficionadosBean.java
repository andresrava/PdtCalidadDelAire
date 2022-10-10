package com.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Aficionado;
import com.entities.Formulario;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class AficionadosBean
 */

@Stateless
public class AficionadosBean implements AficionadosBeanRemote {


	@PersistenceContext
	private EntityManager em;

    public AficionadosBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Aficionado crear(Aficionado aficionado) throws ServiciosException {
		try {
			em.persist(aficionado);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo crear el aficionado: " + aficionado.getNombre());
		}
		return aficionado;
		
	}

	@Override
	public void actualizar(Aficionado aficionado) throws ServiciosException {
		try {
			em.merge(aficionado);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo actualizar el aficionado: " + aficionado.getNombre());
		}
		
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try {
			Aficionado aficionado = em.find(Aficionado.class, id);
			em.remove(aficionado);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo borrar el aficionado");
		}
	}

	@Override
	public List<Aficionado> obtenerTodos() {
		TypedQuery<Aficionado>query = em.createNamedQuery("Aficionado.obtenerTodos", Aficionado.class);
		return query.getResultList();
	}

	@Override
	public List<Aficionado> obtenerTodos(String filtro) {
		TypedQuery<Aficionado>query = em.createQuery("SELECT a FROM Aficionado a WHERE a.nombre LIKE :nombre", Aficionado.class)
				.setParameter("nombre",filtro);
		return query.getResultList();
	}

	@Override
	public List<Aficionado> obtenerPorFormulario(Long idFormulario) {
		Aficionado aficionado = em.find(Aficionado.class, idFormulario);
			TypedQuery<Aficionado>query = em.createQuery("SELECT a FROM Aficionado a WHERE a.id LIKE :id", Aficionado.class)
					.setParameter("id",aficionado.getId());
			return query.getResultList();
	}

	@Override
	public void asignarFormulario(Long idAficionado, Long idFormulario) throws ServiciosException {
		try {
			Aficionado aficionado = em.find(Aficionado.class, idFormulario);
			Formulario formulario = em.find(Formulario.class, idFormulario);
			aficionado.getFormularios().add(formulario);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException ("No se pudo asignar el formulario al aficionado");
		}
		
	}

	@Override
	public List<Aficionado> obtenerPorID(String id) {
		TypedQuery<Aficionado>query = em.createQuery("SELECT a FROM Aficionado a WHERE a.id LIKE :id", Aficionado.class)
				.setParameter("id",id);
		return query.getResultList();
	}

	@Override
	public Aficionado obtenerPorID(Long id) {
		Aficionado aficionado = em.find(Aficionado.class, id);
		return aficionado;
	}

	@Override
	public Aficionado actualizarAficionado(Aficionado aficionado) throws ServiciosException {
		try {
			em.merge(aficionado);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo actualizar el aficionado: " + aficionado.getNombre());
		}
		return aficionado;
		
	}


}
