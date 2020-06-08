import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class Menu {

	private JFrame frame;
	public static ManejoId idEntidad;
	public static ManejoId idAtributo;
	public static ManejoEntidad entidades;
	public static ManejoAtributo atributos;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					entidades = new ManejoEntidad();
					atributos = new ManejoAtributo();
					idEntidad = new ManejoId();
					idAtributo = new ManejoId();

					entidades.abrirArchivo("entidades.data");
					atributos.abrirArchivo("atributos.data");
					idEntidad.abrirArchivo("identidad.data");
					idAtributo.abrirArchivo("idatributo.data");

					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * 
	 */
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 139, 139));
		frame.getContentPane().setForeground(new Color(0, 191, 255));
		frame.setBounds(100, 100, 556, 387);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Nueva entidad ");  // Botón que nos permite crear una nueva entidad 
		btnNewButton.setBackground(new Color(95, 158, 160));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					NuevaEntidad frame = new NuevaEntidad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		});
		btnNewButton.setBounds(42, 69, 180, 44);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Agregar Atributos"); //Boton que nos permite crear los atributos para la entidad seleccionada 
		btnNewButton_1.setBackground(new Color(95, 158, 160));
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				try {
					CrearAtributos frame = new CrearAtributos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
		btnNewButton_1.setBounds(288, 69, 158, 44);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Ingresar Datos"); // Boton que nos permite ingresar datos a los atributos creados
		btnNewButton_2.setForeground(new Color(0, 0, 0));
		btnNewButton_2.setBackground(new Color(95, 158, 160));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				try {
					IngresarDatos frame = new IngresarDatos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
		
		btnNewButton_2.setBounds(42, 176, 145, 39);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Mostrar Datos"); // Boton para mostrar los datos ingresados por el usuario
		btnNewButton_3.setBackground(new Color(95, 158, 160));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					MostrarDato frame = new MostrarDato();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}	
				
			}
		});
		btnNewButton_3.setBounds(42, 240, 145, 33);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Cambiar Nombre Entidad");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ModificarNombre frame = new ModificarNombre();
					frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}	
			}
		});
		btnNewButton_4.setBounds(52, 128, 180, 23);
		frame.getContentPane().add(btnNewButton_4);
	}
}
