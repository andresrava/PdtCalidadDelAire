package com.services;

import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Casilla;
import com.entities.Formulario;
import com.enumerados.BorradoLogico.Estado;
import com.exceptions.ServiciosException;


@Stateless
public class FormulariosBean implements FormulariosBeanRemote {

	@PersistenceContext
	private EntityManager em;
    public FormulariosBean() {
        // TODO Auto-generated constructor stub
    }
//    @Override
//	public Formulario crear(String nombre , Set<Casilla> lista , Investigador investigador) throws ServiciosException {
//    	Formulario formulario = new Formulario(nombre , lista , investigador);
//		try {
//			System.out.println("Lista pasada en el Bean: " + lista);
//			em.merge(formulario);
//			em.flush();
//			System.out.println("Formulario 1 despu�s del flush: " + formulario);
//			
//		}catch (PersistenceException e) {
//			throw new ServiciosException ("No se pudo crear el formulario: " + formulario.getNombre());
//		}
//		return formulario;
//		
//	}
    @Override
    public Formulario crear(Formulario formulario) throws ServiciosException {
    	System.out.println("Formulario 1 en el Bean: " + formulario);
    	
    	try {
    		em.persist(formulario);
    		em.flush();
    		
    	}catch (PersistenceException e) {
    		throw new ServiciosException ("No se pudo crear el formulario: " + formulario.getNombre());
    	}
    	System.out.println("Formulario 1 despu�s del flush: " + formulario);
    	return formulario;
    	
    }

	@Override
	public Formulario actualizar(Formulario formulario) throws ServiciosException {
		try {
			em.merge(formulario);
			em.flush();
			
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo actualizar el formulario: " + formulario.getNombre());
		}
		return formulario;
		
	}
	
	@Override
	public Formulario obtenerPorId(Long idForm) throws ServiciosException {
		Formulario formulario;
		try {
			formulario = em.find(Formulario.class, idForm);
			}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo encontrar el formulario: ");
		}
		return formulario;
		
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try {
			Formulario formulario = em.find(Formulario.class, id);
			formulario.setEstado(Estado.BORRADO);
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo borrar el formulario");
		}
	}

	@Override
	public List<Formulario> obtenerTodos() {
		List<Formulario> a = em.createNamedQuery("Formulario.obtenerTodos",Formulario.class).getResultList();
		List<Formulario> b = new LinkedList<>();
		for (Formulario f : a)
			{if (f.getEstado() == Estado.HABILITADO)
				b.add(f);
			}
		return b;
	}

	@Override
	public List<Formulario> obtenerTodos(String filtro) {
		TypedQuery<Formulario>query = em.createQuery("SELECT f FROM Formulario f WHERE f.nombre LIKE :nombre", Formulario.class)
				.setParameter("nombre",filtro);
		return query.getResultList();
	}

	@Override
	public void asignarCasilla(Long idFormulario, Long idCasilla) throws ServiciosException {
		try {
			Formulario formulario = em.find(Formulario.class, idFormulario);
			Casilla casilla = em.find(Casilla.class, idCasilla);
			formulario.getCasillas().add(casilla);
			em.flush();
		} catch(PersistenceException e) {
			throw new ServiciosException ("No se pudo asignar la Casilla al Formulario");
		}
	}	
	

}
