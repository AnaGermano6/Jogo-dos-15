import java.util.*;

/**
 * Jogo dos 15
 * 
 * @author Ana Coutinho up200303059
 * @author Ana Germano up201105083
 *         
 */

//class principal
public class Jogodos15 {

    public static int posi = 0; 
    public static int posj = 0;
    public static int zeroi, zeroj;
    public static int n; //tamanho do tabuleiro

    //descobrir a posicao do zero/espaco vazio
    public static void findzero(Node d){
    	
    	for(int i=0; i<n; i++){
    		for(int j =0; j<n; j++){
    			if(d.matriz[i][j] == 0){
    				posi = i;
    				posj = j;
    			}	    
    		}
    	}
    }
	
	//converte a matriz num vector
    public static int[] createarray(Node d){
    	int k = 0;
    	int[] array = new int[n*n];
    
    	for(int i=0; i<n; i++){
    		for(int j=0; j<n; j++){
    			array[k]=d.matriz[i][j];
    			k++;
    		}
    	}
		return array;
    }

    //verifica a solvabilidade impar 
    //verifica se existe solucao entre a matriz incial e a matriz final
    static boolean solvabilidadeimpar(Node in, Node out){
    	int tam=n*n; //tamanho do vector
    	int vin[] = new int[tam]; //vector da matriz inicial
    	int vfim[]=new int[tam]; //vector da matriz final
    	int countin=0, countfim=0;

    	//cria os vectores
    	vin=createarray(in);
    	vfim=createarray(out);
    	
    	//conta o numero de inversoes em cada vector
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

    	//metodo auxilar que retorna um booleano para a solvabilidade par
    	//verifica se a matriz que e passada se tem solucao
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
    //verifica se existe solucao entre a matriz incial e a matriz final
    public static boolean  solvabilidadepar(Node in, Node out){
    	int tam=n*n; //tamanho do vector
    	int vin[] = new int[tam]; //vector da matriz inicial
    	int vfim[]=new int[tam]; //vector da matriz final

    	vin=createarray(in);
    	vfim=createarray(out);

        //ATENÇÃO AQUI
    	findzero(in);
    	int localin = n-posi;

    	findzero(out);
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
    public static Node copyNode(Node d){
		Node copia = new Node(d);
		return copia;
	}

    //cria os descendentes
	public static Node[] makedescendants(Node in){
	
		LinkedList<Node> listFilhos = new LinkedList<Node>();  
		findzero(in);
		zeroi = posi; //linha onde esta o zero 
		zeroj = posj; //coluna onde esta o zero
	
		//a posicao anterior onde esta o zero nao pode sair fora da matriz
		if(!(zeroi-1<0)){
			listFilhos.add(trocaPos(in, zeroi-1, zeroj));
		}
		
		//a posicao a seguir tem de ser menor que o tamanho da matriz para poder haver troca
		if(!(zeroi+1>=n)){
			listFilhos.add(trocaPos(in, zeroi+1, zeroj));
		}
		
	
		//a posicao anterior onde esta o zero nao pode sair fora da matriz 	
		if(!(zeroj-1<0)){
			listFilhos.add(trocaPos(in, zeroi, zeroj-1));
		}
	
		//a posicao a seguir tem de ser menor que o tamanho da matriz para poder haver troca
		if(!(zeroj+1>=n)){
			listFilhos.add(trocaPos(in, zeroi, zeroj+1));
		}
		
		Node filhos[] = listFilhos.toArray(new Node[listFilhos.size()]); // convertido no array para poder ser navegado mais facilmente
		
		return filhos;
	}
	
	//troca as posicoes e retorna a nova matriz
	public static Node trocaPos(Node in, int i, int j){
		Node copyM = copyNode(in);
        //~ System.out.println(i +" "+ j);
        //trocar valores na Matriz copia
		copyM.matriz[zeroi][zeroj] = copyM.matriz[i][j];
		copyM.matriz[i][j]= 0;
		
		//adiciona mais 1 nivel ao no pai
		copyM.depth+=1;
        copyM.parent=in;

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
		//vector das coordenadas no valor, com duas posicoes  
		// 0 coordenada do i
		// 1 coordenada do j
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
	
    
    public static void main(String args[]){
    	Scanner stdin = new Scanner(System.in);
		
    	//cria uma lista de Nodes
    	LinkedList<Node> list= new LinkedList<Node>();
    	
    	System.out.println("Insira o tamanho do tabuleiro");
    	//tamanho do tabuleiro
    	n = stdin.nextInt();
    	
    	//matriz inicial
    	int matrizIn [][]= new int[n][n];	
    	//matriz final
    	int matrizFim [][]= new int[n][n];
	
    	System.out.println("Insira o tabuleiro com as posicoes iniciais");
    	//le a matriz inicial	
    	for(int i=0; i<n; i++){
    		for(int j=0; j<n; j++){
    			matrizIn[i][j] = stdin.nextInt();	
    		}
    	}
	
 
    	
    	System.out.println("Insira o tabuleiro com as posicoes finais");
    	//le a matriz final
    	for(int i=0; i<n; i++){
    		for(int j=0; j<n; j++){
    			matrizFim[i][j] = stdin.nextInt();
    		}
    	}
    	
       	//adiciona a matriz na primeira posicao da arvore
		Node in = new Node(matrizIn, 0);
		list.add(in);
		
		Node out = new Node(matrizFim, 0);
		
		
    	if((n%2) == 0){
    		if(solvabilidadepar(in, out)){
    			System.out.println("Vamos em frente!! :D");
    		}
    		else{
    			System.out.println("Nao e possivel resolver :(");
    			return;
    		}
    	}

    	else{
    		if(solvabilidadeimpar(in, out)){
    			System.out.println("Vamos em frente!! :D");
    		}
    		else{
    			System.out.println("Nao e possivel resolver :(");
    			return;
    		}
    	}
    	
    	//escolher uma estregia de busca
    	System.out.print("escolha uma estrategia de pesquisa\n" 
    						+ "1 -> Pesquisa em Profundidade(DFS)\n" 
    						+ "2 -> Pesquisa em Largura(BFS)\n"
    						+ "3 -> Pesquisa Iterativa Limitada em Profundidade\n"
    						+ "4 -> Pesquisa Greedy/Gulosa\n"
    						+ "5 -> A*\n");
    	
    	//guarda o valor da escolha
    	Node.escolhaPesquisa = stdin.nextInt();
    	
    	//implementa cada estrategia
    	switch(Node.escolhaPesquisa){
    	
   			case 1:	//DFS
   					Pesquisas.DFS(in, out);
   					break;
   			case 2: //BFS
   					Pesquisas.BFS(in, out);
   					break;
   			case 3: //pesquisa iterativa limitada em profundidade
   					Pesquisas.IDS(in, out);
   					break;
   			case 4: //pesquisa gulosa
   					Pesquisas.Greedy(in, out);
   					break;
   			case 5: //A*
   					Pesquisas.aStar(in, out);
   					break;
   			default:
   				//caso o numero de escolha seja diferente dos valores pedidos para a execucao
   				System.out.println("Nao intruduziu um valor correcto, por favor intruduza um numero entre 1 e 5");
   			break;
   		}
   	
   	}
}