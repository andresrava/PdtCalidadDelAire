package com.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Administrador;
import com.entities.Ciudad;
import com.entities.Formulario;
import com.entities.Investigador;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class InvestigadoresBean
 */
@Stateless
public class InvestigadoresBean implements InvestigadoresBeanRemote {

	@PersistenceContext
	private EntityManager em;

    public InvestigadoresBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Investigador crear(Investigador investigador) throws ServiciosException {
		try {
			em.persist(investigador);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo crear el investigador: " + " " + investigador.getNombre() + " " + "e.printStackTrace()");
		}
		return investigador;
		
	}

	@Override
	public void actualizar(Investigador investigador) throws ServiciosException {
		try {
			em.merge(investigador);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo actualizar el investigador: " + investigador.getNombre());
		}
		
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try {
			Investigador investigador = em.find(Investigador.class, id);
			em.remove(investigador);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo borrar el investigador");
		}
	}

	@Override
	public List<Investigador> obtenerTodos() {
		TypedQuery<Investigador>query = em.createNamedQuery("Investigador.obtenerTodos", Investigador.class);
		return query.getResultList();
	}

	@Override
	public List<Investigador> obtenerTodos(String filtro) {
		TypedQuery<Investigador>query = em.createQuery("SELECT a FROM Investigador i WHERE i.nombre LIKE :nombre", Investigador.class)
				.setParameter("nombre",filtro);
		return query.getResultList();
	}

	@Override
	public List<Investigador> obtenerPorFormulario(Long idFormulario) {
		Investigador investigador = em.find(Investigador.class, idFormulario);
			TypedQuery<Investigador>query = em.createQuery("SELECT a FROM Investigador i WHERE i.id LIKE :id", Investigador.class)
					.setParameter("id",investigador.getId());
			return query.getResultList();
	}

	@Override
	public void asignarFormulario(Long idInvestigador, Long idFormulario) throws ServiciosException {
		try {
			Investigador investigador = em.find(Investigador.class, idFormulario);
			Formulario formulario = em.find(Formulario.class, idFormulario);
			investigador.getFormularios().add(formulario);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException ("No se pudo asignar el formulario al investigador");
		}
	}
	@Override
	public void asignarCiudad(Long idInvestigador, Long idCiudad) throws ServiciosException {
		try {
			Investigador investigador = em.find(Investigador.class, idInvestigador);
			Ciudad ciudad = em.find(Ciudad.class, idCiudad);
			investigador.setCiudad(ciudad);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException ("No se pudo asignar la ciudad al investigador");
		}
		
	}

	@Override
	public List<Investigador> obtenerPorID(String id) {
		TypedQuery<Investigador>query = em.createQuery("SELECT a FROM Investigador i WHERE i.id LIKE :id", Investigador.class)
				.setParameter("id",id);
		return query.getResultList();
	}

	@Override
	public Investigador obtenerPorID(Long id) {
		Investigador investigador = em.find(Investigador.class, id);
		return investigador;
	}
}

