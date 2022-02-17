package com.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.controlador.GestionFormularios;
import com.controlador.GestionIO;
import com.controlador.GestionRegistros;
import com.entities.Formulario;
import com.entities.Registro;
import com.entities.Usuario;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class VentanaListaRegistroFormulario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaListaRegistroFormulario() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListaRegistroFormulario frame = new VentanaListaRegistroFormulario(usuarioLoged);
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
	 * @throws NamingException 
	 */
	public VentanaListaRegistroFormulario(Usuario usuarioLogedRef) throws NamingException {
		setTitle("Lista Registros por Formulario");
		VentanaListaRegistroFormulario.usuarioLoged = usuarioLogedRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 949, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String nombreUsuario = usuarioLoged.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreUsuario);
		JButton btnExportar = new JButton("Exportar");
		
		JComboBox<Formulario> comboFormularios = new JComboBox();
		GestionFormularios gestionFormularios = new GestionFormularios();
		GestionRegistros gestionRegistros = new GestionRegistros();
		GestionIO gestionIO = new GestionIO();
		
		List<Formulario> formularios = gestionFormularios.listaFormularios();
		for (Formulario f : formularios) {
			comboFormularios.addItem(f);
		}
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione el formulario");
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaGestionRegistros ventanaGestionRegistros = new VentanaGestionRegistros(usuarioLoged);
				ventanaGestionRegistros.ventanaGestionRegistros();
			}
		});
		
		JComboBox<Registro> comboRegistros = new JComboBox();
		
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Formulario formulario = (Formulario) comboFormularios.getSelectedItem();
					Long idFormulario = formulario.getId();
					List<Registro> listaRegistros = gestionRegistros.muestraRegistros(idFormulario);
					comboRegistros.removeAllItems();
					for (Registro r : listaRegistros) {
						comboRegistros.addItem(r);
						}
					listaLlena = listaRegistros;
					
					} catch (NamingException e1) {
					e1.printStackTrace();
					}
				if (comboRegistros.getItemCount()>0)
					{btnExportar.setEnabled(true);}
				
				
			}
		});
		
		btnExportar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				
				 JFileChooser f = new JFileChooser();
				    f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
				    f.showSaveDialog(null);
				    boolean confirma = gestionIO.descargaRegistros(listaLlena , f);
				    if (confirma)
						JOptionPane.showMessageDialog(null, "Se descargó el archivo", "Atención!" , JOptionPane.WARNING_MESSAGE);

			}
		});
		btnExportar.setEnabled(false);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(111)
					.addComponent(lblNewLabel_1)
					.addContainerGap(594, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(861, Short.MAX_VALUE)
							.addComponent(comboFormularios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnExportar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnBuscar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnVolver, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
							.addGap(736)
							.addComponent(comboRegistros, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(32))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(35)
							.addComponent(comboFormularios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1)))
					.addPreferredGap(ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboRegistros, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(28))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnExportar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(46))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
