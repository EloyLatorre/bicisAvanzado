package ausiasbici;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Ausiasbic {
	
	class ConnectionSingleton {
		private static Connection con;

		public static Connection getConnection() throws SQLException {
			String url = "jdbc:mysql://127.0.0.1:3307/ausias_bici";
			String user = "alumno";
			String password = "alumno";
			if (con == null || con.isClosed()) {
				con = DriverManager.getConnection(url, user, password);
			}
			return con;
		}
	}
	private JFrame frmAusiasBici;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable JTableUser;
	private JTable JTableBici;

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
		frmAusiasBici.setBounds(100, 100, 1060, 780);
		frmAusiasBici.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAusiasBici.getContentPane().setLayout(null);
		
		JLabel lblAusiasM = new JLabel("Alquiler de bicicletas Ausiàs March");
		lblAusiasM.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 20));
		lblAusiasM.setBounds(375, 53, 393, 55);
		frmAusiasBici.getContentPane().add(lblAusiasM);
		
		//Cada vez que pulsamos en una opcion mostrar los componentes necesarios
		JPanel panelTotal = new JPanel();
		panelTotal.setBounds(43, 350, 521, 312);
		frmAusiasBici.getContentPane().add(panelTotal);
		panelTotal.setLayout(null);
		
		JPanel panelAddUser = new JPanel();
		panelAddUser.setBounds(12, 11, 498, 289);
		panelTotal.add(panelAddUser);
		panelAddUser.setLayout(null);
		//panelAddUser.setVisible(true);
		//Apilamos paneles para cada opcion segun el radiobtn?
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(54, 80, 65, 24);
		panelAddUser.add(lblNombre);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(219, 82, 237, 20);
		panelAddUser.add(textField);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(54, 28, 65, 24);
		panelAddUser.add(lblId);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(219, 31, 237, 20);
		panelAddUser.add(textField_1);
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(54, 132, 106, 24);
		panelAddUser.add(lblTelefono);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(219, 133, 237, 20);
		panelAddUser.add(textField_2);
		
		JLabel lbldni = new JLabel("DNI:");
		lbldni.setBounds(54, 184, 65, 24);
		panelAddUser.add(lbldni);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(219, 184, 237, 20);
		panelAddUser.add(textField_3);
		
		JLabel lblBicicletaAsociada = new JLabel("Bicicleta asociada:");
		lblBicicletaAsociada.setBounds(54, 236, 170, 24);
		panelAddUser.add(lblBicicletaAsociada);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(219, 235, 237, 20);
		panelAddUser.add(textField_4);
		
		JLabel lblOpcion = new JLabel("Opción a realizar:");
		lblOpcion.setBounds(60, 139, 187, 23);
		frmAusiasBici.getContentPane().add(lblOpcion);
		
		
		JButton btnAddUsuario = new JButton("Añadir usuario");
		btnAddUsuario.setBounds(80, 217, 181, 25);
		frmAusiasBici.getContentPane().add(btnAddUsuario);
		
		JButton btnAddBici = new JButton("Añadir bicicleta");
		btnAddBici.setBounds(329, 217, 181, 25);
		frmAusiasBici.getContentPane().add(btnAddBici);
		
		JButton btnDevolverBicicleta = new JButton("Devolver bicicleta");
		btnDevolverBicicleta.setBounds(329, 283, 181, 25);
		frmAusiasBici.getContentPane().add(btnDevolverBicicleta);
		
		JButton btnAlquilarBicicleta = new JButton("Alquilar bicicleta");
		btnAlquilarBicicleta.setBounds(80, 283, 181, 25);
		frmAusiasBici.getContentPane().add(btnAlquilarBicicleta);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Ausiasbic.class.getResource("/img/bici150.png")));
		lblNewLabel.setBounds(50, 53, 136, 74);
		frmAusiasBici.getContentPane().add(lblNewLabel);
		
		JButton btnMostrarUsuario = new JButton("Mostrar usuarios");
		btnMostrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//MOSTRAR USUARIOS
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("codigo");
				model.addColumn("nombre");
				model.addColumn("precio");
				model.addColumn("unidad");
				
				try {
					model.setRowCount(0);
					Connection con=ConnectionSingleton.getConnection();
					
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
					
					while (rs.next()) {
					Object[] row = new Object[5];
					row[0] = rs.getInt("id_usuario");
					row[1] = rs.getString("nombre");
					row[2] = rs.getString("telefono");
					row[3] = rs.getInt("dni");
					row[4] = rs.getInt("id_bicicleta");
					model.addRow(row);			
					}
					
					stmt.close();
					rs.close();
					con.close();
				}catch(SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);	
				}

				JTableUser = new JTable(model);
				JTableUser.setBounds(571, 334, 237, -158);
				frmAusiasBici.getContentPane().add(JTableUser);
				JTableUser.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				JScrollPane scrollPaneUser = new JScrollPane(JTableUser);
				scrollPaneUser.setBounds(599, 199, 338, 127);
				frmAusiasBici.getContentPane().add(scrollPaneUser);
				
				
				
				
			}
		});
		btnMostrarUsuario.setBounds(599, 138, 162, 25);
		frmAusiasBici.getContentPane().add(btnMostrarUsuario);
		
		JButton btnMostrarBicis = new JButton("Mostrar bicicletas");
		btnMostrarBicis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//MOSTRAR BICIS
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("id_bicicleta");
				model.addColumn("disponibilidad");
	
				
				try {
					model.setRowCount(0);
					Connection con=ConnectionSingleton.getConnection();
					
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM bicicleta");
					
					while (rs.next()) {
					Object[] row = new Object[2];
					row[0] = rs.getInt("id_bicicleta");
					row[1] = rs.getString("disponibilidad");
					
					model.addRow(row);			
					}
					
					stmt.close();
					rs.close();
					con.close();
				}catch(SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);	
				}

				JTableBici = new JTable(model);
			
				JTableBici.setBounds(571, 334, 237, -158);
				frmAusiasBici.getContentPane().add(JTableBici);
				JTableBici.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				JScrollPane scrollPaneBici = new JScrollPane(JTableBici);
				scrollPaneBici.setBounds(599, 470, 338, 127);
				frmAusiasBici.getContentPane().add(scrollPaneBici);
				
				
			}
		});
		btnMostrarBicis.setBounds(599, 414, 162, 25);
		frmAusiasBici.getContentPane().add(btnMostrarBicis);
		

		

	}
}
