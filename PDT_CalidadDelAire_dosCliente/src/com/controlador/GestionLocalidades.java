package com.controlador;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class GestionLocalidades {
//	public Set<String> obtieneDepartamentos () throws NamingException 
//	{
//		String ruta = "PDT_CalidadDelAire_dosEJB/LocalidadesBean!com.services.LocalidadesBeanRemote";
//		LocalidadesBeanRemote localidadBean = (LocalidadesBeanRemote)
//				InitialContext.doLookup(ruta);
//		System.out.println("En gestiónLocalidades: " + localidadBean.obtenerDepartamentos());
//		return localidadBean.obtenerDepartamentos();
//		
//	}
//	
//	public Set<String> obtieneLocalidades (String depto) throws NamingException
//	{
//		String ruta = "PDT_CalidadDelAire_dosEJB/LocalidadesBean!com.services.LocalidadesBeanRemote";
//		LocalidadesBeanRemote localidadBean = (LocalidadesBeanRemote)
//				InitialContext.doLookup(ruta);
//		//System.out.println("En gestiónLocalidades: " + localidadBean.obtenerDepartamentos());
//		return localidadBean.listaLocalidades(depto);
//	}

	public Set<String> obtieneDepartamentos() {
    	Set<String> listaDepartamentos = new HashSet<>();
    	try   
	    	{
//    		File file = new File(this.getClass().getClassLoader().getResource("resources/DeptoLocalidad.xml").toURI());
    		File file = new File("Resources/DeptoLocalidad.xml");
//    		File absolute = file.getAbsoluteFile();   		
    		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();  
	    	Document document = documentBuilder.parse(file);  
	    	document.getDocumentElement().normalize();
	    	XPath xPath = XPathFactory.newInstance().newXPath();
	    	String caminoDepartamento = "/Localidades/departamento";
	    	NodeList nodeListDepartamentos = (NodeList) xPath.compile(caminoDepartamento).evaluate(document, XPathConstants.NODESET);
	    	
	    	for (int i=0 ; i<nodeListDepartamentos.getLength() ; i++) 
	    		{
	    		Node nodo = nodeListDepartamentos.item(i);
	    		NamedNodeMap nodeMapNombre = nodo.getAttributes();
	    		String nombreDelItem = nodeMapNombre.item(0).getTextContent();
	    		listaDepartamentos.add(nombreDelItem);
	    		
	    		}
	    	
	    	}   
    	catch (Exception e)  
	    	{  
    			System.out.println(e.getMessage());  
	    	} 
    	return listaDepartamentos;  
    	}
    
   public Set<String> obtieneLocalidades(String depto) 
	{
	Set<String> listaLocalidades = new HashSet<>();
	
	try   
	{
//	File file = new File(this.getClass().getResource("PDT_CalidadDelAire_dosEJB/resources/DeptoLocalidad.xml").toURI());
	File file = new File("Resources/DeptoLocalidad.xml");
	DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();  
	Document document = documentBuilder.parse(file);  
	document.getDocumentElement().normalize();
	XPath xPath = XPathFactory.newInstance().newXPath();
	String caminoDepartamento = "/Localidades/departamento";
	NodeList nodeListDepartamentos = (NodeList) xPath.compile(caminoDepartamento).evaluate(document, XPathConstants.NODESET);
	
	for (int i=0 ; i<nodeListDepartamentos.getLength() ; i++) 
		{
		
		Node nodo = nodeListDepartamentos.item(i);
		NamedNodeMap nodeMapNombre = nodo.getAttributes();
		String nombreDelItem = nodeMapNombre.item(0).getTextContent();
		if (nombreDelItem.equals(depto))
			{
			NodeList localidades = nodo.getChildNodes();
			int largo = localidades.getLength();
			for (int j=0 ; j < largo ;  j++ )
				{
				Node nodeLocalidad = localidades.item(j);
				String localidad = nodeLocalidad.getTextContent();
				listaLocalidades.add(localidad);
				}
			}
		}	
	}   
catch (Exception e)  
	{  
		System.out.println(e.getMessage());  
	}  
	
	return listaLocalidades;
}

	
}
