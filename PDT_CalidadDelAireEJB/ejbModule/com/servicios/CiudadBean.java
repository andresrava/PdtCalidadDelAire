package com.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Administrador;
import com.entities.Ciudad;
import com.entities.Investigador;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class CiudadBean
 */
@Stateless
public class CiudadBean implements CiudadBeanRemote {

	@PersistenceContext
	private EntityManager em;
	
    public CiudadBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void crear(Ciudad ciudad) throws ServiciosException {
		try{
			em.persist(ciudad);
			em.flush();
		}catch(PersistenceException e){ throw new
			ServiciosException("No se pudo crear el departamento");
		}
	}

	@Override
	public void actualizar(Ciudad ciudad) throws ServiciosException {
		try{
			 em.merge(ciudad);
			 em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el departamento");
		}
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try{
			Ciudad ciudad  = em.find(Ciudad.class, id);
			em.remove(ciudad);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar el departamento");
		} 
	}

	@Override
	public void asignarAdministrador(Long idCiudad, Long idAdministrador) throws ServiciosException {
		try{
			Ciudad ciudad = em.find(Ciudad.class, idCiudad);
			ciudad.getAdministradores().add(em.find(Administrador.class, idAdministrador));
			em.flush();
		}catch(PersistenceException e){ 
			throw new ServiciosException("No se pudo asignar la materia a la carrera");
		} 
	}

	@Override
	public void asignarInvestigador(Long idCiudad, Long idInvestigador) throws ServiciosException {
		try{
			Ciudad ciudad = em.find(Ciudad.class, idCiudad);
			ciudad.getInvestigadores().add(em.find(Investigador.class, idInvestigador));
			em.flush();
		}catch(PersistenceException e){ 
			throw new ServiciosException("No se pudo asignar la materia a la carrera");
		} 
		
	}

	@Override
	public List<Ciudad> obtenerTodos() {
		TypedQuery<Ciudad> query = em.createQuery("SELECT d FROM Ciudad d",Ciudad.class);
		return query.getResultList();
	}

}
