package com.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Casilla;
import com.entities.Formulario;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class FormulariosBean
 */
@Stateless
public class FormulariosBean implements FormulariosBeanRemote {

	@PersistenceContext
	private EntityManager em;
    public FormulariosBean() {
        // TODO Auto-generated constructor stub
    }
    @Override
	public void crear(Formulario formulario) throws ServiciosException {
		try {
			em.persist(formulario);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo crear el formulario: " + formulario.getNombre());
		}
		
	}

	@Override
	public void actualizar(Formulario formulario) throws ServiciosException {
		try {
			em.merge(formulario);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo actualizar el formulario: " + formulario.getNombre());
		}
		
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try {
			Formulario formulario = em.find(Formulario.class, id);
			em.remove(formulario);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo borrar el formulario");
		}
	}

	@Override
	public List<Formulario> obtenerTodos() {
		TypedQuery<Formulario>query = em.createNamedQuery("Formulario.obtenerTodos", Formulario.class);
		return query.getResultList();
	}

	@Override
	public List<Formulario> obtenerTodos(String filtro) {
		TypedQuery<Formulario>query = em.createQuery("SELECT f FROM Formulario f WHERE f.nombre LIKE :nombre", Formulario.class)
				.setParameter("nombre",filtro);
		return query.getResultList();
	}

	@Override
	public void asignarCasilla(Long idFormulario, Long idCasilla) throws ServiciosException {
		try {
			Formulario formulario = em.find(Formulario.class, idFormulario);
			Casilla casilla = em.find(Casilla.class, idCasilla);
			formulario.getCasillas().add(casilla);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException ("No se pudo asignar la Casilla al Formulario");
		}
		
	}
	@Override
	public List<Formulario> obtenerPorCasilla(Long idCasilla) {
		TypedQuery<Formulario>query = em.createQuery("SELECT f FROM Formulario f WHERE f.casillas", null);
		return null;
	}

}
