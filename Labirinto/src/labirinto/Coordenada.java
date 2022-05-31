package labirinto;

public class Coordenada{
	
	private int x, y;
	 
	public Coordenada(int x, int y){
		this.x = x;
		this.y = y;
	}
	 
	public void setX(int x){
	    this.x = x;
	}

	public void setY(int y){
	    this.y = y;
	}
															    
	public int getX(){
		return this.x;
	}
	 
	public int getY(){
		return this.y;
	}
	 
	public String toString (){
		return ("("+this.x+";"+this.y+")");
	}

	public int hashCode (){
		int ret = 10;
		
		ret = ret*7 + new Integer(this.x).hashCode();
		ret = ret*7 + new Integer(this.y).hashCode();
		
		return ret;
	}
}

