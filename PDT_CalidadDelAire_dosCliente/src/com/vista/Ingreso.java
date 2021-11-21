package com.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.cliente.CargaInicial;
import com.controlador.ValidaLogin;
import com.entities.Administrador;
import com.entities.Investigador;
import com.entities.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 314, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Mail:");
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a:");
		
		textMail = new JTextField();
		textMail.setColumns(10);
		
		textClave = new JTextField();
		textClave.setColumns(10);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ValidaLogin validaLogin = new ValidaLogin();
				Usuario usuario = new Usuario();
				try {
					usuario = validaLogin.validarLogin(textMail.getText(), textClave.getText());
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					if (usuario instanceof Administrador) {
						System.out.println("Es una instancia de Administrador!!!");
						VentanaAdministrador ventanaAdministrador = new VentanaAdministrador((Administrador) usuario);
						ventanaAdministrador.ventanaAdministrador();
					}
					else if (usuario instanceof Investigador) {
						System.out.println("Es una instancia de Investigador!!!");
						JOptionPane.showMessageDialog(null, "No se ha implementado ventana Investigador");
					}
					else {
						JOptionPane.showMessageDialog(null, "Error - credenciales incorrectas.");
						textClave.setText(null);
						textMail.setText(null);
					}
				
			}
		});
		
		JButton btnCargaInicial = new JButton("Carga Inicial");
		btnCargaInicial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CargaInicial cargaInicial = new CargaInicial();
				try {
					cargaInicial.cargaInicial();
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
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textClave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(55)
							.addComponent(btnIngresar)))
					.addPreferredGap(ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
					.addComponent(btnCargaInicial)
					.addGap(66))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textClave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCargaInicial)
						.addComponent(btnIngresar))
					.addContainerGap(125, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
