package com.vista;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.entities.Usuario;
import com.enumerados.BorradoLogico.Estado;

public class HaceTablas {

	public DefaultTableModel modelo (String[] nombresColumnas, String[][] renglones ) {
		
		DefaultTableModel modelo = new DefaultTableModel();
		//crea un array que contiene los nombre de las columnas
		final String[] columnNames = nombresColumnas;
		// insertamos las columnas
		for(int column = 0; column < columnNames.length; column++){
			//agrega las columnas a la tabla
			modelo.addColumn(columnNames[column]);
		}
		// Se crea un array que será una de las filas de la tabla. 
		Object [] fila = new Object[columnNames.length]; 
		// Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
		for (int i=0;i<renglones.length;i++){
			fila[0] = id; 
			fila[1] = nombre; 
			fila[2] = apellido; 
			fila[3] = mail;
			modelo.addRow(fila); 
			
		}
		 
		final JTable table = new JTable(modelo);
		// Se añade al modelo la fila completa.

		//se define el tamaño de la tabla
		table.setPreferredScrollableViewportSize(new Dimension(600, 200));
		//Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(table);
		//crea el panel
		JPanel panel = new JPanel();
				
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(19, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
	
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					volver(usuarioLoged);
				} catch (NamingException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Long id;
					if (table.getSelectedRow() != -1) {
						id = (Long) table.getValueAt(table.getSelectedRow(), 0);
					}else {
						id = null;
					}
					editar(usuarioLoged, id);
				} catch (NamingException e1) {
					e1.printStackTrace();
				}
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnVolver)
						.addComponent(btnEditar))
					.addContainerGap(65, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnVolver)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEditar)
					.addContainerGap(144, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
		frame.pack();

	}
	
	
	

