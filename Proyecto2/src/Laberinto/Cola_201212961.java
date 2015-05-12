package Laberinto;

public class Cola_201212961 {
	
private Nodo_201212961 inicio, fin;
    
    public Cola_201212961(){
        inicio=fin = null;
    }
    
    boolean empty(){
        if(inicio==null){
            return true; //SI INICIO APUNTA A NADA, ESTA VACIA.
        }
        return false;
    }
    
    public void add(int PosX, int PosY){
        if(empty()==false){//SI YA TIENE ALGO
            fin.siguiente = new Nodo_201212961(PosX,PosY);
            fin = fin.siguiente;
        }else{//SI AUN NO HAY NADA
            inicio=fin = new Nodo_201212961(PosX,PosY);
        }
        //System.out.println(PosX +","+ PosY);//MOSTRANDO LOS DATOS
    }
    
    public String sacar(){
    	String devolver=""; int px=0,py=0;
    	
    	px = inicio.PoX;
    	py = inicio.PoY;
    	devolver = px +","+ py;
        inicio = inicio.siguiente;
        return devolver;
    }
    
}
