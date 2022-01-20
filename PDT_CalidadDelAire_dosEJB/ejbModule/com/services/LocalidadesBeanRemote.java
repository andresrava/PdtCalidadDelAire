package com.services;

import java.util.Set;

import javax.ejb.Remote;

@Remote

public interface LocalidadesBeanRemote {
	 Set<String> obtenerDepartamentos();
	 Set<String> listaLocalidades(String depto);
}
