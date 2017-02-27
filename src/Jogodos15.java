import java.util.*;

/**
 * Jogo dos 15
 * 
 * @author Ana Coutinho up200303059
 *         Ana Germano up201105083
 *         
 */

class Node{
    int matriz[][];
    int altura;
    
    public Node(int[][] matriz,int altura){
    	this.matriz = matriz;
    	this.altura = altura;
    }
    
   /* public static int depth(int altura){
    	return altura+1;
    }*/
}

public class Jogodos15 {

    public static int posi = 0;
    public static int posj = 0;
    public static int zeroi, zeroj;

    //descobrir a posição do zero/espaço vazio
    public static void findzero(int[][] matriz, int n){
    	
    	for(int i=0; i<n; i++){
    		for(int j =0; j<n; j++){
    			if(matriz[i][j] == 0){
    				posi = i;
    				posj = j;
    			}	    
    		}
    	}
    }
    
    //converte a matriz num vector
    public static int[] createarray(int matriz[][], int n){
    	int k = 0;
    	int[] array = new int[n*n];
    
    	for(int i=0; i<n; i++){
    		for(int j=0; j<n; j++){
    			array[k]=matriz[i][j];
    			k++;
    		}
    	}
		return array;
    }

    //verifica a solvabilidade impar 
    //verifica se existe solução entre a matriz incial e a matriz final
    static boolean solvabilidadeimpar(int[][] in, int fim[][], int n){
    	int tam=n*n; //tamanho do vector
    	int vin[] = new int[tam]; //vector da matriz inicial
    	int vfim[]=new int[tam]; //vector da matriz final
    	int countin=0, countfim=0;

    	//cria os vectores
    	vin=createarray(in, n);
    	vfim=createarray(fim, n);
    	
    	//conta o numero de inversões em cada vector
    	for(int i=0; i<tam; i++){
    		for(int j=i; j<tam; j++){
    			if(vin[j] != 0 && vin[i] > vin[j])
    				countin++;
    			if(vfim[j] != 0 && vfim[i] > vfim[j])
    				countfim++;
    		}
    	}
    	
    	if((countin%2)==0 && (countfim%2)==0)
    		return true;

    	else if((countin%2)!=0 && (countfim%2)!=0)
    		return true;

    	else
    		return false;
    	}

    	//verefica se ambas as configurações conseguem chegar a uma configuração final no caso da solvabilidade par
    	public static boolean verifica(int array[], int lzero, int size){
    		int count = 0;

    		for(int i=0; i<size; i++){
    			for(int j=i; j<size; j++){
    				if(array[j] != 0 && array[i] > array[j]){
    					count++;
    				}
    			}
    		}

    		if((lzero%2)==0 && (count%2)!=0)
    			return true;

    		else if((lzero%2)!=0 && (count%2)==0)
    			return true;

    		else
    			return false;
    	}
    
    //verifica a solvabilidade par
    //verifica se existe solução entre a matriz incial e a matriz final
    public static boolean  solvabilidadepar(int in[][], int fim[][], int n){
    	int tam=n*n; //tamanho do vector
    	int vin[] = new int[tam]; //vector da matriz inicial
    	int vfim[]=new int[tam]; //vector da matriz final

    	vin=createarray(in, n);
    	vfim=createarray(fim, n);

    	findzero(in, n);
    	int localin = n-posi;

    	findzero(fim, n);
    	int localfim = n-posi;
	
    	if(verifica(vin, localin, tam) && verifica(vfim, localfim, tam))
    		return true;

    	else if(!verifica(vin, localin, tam) && !verifica(vfim, localfim, tam))
    		return true;

    	else
    		return false;
    }
    
