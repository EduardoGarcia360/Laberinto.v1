package Laberinto;

public class Nodo_201212961 {
	
	
	public int PoX, PoY;
	public Nodo_201212961 siguiente;
	
	//constructor para insertar al final
	public Nodo_201212961 (int PX, int PY){
		
		this.PoX = PX;
		this.PoY = PY;
		this.siguiente = null; //para que apunte el nodo creado a nulo
	}

}
