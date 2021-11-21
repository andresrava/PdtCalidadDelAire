package com.controlador;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.EstacionDeMedicion;
import com.exceptions.ServiciosException;
import com.services.CasillasBeanRemote;

public class AsignaCasillaAEstacion {
	public EstacionDeMedicion asignaCasillaAEstacion( Long idEstMedic , Long idCasilla ) throws ServiciosException, NamingException {
		String ruta1 = "PDT_CalidadDelAire_dosEJB/CasillasBean!com.services.CasillasBeanRemote";
		CasillasBeanRemote casillaBean = (CasillasBeanRemote)
				InitialContext.doLookup(ruta1);
		
		String ruta2 = "PDT_CalidadDelAire_dosEJB/EstacionesDeMedicionBean!com.services.EstacionesDeMedicionBeanRemote";
		
		return null;
		
	}
}
