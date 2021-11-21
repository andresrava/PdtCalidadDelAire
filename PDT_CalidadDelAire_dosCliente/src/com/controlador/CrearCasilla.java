package com.controlador;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Casilla;
import com.exceptions.ServiciosException;
import com.services.CasillasBeanRemote;

public class CrearCasilla {

		public Casilla crearCasilla(Casilla casilla) throws NamingException, ServiciosException {
			String ruta = "PDT_CalidadDelAire_dosEJB/CasillasBean!com.services.CasillasBeanRemote";
			CasillasBeanRemote casillaBean = (CasillasBeanRemote)
					InitialContext.doLookup(ruta);
			casillaBean.crear(casilla);
			return casilla;
		}
}
