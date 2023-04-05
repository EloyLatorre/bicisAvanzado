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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;

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
	private JTextField textFieldNombre;
	private JTextField textFieldIdUsuario;
	private JTextField textFieldTelef;
	private JTextField textFieldDni;
	private JTable JTableUser;
	private JTable JTableBici;
	private JTextField textFieldIDBici;
	private JTextField textFieldIdUsuarioAlquilar;

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

		// MOSTRAR USUARIOS
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID Usuario");
		model.addColumn("Nombre");
		model.addColumn("Teléfono");
		model.addColumn("DNI");
		model.addColumn("ID Bicicleta");

		try {
			model.setRowCount(0);
			Connection con = ConnectionSingleton.getConnection();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");

			while (rs.next()) {
				Object[] row = new Object[5];
				row[0] = rs.getInt("id_usuario");
				row[1] = rs.getString("nombre");
				row[2] = rs.getString("telefono");
				row[3] = rs.getString("dni");
				row[4] = rs.getInt("id_bicicleta");
				model.addRow(row);
			}

			stmt.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		JTableUser = new JTable(model);
		JTableUser.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		JScrollPane scrollPaneUser = new JScrollPane(JTableUser);
		scrollPaneUser.setBounds(599, 199, 393, 150);

		frmAusiasBici.getContentPane().add(scrollPaneUser);

		// MOSTRAR BICIS
		DefaultTableModel modelB = new DefaultTableModel();
		modelB.addColumn("ID Bicicleta");
		modelB.addColumn("Disponibilidad");

		try {
			modelB.setRowCount(0);
			Connection con = ConnectionSingleton.getConnection();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM bicicleta");

			while (rs.next()) {
				Object[] row = new Object[2];
				row[0] = rs.getInt("id_bicicleta");
				row[1] = rs.getString("disponibilidad");

				modelB.addRow(row);
			}

			stmt.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		JTableBici = new JTable(modelB);
		JTableBici.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		JScrollPane scrollPaneBici = new JScrollPane(JTableBici);
		scrollPaneBici.setBounds(599, 475, 393, 150);

		frmAusiasBici.getContentPane().add(scrollPaneBici);

		JLabel lblAusiasM = new JLabel("Alquiler de bicicletas Ausiàs March");
		lblAusiasM.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 20));
		lblAusiasM.setBounds(375, 53, 393, 55);
		frmAusiasBici.getContentPane().add(lblAusiasM);

		// Cada vez que pulsamos en una opcion mostrar los componentes necesarios
		// PANELES
		JPanel panelTotal = new JPanel();
		panelTotal.setBounds(43, 331, 522, 367);
		frmAusiasBici.getContentPane().add(panelTotal);
		panelTotal.setLayout(new CardLayout(0, 0));

		JPanel panelAddUser = new JPanel();
		panelTotal.add(panelAddUser, "name_1028625079080600");
		panelAddUser.setLayout(null);
		panelAddUser.setVisible(false);

		JPanel panelAddBici = new JPanel();
		panelTotal.add(panelAddBici, "name_1028714407154100");
		panelAddBici.setLayout(null);

		JPanel panelAlquilar = new JPanel();
		panelTotal.add(panelAlquilar, "name_1028674238365400");
		panelAlquilar.setLayout(null);

		JPanel panelDevolver = new JPanel();
		panelTotal.add(panelDevolver, "name_1028689232643000");
		panelDevolver.setLayout(null);

		// MOSTRAR USUARIOS
		JButton btnMostrarUsuario = new JButton("Mostrar usuarios");
		btnMostrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					Connection con = ConnectionSingleton.getConnection();

					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
					model.setRowCount(0);
					while (rs.next()) {
						Object[] row = new Object[5];
						row[0] = rs.getInt("id_usuario");
						row[1] = rs.getString("nombre");
						row[2] = rs.getString("telefono");
						row[3] = rs.getString("dni");
						row[4] = rs.getInt("id_bicicleta");
						model.addRow(row);
					}

					stmt.close();
					rs.close();
					con.close();
				} catch (SQLException e5) {
					JOptionPane.showMessageDialog(null, e5.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnMostrarUsuario.setBounds(599, 138, 162, 25);
		frmAusiasBici.getContentPane().add(btnMostrarUsuario);

		// MOSTRAR BICIS
		JButton btnMostrarBicis = new JButton("Mostrar bicicletas");
		btnMostrarBicis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					Connection con = ConnectionSingleton.getConnection();

					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM bicicleta");
					modelB.setRowCount(0);
					while (rs.next()) {
						Object[] row = new Object[2];
						row[0] = rs.getInt("id_bicicleta");
						row[1] = rs.getString("disponibilidad");

						modelB.addRow(row);
					}

					stmt.close();
					rs.close();
					con.close();
				} catch (SQLException e3) {
					JOptionPane.showMessageDialog(null, e3.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		btnMostrarBicis.setBounds(599, 414, 162, 25);
		frmAusiasBici.getContentPane().add(btnMostrarBicis);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(52, 111, 65, 24);
		panelAddUser.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(217, 113, 237, 20);
		panelAddUser.add(textFieldNombre);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(52, 59, 106, 24);
		panelAddUser.add(lblId);

		textFieldIdUsuario = new JTextField();
		textFieldIdUsuario.setEditable(false);
		textFieldIdUsuario.setColumns(10);
		textFieldIdUsuario.setBounds(217, 62, 237, 20);
		panelAddUser.add(textFieldIdUsuario);

		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(52, 163, 106, 24);
		panelAddUser.add(lblTelefono);

		textFieldTelef = new JTextField();
		textFieldTelef.setColumns(10);
		textFieldTelef.setBounds(217, 164, 237, 20);
		panelAddUser.add(textFieldTelef);

		JLabel lbldni = new JLabel("DNI:");
		lbldni.setBounds(52, 215, 65, 24);
		panelAddUser.add(lbldni);

		textFieldDni = new JTextField();
		textFieldDni.setColumns(10);
		textFieldDni.setBounds(217, 215, 237, 20);
		panelAddUser.add(textFieldDni);

		JComboBox comboBoxDevolver = new JComboBox();
		comboBoxDevolver.setBounds(150, 160, 195, 22);
		panelDevolver.add(comboBoxDevolver);

		JLabel lblIdBiciAlquilar = new JLabel("ID bicicleta a alquilar:");
		lblIdBiciAlquilar.setBounds(57, 161, 175, 14);
		panelAlquilar.add(lblIdBiciAlquilar);

		JLabel lblIdUsuarioAlquilar = new JLabel("ID usuario que desea alquilar:");
		lblIdUsuarioAlquilar.setBounds(57, 95, 214, 14);
		panelAlquilar.add(lblIdUsuarioAlquilar);

		textFieldIdUsuarioAlquilar = new JTextField();
		textFieldIdUsuarioAlquilar.setColumns(10);
		textFieldIdUsuarioAlquilar.setBounds(319, 83, 151, 20);
		panelAlquilar.add(textFieldIdUsuarioAlquilar);

		JLabel lblNewLabel_1 = new JLabel("Selecciona el ID de la bici a devolver:");
		lblNewLabel_1.setBounds(113, 106, 323, 16);
		panelDevolver.add(lblNewLabel_1);

		JComboBox comboBoxIdBiciAlquilar = new JComboBox();
		comboBoxIdBiciAlquilar.setBounds(319, 157, 151, 22);
		panelAlquilar.add(comboBoxIdBiciAlquilar);

		textFieldIDBici = new JTextField();
		textFieldIDBici.setBounds(128, 173, 235, 26);
		panelAddBici.add(textFieldIDBici);
		textFieldIDBici.setColumns(10);

		JLabel lblOpcion = new JLabel("Opción a realizar:");
		lblOpcion.setBounds(60, 139, 187, 23);
		frmAusiasBici.getContentPane().add(lblOpcion);

		// AÑADIR USUARIO
		JButton btnAddUsuario = new JButton("Añadir");
		btnAddUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					String nombre = textFieldNombre.getText();
					String telefono = textFieldTelef.getText();
					String dni = textFieldDni.getText();

					if (nombre == null || nombre.isEmpty() || nombre.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Introduce un nombre");
						textFieldNombre.requestFocus();
						return;
					}
					if (telefono == null || telefono.isEmpty() || telefono.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Introduce un teléfono");
						textFieldTelef.requestFocus();
						return;
					}
					if (dni == null || dni.isEmpty() || dni.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Introduce un DNI");
						textFieldDni.requestFocus();
						return;
					}

					// Obtener la conexión a la base de datos
					Connection con = ConnectionSingleton.getConnection();

					// Crear la sentencia SQL para insertar un nuevo producto

					PreparedStatement pst = con.prepareStatement(
							"INSERT INTO usuario (nombre, telefono, dni, id_bicicleta) VALUES (?, ?, ?, ?)");
					pst.setString(1, textFieldNombre.getText());
					pst.setInt(2, Integer.parseInt(textFieldTelef.getText()));
					pst.setString(3, textFieldDni.getText());
					pst.setInt(4, 0);

					// Ejecutar la sentencia SQL
					pst.executeUpdate();

					btnMostrarUsuario.doClick();
					JOptionPane.showMessageDialog(null, "Usuario añadido"); // Caso OK

					textFieldNombre.setText("");
					textFieldTelef.setText("");
					textFieldDni.setText("");

					btnMostrarUsuario.doClick();

					pst.close();
					con.close();
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnAddUsuario.setBounds(193, 289, 89, 23);
		panelAddUser.add(btnAddUsuario);

		// AÑADIR BICI
		JButton btnNuevaBici = new JButton("Añadir");
		btnNuevaBici.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String idBici = textFieldIDBici.getText();

				if (idBici == null || idBici.isEmpty() || idBici.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Introduce un ID válido");
					textFieldIDBici.requestFocus();
					return;
				}

				try {
					// Obtener la conexión a la base de datos
					Connection con = ConnectionSingleton.getConnection();

					// Comprobar que existe bici y user
					PreparedStatement exist_b_pstmt = con
							.prepareStatement("SELECT id_bicicleta FROM bicicleta WHERE id_bicicleta=?");
					exist_b_pstmt.setInt(1, Integer.parseInt(idBici));
					ResultSet exist_b = exist_b_pstmt.executeQuery();
					if (!exist_b.next()) {
						// Crear la sentencia SQL para insertar una nueva bicicleta
						PreparedStatement stmt = con
								.prepareStatement("INSERT INTO bicicleta (id_bicicleta, disponibilidad) VALUES (?, ?)");

						stmt.setString(1, idBici);
						stmt.setString(2, "true"); // Especifica que la bicicleta no está alquilada al crearla

						// Ejecutar la sentencia SQL
						stmt.executeUpdate();

					} else {
						JOptionPane.showMessageDialog(null, "Ya existe una bicicleta con ese ID", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

				// Actualizar la lista de bicicletas mostrada en pantalla
				btnMostrarBicis.doClick();
				
				
				
				// PARA ALQUILER COMBOBOX
				try {
					// Obtener una conexión a la base de datos
					Connection con = ConnectionSingleton.getConnection();

					// Crear un objeto Statement o PreparedStatement
					Statement stmt = con.createStatement();

					// Ejecutar la consulta SQL y obtener un ResultSet
					ResultSet rs = stmt.executeQuery("SELECT id_bicicleta FROM bicicleta WHERE disponibilidad = 'true' ");

					// Borrar los elementos existentes del JComboBox
					comboBoxIdBiciAlquilar.removeAllItems();

					// Iterar a través de los resultados y agregarlos al JComboBox
					while (rs.next()) {
						comboBoxIdBiciAlquilar.addItem(rs.getString("id_bicicleta"));
					}

					// Cerrar el ResultSet, el Statement y la conexión
					rs.close();
					stmt.close();
					con.close();
				} catch (SQLException e7) {
					JOptionPane.showMessageDialog(null, e7.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnNuevaBici.setBounds(180, 231, 117, 29);
		panelAddBici.add(btnNuevaBici);

		JLabel lblBici = new JLabel("Introduzca el ID de la nueva bicicleta:");
		lblBici.setBounds(113, 111, 329, 20);
		panelAddBici.add(lblBici);

		// ALQUILAR
		JButton btnAlquilar = new JButton("Alquilar");
		btnAlquilar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// Obtener la conexión a la base de datos
					Connection con = ConnectionSingleton.getConnection();

					// valor seleccionado en el JComboBox
					int id_bici = Integer.parseInt((String) comboBoxIdBiciAlquilar.getSelectedItem());

					int idUsuario = Integer.parseInt(textFieldIdUsuarioAlquilar.getText());

					if (comboBoxIdBiciAlquilar.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "Seleccione una bicicleta válida.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Verificar si el usuario ya tiene una bicicleta asignada
					PreparedStatement pstmt1 = con
							.prepareStatement("SELECT id_bicicleta FROM usuario WHERE id_usuario = ?");
					pstmt1.setInt(1, idUsuario);
					ResultSet rs1 = pstmt1.executeQuery();
					if (rs1.next()) {
						int idBiciAsignada = rs1.getInt("id_bicicleta");
						if (idBiciAsignada > 0) {
							JOptionPane.showMessageDialog(null, "Este usuario ya tiene una bicicleta asignada.",
									"Error", JOptionPane.ERROR_MESSAGE);
							rs1.close();
							pstmt1.close();
							con.close();
							return;
						}
					}
					rs1.close();
					pstmt1.close();

					PreparedStatement pstmt = con
							.prepareStatement("UPDATE usuario SET id_bicicleta = ? WHERE id_usuario = ?");
					pstmt.setInt(1, id_bici);
					pstmt.setInt(2, idUsuario);

					pstmt.executeUpdate();
					pstmt.close();

					// Cambiar el estado de la bicicleta a "false" en la tabla "bicicleta"
					PreparedStatement pstmt2 = con
							.prepareStatement("UPDATE bicicleta SET disponibilidad = 'false' WHERE id_bicicleta = ?");
					pstmt2.setInt(1, id_bici);
					pstmt2.executeUpdate();
					pstmt2.close();

					// Eliminar y actualizar el JComboBox con los códigos de bicicleta restantes
					comboBoxIdBiciAlquilar.removeAllItems();
					Statement stmt = con.createStatement();
					ResultSet rs = stmt
							.executeQuery("SELECT id_bicicleta FROM bicicleta WHERE disponibilidad = 'true'");
					while (rs.next()) {
						comboBoxIdBiciAlquilar.addItem(rs.getInt("id_bicicleta"));
					}

					// Ejecutar el botón "Mostrar" para actualizar la tabla
					btnMostrarBicis.doClick();
					btnMostrarUsuario.doClick();
					// Mostrar un mensaje de éxito
					JOptionPane.showMessageDialog(null, "Alquiler con éxito");

					rs.close();
					stmt.close();
					con.close();

				} catch (SQLException e3) {
					JOptionPane.showMessageDialog(null, e3.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				// Cargar IDs de bicicletas disponibles en el ComboBox
				try {
					Connection con = ConnectionSingleton.getConnection();

					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT id_bicicleta FROM bicicleta WHERE disponibilidad = 'false'");
					comboBoxDevolver.removeAllItems();

					while (rs.next()) {
						int idBici = rs.getInt("id_bicicleta");
						comboBoxDevolver.addItem(rs.getString("id_bicicleta"));
					}
					rs.close();
					stmt.close();
					con.close();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnAlquilar.setBounds(193, 246, 89, 25);
		panelAlquilar.add(btnAlquilar);

		// DEVOLVER
		JButton btnDevolver = new JButton("Devolver");
		btnDevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// Obtener la conexión a la base de datos
					Connection con = ConnectionSingleton.getConnection();

					// valor seleccionado en el JComboBox
					int id_bici = Integer.parseInt((String) comboBoxDevolver.getSelectedItem());

					// Obtener el id_usuario del usuario que tiene asignada la bicicleta
					PreparedStatement pstmt1 = con
							.prepareStatement("SELECT id_usuario FROM usuario WHERE id_bicicleta = ?");
					pstmt1.setInt(1, id_bici);
					ResultSet rs1 = pstmt1.executeQuery();
					if (rs1.next()) {
						int id_usuario = rs1.getInt("id_usuario");

						// Actualizar el id_bicicleta del usuario a 0
						PreparedStatement pstmt2 = con
								.prepareStatement("UPDATE usuario SET id_bicicleta = 0 WHERE id_usuario = ?");
						pstmt2.setInt(1, id_usuario);
						pstmt2.executeUpdate();
						pstmt2.close();
					}
					rs1.close();
					pstmt1.close();

					// Cambiar el estado de la bicicleta a "true" en la tabla "bicicleta"
					PreparedStatement pstmt3 = con
							.prepareStatement("UPDATE bicicleta SET disponibilidad = 'true' WHERE id_bicicleta = ?");
					pstmt3.setInt(1, id_bici);
					pstmt3.executeUpdate();
					pstmt3.close();

					// Eliminar y actualizar el JComboBox con los códigos de bicicleta restantes
					comboBoxIdBiciAlquilar.removeAllItems();
					Statement stmt = con.createStatement();

					ResultSet rs = stmt
							.executeQuery("SELECT id_bicicleta FROM bicicleta WHERE disponibilidad = 'false'");
					while (rs.next()) {
						comboBoxIdBiciAlquilar.addItem(rs.getInt("id_bicicleta"));
					}

					// Ejecutar el botón "Mostrar" para actualizar la tabla
					btnMostrarBicis.doClick();
					btnMostrarUsuario.doClick();

					// Mostrar un mensaje de éxito
					JOptionPane.showMessageDialog(null, "Devolución con éxito");

					rs.close();
					stmt.close();
					con.close();

				} catch (SQLException e3) {
					JOptionPane.showMessageDialog(null, e3.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				// Cargar IDs de bicicletas disponibles en el ComboBox
				try {
					Connection con = ConnectionSingleton.getConnection();

					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT id_bicicleta FROM bicicleta WHERE disponibilidad = 'false'");
					comboBoxDevolver.removeAllItems();

					while (rs.next()) {
						int idBici = rs.getInt("id_bicicleta");
						comboBoxDevolver.addItem(rs.getString("id_bicicleta"));
					}
					rs.close();
					stmt.close();
					con.close();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				// PARA ALQUILER COMBOBOX
				try {
					// Obtener una conexión a la base de datos
					Connection con = ConnectionSingleton.getConnection();

					// Crear un objeto Statement o PreparedStatement
					Statement stmt = con.createStatement();

					// Ejecutar la consulta SQL y obtener un ResultSet
					ResultSet rs = stmt.executeQuery("SELECT id_bicicleta FROM bicicleta WHERE disponibilidad = 'true' ");

					// Borrar los elementos existentes del JComboBox
					comboBoxIdBiciAlquilar.removeAllItems();

					// Iterar a través de los resultados y agregarlos al JComboBox
					while (rs.next()) {
						comboBoxIdBiciAlquilar.addItem(rs.getString("id_bicicleta"));
					}

					// Cerrar el ResultSet, el Statement y la conexión
					rs.close();
					stmt.close();
					con.close();
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}

		});

		btnDevolver.setBounds(181, 229, 117, 29);
		panelDevolver.add(btnDevolver);

		JButton btnRellenarUsuario = new JButton("Añadir usuario");
		btnRellenarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAddUser.setVisible(true);
				panelAddBici.setVisible(false);
				panelAlquilar.setVisible(false);
				panelDevolver.setVisible(false);

			}
		});
		btnRellenarUsuario.setBounds(83, 200, 181, 25);
		frmAusiasBici.getContentPane().add(btnRellenarUsuario);

		JButton btnRellenarBici = new JButton("Añadir bicicleta");
		btnRellenarBici.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				panelAddUser.setVisible(false);
				panelAlquilar.setVisible(false);
				panelDevolver.setVisible(false);
				panelAddBici.setVisible(true);

			}
		});
		btnRellenarBici.setBounds(332, 200, 181, 25);
		frmAusiasBici.getContentPane().add(btnRellenarBici);

		JButton btnDevolverBicicleta = new JButton("Devolver bicicleta");
		btnDevolverBicicleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panelAddUser.setVisible(false);
				panelAddBici.setVisible(false);
				panelAlquilar.setVisible(false);
				panelDevolver.setVisible(true);

			}

		});

		btnDevolverBicicleta.setBounds(332, 266, 181, 25);
		frmAusiasBici.getContentPane().add(btnDevolverBicicleta);

		JButton btnAlquilarBicicleta = new JButton("Alquilar bicicleta");
		btnAlquilarBicicleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAddUser.setVisible(false);
				panelAddBici.setVisible(false);
				panelAlquilar.setVisible(true);
				panelDevolver.setVisible(false);
			}
		});
		btnAlquilarBicicleta.setBounds(83, 266, 181, 25);
		frmAusiasBici.getContentPane().add(btnAlquilarBicicleta);

		// PARA ALQUILER COMBOBOX
		try {
			// Obtener una conexión a la base de datos
			Connection con = ConnectionSingleton.getConnection();

			// Crear un objeto Statement o PreparedStatement
			Statement stmt = con.createStatement();

			// Ejecutar la consulta SQL y obtener un ResultSet
			ResultSet rs = stmt.executeQuery("SELECT id_bicicleta FROM bicicleta WHERE disponibilidad = 'true' ");

			// Borrar los elementos existentes del JComboBox
			comboBoxIdBiciAlquilar.removeAllItems();

			// Iterar a través de los resultados y agregarlos al JComboBox
			while (rs.next()) {
				comboBoxIdBiciAlquilar.addItem(rs.getString("id_bicicleta"));
			}

			// Cerrar el ResultSet, el Statement y la conexión
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		// Cargar IDs de bicicletas disponibles en el ComboBox
		try {
			Connection con = ConnectionSingleton.getConnection();

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id_bicicleta FROM bicicleta WHERE disponibilidad = 'false'");
			comboBoxDevolver.removeAllItems();

			while (rs.next()) {
				int idBici = rs.getInt("id_bicicleta");
				comboBoxDevolver.addItem(rs.getString("id_bicicleta"));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

//	// Load Table
//		public void loadData() {
//			try {
//				
//			    Connection con = ConnectionSingleton.getConnection();
//			    PreparedStatement pstmt3 = con.prepareStatement("SELECT * FROM bicicleta AND usuarios");
//
//			    ResultSet rs = pstmt3.executeQuery();
//			    JTableBici.setModel(Ampliacion.resultSetToTableModel(rs));
//			    JTableUser.setModel(Ampliacion.resultSetToTableModel(rs));
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//		}
}
