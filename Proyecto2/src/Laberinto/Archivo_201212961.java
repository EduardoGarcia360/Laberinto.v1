package Laberinto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Archivo_201212961 {
	
	FileReader fr=null;
	BufferedReader br = null;
	InputStream archivo, archivo_tema;
	String body, pagina, NombreUsuario = Tema_201212961.txtNombreusuario.getText();
	String head = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
	         +"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
	         +"<head>\n"
	         +"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
	         +"<title>Resultados</title>\n"
	         +"</head>";
	
	public String AbrirCSV(String NOMBRE, int LINEAS){
		archivo=getClass().getResourceAsStream(NOMBRE+".csv");
		BufferedReader buf = new BufferedReader(new InputStreamReader(archivo));
		String LeerLinea = "";
		
		String DATO="", DATOv2="";
		
		try{
			while((LeerLinea = buf.readLine()) != null){
				String [] DATOARCHIVO = LeerLinea.split(",");//METE EN EL ARREGLO LOS DATOS DE LA LINEA
					for(int i=0; i<LINEAS; i++){
						DATO += DATOARCHIVO[i]+","; //CONCATENA LOS DATOS
					}
			}
			buf.close();
			DATOv2 = DATO.substring(0, DATO.length()-1);//QUITA LA COMA QUE ESTA DE ULTIMO
		}catch(Exception e){}
		
	    return DATOv2;//RETORNA LA CADENA
	}
	
	public String AbrirTEMA(String Nombre_Tema){
		archivo_tema = getClass().getResourceAsStream("temas.csv");
		BufferedReader BR = new BufferedReader(new InputStreamReader(archivo_tema));
		String LeerLinea="", retorno="";
		try{
			while((LeerLinea = BR.readLine()) != null){
				String[] Datos = LeerLinea.split(",");
				if(Datos[0].equals(Nombre_Tema)){
					retorno = LeerLinea;
				}
			}
			BR.close();
		}catch(Exception e){}
		return retorno;
		
	}
	
	public void CrearARCHIVO(String TiempoJugado){
		
		String nombreARCHIVO = NombreUsuario + TiempoJugado + ".html";
		File a;
		FileWriter escritor;
		
		body="<body background=\"/FondoHTML.jpg\">"
			+"<center>"
			+"<h1>Resultados Obtenidos en la Partida</h1>"
			+"<h1>de Laberintos!</h1>"
			+"<hr />"
			+"<h3>Nombre del Jugador: </h3>"
			+"<h4>Tiempo: </h4>"
			+"<h4>Laberinto: </h4>"
			+"<h4>Movimientos</h4>"
			+"<h4>Arriba: </h4>"
			+"<h4>Abajo: </h4>"
			+"<h4>Izquierda: </h4>"
			+"<h4>Derecha: </h4>"
			+"<h4>Pasos: </h4>"
			+"</center>"
			+"</body>"
			+"</html>";
		
		pagina = head + body;
		
		try{
			a = new File(nombreARCHIVO);
			escritor = new FileWriter(a);
			BufferedWriter bw = new BufferedWriter(escritor);
			PrintWriter salida = new PrintWriter(bw);
			
			salida.write(pagina);//CREA EL ARCHIVO, CON EL CONTENIDO DE LA PAGINA
			JOptionPane.showMessageDialog(null, "archivo creado");
			salida.close();
			bw.close();
			
		}catch(Exception e){}
		
		File archivo = new File("C:\\Eduardo\\Progra\\EclipseProjects\\Proyecto2\\" + nombreARCHIVO);
		Desktop des = Desktop.getDesktop();//usa el programa por defecto para abrir el archivo. en este caso el navegador.
		try{
			if(archivo.exists()){
				JOptionPane.showMessageDialog(null, "abriendo archivo, posteriormente el juego se cerrara!");
				des.open(archivo);
			}
		}catch(Exception e){}
		//System.exit(1);
	}

}
