package Laberinto;

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

public class Ventana_201212961 extends JFrame implements KeyListener {
	
	//LABELES
	JLabel laberinto[][] = new JLabel[26][26];
	JLabel comparar[][] = new JLabel[26][26];
	JLabel pasos, derecha, izquierda, arriba, abajo, cronom, lblIcocrono;
	//
	Archivo_201212961 ar = new Archivo_201212961();
	String DatosDeArchivo = "";
	Cola_201212961 cola = new Cola_201212961();
	Visual_201212961 vi = new Visual_201212961();
	String DatosDeTema = vi.TemaSeleccionado();
	String tiempo="";
	Seguir s;
	Hilo Cronometro;
	Boolean hilo_seguir=false;
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
	
	//FONDO
	Image fondo =  new ImageIcon(this.getClass().getResource(vi.Fondo())).getImage();
	//
	int PosX, PosY, cArriba, cAbajo, cDerecha, cIzquierda, cPasos, minuto, segundo, centesima, PX, PY, pX, pY;

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
		lblMovidas.setBounds(862, 294, 100, 14);
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
					JOptionPane.showMessageDialog(null, "El Villano te alcanzo!\nTiempo: " + tiempo + 
							"\nPasos Dados: " + cPasos);
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
		pasos.setBounds(825, 322, 50, 50);
		pasos.setIcon(pasos2);
		pasos.setVisible(true);
		getContentPane().add(pasos);
		//FLECHAS
		arriba = new JLabel();
		arriba.setOpaque(false);
		arriba.setBounds(875, 441, 50, 50);
		arriba.setIcon(arriba2);
		arriba.setVisible(true);
		getContentPane().add(arriba);
		
		abajo = new JLabel();
		abajo.setOpaque(false);
		abajo.setBounds(875, 492, 50, 50);
		abajo.setIcon(abajo2);
		abajo.setVisible(true);
		getContentPane().add(abajo);
		
		derecha = new JLabel();
		derecha.setOpaque(false);
		derecha.setBounds(825, 492, 50, 50);
		derecha.setIcon(derecha2);
		derecha.setVisible(true);
		getContentPane().add(derecha);
		
		izquierda = new JLabel();
		izquierda.setOpaque(false);
		izquierda.setBounds(925, 492, 50, 50);
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
		lblPasos.setBounds(899, 336, 46, 23);
		contentPane.add(lblPasos);
		
		lblArriba = new JLabel();
		lblArriba.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblArriba.setBounds(879, 419, 46, 14);
		contentPane.add(lblArriba);
		
		lblAbajo = new JLabel();
		lblAbajo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblAbajo.setBounds(875, 575, 46, 14);
		contentPane.add(lblAbajo);
		
		lblIzquierda = new JLabel();
		lblIzquierda.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblIzquierda.setBounds(829, 553, 46, 14);
		contentPane.add(lblIzquierda);
		
		lblDerecha = new JLabel();
		lblDerecha.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblDerecha.setBounds(925, 553, 46, 14);
		contentPane.add(lblDerecha);
		
		lblIcocrono = new JLabel();
		lblIcocrono.setOpaque(false);
		lblIcocrono.setBorder(null);
		lblIcocrono.setIcon(crono);
		lblIcocrono.setBounds(808, 56, 44, 50);
		contentPane.add(lblIcocrono);
		
		JButton btnIniciar = new JButton("Continuar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				btnIniciar.setSelected(false);
			}
		});
		btnIniciar.setBounds(786, 127, 89, 23);
		contentPane.add(btnIniciar);
		
		JButton btnPausar = new JButton("Pausa");
		btnPausar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnPausar.setSelected(false);
			}
		});
		btnPausar.setBounds(886, 127, 89, 23);
		contentPane.add(btnPausar);
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
