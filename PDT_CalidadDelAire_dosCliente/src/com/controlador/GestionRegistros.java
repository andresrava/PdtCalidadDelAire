package com.controlador;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
	
	public List<Registro> muestraRegistros(Long idFormulario) throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/RegistrosBean!com.services.RegistrosBeanRemote";
		RegistrosBeanRemote registroBean = (RegistrosBeanRemote)
				InitialContext.doLookup(ruta);
		List<Registro> registros = registroBean.obtenerTodosLista(idFormulario);
		return  registros;
		}
//	public void descargaRegistrosFormulario(Long idFormulario) throws NamingException {
//		String ruta = "PDT_CalidadDelAire_dosEJB/RegistrosBean!com.services.RegistrosBeanRemote";
//		RegistrosBeanRemote registroBean = (RegistrosBeanRemote)
//				InitialContext.doLookup(ruta);
//		List<Registro> registros = registroBean.obtenerTodosLista(idFormulario);
//        String excelFilePath = "Reviews-export.xlsx";
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
// 
//    private void writeHeaderLine(XSSFSheet sheet) {
// 
//        Row headerRow = sheet.createRow(0);
// 
//        Cell headerCell = headerRow.createCell(0);
//        headerCell.setCellValue("Parámetro");
// 
//        headerCell = headerRow.createCell(1);
//        headerCell.setCellValue("Valor");
// 
//        headerCell = headerRow.createCell(2);
//        headerCell.setCellValue("Fecha");
// 
//        headerCell = headerRow.createCell(3);
//        headerCell.setCellValue("Latitud");
// 
//        headerCell = headerRow.createCell(4);
//        headerCell.setCellValue("Longitud");
//    }
// 	Original writeDataLines
//    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
//            XSSFSheet sheet) throws SQLException {
//        int rowCount = 1;
// 
//        while (result.next()) {
//            String parametro = result.getString("CASILLA_FK");
//            String valor = result.getString("VALORSTRING");
//            Timestamp fecha = result.getTimestamp("FECHAHORA");
//            float latitud = result.getFloat("LATITUD");
//            float longitud = result.getFloat("LONGITUD");
//            
//            Row row = sheet.createRow(rowCount++);
// 
//            int columnCount = 0;
//            Cell cell = row.createCell(columnCount++);
//            cell.setCellValue(parametro);
// 
//            cell = row.createCell(columnCount++);
//            cell.setCellValue(valor);
// 
//            cell = row.createCell(columnCount++);
// 
//            CellStyle cellStyle = workbook.createCellStyle();
//            CreationHelper creationHelper = workbook.getCreationHelper();
//            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
//            cell.setCellStyle(cellStyle);
// 
//            cell.setCellValue(fecha);
// 
//            cell = row.createCell(columnCount++);
//            cell.setCellValue(latitud);
// 
//            cell = row.createCell(columnCount);
//            cell.setCellValue(longitud);
// 
//        }
//    } 
//    private ArrayList<Registro> result;
//    
//    private void writeDataLines(LinkedList result, XSSFWorkbook workbook,
//            XSSFSheet sheet) throws SQLException {
//        int rowCount = 1;
// 
//        while (true) {
//        	Registro
//            String parametro = result.get(0).get("CASILLA_FK");
//            String valor = result.getString("VALORSTRING");
//            Timestamp fecha = result.getTimestamp("FECHAHORA");
//            float latitud = result.getFloat("LATITUD");
//            float longitud = result.getFloat("LONGITUD");
//            
//            Row row = sheet.createRow(rowCount++);
// 
//            int columnCount = 0;
//            Cell cell = row.createCell(columnCount++);
//            cell.setCellValue(parametro);
// 
//            cell = row.createCell(columnCount++);
//            cell.setCellValue(valor);
// 
//            cell = row.createCell(columnCount++);
// 
//            CellStyle cellStyle = workbook.createCellStyle();
//            CreationHelper creationHelper = workbook.getCreationHelper();
//            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
//            cell.setCellStyle(cellStyle);
// 
//            cell.setCellValue(fecha);
// 
//            cell = row.createCell(columnCount++);
//            cell.setCellValue(latitud);
// 
//            cell = row.createCell(columnCount);
//            cell.setCellValue(longitud);
// 
//        }
//    } 
//    

	
}
