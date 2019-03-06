import java.awt.*;
import javax.swing.*;

class VentanaPrincipal extends JFrame {
	
	public VentanaPrincipal(){
		getContentPane().setLayout(new FlowLayout());
		setSize(450, 500);
		setTitle("Concurencia");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setVisible(true);
		
		
	}
}

public class Prueba {
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VentanaPrincipal();	
			}
		});

	}

}
