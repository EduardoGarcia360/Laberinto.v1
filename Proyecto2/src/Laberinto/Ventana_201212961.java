package Laberinto;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.JTextArea;

public class Ventana_201212961 extends JFrame implements KeyListener {
	
	//LABELES
	JLabel laberinto[][] = new JLabel[26][26];
	JLabel comparar[][] = new JLabel[26][26];
	JLabel pasos, derecha, izquierda, arriba, abajo, cronom, lblIcocrono, lblSuerte, lblPuntos;
	//
	Archivo_201212961 ar = new Archivo_201212961();
	String DatosDeArchivo = "";
	Cola_201212961 cola = new Cola_201212961();
	Visual_201212961 vi = new Visual_201212961();
	String DatosDeTema = vi.TemaSeleccionado();
	public static String tiempo="";
	Seguir s;
	Hilo Cronometro;
	Random aleatorio = new Random();
	Boolean enPausa=false;
	//PERSONAJES
	ImageIcon Protagonista, Rival, Cofre, Pared, Fin;
	//OTROS ICONOS
	ImageIcon arriba2 = new ImageIcon(getClass().getResource("/Imagenes/arriba.png"));
	ImageIcon abajo2 = new ImageIcon(getClass().getResource("/Imagenes/abajo.png"));
	ImageIcon derecha2 = new ImageIcon(getClass().getResource("/Imagenes/derecha.png"));
	ImageIcon izquierda2 = new ImageIcon(getClass().getResource("/Imagenes/izquierda.png"));
	
	ImageIcon arribaS = new ImageIcon(getClass().getResource("/Imagenes/arriba_sele.png"));
	ImageIcon abajoS = new ImageIcon(getClass().getResource("/Imagenes/abajo_sele.png"));
	ImageIcon derechaS = new ImageIcon(getClass().getResource("/Imagenes/derecha_sele.png"));
	ImageIcon izquierdaS = new ImageIcon(getClass().getResource("/Imagenes/izquierda_sele.png"));
	
	ImageIcon crono = new ImageIcon(getClass().getResource("/Iconos/crono.png"));
	ImageIcon pasos2 = new ImageIcon(getClass().getResource("/Iconos/pasos.png"));
	ImageIcon inicio = new ImageIcon(getClass().getResource("/Iconos/inicio.png"));
	
	ImageIcon bo1 = new ImageIcon(getClass().getResource("/Suerte/bono1.png"));
	ImageIcon bo2 = new ImageIcon(getClass().getResource("/Suerte/bono2.png"));
	ImageIcon bo3 = new ImageIcon(getClass().getResource("/Suerte/bono3.png"));
	ImageIcon tra1 = new ImageIcon(getClass().getResource("/Suerte/trampa1.jpg"));
	ImageIcon tra2 = new ImageIcon(getClass().getResource("/Suerte/trampa2.jpg"));
	ImageIcon tra3 = new ImageIcon(getClass().getResource("/Suerte/trampa3.jpg"));
	//FONDO
	Image fondo =  new ImageIcon(this.getClass().getResource(vi.Fondo())).getImage();
	//
	int PosX, PosY, cArriba, cAbajo, cDerecha, cIzquierda, cPasos, minuto, segundo, centesima, PX, PY, pX, pY, cPuntos;
	//
	JButton btnIniciar, btnPausar;
	JTextArea txareaSuerte;
	AudioClip Bono = java.applet.Applet.newAudioClip(getClass().getResource("/Audios/bono.wav"));
	AudioClip Tram = java.applet.Applet.newAudioClip(getClass().getResource("/Audios/trampa.wav"));
	AudioClip Nada = java.applet.Applet.newAudioClip(getClass().getResource("/Audios/nada.wav"));
	AudioClip Gano = java.applet.Applet.newAudioClip(getClass().getResource("/Audios/gano.wav"));
	AudioClip Perdio = java.applet.Applet.newAudioClip(getClass().getResource("/Audios/perdio.wav"));
	String mandarTiempo="";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_201212961 frame = new Ventana_201212961();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
	
	public JPanel contentPane = new JPanel(){
		public void paintComponent(Graphics g){
			g.drawImage(fondo, 0, 0,getWidth(), getHeight(), null);
		}
	};
	private JLabel lblPasos;
	private JLabel lblArriba;
	private JLabel lblAbajo;
	private JLabel lblIzquierda;
	private JLabel lblDerecha;

