package com.services;

import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Casilla;
import com.entities.EstacionDeMedicion;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import com.enumerados.BorradoLogico.Estado;

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
			throw new ServiciosException ("No se pudo crear la Estación");
		}
		return estacion; 
	}
	
	public void agregarUsuario(Long idEM , Long idUsuario) {
		EstacionDeMedicion estacion = em.find(EstacionDeMedicion.class, idEM);
		Usuario usuario = em.find(Usuario.class, idUsuario);
		estacion.setUsuario(usuario);
		em.flush();
		
		
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
			Estado estado = estacion.getEstado();
			if ( estado == Estado.HABILITADO)
			estacion.setEstado(Estado.BORRADO);
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo borrar la estacion");
		}
	}

	
	@Override
	public EstacionDeMedicion obtenerPorId(Long id) throws ServiciosException {
		EstacionDeMedicion estacion = new EstacionDeMedicion();
		EstacionDeMedicion estacionVacia = new EstacionDeMedicion();
		
		try {
			estacion = em.find(EstacionDeMedicion.class, id);
			
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se encontró la estación");
		}
		if (estacion.getEstado() == Estado.HABILITADO)
		return estacion;
		else
			return estacionVacia;
	}
	
	
	@Override
	public List<EstacionDeMedicion> obtenerTodasEM() {
		List<EstacionDeMedicion> a = em.createNamedQuery("EstacionDeMedicion.obtenerTodos" , EstacionDeMedicion.class).getResultList();
		List<EstacionDeMedicion> b = new LinkedList<>();
		for (EstacionDeMedicion e : a)
			{if (e.getEstado() == Estado.HABILITADO)
				b.add(e);
			}
		return b;
	}

	@Override
	public List<EstacionDeMedicion> obtenerTodasEM(String filtro) {
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
	public void asignarCasillaAEM(Long idEstacion, Long idCasilla) throws ServiciosException {
		try {
			EstacionDeMedicion estacion = em.find(EstacionDeMedicion.class, idEstacion);
			Casilla casilla = em.find(Casilla.class, idCasilla);
			estacion.getCasillas().add(casilla);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException ("No se pudo asignar la Casilla a la Estación de Medición");
		}
		
	}

}
