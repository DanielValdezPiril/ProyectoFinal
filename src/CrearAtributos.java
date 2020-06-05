import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearAtributos extends JFrame {

	private JPanel contentPane;
	private JTextField txtnombre;
	private JTextField txttipo;
	private JTextField txtlongitud;

	

	/**
	 * Create the frame.
	 */
	public CrearAtributos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(35, 60, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(35, 108, 46, 21);
		contentPane.add(lblTipo);
		
		JLabel lblLongitud = new JLabel("Longitud");
		lblLongitud.setBounds(35, 161, 46, 14);
		contentPane.add(lblLongitud);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(90, 57, 86, 20);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);
		
		txttipo = new JTextField();
		txttipo.setColumns(10);
		txttipo.setBounds(91, 108, 86, 20);
		contentPane.add(txttipo);
		
		txtlongitud = new JTextField();
		txtlongitud.setColumns(10);
		txtlongitud.setBounds(90, 158, 86, 20);
		contentPane.add(txtlongitud);
		
		
		
		
		JComboBox list = new JComboBox();
		list.setBounds(90, 9, 175, 21);
		contentPane.add(list);
		
		JList list_1 = new JList();
		list_1.setBounds(83, 11, 144, 23);
		contentPane.add(list_1);
		
		
		ArrayList<Entidad> entidades = Menu.entidades.listarEntidad();
		for(Entidad item : entidades) {
			list.addItem(item.getId()+"-"+item.getNombre());
		} 
		
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String entidadSeleccionada = list.getSelectedItem().toString().split("-")[0];
				int idEntidad = Integer.parseInt(entidadSeleccionada);
				int idAtributo = Menu.idAtributo.obtenerID();
				Atributo atributo = new Atributo();
				atributo.setIdatributo(idAtributo);
				atributo.setIdentidad(idEntidad);
				atributo.setNombre(txtnombre.getText());
				atributo.setTipo(Integer.parseInt(txttipo.getText()));
				atributo.setLongitud(Integer.parseInt(txtlongitud.getText()));
				try {
					boolean resultado = Menu.atributos.agregarAtributo(atributo);
					Menu.idAtributo.modificarID(idAtributo+1);
					if(resultado) {
						JOptionPane.showMessageDialog(null, "Atributo agregado");
						txtnombre.setText("");
						txttipo.setText("");
						txtlongitud.setText("");
					}
					
				}catch(Exception e) {
					
				}
			}
		});
		btnNewButton.setBounds(245, 107, 89, 23);
		contentPane.add(btnNewButton);
		
	}
}
