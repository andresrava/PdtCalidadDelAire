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
import com.entities.Formulario;
import com.enumerados.BorradoLogico.Estado;
import com.exceptions.ServiciosException;



@Stateless
public class CasillasBean implements CasillasBeanRemote {
@PersistenceContext
private EntityManager em;

public CasillasBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Casilla crear(Casilla casilla) throws ServiciosException {
		try {
			em.merge(casilla);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo crear la Casilla: " + casilla.getNombre());
		}
		return casilla; 
		
		
	}

	@Override
	public void actualizar(Casilla casilla) throws ServiciosException {
		try {
			em.merge(casilla);
			em.flush();
		}catch (PersistenceException e) {
			throw new ServiciosException ("No se pudo actualizar la Casilla: " + casilla.getNombre());
		} 
	}

//	@Override
//	public void borrar(Long id) throws ServiciosException {
//		try {
//		Casilla casilla = em.find(Casilla.class, id);
//		if (!casilla.getFormularios().isEmpty()) {
//			throw new ServiciosException ("La casilla tiene Formularios asociados, no se pudo borrar");
//		}	
//		else {
//		casilla.setEstado(Estado.BORRADO);
//		}
//		}catch (PersistenceException e) {
//			throw new ServiciosException("No se pudo borrar la casilla");
//		}
//	}

	@Override
	public List<Casilla> obtenerTodasCasillas() {
		TypedQuery<Casilla>query = em.createNamedQuery("Casilla.obtenerTodos", Casilla.class);
		List<Casilla> a = query.getResultList();
		List<Casilla> b = new LinkedList<>();
		for (Casilla c : a)
			{
				if (c.getEstado() == Estado.HABILITADO)
					b.add(c);
			}
		return b;
	}

	@Override
	public List<Casilla> obtenerTodos(String filtro) {
		TypedQuery<Casilla>query = em.createQuery("SELECT c FROM Casilla c WHERE c.nombre LIKE :nombre", Casilla.class)
				.setParameter("nombre",filtro);
		List<Casilla> a = query.getResultList();
		List<Casilla> b = new LinkedList<>();
		for (Casilla c : a)
		{
			if (c.getEstado() == Estado.HABILITADO)
			b.add(c);
		}
		return b;
	}

	@Override
	public List<Casilla> obtenerPorParametro(Long parametro) {
		TypedQuery<Casilla>query = em.createQuery("SELECT c FROM Casilla c WHERE c.parametro LIKE :parametro", Casilla.class)
				.setParameter("parametro",parametro);
		List<Casilla> a = query.getResultList();
		List<Casilla> b = new LinkedList<>();
		for (Casilla c : a)
			{
				if (c.getEstado() == Estado.HABILITADO)
				b.add(c);
			}
		return b;
	}

	@Override
	public List<Casilla> obtenerPorFormulario(Long idFormulario) {
		Formulario formulario = em.find(Formulario.class, idFormulario);
		List<Casilla> lista = formulario.getCasillas();
		List<Casilla> b = new LinkedList<>();
		for (Casilla c : lista)
			{
				if (c.getEstado() == Estado.HABILITADO)
					b.add(c);
			}
		return b;
	}
	
	@Override
	public List<Casilla> obtenerPorEM(Long idEM) {
		EstacionDeMedicion estacion = em.find(EstacionDeMedicion.class, idEM);
		List<Casilla> lista = estacion.getCasillas();
		List<Casilla> b = new LinkedList<>();
		for (Casilla c : lista)
			{
				if (c.getEstado() == Estado.HABILITADO)
					b.add(c);
			}
		return b;
	}

}
