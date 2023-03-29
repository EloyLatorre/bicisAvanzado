package ausiasbici;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Ausiasbic {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ausiasbic window = new Ausiasbic();
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
	public Ausiasbic() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPrueba = new JLabel("Prueba");
		lblPrueba.setBounds(463, 187, 70, 15);
		frame.getContentPane().add(lblPrueba);
	}
}
