package com.controlador;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.exceptions.ServiciosException;
import com.services.CasillasBeanRemote;

public class BorraCasilla {
	public void borraCasilla(Long idCasilla) throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/CasillasBean!com.services.CasillasBeanRemote";
		CasillasBeanRemote casillaBean = (CasillasBeanRemote)
				InitialContext.doLookup(ruta);
			
		try {
			casillaBean.borrar(idCasilla);
			
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
