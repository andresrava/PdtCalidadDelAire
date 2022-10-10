package com.vista;

import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import com.controlador.GestionCasillas;
import com.controlador.GestionEstaciones;
import com.entities.Casilla;
import com.entities.EstacionDeMedicion;
import com.entities.Formulario;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VentanaListaCasillas extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaListaCasillas() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListaCasillas frame = new VentanaListaCasillas(usuarioLoged);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static Usuario usuarioLoged;
	private JTextField textNombreCasilla;
	DefaultTableModel modelo = new DefaultTableModel();
	JTable table = new JTable(modelo);
	JScrollPane scrollPane = new JScrollPane(table);
	JPanel panel_1 = new JPanel();
	
	
	
	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public VentanaListaCasillas(Usuario usuarioLogedRef) throws NamingException {
		setTitle("Lista Casillas");
		VentanaListaCasillas.usuarioLoged = usuarioLogedRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);	
		//Agrego el fondo
		contentPane = new PaneImage();		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String nombreUsuario = usuarioLoged.getNombre();
		JLabel lblNewLabel_3 = new JLabel("Usuario: " + nombreUsuario);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		//Creo el modelo de tabla con todas las estaciones
		GestionCasillas gestionCasillas = new GestionCasillas();
		List<Casilla> casillas = gestionCasillas.listaCasillas();

		HaceTablas haceTablas = new HaceTablas();
		modelo = haceTablas.haceTablaCasillas(casillas);
		
		//Agrego el modelo a la tabla
		table.setModel(modelo);
		
		panel_1.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Filtros: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Nombre: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textNombreCasilla = new JTextField();
		textNombreCasilla.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Est. de Medici\u00F3n");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		//Creo el combo con Estaciones de medición y lo lleno
		JComboBox<EstacionDeMedicion> comboEM = new JComboBox();
		comboEM.setSize( 350, 22 );
		List<EstacionDeMedicion> estacionesDeMedicion = new LinkedList<EstacionDeMedicion>();
		GestionEstaciones gestionEstaciones = new GestionEstaciones();

		try { 
			estacionesDeMedicion = gestionEstaciones.obtieneEM();
			for (EstacionDeMedicion EM: estacionesDeMedicion) {
				comboEM.addItem(EM); 
			} 
			comboEM.setSelectedIndex(-1);
		} catch (NamingException e) { // TODO Auto-generated catch block 
			e.printStackTrace();
		}
		  
		
		JButton btnAplicaFiltro = new JButton("Aplicar");
		btnAplicaFiltro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAplicaFiltro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nombreCasilla = "";
				if (textNombreCasilla != null)
					nombreCasilla = textNombreCasilla.getText();
				List<Casilla> listaCasillasNombre = new LinkedList<Casilla>();
				List<Casilla> listaCasillas = new LinkedList<Casilla>();
				try {
					listaCasillasNombre = gestionCasillas.listaCasillas(nombreCasilla);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				EstacionDeMedicion estacionSeleccionada = new EstacionDeMedicion();
				
				if (comboEM.getSelectedIndex() != -1) {
					estacionSeleccionada = (EstacionDeMedicion) comboEM.getSelectedItem();
					List<Casilla> listaCasillasEM = estacionSeleccionada.getCasillas();
					for (Casilla casilla : listaCasillasEM) {
						Long id = casilla.getId();
						for (Casilla casillaa : listaCasillasNombre) {
							if (casillaa.getId() == id) {
								listaCasillas.add(casillaa);							}
						}
					}
				}
				else {
					listaCasillas = listaCasillasNombre;
				}
				
				//Creo el modelo de tabla con las estaciones filtradas
				modelo= haceTablas.haceTablaCasillas(listaCasillas);
				//Agrego el modelo a la tabla				
				table.setModel(modelo);
								
			}
		});
		
		JButton btnLimpiaFiltros = new JButton("Limpia");
		btnLimpiaFiltros.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLimpiaFiltros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboEM.setSelectedIndex(-1);
				textNombreCasilla.setText(null);
				//Creo el modelo de tabla con las estaciones filtradas
				modelo= haceTablas.haceTablaCasillas(casillas);
				//Agrego el modelo a la tabla				
				table.setModel(modelo);
				
			}
		});
	
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnAplicaFiltro, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLimpiaFiltros, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(34)
							.addComponent(textNombreCasilla, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_2)
						.addComponent(comboEM, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textNombreCasilla, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboEM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnLimpiaFiltros, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAplicaFiltro, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
					.addGap(7))
		);
		panel.setLayout(gl_panel);
		
		

		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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

		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Casilla casilla = new Casilla();
				int id = 0;
				if (table.getSelectedRow() != -1) {
					id = table.getSelectedRow();
					casilla = casillas.get(id);
					String nombreCasillaAEliminar = casilla.getNombre();
					try {
						List<Formulario> formularios = gestionCasillas.revisaCasilla(casilla.getId());
						int cantForm = 	formularios.size();					
							if (cantForm != 0) {
								JOptionPane.showMessageDialog(null, "Esta Casilla está en: " + cantForm + " formulario(s). No se puede eliminar");	
							}
							else {
								int confirmacion =  JOptionPane.showConfirmDialog(null,"Realmente desea Eliminar la Casilla: " + nombreCasillaAEliminar + "?", "Confirmar la eliminación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
								if (confirmacion == 0)
									{
									
										try {
											gestionCasillas.borrarCasilla(casilla);
											JFrame jFrame = new JFrame();
									        JOptionPane.showMessageDialog(jFrame, "Se eliminó la Casilla: " + nombreCasillaAEliminar);
									        dispose();
											VentanaListaCasillas frame = new VentanaListaCasillas(usuarioLoged);
											frame.setVisible(true);
										} catch (NamingException | ServiciosException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
							}
						
					} catch (NamingException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					
				}else {
					JOptionPane.showMessageDialog(null, "Seleccione la Casilla a eliminar");
					dispose();
					VentanaListaEM frame = new VentanaListaEM(usuarioLoged);
					frame.setVisible(true);
				}
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 456, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(52)
							.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel_3)
					.addGap(11)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(67)
					.addComponent(btnVolver, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(104))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(17))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
