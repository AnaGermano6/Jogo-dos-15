import java.util.*;

public class Pesquisas extends Jogodos15{
	
	//public static LinkedList<int[][]> visited = new LinkedList<int[][]>();
	public static int countnode = 0;  //calcular nos utilizados (espaco)
	public static boolean foundsolution = false; 
	public static LinkedList<Node> noList = new LinkedList<Node>();
	public static HashSet<Node> visited = new HashSet<Node>();
	
	
	//pesquisa em porfundidade (DFS)
	public static void DFS(Node in, Node fim) {
		
		//usado para calcular o tempo
		long startTime = System.currentTimeMillis();
		
		//adiciona configuracao inicial a lista
		noList.add(in); 									
			
		while(!noList.isEmpty()){ 							
			Node removed = noList.removeFirst();			
				
            if(noList.size()%1000==0)
                  System.out.println(noList.size()+" "+countnode+" "+removed.depth);
            
            // se esse elemento ainda nao tiver sido visitado
			if(!visited.contains(removed.matriz)){ 				
				countnode++;
				//adiciono a lista de visitados
				visited.add(removed); 						
					
				//se o no removido for solucao
				if(Arrays.deepEquals(removed.matriz, fim.matriz)){ 					
					foundsolution = true; 					
					System.out.println("Solucao encontrada no nível: " + removed.depth + ", quantidade de nos criados: " + countnode);
					
					long stopTime = System.currentTimeMillis();
					//tempo de execução
				    long elapsedTime = stopTime - startTime; 
				    System.out.println("Tempo que demorou: " + elapsedTime + " ms");
					return;
				}
				
				//funcao para criar os filhos
				Node children[] = makedescendants(removed); 
				
				//visitar todos os filhos
				for(int i = 0; i<children.length; i++){ 
					// se o filho ainda nao foi visitado
					if(!visited.contains(children[i].matriz)){	
						//adicionar ao inicio da lista
						noList.addFirst(children[i]); 			
					}
				}
			}
		}
	}
	
	//Pesqusa em largura
 	public static void BFS(Node in, Node fim) {
 		//usado para calcular o tempo
		long startTime = System.currentTimeMillis();
		
		//adiciona configuracao inicial a lista
		noList.add(in); 									
			
		while(!noList.isEmpty()){ 							
			Node removed = noList.removeFirst();			
				
            if(noList.size()%1000==0)
                  System.out.println(noList.size()+" "+countnode+" "+removed.depth);
            
            // se esse elemento ainda nao tiver sido visitado
			if(!visited.contains(removed.matriz)){ 				
				countnode++;
				//adiciono a lista de visitados
				visited.add(removed); 						
					
				//se o no removido for solucao
				if(Arrays.deepEquals(removed.matriz, fim.matriz)){ 					
					foundsolution = true; 					
					System.out.println("Solucao encontrada no nível: " + removed.depth + ", quantidade de nos criados: " + countnode);
					
					long stopTime = System.currentTimeMillis();
					//tempo de execução
				    long elapsedTime = stopTime - startTime; 
				    System.out.println("Tempo que demorou: " + elapsedTime + " ms");
					return;
				}
				
				//funcao para criar os filhos
				Node children[] = makedescendants(removed); 
				
                for(int i = 0; i<children.length; i++){ 		
                    if(!visited.contains(children[i].matriz)){
                    	//adicionar ao fim da lista
                        noList.addLast(children[i]); 			
                    }
                }
            }
        }
	}
 	
