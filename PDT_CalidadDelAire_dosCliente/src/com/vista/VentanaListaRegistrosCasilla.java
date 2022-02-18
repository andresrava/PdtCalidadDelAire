package com.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.controlador.GestionCasillas;
import com.controlador.GestionIO;
import com.controlador.GestionRegistros;
import com.entities.Casilla;
import com.entities.Registro;
import com.entities.Usuario;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class VentanaListaRegistrosCasilla extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaListaRegistrosCasilla() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListaRegistrosCasilla frame = new VentanaListaRegistrosCasilla(usuarioLoged);
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
	public VentanaListaRegistrosCasilla(Usuario usuarioLogedRef) throws NamingException {
		setBackground(Color.WHITE);
		setTitle("Lista Registros por Casilla");
		VentanaListaRegistrosCasilla.usuarioLoged = usuarioLogedRef;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 310);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String nombreUsuario = usuarioLoged.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreUsuario);
		GestionRegistros gestionRegistros = new GestionRegistros();
		GestionIO gestionIO = new GestionIO();
		GestionCasillas gestionCasillas = new GestionCasillas();
		List<Casilla> casillasDisponibles = gestionCasillas.listaCasillas();
		JComboBox<Casilla> comboCasillasDisponibles = new JComboBox();
		for (Casilla c : casillasDisponibles)
			comboCasillasDisponibles.addItem(c);
		
		JComboBox<Registro> comboRegistros = new JComboBox();
		JButton btnExportar = new JButton("Exportar");
		btnExportar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 JFileChooser f = new JFileChooser();
			    f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
			    f.showSaveDialog(null);
			    boolean confirma = gestionIO.descargaRegistros(listaLlena , f);
			    if (confirma)
					JOptionPane.showMessageDialog(null, "Se descargó el archivo", "Atención!" , JOptionPane.WARNING_MESSAGE);

			}
		});
		btnExportar.setEnabled(false);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaGestionRegistros ventanaGestionFormularios = new VentanaGestionRegistros(usuarioLoged);
				ventanaGestionFormularios.ventanaGestionRegistros();
			}
		});
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Casilla casilla = (Casilla) comboCasillasDisponibles.getSelectedItem();
				Long idCasilla = casilla.getId();
				List<Registro> listaRegistros;
				try {
					listaRegistros = gestionRegistros.muestraRegistrosPorCasilla(idCasilla);
					comboRegistros.removeAllItems();
					for (Registro r : listaRegistros) {
						comboRegistros.addItem(r);
						}
					listaLlena = listaRegistros;
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (comboRegistros.getItemCount()>0)
					{btnExportar.setEnabled(true);}
			
				
			}
		});
		
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione la casilla:");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblNewLabel_1))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addContainerGap()
										.addComponent(btnExportar, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addContainerGap()
											.addComponent(btnBuscar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(lblNewLabel)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(10)
											.addComponent(btnVolver, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))))
							.addPreferredGap(ComponentPlacement.RELATED, 366, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(160)
							.addComponent(comboCasillasDisponibles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(275)
							.addComponent(comboRegistros, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(219)))
					.addGap(40))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(26)
					.addComponent(lblNewLabel_1)
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboCasillasDisponibles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboRegistros, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addComponent(btnExportar, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
