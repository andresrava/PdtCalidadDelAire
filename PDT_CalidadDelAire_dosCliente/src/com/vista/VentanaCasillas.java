package com.vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Administrador;
import com.entities.Aficionado;
import com.entities.Investigador;
import com.entities.Usuario;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCasillas extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaCasillas() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCasillas frame = new VentanaCasillas(usuarioLoged);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static Usuario usuarioLoged;
	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public VentanaCasillas(Usuario usuarioLogedRef) throws NamingException {
		setTitle("Gesti\u00F3n de Casillas");
		VentanaCasillas.usuarioLoged = usuarioLogedRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		String nombreDelUsuario = usuarioLogedRef.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreDelUsuario);
		
		JButton btnCreaCasilla = new JButton("Crear");
		btnCreaCasilla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaCreaCasilla ventanaCreaCasilla = new VentanaCreaCasilla(usuarioLoged);
				ventanaCreaCasilla.ventanaCreaCasilla();
			}
		});
		
		JButton btnListaCasillas_1_1 = new JButton("Lista");
		btnListaCasillas_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnListaCasillas_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaListaCasillas ventanaListaCasillas;
				dispose();
				try {
					ventanaListaCasillas = new VentanaListaCasillas(usuarioLoged);
					ventanaListaCasillas.ventanaListaCasillas();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}					
			}
		});
		
		JButton btnVolver = new JButton("Volver");					// El bot�n volver abre una ventana acorde al usuario que est� trabajando
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVolver.addMouseListener(new MouseAdapter() {				// Me falta lograr que se cierre la ventana que est� en uso		
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				if (usuarioLoged instanceof Administrador) {
				VentanaAdministrador ventanaAdministrador = new VentanaAdministrador((Administrador) usuarioLoged);
				ventanaAdministrador.ventanaAdministrador();
				}
				if (usuarioLoged instanceof Investigador) {
				VentanaInvestigador ventanaInvestigador = new VentanaInvestigador((Investigador) usuarioLoged);
				ventanaInvestigador.ventanaInvestigador();
				}
				if (usuarioLoged instanceof Aficionado) {
					VentanaAficionado ventanaAficionado = new VentanaAficionado((Aficionado) usuarioLoged);
					ventanaAficionado.ventanaAficionado();
				}
				

			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnCreaCasilla, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnListaCasillas_1_1, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(276, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(11)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCreaCasilla, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(60)
					.addComponent(btnListaCasillas_1_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		
		contentPane.setLayout(gl_contentPane);
	}
}
