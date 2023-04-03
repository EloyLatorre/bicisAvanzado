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
	private JTextField textFieldIdBiciAlquilar;
	private JTextField textFieldIdUsuarioAlquilar;
	private JTextField textFieldIDBiciDevolver;

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

	private void btnDevolverBicicletaActionPerformed(java.awt.event.ActionEvent evt) {                                                     
	    // Mostrar formulario para ingresar el ID de la bicicleta
	    JTextField textFieldIDBici = new JTextField();
	    Object[] message = {
	        "ID de la bicicleta:", textFieldIDBici
	    };
	    int option = JOptionPane.showConfirmDialog(null, message, "Devolver bicicleta", JOptionPane.OK_CANCEL_OPTION);
	    if (option == JOptionPane.OK_OPTION) {
	        String idBici = textFieldIDBici.getText();
	        // Buscar la bicicleta en la base de datos
	        try {
	            Connection con = ConnectionSingleton.getConnection();
	            Statement stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT * FROM bicicletas WHERE id=" + idBici);
	            if (rs.next()) {
	                // Si la bicicleta está alquilada, marcarla como disponible
	                if (rs.getBoolean("alquilada")) {
	                    PreparedStatement updateStmt = con.prepareStatement("UPDATE bicicleta SET disponibilidad=false WHERE id=?");
	                    updateStmt.setString(1, idBici);
	                    updateStmt.executeUpdate();
	                    JOptionPane.showMessageDialog(null, "Bicicleta devuelta con éxito.");
	                } else {
	                    JOptionPane.showMessageDialog(null, "La bicicleta no está alquilada.");
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "No se encontró la bicicleta con ID " + idBici + ".");
	            }
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, "Error al buscar la bicicleta en la base de datos: " + ex.getMessage());
	        }
	    }
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
		//PANELES
		JPanel panelTotal = new JPanel();
		panelTotal.setBounds(43, 331, 522, 367);
		frmAusiasBici.getContentPane().add(panelTotal);
		panelTotal.setLayout(new CardLayout(0, 0));
		
		JPanel panelAddUser = new JPanel();
		panelTotal.add(panelAddUser, "name_1028625079080600");
		panelAddUser.setLayout(null);
		panelAddUser.setVisible(false);
		
		
		
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
		
		JButton btnMostrarUsuario = new JButton("Mostrar usuarios");
		btnMostrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//MOSTRAR USUARIOS
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("ID Usuario");
				model.addColumn("Nombre");
				model.addColumn("Teléfono");
				model.addColumn("DNI");
				model.addColumn("ID Bicicleta");
				
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
					row[3] = rs.getString("dni");
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
				scrollPaneUser.setBounds(599, 199, 380, 130);
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
				model.addColumn("ID Bicicleta");
				model.addColumn("Disponibilidad");
	
				
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
				scrollPaneBici.setBounds(599, 475, 380, 130);
				frmAusiasBici.getContentPane().add(scrollPaneBici);
				
				
			}
		});
		btnMostrarBicis.setBounds(599, 414, 162, 25);
		frmAusiasBici.getContentPane().add(btnMostrarBicis);
		
		
		JButton btnAddUsuario = new JButton("Añadir");
		btnAddUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					//AÑADIR USUARIO

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
					Connection con=ConnectionSingleton.getConnection();
					
		            // Crear la sentencia SQL para insertar un nuevo producto

					 PreparedStatement pst = con.prepareStatement("INSERT INTO usuario (nombre, telefono, dni, id_bicicleta) VALUES (?, ?, ?, ?)");
					pst.setString(1,textFieldNombre.getText());
					pst.setInt(2,Integer.parseInt(textFieldTelef.getText()));
					pst.setString(3,textFieldDni.getText());
					pst.setInt(4, 0);
					
					// Ejecutar la sentencia SQL
					pst.executeUpdate();
					
					// Actualizar la lista de productos mostrada en la interfaz gráfica
					btnMostrarUsuario.doClick();
					
					JOptionPane.showMessageDialog(null, "Usuario añadido"); //Caso OK

					textFieldNombre.setText("");
					textFieldTelef.setText("");
					textFieldDni.setText("");

					
					pst.close();
					con.close();
				}catch(SQLException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

				
			}
		});
		btnAddUsuario.setBounds(193, 289, 89, 23);
		panelAddUser.add(btnAddUsuario);
		
		JPanel panelAlquilar = new JPanel();
		panelTotal.add(panelAlquilar, "name_1028674238365400");
		panelAlquilar.setLayout(null);
		
		JLabel lblIdBiciAlquilar = new JLabel("ID bicicleta a alquilar:");
		lblIdBiciAlquilar.setBounds(54, 161, 175, 14);
		panelAlquilar.add(lblIdBiciAlquilar);
		
		textFieldIdBiciAlquilar = new JTextField();
		textFieldIdBiciAlquilar.setBounds(239, 158, 237, 20);
		panelAlquilar.add(textFieldIdBiciAlquilar);
		textFieldIdBiciAlquilar.setColumns(10);
		
		JLabel lblIdUsuarioAlquilar = new JLabel("ID usuario que desea alquilar:");
		lblIdUsuarioAlquilar.setBounds(54, 96, 185, 14);
		panelAlquilar.add(lblIdUsuarioAlquilar);
		
		textFieldIdUsuarioAlquilar = new JTextField();
		textFieldIdUsuarioAlquilar.setColumns(10);
		textFieldIdUsuarioAlquilar.setBounds(239, 93, 237, 20);
		panelAlquilar.add(textFieldIdUsuarioAlquilar);
		
		JButton btnAlquilar = new JButton("Alquilar");
		btnAlquilar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id_bici_alquilar=textFieldIdBiciAlquilar.getText();
				String id_user_alquilar=textFieldIdUsuarioAlquilar.getText();
				
				 if (id_bici_alquilar.isEmpty() || id_user_alquilar.isEmpty()) {
			            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos", "Error", JOptionPane.ERROR_MESSAGE);
			            
			        }

			try {
				
				Connection con=ConnectionSingleton.getConnection();
				
				if (id_bici_alquilar.equals("0")) {
                    JOptionPane.showMessageDialog(null, "La bicicleta con id 0 no puede ser alquilada", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
				
				
				//Comprobar que existe bici y user
				PreparedStatement exist_b_pstmt = con.prepareStatement("SELECT * FROM bicicleta WHERE id_bicicleta=?");
				exist_b_pstmt.setInt(1, Integer.parseInt(id_bici_alquilar));
				ResultSet rs_exist_b = exist_b_pstmt.executeQuery();

				PreparedStatement exist_u_pstmt = con.prepareStatement("SELECT * FROM usuario WHERE id_usuario=?");
				exist_u_pstmt.setInt(1, Integer.parseInt(id_user_alquilar));
				ResultSet rs_exist_u = exist_u_pstmt.executeQuery();

				if (!rs_exist_b.next()) {//no existe bici
				    JOptionPane.showMessageDialog(null, "El ID de la bici ingresado no existe", "Error", JOptionPane.ERROR_MESSAGE);
				}  else if(!rs_exist_u.next()) {//no existe user
				    	 JOptionPane.showMessageDialog(null, "El ID del usuario ingresado no existe", "Error", JOptionPane.ERROR_MESSAGE);
				
				} else {//si existen los 2
					
					//Comprobar usuario id_bici =0
					PreparedStatement sel2_pstmt = con.prepareStatement("SELECT * FROM usuario WHERE id_usuario = ? AND id_bicicleta = 0");
					sel2_pstmt.setInt(1, Integer.parseInt(id_user_alquilar));
					ResultSet rs2_sel = sel2_pstmt.executeQuery();
					
					
					if(!rs2_sel.next()) {
						JOptionPane.showMessageDialog(null, "Este usuario ya tiene una bicicleta alquilada", "Error", JOptionPane.ERROR_MESSAGE);

					}else {
						 //Compruebo que la bici este libre
						
						PreparedStatement sel_pstmt = con.prepareStatement("SELECT disponibilidad FROM bicicleta WHERE id_bicicleta=? AND disponibilidad = 'true'");
						sel_pstmt.setInt(1, Integer.parseInt(id_bici_alquilar));
						ResultSet rs_sel = sel_pstmt.executeQuery();
						
						
						if (!rs_sel.next()) {// no dispo
							JOptionPane.showMessageDialog(null, "La bicicleta no está disponible para alquilar", "Error", JOptionPane.ERROR_MESSAGE);

						} else {//dispo, poner a false ahora
						   
							 PreparedStatement upd_pstmt = con.prepareStatement("UPDATE bicicleta SET disponibilidad = 'false' WHERE id_bicicleta = ?");
						        upd_pstmt.setInt(1, Integer.parseInt(id_bici_alquilar));
						        upd_pstmt.executeUpdate();
						        
						        

						        PreparedStatement upd2_pstmt = con.prepareStatement("UPDATE usuario SET id_bicicleta = ? WHERE id_usuario = ?");
						        upd2_pstmt.setInt(1, Integer.parseInt(id_bici_alquilar));
						        upd2_pstmt.setInt(2, Integer.parseInt(id_user_alquilar));
						        
						        upd2_pstmt.executeUpdate();
						        
						        btnMostrarBicis.doClick();
						        btnMostrarUsuario.doClick();							
						}
					
						rs_sel.close();
						sel_pstmt.close();
					}	
					}
					}
				
				con.close();
				
			}catch(SQLException e3) {
				JOptionPane.showMessageDialog(null, e3.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        } 
			
			}
		});
		btnAlquilar.setBounds(193, 246, 89, 25);
		panelAlquilar.add(btnAlquilar);
		
		JPanel panelDevolver = new JPanel();
		panelTotal.add(panelDevolver, "name_1028689232643000");
		panelDevolver.setLayout(null);
		
		textFieldIDBiciDevolver = new JTextField();
		textFieldIDBiciDevolver.setBounds(284, 65, 221, 26);
		panelDevolver.add(textFieldIDBiciDevolver);
		textFieldIDBiciDevolver.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Selecciona el ID de la bici a devolver:");
		lblNewLabel_1.setBounds(22, 70, 250, 16);
		panelDevolver.add(lblNewLabel_1);
		
		JButton btnDevolver = new JButton("Devolver");
		btnDevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String idBici = textFieldIDBiciDevolver.getText();
		        // Buscar la bicicleta en la base de datos
		        try {
		            Connection con = ConnectionSingleton.getConnection();
		            Statement stmt = con.createStatement();
		            ResultSet rs = stmt.executeQuery("SELECT * FROM bicicleta WHERE id=" + idBici);
		            if (rs.next()) {
		                // Si la bicicleta está alquilada, marcarla como disponible
		                if (rs.getBoolean("alquilada")) {
		                    PreparedStatement updateStmt = con.prepareStatement("UPDATE bicicletas SET disponibilidad=true, disponibilidad=false WHERE id=?");
		                    updateStmt.setString(1, idBici);
		                    updateStmt.executeUpdate();
		                    JOptionPane.showMessageDialog(null, "Bicicleta devuelta con éxito.");
		                } else {
		                    JOptionPane.showMessageDialog(null, "La bicicleta no está alquilada.");
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "No se encontró la bicicleta con ID " + idBici + ".");
		            }
		            rs.close();
					con.close();
					stmt.close();
		        } catch (SQLException ex) {
		           // JOptionPane.showMessageDialog(null, "Error al buscar la bicicleta en la base de datos: " + ex.getMessage());
		        }
		        
		    	
				/*
				 * No se podrá devolver una bici que no esté alquilada
				 */
				// Buscar la bicicleta en la base de datos
				try {
					// String idBici = textFieldIDBici.getText();
				    Connection con = ConnectionSingleton.getConnection();
				    Statement stmt = con.createStatement();
				    ResultSet rs = stmt.executeQuery("SELECT * FROM bicicleta WHERE id=" + idBici);
				    if (rs.next()) {
				        // Si la bicicleta está alquilada, marcarla como disponible
				        if (rs.getBoolean("alquilada")) {
				            PreparedStatement updateStmt = con.prepareStatement("UPDATE bicicleta SET disponibilidad=false WHERE id=?");
				            updateStmt.setString(1, idBici);
				            updateStmt.executeUpdate();
				            JOptionPane.showMessageDialog(null, "Bicicleta devuelta con éxito.");
				        } else {
				            JOptionPane.showMessageDialog(null, "La bicicleta no está alquilada.");
				        }
				    } else {
				        JOptionPane.showMessageDialog(null, "No se encontró la bicicleta con ID " + idBici + ".");
				    }
				    rs.close();
				    con.close();
				    stmt.close();
				} catch (SQLException ex) {
				  //  JOptionPane.showMessageDialog(null, "Error al buscar la bicicleta en la base de datos: " + ex.getMessage());
				}

				
			}
		});
		btnDevolver.setBounds(388, 202, 117, 29);
		panelDevolver.add(btnDevolver);
		
		JPanel panelAddBici = new JPanel();
		panelTotal.add(panelAddBici, "name_1028714407154100");
		panelAddBici.setLayout(null);
		

		
		JLabel lblIDBici = new JLabel("ID para  la bicicleta:");
		lblIDBici.setBounds(52, 76, 184, 16);
		panelAddBici.add(lblIDBici);
		
		textFieldIDBici = new JTextField();
		textFieldIDBici.setBounds(213, 71, 235, 26);
		panelAddBici.add(textFieldIDBici);
		textFieldIDBici.setColumns(10);
		
		JButton btnNuevaBici = new JButton("Añadir");
		btnNuevaBici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		    	String idBici = textFieldIDBici.getText();

		        try {
		            Connection con = ConnectionSingleton.getConnection();
		            PreparedStatement stmt = con.prepareStatement("INSERT INTO bicicleta (id_bicicleta, disponibilidad) VALUES (?, ?)");
		            stmt.setString(1, idBici);
		            stmt.setBoolean(2, false); // Especifica que la bicicleta no está alquilada al crearla
		            stmt.executeUpdate();
		            JOptionPane.showMessageDialog(frmAusiasBici, "Bicicleta agregada correctamente.");

		        } catch (SQLException ex) {
		            ex.printStackTrace();
		         //   JOptionPane.showMessageDialog(frmAusiasBici, "Error al agregar bicicleta: " + ex.getMessage());
		        }
				
				
			}
		});
		btnNuevaBici.setBounds(331, 205, 117, 29);
		panelAddBici.add(btnNuevaBici);
		
	
		JLabel lblOpcion = new JLabel("Opción a realizar:");
		lblOpcion.setBounds(60, 139, 187, 23);
		frmAusiasBici.getContentPane().add(lblOpcion);
		
		
		JButton btnRellenarUsuario = new JButton("Añadir usuario");
		btnRellenarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAddUser.setVisible(true);
				panelAddBici.setVisible(false);
				panelAlquilar.setVisible(false);
				
			}
		});
		btnRellenarUsuario.setBounds(83, 200, 181, 25);
		frmAusiasBici.getContentPane().add(btnRellenarUsuario);
		
		
		/*
		 * Añadir una bici y verificar que su estado de alquiler sea disponible.
		 */
		JButton btnRellenarBici = new JButton("Añadir bicicleta");
		btnRellenarBici.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
		        panelAddUser.setVisible(false);
		        panelAlquilar.setVisible(false);
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
			}
		});
		btnAlquilarBicicleta.setBounds(83, 266, 181, 25);
		frmAusiasBici.getContentPane().add(btnAlquilarBicicleta);
		
		JLabel lblNewLabel = new JLabel("");
	
	
		
		//Esto da error
		//lblNewLabel.setIcon(new ImageIcon(Ausiasbic.class.getResource("/img/bici150.png")));
		//Creo que se soluciona así:
		/*
		 * URL url = getClass().getResource("/ruta/a/la/imagen.png");
		 * if (url != null) {
		 * ImageIcon imageIcon = new ImageIcon(url)
		 * } else {
		 *  System.out.println("No se pudo cargar la imagen.");
		 *  }
		 */
		lblNewLabel.setBounds(50, 53, 136, 74);
		frmAusiasBici.getContentPane().add(lblNewLabel);
		
	
		
		


	}
}
