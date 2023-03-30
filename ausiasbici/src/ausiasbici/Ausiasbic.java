package ausiasbici;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Ausiasbic {

	private JFrame frmAusiasBici;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ausiasbic window = new Ausiasbic();
					window.frmAusiasBici.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ausiasbic() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAusiasBici = new JFrame();
		frmAusiasBici.setTitle("AUSIÀS BICI");
		frmAusiasBici.setBounds(100, 100, 1150, 680);
		frmAusiasBici.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAusiasBici.getContentPane().setLayout(null);
		
		JLabel lblAusiasM = new JLabel("Alquiler de bicicletas Ausiàs March");
		lblAusiasM.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 20));
		lblAusiasM.setBounds(375, 53, 340, 55);
		frmAusiasBici.getContentPane().add(lblAusiasM);
		
		//Cada vez que pulsamos en una opcion mostrar los componentes necesarios
		JPanel panelTotal = new JPanel();
		panelTotal.setBounds(37, 252, 520, 291);
		frmAusiasBici.getContentPane().add(panelTotal);
		panelTotal.setLayout(null);
		
		JPanel panelAddUser = new JPanel();
		panelAddUser.setBounds(10, 11, 500, 269);
		panelTotal.add(panelAddUser);
		panelAddUser.setLayout(null);
		//panelAddUser.setVisible(true);
		//Apilamos paneles para cada opcion segun el radiobtn?
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(54, 72, 65, 24);
		panelAddUser.add(lblNombre);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(178, 74, 237, 20);
		panelAddUser.add(textField);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(54, 24, 65, 24);
		panelAddUser.add(lblId);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(178, 26, 237, 20);
		panelAddUser.add(textField_1);
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(54, 120, 65, 24);
		panelAddUser.add(lblTelefono);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(178, 122, 237, 20);
		panelAddUser.add(textField_2);
		
		JLabel lbldni = new JLabel("DNI:");
		lbldni.setBounds(54, 168, 65, 24);
		panelAddUser.add(lbldni);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(178, 170, 237, 20);
		panelAddUser.add(textField_3);
		
		JLabel lblBicicletaAsociada = new JLabel("Bicicleta asociada:");
		lblBicicletaAsociada.setBounds(54, 216, 130, 24);
		panelAddUser.add(lblBicicletaAsociada);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(178, 218, 237, 20);
		panelAddUser.add(textField_4);
	
		
		//Dependiendo de si selecciona una opcion relacionada con bici o usuario en el Scroll mostrar tabla de cada una o las dos a la vez?
		//Ponemos que se muestre una tabla en el scroll predeterminada o lo dejamos en blanco hasta que se seleccione opcion
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(582, 252, 520, 291);
		frmAusiasBici.getContentPane().add(scrollPane);
		
		JLabel lblOpcion = new JLabel("Opción a relizar:");
		lblOpcion.setBounds(50, 148, 187, 23);
		frmAusiasBici.getContentPane().add(lblOpcion);
		
		JRadioButton rdbtnAddUser = new JRadioButton("Añadir usuario");
		rdbtnAddUser.setBounds(31, 195, 130, 23);
		frmAusiasBici.getContentPane().add(rdbtnAddUser);
		
		JRadioButton rdbtnAddBici = new JRadioButton("Añadir bicicleta");
		rdbtnAddBici.setBounds(163, 195, 130, 23);
		frmAusiasBici.getContentPane().add(rdbtnAddBici);
		
		JRadioButton rdbtnAlquilarBici = new JRadioButton("Alquilar bicicleta");
		rdbtnAlquilarBici.setBounds(295, 195, 130, 23);
		frmAusiasBici.getContentPane().add(rdbtnAlquilarBici);
		
		JRadioButton rdbtnDevolverBici = new JRadioButton("Devolver bicicleta");
		rdbtnDevolverBici.setBounds(427, 195, 130, 23);
		frmAusiasBici.getContentPane().add(rdbtnDevolverBici);
		
		// Groupo Opcion de radio buttons.
		ButtonGroup groupOpcion = new ButtonGroup();
		groupOpcion.add(rdbtnAddUser);
		groupOpcion.add(rdbtnAddBici);
		groupOpcion.add(rdbtnAlquilarBici);
		groupOpcion.add(rdbtnDevolverBici);
		
		
		JLabel lblOpcionMostrar = new JLabel("Opción a mostrar:");
		lblOpcionMostrar.setBounds(601, 152, 130, 14);
		frmAusiasBici.getContentPane().add(lblOpcionMostrar);
		
		JButton btnMostrarBicis = new JButton("Mostrar bicis");
		btnMostrarBicis.setBounds(666, 195, 138, 23);
		frmAusiasBici.getContentPane().add(btnMostrarBicis);
		
		JButton btnMostrarUsuarios = new JButton("Mostrar usuarios");
		btnMostrarUsuarios.setBounds(859, 195, 138, 23);
		frmAusiasBici.getContentPane().add(btnMostrarUsuarios);
		

	}
}
