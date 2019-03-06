import java.awt.*;
import javax.swing.*;

class VentanaPrincipal extends JFrame {
	JTextArea txtAreaSi,txtAreaNo;
	JButton btnIniciar;
	public VentanaPrincipal(){
		getContentPane().setLayout(null);
		setSize(490, 450);
		setTitle("Concurencia");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setVisible(true);
		
		JLabel lblSi=new JLabel("Respuestas en SI");
		lblSi.setBounds(70,10,100,30);
		add(lblSi);
		JLabel lblNo =new JLabel("Respuestas en No");
		lblNo.setBounds(290, 10, 110, 30);
		add(lblNo);
		txtAreaSi=new JTextArea();
		txtAreaSi.setBounds(20, 50, 200, 200);
		add(txtAreaSi);
		txtAreaNo=new JTextArea();
		txtAreaNo.setBounds(250, 50, 200, 200);
		add(txtAreaNo);
		btnIniciar=new JButton("Iniciar");
		btnIniciar.setBounds(160, 280, 130, 30);
		add(btnIniciar);
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