    //copia a matriz incial para a matriz dos descendentes
    //Para poder alterar a matriz sem mexer na matriz inicial
    public static int[][] copyMatriz(int matriz[][], int n){
		int descen[][]= new int[n][n]; //matriz descendentes 
		 
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				matriz[i][j]=descen[i][j];
			}
		}
		return descen;
	}

    //cria os descendentes
	public static LinkedList<Node> makedescendants(int matriz[][], int n){
	
		LinkedList<Node> listFilhos = new LinkedList<Node>();  
		findzero(matriz, n);
		zeroi = posi; //linha onde está o zero 
		zeroj = posj; //coluna onde está o zero
	
		//a posição anterior onde está o zero nao pode sair fora da matriz
		if(!(zeroi-1<0)){
			listFilhos.add(trocaPos(matriz, n, zeroi-1, zeroj));
		}
		
		//a posição a seguir tem de ser menor que o tamanho da matriz para poder haver troca
		if(!(zeroi+1>=n)){
			listFilhos.add(trocaPos(matriz, n, zeroi+1, zeroj));
		}
		
	
		//a posição anterior onde está o zero nao pode sair fora da matriz 	
		if(!(zeroj-1>0)){
			listFilhos.add(trocaPos(matriz, n, zeroi, zeroj-1));
		}
	
		//a posição a seguir tem de ser menor que o tamanho da matriz para poder haver troca
		if(!(zeroj+1>=n)){
			listFilhos.add(trocaPos(matriz, n, zeroi, zeroj+1));
		}
		
		return listFilhos;
	}
	
	//troca as posições e retorna a nova matriz
	//***********FALTA A ALTURA********
	public static Node trocaPos(int matriz[][], int n, int i, int j){
		int copyM[][] = copyMatriz(matriz, n);
		
		//trocar valores na Matriz copia
		copyM[zeroi][zeroj] = copyM[i][j];
		copyM[i][j]= 0;
		
		Node filho = new Node(copyM,0); //pai +1

		return filho;
	}
	
	//calcula a distancia entre a posicao inicial e a posicao final 
	public static int calManattanDistance(int matrizIn[][], int matrizFim[][], int n){
	
		int distance = 0;
		
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				
				findzero(matrizIn, n);
				//guarda as coordenadas da matriz inicial
				int x0=posi; 
				int y0=posj;
				
				findzero(matrizFim, n);
				//guarda as coordenadas da matriz final
				int x1=posi;
				int y1=posj;
				
				distance+= Math.abs(x0-x1)+Math.abs(y0-y1);
			}
		}
		return distance;
	}
	
	public static int findNum(int matriz, int i, int j){
		
		return num;
	}
	
	/*//Algoritmo Geral de Busca
	public static boolean GeneralSearchAlgorithm( ){
		//algoritmos de pesquisa 
		
		while(){
			
			
		}
	return false;	
	}
    */
    public static void main(String args[]){
    	Scanner stdin = new Scanner(System.in);
		
    	//cria uma lista de Nodes
    	LinkedList<Node> list= new LinkedList<Node>();
    	
    	System.out.println("Insira o tamanho do tabuleiro");
    	//tamanho do tabuleiro
    	int n = stdin.nextInt();
    	
    	//matriz inicial
    	int in [][]= new int[n][n];	
    	//matriz final
    	int fim [][]= new int[n][n];
	
    	System.out.println("Insira o tabuleiro com as posições iniciais");
    	//lê a matriz inicial	
    	for(int i=0; i<n; i++){
    		for(int j=0; j<n; j++){
    			in[i][j] = stdin.nextInt();	
    		}
    	}
	
    	//adiciona a matriz na primeira posição da arvore
    	Node posInicial = new Node(in, 0); 
    	list.add(posInicial);
    	//makedescendants(in, n);

    	System.out.println("Insira o tabuleiro com as posições finais");
    	//lê a matriz final
    	for(int i=0; i<n; i++){
    		for(int j=0; j<n; j++){
    			fim[i][j] = stdin.nextInt();
    		}
    	}

    	if((n%2) == 0){
    		if(solvabilidadepar(in, fim, n)){
    			System.out.println("Vamos em frente!! :D");
    		}
    		else{
    			System.out.println("Não é possível resolver :(");
    			return;
    		}
    	}

    	else{
    		if(solvabilidadeimpar(in, fim, n)){
    			System.out.println("Vamos em frente!! :D");
    		}
    		else{
    			System.out.println("Não é possível resolver :(");
    			return;
    		}
    	}
    }
}
