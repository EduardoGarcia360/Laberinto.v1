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

public class Archivo_201212961 {
	
	FileReader fr=null;
	BufferedReader br = null;
	InputStream archivo, archivo_tema;
	String head, nombredelarchivo;
	
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
	
	public void CrearARCHIVO(){
		File a;
		FileWriter escritor;
		
		
		head = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
		         +"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
		         +"<head>\n"
		         +"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
		         +"<title>Resultados</title>\n"
		         +"</head>";
	}

}
