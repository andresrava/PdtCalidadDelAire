package com.services;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

import com.entities.Casilla;
import com.entities.Ciudad;
import com.entities.EstacionDeMedicion;
import com.entities.Usuario;
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
		String nombre = estacion.getNombre();
		
		
		try {
			Ciudad ciudad = estacion.getCiudad();
			Long idCiudad = ciudad.getId();
			ciudad = em.find(Ciudad.class, idCiudad);
			estacion.setCiudad(ciudad);
			Usuario usuario = estacion.getUsuario();
			Long idUsuario = usuario.getId();
			usuario = em.find(Usuario.class, idUsuario);
			estacion.setUsuario(usuario);
			List<Casilla> casillas = new LinkedList<Casilla>();
			List<Casilla> casillasPlus = new LinkedList<Casilla>();
			casillas = estacion.getCasillas();
			if (!casillas.isEmpty())
			{
				Iterator<Casilla> it = casillas.iterator();
				Long idCasilla;
				Casilla casilla = new Casilla();
				while (it.hasNext()) {
					idCasilla = it.next().getId();
					casilla = em.find(Casilla.class, idCasilla);
					casillasPlus.add(casilla);

				}
				estacion.setCasillas(casillasPlus);
			}
			em.persist(estacion);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo crear la estacion: " + nombre + e.getMessage());
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
			em.remove(estacion);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo borrar la estacion");
		}
	}

	
	@Override
	public EstacionDeMedicion obtenerPorId(Long id) throws ServiciosException {
		EstacionDeMedicion estacion = new EstacionDeMedicion();
		try {
			estacion = em.find(EstacionDeMedicion.class, id);
			
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo borrar la estacion");
		}
		return estacion;
	}
	
	
	@Override
	public List<EstacionDeMedicion> obtenerTodasEM() {
		TypedQuery<EstacionDeMedicion>query = em.createNamedQuery("EstacionDeMedicion.obtenerTodos", EstacionDeMedicion.class);
		return  query.getResultList();
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
