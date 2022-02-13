package com.controlador;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFileChooser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.entities.Formulario;
import com.entities.Registro;
import com.exceptions.ServiciosException;
import com.services.RegistrosBeanRemote;

public class GestionRegistros {
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
	
	public List<Registro> muestraRegistros(Long idFormulario) throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/RegistrosBean!com.services.RegistrosBeanRemote";
		RegistrosBeanRemote registroBean = (RegistrosBeanRemote)
				InitialContext.doLookup(ruta);
		List<Registro> registros = (List<Registro>) registroBean.obtenerTodosLista(idFormulario);
		System.out.println("El largo de la Result list es: " + ((List) registros).size());
		System.out.println(registros);
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
	
	public void descargaRegistros (List<Registro> registros , JFileChooser f) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String excelFilePath = f.getCurrentDirectory() + "\\Registros" + timeStamp + ".xlsx";

        try {
 
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Registros");
 
            writeHeaderLine(sheet);
 
            writeDataLines( registros, workbook, sheet);
 
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
 
        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
	}
	
	public void descargaRegistrosFormulario(Long idFormulario, JFileChooser f) throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/RegistrosBean!com.services.RegistrosBeanRemote";
		RegistrosBeanRemote registroBean = (RegistrosBeanRemote)
				InitialContext.doLookup(ruta);
		List<Registro> registros = registroBean.obtenerTodosLista(idFormulario);
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String excelFilePath = f.getCurrentDirectory() + "\\Registros" + timeStamp + ".xlsx";

        try {
 
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Registros");
 
            writeHeaderLine(sheet);
 
            writeDataLines( registros, workbook, sheet);
 
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
 
        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
    }
 
    private void writeHeaderLine(XSSFSheet sheet) {
 
        Row headerRow = sheet.createRow(0);
 
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Parámetro");
 
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Valor");
 
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Fecha");
 
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Latitud");
 
        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Longitud");
    }
 	//Original writeDataLines
    private void writeDataLines(List<Registro> result, XSSFWorkbook workbook,
            XSSFSheet sheet) throws SQLException {
        int rowCount = 1;
        int largo = result.size();
        while (rowCount<largo+1) {
        	Registro registro = result.get(rowCount-1);
        	String parametro = registro.getCasilla().getParametro();
        	String valor = registro.getValor();
        	Date fecha = registro.getFechaHora();
        	Float latitud = registro.getLatitud();
        	Float longitud = registro.getLongitud();

            Row row = sheet.createRow(rowCount++);
 
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(parametro);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(valor);
 
            cell = row.createCell(columnCount++);
 
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
            cell.setCellStyle(cellStyle);
 
            cell.setCellValue(fecha);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(latitud);
 
            cell = row.createCell(columnCount);
            cell.setCellValue(longitud);
            
            rowCount =+ rowCount;
 
        }
    }

	

	



	
}
