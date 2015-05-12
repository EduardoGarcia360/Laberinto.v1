package Laberinto;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import java.applet.AudioClip;

public class Tema_201212961 extends JFrame implements ActionListener {

	private JPanel contentPane;
	ButtonGroup temas, laberintos, nivel, modo;
	//IMAGENES
	ImageIcon zelda = new ImageIcon(getClass().getResource("/Imagenes/boton_zelda.jpg"));
	ImageIcon sonic = new ImageIcon(getClass().getResource("/Imagenes/boton_sonic.png"));
	ImageIcon flash = new ImageIcon(getClass().getResource("/Imagenes/boton_flash.jpg"));
	ImageIcon pacman = new ImageIcon(getClass().getResource("/Imagenes/boton_pacman.jpg"));
	ImageIcon regresar = new ImageIcon(getClass().getResource("/Imagenes/regresar.png"));
	Image icono = new ImageIcon(this.getClass().getResource("/Iconos/tema.png")).getImage();
	//RADIOBUTTON
	JRadioButton rdbtnNum, rdbtnNum_1, rdbtnNum_2, rdbtnNum_3, rdbtnLabe, rdbtnLabe_1, rdbtnLabe_2, rdbtnLabe_3, rdbtnFacil, rdbtnMedio;
	JRadioButton rdbtnDificil, rdbtnIndividual, rdbtnVsPc;
	//AUDIO
	AudioClip SonidoZ = java.applet.Applet.newAudioClip(getClass().getResource("/Audios/selec_zelda.wav"));
	AudioClip SonidoS = java.applet.Applet.newAudioClip(getClass().getResource("/Audios/selec_sonic.wav"));
	AudioClip SonidoF = java.applet.Applet.newAudioClip(getClass().getResource("/Audios/selec_flash.wav"));
	AudioClip SonidoP = java.applet.Applet.newAudioClip(getClass().getResource("/Audios/selec_pacman.wav"));
	//
	public static boolean S_flash=false, S_sonic=false, S_zelda=false, S_pacman=false;
	public static boolean S_lab1=false, S_lab2=false, S_lab3=false, S_lab4=false;
	//
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tema_201212961 frame = new Tema_201212961();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tema_201212961() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 438);
		this.setIconImage(icono);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		temas = new ButtonGroup();
		laberintos = new ButtonGroup();
		nivel = new ButtonGroup();
		modo = new ButtonGroup();
		
		JButton btnRegresarAlMenu = new JButton();
		btnRegresarAlMenu.setBorder(null);
		btnRegresarAlMenu.setOpaque(false);
		btnRegresarAlMenu.setContentAreaFilled(false);
		btnRegresarAlMenu.setIcon(regresar);
		btnRegresarAlMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				main_201212961.m.setVisible(true);
			}
		});
		btnRegresarAlMenu.setBounds(544, 254, 89, 59);
		contentPane.add(btnRegresarAlMenu);
		
		JLabel lblTema = new JLabel();
		lblTema.setIcon(zelda);
		lblTema.setBounds(92, 111, 119, 100);
		contentPane.add(lblTema);
		
		JLabel lblTema_1 = new JLabel();
		lblTema_1.setIcon(flash);
		lblTema_1.setBounds(92, 255, 119, 100);
		contentPane.add(lblTema_1);
		
		JLabel lblTema_2 = new JLabel();
		lblTema_2.setIcon(sonic);
		lblTema_2.setBounds(303, 111, 119, 100);
		contentPane.add(lblTema_2);
		
		JLabel lblTema_3 = new JLabel();
		lblTema_3.setIcon(pacman);
		lblTema_3.setBounds(303, 255, 119, 100);
		contentPane.add(lblTema_3);
		
		rdbtnNum = new JRadioButton("Zelda");
		rdbtnNum.addActionListener(this);
		temas.add(rdbtnNum);
		rdbtnNum.setBounds(6, 149, 64, 23);
		contentPane.add(rdbtnNum);
		
		rdbtnNum_1 = new JRadioButton("Flash");
		rdbtnNum_1.addActionListener(this);
		temas.add(rdbtnNum_1);
		rdbtnNum_1.setBounds(6, 290, 64, 23);
		contentPane.add(rdbtnNum_1);
		
		rdbtnNum_2 = new JRadioButton("Sonic");
		rdbtnNum_2.addActionListener(this);
		temas.add(rdbtnNum_2);
		rdbtnNum_2.setBounds(233, 149, 64, 23);
		contentPane.add(rdbtnNum_2);
		
		rdbtnNum_3 = new JRadioButton("Pac-Man");
		rdbtnNum_3.addActionListener(this);
		temas.add(rdbtnNum_3);
		rdbtnNum_3.setBounds(233, 290, 67, 23);
		contentPane.add(rdbtnNum_3);
		
		JLabel lblSeleccionaUnLaberinto = new JLabel("Selecciona un Laberinto:");
		lblSeleccionaUnLaberinto.setBounds(484, 66, 163, 23);
		contentPane.add(lblSeleccionaUnLaberinto);
		
		rdbtnLabe = new JRadioButton("Laberinto #1");
		rdbtnLabe.addActionListener(this);
		laberintos.add(rdbtnLabe);
		rdbtnLabe.setBounds(484, 111, 109, 23);
		contentPane.add(rdbtnLabe);
		
		rdbtnLabe_1 = new JRadioButton("Laberinto #2");
		rdbtnLabe_1.addActionListener(this);
		laberintos.add(rdbtnLabe_1);
		rdbtnLabe_1.setBounds(610, 111, 109, 23);
		contentPane.add(rdbtnLabe_1);
		
		rdbtnLabe_2 = new JRadioButton("Laberinto #3");
		rdbtnLabe_2.addActionListener(this);
		laberintos.add(rdbtnLabe_2);
		rdbtnLabe_2.setBounds(484, 170, 109, 23);
		contentPane.add(rdbtnLabe_2);
		
		rdbtnLabe_3 = new JRadioButton("Laberinto #4");
		rdbtnLabe_3.addActionListener(this);
		laberintos.add(rdbtnLabe_3);
		rdbtnLabe_3.setBounds(610, 170, 109, 23);
		contentPane.add(rdbtnLabe_3);
		
		JLabel lblRegresar = new JLabel("Regresar");
		lblRegresar.setBounds(554, 318, 64, 23);
		contentPane.add(lblRegresar);
		
		JLabel lblSeleccionaUnTema = new JLabel("Selecciona un Tema:");
		lblSeleccionaUnTema.setBounds(112, 70, 124, 14);
		contentPane.add(lblSeleccionaUnTema);
		
		JLabel lblNivelDeDificultad = new JLabel("Nivel de Dificultad");
		lblNivelDeDificultad.setBounds(760, 70, 100, 14);
		contentPane.add(lblNivelDeDificultad);
		
		rdbtnFacil = new JRadioButton("Facil");
		rdbtnFacil.addActionListener(this);
		nivel.add(rdbtnFacil);
		rdbtnFacil.setBounds(760, 111, 109, 23);
		contentPane.add(rdbtnFacil);
		
		rdbtnMedio = new JRadioButton("Medio");
		rdbtnMedio.addActionListener(this);
		nivel.add(rdbtnMedio);
		rdbtnMedio.setBounds(760, 137, 109, 23);
		contentPane.add(rdbtnMedio);
		
		rdbtnDificil = new JRadioButton("Dificil");
		rdbtnDificil.addActionListener(this);
		nivel.add(rdbtnDificil);
		rdbtnDificil.setBounds(760, 163, 109, 23);
		contentPane.add(rdbtnDificil);
		
		JLabel lblModoDeJuego = new JLabel("Modo de Juego");
		lblModoDeJuego.setBounds(760, 227, 100, 14);
		contentPane.add(lblModoDeJuego);
		
		rdbtnIndividual = new JRadioButton("Individual");
		rdbtnIndividual.addActionListener(this);
		modo.add(rdbtnIndividual);
		rdbtnIndividual.setBounds(708, 255, 109, 23);
		contentPane.add(rdbtnIndividual);
		
		rdbtnVsPc = new JRadioButton("vs PC");
		rdbtnVsPc.addActionListener(this);
		modo.add(rdbtnVsPc);
		rdbtnVsPc.setBounds(836, 255, 109, 23);
		contentPane.add(rdbtnVsPc);
	}
	
	public void actionPerformed(ActionEvent e){
		if(rdbtnNum.isSelected()){
			this.setTitle("Tema de Zelda elegido");
			//SonidoZ.play();
			SonidoF.stop(); SonidoS.stop(); SonidoP.stop();
			S_zelda=true;
			S_flash=false; S_sonic=false; S_pacman=false;
		}
		if(rdbtnNum_1.isSelected()){
			this.setTitle("Tema de Flash elegido");
			SonidoF.play();
			SonidoS.stop(); SonidoP.stop(); SonidoZ.stop();
			S_flash=true;
			S_zelda=false; S_sonic=false; S_pacman=false;
		}
		if(rdbtnNum_2.isSelected()){
			this.setTitle("Tema de Sonic elegido");
			SonidoS.play();
			SonidoP.stop(); SonidoZ.stop(); SonidoF.stop();
			S_sonic=true;
			S_zelda=false; S_flash=false; S_pacman=false;
		}
		if(rdbtnNum_3.isSelected()){
			this.setTitle("Tema de Pac-Man elegido");
			SonidoP.play();
			SonidoZ.stop(); SonidoF.stop(); SonidoS.stop();
			S_pacman=true;
			S_zelda=false; S_sonic=false; S_flash=false;
		}
		if(rdbtnLabe.isSelected()){
			S_lab1=true;
			S_lab2=false; S_lab3=false; S_lab4=false;
		}
		if(rdbtnLabe_1.isSelected()){
			S_lab2=true;
			S_lab1=false; S_lab3=false; S_lab4=false;
		}
		if(rdbtnLabe_2.isSelected()){
			S_lab3=true;
			S_lab1=false; S_lab2=false; S_lab4=false;
		}
		if(rdbtnLabe_3.isSelected()){
			S_lab4=true;
			S_lab1=false; S_lab2=false; S_lab3=false;
		}
	}
}
