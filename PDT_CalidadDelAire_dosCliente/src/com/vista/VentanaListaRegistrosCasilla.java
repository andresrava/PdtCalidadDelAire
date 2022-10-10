package com.vista;

import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.UtilDateModel;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;

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
	List<Registro> registros = new LinkedList<Registro>();
	DefaultTableModel modelo = new DefaultTableModel();
	JTable table = new JTable(modelo);
	JScrollPane scrollPane = new JScrollPane(table);
	JPanel panel = new JPanel();
	
	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public VentanaListaRegistrosCasilla(Usuario usuarioLogedRef) throws NamingException {
		setBackground(Color.WHITE);
		setTitle("Lista Registros por Parámetro");
		VentanaListaRegistrosCasilla.usuarioLoged = usuarioLogedRef;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);	
		//Agrego el fondo
		contentPane = new PaneImage();		
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		String nombreUsuario = usuarioLoged.getNombre();
		
				//****Servicios****
		UtilDateModel model = new UtilDateModel();
		HaceTablas haceTablas = new HaceTablas();
		GestionIO gestionIO = new GestionIO();
		GestionCasillas gestionCasillas = new GestionCasillas();
		
				//****Etiquetas****
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreUsuario);
		JLabel lblNewLabel_1 = new JLabel("Seleccione la casilla:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
				//Creo el combo con las casillas(parámetros)
		List<Casilla> casillasDisponibles = gestionCasillas.listaCasillas();
		JComboBox<Casilla> comboCasillasDisponibles = new JComboBox();
		for (Casilla c : casillasDisponibles)
			comboCasillasDisponibles.addItem(c);
		
				//****Botones****
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
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaGestionRegistros ventanaGestionFormularios = new VentanaGestionRegistros(usuarioLoged);
				ventanaGestionFormularios.ventanaGestionRegistros();
			}
		});
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Casilla casilla = (Casilla) comboCasillasDisponibles.getSelectedItem();
				System.out.println("La casilla elegida es: " + casilla);
				registros = casilla.getRegistros();
				System.out.println("Los registros son: " + registros);
				if (registros.size() != 0)
					btnExportar.setVisible(true);
				else
					btnExportar.setVisible(false);
				modelo = haceTablas.haceTablaRegistros(registros);
				//Agrego el modelo a la tabla
				table.setModel(modelo);
				panel.add(scrollPane);
			}
		});
				
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel_1))
								.addComponent(comboCasillasDisponibles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(132)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnExportar, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnVolver, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
									.addGap(350))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 466, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(26)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboCasillasDisponibles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExportar, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addGap(52)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(scrollPane);
		contentPane.setLayout(gl_contentPane);
	}
}
