package com.controlador;

import java.util.LinkedList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Casilla;
import com.entities.EstacionDeMedicion;
import com.entities.Formulario;
import com.exceptions.ServiciosException;
import com.services.CasillasBeanRemote;
import com.services.EstacionesDeMedicionBeanRemote;

public class GestionCasillas {
	public Casilla crearCasilla(Casilla casilla) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/CasillasBean!com.services.CasillasBeanRemote";
		CasillasBeanRemote casillaBean = (CasillasBeanRemote)
				InitialContext.doLookup(ruta);
		casilla = casillaBean.crear(casilla);
		return casilla;
	}
	
	public List<Casilla> listaCasillas() throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/CasillasBean!com.services.CasillasBeanRemote";
		CasillasBeanRemote casillaBean = (CasillasBeanRemote)
				InitialContext.doLookup(ruta);
		List<Casilla> casillas = casillaBean.obtenerTodasCasillas();

		return casillas;	
	}
	
	public List<Casilla> listaCasillasObligatorias() throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/CasillasBean!com.services.CasillasBeanRemote";
		CasillasBeanRemote casillaBean = (CasillasBeanRemote)
				InitialContext.doLookup(ruta);
		List<Casilla> casillas = casillaBean.obtenerCasillasObligatorias();
		
		return casillas;	
	}
	
	public List<Casilla> listaCasillasOpcionales() throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/CasillasBean!com.services.CasillasBeanRemote";
		CasillasBeanRemote casillaBean = (CasillasBeanRemote)
				InitialContext.doLookup(ruta);
		List<Casilla> casillas = casillaBean.obtenerCasillasOpcionales();
		
		return casillas;	
	}
	
	//Busca casillas a partir de un String nombre
	public List<Casilla> listaCasillas(String nombre) throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/CasillasBean!com.services.CasillasBeanRemote";
		CasillasBeanRemote casillaBean = (CasillasBeanRemote)
				InitialContext.doLookup(ruta);
		List<Casilla> casillas = casillaBean.obtenerTodos("%" + nombre + "%");

		return casillas;
	}
	
	public List<Casilla> listaCasillas(Long idEM) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/EstacionesDeMedicionBean!com.services.EstacionesDeMedicionBeanRemote";
		EstacionesDeMedicionBeanRemote estacionBean = (EstacionesDeMedicionBeanRemote)
				InitialContext.doLookup(ruta);
		EstacionDeMedicion estacion = estacionBean.obtenerPorId(idEM);
		List<Casilla> casillas = estacion.getCasillas();
		return casillas;
	}
	//Revisa si la casilla est� en alg�n formulario. Devuelve la lista con los formularios en que est� la casilla
	public List<Formulario> revisaCasilla(Long idCasilla) throws NamingException {
		List<Formulario> formulariosConCasilla = new LinkedList<Formulario>();
		GestionFormularios gestionFormularios = new GestionFormularios();
		List<Formulario> formularios = gestionFormularios.listaFormularios();
		for (Formulario form : formularios) {
			List<Casilla> casillas = form.getCasillas();
			for (Casilla casilla : casillas) {
				if (casilla.getId() == idCasilla)
					formulariosConCasilla.add(form);
			}			
		}
		
		return formulariosConCasilla;
		
	}

	public void borrarCasilla(Casilla casillaAEliminar) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/CasillasBean!com.services.CasillasBeanRemote";
		CasillasBeanRemote casillaBean = (CasillasBeanRemote)
				InitialContext.doLookup(ruta);
		Long idCasillaAEliminar = casillaAEliminar.getId();
		casillaBean.borrar(idCasillaAEliminar);
	}

	public Casilla obtienePorId(Long id) throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/CasillasBean!com.services.CasillasBeanRemote";
		CasillasBeanRemote casillaBean = (CasillasBeanRemote)
				InitialContext.doLookup(ruta);
		Casilla casilla = casillaBean.ObtienePorId(id);
		return casilla;
	}
	
	
}
