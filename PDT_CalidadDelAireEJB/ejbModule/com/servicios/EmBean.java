package com.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Casilla;
import com.entities.Em;
import com.entities.Formulario;
import com.entities.Investigador;
import com.entities.Usuario;
import com.entities.Departamento;
import com.entities.Ciudad;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class EmBean
 */
@Stateless
public class EmBean implements EmBeanRemote {

	@PersistenceContext
	private EntityManager em;
	
    public EmBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void crear(Em eme) throws ServiciosException {
		try{
			em.persist(eme);
			em.flush();
		}catch(PersistenceException e){ throw new
			ServiciosException("No se pudo crear el departamento");
		}
	}

	@Override
	public void actualizar(Em eme) throws ServiciosException {
		try{
			 em.merge(eme);
			 em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el departamento");
		}
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try{
			Em eme = em.find(Em.class, id);
			em.remove(eme);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar el departamento");
		}
	}

	@Override
	public void asignarCiudad(Long idEm, Long idCiudad) throws ServiciosException {
		try{
			Em eme = em.find(Em.class, idEm);
			eme.setCiudad(em.find(Ciudad.class, idCiudad));
			em.flush();
		}catch(PersistenceException e){ 
				throw new ServiciosException("No se pudo asignar el departamento a la carrera");
		}
	}

	@Override
	public void asignarDepartamento(Long idEm, Long idDepartamento) throws ServiciosException {
		try{
			Em eme = em.find(Em.class, idEm);
			eme.setDepartamento(em.find(Departamento.class, idDepartamento));
			em.flush();
		}catch(PersistenceException e){ 
				throw new ServiciosException("No se pudo asignar el departamento a la carrera");
		}
	}

	@Override
	public void asignarInvestigador(Long idEm, Long idInvestigador) throws ServiciosException {
		try{
			Em eme = em.find(Em.class, idEm);
			eme.setInvestigador(em.find(Investigador.class, idInvestigador));
			em.flush();
		}catch(PersistenceException e){ 
				throw new ServiciosException("No se pudo asignar el departamento a la carrera");
		} 
	}

	@Override
	public void asignarUsuario(Long idEm, Long idUsuario) throws ServiciosException {
		try{
			Em eme = em.find(Em.class, idEm);
			eme.getUsuarios().add(em.find(Usuario.class, idUsuario));
			em.flush();
		}catch(PersistenceException e){ 
			throw new ServiciosException("No se pudo asignar la materia a la carrera");
		} 
		
	}

	@Override
	public List<Em> obtenerTodos() {
		TypedQuery<Em> query = em.createQuery("SELECT d FROM Em d",Em.class);
		return query.getResultList();
	}

}
