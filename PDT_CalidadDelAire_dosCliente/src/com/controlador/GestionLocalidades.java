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

	public Set<String> obtieneDepartamentos() {
    	Set<String> listaDepartamentos = new HashSet<>();
    	try   
	    	{
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
