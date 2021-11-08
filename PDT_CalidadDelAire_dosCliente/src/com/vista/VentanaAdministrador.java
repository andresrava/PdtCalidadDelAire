/*
 * Created by JFormDesigner on Sun Nov 07 20:51:06 ART 2021
 */

package com.vista;

import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Andrés
 */
public class VentanaAdministrador extends JPanel {
	public VentanaAdministrador() {
		initComponents();
	}

	private void button1ActionPerformed(ActionEvent e) {
	VentanaGestionUsuarios ventanaGestionUsuarios = new VentanaGestionUsuarios();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Andrés
		button1 = new JButton();
		button4 = new JButton();
		button3 = new JButton();
		button2 = new JButton();
		button5 = new JButton();

		//======== this ========
		setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
		EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing
		. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ),
		java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( )
		{ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () ))
		throw new RuntimeException( ); }} );
		setLayout(new MigLayout(
			"hidemode 3",
			// columns
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
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
			"[]"));

		//---- button1 ----
		button1.setText("Gesti\u00f3n de Usuarios");
		button1.addActionListener(e -> button1ActionPerformed(e));
		add(button1, "cell 9 1");

		//---- button4 ----
		button4.setText("Casillas");
		add(button4, "cell 9 2");

		//---- button3 ----
		button3.setText("Estaciones de Medici\u00f3n");
		add(button3, "cell 9 3");

		//---- button2 ----
		button2.setText("Formularios");
		add(button2, "cell 9 4");

		//---- button5 ----
		button5.setText("Actividad de Campo");
		add(button5, "cell 9 5");
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Andrés
	private JButton button1;
	private JButton button4;
	private JButton button3;
	private JButton button2;
	private JButton button5;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
