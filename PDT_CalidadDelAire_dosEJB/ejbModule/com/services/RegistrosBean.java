package com.services;


import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Registro;
import com.entities.RegistroBoolean;
import com.entities.RegistroFloat;
import com.entities.RegistroInteger;
import com.entities.RegistroString;
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
	public Registro crear(Registro registro) throws ServiciosException {
    	if (registro instanceof RegistroString) {
    		
    	}
    	if (registro instanceof RegistroBoolean) {
    		registro = (RegistroBoolean) registro;
    		try {
    			em.persist(registro);
    			em.flush();
    		}catch (PersistenceException e) {
    			throw new ServiciosException ("No se pudo crear el Registro");
    		}
    	}
    	if (registro instanceof RegistroFloat) {
    		registro = (RegistroFloat) registro;
    		try {
    			em.persist(registro);
    			em.flush();
    		}catch (PersistenceException e) {
    			throw new ServiciosException ("No se pudo crear el Registro");
    		}
    	}
    	if (registro instanceof RegistroInteger) {
    		registro = (RegistroInteger) registro;
    		
    	}
    	
    	
		return registro;
		
	}

	@Override
	public Registro actualizar(Registro registro) throws ServiciosException {
		try {
			em.merge(registro);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo actualizar el Registro");
		}
		return registro;
		
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
	public List<Registro> obtenerTodos(Date dateDesde , Date dateHasta) {
		List<Registro> lista = em.createQuery(
				"SELECT r FROM Registro r WHERE ?1<r.fechaHora AND r.fechaHora<?2")
				.setParameter(1, dateDesde)
				.setParameter(2, dateHasta)
				.getResultList();
		return lista;
	}

	@Override
	public java.sql.ResultSet obtenerTodos(Long idFormulario) {
		TypedQuery<Registro>query = em.createQuery("SELECT r FROM Registro r WHERE r.actividad.formulario.id LIKE :formId", Registro.class)
				.setParameter("formId",idFormulario);
		System.out.println("El largo de la Result list es: " + query.getResultList().size());
		System.out.println(query.getResultList());
		return (ResultSet) query.getResultList();
	}
	
	@Override
	public List<Registro> obtenerTodosLista(Long idFormulario) {
		TypedQuery<Registro>query = em.createQuery("SELECT r FROM Registro r WHERE r.actividad.formulario.id LIKE :formId", Registro.class)
				.setParameter("formId",idFormulario);
		System.out.println("El largo de la Result list es: " + query.getResultList().size());
		System.out.println(query.getResultList());
		return query.getResultList();
	}
	@Override
	public Registro encuentraPorId(Long id) {
		Registro registro = em.find(Registro.class, id);
		return registro;
	}
	@Override
	public RegistroString crearString(RegistroString registro1) throws ServiciosException {
		try {
			em.persist(registro1);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo crear el RegistroString");
		}
		return registro1;
	}
	@Override
	public RegistroInteger crearInteger(RegistroInteger registro2) throws ServiciosException {
		try {
			em.persist(registro2);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo crear el Registro");
		}
		return registro2;
	}
	@Override
	public RegistroFloat crearFloat(RegistroFloat registro3) throws ServiciosException {
		try {
			em.persist(registro3);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo crear el Registro");
		}
		return registro3;
	}
	@Override
	public RegistroBoolean crearBoolean(RegistroBoolean registro4) throws ServiciosException {
		try {
			em.persist(registro4);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo crear el Registro");
		}
		return registro4;
	}

}	
