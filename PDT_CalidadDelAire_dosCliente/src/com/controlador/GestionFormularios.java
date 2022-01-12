package com.controlador;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Formulario;
import com.exceptions.ServiciosException;
import com.services.FormulariosBeanRemote;

public class GestionFormularios {
	public Formulario crearFormulario(Formulario formulario) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/FormulariosBean!com.services.FormulariosBeanRemote";
		FormulariosBeanRemote formularioBean = (FormulariosBeanRemote)
				InitialContext.doLookup(ruta);
		formulario = formularioBean.crear(formulario);
		return formulario;
	}

}
