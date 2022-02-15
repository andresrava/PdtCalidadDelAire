package com.vista;

import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

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
	List<Registro> listaLlena = new LinkedList<Registro>();
	
	/**
	 * Create the frame.
	 */
	public VentanaListaRegistrosFecha(Usuario usuarioLogedRef) {
		setTitle("Lista Registros por fecha");
		VentanaListaRegistrosFecha.usuarioLoged = usuarioLogedRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 746, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		String nombreusuario = usuarioLoged.getNombre();
		GestionIO gestionIO = new GestionIO();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreusuario);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione el rango de fechas:");
		
		JInternalFrame internalFrameDesde = new JInternalFrame("Desde:");
		internalFrameDesde.setVisible(true);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaGestionRegistros ventanaGestionRegistros = new VentanaGestionRegistros(usuarioLoged);
				ventanaGestionRegistros.ventanaGestionRegistros();
			}
		});
		
		JButton btnBuscar = new JButton("Buscar");
		
		
		JButton btnExportar = new JButton("Exportar");
		btnExportar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser f = new JFileChooser();
			    f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
			    f.showSaveDialog(null);
			    
			    gestionIO.descargaRegistros(listaLlena , f);
			    if (f != null)
					JOptionPane.showMessageDialog(null, "Se descargó el archivo", "Atención!" , JOptionPane.WARNING_MESSAGE);
			}
		});
		btnExportar.setEnabled(false);
		
		JComboBox<Registro> comboRegistros = new JComboBox();
		
		JInternalFrame internalFrameHasta = new JInternalFrame("Hasta:");
		internalFrameHasta.setVisible(true);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(40)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED, 491, Short.MAX_VALUE)
							.addComponent(comboRegistros, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(internalFrameDesde, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(internalFrameHasta))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnExportar, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_1)
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(internalFrameHasta)
								.addComponent(internalFrameDesde, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnExportar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnBuscar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnVolver, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(33)
							.addComponent(comboRegistros, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanelDesde = new JDatePanelImpl(model, p);
		DateComponentFormatter formato1 = new DateComponentFormatter();
		JDatePickerImpl datePickerDesde = new JDatePickerImpl(datePanelDesde, formato1);
		 
		internalFrameDesde.getContentPane().add(datePickerDesde);
		
//		Properties pp = new Properties();
//		pp.put("text.today", "Today");
//		pp.put("text.month", "Month");
//		pp.put("text.year", "Year");
		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl datePanelHasta = new JDatePanelImpl(model2, p);
		DateComponentFormatter formato2 = new DateComponentFormatter();
		JDatePickerImpl datePickerHasta = new JDatePickerImpl(datePanelHasta, formato2);
		internalFrameHasta.getContentPane().add(datePickerHasta);
		
		
		contentPane.setLayout(gl_contentPane);
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboRegistros.removeAllItems();
				java.sql.Date dateDesde = new java.sql.Date(((java.util.Date) datePickerDesde.getModel().getValue()).getTime());
				java.sql.Date dateHasta = new java.sql.Date(((java.util.Date) datePickerHasta.getModel().getValue()).getTime());
				GestionRegistros gestionRegistros = new GestionRegistros();
				try {
					List<Registro> lista = gestionRegistros.encuentraPorFechas(dateDesde , dateHasta);
					listaLlena = lista;
					for (Registro r : lista) {
						comboRegistros.addItem(r);
					}
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (comboRegistros.getItemCount()>0)
				{btnExportar.setEnabled(true);}
			
				
			}
		});
	}
}
