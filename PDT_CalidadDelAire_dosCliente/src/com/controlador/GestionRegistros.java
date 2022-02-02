package com.controlador;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Registro;
import com.exceptions.ServiciosException;
import com.services.RegistrosBeanRemote;

public class GestionRegistros {
	public Registro crearRegistro (Registro registro) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/RegistrosBean!com.services.RegistrosBeanRemote";
		RegistrosBeanRemote registroBean = (RegistrosBeanRemote)
				InitialContext.doLookup(ruta);
		registro = registroBean.crear(registro);
		return registro;
		
	}
}
