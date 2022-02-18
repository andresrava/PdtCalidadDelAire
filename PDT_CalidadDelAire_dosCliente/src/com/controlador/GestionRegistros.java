package com.controlador;

import java.sql.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Actividad;
import com.entities.Casilla;
import com.entities.Registro;
import com.entities.RegistroBoolean;
import com.entities.RegistroFloat;
import com.entities.RegistroInteger;
import com.entities.RegistroString;
import com.exceptions.ServiciosException;
import com.services.RegistrosBeanRemote;

public class GestionRegistros {
	
	GestionCasillas gestionCasillas = new GestionCasillas();
	GestionActividades gestionActividades = new GestionActividades();
	
	public Registro crearRegistro (Registro registro) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/RegistrosBean!com.services.RegistrosBeanRemote";
		RegistrosBeanRemote registroBean = (RegistrosBeanRemote)
				InitialContext.doLookup(ruta);
		registro = registroBean.crear(registro); 
		return registro;
		
	}
	
	public Registro actualizarRegistro (Registro registro) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/RegistrosBean!com.services.RegistrosBeanRemote";
		RegistrosBeanRemote registroBean = (RegistrosBeanRemote)
				InitialContext.doLookup(ruta);
		registro = registroBean.actualizar(registro); 
		return registro;
		
	}
	
	public List<Registro> muestraRegistrosPorFormulario(Long idFormulario) throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/RegistrosBean!com.services.RegistrosBeanRemote";
		RegistrosBeanRemote registroBean = (RegistrosBeanRemote)
				InitialContext.doLookup(ruta);
		List<Registro> registros = (List<Registro>) registroBean.obtenerPorFormulario(idFormulario);
		return  registros;
		}
	
	public List<Registro> muestraRegistrosPorCasilla(Long idCasilla) throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/RegistrosBean!com.services.RegistrosBeanRemote";
		RegistrosBeanRemote registroBean = (RegistrosBeanRemote)
				InitialContext.doLookup(ruta);
		List<Registro> registros = (List<Registro>) registroBean.obtenerPorCasilla(idCasilla);
		return  registros;
	}
	
	public Registro encuentraPorId(Long id) throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/RegistrosBean!com.services.RegistrosBeanRemote";
		RegistrosBeanRemote registroBean = (RegistrosBeanRemote)
				InitialContext.doLookup(ruta);
		Registro registro = registroBean.encuentraPorId(id);
		return registro;
	} 
   
	public List<Registro> encuentraPorFechas(Date dateDesde, Date dateHasta) throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/RegistrosBean!com.services.RegistrosBeanRemote";
		RegistrosBeanRemote registroBean = (RegistrosBeanRemote)
				InitialContext.doLookup(ruta);
		List<Registro> lista = registroBean.obtenerTodos(dateDesde , dateHasta);
		return lista;
	}

	public RegistroString crearRegistroString(RegistroString registro1) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/RegistrosBean!com.services.RegistrosBeanRemote";
		RegistrosBeanRemote registroBean = (RegistrosBeanRemote)
				InitialContext.doLookup(ruta);
		registro1 = registroBean.crearString(registro1); 
		return registro1;
	}

	public RegistroInteger crearRegistroInteger(RegistroInteger registro2) throws ServiciosException, NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/RegistrosBean!com.services.RegistrosBeanRemote";
		RegistrosBeanRemote registroBean = (RegistrosBeanRemote)
				InitialContext.doLookup(ruta);
		registro2 = registroBean.crearInteger(registro2); 
		return registro2;
	}

	public RegistroFloat crearRegistroFloat(RegistroFloat registro3) throws ServiciosException, NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/RegistrosBean!com.services.RegistrosBeanRemote";
		RegistrosBeanRemote registroBean = (RegistrosBeanRemote)
				InitialContext.doLookup(ruta);
		Casilla casilla = registro3.getCasilla();
		casilla = gestionCasillas.obtienePorId(casilla.getId());
		registro3.setCasilla(casilla);
		Actividad actividad = registro3.getActividad();
		System.out.println("Id de actividad: " + actividad.getId());
		actividad = gestionActividades.obtienePorId(actividad.getId());
		registro3.setActividad(actividad);
		registro3 = registroBean.crearFloat(registro3); 
		return registro3;
	}

	public RegistroBoolean crearRegistroBoolean(RegistroBoolean registro4) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/RegistrosBean!com.services.RegistrosBeanRemote";
		RegistrosBeanRemote registroBean = (RegistrosBeanRemote)
				InitialContext.doLookup(ruta);
		registro4 = registroBean.crearBoolean(registro4); 
		return registro4;
	}
	
	
//	public void descargaRegistrosFormulario(Long idFormulario, JFileChooser f) throws NamingException {
//		String ruta = "PDT_CalidadDelAire_dosEJB/RegistrosBean!com.services.RegistrosBeanRemote";
//		RegistrosBeanRemote registroBean = (RegistrosBeanRemote)
//				InitialContext.doLookup(ruta);
//		List<Registro> registros = registroBean.obtenerTodosLista(idFormulario);
//		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
//        String excelFilePath = f.getCurrentDirectory() + "\\Registros" + timeStamp + ".xlsx";
//
//        try {
// 
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            XSSFSheet sheet = workbook.createSheet("Registros");
// 
//            writeHeaderLine(sheet);
// 
//            writeDataLines( registros, workbook, sheet);
// 
//            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
//            workbook.write(outputStream);
//            workbook.close();
// 
//        } catch (SQLException e) {
//            System.out.println("Datababse error:");
//            e.printStackTrace();
//        } catch (IOException e) {
//            System.out.println("File IO error:");
//            e.printStackTrace();
//        }
//    }
 
  

	

	



	
}
