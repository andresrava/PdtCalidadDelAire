package com.controlador;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.entities.Actividad;
import com.entities.Formulario;
import com.entities.Registro;
import com.entities.RegistroBoolean;
import com.entities.RegistroFloat;
import com.entities.RegistroInteger;
import com.entities.RegistroString;
import com.entities.Usuario;
import com.enumerados.Enumerados.Booleano;

public class GestionIO {
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
	        	
	        	Date fecha = registro.getFechaHora();
	        	Float latitud = registro.getLatitud();
	        	Float longitud = registro.getLongitud();
	        	
	        	

	            Row row = sheet.createRow(rowCount++);
	 
	            int columnCount = 0;
	            Cell cell = row.createCell(columnCount++);
	            cell.setCellValue(parametro);
	 
	            cell = row.createCell(columnCount++);
	            //*********************
	            if (registro instanceof RegistroString)
        		{
	            	String valor = ((RegistroString) registro).getValor();
	            	cell.setCellValue(valor);
        		}
	            
	            //*********************
	            if (registro instanceof RegistroFloat)
	            {
	            	Double valor = ((RegistroFloat) registro).getValor();
	            	cell.setCellValue(valor);
	            }
	            
	            //*********************
	            if (registro instanceof RegistroInteger)
	            {
	            	Integer valor = ((RegistroInteger) registro).getValor();
	            	cell.setCellValue(valor);
	            }
	            
	            //*********************
	            if (registro instanceof RegistroBoolean)
	            {
	            	Booleano valor = ((RegistroBoolean) registro).getValor();
	            	cell.setCellValue(valor.toString());
	            }
	            
	            
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

		public void cargarRegistros(Usuario usuarioLoged, Formulario form, JFileChooser f) {
			Actividad actividad = new Actividad();
			actividad.setUsuario(usuarioLoged);
			actividad.setFormulario(form);
			try
	        {
	            FileInputStream file = new FileInputStream(f.getSelectedFile());
	            JOptionPane.showMessageDialog(null, "Llegué hasta acá", "Atención!" , JOptionPane.WARNING_MESSAGE);
	            System.out.println("Llegué hasta acá!");
	        }catch(Exception e)
	        {
				
			}
		}
	
}
