package com.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.controlador.GestionCasillas;
import com.entities.Casilla;
import com.entities.Casilla.TipoDatoEnum;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import javax.naming.NamingException;
import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaCreaCasilla extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaCreaCasilla() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCreaCasilla frame = new VentanaCreaCasilla(usuarioLoged);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static Usuario usuarioLoged;
	private JTextField textNombre;
	private JTextField textParametro;
	private JTextField textUnidad;
	private JTextField textComentarios;

	/**
	 * Create the frame.
	 */
	public VentanaCreaCasilla(Usuario usuarioLogedRef) {
		setTitle("Crear Casilla");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VentanaCreaCasilla.usuarioLoged = usuarioLogedRef;
		setBounds(100, 100, 450, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String nombreDelUsuario = usuarioLogedRef.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreDelUsuario);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre(*):");
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		
		textParametro = new JTextField();
		textParametro.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Par\u00E1metro(*):");
		
		textUnidad = new JTextField();
		textUnidad.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("Unidad(*):");
		
		JLabel lblNewLabel_1_3 = new JLabel("Tipo de dato(*):");
		
		textComentarios = new JTextField();
		textComentarios.setColumns(10);
		
		JLabel lblNewLabel_1_4 = new JLabel("Descripci\u00F3n:");
		
		JLabel lblNewLabel_2 = new JLabel("(*) obligatorios");
		
		
		JComboBox<String> comboBoxTipos = new JComboBox();
		for (TipoDatoEnum t : Casilla.TipoDatoEnum.values()) {
			comboBoxTipos.addItem(t.name());
		}
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textNombre.getText() == null || textParametro.getText() == null || textUnidad.getText() == null) 
					JOptionPane.showMessageDialog(null, "Hay campos obligatorios incompletos","Error", JOptionPane.WARNING_MESSAGE);
					
				Casilla casilla = new Casilla(textNombre.getText(), (String)comboBoxTipos.getSelectedItem() , textParametro.getText(), textUnidad.getText(), textComentarios.getText() , usuarioLoged );
				GestionCasillas gestionCasillas = new GestionCasillas();
				try {
					gestionCasillas.crearCasilla(casilla);
					JOptionPane.showMessageDialog(null, "Se creó la Casilla","Crear Casilla", JOptionPane.OK_OPTION);
				} catch (NamingException | ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				try {
					VentanaCasillas ventanaCasillas = new VentanaCasillas(usuarioLoged);
					ventanaCasillas.ventanaCasillas();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					VentanaCasillas ventanaCasillas = new VentanaCasillas(usuarioLoged);
					ventanaCasillas.ventanaCasillas();
					dispose();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addGap(56)
							.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addGap(56)
							.addComponent(textParametro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addGap(56)
							.addComponent(textUnidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_2)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
							.addGap(42)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBoxTipos, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textComentarios))
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnCrear, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnVolver, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_1_1))
						.addComponent(textParametro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_1_2))
						.addComponent(textUnidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_3)
						.addComponent(comboBoxTipos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCrear, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_1_4))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(textComentarios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
					.addGap(28)
					.addComponent(lblNewLabel_2)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
