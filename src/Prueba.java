import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

class HiloImpresion implements Runnable{
	VentanaPrincipal ventana;
	int contadorSi=1, contadorNo=1;
	public HiloImpresion(VentanaPrincipal ventana1) {
		ventana=ventana1;
	}
	@Override
	public void run() {
		for (int i = 0; i < ventana.resultados.length; i++) {
			if(ventana.resultados[i].equals("Si")) {
				ventana.txtAreaSi.append(contadorSi+" - Si\n");
				contadorSi++;
			}else {
				ventana.txtAreaNo.append(contadorNo+" - No\n");
				contadorNo++;
			}
		}
	}	
}

class HiloGenerarHistograma implements Runnable{
	VentanaPrincipal ventana;
	int contadorSi=0, contadorNo=0;
	public HiloGenerarHistograma(VentanaPrincipal ventana1) {
		ventana=ventana1;
	}
	@Override
	public void run() {
		for (int i = 0; i < ventana.resultados.length; i++) {
			if(ventana.resultados[i].equals("Si")) {
				contadorSi++;
			}else {
				contadorNo++;
			}
		}
		ventana.progressBarSi.setString(null);
		ventana.progressBarSi.setStringPainted(true);
		ventana.progressBarSi.setValue(contadorSi);
		ventana.progressBarNo.setString(null);
		ventana.progressBarNo.setStringPainted(true);
		ventana.progressBarNo.setValue(contadorNo);
		
	}
	
}


class VentanaPrincipal extends JFrame{
	String [] resultados=generarResultados();
	JTextArea txtAreaSi,txtAreaNo;
	JProgressBar progressBarSi,progressBarNo;
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
		
		JLabel lblResultado=new JLabel("Resultados");
		lblResultado.setBounds(200, 260, 100, 30);
		add(lblResultado);
		
		JLabel lblRSi=new JLabel("Si");
		lblRSi.setBounds(20,290,20,30);
		add(lblRSi);
		progressBarSi = new JProgressBar(0, 100);
		progressBarSi.setBounds(50, 290, 400, 30);
		progressBarSi.setStringPainted(true);
		progressBarSi.setString("Cargando......");
		add(progressBarSi);
		
		JLabel lblRNo=new JLabel("No");
		lblRNo.setBounds(20,340,20,30);
		add(lblRNo);
		progressBarNo = new JProgressBar(0, 100);
		progressBarNo.setBounds(50, 340, 400, 30);
		progressBarNo.setStringPainted(true);
		progressBarNo.setString("Cargando......");
		add(progressBarNo);
	}
	
	public String [] generarResultados() {
		String [] resultados=new String[100];
		String respuestas[]= {"No","Si","No","Si","Si","No"};
		for (int i = 0; i < resultados.length; i++) {
		resultados[i] = respuestas[new Random().nextInt(respuestas.length)];
		}
		return resultados;
	}

	
}

public class Prueba {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaPrincipal ventana=new VentanaPrincipal();
				Thread hiloImpresion=new Thread(new HiloImpresion(ventana));
				Thread hiloGenerarHistograma=new Thread(new HiloGenerarHistograma(ventana));
				hiloImpresion.start();
				try {
					hiloImpresion.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hiloGenerarHistograma.start();
			}
		});

	}

}
