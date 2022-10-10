package com.vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.controlador.GestionCasillas;
import com.entities.Casilla;
import com.entities.Usuario;
import com.exceptions.ServiciosException;
import com.enumerados.BorradoLogico.*;

import javax.naming.NamingException;
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
import javax.swing.JRadioButton;
import java.awt.Font;

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
		setBounds(100, 100, 800, 500);		//Cambié esto para el fondo
		contentPane = new PaneImage();		//Cambié esto para el fomdo
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String nombreDelUsuario = usuarioLogedRef.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreDelUsuario);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre(*):");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		
		textParametro = new JTextField();
		textParametro.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Par\u00E1metro(*):");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textUnidad = new JTextField();
		textUnidad.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("Unidad(*):");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1_3 = new JLabel("Tipo de dato(*):");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textComentarios = new JTextField();
		textComentarios.setColumns(10);
		
		JLabel lblNewLabel_1_4 = new JLabel("Descripci\u00F3n:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_2 = new JLabel("(*) obligatorios");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		

		JLabel lblNewLabel_3 = new JLabel("Casilla obligatoria:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		JRadioButton rdbtnObligatoria = new JRadioButton("");
		
		
		JComboBox<TipoDatoEnum> comboBoxTipos = new JComboBox();
		for (TipoDatoEnum t : TipoDatoEnum.values()) {
			comboBoxTipos.addItem(t);
		}
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCrear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textNombre.getText().isEmpty() || textParametro.getText().isEmpty() || textUnidad.getText().isEmpty()) 
					JOptionPane.showMessageDialog(null, "Hay campos obligatorios incompletos","Error", JOptionPane.WARNING_MESSAGE);
				else {
				Casilla casilla = new Casilla(textNombre.getText(), (TipoDatoEnum) comboBoxTipos.getSelectedItem() , textParametro.getText(), textUnidad.getText(), textComentarios.getText() , usuarioLoged );
				if (rdbtnObligatoria.isSelected() )
					casilla.setObligatoria(Obligatoria.SI);
				GestionCasillas gestionCasillas = new GestionCasillas();
				try {
					gestionCasillas.crearCasilla(casilla);
					JOptionPane.showMessageDialog(null, "Se creó la Casilla");
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
			}
		});
	
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblNewLabel_3)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(rdbtnObligatoria))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblNewLabel_1)
														.addComponent(lblNewLabel)
														.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_contentPane.createSequentialGroup()
															.addGap(7)
															.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addComponent(textUnidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addComponent(comboBoxTipos, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
														.addGroup(gl_contentPane.createSequentialGroup()
															.addPreferredGap(ComponentPlacement.UNRELATED)
															.addComponent(textParametro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(textComentarios, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
													.addGap(30))
												.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED, 366, Short.MAX_VALUE)
													.addComponent(btnCrear, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)))))
									.addGap(560))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(306)
							.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
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
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1)
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
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_4)
						.addComponent(textComentarios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(rdbtnObligatoria))
					.addGap(24)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
