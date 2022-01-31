package com.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaEditaFormulario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaEditaFormulario() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEditaFormulario frame = new VentanaEditaFormulario(usuarioLoged, formularioAEditar);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static Usuario usuarioLoged;
	private static Formulario formularioAEditar;
	
		
	
	private JTextField textNombre;
	private JTextField textComentarios;
	
	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public VentanaEditaFormulario(Usuario usuarioLogedRef, Formulario formularioAEditarRef) throws NamingException {
		setTitle("Edita formulario");
		VentanaEditaFormulario.usuarioLoged = usuarioLogedRef;
		VentanaEditaFormulario.formularioAEditar = formularioAEditarRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 917, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		List<Casilla> lista = new LinkedList<>();
		if (formularioAEditar.getCasillas() != null)
			for (Casilla c : formularioAEditar.getCasillas())
			{
				lista.add(c);
			}
		
		String nombreDelUsuario = usuarioLogedRef.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreDelUsuario);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre(*):");
		
		textNombre = new JTextField();
		textNombre.setText(formularioAEditar.getNombre());
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Casillas:");
		
		JComboBox<Casilla> comboCasillasEnFormulario = new JComboBox();
		for (Casilla c : lista)
		{
			comboCasillasEnFormulario.addItem(c);
		}
		
		
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Casilla> listasa = lista;
				Casilla c = (Casilla) comboCasillasEnFormulario.getSelectedItem();
				if (c.getObligatoria() == Obligatoria.NO) {
				lista.remove(c);
				comboCasillasEnFormulario.removeItem(c);
				comboCasillasEnFormulario.updateUI();
				}
			}
		});
		
		JLabel lblResumen = new JLabel("Resumen:");
		
		textComentarios = new JTextField();
		textComentarios.setText(formularioAEditar.getResumen());
		textComentarios.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel lblCasillasDisponibles = new JLabel("Casillas disponibles:");
		
		JComboBox<Casilla> comboCasillasDisponibles = new JComboBox();
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
					lista.add(c);
					}
			}
		});
		btnAgregarCasilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
					.addContainerGap(439, Short.MAX_VALUE)
					.addComponent(comboCasillasDisponibles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(32))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(199, Short.MAX_VALUE)
					.addComponent(btnAgregarCasilla, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addComponent(lblCasillasDisponibles)
					.addGap(12)
					.addComponent(comboCasillasDisponibles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
					.addComponent(btnAgregarCasilla, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				try {
					VentanaListaFormularios ventanaListaFormularios = new VentanaListaFormularios(usuarioLoged);
					ventanaListaFormularios.ventanaListaFormularios();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnCrearFormulario = new JButton("Actualizar");
		btnCrearFormulario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textNombre.getText() == null) 
					JOptionPane.showMessageDialog(null, "Debe ingresar un nombre para el formulario","Error", JOptionPane.WARNING_MESSAGE);
				else {
				String nombreFormulario = textNombre.getText();
				formularioAEditar.setNombre(nombreFormulario);
				String resumen = textComentarios.getText();
				formularioAEditar.setResumen(resumen);
				formularioAEditar.setCasillas(lista);
				GestionFormularios gestionFormularios = new GestionFormularios();
				try {
					formularioAEditar = gestionFormularios.actualizarFormulario(formularioAEditar);
					JOptionPane.showMessageDialog(null, "Se actualizó el Formulario","Crear Formulario", JOptionPane.OK_OPTION);
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
		
		//En la ventana se muestra el usuario creador del formulario
		Administrador administradorCreador = formularioAEditar.getAdministrador();
		Investigador investigadorCreador = formularioAEditar.getInvestigador();
		String etiqueta = new String();
		if (administradorCreador != null)
			{String nombreCreador = administradorCreador.getNombre();
			etiqueta = ("Formulario creado por el Administrador: " + nombreCreador);
			}
		if (investigadorCreador != null)
			{String nombreCreador = investigadorCreador.getNombre();
			etiqueta = ("Formulario creado por el Investigador: " + nombreCreador);
			}
		JLabel lblCreador = new JLabel(etiqueta);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_1)
							.addGap(84)
							.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblResumen)
							.addGap(40)
							.addComponent(textComentarios, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(55)
							.addComponent(lblNewLabel_4)
							.addGap(4)
							.addComponent(comboCasillasEnFormulario, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblCreador))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(282)
					.addComponent(btnQuitar, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(52)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addGap(190)
					.addComponent(btnCrearFormulario, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCreador)
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNewLabel_1))
								.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(15)
									.addComponent(lblResumen))
								.addComponent(textComentarios, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
							.addGap(32)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(4)
									.addComponent(lblNewLabel_4))
								.addComponent(comboCasillasEnFormulario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(14)
					.addComponent(btnQuitar, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCrearFormulario, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
