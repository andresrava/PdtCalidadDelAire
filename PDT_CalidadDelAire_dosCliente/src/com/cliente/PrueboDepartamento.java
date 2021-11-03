package com.cliente;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Departamento;
import com.entities.Departamento.NombresEnum;
import com.exceptions.ServiciosException;
import com.services.DepartamentosBeanRemote;

public class PrueboDepartamento {

	public static void main(String[] args) throws NamingException {
String ruta = "PDT_CalidadDelAire_dosEJB/DepartamentosBean!com.services.DepartamentosBeanRemote";
		
DepartamentosBeanRemote departamentoBean = (DepartamentosBeanRemote)
					InitialContext.doLookup(ruta);
	
		
Departamento departamento = new Departamento(NombresEnum.TREINTA_Y_TRES , "El pago menos occidental");
	
		try {
			departamentoBean.crear(departamento);
			System.out.println("Se creó el Departamento");
			
		} catch (ServiciosException e) {
			System.out.println(e.getMessage());
		}
	}

}
