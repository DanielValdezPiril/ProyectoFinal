import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 556, 387);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Nueva entidad ");
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
		btnNewButton.setBounds(50, 69, 180, 33);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Agregar Atributos");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			//agregar atributos
				try {
					CrearAtributos frame = new CrearAtributos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
		btnNewButton_1.setBounds(278, 69, 139, 33);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Ingresar Datos");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			//agregar datos
				try {
					IngresarDatos frame = new IngresarDatos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
		
		btnNewButton_2.setBounds(62, 162, 133, 33);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Mostrar Datos");
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
		btnNewButton_3.setBounds(61, 220, 122, 33);
		frame.getContentPane().add(btnNewButton_3);
	}
}
