import java.util.Arrays;
import java.util.LinkedList;

//classe respectiva aos nós
public class Node extends Jogodos15 implements Comparable<Node> {

	public int matriz[][];
	public long depth; //nivel/altura da arvore
	public long distance; //distancia manhattan
	public static int escolhaPesquisa;
	public Node parent;


	public Node(int[][] matriz,long altura){
		this.matriz = matriz;
		this.depth = altura;
		this.distance=0;
		this.parent=null;
		this.escolhaPesquisa=0;
	}

	//novo construtor para copias
	public Node(int[][] matriz,long altura, int pesq){  
		this.matriz = matriz;
		this.depth = altura;
		this.distance=0;
		this.parent=null;
		this.escolhaPesquisa=pesq;
	}




	public Node(Node other){
		this.matriz=other.getMatriz();
		this.depth=other.getDepth();
		this.parent=other;
		this.escolhaPesquisa=other.getescolhaPesquisa();		
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
	
	

	//cria os descendentes
	public static LinkedList<Node> makedescendants(Node in){

		LinkedList<Node> noList = new LinkedList<Node>();
		findzero(in);
		zeroi = posi; //linha onde esta o zero 
		zeroj = posj; //coluna onde esta o zero

		Node tran;
		//a posicao anterior onde esta o zero nao pode sair fora da matriz
		if(!(zeroi-1<0)){
			tran=trocaPos(in, zeroi-1, zeroj);
				if(/*!Jogodos15.visited.containsValue(tran.matriz) &&*/ !(in.matriz.equals(tran.matriz))){
					Node filho = new Node(tran);	
					noList.add(filho);	
					//System.out.println("esquerda");
				}
		}
		//a posicao a seguir tem de ser menor que o tamanho da matriz para poder haver troca
		if(!(zeroi+1>=n)){
			tran=trocaPos(in, zeroi+1, zeroj);
			if(/*!Jogodos15.visited.containsValue(tran.matriz) &&*/ !(in.matriz.equals(tran.matriz))){
				Node filho = new Node(tran);	
				noList.add(filho);	
			//	System.out.println("dir");
				
			}
		}
		//a posicao anterior onde esta o zero nao pode sair fora da matriz 	
		if(!(zeroj-1<0)){
			tran=trocaPos(in, zeroi, zeroj-1);
			if(/*!Jogodos15.visited.containsValue(tran.matriz) &&*/ !(in.matriz.equals(tran.matriz))){
				Node filho = new Node(tran);	
				noList.add(filho);	
				//System.out.println("up");
			}
		}

		//a posicao a seguir tem de ser menor que o tamanho da matriz para poder haver troca
		if(!(zeroj+1>=n)){
			tran=trocaPos(in, zeroi, zeroj+1);
			if(/*!Jogodos15.visited.containsValue(tran.matriz) &&*/ !(in.matriz.equals(tran.matriz))){
				Node filho = new Node(tran);	
				noList.add(filho);	
				//System.out.println("baixo");
			}
		}
		return noList;
	}

	//troca as posicoes e retorna a nova matriz
	public static Node trocaPos(Node in, int i, int j){
		int matriz[][]= new int[n][n];
		
		//copia para uma nova matriz
		for(int ii=0; ii<n; ii++){
			for(int jj=0; jj<n; jj++){
				matriz[ii][jj]=in.matriz[ii][jj];
			}
		}
		 int aux;
		//trocar valores na Matriz copia
		aux = matriz[i][j];
		matriz[zeroi][zeroj] = aux;
		matriz[i][j]= 0;

		Node copyM = new Node(matriz, in.depth+1);
		//adiciona mais 1 nivel ao no pai
		//copyM.depth+=1;
		copyM.parent=in;

		/*
		System.out.println("\n \n");
		for(int asd= 0; asd<4; asd++){
			for(int asr=0; asr < 4; asr++){
				System.out.print(matriz[asd][asr]+" | " );
			}
			System.out.println();
		}
		System.out.println();
		for(int asd= 0; asd<4; asd++){
			for(int asr=0; asr < 4; asr++){
				System.out.print(in.matriz[asd][asr]+ " | " );
			}
			System.out.println();
		}
		System.out.println("\n \n");*/
		
		return copyM;
	}

	//calcula a distancia entre a posicao inicial e a posicao final 
	//public static int calcManattanDistance(int matrizIn[][], int matrizFim[][]){
	public static int calcManhattanDistance(Node in, Node out){
		//coordenadas da posicao do valor
		int coord[]=new int[2]; 
		int distance=0;

		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){

				//verifica em que posicao se encontra o valor na matriz final
				coord=findNum(in.matriz[i][j], out.matriz);

				int x1=coord[0];
				int y1=coord[1];

				distance+= Math.abs(i-x1)+Math.abs(j-y1);
			}
		}
		return distance;
	}

	//procura o valor na matriz final e devolve as suas coordenadas
	public static int[] findNum(int valor, int matriz[][]){
		//vector das coordenadas no valor
		int coordinates[] = new int[2];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (matriz[i][j] == valor) {
					coordinates[0] = i;
					coordinates[1] = j;
				}
			}
		}
		return coordinates;
	}
}