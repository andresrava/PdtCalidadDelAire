package com.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Ciudad;
import com.entities.Departamento;
import com.exceptions.ServiciosException;


@Stateless
public class DepartamentosBean implements DepartamentosBeanRemote {

@PersistenceContext
private EntityManager em;

 public DepartamentosBean() {
        // TODO Auto-generated constructor stub
    }
    @Override
	public void crear(Departamento departamento) throws ServiciosException {
		try {
			em.persist(departamento);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo crear el Departamento: " + departamento.getNombre());
		}
		
	}

	@Override
	public void actualizar(Departamento departamento) throws ServiciosException {
		try {
			em.merge(departamento);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo actualizar el departamento: " + departamento.getNombre());
		}
		
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try {
			Departamento departamento = em.find(Departamento.class, id);
			em.remove(departamento);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo borrar el departamento");
		}
	}

	@Override
	public List<Departamento> obtenerTodos() {
		TypedQuery<Departamento>query = em.createNamedQuery("Departamento.obtenerTodos", Departamento.class);
		return query.getResultList();
	}

	@Override
	public List<Departamento> obtenerTodos(String filtro) {
		TypedQuery<Departamento>query = em.createQuery("SELECT d FROM Departamento d WHERE u.nombre LIKE :nombre", Departamento.class)
				.setParameter("nombre",filtro);
		return query.getResultList();
	}

	@Override
	public void asignarCiudad(Long idDepartamento, Long idCiudad) throws ServiciosException {
		try {
			Departamento departamento = em.find(Departamento.class, idDepartamento);
			Ciudad ciudad = em.find(Ciudad.class, idCiudad);
			departamento.getCiudades().add(ciudad);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException ("No se pudo asignar la Ciudad al Departamento");
		}
	}
}
