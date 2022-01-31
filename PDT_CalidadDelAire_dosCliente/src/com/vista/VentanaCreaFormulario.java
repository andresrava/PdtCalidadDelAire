package com.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.controlador.GestionCasillas;
import com.controlador.GestionFormularios;
import com.entities.Administrador;
import com.entities.Casilla;
import com.entities.Formulario;
import com.entities.Investigador;
import com.entities.Usuario;
import com.enumerados.BorradoLogico.Obligatoria;
import com.exceptions.ServiciosException;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	    GestionCasillas gestionCasillas = new GestionCasillas();
	    lista = gestionCasillas.listaCasillasObligatorias();
		String nombreDelUsuario = usuarioLoged.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreDelUsuario);
		
		JLabel lblNombre = new JLabel("Nombre(*):");
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		
		JLabel lblResumen = new JLabel("Resumen:");
		
		textResumen = new JTextField();
		textResumen.setColumns(10);
		
		//Creo el Combo con las casillas en el formulario y lo lleno con las casillas obligatorias
		JComboBox<Casilla> comboCasillasEnFormulario = new JComboBox();
		List<Casilla> obligatorias = gestionCasillas.listaCasillasObligatorias();
		for (Casilla c : obligatorias)
			comboCasillasEnFormulario.addItem(c);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JButton btnCrearFormulario = new JButton("Crear");
		btnCrearFormulario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textNombre.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un nombre para el formulario","Error", JOptionPane.WARNING_MESSAGE);
				}
				else {
				Formulario formulario = new Formulario();
				String nombreFormulario = textNombre.getText();
				formulario.setNombre(nombreFormulario);
				
				if (usuarioLoged instanceof Administrador)
					formulario.setAdministrador((Administrador) usuarioLoged);
				if (usuarioLoged instanceof Investigador)
					formulario.setInvestigador((Investigador) usuarioLoged);
				
				String resumen = textResumen.getText();
				formulario.setResumen(resumen);
				formulario.setCasillas(lista);
				GestionFormularios gestionFormularios = new GestionFormularios();
				try {
					formulario = gestionFormularios.crearFormulario(formulario);
					JOptionPane.showMessageDialog(null, "Se creó el Formulario","Crear Formulario", JOptionPane.OK_OPTION);
				} catch (NamingException | ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				VentanaFormularios ventanaFormulario = new VentanaFormularios(usuarioLoged);
				ventanaFormulario.ventanaFormularios();
				}
			}
		});
		
		JLabel lblCasillasDisponibles = new JLabel("Casillas disponibles:");
		
		JComboBox<Casilla> comboCasillasDisponibles = new JComboBox();
		List<Casilla> casillasDisponibles = new LinkedList<Casilla>();
		casillasDisponibles = gestionCasillas.listaCasillasOpcionales();
		for (Casilla c : casillasDisponibles) {
			comboCasillasDisponibles.addItem(c);
		}
		
		JButton btnAgregarCasilla = new JButton("Agregar");
		btnAgregarCasilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
					.addGap(20)
					.addComponent(lblCasillasDisponibles)
					.addContainerGap(381, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(199)
					.addComponent(btnAgregarCasilla, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(439, Short.MAX_VALUE)
					.addComponent(comboCasillasDisponibles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(32))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addComponent(lblCasillasDisponibles)
					.addGap(12)
					.addComponent(comboCasillasDisponibles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
					.addComponent(btnAgregarCasilla, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(26))
		);
		panel.setLayout(gl_panel);
		
		JLabel lblCasillasEnFormulario = new JLabel("Casillas:");
		
		
		JButton btnQuitarCasilla = new JButton("Quitar Casilla");
		btnQuitarCasilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnQuitarCasilla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Casilla c = (Casilla) comboCasillasEnFormulario.getSelectedItem();
				if (c.getObligatoria() == Obligatoria.NO) {
				lista.remove(c);
				comboCasillasEnFormulario.removeItem(c);
				comboCasillasEnFormulario.updateUI();
				}
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
