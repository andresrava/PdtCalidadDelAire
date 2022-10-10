package com.vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.controlador.GestionIO;
import com.controlador.GestionRegistros;
import com.entities.Registro;
import com.entities.Usuario;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFileChooser;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaListaRegistrosFecha extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaListaRegistrosFecha() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListaRegistrosFecha frame = new VentanaListaRegistrosFecha(usuarioLoged);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static Usuario usuarioLoged;
	List<Registro> registros = new LinkedList<Registro>();
	DefaultTableModel modelo = new DefaultTableModel();
	JTable table = new JTable(modelo);
	JScrollPane scrollPane = new JScrollPane(table);
	JPanel panel = new JPanel();
	
	/**
	 * Create the frame.
	 */
			
	public VentanaListaRegistrosFecha(Usuario usuarioLogedRef) {
		
		setTitle("Lista Registros por fecha");
		VentanaListaRegistrosFecha.usuarioLoged = usuarioLogedRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);	
		//Agrego el fondo
		contentPane = new PaneImage();		
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		String nombreusuario = usuarioLoged.getNombre();
		
					//******Servicios*******
		GestionIO gestionIO = new GestionIO();
		UtilDateModel model = new UtilDateModel();
		HaceTablas haceTablas = new HaceTablas();
		
					// *****Etiquetas**********
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreusuario);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione el rango de fechas:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JInternalFrame internalFrameDesde = new JInternalFrame("Desde:");
		internalFrameDesde.setVisible(true);

		JInternalFrame internalFrameHasta = new JInternalFrame("Hasta:");
		internalFrameHasta.setVisible(true);
		
		//Establezco el formato para la fecha
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		
		JDatePanelImpl datePanelDesde = new JDatePanelImpl(model, p);
		DateComponentFormatter formato1 = new DateComponentFormatter();
		JDatePickerImpl datePickerDesde = new JDatePickerImpl(datePanelDesde, formato1);
		internalFrameDesde.getContentPane().add(datePickerDesde);

		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl datePanelHasta = new JDatePanelImpl(model2, p);
		DateComponentFormatter formato2 = new DateComponentFormatter();
		JDatePickerImpl datePickerHasta = new JDatePickerImpl(datePanelHasta, formato2);
		internalFrameHasta.getContentPane().add(datePickerHasta);
	
		
		//Agrego el modelo a la tabla
		table.setModel(modelo);
		panel.add(scrollPane);
		
							// *******Botones***********
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaGestionRegistros ventanaGestionRegistros = new VentanaGestionRegistros(usuarioLoged);
				ventanaGestionRegistros.ventanaGestionRegistros();
			}
		});
		
		JButton btnExportar = new JButton("Exportar");
		btnExportar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExportar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser f = new JFileChooser();
			    f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
			    f.showSaveDialog(null);
			    if (f.getSelectedFile() != null) {
					boolean confirma = false;
					confirma = gestionIO.descargaRegistros(registros , f);
				    if (confirma)
						JOptionPane.showMessageDialog(null, "Se descargó el archivo", "Atención!" , JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnExportar.setVisible(false);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				java.sql.Date dateDesde = new java.sql.Date(((java.util.Date) datePickerDesde.getModel().getValue()).getTime());
				java.sql.Date dateHasta = new java.sql.Date(((java.util.Date) datePickerHasta.getModel().getValue()).getTime());
				GestionRegistros gestionRegistros = new GestionRegistros();
				List<Registro> registrosEncontrados = new LinkedList<Registro>();
				try {
					registrosEncontrados = gestionRegistros.encuentraPorFechas(dateDesde , dateHasta);
					if (registrosEncontrados.size() != 0)
						btnExportar.setVisible(true);
					else
						btnExportar.setVisible(false);
					modelo = haceTablas.haceTablaRegistros(registrosEncontrados);
					//Agrego el modelo a la tabla
					table.setModel(modelo);
					panel.add(scrollPane);
					
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				registros = registrosEncontrados;
			
				
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(326)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(347, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(btnExportar, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(40)
								.addComponent(lblNewLabel_1))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(internalFrameDesde, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
									.addComponent(internalFrameHasta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)))))
					.addGap(55)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(38)
							.addComponent(internalFrameDesde, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(internalFrameHasta, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addGap(45)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnBuscar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnExportar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(53))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(40))
		);
		
	
		
		
		contentPane.setLayout(gl_contentPane);
		
	}
}
