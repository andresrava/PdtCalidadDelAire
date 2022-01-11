package com.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.controlador.GestionCasillas;
import com.entities.Administrador;
import com.entities.Casilla;
import com.entities.Investigador;
import com.entities.Usuario;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class VentanaCreaFormulario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void VentanaCreaFormulario() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCreaFormulario frame = new VentanaCreaFormulario(usuarioLoged);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static Usuario usuarioLoged;
	private JTextField textNombre;
	private JTextField textResumen;
	private List<Casilla> lista = new LinkedList<Casilla>();

	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public VentanaCreaFormulario(Usuario usuarioLogedRef) throws NamingException {
		setTitle("Crea Formulario");
		VentanaCreaFormulario.usuarioLoged = usuarioLogedRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1009, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String nombreDelUsuario = usuarioLoged.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreDelUsuario);
		
		JLabel lblNombre = new JLabel("Nombre(*):");
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		
		JLabel lblResumen = new JLabel("Resumen:");
		
		textResumen = new JTextField();
		textResumen.setColumns(10);
		
		JComboBox comboCasillasEnFormulario = new JComboBox();
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JButton btnCrearFormulario = new JButton("Crear");
		
		JLabel lblCasillasDisponibles = new JLabel("Casillas disponibles:");
		
		JComboBox comboCasillasDisponibles = new JComboBox();
		List<Casilla> casillasDisponibles = new LinkedList<Casilla>();
		GestionCasillas gestionCasillas = new GestionCasillas();
		casillasDisponibles = gestionCasillas.listaCasillas();
		for (Casilla c : casillasDisponibles) {
			comboCasillasDisponibles.addItem(c);
		}
		
		JButton btnAgregarCasilla = new JButton("Agregar");
		btnAgregarCasilla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Casilla c = (Casilla) comboCasillasDisponibles.getSelectedItem();
				if (!lista.contains(c)) {
					comboCasillasEnFormulario.addItem(c);
					comboCasillasEnFormulario.updateUI();
				lista.add(c);}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap(247, Short.MAX_VALUE)
							.addComponent(comboCasillasDisponibles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(20)
							.addComponent(lblCasillasDisponibles)
							.addPreferredGap(ComponentPlacement.RELATED, 157, Short.MAX_VALUE)))
					.addGap(32))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(199)
					.addComponent(btnAgregarCasilla, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addComponent(lblCasillasDisponibles)
					.addGap(18)
					.addComponent(comboCasillasDisponibles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
					.addComponent(btnAgregarCasilla, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(26))
		);
		panel.setLayout(gl_panel);
		
		JLabel lblCasillasEnFormulario = new JLabel("Casillas:");
		
		
		JButton btnQuitarCasilla = new JButton("Quitar Casilla");
		btnQuitarCasilla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Casilla c = (Casilla) comboCasillasEnFormulario.getSelectedItem();
				lista.remove(c);
				comboCasillasEnFormulario.removeItem(c);
				comboCasillasEnFormulario.updateUI();
			}
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaFormularios ventanaFormularios;
				if (usuarioLoged instanceof Administrador) {
					ventanaFormularios = new VentanaFormularios((Administrador) usuarioLoged);
					ventanaFormularios.ventanaFormularios();;
				}
				if (usuarioLoged instanceof Investigador) {
					ventanaFormularios = new VentanaFormularios((Investigador) usuarioLoged);
					ventanaFormularios.ventanaFormularios();;
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNombre)
										.addComponent(lblResumen))
									.addGap(98)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
										.addComponent(textResumen, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(100)
											.addComponent(btnQuitarCasilla))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCasillasEnFormulario)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboCasillasEnFormulario, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 505, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(169)
							.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addGap(83)
							.addComponent(btnCrearFormulario, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
					.addGap(85))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNombre)
									.addGap(40)
									.addComponent(lblResumen)
									.addGap(48)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblCasillasEnFormulario)
										.addComponent(comboCasillasEnFormulario, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(31)
									.addComponent(textResumen, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addGap(42)
									.addComponent(btnQuitarCasilla))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnVolver, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnCrearFormulario, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
