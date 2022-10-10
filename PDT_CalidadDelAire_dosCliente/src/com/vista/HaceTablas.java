package com.vista;

import java.sql.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.entities.Actividad;
import com.entities.Casilla;
import com.entities.EstacionDeMedicion;
import com.entities.Formulario;
import com.entities.Registro;
import com.entities.RegistroBoolean;
import com.entities.RegistroFloat;
import com.entities.RegistroInteger;
import com.entities.RegistroString;
import com.entities.Usuario;
import com.enumerados.BorradoLogico.Estado;

public class HaceTablas {
	
	// Tabla de EM
	public DefaultTableModel haceTablaEM (List<EstacionDeMedicion> estaciones) {
		DefaultTableModel modelo= new DefaultTableModel();
		//crea un array que contiene los nombre de las columnas
		final String[] columnNames = {"Nombre", "Departamento", "Localidad"};
		// insertamos las columnas
		for(int column = 0; column < columnNames.length; column++){
			//agrega las columnas a la tabla
			modelo.addColumn(columnNames[column]);
		}
		// Se crea un array que será una de las filas de la tabla. 
				Object [] fila = new Object[columnNames.length]; 
				// Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
				for (int i=0;i<estaciones.size();i++){
					if (estaciones.get(i).getEstado() == Estado.HABILITADO) {
						String nombre=estaciones.get(i).getNombre(); 
						String departamento=estaciones.get(i).getDepartamento(); 
						String localidad=estaciones.get(i).getLocalidad();
						fila[0] = nombre; 
						fila[1] = departamento; 
						fila[2] = localidad;
						modelo.addRow(fila); 
					}
				}
		 return modelo;
	}
		//Tabla de Casillas
	
	public DefaultTableModel haceTablaCasillas (List<Casilla> casillas) {
		DefaultTableModel modelo= new DefaultTableModel();
		//crea un array que contiene los nombre de las columnas
		final String[] columnNames = {"Nombre", "Parámetro", "Unidad"};
		// insertamos las columnas
		for(int column = 0; column < columnNames.length; column++){
			//agrega las columnas a la tabla
			modelo.addColumn(columnNames[column]);
		}
		// Se crea un array que será una de las filas de la tabla. 
				Object [] fila = new Object[columnNames.length]; 
				// Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
				for (int i=0;i<casillas.size();i++){
					if (casillas.get(i).getEstado() == Estado.HABILITADO) {
						String nombre=casillas.get(i).getNombre(); 
						String parametro=casillas.get(i).getParametro(); 
						String unidad=casillas.get(i).getUnidaDeMedida();
						fila[0] = nombre; 
						fila[1] = parametro; 
						fila[2] = unidad;
						modelo.addRow(fila); 
					}
				}
		 return modelo;
	}
	
	//Tabla de Registros
	
	public DefaultTableModel haceTablaRegistros (List<Registro> registros) {
		DefaultTableModel modelo= new DefaultTableModel();
		//crea un array que contiene los nombre de las columnas
		final String[] columnNames = {"Parámetro", "Unidad", "Valor", "Autor"};
		// insertamos las columnas
		for(int column = 0; column < columnNames.length; column++){
			//agrega las columnas a la tabla
			modelo.addColumn(columnNames[column]);
		}
		// Se crea un array que será una de las filas de la tabla. 
		Object [] fila = new Object[columnNames.length]; 
		// Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
		for (int i=0;i<registros.size();i++){
			
			if (registros.get(i).getEstado() == Estado.HABILITADO) {
				String parametro = registros.get(i).getCasilla().getParametro(); 
				String unidad = registros.get(i).getCasilla().getUnidaDeMedida();
				Actividad actividad = registros.get(i).getActividad();
				Usuario usuario = actividad.getUsuario();
				String autor = usuario.getNombre();
				String valor = "";
				RegistroBoolean registroBoolean = new RegistroBoolean();
				RegistroString registroString = new RegistroString();
				RegistroFloat registroFloat = new RegistroFloat();
				RegistroInteger registroInteger = new RegistroInteger();
				if (registros.get(i) instanceof RegistroBoolean) {
					registroBoolean = (RegistroBoolean)registros.get(i);
					valor = registroBoolean.getValor().toString();
				}
				if (registros.get(i) instanceof RegistroString) {
					registroString = (RegistroString) registros.get(i);
					valor = registroString.getValor();
				}
				if (registros.get(i) instanceof RegistroFloat) {
					registroFloat = (RegistroFloat) registros.get(i);
					valor = registroFloat.getValor().toString();
				}
				if (registros.get(i) instanceof RegistroInteger) {
					registroInteger = (RegistroInteger) registros.get(i);
					valor = registroInteger.getValor().toString();
				}
									
				fila[0] = parametro; 
				fila[1] = unidad; 
				fila[2] = valor;
				fila[3] = autor;
				modelo.addRow(fila); 
			}
		}
		return modelo;
	}