	/**
	 * Create the frame.
	 */
	public Ventana_201212961() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 700);
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Cronometro = new Hilo();
		s = new Seguir();
		
		String [] DatosTema = DatosDeTema.split(",");
		Protagonista = new ImageIcon(getClass().getResource("/Iconos/" + DatosTema[1]));
		Pared = new ImageIcon(getClass().getResource("/Iconos/" + DatosTema[2]));
		Fin = new ImageIcon(getClass().getResource("/Iconos/" + DatosTema[3]));
		Rival = new ImageIcon(getClass().getResource("/Iconos/" + DatosTema[4]));
		Cofre = vi.Cofre();
		
		this.addKeyListener(this);
		this.setFocusable(true);
		JLabel lblMovidas = new JLabel("Pasos Dados:");
		lblMovidas.setBounds(862, 367, 100, 14);
		contentPane.add(lblMovidas);
		ElementosVisuales();
		MatrizDeLaberinto();
		Cronometro.start();
	}
	
	private class Hilo extends Thread{	
		public void run(){
			while(true){
				centesima++;
				if(centesima == 100){
					centesima=0; segundo++;
				}
				if(segundo == 60){
					segundo=0; minuto++;
				}
				tiempo = (minuto<=9?"0":"") + minuto + ":" + (segundo<=9?"0":"") + segundo + ":" + (centesima<=9?"0":"") + centesima;
				cronom.setText(tiempo);
				try {
					Thread.sleep(10); //10 -> CENTESIMA, 1000 -> SEGUNDO
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}//FIN WHILE
		}
	}
	
	private class Seguir extends Thread{
		public void run(){
			while(true){
				
				if(cola.empty()){
					Cronometro.stop();
					Perdio.play();
					JOptionPane.showMessageDialog(null, "El Villano te alcanzo!\nTiempo: " + tiempo + 
							"\nPasos Dados: " + cPasos+
							"\nArriba: "+ cArriba +
							"\nAbajo: "+ cAbajo +
							"\nDerecha: " + cDerecha +
							"\nIzquierda: " + cIzquierda);
					Perdio.stop();
					ar.CrearARCHIVO(tiempo);
					break;
				}else if(pX == PosX && pY == PosY){
					Cronometro.stop();
					s.stop();
					Perdio.play();
					JOptionPane.showMessageDialog(null, "El Villano te alcanzo!\nTiempo: " + tiempo + 
							"\nPasos Dados: " + cPasos+
							"\nArriba: "+ cArriba +
							"\nAbajo: "+ cAbajo +
							"\nDerecha: " + cDerecha +
							"\nIzquierda: " + cIzquierda);
					Perdio.stop();
					ar.CrearARCHIVO(tiempo);
					break;
				}else{
					laberinto[pX][pY].setIcon(null);
					String [] Guia = cola.sacar().split(",");
					PX = Integer.parseInt(Guia[0]);
					PY = Integer.parseInt(Guia[1]);
					laberinto[PX][PY].setIcon(Rival);
					pX=PX; pY=PY;
					if(Tema_201212961.S_facil){
						try {
							Thread.sleep(600); //10 -> CENTESIMA, 1000 -> SEGUNDO
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}else if(Tema_201212961.S_medio){
						try {
							Thread.sleep(400); //10 -> CENTESIMA, 1000 -> SEGUNDO
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}else if(Tema_201212961.S_dificil){
						try {
							Thread.sleep(200); //10 -> CENTESIMA, 1000 -> SEGUNDO
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				
			}
			
		}
	}
	
 	void ElementosVisuales(){
		pasos = new JLabel();
		pasos.setOpaque(false);
		pasos.setBounds(825, 392, 50, 50);
		pasos.setIcon(pasos2);
		pasos.setVisible(true);
		getContentPane().add(pasos);
		//FLECHAS
		arriba = new JLabel();
		arriba.setOpaque(false);
		arriba.setBounds(875, 503, 50, 50);
		arriba.setIcon(arriba2);
		arriba.setVisible(true);
		getContentPane().add(arriba);
		
		abajo = new JLabel();
		abajo.setOpaque(false);
		abajo.setBounds(875, 554, 50, 50);
		abajo.setIcon(abajo2);
		abajo.setVisible(true);
		getContentPane().add(abajo);
		
		derecha = new JLabel();
		derecha.setOpaque(false);
		derecha.setBounds(825, 554, 50, 50);
		derecha.setIcon(derecha2);
		derecha.setVisible(true);
		getContentPane().add(derecha);
		
		izquierda = new JLabel();
		izquierda.setOpaque(false);
		izquierda.setBounds(925, 554, 50, 50);
		izquierda.setIcon(izquierda2);
		izquierda.setVisible(true);
		getContentPane().add(izquierda);
		
		JLabel lblTiempoJugado = new JLabel("Tiempo Jugado");
		lblTiempoJugado.setBounds(862, 46, 100, 14);
		contentPane.add(lblTiempoJugado);
		
		cronom = new JLabel();
		cronom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		cronom.setBounds(862, 71, 65, 26);
		cronom.setVisible(true);
		getContentPane().add(cronom);
		
		lblPasos = new JLabel();
		lblPasos.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblPasos.setBounds(899, 406, 46, 23);
		contentPane.add(lblPasos);
		
		lblArriba = new JLabel();
		lblArriba.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblArriba.setBounds(879, 481, 46, 14);
		contentPane.add(lblArriba);
		
		lblAbajo = new JLabel();
		lblAbajo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblAbajo.setBounds(875, 637, 46, 14);
		contentPane.add(lblAbajo);
		
		lblIzquierda = new JLabel();
		lblIzquierda.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblIzquierda.setBounds(829, 615, 46, 14);
		contentPane.add(lblIzquierda);
		
		lblDerecha = new JLabel();
		lblDerecha.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblDerecha.setBounds(925, 615, 46, 14);
		contentPane.add(lblDerecha);
		
		lblIcocrono = new JLabel();
		lblIcocrono.setOpaque(false);
		lblIcocrono.setBorder(null);
		lblIcocrono.setIcon(crono);
		lblIcocrono.setBounds(808, 56, 44, 50);
		contentPane.add(lblIcocrono);
		
		btnIniciar = new JButton("Continuar");
		btnIniciar.setEnabled(false);
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cronometro.resume();
				s.resume();
				enPausa=false;
				btnPausar.setEnabled(true);
				btnIniciar.setEnabled(false);
				setFocusable(true);
			}
		});
		btnIniciar.setBounds(786, 127, 89, 23);
		contentPane.add(btnIniciar);
		
		btnPausar = new JButton("Pausa");
		btnPausar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.suspend();
				Cronometro.suspend();
				enPausa=true;
				btnPausar.setEnabled(false);
				btnIniciar.setEnabled(true);
			}
		});
		btnPausar.setBounds(886, 127, 89, 23);
		contentPane.add(btnPausar);
		
		lblSuerte = new JLabel();
		lblSuerte.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblSuerte.setBounds(829, 161, 116, 102);
		contentPane.add(lblSuerte);
		
		txareaSuerte = new JTextArea();
		txareaSuerte.setOpaque(false);
		txareaSuerte.setBounds(825, 274, 120, 34);
		contentPane.add(txareaSuerte);
		
		lblPuntos = new JLabel();
		lblPuntos.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblPuntos.setBounds(829, 319, 96, 26);
		contentPane.add(lblPuntos);
	}
	
	void MatrizDeLaberinto(){
		if(Tema_201212961.S_lab1){
			DatosDeArchivo = ar.AbrirCSV("laberinto1", 25);
		}else if(Tema_201212961.S_lab2){
			DatosDeArchivo = ar.AbrirCSV("laberinto2", 25);
		}else if(Tema_201212961.S_lab3){
			DatosDeArchivo = ar.AbrirCSV("laberinto3", 25);
		}else if(Tema_201212961.S_lab4){
			DatosDeArchivo = ar.AbrirCSV("laberinto4", 25);
		}
		
		String DATOS [] = DatosDeArchivo.split(",");
		int PosArreglo = 0;
		for(int y=1; y<26; y++){
			for(int x=1; x<26; x++){
				laberinto[x][y] = new JLabel();
				laberinto[x][y].setOpaque(false);
				laberinto[x][y].setBounds(x*30, y*25, 30, 25);
				laberinto[x][y].setBorder(null);
				
				comparar[x][y] = new JLabel();
				comparar[x][y].setText(DATOS[PosArreglo]);
					if( DATOS[PosArreglo].equals("pared") ){
						laberinto[x][y].setIcon(Pared);
					}
					if( DATOS[PosArreglo].equals("inici") ){
						laberinto[x][y].setIcon(inicio);
						PosX=pX = x; PosY=pY = y;
					}
					if( DATOS[PosArreglo].equals("final") ){
						laberinto[x][y].setIcon(Fin);
					}
					if( DATOS[PosArreglo].equals("COFRE") ){
						laberinto[x][y].setIcon(Cofre);
					}
					
				laberinto[x][y].setVisible(true);
				getContentPane().add(laberinto[x][y]);
				PosArreglo++;
			}
		}
	}//FIN MATRIZ
	
	void Cofres(){
		String [] Suerte = {"trampa1", "bono1", "trampa2", "bono2", "trampa3", "bono3"};
		int TAM = Suerte.length;
		int Posicion = aleatorio.nextInt(TAM-1);
		String Resultado = Suerte[Posicion];
		
		if(Resultado.equals("trampa1")){
			lblSuerte.setIcon(tra1);
			txareaSuerte.setText("SkullKid te ha\nrobado 20 monedas!");
				if(cPuntos==0){
					lblPuntos.setText("..Nada que robar.");
					Nada.play();
				}else{
					cPuntos = cPuntos - 20;
					lblPuntos.setText("Pts.: "+ cPuntos);
					Tram.play();
				}
		}else if(Resultado.equals("bono1")){
			lblSuerte.setIcon(bo1);
			txareaSuerte.setText("+20 monedas!");
			cPuntos = cPuntos + 20;
			lblPuntos.setText("Pts.: "+ cPuntos);
			Bono.play();
		}else if(Resultado.equals("trampa2")){
			lblSuerte.setIcon(tra2);
			txareaSuerte.setText("¡Bomba!\n15 monedas han quedado tiradas!");
				if(cPuntos==0){
					lblPuntos.setText("..Nada ha pasado");
					Nada.play();
				}else{
					cPuntos = cPuntos - 15;
					lblPuntos.setText("Pts.: "+ cPuntos);
					Tram.play();
				}
		}else if(Resultado.equals("bono2")){
			lblSuerte.setIcon(bo2);
			txareaSuerte.setText("+30 monedas!");
			cPuntos = cPuntos + 30;
			lblPuntos.setText("Pts.: "+ cPuntos);
			Bono.play();
		}else if(Resultado.equals("trampa3")){
			lblSuerte.setIcon(tra3);
			txareaSuerte.setText("Pierdes 30 monedas!");
				if(cPuntos==0){
					lblPuntos.setText("..Nada que perder");
					Nada.play();
				}else{
					cPuntos = cPuntos - 30;
					lblPuntos.setText("Pts.: "+ cPuntos);
					Tram.play();
				}
		}else if(Resultado.equals("bono3")){
			lblSuerte.setIcon(bo3);
			txareaSuerte.setText("+40 monedas!");
			cPuntos = cPuntos + 40;
			lblPuntos.setText("Pts.: "+ cPuntos);
			Bono.play();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
			if(e.getKeyCode() == KeyEvent.VK_UP){
				if(!comparar[PosX][PosY-1].getText().equals("pared")){
					cola.add(PosX, PosY);
					arriba.setIcon(arribaS);
					laberinto[PosX][PosY].setIcon(null);
					PosY = PosY - 1;
					laberinto[PosX][PosY].setIcon(Protagonista);
					cArriba++;
					cPasos++;
					lblArriba.setText(""+cArriba);
					lblPasos.setText(""+cPasos);
					if(cPasos == 1){
						s.start();
					}
					if(comparar[PosX][PosY].getText().equals("COFRE")){
						Cofres();
						comparar[PosX][PosY].setText("camin");
					}
					if(comparar[PosX][PosY].getText().equals("final")){
						s.stop();
						Cronometro.stop();
						Gano.play();
						JOptionPane.showMessageDialog(null, "Laberinto completado!\nTiempo Jugado: " + tiempo + 
								"\nPasos Dados: " + cPasos+
								"\nArriba: "+ cArriba +
								"\nAbajo: "+ cAbajo +
								"\nDerecha: " + cDerecha +
								"\nIzquierda: " + cIzquierda);
						Gano.stop();
						ar.CrearARCHIVO(tiempo);
					}
				}
				
			}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){//IZQUIERDA
				if(!comparar[PosX+1][PosY].getText().equals("pared")){
					cola.add(PosX, PosY);
					izquierda.setIcon(izquierdaS);
					laberinto[PosX][PosY].setIcon(null);
					PosX = PosX + 1;
					laberinto[PosX][PosY].setIcon(Protagonista);
					cDerecha++;
					cPasos++;
					lblDerecha.setText(""+cDerecha);
					lblPasos.setText(""+cPasos);
					if(cPasos == 1){
						s.start();
					}
					if(comparar[PosX][PosY].getText().equals("COFRE")){
						Cofres();
						comparar[PosX][PosY].setText("camin");
					}
					if(comparar[PosX][PosY].getText().equals("final")){
						s.stop();
						Cronometro.stop();
						Gano.play();
						JOptionPane.showMessageDialog(null, "Laberinto completado!\nTiempo: " + tiempo + 
								"\nPasos Dados: " + cPasos +
								"\nArriba: "+ cArriba +
								"\nAbajo: "+ cAbajo +
								"\nDerecha: " + cDerecha +
								"\nIzquierda: " + cIzquierda);
						Gano.stop();
						ar.CrearARCHIVO(tiempo);
					}
				}
				
			}else if(e.getKeyCode() == KeyEvent.VK_LEFT){//DERECHA
				if(!comparar[PosX-1][PosY].getText().equals("pared")){
					cola.add(PosX, PosY);
					derecha.setIcon(derechaS);
					laberinto[PosX][PosY].setIcon(null);
					PosX = PosX - 1;
					laberinto[PosX][PosY].setIcon(Protagonista);
					cIzquierda++;
					cPasos++;
					lblIzquierda.setText(""+cIzquierda);
					lblPasos.setText(""+cPasos);
					if(cPasos == 1){
						s.start();
					}
					if(comparar[PosX][PosY].getText().equals("COFRE")){
						Cofres();
						comparar[PosX][PosY].setText("camin");
					}
					if(comparar[PosX][PosY].getText().equals("final")){
						s.stop();
						Cronometro.stop();
						Gano.play();
						JOptionPane.showMessageDialog(null, "Laberinto completado!\nTiempo: " + tiempo + 
								"\nPasos Dados: " + cPasos+
								"\nArriba: "+ cArriba +
								"\nAbajo: "+ cAbajo +
								"\nDerecha: " + cDerecha +
								"\nIzquierda: " + cIzquierda);
						Gano.stop();
						ar.CrearARCHIVO(tiempo);
					}
				}

				
			}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
				if(!comparar[PosX][PosY+1].getText().equals("pared")){
					cola.add(PosX, PosY);
					abajo.setIcon(abajoS);
					laberinto[PosX][PosY].setIcon(null);
					PosY = PosY + 1;
					laberinto[PosX][PosY].setIcon(Protagonista);
					cAbajo++;
					lblAbajo.setText(""+cAbajo);
					cPasos++;
					lblPasos.setText(""+cPasos);
					if(cPasos == 1){
						s.start();
					}
					if(comparar[PosX][PosY].getText().equals("COFRE")){
						Cofres();
						comparar[PosX][PosY].setText("camin");
					}
					if(comparar[PosX][PosY].getText().equals("final")){
						s.stop();
						Cronometro.stop();
						Gano.play();
						JOptionPane.showMessageDialog(null, "Laberinto completado!\nTiempo: " + tiempo + 
								"\nPasos Dados: " + cPasos+
								"\nArriba: "+ cArriba +
								"\nAbajo: "+ cAbajo +
								"\nDerecha: " + cDerecha +
								"\nIzquierda: " + cIzquierda);
						Gano.stop();
						ar.CrearARCHIVO(tiempo);
					}
				}
				
			}
			
			repaint();
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			arriba.setIcon(arriba2);
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			izquierda.setIcon(izquierda2);
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			derecha.setIcon(derecha2);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			abajo.setIcon(abajo2);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
