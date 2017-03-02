import java.util.*;

public class Pesquisas extends Jogodos15{
	
	public static LinkedList<int[][]> visited = new LinkedList<int[][]>();
	public static int countnode = 0;
	public static boolean foundsolution = false;
	public static LinkedList<Node> noList = new LinkedList<Node>();
	
	
	//pesquisa em porfundidade (DFS)
	public static void DFS(Node in, Node fim) {
			noList.add(in); 									//adiciona configuracao inicial a lista
			
			while(!noList.isEmpty()){ 							
				Node removed = noList.removeFirst();			//removo primeiro elemento
				
                if(noList.size()%1000==0)
                    System.out.println(noList.size()+" "+countnode+" "+removed.depth);
                    
				if(!visited.contains(removed.matriz)){ 				// se esse elemento ainda nao tiver sido visitado
					countnode++; 								// necessario para calcular nos utilizados (espaco)
					visited.add(removed.matriz); 						//adiciono a lista de visitados
					
					if(Arrays.deepEquals(removed.matriz, fim.matriz)){ 					//se o no removido for solucao
						foundsolution = true; 					//altero flag para true indicando que foi encontrada solucao
						System.out.println("Solucao encontrada " + removed.depth + "nos criados: " + countnode);
						return;
					}
					
					Node children[] = makedescendants(removed); 	//chamo funcao para criar os filhos
					for(int i = 0; i<children.length; i++){ 		// visitar todos os filhos
						if(!visited.contains(children[i].matriz)){			// se o filho ainda nao foi visitado
							noList.addFirst(children[i]); 			//adicionar ao inicio da lista
						}
					}
				}
			}
	}
	
	//Pesqusa em largura
 	public static void BFS(Node in, Node fim) {
        noList.add(in); 									//adiciona configuracao inicial a lista
        
        while(!noList.isEmpty()){ 							
            Node removed = noList.removeFirst();			//removo primeiro elemento
            
            removed.printNode();
            
            if(noList.size()%1000==0)
                System.out.println(noList.size()+" "+countnode+" "+removed.depth);
                
            if(!visited.contains(removed.matriz)){ 				// se esse elemento ainda nao tiver sido visitado
                countnode++; 								// necessario para calcular nos utilizados (espaco)
                visited.add(removed.matriz); 						//adiciono a lista de visitados
                
                if(Arrays.deepEquals(removed.matriz, fim.matriz)){ 					//se o no removido for solucao
                    foundsolution = true; 					//altero flag para true indicando que foi encontrada solucao
                    System.out.println("Solucao encontrada " + removed.depth + "nos criados: " + countnode);
                    return;
                }
                
                Node children[] = makedescendants(removed); 	//chamo funcao para criar os filhos
                for(int i = 0; i<children.length; i++){ 		// visitar todos os filhos
                    if(!visited.contains(children[i].matriz)){			// se o filho ainda nao foi visitado
                        noList.addLast(children[i]); 			//adicionar ao fim da lista
                    }
                }
            }
        }
	}
	
