package com.vista;

import java.awt.EventQueue;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.controlador.GestionActividades;
import com.controlador.GestionRegistros;
import com.entities.Actividad;
import com.entities.Casilla;
import com.entities.Casilla.TipoDatoEnum;
import com.entities.Formulario;
import com.entities.Registro;
import com.entities.Usuario;
import com.enumerados.BorradoLogico.Booleano;
import com.enumerados.BorradoLogico.Obligatoria;
import com.exceptions.ServiciosException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JInternalFrame;

public class VentanaLlenaFormulario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaLlenaFormulario() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLlenaFormulario frame = new VentanaLlenaFormulario(usuarioLoged , formularioElegido);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static Usuario usuarioLoged;
	private static Formulario formularioElegido;
	private JButton btnVolver;
	private JButton btnIngresar;
	
	private JTextField text4;
	private JLabel lbl0;
	private JTextField text0;
	private JTextField text2;
	private JLabel lbl1;
	private JTextField text1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JTextField text3;
	private JLabel lbl4;
	private JLabel lbl5;
	private JTextField text5;
	private JLabel lbl6;
	private JTextField text6;
	private JLabel lbl7;
	private JTextField text7;
	private JLabel lblFecha;
	private JInternalFrame internalFrame;
	private JLabel lblLatitud;
	private JTextField textLatitud;
	private JLabel lblLongitud;
	private JTextField textLongitud;
	
	
	/**
	 * Create the frame.
	 */
	public VentanaLlenaFormulario(Usuario usuarioLogedRef , Formulario formularioElegidoRef) {
		setTitle("Completa Actividad de Campo");
		VentanaLlenaFormulario.usuarioLoged = usuarioLogedRef;
		VentanaLlenaFormulario.formularioElegido = formularioElegidoRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("20px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				ColumnSpec.decode("424px:grow"),},
			new RowSpec[] {
				RowSpec.decode("24px"),
				RowSpec.decode("14px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				RowSpec.decode("31px"),
				RowSpec.decode("14px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		String nombreDelUsuario = usuarioLogedRef.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreDelUsuario);
		contentPane.add(lblNewLabel, "4, 2, fill, top");
		
		String nombreFormulario = formularioElegido.getNombre();
		JLabel lblNewLabel_1 = new JLabel("Formulario: " + nombreFormulario);
		contentPane.add(lblNewLabel_1, "4, 4, fill, top");
		
		List<Casilla> casillas = formularioElegido.getCasillas();
		
		lblLatitud = new JLabel("Latitud: (*)");
		contentPane.add(lblLatitud, "3, 26, right, default");
		
		textLatitud = new JTextField();
		contentPane.add(textLatitud, "4, 26, fill, default");
		textLatitud.setColumns(10);
		
		lblLongitud = new JLabel("Longitud:(*)");
		contentPane.add(lblLongitud, "3, 28, right, default");
		
		textLongitud = new JTextField();
		contentPane.add(textLongitud, "4, 28, fill, default");
		textLongitud.setColumns(10);
		
		//Implemantando el manejo de la fecha
		lblFecha = new JLabel("Fecha: (*)");
		contentPane.add(lblFecha, "3, 32, right, default");
		internalFrame = new JInternalFrame("New JInternalFrame");
		contentPane.add(internalFrame, "4, 32");
		internalFrame.setVisible(true);
		UtilDateModel model = new UtilDateModel();
//		model.setDate(defaultCloseOperation, defaultCloseOperation, defaultCloseOperation);

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		 
		internalFrame.getContentPane().add(datePicker);
		
		
		
		btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				try {
					VentanaCreaActividad ventanaCreaActividad = new VentanaCreaActividad(usuarioLoged);
					ventanaCreaActividad.ventanaCreaActividad();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		text0 = new JTextField();
		text0.setVisible(false);
		text0.setColumns(10);
		if (casillas.size() > 0) {
			String etiqueta = casillas.get(0).getParametro() + ": ";
			if (casillas.get(0).getObligatoria() == Obligatoria.SI)
				etiqueta = etiqueta + "(*)";
			lbl0 = new JLabel(etiqueta);
			contentPane.add(lbl0, "3, 8, right, default");
			contentPane.add(text0, "4, 8, fill, default");
			text0.setVisible(true);
		}
		
		text1 = new JTextField();
		text1.setVisible(false);
		text1.setColumns(10);
		if (casillas.size() > 1) {
			String etiqueta = casillas.get(1).getParametro() + ": ";
			if (casillas.get(1).getObligatoria() == Obligatoria.SI)
				etiqueta = etiqueta + "(*)";
			lbl1 = new JLabel(etiqueta);
			contentPane.add(lbl1, "3, 10, right, default");
			contentPane.add(text1, "4, 10, fill, default");
			text1.setVisible(true);
		}
		
		text2 = new JTextField();
		text2.setVisible(false);
		text2.setColumns(10);
		if (casillas.size() > 2) {
			String etiqueta = casillas.get(2).getParametro() + ": ";
			if (casillas.get(2).getObligatoria() == Obligatoria.SI)
				etiqueta = etiqueta + "(*)";
			lbl2 = new JLabel(etiqueta);
			contentPane.add(lbl2, "3, 12, right, default");
			contentPane.add(text2, "4, 12, fill, default");
			text2.setVisible(true);
		}
		
		text3 = new JTextField();
		text3.setVisible(false);
		text3.setColumns(10);
		if (casillas.size() > 3) {
			String etiqueta = casillas.get(3).getParametro() + ": ";
			if (casillas.get(3).getObligatoria() == Obligatoria.SI)
				etiqueta = etiqueta + "(*)";
			lbl3 = new JLabel(etiqueta);
			contentPane.add(lbl3, "3, 14, right, default");
			contentPane.add(text3, "4, 14, fill, default");
			text3.setVisible(true);
		}
		
		text4 = new JTextField();
		text4.setVisible(false);
		text4.setColumns(10);
		if (casillas.size() > 4) {
			String etiqueta = casillas.get(4).getParametro() + ": ";
			if (casillas.get(4).getObligatoria() == Obligatoria.SI)
				etiqueta = etiqueta + "(*)";
			lbl4 = new JLabel(etiqueta);contentPane.add(lbl4, "3, 16, right, default");
			contentPane.add(text4, "4, 16, fill, default");
			text4.setVisible(true);
		}
		
		text5 = new JTextField();
		text5.setVisible(false);
		text5.setColumns(10);
		if (casillas.size() > 5) {
			String etiqueta = casillas.get(5).getParametro() + ": ";
			if (casillas.get(5).getObligatoria() == Obligatoria.SI)
				etiqueta = etiqueta + "(*)";
			lbl5 = new JLabel(etiqueta);
			contentPane.add(lbl5, "3, 18, right, default");
			contentPane.add(text5, "4, 18, fill, default");
			text5.setVisible(true);
		}
		
		text6 = new JTextField();
		text6.setVisible(false);
		text6.setColumns(10);
		if (casillas.size() > 6) {
			String etiqueta = casillas.get(6).getParametro() + ": ";
			if (casillas.get(6).getObligatoria() == Obligatoria.SI)
				etiqueta = etiqueta + "(*)";
			lbl6 = new JLabel(etiqueta);
			contentPane.add(lbl6, "3, 20, right, default");
			contentPane.add(text6, "4, 20, fill, default");
			text6.setVisible(true);
		}
		
		text7 = new JTextField();
		text7.setVisible(false);
		text7.setColumns(10);
		if (casillas.size() > 7) {
			String etiqueta = casillas.get(7).getParametro() + ": ";
			if (casillas.get(7).getObligatoria() == Obligatoria.SI)
				etiqueta = etiqueta + "(*)";
			lbl7 = new JLabel(etiqueta);
			contentPane.add(lbl7, "3, 22, right, default");
			contentPane.add(text7, "4, 22, fill, default");
			text7.setVisible(true);
		}
		
		List<JTextField> campos = new LinkedList<>();
		campos.add(text0);
		campos.add(text1);
		campos.add(text2);
		campos.add(text3);
		campos.add(text4);
		campos.add(text5);
		campos.add(text6);
		campos.add(text7);
		for (JTextField t : campos) {
			System.out.println("Campo " + t + " : " + t.getText());
		}
		int largo = formularioElegido.getCasillas().size();
		
		System.out.println("Largo de las casillas: " + largo);

		contentPane.add(btnVolver, "3, 30");
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				JOptionPane.showMessageDialog(null, "No se ha implementado esta funcionalidad","Error", JOptionPane.WARNING_MESSAGE);
				Actividad actividad = new Actividad();
				actividad.setUsuario(usuarioLoged);
				actividad.setFormulario(formularioElegido);
				GestionActividades gestionActividades = new GestionActividades();
				GestionRegistros gestionRegistros = new GestionRegistros();
				try {
					actividad = gestionActividades.crearActividad(actividad);
					System.out.println("Actividad: " + actividad);
					Float latitud = Float.parseFloat(textLatitud.getText());
					Float longitud = Float.parseFloat(textLongitud.getText());
					 java.sql.Date selectedDate = new java.sql.Date(((java.util.Date) datePicker.getModel().getValue()).getTime());
					for (int i=0 ; i<largo ; i++) {
						Registro registro = new Registro();
						
						System.out.println("Entro al for" + i);
						registro.setCasilla(formularioElegido.getCasillas().get(i));
						registro.setLatitud(latitud);
						registro.setLongitud(longitud);
						registro.setFechaHora(selectedDate);
						registro = gestionRegistros.crearRegistro(registro);
						System.out.println("Registro antes: " + registro);			
						String dato = casillas.get(i).getTipoDeDato().toString();
						System.out.println(dato);
						if (casillas.get(i).getTipoDeDato() == TipoDatoEnum.STRING) {
							System.out.println("Entré al STRING");
							JTextField jValor = campos.get(i);
							System.out.println("Llegué al jValor: " + jValor);
							String valor = jValor.getText();
							System.out.println("Le saqué el String al jValor: " + valor);
							registro.setValorString(valor);
						}
		//						Casillas con tipo de dato Booleano						
//						if (casillas.get(i).getTipoDeDato() == TipoDatoEnum.BOOLEAN) {
//							Booleano valor = (Booleano) campos.get(i).getText();
//							registro.setValorBooleano( valor);
//						}
						
						if (casillas.get(i).getTipoDeDato() == TipoDatoEnum.INTEGER) {
							System.out.println("Entré al INTEGER");
							Integer valor = Integer.valueOf(campos.get(i).getText());
							registro.setValorInteger(valor);
						}
						
						if (casillas.get(i).getTipoDeDato() == TipoDatoEnum.FLOAT) {
							System.out.println("Entré al FLOAT");
							float valor = Float.valueOf(campos.get(i).getText());
							registro.setValorFloat(valor);
						}
					
						System.out.println("Registro después: " + registro);
						
						Long idActividad = actividad.getId();
						Long idRegistro = registro.getId();
						actividad = gestionActividades.agregaRegistro(idActividad, idRegistro);
						}	
						JOptionPane.showMessageDialog(null, "Actividad cargada", "Éxito!" , JOptionPane.WARNING_MESSAGE);
					
					
					
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		contentPane.add(btnIngresar, "4, 30");
	}
	Registro extraeDato(int i) {
		Registro registro = new Registro();
		return registro;
	}
}
