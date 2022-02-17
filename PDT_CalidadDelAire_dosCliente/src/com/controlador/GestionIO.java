package com.controlador;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.entities.Actividad;
import com.entities.Casilla;
import com.entities.Formulario;
import com.entities.Registro;
import com.entities.RegistroBoolean;
import com.entities.RegistroFloat;
import com.entities.RegistroInteger;
import com.entities.RegistroString;
import com.entities.Usuario;
import com.enumerados.BorradoLogico.*;

public class GestionIO {
	public static boolean descargaRegistros (List<Registro> registros , JFileChooser f) {
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
            return true;
            
        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
            return false;
        }
	}
	  private static void writeHeaderLine(XSSFSheet sheet) {
		  
	        Row headerRow = sheet.createRow(0);
	 
	        Cell headerCell = headerRow.createCell(0);
	        headerCell.setCellValue("Par�metro");
	 
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
	    private static void writeDataLines(List<Registro> result, XSSFWorkbook workbook,
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

		public static void cargarRegistros(Usuario usuarioLoged, Formulario form, JFileChooser f) {
			
			try
	        {
	            FileInputStream file = new FileInputStream(f.getSelectedFile());
	            GestionActividades gestionActividades = new GestionActividades();
	            GestionRegistros gestionRegistros = new GestionRegistros();
	            Workbook workbook = new XSSFWorkbook(file);
	            int actividades = workbook.getNumberOfSheets();		//Cantidad de actividades (hojas) en la carga
	            List<Casilla> casillas = form.getCasillas();
	            int campos = casillas.size();						//Cantidad de campos en cada formulario
	           
	            for (int i=0 ; i < actividades ; i++) {
	            	Actividad actividad = new Actividad();
	            	actividad.setUsuario(usuarioLoged);
	    			actividad.setFormulario(form);
	    			actividad.setIngreso(Ingreso.MASIVO);
	    			actividad = gestionActividades.crearActividad(actividad);
	    			
	    			Sheet sheet = workbook.getSheetAt(i);
		            
		            for ( int j=1 ; j-1<campos ; j++) {
		            	Row row = sheet.getRow(j);
			            Cell cell1 = row.getCell(1);
			            switch (casillas.get(j-1).getTipoDeDato().toString()) {
		                        case "STRING":
		                        	System.out.println("Entr� a String");
		                            RegistroString registroS = new RegistroString();
		                            registroS.setValor(cell1.getStringCellValue());
		                            registroS.setActividad(actividad);
		                            registroS.setCasilla(casillas.get(j-1));
		                            registroS.setFechaHora(dameSql(row.getCell(2)));
		                            registroS.setLatitud((float) row.getCell(3).getNumericCellValue());
		                            registroS.setLongitud((float) row.getCell(4).getNumericCellValue());
		                            registroS = gestionRegistros.crearRegistroString(registroS);
		                            actividad = gestionActividades.agregaRegistro(actividad.getId(), registroS.getId());
		                            break;
		                        case "BOOLEAN":
		                        	System.out.println("Entr� a Boolean");
		                            RegistroBoolean registroB = new RegistroBoolean();
		                            System.out.println("El stringCellValue es: " + cell1.toString());
//		                            String valorBoolean = cell1.getStringCellValue().toString();
		                            String valorBoolean = cell1.toString();
		                            String verdadero = "TRUE";
		                        	if (valorBoolean == verdadero)
		                        	{
		                        		registroB.setValor(Booleano.TRUE);
		                        		System.out.println("Ahora desde el registro: " + registroB.getValor());
		                        	}
		                        	String falso = "FALSE";
		                        	if (valorBoolean == falso)
		                        	{
		                        		registroB.setValor(Booleano.FALSE);
		                        		System.out.println("Ahora desde el registro: " + registroB.getValor());
		                        	}
		                        	System.out.println("Registro boolean: " + registroB.getValor());
		                            registroB.setActividad(actividad);
		                            registroB.setCasilla(casillas.get(j-1));
		                            registroB.setFechaHora(dameSql(row.getCell(2)));
		                            registroB.setLatitud((float) row.getCell(3).getNumericCellValue());
		                            registroB.setLongitud((float) row.getCell(4).getNumericCellValue());
		                            registroB = gestionRegistros.crearRegistroBoolean(registroB);
		                            actividad = gestionActividades.agregaRegistro(actividad.getId(), registroB.getId());
		                            break;
		                        case "INTEGER":
		                        	System.out.println("Entr� a Integer");
		                            RegistroInteger registroI = new RegistroInteger();
		                            registroI.setValor((int) cell1.getNumericCellValue());
		                            registroI.setActividad(actividad);
		                            registroI.setCasilla(casillas.get(j-1));
		                            registroI.setFechaHora(dameSql(row.getCell(2)));
		                            registroI.setLatitud((float) row.getCell(3).getNumericCellValue());
		                            registroI.setLongitud((float) row.getCell(4).getNumericCellValue());
		                            registroI = gestionRegistros.crearRegistroInteger(registroI);
		                            actividad = gestionActividades.agregaRegistro(actividad.getId(), registroI.getId());
		                            break;
		                        case "FLOAT":
		                        	System.out.println("Entr� a Float");
		                        	System.out.println("la celda tiene: " + cell1.getNumericCellValue());
		                            RegistroFloat registroF = new RegistroFloat();
		                            registroF.setValor( cell1.getNumericCellValue());
		                            registroF.setActividad(actividad);
		                            registroF.setCasilla(casillas.get(j-1));
		                            registroF.setFechaHora(dameSql(row.getCell(2))); 
		                            registroF.setLatitud((float) row.getCell(3).getNumericCellValue());
		                            registroF.setLongitud((float) row.getCell(4).getNumericCellValue());
		                            registroF = gestionRegistros.crearRegistroFloat(registroF);
		                            actividad = gestionActividades.agregaRegistro(actividad.getId(), registroF.getId());
		                            break;
		                    }
		            }
	            }
	             
	            workbook.close();
	            file.close();
	        }catch(Exception e)
	        {
	        	JOptionPane.showMessageDialog(null, "Mal archivo", "Atenci�n!" , JOptionPane.WARNING_MESSAGE);
	            e.printStackTrace();	
			}
		}
		static Date dameSql(Cell celda) {
			LocalDateTime dateTime = celda.getLocalDateTimeCellValue();
            Date sqlDate = Date.valueOf(dateTime.toLocalDate());
			return sqlDate;
        	
        }
	
}
