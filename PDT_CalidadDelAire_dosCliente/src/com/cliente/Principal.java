package com.cliente;

import javax.naming.NamingException;

import com.vista.VentanaLogin;

public class Principal {

	public static void main(String[] args) throws NamingException {
		CargaInicial carga = new CargaInicial();
		carga.cargaInicial();

		System.out.println("Carga inicial completa");
		com.vista.VentanaLogin ventanaLogin = new VentanaLogin();
		ventanaLogin.initialize();
	}

}
