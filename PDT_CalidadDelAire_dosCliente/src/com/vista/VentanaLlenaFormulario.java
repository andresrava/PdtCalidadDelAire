package com.vista;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.entities.Casilla;
import com.entities.Formulario;
import com.entities.Usuario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Color;

import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaLlenaFormulario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaLlenaFormulario() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLlenaFormulario frame = new VentanaLlenaFormulario(usuarioLoged , formularioElegido);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static Usuario usuarioLoged;
	private static Formulario formularioElegido;
	private JButton btnVolver;
	private JButton btnIngresar;
	
	private JTextField text4;
	private JLabel lbl0;
	private JTextField text0;
	private JTextField text2;
	private JLabel lbl1;
	private JTextField text1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JTextField text3;
	private JLabel lbl4;
	private JLabel lbl5;
	private JTextField text5;
	private JLabel lbl6;
	private JTextField text6;
	private JLabel lbl7;
	private JTextField text7;
	
	
	/**
	 * Create the frame.
	 */
	public VentanaLlenaFormulario(Usuario usuarioLogedRef , Formulario formularioElegidoRef) {
		setTitle("Completa Actividad de Campo");
		VentanaLlenaFormulario.usuarioLoged = usuarioLogedRef;
		VentanaLlenaFormulario.formularioElegido = formularioElegidoRef;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("20px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				ColumnSpec.decode("424px:grow"),},
			new RowSpec[] {
				RowSpec.decode("24px"),
				RowSpec.decode("14px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				RowSpec.decode("31px"),
				RowSpec.decode("14px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		String nombreDelUsuario = usuarioLogedRef.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreDelUsuario);
		contentPane.add(lblNewLabel, "4, 2, fill, top");
		
		String nombreFormulario = formularioElegido.getNombre();
		JLabel lblNewLabel_1 = new JLabel("Formulario: " + nombreFormulario);
		contentPane.add(lblNewLabel_1, "4, 4, fill, top");
		
		List<Casilla> casillas = formularioElegido.getCasillas();
		
		btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				try {
					VentanaCreaActividad ventanaCreaActividad = new VentanaCreaActividad(usuarioLoged);
					ventanaCreaActividad.ventanaCreaActividad();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		text0 = new JTextField();
		text0.setVisible(false);
		text0.setColumns(10);
		if (casillas.size() > 0) {
			lbl0 = new JLabel(casillas.get(0).getParametro() + ": ");
			contentPane.add(lbl0, "3, 8, right, default");
			contentPane.add(text0, "4, 8, fill, default");
			text0.setVisible(true);
		}
		
		text1 = new JTextField();
		text1.setVisible(false);
		text1.setColumns(10);
		if (casillas.size() > 1) {
			lbl1 = new JLabel(casillas.get(1).getParametro() + ": ");
			contentPane.add(lbl1, "3, 10, right, default");
			contentPane.add(text1, "4, 10, fill, default");
			text1.setVisible(true);
		}
		
		text2 = new JTextField();
		text2.setVisible(false);
		text2.setColumns(10);
		if (casillas.size() > 2) {
			lbl2 = new JLabel(casillas.get(2).getParametro() + ": ");
			contentPane.add(lbl2, "3, 12, right, default");
			contentPane.add(text2, "4, 12, fill, default");
			text2.setVisible(true);
		}
		
		text3 = new JTextField();
		text3.setVisible(false);
		text3.setColumns(10);
		if (casillas.size() > 3) {
			lbl3 = new JLabel(casillas.get(3).getParametro() + ": ");
			contentPane.add(lbl3, "3, 14, right, default");
			contentPane.add(text3, "4, 14, fill, default");
			text3.setVisible(true);
		}
		
		text4 = new JTextField();
		text4.setVisible(false);
		text4.setColumns(10);
		if (casillas.size() > 4) {
			lbl4 = new JLabel(casillas.get(4).getParametro() + ": ");
			contentPane.add(lbl4, "3, 16, right, default");
			contentPane.add(text4, "4, 16, fill, default");
			text4.setVisible(true);
		}
		
		text5 = new JTextField();
		text5.setVisible(false);
		text5.setColumns(10);
		if (casillas.size() > 5) {
			lbl5 = new JLabel(casillas.get(5).getParametro() + ": ");
			contentPane.add(lbl5, "3, 18, right, default");
			contentPane.add(text5, "4, 18, fill, default");
			text5.setVisible(true);
		}
		
		text6 = new JTextField();
		text6.setVisible(false);
		text6.setColumns(10);
		if (casillas.size() > 6) {
			lbl6 = new JLabel(casillas.get(6).getParametro() + ": ");
			contentPane.add(lbl6, "3, 20, right, default");
			contentPane.add(text6, "4, 20, fill, default");
			text6.setVisible(true);
		}
		
		text7 = new JTextField();
		text7.setVisible(false);
		text7.setColumns(10);
		if (casillas.size() > 7) {
			lbl7 = new JLabel(casillas.get(7).getParametro() + ": ");
			contentPane.add(lbl7, "3, 22, right, default");
			contentPane.add(text7, "4, 22, fill, default");
			text7.setVisible(true);
		}
		

		contentPane.add(btnVolver, "3, 30");
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "No se ha implementado esta funcionalidad","Error", JOptionPane.WARNING_MESSAGE);
				
			}
		});
		contentPane.add(btnIngresar, "4, 30");
		

	}

}
