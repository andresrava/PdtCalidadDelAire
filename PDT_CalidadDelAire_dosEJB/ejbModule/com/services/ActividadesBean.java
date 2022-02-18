package com.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Actividad;
import com.entities.Registro;
import com.exceptions.ServiciosException;



@Stateless
public class ActividadesBean implements ActividadesBeanRemote {

	@PersistenceContext
	private EntityManager em;
    public ActividadesBean() {
        // TODO Auto-generated constructor stub
    }
    @Override
	public Actividad crear(Actividad actividad) throws ServiciosException {
		try {
			em.persist(actividad);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo crear la Actividad");
			 
		}
		return actividad;
	}

	@Override
	public Actividad actualizar(Actividad actividad) throws ServiciosException {
		try {
			em.merge(actividad);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo actualizar la Actividad" );
		}
		return actividad;
		
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try {
			Actividad actividad = em.find(Actividad.class, id);
			em.remove(actividad);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo borrar la actividad");
		}
	}

	@Override
	public List<Actividad> obtenerTodos() {
		TypedQuery<Actividad>query = em.createNamedQuery("Actividad.obtenerTodos", Actividad.class);
		return query.getResultList();
	}

	@Override
	public List<Actividad> obtenerTodos(String filtro) {
		TypedQuery<Actividad>query = em.createQuery("SELECT a FROM Actividad a WHERE a.nombre LIKE :nombre", Actividad.class)
				.setParameter("nombre",filtro);
		return query.getResultList();
	}

	@Override
	public void asignarRegistro(Long idActividad, Long idRegistro) throws ServiciosException {
		try {
			Actividad actividad = em.find(Actividad.class, idActividad);
			Registro registro = em.find(Registro.class, idRegistro);
			actividad.getRegistros().add(registro);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException ("No se pudo asignar el Registro a la Actividad");
		}
		
	}
	
	@Override
	public Actividad agregaRegistro(Long idActividad, Long idRegistro) {
		Registro registro = em.find(Registro.class, idRegistro);
		Actividad actividad = em.find(Actividad.class, idActividad);
		actividad.getRegistros().add(registro);
		em.merge(actividad);
		em.flush();
		return actividad;
	}
	@Override
	public Actividad encuentraPorId(Long id) {
		Actividad actividad = em.find(Actividad.class, id);
		return actividad;
	}
	
}