	//pesquisa Iterativa Limitada em Profundidade (IDFS)
 	public static void IDS(Node in, Node fim) {
 		int level = 0;
 		while(!foundsolution){
 		    noList.removeAll(noList);
 		    visited.removeAll(visited);
 					
 		    noList.add(in); 						//adiciona configuracao inicial a lista
 		    while(!noList.isEmpty()){ 				//enquanto a lista nao esta vazia
 			Node removed = noList.removeFirst(); 	//removo primeiro elemento
 			if(!visited.contains(removed.matriz)){ 		// se esse elemento ainda nao tiver sido visitado
 			    countnode++; 						// necessário para calcular nós utilizados (espaco)
 			    visited.addFirst(removed.matriz); 			//adiciono a lista de visitados
 			    if(removed.equals(fim)){ 			//se o no removido for solucao
 				foundsolution = true; 				//altero flag para true indicando que foi encontrada solucao
 				System.out.println("Solução encontrada " + removed.depth + "nós criados: " + countnode);
 				return;
 			    }
 			    Node children[] = makedescendants(removed); //chamo funcao para criar os filhos
 			    for(int i = 0; i<children.length; i++){ 	// visitar todos os filhos
 				if(!visited.contains(children[i].matriz) && children[i].depth <= level){ // se o filho ainda nao foi visitado e tiver a altura permitida, isto é <= que o nivel (level)
 				    noList.addFirst(children[i]); 			//adicionar ao inicio da lista
 				}
 				visited.removeFirst(); //se o no falhou a condicao anterior entao eu quero remove-lo dos visitados para poder ser expandido noutro ramo
 			    }
 			}
 		    }
 		    level++;
 		}
 	}
	
    
    //pesquisa greedy
	public static void Greedy(Node in, Node fim) {
			noList.add(in); 									//adiciona configuracao inicial a lista
			
			while(!noList.isEmpty()){ 							
				Node removed = noList.removeFirst();			//removo primeiro elemento
				
                if(noList.size()%1000==0)
                    System.out.println(noList.size()+" "+countnode+" "+removed.depth);
                    
				if(!visited.contains(removed.matriz)){ 				// se esse elemento ainda nao tiver sido visitado
					countnode++; 								// necessario para calcular nos utilizados (espaco)
					visited.add(removed.matriz); 						//adiciono a lista de visitados
					
					if(Arrays.deepEquals(removed.matriz, fim.matriz)){ 					//se o no removido for solucao
						foundsolution = true; 					//altero flag para true indicando que foi encontrada solucao
						System.out.println("Solucao encontrada " + removed.depth + "nos criados: " + countnode);
						return;
					}
					
					Node children[] = makedescendants(removed); 	//chamo funcao para criar os filhos
					for(int i = 0; i<children.length; i++){				// visitar todos os filhos
                        children[i].distance = calcManhattanDistance(children[i], fim); 	// para cada filho, meter-lhe a distancia manhattan
                    }
				
                    for(int i = 0; i<children.length; i++){ 	// visitar todos os filhos
                        if(!visited.contains(children[i].matriz)){ 	// se o filho ainda nao foi visitado
                            noList.addFirst(children[i]); 		//adicionar ao inicio da lista
                        }
                    }
                    Collections.sort(noList);
				}
			}
	}
	
	//A*
	public static void aStar(Node in, Node out){		
			noList.add(in); 									//adiciona configuracao inicial a lista
			
			while(!noList.isEmpty()){ 							
				Node removed = noList.removeFirst();			//removo primeiro elemento
				
                if(noList.size()%1000==0)
                    System.out.println(noList.size()+" "+countnode+" "+removed.depth);
                    
				if(!visited.contains(removed.matriz)){ 				// se esse elemento ainda nao tiver sido visitado
					countnode++; 								// necessario para calcular nos utilizados (espaco)
					visited.add(removed.matriz); 						//adiciono a lista de visitados
					
					if(Arrays.deepEquals(removed.matriz, out.matriz)){ 					//se o no removido for solucao
						foundsolution = true; 					//altero flag para true indicando que foi encontrada solucao
						System.out.println("Solucao encontrada " + removed.depth + "nos criados: " + countnode);
						return;
					}
					
					Node children[] = makedescendants(removed); 	//chamo funcao para criar os filhos
					for(int i = 0; i<children.length; i++){				// visitar todos os filhos
                        children[i].distance = calcManhattanDistance(children[i], out); 	// para cada filho, meter-lhe a distancia manhattan
                    }
				
                    Arrays.sort(children);
                    for(int i = 0; i<children.length; i++){ 	// visitar todos os filhos
                        if(!visited.contains(children[i].matriz)){ 	// se o filho ainda nao foi visitado
                            noList.addFirst(children[i]); 		//adicionar ao inicio da lista
                        }
                    }
				}
			}
	}
	
}		
