import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NuevaEntidad extends JFrame {

	private JPanel contentPane;
	private JTextField txtnombre;

	/**
	 * Create the frame.
	 */
	public NuevaEntidad() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingresa Nueva Entidad");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(28, 70, 153, 23);
		contentPane.add(lblNewLabel);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(191, 71, 158, 20);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int id = Menu.idEntidad.obtenerID();
				
				Entidad nuevaEntidad = new Entidad();
				nuevaEntidad.setNombre(txtnombre.getText());
				nuevaEntidad.setId(id);
				try {
					boolean resultado = Menu.entidades.agregarEntidad(nuevaEntidad);
					Menu.idEntidad.modificarID(id+1);
					if(resultado) {
						JOptionPane.showMessageDialog(null, "Entidad creada");
						txtnombre.setText("");
					}
					
				}catch(Exception e) {
					
				}
			}
			
		});
		btnNewButton.setBounds(168, 153, 89, 23);
		contentPane.add(btnNewButton);
	}
}
