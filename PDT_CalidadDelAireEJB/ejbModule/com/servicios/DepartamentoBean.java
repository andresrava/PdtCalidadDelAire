package com.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Departamento;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class DepartamentoBean
 */
@Stateless
public class DepartamentoBean implements DepartamentoBeanRemote {

	@PersistenceContext
	private EntityManager em;
	
    public DepartamentoBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void crear(Departamento departamento) throws ServiciosException {
		try{
			em.persist(departamento);
			em.flush();
		}catch(PersistenceException e){ throw new
			ServiciosException("No se pudo crear el departamento");
		}
	}

	@Override
	public void actualizar(Departamento departamento) throws ServiciosException {
		try{
			 em.merge(departamento);
			 em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el departamento");
		}
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try{
			Departamento departamento = em.find(Departamento.class, id);
			em.remove(departamento);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar el departamento");
		} 
	}

	@Override
	public List<Departamento> obtenerTodos() {
		TypedQuery<Departamento> query = em.createQuery("SELECT d FROM Departamento d",Departamento.class);
		return query.getResultList();
	}

}
