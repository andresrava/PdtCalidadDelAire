package com.cliente;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Aficionado;
import com.exceptions.ServiciosException;
import com.services.AficionadosBeanRemote;

public class PrueboAficionado {

	public static void main(String[] args) throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/AficionadosBean!com.services.AficionadosBeanRemote";
		
		AficionadosBeanRemote aficionadoBean = (AficionadosBeanRemote)
							InitialContext.doLookup(ruta);
			

				Aficionado aficionado = new Aficionado( "Aficionado" ,"Einstein" , "AficinadoMail", "AficionadoClave"  );
				System.out.println(aficionado.toString());
				try {
					aficionadoBean.crear(aficionado);
					System.out.println("Se creó el Aficionado");
					
				} catch (ServiciosException e) {
					System.out.println(e.getMessage());
				}
			}


}
