package Laberinto;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Soluciones_201212961 extends JFrame {

	private JPanel contentPane;
	int numero=0;
	ImageIcon Solucion, icono;
	JLabel lblImagen, lblLaberinto;
	JButton btnSiguiente, btnAnterior, btnMenu;
	ImageIcon regresar = new ImageIcon(getClass().getResource("/Imagenes/regresar.png"));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Soluciones_201212961 frame = new Soluciones_201212961();
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
	public Soluciones_201212961() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		icono = new ImageIcon(getClass().getResource("/Soluciones/indiv_labe_0.jpg"));
		
		btnAnterior = new JButton("Anterior");
		btnAnterior.setEnabled(false);
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				numero--;
				if(numero == 0){
					btnAnterior.setEnabled(false);
					lblLaberinto.setVisible(false);
				}else{
					lblLaberinto.setText("Laberinto #"+numero);
				}
				
				lblImagen.setIcon(null);
				String ruta = "/Soluciones/indiv_labe_"+numero+".jpg";
				Solucion = new ImageIcon(getClass().getResource(ruta));
				lblImagen.setIcon(Solucion);
				btnSiguiente.setEnabled(true);
			}
		});
		btnAnterior.setBounds(69, 517, 89, 23);
		contentPane.add(btnAnterior);
		
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numero++;
				lblLaberinto.setText("Laberinto #"+numero);
				lblImagen.setIcon(null);
				String ruta = "/Soluciones/indiv_labe_"+numero+".jpg";
				Solucion = new ImageIcon(getClass().getResource(ruta));
				lblImagen.setIcon(Solucion);
				btnAnterior.setEnabled(true);
				if(numero == 4){
					btnSiguiente.setEnabled(false);
				}
			}
		});
		btnSiguiente.setBounds(515, 517, 89, 23);
		contentPane.add(btnSiguiente);
		
		lblLaberinto = new JLabel();
		lblLaberinto.setBounds(262, 11, 144, 24);
		contentPane.add(lblLaberinto);
		
		lblImagen = new JLabel();
		lblImagen.setIcon(icono);
		lblImagen.setBounds(69, 46, 535, 436);
		contentPane.add(lblImagen);
		
		btnMenu = new JButton("Menu");
		btnMenu.setBorder(null);
		btnMenu.setOpaque(false);
		btnMenu.setContentAreaFilled(false);
		btnMenu.setIcon(regresar);
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				main_201212961.m.setVisible(true);
			}
		});
		btnMenu.setBounds(302, 493, 89, 59);
		contentPane.add(btnMenu);
	}
}
