import java.util.*;

public class Pesquisas extends Jogodos15{
	
	public static LinkedList<Node> visited = new LinkedList<Node>();
	public static int countnode = 0;
	public static boolean foundsolution = false;
	public static LinkedList<Node> noList = new LinkedList<Node>();
	
	
	//pesquisa em porfundidade (DFS)
	public static void DFS(Node in, Node fim) {
			noList.add(in); 									//adiciona configuracao inicial a lista
			
			while(!noList.isEmpty()){ 							
				Node removed = noList.removeFirst();			//removo primeiro elemento
				
				if(!visited.contains(removed)){ 				// se esse elemento ainda nao tiver sido visitado
					countnode++; 								// necessário para calcular nós utilizados (espaco)
					visited.add(removed); 						//adiciono a lista de visitados
					
					if(removed.equals(fim)){ 					//se o no removido for solucao
						foundsolution = true; 					//altero flag para true indicando que foi encontrada solucao
						System.out.println("Solução encontrada " + removed.depth + "nós criados: " + countnode);
						return;
					}
					
					Node children[] = makedescendants(removed); 	//chamo funcao para criar os filhos
					for(int i = 0; i<children.length; i++){ 		// visitar todos os filhos
						if(!visited.contains(children[i])){			// se o filho ainda nao foi visitado
							noList.addFirst(children[i]); 			//adicionar ao inicio da lista
						}
					}
				}
			}
	}
	
	
	//pesquisa em largura (BFS)
	public static void BFS(Node in, Node fim) {
		noList.add(in); 									//adiciona configuracao inicial a lista
		
		while(!noList.isEmpty()){ 						
			Node removed = noList.removeFirst();			//removo primeiro elemento
			
			if(!visited.contains(removed)){					// se esse elemento ainda nao tiver sido visitado
				countnode++;								// necessário para calcular nós utilizados (espaco)
				visited.add(removed);						//adiciono a lista de visitados
				
				if(removed.equals(fim)){					//se o no removido for solucao
					foundsolution = true;					//altero flag para true indicando que foi encontrada solucao
					System.out.println("Solução encontrada " + removed.depth + "nós criados: " + countnode);
					return;
				}
				
				Node children[] = makedescendants(removed); //chamo funcao para criar os filhos
				for(int i = 0; i<children.length; i++){ 	// visitar todos os filhos
					if(!visited.contains(children[i])){ 	// se o filho ainda nao foi visitado
						noList.addLast(children[i]); 		//adicionar ao fim da lista
					}
				}
			}
		}
	}
	
	
	//pesquisa Iterativa Limitada em Profundidade (IDFS)
	
	
	
	

	//pesquisa Greedy
	public static void Greedy(Node in, Node fim) {
		noList.add(in); 								//adiciona configuracao inicial a lista
		
		while(!noList.isEmpty()){ 					
			Node removed = noList.removeFirst(); 		//removo primeiro elemento
			
			if(!visited.contains(removed)){				// se esse elemento ainda nao tiver sido visitado
				countnode++; 							// necessário para calcular nós utilizados (espaco)
				visited.add(removed); 					//adiciono a lista de visitados
				
				if(removed.equals(fim)){ 				//se o no removido for solucao
					foundsolution = true; 				//altero flag para true indicando que foi encontrada solucao
					System.out.println("Solução encontrada " + removed.depth + "nós criados: " + countnode);
					return;
				}
				
				Node children[] = makedescendants(removed); 		//chamo funcao para criar os filhos
				for(int i = 0; i<children.length; i++){				// visitar todos os filhos
					children[i].distance = calcManhattanDistance(children[i], fim); 	// para cada filho, meter-lhe a distancia manhattan
				}
				
				
				for(int i = 0; i<children.length; i++){ 	// visitar todos os filhos
					if(!visited.contains(children[i])){ 	// se o filho ainda nao foi visitado
						noList.addFirst(children[i]); 		//adicionar ao inicio da lista
					}
				}
			}
		}
	}
	
	
	//A*
	public static void aStar(Node in, Node out){
		
	
	}
	
		
		
}
	
			
	

	
	