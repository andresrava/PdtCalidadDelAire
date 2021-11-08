package com.vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.controlador.ValidaLogin;
import com.entities.Usuario;

public class VentanaLogin {
	private JFrame frame;
	private JTextField textMail;
	private JTextField textPass;
	
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 376, 242);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 436, 263);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		textMail = new JTextField();
		textMail.setBounds(123, 78, 133, 20);
		panel.add(textMail);
		textMail.setColumns(10);
		
		textPass = new JPasswordField();
		textPass.setColumns(10);
		textPass.setBounds(123, 110, 133, 20);
		panel.add(textPass);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(69, 81, 47, 14);
		panel.add(lblMail);
		
		JLabel lblPass = new JLabel("Pass");
		lblPass.setBounds(69, 113, 47, 14);
		panel.add(lblPass);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ValidaLogin validaLogin = new ValidaLogin();
					Usuario usuario = validaLogin.validarLogin(textMail.getText(), textPass.getText());
					VentanaIngreso ventanaIngreso = new VentanaIngreso();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textMail.setText(null);
				textPass.setText(null);
			}
		});
		btnLogin.setBounds(148, 141, 89, 23);
		panel.add(btnLogin);
	}

	public VentanaLogin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
