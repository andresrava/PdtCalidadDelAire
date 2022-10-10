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
import com.entities.Usuario;
import com.enumerados.BorradoLogico.Estado;
import com.exceptions.ServiciosException;


@Stateless
public class FormulariosBean implements FormulariosBeanRemote {

	@PersistenceContext
	private EntityManager em;
    public FormulariosBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Formulario crear(Formulario formulario) throws ServiciosException {
    	System.out.println("Formulario en el Bean: " + formulario);
    	
    	try {
    		em.persist(formulario);
    		em.flush();
    		
    	}catch (PersistenceException e) {
    		throw new ServiciosException ("No se pudo crear el formulario: " + formulario.getNombre());
    	}
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
		if (formulario.getEstado() == Estado.HABILITADO)
			return formulario;
		else
			return null;
		
	}

	@Override
	public void borrar(Long id) throws ServiciosException {
		try {
			Formulario formulario = em.find(Formulario.class, id);
			formulario.setEstado(Estado.BORRADO);
			em.flush();
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
		List<Formulario> formulariosHabilitados = new LinkedList<Formulario>();
		for (Formulario f : query.getResultList()) 
			if (f.getEstado() == Estado.HABILITADO)
				formulariosHabilitados.add(f);
		return formulariosHabilitados;
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

	@Override
	public List<Formulario> obtenerPorNombre(String nombreFormulario) {
		TypedQuery<Formulario>query = em.createQuery("SELECT c FROM Formulario c WHERE c.nombre LIKE :nombre", Formulario.class)
				.setParameter("nombre",nombreFormulario);
		List<Formulario> a = query.getResultList();
		List<Formulario> b = new LinkedList<>();
		for (Formulario c : a)
		{
			if (c.getEstado() == Estado.HABILITADO)
			b.add(c);
		}
		return b;
	}

	@Override
	public Formulario asignarUsuario(Long idFormulario, Long idUsuario) throws ServiciosException {
		Formulario formulario = em.find(Formulario.class, idFormulario);
		Usuario usuario = em.find(Usuario.class, idUsuario);
		if (formulario.getEstado() == Estado.HABILITADO && usuario.getEstado() == Estado.HABILITADO) {
			formulario.getUsuarios().add(usuario);
			em.flush();
		}
		return formulario;
	}

	
	

}
