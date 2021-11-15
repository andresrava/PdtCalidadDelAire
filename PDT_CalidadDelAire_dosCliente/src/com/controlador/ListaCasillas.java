package com.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Casilla;
import com.exceptions.ServiciosException;
import com.services.CasillasBeanRemote;

public class ListaCasillas {
	
	public List<Casilla> listaCasillas() throws NamingException {
	String ruta = "PDT_CalidadDelAire_dosEJB/CasillasBean!com.services.CasillasBeanRemote";
	CasillasBeanRemote casillaBean = (CasillasBeanRemote)
			InitialContext.doLookup(ruta);
	List<Casilla> casillas = new ArrayList<>();
	casillas = casillaBean.obtenerTodos();
	return casillas;
	}
}
