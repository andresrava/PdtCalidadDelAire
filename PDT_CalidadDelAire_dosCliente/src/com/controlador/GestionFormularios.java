package com.controlador;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Formulario;
import com.exceptions.ServiciosException;
import com.services.FormulariosBeanRemote;

public class GestionFormularios {
	private String ruta = "PDT_CalidadDelAire_dosEJB/FormulariosBean!com.services.FormulariosBeanRemote";
	
	public Formulario crearFormulario(Formulario formulario) throws NamingException, ServiciosException {
		FormulariosBeanRemote formularioBean = (FormulariosBeanRemote)
				InitialContext.doLookup(ruta);
		formulario = formularioBean.crear(formulario);
		return formulario;
	}
	
	public List<Formulario> listaFormularios() throws NamingException {
		FormulariosBeanRemote formularioBean = (FormulariosBeanRemote)
				InitialContext.doLookup(ruta);
		List<Formulario> formularios = formularioBean.obtenerTodos();
		return formularios;
		
	}

}
