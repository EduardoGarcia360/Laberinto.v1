package Laberinto;

import javax.swing.ImageIcon;

public class Visual_201212961 {
	
	ImageIcon cofre;
	String fondo, DatosDeArchivo = "";
	Archivo_201212961 ar = new Archivo_201212961();
	
	String TemaSeleccionado(){
		if(Tema_201212961.S_flash){
			DatosDeArchivo = ar.AbrirTEMA("Tema_Flash");
		}else if(Tema_201212961.S_pacman){
			DatosDeArchivo = ar.AbrirTEMA("Tema_Pacman");
		}else if(Tema_201212961.S_sonic){
			DatosDeArchivo = ar.AbrirTEMA("Tema_Sonic");
		}else if(Tema_201212961.S_zelda){
			DatosDeArchivo = ar.AbrirTEMA("Tema_Zelda");
		}
		return DatosDeArchivo;
	}
	
	ImageIcon Cofre(){
		if(Tema_201212961.S_flash || Tema_201212961.S_pacman || Tema_201212961.S_sonic){
			cofre = new ImageIcon(getClass().getResource("/Iconos/cofre.png"));
		}else if(Tema_201212961.S_zelda){
			cofre = new ImageIcon(getClass().getResource("/Iconos/cofre_zelda.png"));
		}
		return cofre;
	}
	
	String Fondo(){
		if(Tema_201212961.S_flash){
			fondo = "/Imagenes/fondo_flash.jpg";
		}else if(Tema_201212961.S_pacman){
			fondo = "/Imagenes/fondo_pacman.jpg";
		}else if(Tema_201212961.S_sonic){
			fondo = "/Imagenes/fondo_sonic.jpg";
		}else if(Tema_201212961.S_zelda){
			fondo = "/Imagenes/fondo_zelda.jpg";
		}
		return fondo;
	}

}
