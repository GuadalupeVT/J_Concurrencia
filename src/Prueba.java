import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

class HiloImpresion implements Runnable {
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
			Thread.yield();
		}
	}	
}

class HiloGenerarHistograma implements Runnable{
	VentanaPrincipal ventana;
	public HiloGenerarHistograma(VentanaPrincipal ventana1) {
		ventana=ventana1;
	}
	@Override
	public void run() {
		for (int i = 0; i < ventana.resultados.length; i++) {
			if(ventana.resultados[i].equals("Si")) {
				ventana.progressBarSi.setValue(ventana.progressBarSi.getValue()+1);
			}else {
				ventana.progressBarNo.setValue(ventana.progressBarNo.getValue()+1);
			}
		}
		
	}
	
}

class VentanaPrincipal extends JFrame implements ActionListener{
	String [] resultados=generarResultados();
	JTextArea txtAreaSi,txtAreaNo;
	JProgressBar progressBarSi,progressBarNo;
	JButton btnIniciar, btnLimpiar;
	public VentanaPrincipal(){
		getContentPane().setLayout(null);
		setSize(490, 500);
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
		progressBarSi = new JProgressBar(0,10000000);
		progressBarSi.setBounds(50, 290, 400, 30);
		progressBarSi.setStringPainted(true);
		
		add(progressBarSi);
		
		JLabel lblRNo=new JLabel("No");
		lblRNo.setBounds(20,340,20,30);
		add(lblRNo);
		progressBarNo = new JProgressBar(0,10000000);
		progressBarNo.setBounds(50, 340, 400, 30);
		progressBarNo.setStringPainted(true);
		add(progressBarNo);
		
		btnIniciar=new JButton("Iniciar");
		btnIniciar.setBounds(100,400,100,30);
		btnIniciar.addActionListener(this);
		add(btnIniciar);
		
		btnLimpiar=new JButton("Limpiar");
		btnLimpiar.setBounds(300,400,100,30);
		btnLimpiar.addActionListener(this);
		add(btnLimpiar);
	}
	
	public String [] generarResultados() {
		String [] resultados=new String[10000000];
		String respuestas[]= {"Si","Si","Si","No","No","No"};
		for (int i = 0; i < resultados.length; i++) {
		resultados[i] = respuestas[new Random().nextInt(respuestas.length)];
		}
		return resultados;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Thread hiloImpresion=new Thread(new HiloImpresion(this));
		Thread hiloGenerarHistograma=new Thread(new HiloGenerarHistograma(this));
		
		txtAreaSi.setText("");
		 txtAreaNo.setText("");
		 progressBarSi.setValue(0);
		 progressBarNo.setValue(0);
		 resultados=generarResultados();
		 
		if (e.getSource()==btnIniciar) {
			hiloImpresion.start();
			hiloGenerarHistograma.start();
			
			try {
				hiloGenerarHistograma.join();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	 if (e.getSource()==btnLimpiar) {
		 txtAreaSi.setText("");
		 txtAreaNo.setText("");
		 progressBarSi.setValue(0);
		 progressBarNo.setValue(0);
		 resultados=generarResultados();
	 }
		
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
