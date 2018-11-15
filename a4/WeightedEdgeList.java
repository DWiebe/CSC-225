//David Wiebe
//V00875342

/*
   The WeightedEdgeNode class is provided to you for CSC 225
   by Wendy Myrvold.  You do not have permission to distribute
   this code or to use it for any other purpose.

   You should add code for edgeSort and minWeightTree.
   Otherwise, do not modify the code.

*/

import java.util.*;
import java.io.*;
public class WeightedEdgeList
{
    public static boolean debug= false; // Change to false before submitting.

    int n;
    int m;
    WeightedEdgeNode start;
    WeightedEdgeNode rear;
    
	//uses radix sort to sort w, u, and v in order to radix sort e
	public void edgeSort()
	{
		//for each parameter in edge 
		//(goes from 2 to 0 because values at edge[0] are most significant)
		for(int p = 2; p > -1; p--){
			
			//find the max number of digits for edge[p] of all nodes
			int max = 0;
			WeightedEdgeNode temp = start;
			for(int x = 0; x < m; x++){
				if (temp.edge[p] > max){
					max = temp.edge[p];
				}
				temp = temp.next;
			}
			max = (int)(Math.log10(max) + 1);

			//for each digit
			for(int x = 0; x < max; x++){
				
				//node to traverse the list
				temp = start;
				
				//initialize sublists
				WeightedEdgeList[] sub = new WeightedEdgeList[10];
				for(int s = 0; s < 10; s++){
					sub[s] = new WeightedEdgeList();
				}
				
				//for each edge in the list
				for(int y = 0; y < m; y++){
					
					//puts the edges into the correct list based on the value of its digits
					sub[((temp.edge[p] % (int)Math.pow(10, x + 1))/(int)(Math.pow(10, x)))].addRear(new WeightedEdgeNode(temp.edge[0], temp.edge[1], temp.edge[2], null));
					
					temp = temp.next;
				}
				
				//clear the list
				start = null;
				rear = null;
				
				//rebuild the list with the sublists
				for(int z = 0; z < 10; z++){
					
					if (start == null && sub[z].start != null){
						start = sub[z].start;
						rear = sub[z].rear;
					} else if (sub[z].start != null) {
						
						rear.next = sub[z].start;
						rear = sub[z].rear;
					}
					
				
				}
				
				
			}
		}
		
		
	}
	
	
	
/*  You should assume this method is called with the edges
    already sorted by weight. The two tasks have been 
    separated so that you can get part marks if one of your
    methods works but the other one does not.
*/
    
	public WeightedEdgeList minWeightTree()
    {
		//list to contain edges of the minimum weight spanning tree
		WeightedEdgeList treeList = new WeightedEdgeList();
		treeList.addRear(new WeightedEdgeNode(start.edge[0], start.edge[1],start.edge[2], null));
		
		//UnionFind to maintain information
		UnionFind uf = new UnionFind(n);
		
		//the first value is guaranteed to be in the minimum weight tree
		uf.union(start.edge[1], start.edge[2]);
		
		//node to traverse list
		WeightedEdgeNode temp = start;
		
		//always checking temp.next because it is easier to remove nodes that way
		while(temp.next != null){

			//if u and v are not in the same component
			if(!uf.same_component(temp.next.edge[1], temp.next.edge[2])){
				
				//adds the node to treeList and updates the UnionFind
				treeList.addRear(new WeightedEdgeNode(temp.next.edge[0], temp.next.edge[1],temp.next.edge[2], null));
				uf.union(temp.next.edge[1], temp.next.edge[2]);
				
				//remove the node
				temp.next = temp.next.next;
				
			} else {
				
				//dont always move to next node because when a node is removed, temp.next changes anyways
				temp = temp.next;
			}
		}
		//removes the first node since it has been added to treeList
		start = start.next;
        
		return(treeList);
    }

// Do not make any changes to code below this line:
//----------------------------------------------------------//

    public WeightedEdgeList(int num_vertex, int num_edge, 
              WeightedEdgeNode start_node, WeightedEdgeNode rear_node)
    {
        n=num_vertex;
        m=num_edge;
        start= start_node;
        rear= rear_node;
    }

    public WeightedEdgeList()
    {
        n=0;
        m=0;
        start= null;
        rear= null;
    }

/*  
    The input format consists of two integers
    n m 
    where 
    n is the number of nodes of the graph, and
    m is the number of edges of the graph.
    Legal edge weights are always greater than or equal to 1.
    Then for each edge (u, v) with weight w the input contains:
    w  u  v
*/

    public static WeightedEdgeList readWeightedEdgeList(Scanner in)
    {
        WeightedEdgeList G;
        WeightedEdgeNode add_node;

        int num_edge;
        int w, u, v;
        int i;

        if (! in.hasNextInt()) return null;

        G= new WeightedEdgeList();
        G.n= in.nextInt();

        if (! in.hasNextInt())
        {
           System.out.println("Missing value for m.");
           return(null);
        }

        num_edge= in.nextInt();

        if (debug) System.out.println("n= " + G.n + "  m= " + num_edge);

        for (i=0; i < num_edge; i++)
        {
            if (! in.hasNextInt())
            {
               System.out.println("Error- Missing weight for edge " + (i+1));
               return(null);
            }
            w= in.nextInt();
            if (w < 1)
            {
               System.out.println("Error- Invalid edge weight " + w);
               return(null);
            }

            if (! in.hasNextInt())
            {
               System.out.println("Error- Missing u for edge " + (i+1));
               return(null);
            }
            u= in.nextInt();

            if (! in.hasNextInt())
            {
               System.out.println("Error- Missing v for edge " + (i+1));
               return(null);
            }
            v= in.nextInt();

            if (u < 0 || v < 0 || u >= G.n || v >= G.n)
            {
                System.out.println("Error- Invalid edge: " + u + " " + v);
                return(null);
            } 

            add_node= new WeightedEdgeNode(w, u, v, null);
            G.addRear(add_node);
        }
        return(G);
    }
    public void addRear(WeightedEdgeNode add_node)
    {
        if (start == null)
        {
            start= add_node;
        }
        else
        {
            rear.next= add_node;
        }
        rear= add_node;
        rear.next= null;
        m++;
    }

    public void printWeightedEdgeList()
    {
        WeightedEdgeNode current;

        int n_per_line=5;
        int num;
        int i;

        current= start;
        num=0;
        while (current != null)
        {
            System.out.format("[%3d (%3d, %3d)] ", current.edge[0], 
                                      current.edge[1], current.edge[2]);
            num++;
            if (num== n_per_line)
            {
               System.out.println();
               num=0;
            }
            current= current.next;
        }
        if (num!= 0) System.out.println();
    }
}
