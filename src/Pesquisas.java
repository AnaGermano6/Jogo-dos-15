import java.util.*;

public class Pesquisas extends Jogodos15{
	
	public static int countnodevisit = 0;  //calcular nos utilizados
	public static int countnos=0; //nos que sao gerados
	public static boolean foundsolution = false; 
	public static LinkedList<Node> noList = new LinkedList<Node>();
	public static HashMap<Integer,Integer> visited = new HashMap<Integer,Integer>();
	
	
	// last hurrah
	public static boolean comp(int a[][], int b[][]){
		
		for(int i = 0 ; i< 4 ; i++ )
			for(int j = 0 ; j<4 ; j++ )
				if(a[i][j]!=b[i][j])
					return false;
		return true;
	}

	//pesquisa em porfundidade (DFS)
	public static void DFS(Node in, Node fim) {

		//usado para calcular o tempo
		long startTime = System.currentTimeMillis();

		//adiciona configuracao inicial a lista
		noList.add(in); 									

		while(!noList.isEmpty()){ 							
			Node removed = noList.removeFirst();
			
			// se esse elemento ainda nao tiver sido visitado
			if(!visited.containsValue(java.util.Arrays.deepHashCode(removed.matriz))){ 			
				countnodevisit++;
				//adiciono a lista de visitados
				visited.put(countnodevisit,java.util.Arrays.deepHashCode(removed.matriz)); 					

				//se o no removido for solucao
				if(comp(removed.matriz, fim.matriz)){ 		
					foundsolution = true; 					
					System.out.println("Solucao encontrada no nível: " + removed.depth + ", quantidade de nos criados: " + countnos);

					long stopTime = System.currentTimeMillis();
					//tempo de execução
					long elapsedTime = stopTime - startTime; 
					System.out.println("Tempo que demorou: " + elapsedTime + " ms");
					return;
				}

				//funcao para criar os filhos
				LinkedList<Node> children  = Node.makedescendants(removed); 
				countnos+=children.size();

				//visitar todos os filhos
				//for(int i=0; i<children.size();i++){
				while(!children.isEmpty()){	
					Node n = children.removeFirst();
					if(!visited.containsValue(java.util.Arrays.deepHashCode(n.matriz))){
						//adicionar ao inicio da lista
						noList.addFirst(n); 			
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
			
			// se esse elemento ainda nao tiver sido visitado
			if(!visited.containsValue(java.util.Arrays.deepHashCode(removed.matriz))){ 			
				countnodevisit++;
				//adiciono a lista de visitados
				visited.put(countnodevisit,java.util.Arrays.deepHashCode(removed.matriz)); 					

				//se o no removido for solucao
				if(comp(removed.matriz, fim.matriz)){ 	
				//if(removed.equals(fim)){	
					foundsolution = true; 					
					System.out.println("Solucao encontrada no nível: " + removed.depth + ", quantidade de nos criados: " + countnos);

					long stopTime = System.currentTimeMillis();
					//tempo de execução
					long elapsedTime = stopTime - startTime;
					double time = elapsedTime/1000;
					System.out.println("Tempo que demorou: " + time + " s");
					return;
				}

				//funcao para criar os filhos
				LinkedList<Node> children  = Node.makedescendants(removed); 
				countnos+=children.size();

				//visitar todos os filhos
				while(!children.isEmpty()){	
					Node n = children.removeFirst();
					if(!visited.containsValue(java.util.Arrays.deepHashCode(n.matriz))){
						//adicionar ao inicio da lista
							noList.addLast(n); 			
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
 			//noList.removeAll(noList);
 		    //visited.remove(visited);

 		  	//adiciona configuracao inicial a lista
 			noList.add(in); 									

 			while(!noList.isEmpty()){ 							
 				Node removed = noList.removeFirst();
 				
 				// se esse elemento ainda nao tiver sido visitado
 				if(!visited.containsValue(java.util.Arrays.deepHashCode(removed.matriz))){ 			
 					countnodevisit++;
 					//adiciono a lista de visitados
 					visited.put(countnodevisit,java.util.Arrays.deepHashCode(removed.matriz)); 					

 					//se o no removido for solucao
 					if(comp(removed.matriz, fim.matriz)){ 		
 						foundsolution = true; 					
 						System.out.println("Solucao encontrada no nível: " + removed.depth + ", quantidade de nos criados: " + countnos);

 						long stopTime = System.currentTimeMillis();
 						//tempo de execução
 						long elapsedTime = stopTime - startTime;
 						double time = elapsedTime/1000;
 						System.out.println("Tempo que demorou: " + elapsedTime + " ms, convertido em segundos " + time);
 						return;
 					}

 					//funcao para criar os filhos
 					LinkedList<Node> children  = Node.makedescendants(removed); 
 					countnos+=children.size();

 					//visitar todos os filhos
 					while(!children.isEmpty()){	
 						Node n = children.removeFirst();
 		    			//se o filho ainda nao foi visitado e tiver a altura permitida, isto é <= que o nivel (level)
 						if(!visited.containsValue(java.util.Arrays.deepHashCode(n.matriz)) && n.depth<=level){
 							//adicionar ao inicio da lista
 		    				noList.addFirst(n); 			
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

		//adiciona configuracao inicial a lista
		noList.add(in); 									

		while(!noList.isEmpty()){ 							
			Node removed = noList.removeFirst();
			
			// se esse elemento ainda nao tiver sido visitado
			if(!visited.containsValue(java.util.Arrays.deepHashCode(removed.matriz))){ 			
				countnodevisit++;
				//adiciono a lista de visitados
				visited.put(countnodevisit,java.util.Arrays.deepHashCode(removed.matriz)); 					

				//se o no removido for solucao
				if(comp(removed.matriz, fim.matriz)){ 		
					foundsolution = true; 					
					System.out.println("Solucao encontrada no nível: " + removed.depth + ", quantidade de nos criados: " + countnos);

					long stopTime = System.currentTimeMillis();
					//tempo de execução
					long elapsedTime = stopTime - startTime;
					double time = elapsedTime/1000;
					System.out.println("Tempo que demorou: " + elapsedTime + " ms, convertido em segundos " + time);
					return;
				}

				//funcao para criar os filhos
				LinkedList<Node> children  = Node.makedescendants(removed);
				countnos+=children.size();
				
				while(!children.isEmpty()){
					Node n = children.removeFirst();
					// para cada filho, meter-lhe a distancia manhattan
                    n.distance = Node.calcManhattanDistance(n, fim); 
                }

				while(!children.isEmpty()){
					Node n = children.removeFirst();
                    if(!visited.containsValue(java.util.Arrays.deepHashCode(n.matriz))){ 	
                        noList.addFirst(n); 	
                    }
                }
			}
		}
	}
	
	/*

	//pesquisa A*
	public static void aStar(Node in, Node out){	

		long startTime = System.currentTimeMillis();

		noList.add(in); 									

		while(!noList.isEmpty()){ 							
			Node removed = noList.removeFirst();			

			if(!visited.contains(removed.matriz)){ 				
				countnodevisit++; 								
				visited.add(removed); 					

				if(Arrays.deepEquals(removed.matriz, out.matriz)){ 					
					foundsolution = true; 					
					System.out.println("Solucao encontrada no nível: " + removed.depth + ", quantidade de nos criados: " + countnodevisit);

					long stopTime = System.currentTimeMillis();
			 	    long elapsedTime = stopTime - startTime;
			 	    System.out.println("Tempo que demorou: " + elapsedTime);
					return;
				}

				//funcao para criar os filhos
				Node children = makedescendants(removed); 
				countnos++;
				//for(int i = 0; i<children.length; i++){	
				while(!noList.isEmpty()){
					// para cada filho, meter-lhe a distancia manhattan
                    children.distance = calcManhattanDistance(children, out);
				}

               // Arrays.sort(children);
                //for(int i = 0; i<children.length; i++){
				while(!noList.isEmpty()){
                	// se o filho ainda nao foi visitado
                    if(!visited.contains(children.matriz)){ 
                    	//adicionar ao inicio da lista
                        noList.addFirst(children); 		
                    }
                }

                if(noList.size()%1000==0)
                    System.out.println(noList.size()+" "+countnodevisit+" "+removed.depth);
			}
		}
	}*/
}		
