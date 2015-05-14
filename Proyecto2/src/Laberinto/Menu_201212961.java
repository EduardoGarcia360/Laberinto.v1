package Laberinto;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu_201212961 extends JFrame {

	
	Image fondo = new ImageIcon(this.getClass().getResource("/Imagenes/fondo.jpg")).getImage();
	Image icono = new ImageIcon(this.getClass().getResource("/Iconos/laberinto.png")).getImage();
	ImageIcon individual = new ImageIcon(this.getClass().getResource("/Imagenes/individual.jpg"));
	ImageIcon vspc = new ImageIcon(this.getClass().getResource("/Imagenes/contrapc.jpg"));
	ImageIcon tema = new ImageIcon(this.getClass().getResource("/Imagenes/tema.jpg"));
	ImageIcon documentos = new ImageIcon(this.getClass().getResource("/Imagenes/documentos.jpg"));
	ImageIcon salir = new ImageIcon(this.getClass().getResource("/Imagenes/salir.jpg"));
	ImageIcon solucion = new ImageIcon(this.getClass().getResource("/Imagenes/soluciones.jpg"));
	//
	public static Tema_201212961 tem;
	public static Soluciones_201212961 sol;
	//
	JButton btnJuegoNuevo, btnVsPc, btnTema, btnDocumentacion, btnSalir, btnSolucion;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu_201212961 frame = new Menu_201212961();
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

	public Menu_201212961() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 455);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setIconImage(icono);
		this.setTitle("Bienvenido al Juego del Laberinto");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnJuegoNuevo = new JButton();
		btnJuegoNuevo.setBorder(null);
		btnJuegoNuevo.setOpaque(false);
		btnJuegoNuevo.setIcon(individual);
		btnJuegoNuevo.setEnabled(true);	
		btnJuegoNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!Tema_201212961.S_indi){
					JOptionPane.showMessageDialog(null, "Empieza por elegir el tema de juego\nhaz click en el boton Seleccionar Tema.");
				}else{
					setVisible(false);
					Ventana_201212961 v1 = new Ventana_201212961();
					v1.setVisible(true);
				}
			}
		});
		btnJuegoNuevo.setBounds(120, 124, 208, 70);
		contentPane.add(btnJuegoNuevo);
		
		btnVsPc = new JButton();
		btnVsPc.setBorder(null);
		btnVsPc.setOpaque(false);
		btnVsPc.setIcon(vspc);
		btnVsPc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!Tema_201212961.S_vspc){
					JOptionPane.showMessageDialog(null, "Empieza por elegir el tema de juego\nhaz click en el boton Seleccionar Tema.");
				}else{
					
				}
			}
		});
		btnVsPc.setBounds(344, 124, 208, 70);
		contentPane.add(btnVsPc);
		//
		btnTema = new JButton();
		btnTema.setBorder(null);
		btnTema.setOpaque(false);
		btnTema.setIcon(tema);
		btnTema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				tem = new Tema_201212961();
				tem.setVisible(true);
			}
		});
		btnTema.setBounds(34, 227, 208, 70);
		contentPane.add(btnTema);
		//
		btnDocumentacion = new JButton();
		btnDocumentacion.setBorder(null);
		btnDocumentacion.setOpaque(false);
		btnDocumentacion.setIcon(documentos);
		btnDocumentacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDocumentacion.setBounds(252, 227, 208, 70);
		contentPane.add(btnDocumentacion);
		//
		btnSalir = new JButton();
		btnSalir.setBorder(null);
		btnSalir.setOpaque(false);
		btnSalir.setIcon(salir);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		btnSalir.setBounds(246, 317, 208, 70);
		contentPane.add(btnSalir);
		//
		btnSolucion = new JButton();
		btnSolucion.setBorder(null);
		btnSolucion.setOpaque(false);
		btnSolucion.setIcon(solucion);
		btnSolucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				sol = new Soluciones_201212961();
				sol.setVisible(true);
			}
		});
		btnSolucion.setBounds(485, 227, 208, 70);
		contentPane.add(btnSolucion);
	}
}
