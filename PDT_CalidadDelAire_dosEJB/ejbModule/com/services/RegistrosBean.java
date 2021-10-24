package com.services;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Registro;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class RegistrosBean
 */
@Stateless
public class RegistrosBean implements RegistrosBeanRemote {

	@PersistenceContext
	private EntityManager em;

    public RegistrosBean() {
        // TODO Auto-generated constructor stub
    }
    @Override
	public void crear(Registro registro) throws ServiciosException {
		try {
			em.persist(registro);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo crear el Registro");
		}
		
	}

	@Override
	public void actualizar(Registro registro) throws ServiciosException {
		try {
			em.merge(registro);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo actualizar el Registro");
		}
		
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try {
			Registro registro = em.find(Registro.class, id);
			em.remove(registro);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo borrar el Registro");
		}
	}

	@Override
	public List<Registro> obtenerTodos() {
		TypedQuery<Registro>query = em.createNamedQuery("Registro.obtenerTodos", Registro.class);
		return query.getResultList();
	}

	@Override
	public List<Registro> obtenerTodos(Date fechahora) {
		TypedQuery<Registro>query = em.createQuery("SELECT r FROM Registro r WHERE r.fechaHora LIKE :fechaHora", Registro.class)
				.setParameter("fechaHora",fechahora);
		return query.getResultList();
	}

	
		
	
}
