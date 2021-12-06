package com.controlador;

import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Casilla;
import com.entities.Ciudad;
import com.entities.Ciudad.NombresEnum;
import com.entities.EstacionDeMedicion;
import com.entities.Usuario;
import com.exceptions.ServiciosException;
import com.services.EstacionesDeMedicionBeanRemote;
import com.services.UsuariosBeanRemote;

public class GestionEstaciones {
	public EstacionDeMedicion crearEstacion(EstacionDeMedicion estacion) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/EstacionesDeMedicionBean!com.services.EstacionesDeMedicionBeanRemote";
		EstacionesDeMedicionBeanRemote estacionBean = (EstacionesDeMedicionBeanRemote)
				InitialContext.doLookup(ruta);
		estacion = estacionBean.crear(estacion);
		
		return estacion;
		
	}
	
	public void asignarCasillaAEM ( EstacionDeMedicion estacion , Casilla casilla) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/EstacionesDeMedicionBean!com.services.EstacionesDeMedicionBeanRemote";
		EstacionesDeMedicionBeanRemote estacionBean = (EstacionesDeMedicionBeanRemote)
				InitialContext.doLookup(ruta);
		Long idEstacion = estacion.getId();
		Long idCasilla = casilla.getId();
		estacionBean.asignarCasillaAEM(idEstacion, idCasilla);
		
	}
	
	public EstacionDeMedicion creaLaEstacion(String nombre , String nomCiudad , NombresEnum depto , String coment , Set<Casilla> lista , Usuario usuario) throws NamingException, ServiciosException {
		EstacionDeMedicion estacion = new EstacionDeMedicion();
		
		estacion.setNombre(nombre);
		estacion.setDescripcion(coment);
		Ciudad ciudad = creaCiudad(nomCiudad , depto);
		estacion.setCiudad(ciudad);
		String ruta1 = "PDT_CalidadDelAire_dosEJB/UsuariosBean!com.services.UsuariosBeanRemote";
		UsuariosBeanRemote usuarioBean = (UsuariosBeanRemote)
				InitialContext.doLookup(ruta1);
		usuario = usuarioBean.obtenerPorId(usuario.getId());
		estacion.setUsuario(usuario);
		estacion.setCasillas(lista);
		estacion = crearEstacion(estacion);
//		String ruta2 = "PDT_CalidadDelAire_dosEJB/EstacionesDeMedicionBean!com.services.EstacionesDeMedicionBeanRemote";
//		EstacionesDeMedicionBeanRemote estacionBean = (EstacionesDeMedicionBeanRemote)
//				InitialContext.doLookup(ruta2);
//		estacion = estacionBean.crear(estacion);
		return estacion;
		
	}
	private Ciudad creaCiudad(String nombre , NombresEnum depto) {
		Ciudad ciudad = new Ciudad(nombre , depto);
		GestionCiudades gestionCiudades = new GestionCiudades();
		try {
			gestionCiudades.creaCiudad(ciudad);
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ServiciosException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return ciudad;
	}
}
