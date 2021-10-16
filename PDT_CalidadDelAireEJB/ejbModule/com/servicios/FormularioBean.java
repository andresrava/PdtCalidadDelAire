package com.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;


import com.entities.Formulario;
import com.entities.Aficionado;
import com.entities.Casilla;
import com.entities.Administrador;
import com.entities.Investigador;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class FormularioBean
 */
@Stateless
public class FormularioBean implements FormularioBeanRemote {

	@PersistenceContext
	private EntityManager em;
	
    public FormularioBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void crear(Formulario formulario) throws ServiciosException {
		try{
			em.persist(formulario);
			em.flush();
		}catch(PersistenceException e){ throw new
			ServiciosException("No se pudo crear el departamento");
		}
	}

	@Override
	public void actualizar(Formulario formulario) throws ServiciosException {
		try{
			 em.merge(formulario);
			 em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el departamento");
		}
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try{
			Formulario formulario = em.find(Formulario.class, id);
			em.remove(formulario);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar el departamento");
		} 
	}

	@Override
	public void asignarAficionado(Long idFormulario, Long idAficionado) throws ServiciosException {
		try{
			Formulario formulario = em.find(Formulario.class, idFormulario);
			formulario.setAficionado(em.find(Aficionado.class, idAficionado));
			em.flush();
		}catch(PersistenceException e){ 
				throw new ServiciosException("No se pudo asignar el departamento a la carrera");
		} 
		
	}

	@Override
	public void asignarAdministrador(Long idFormulario, Long idAdministrador) throws ServiciosException {
		try{
			Formulario formulario = em.find(Formulario.class, idFormulario);
			formulario.setAdministrador(em.find(Administrador.class, idAdministrador));
			em.flush();
		}catch(PersistenceException e){ 
				throw new ServiciosException("No se pudo asignar el departamento a la carrera");
		} 
		
	}

	@Override
	public void asignarInvestigador(Long idFormulario, Long idInvestigador) throws ServiciosException {
		try{
			Formulario formulario = em.find(Formulario.class, idFormulario);
			formulario.setInvestigador(em.find(Investigador.class, idInvestigador));
			em.flush();
		}catch(PersistenceException e){ 
				throw new ServiciosException("No se pudo asignar el departamento a la carrera");
		} 
	}

	@Override
	public void asignarCasilla(Long idFormulario, Long idCasilla) throws ServiciosException {
		try{
			Formulario formulario = em.find(Formulario.class, idFormulario);
			formulario.getCasillas().add(em.find(Casilla.class, idCasilla));
			em.flush();
		}catch(PersistenceException e){ 
			throw new ServiciosException("No se pudo asignar la materia a la carrera");
		} 
	}

	@Override
	public List<Formulario> obtenerTodos() {
		TypedQuery<Formulario> query = em.createQuery("SELECT d FROM Formulario d",Formulario.class);
		return query.getResultList();
	}

}
