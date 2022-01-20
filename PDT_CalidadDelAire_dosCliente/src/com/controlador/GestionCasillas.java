package com.controlador;

import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Casilla;
import com.entities.EstacionDeMedicion;
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
	
	
}
