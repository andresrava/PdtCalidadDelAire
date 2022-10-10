package com.vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.cliente.CargaInicial;
import com.controlador.GestionUsuarios;
import com.entities.Administrador;
import com.entities.Aficionado;
import com.entities.Investigador;
import com.entities.Usuario;
import com.exceptions.ServiciosException;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Ingreso extends JFrame {

	private JPanel contentPane;
	private JTextField textMail;
	private JTextField textClave;

	/**
	 * Launch the application.
	 * @throws NamingException 
	 */
	public static void main(String[] args) throws NamingException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// Cargo elementos iniciales en la Base de Datos
				CargaInicial cargaInicial = new CargaInicial();
				try {
					cargaInicial.cargaInicial();
				} catch (NamingException | ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					Ingreso frame = new Ingreso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ingreso() {
		setTitle("Ingreso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);	
		//Agrego el fondo
		contentPane = new PaneImage();		
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Correo electr\u00F3nico:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textMail = new JTextField();
		textMail.setColumns(10);
		
		textClave = new JPasswordField();
		textClave.setColumns(10);
		textClave.setBounds(123, 110, 133, 20);
		contentPane.add(textClave);
		
		textClave.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) 
	               entra();
			}

			@Override
			public void keyReleased (KeyEvent e)  {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIngresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				entra();
			
			
			}
		});
//		// Cargo elementos iniciales en la Base de Datos
//		CargaInicial cargaInicial = new CargaInicial();
//		try {
//			cargaInicial.cargaInicial();
//		} catch (NamingException | ServiciosException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
		JLabel lblNewLabel_2 = new JLabel("<html>Sistema de gesti\u00F3n de datos  de calidad de aire<html>");
		lblNewLabel_2.setForeground(Color.PINK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 33));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textMail, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textClave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnIngresar, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addContainerGap(87, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(76)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textClave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
							.addGap(30)
							.addComponent(btnIngresar, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(155, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
		void entra() {
			GestionUsuarios gestionUsuarios = new GestionUsuarios();
			Usuario usuario = new Usuario();
			try {
				// Verificalas credenciales y devuelve un usuario si hay match
				usuario = gestionUsuarios.validarLogin(textMail.getText(), textClave.getText());
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				if (usuario instanceof Administrador) {
					dispose();
					VentanaAdministrador ventanaAdministrador = new VentanaAdministrador((Administrador) usuario);
					ventanaAdministrador.ventanaAdministrador();
				}
				else if (usuario instanceof Investigador) {
					dispose();
					VentanaInvestigador ventanaInvestigador = new VentanaInvestigador((Investigador) usuario);
					ventanaInvestigador.ventanaInvestigador();
				}
				else if (usuario instanceof Aficionado) {
					dispose();
					VentanaAficionado ventanaAficionado = new VentanaAficionado((Aficionado) usuario);
					ventanaAficionado.ventanaAficionado();
				}
				else {
					JOptionPane.showMessageDialog(null, "Error - credenciales incorrectas.");
					textClave.setText(null);
					textMail.setText(null);
				}
		}
}
