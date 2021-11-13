package com.cliente;

import javax.naming.NamingException;

public class Principal {

	public static void main(String[] args) throws NamingException {
		CargaInicial carga = new CargaInicial();
		carga.cargaInicial();

		System.out.println("Carga inicial completa");
		
	}

}
