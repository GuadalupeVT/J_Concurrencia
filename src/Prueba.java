import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class HiloImpresion implements Runnable{
	VentanaPrincipal ventana=new VentanaPrincipal();
	@Override
	public void run() {
		for (int i = 0; i < ventana.resultados.length; i++) {
			if(ventana.resultados[i].equals("Si")) {
				ventana.txtAreaSi.append("Si\n");
			}else {
				ventana.txtAreaNo.append("No\n");
			}
		}
		
	}
	
}

class VentanaPrincipal extends JFrame{
	String [] resultados=generarResultados();
	JTextArea txtAreaSi,txtAreaNo;
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
		txtAreaSi.setLineWrap(true);
		txtAreaSi.setWrapStyleWord(true);
		 JScrollPane scroll1=new JScrollPane(txtAreaSi);
		scroll1.setBounds(20, 50, 200, 200);
		add(scroll1);
		txtAreaNo=new JTextArea();
		txtAreaNo.setLineWrap(true);
		txtAreaNo.setWrapStyleWord(true);
		JScrollPane scroll2=new JScrollPane(txtAreaNo);
		scroll2.setBounds(250, 50, 200, 200);
		add(scroll2);
	}
	
	public String [] generarResultados() {
		String [] resultados=new String[100];
		for (int i = 0; i < resultados.length; i++) {
			if(Math.random()<0.5) {
				resultados[i]="Si";
			}else {
				resultados[i]="No";
			}
		}
		return resultados;
	}

	
}

public class Prueba {
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Thread hiloImpresion=new Thread(new HiloImpresion());
				hiloImpresion.start();

			}
		});

	}

}
