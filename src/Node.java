import java.util.Arrays;

//classe respectiva aos nós
public class Node extends Jogodos15 implements Comparable<Node> {
	
	public int matriz[][];
	public long depth; //nivel/altura da arvore
	public long distance; //distancia manhattan
	public boolean visited; //no visitado
	public static int escolhaPesquisa;
	public Node parent;

  
    public Node(int[][] matriz,long altura){
    	this.matriz = matriz;
    	this.depth = altura;
    	this.distance=0;
    	this.visited=false;
        this.parent=null;
        this.escolhaPesquisa=0;
    }
    
    //novo construtor para copias
    public Node(int[][] matriz,long altura, int pesq){  
    	this.matriz = matriz;
    	this.depth = altura;
    	this.distance=0;
    	this.visited=false;
        this.parent=null;
        this.escolhaPesquisa=pesq;
    }
	
	public Node(Node other){
		this(other.getMatriz(), other.getDepth(), other.getescolhaPesquisa());		
	}
	
    public int getescolhaPesquisa(){
        return Node.escolhaPesquisa;
    }
    
	public int[][] getMatriz(){
    
        int[][] res = new int[this.matriz.length][];
        for (int i = 0; i < this.matriz.length; i++) 
            res[i] = Arrays.copyOf(this.matriz[i], this.matriz[i].length);
		return res;
        
	}
    
    public void printNode(){
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz.length; j++) 
                System.out.print("\t"+this.matriz[i][j]);
            System.out.println();
        }
        System.out.println();
                
    }
	
    public long getDepth() {
		return depth;
	}
	
	public long getDistance(){
		return distance;
	}
	
	@Override
	public int compareTo(Node no){
		if(escolhaPesquisa==4){ 
			//escolha ser greedy
			if(this.distance > no.distance)
				return 1;
			else if(this.distance < no.distance)
				return -1;
			else
				return 0;
		}
		else{
			//calcula a funcao, sendo a heuristica o manhattan distance
			long f = this.distance+this.depth; //funcao
			if(f > no.distance+no.depth)
				return 1;
			else if(f < no.distance+no.depth)
				return -1;
		}
		return 0;
	}
	
	public int hashCode() {
		return matriz.toString().hashCode();
	}
}