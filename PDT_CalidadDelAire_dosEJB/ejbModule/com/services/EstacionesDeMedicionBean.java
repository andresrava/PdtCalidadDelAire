package com.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Casilla;
import com.entities.EstacionDeMedicion;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class EstacionesDeMedicionBean
 */
@Stateless
public class EstacionesDeMedicionBean implements EstacionesDeMedicionBeanRemote {

	@PersistenceContext
	private EntityManager em;

    public EstacionesDeMedicionBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public EstacionDeMedicion crear(EstacionDeMedicion estacion) throws ServiciosException {
		try {
			em.persist(estacion);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo crear la estacion: " + estacion.getNombre());
		}
		return estacion;
		
	}
	

	@Override
	public EstacionDeMedicion actualizar(EstacionDeMedicion estacion) throws ServiciosException {
		try {
			em.merge(estacion);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo actualizar la estacion: " + estacion.getNombre());
		}
		return estacion;
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try {
			EstacionDeMedicion estacion = em.find(EstacionDeMedicion.class, id);
			em.remove(estacion);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo borrar la estacion");
		}
	}

	@Override
	public List<EstacionDeMedicion> obtenerTodos() {
		TypedQuery<EstacionDeMedicion>query = em.createNamedQuery("EstacionDeMedicion.obtenerTodos", EstacionDeMedicion.class);
		return query.getResultList();
	}

	@Override
	public List<EstacionDeMedicion> obtenerTodos(String filtro) {
		TypedQuery<EstacionDeMedicion>query = em.createQuery("SELECT e FROM EstacionDeMedicion e WHERE e.nombre LIKE :nombre", EstacionDeMedicion.class)
				.setParameter("nombre",filtro);
		return query.getResultList();
	}

	@Override
	public List<EstacionDeMedicion> obtenerPorDepartamento(Long idDepartamento) {
		TypedQuery<EstacionDeMedicion>query = em.createQuery("SELECT e FROM EstacionDeMedicion e WHERE e.ciudad.departamento.id LIKE :idDepartamento", EstacionDeMedicion.class)
				.setParameter("idDepartamento",idDepartamento);
		return query.getResultList();
	}

	@Override
	public List<EstacionDeMedicion> obtenerPorCiudad(Long idCiudad) {
		TypedQuery<EstacionDeMedicion>query = em.createQuery("SELECT e FROM EstacionDeMedicion e WHERE e.ciudad.id LIKE :idCiudad", EstacionDeMedicion.class)
				.setParameter("idCiudad",idCiudad);
		return query.getResultList();
	}

	@Override
	public List<EstacionDeMedicion> obtenerPorInvestigador(Long idInvestigador) {
		TypedQuery<EstacionDeMedicion>query = em.createQuery("SELECT e FROM EstacionDeMedicion e WHERE e.investigador.id LIKE :idInvestigador", EstacionDeMedicion.class)
				.setParameter("idInvestigador",idInvestigador);
		return query.getResultList();
	}

	@Override
	public void asignarCasilla(Long idEstacion, Long idCasilla) throws ServiciosException {
		try {
			EstacionDeMedicion estacion = em.find(EstacionDeMedicion.class, idEstacion);
			Casilla casilla = em.find(Casilla.class, idCasilla);
			estacion.getCasillas().add(casilla);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException ("No se pudo asignar la Casilla a la Estaci�n de Medici�n");
		}
		
	}

}
