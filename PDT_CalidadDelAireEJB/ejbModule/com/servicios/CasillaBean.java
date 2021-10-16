package com.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Casilla;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class CasillaBean
 */
@Stateless
public class CasillaBean implements CasillaBeanRemote {

	@PersistenceContext
	private EntityManager em;
	
    public CasillaBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void crear(Casilla casilla) throws ServiciosException {
		try{
			em.persist(casilla);
			em.flush();
		}catch(PersistenceException e){ throw new
			ServiciosException("No se pudo crear el departamento");
		}
		
	}

	@Override
	public void actualizar(Casilla casilla) throws ServiciosException {
		try{
			 em.merge(casilla);
			 em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el departamento");
		}
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try{
			Casilla casilla = em.find(Casilla.class, id);
			em.remove(casilla);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar el departamento");
		} 
	}

	@Override
	public void asignarFormulario(Long idCasilla, Long idFormulario) throws ServiciosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Casilla> obtenerTodos() {
		TypedQuery<Casilla> query = em.createQuery("SELECT d FROM Casilla d",Casilla.class);
		return query.getResultList();
	}

}