	public DefaultTableModel haceTablaFormularios(List<Formulario> formularios) {
		DefaultTableModel modelo= new DefaultTableModel();
		//crea un array que contiene los nombre de las columnas
		final String[] columnNames = {"Nombre", "Creador"};
		// insertamos las columnas
		for(int column = 0; column < columnNames.length; column++){
			//agrega las columnas a la tabla
			modelo.addColumn(columnNames[column]);
		}
		// Se crea un array que será una de las filas de la tabla. 
		Object [] fila = new Object[columnNames.length]; 
		// Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
		for (int i=0;i<formularios.size();i++){
			String creadorAdmin = "";
			String creadorInvest = "";
			String creador = "";
			if (formularios.get(i).getEstado() == Estado.HABILITADO) {
				String nombre = formularios.get(i).getNombre();
				if (formularios.get(i).getAdministrador() != null) {
					creadorAdmin = formularios.get(i).getAdministrador().getNombre();
				}
				if (formularios.get(i).getInvestigador() != null) {
					creadorInvest = formularios.get(i).getInvestigador().getNombre();							
				}
				creador = creadorInvest + creadorAdmin;
				fila[0] = nombre; 
				fila[1] = creador; 
				modelo.addRow(fila); 
			}
		}
		 return modelo;
	}

	public DefaultTableModel haceTablaUsuarios(List<Usuario> usuarios) {
		DefaultTableModel modelo= new DefaultTableModel();
		//crea un array que contiene los nombre de las columnas
		final String[] columnNames = {"Nombre", "Apellido", "Id Usuario", "correo electrónico" };
		// insertamos las columnas
		for(int column = 0; column < columnNames.length; column++){
			//agrega las columnas a la tabla
			modelo.addColumn(columnNames[column]);
		}
		// Se crea un array que será una de las filas de la tabla. 
		Object [] fila = new Object[columnNames.length]; 
		// Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
		for (int i=0;i<usuarios.size();i++){
			String nombre = usuarios.get(i).getNombre();
			String apellido = usuarios.get(i).getApellido();
			String id = usuarios.get(i).getId().toString();
			String mail = usuarios.get(i).getMail();
			if (usuarios.get(i).getEstado() == Estado.HABILITADO) {
				fila[0] = nombre; 
				fila[1] = apellido; 
				fila[2] = id; 
				fila[3] = mail; 
				modelo.addRow(fila); 
			}
		}
		 return modelo;
	}

	public DefaultTableModel haceTablaActividades(List<Actividad> actividades) {
		DefaultTableModel modelo= new DefaultTableModel();
		//crea un array que contiene los nombre de las columnas
		final String[] columnNames = {"Id", "Formulario", "Usuario", "Fecha" };
		// insertamos las columnas
		for(int column = 0; column < columnNames.length; column++){
			//agrega las columnas a la tabla
			modelo.addColumn(columnNames[column]);
		}
		// Se crea un array que será una de las filas de la tabla. 
		Object [] fila = new Object[columnNames.length]; 
		// Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
		for (int i=0;i<actividades.size();i++){
			Long id = actividades.get(i).getId();
			String formulario = actividades.get(i).getFormulario().getNombre();
			String idUsuario = actividades.get(i).getUsuario().getNombre().toString();
			Date fecha = actividades.get(i).getRegistros().get(0).getFechaHora();
			if (actividades.get(i).getEstado() == Estado.HABILITADO) {
				fila[0] = id; 
				fila[1] = formulario; 
				fila[2] = idUsuario; 
				fila[3] = fecha; 
				modelo.addRow(fila); 
			}
		}
		 return modelo;
	}
}
	
	
	

