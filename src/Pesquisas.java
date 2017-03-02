import java.util.*;

public class Pesquisas extends Jogodos15{
	
	//public static LinkedList<int[][]> visited = new LinkedList<int[][]>();
	public static int countnodevisit = 0;  //calcular nos utilizados
	public static int countnos=0; //nos que sao gerados
	public static boolean foundsolution = false; 
	public static LinkedList<Node> noList = new LinkedList<Node>();
	public static LinkedList<Node> list = new LinkedList<Node>();
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
                 System.out.println(noList.size()+" "+countnodevisit+" "+removed.depth);
			            
            // se esse elemento ainda nao tiver sido visitado
			if(!visited.contains(removed)){ 				
				countnodevisit++;
				//adiciono a lista de visitados
				visited.add(removed); 			            
					
				//se o no removido for solucao
				//if(Arrays.deepEquals(removed.matriz, fim.matriz)){ 					
				if(removed.equals(fim))	{
					foundsolution = true; 					
					System.out.println("Solucao encontrada no nível: " + removed.depth + ", quantidade de nos criados: " + countnos);
					
					long stopTime = System.currentTimeMillis();
					//tempo de execução
				    long elapsedTime = stopTime - startTime; 
				    System.out.println("Tempo que demorou: " + elapsedTime + " ms");
					return;
				}
				
				//funcao para criar os filhos
				Node children = makedescendants(removed); 
				list.addLast(children);
				countnos++;
				
				//visitar todos os filhos
				//for(int i = 0; i<noList; i++){ 
				while(!list.isEmpty()){
					// se o filho ainda nao foi visitado
					//if(!visited.contains(children)){
					Node no = list.removeFirst();
						//adicionar ao inicio da lista
						noList.addFirst(no); 			
					//}
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
			//if(!visited.contains(removed.matriz)){
			if(removed.equals(fim))	{
				countnodevisit++;
				//adiciono a lista de visitados
				visited.add(removed); 						
					
				//se o no removido for solucao
				if(Arrays.deepEquals(removed.matriz, fim.matriz)){ 					
					foundsolution = true; 					
					System.out.println("Solucao encontrada no nível: " + removed.depth + ", quantidade de nos criados: " + countnos);
					
					long stopTime = System.currentTimeMillis();
					//tempo de execução
				    long elapsedTime = stopTime - startTime; 
				    System.out.println("Tempo que demorou: " + elapsedTime + " ms");
					return;
				}
				
				//funcao para criar os filhos
				Node children = makedescendants(removed); 
				list.addLast(children);
				countnos++;
				
				//visitar todos os filhos
				//for(int i = 0; i<noList; i++){ 
				while(!list.isEmpty()){
					// se o filho ainda nao foi visitado
					//if(!visited.contains(children)){
					Node no = list.removeFirst();
						//adicionar ao inicio da lista
                        noList.addLast(no); 			
                    }
                }
            	
                if(noList.size()%1000==0)
                      System.out.println(noList.size()+" "+countnodevisit+" "+removed.depth);
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
 		    	 		    	
 		    	if(!visited.contains(removed.matriz)){ 		
 		    		countnodevisit++; 						
 		    		visited.add(removed); 			
 		    		
 		    		if(removed.equals(fim)){ 			
 		    			foundsolution = true; 				
 		    			System.out.println("Solução encontrada " + removed.depth + "nós criados: " + countnos);
 		    			
 		    			long stopTime = System.currentTimeMillis();
 	        	 	    long elapsedTime = stopTime - startTime;
 	        	 	    System.out.println("Tempo que demorou: " + elapsedTime + " ms");
 		    			return;
 		    		}
 		    		
 		    		//funcao para criar os filhos
 		    		Node children = makedescendants(removed); 
 		    		countnos++;
 		    		//for(int i = 0; i<children.length; i++){ 	
 		    		while(!noList.isEmpty()){
 		    			//se o filho ainda nao foi visitado e tiver a altura permitida, isto é <= que o nivel (level)
 		    			if(!visited.contains(children.matriz) && children.depth <= level){ 
 		    				//adicionar ao inicio da lista
 		    				noList.addFirst(children); 			
 		    			}
 		    			
 		    		}
 		    		if(noList.size()%1000==0)
 	 	                System.out.println(noList.size()+" "+countnodevisit+" "+removed.depth);
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
			                
			if(!visited.contains(removed.matriz)){ 			
				countnodevisit++; 								
				visited.add(removed); 					
				
				//se o no removido for solucao
				if(Arrays.deepEquals(removed.matriz, fim.matriz)){ 					
					foundsolution = true; 					
					System.out.println("Solucao encontrada no nível: " + removed.depth + ", quantidade de nos criados: " + countnos);
					
					long stopTime = System.currentTimeMillis();
			 	    long elapsedTime = stopTime - startTime;
			 	    System.out.println("Tempo que demorou: " + elapsedTime + " ms");
					return;
				}
				
				//funcao para criar os filhos
				Node children = makedescendants(removed);
				countnos++;
				//for(int i = 0; i<children.length; i++){
				while(!noList.isEmpty()){
					// para cada filho, meter-lhe a distancia manhattan
                    children.distance = calcManhattanDistance(children, fim); 
                }
			
                //for(int i = 0; i<children.length; i++){
				while(!noList.isEmpty()){
                    if(!visited.contains(children.matriz)){ 	
                        noList.addFirst(children); 	
                    }
                }

                if(noList.size()%1000==0)
                    System.out.println(noList.size()+" "+countnodevisit+" "+removed.depth);
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
	}
}		