 	//pesquisa Iterativa Limitada em Profundidade (IDFS)
 	public static void IDS(Node in, Node fim) {
 		
 		long startTime = System.currentTimeMillis();
 		
 		//o limite do nivel
 		int level = 0; 
 		
 		while(foundsolution==false){
 		    noList.removeAll(noList);
 		    visited.removeAll(visited);
 					
 		    noList.add(in); 						
 		    while(!noList.isEmpty()){ 
 		    	//removo primeiro elemento
 		    	Node removed = noList.removeFirst(); 	
 		    	
 		    	if(noList.size()%1000==0)
 	                System.out.println(noList.size()+" "+countnode+" "+removed.depth);
 		    	
 		    	if(!visited.contains(removed.matriz)){ 		
 		    		countnode++; 						
 		    		visited.add(removed); 			
 		    		
 		    		if(removed.equals(fim)){ 			
 		    			foundsolution = true; 				
 		    			System.out.println("Solução encontrada " + removed.depth + "nós criados: " + countnode);
 		    			
 		    			long stopTime = System.currentTimeMillis();
 	        	 	    long elapsedTime = stopTime - startTime;
 	        	 	    System.out.println("Tempo que demorou: " + elapsedTime + " ms");
 		    			return;
 		    		}
 		    		
 		    		//funcao para criar os filhos
 		    		Node children[] = makedescendants(removed); 
 		    		for(int i = 0; i<children.length; i++){ 	
 		    			//se o filho ainda nao foi visitado e tiver a altura permitida, isto é <= que o nivel (level)
 		    			if(!visited.contains(children[i].matriz) && children[i].depth <= level){ 
 		    				//adicionar ao inicio da lista
 		    				noList.addFirst(children[i]); 			
 		    			}
 		    			
 		    		}
 		    	}
 		    }
 		    visited.clear(); 
			noList.clear();
 		    level++;
 		}
 	}

    
    //pesquisa greedy
	public static void Greedy(Node in, Node fim) {
		
		long startTime = System.currentTimeMillis();
		
		noList.add(in); 									
		
		while(!noList.isEmpty()){ 							
			Node removed = noList.removeFirst();		
			
            if(noList.size()%1000==0)
                System.out.println(noList.size()+" "+countnode+" "+removed.depth);
                
			if(!visited.contains(removed.matriz)){ 			
				countnode++; 								
				visited.add(removed); 					
				
				//se o no removido for solucao
				if(Arrays.deepEquals(removed.matriz, fim.matriz)){ 					
					foundsolution = true; 					
					System.out.println("Solucao encontrada no nível: " + removed.depth + ", quantidade de nos criados: " + countnode);
					
					long stopTime = System.currentTimeMillis();
			 	    long elapsedTime = stopTime - startTime;
			 	    System.out.println("Tempo que demorou: " + elapsedTime + " ms");
					return;
				}
				
				//funcao para criar os filhos
				Node children[] = makedescendants(removed); 	
				for(int i = 0; i<children.length; i++){	
					// para cada filho, meter-lhe a distancia manhattan
                    children[i].distance = calcManhattanDistance(children[i], fim); 
                }
			
                for(int i = 0; i<children.length; i++){ 	
                    if(!visited.contains(children[i].matriz)){ 	
                        noList.addFirst(children[i]); 	
                    }
                }
                Collections.sort(noList);
			}
		}
	}
	
	//pesquisa A*
	public static void aStar(Node in, Node out){	
		
		long startTime = System.currentTimeMillis();
		
		noList.add(in); 									
		
		while(!noList.isEmpty()){ 							
			Node removed = noList.removeFirst();			
			
            if(noList.size()%1000==0)
                System.out.println(noList.size()+" "+countnode+" "+removed.depth);
                
			if(!visited.contains(removed.matriz)){ 				
				countnode++; 								
				visited.add(removed); 					
				
				if(Arrays.deepEquals(removed.matriz, out.matriz)){ 					
					foundsolution = true; 					
					System.out.println("Solucao encontrada no nível: " + removed.depth + ", quantidade de nos criados: " + countnode);
					
					long stopTime = System.currentTimeMillis();
			 	    long elapsedTime = stopTime - startTime;
			 	    System.out.println("Tempo que demorou: " + elapsedTime);
					return;
				}
				
				//funcao para criar os filhos
				Node children[] = makedescendants(removed); 	
				for(int i = 0; i<children.length; i++){	
					// para cada filho, meter-lhe a distancia manhattan
                    children[i].distance = calcManhattanDistance(children[i], out);
				}
			
                Arrays.sort(children);
                for(int i = 0; i<children.length; i++){
                	// se o filho ainda nao foi visitado
                    if(!visited.contains(children[i].matriz)){ 
                    	//adicionar ao inicio da lista
                        noList.addFirst(children[i]); 		
                    }
                }
			}
		}
	}
}		
