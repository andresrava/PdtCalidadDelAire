/*
 * Created by JFormDesigner on Sun Nov 07 20:20:18 ART 2021
 */

package com.vista;

import java.awt.event.*;

import javax.naming.NamingException;
import javax.swing.*;

import com.controlador.ValidaLogin;
import com.entities.Administrador;
import com.entities.Usuario;

import net.miginfocom.swing.*;

/**
 * @author Andrés
 */
public class PruebaIngreso  {

	private void button1ActionPerformed(ActionEvent e) throws NamingException {
		ValidaLogin validaLogin = new ValidaLogin();
		Usuario usuario = validaLogin.validarLogin(textCorreo.getText(), textClave.getText());
		if (  usuario instanceof Administrador) {
			VentanaAdministrador ventanaAdministrador = new VentanaAdministrador();
			
		}
		else {
			JOptionPane.showMessageDialog(null, "Error - credenciales incorrectas.");
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Andrés
		panel1 = new JPanel();
		label1 = new JLabel();
		textCorreo = new JTextField();
		label2 = new JLabel();
		textClave = new JTextField();
		button1 = new JButton();

		//======== panel1 ========
		{
			panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
			border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER
			, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font
			.BOLD ,12 ), java. awt. Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (
			new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r"
			.equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
			panel1.setLayout(new MigLayout(
				"hidemode 3",
				// columns
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]",
				// rows
				"[]" +
				"[]" +
				"[]" +
				"[]" +
				"[]" +
				"[]" +
				"[]"));

			//---- label1 ----
			label1.setText("e-mail");
			panel1.add(label1, "cell 1 1");
			panel1.add(textCorreo, "cell 4 1");

			//---- label2 ----
			label2.setText("clave: ");
			panel1.add(label2, "cell 1 2");
			panel1.add(textClave, "cell 4 2");

			//---- button1 ----
			button1.setText("Ingreso");
			button1.addActionListener(e -> {
			try {
				button1ActionPerformed(e);
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				button1ActionPerformed(e);
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
			panel1.add(button1, "cell 4 6");
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Andrés
	private JPanel panel1;
	private JLabel label1;
	private JTextField textCorreo;
	private JLabel label2;
	private JTextField textClave;
	private JButton button1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
