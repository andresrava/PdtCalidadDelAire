package com.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.entities.Administrador;
import com.entities.Usuario;
import com.exceptions.ServiciosException;
import com.services.AdministradoresBeanRemote;
import com.services.UsuariosBeanRemote;

public class Login {
	private JFrame frame;
	private JTextField textMail;
	private JTextField textPass;

	public static void main(String[] args) throws NamingException, ServiciosException {
		
		AdministradoresBeanRemote recurso;
        try {
     	   String ruta2="PDT_CalidadDelAire_dosEJB/AdministradoresBean!com.services.AdministradoresBeanRemote";
     	   recurso=(AdministradoresBeanRemote)InitialContext.doLookup(ruta2);
     	   cargaInicial((AdministradoresBeanRemote) recurso);
     	   System.out.println("Se crearon correctamente las personas");
        }
     	  catch(NamingException e) {
        	   System.out.println("Error");
        	   System.out.println(e);
     	  }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Login() {
		initialize();
	}
	private void initialize() {
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
					validarLogin();
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
	//Funcion para validar el login
		public void validarLogin() throws NamingException {
			String ruta="PDT_CalidadDelAire_dosEJB/UsuariosBean!com.services.UsuariosBeanRemote";
			UsuariosBeanRemote usuarioBean = (UsuariosBeanRemote) InitialContext.doLookup(ruta);
			List<Usuario> usuarios = usuarioBean.validarLogin(textMail.getText(), textPass.getText());
			if ( !usuarios.isEmpty()) {
				this.frame.dispose();
				
				
				
	        }else {
	        	JOptionPane.showMessageDialog(null, "Error - credenciales incorrectas.");
	        	
	        }
		}
		private static void cargaInicial(AdministradoresBeanRemote recurso) throws NamingException, ServiciosException {
			

			recurso.crear(new Administrador("Rava","miclave","3854","mimail","Andrés" , "mitelefono"));
		
//			Usuario usuario = new Usuario(1, "Rava","miclave","midocumento","mimail","Andrés");
//			String ruta="PDT_CalidadDelAire_dosEJB/UsuariosBean!com.services.UsuariosBeanRemote";
//			UsuariosBeanRemote usuarioBean = (UsuariosBeanRemote) InitialContext.doLookup(ruta);
//			usuarioBean.crear(usuario);
//			System.out.println("Se creó el usuario Andrés");
			
			
		}
		
}	
