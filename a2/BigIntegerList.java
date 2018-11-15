//David Wiebe
//V00875342


import java.util.*;
import java.io.*;
/* This class is for creating a singly linked class of big integer nodes. */
/* Hand this in together with your LinkedList.java. */
class BigIntegerList
{
	int n; // Number of items in the list.
	BigIntegerNode start;
	BigIntegerNode rear;

// You can set debug=true when you are debugging and want
// to print out what your program is doing.
// Please leave your debugging messages in your code when
// you submit it. Change debug back to false before
// submitting your code.
	static final boolean debug= false;

	public BigIntegerList()
	{
		n=0;
		start= null;
		rear= null;
	}
   
	
	public static BigIntegerList readBigIntegerList(Scanner in)
	{
		BigIntegerList problem;

		// Insert your code for Question 2 here.
		
		//check if there is any input
		if(!in.hasNextInt()){
			
			//no input so return null
			return null;
		}
		
		//Initialize list
		problem = new BigIntegerList();
		
		//list size is the first number from iput
		problem.n = in.nextInt();
		
		//keep track of the current bigInteger
		LinkedList tempList;
		
		//keep track of the current BigIntegerNode
		BigIntegerNode tempNode = problem.start;
		
		for(int i = 0; i < problem.n; i++){
			
			//read the the big integer
			tempList = LinkedList.readBigInteger(in);
			
			//put the big integer in the list
			if(i == 0){
				//case for first node
				
				//set the first node in the list as start and rear
				problem.start = new BigIntegerNode(tempList, null);
				problem.rear = problem.start;
				
				//set tempNode as the start of the list
				tempNode = problem.start;
			
			}else {
				
				//create the next node in the list
				tempNode.next = new BigIntegerNode(tempList, null);
				
				//move tempNode and rear pointers
				tempNode = tempNode.next;
				problem.rear = tempNode;
			} 
		}
		
		return(problem); 
	}
	
	public void printBigIntegerList()
	{
		// Insert your code for Question 3 here.
		
		//a node to traverse the list
		BigIntegerNode temp = start;
		
		//loop for every line of output
		for(int i = 0; i < n / 10 + 1; i++){
			
			//prints up to 10 BigIntegers per line
			for(int x = 0; x < 10; x++){
				
				//breaks the loop if there are less than 10 BigIntegers
				if(temp == null){
					break;
				}
				//print the BigInteger and a space afterwards
				temp.x.printBigInteger(8);
			
				//iterate to the next node
				temp = temp.next;
			}
			System.out.println();
			
		}
		
		System.out.println();
	}
	
	public void quickSort(int level)
	{
		// Insert your code for Question 4 here.
		
		if(n <= 1){
			
			//list of size 1 or 0 is already sorted so return
			return;
		}
		
		//create 3 new lists
		BigIntegerList list1 = new BigIntegerList();
		BigIntegerList list2 = new BigIntegerList();
		BigIntegerList list3 = new BigIntegerList();
		
		//a node to traverse the list starting from the second node
		//because the first node is guaranteed to be in list2
		BigIntegerNode temp = start.next;
		
		//set the pivot
		BigIntegerNode pivot = start;
		
		//add the pivot to list 2
		list2.addNode(pivot);
		
		for(int x = 1; x < n; x++){
			
			
			if(temp.x.compare(pivot.x) == 1){
				
				//number is greater than pivot
				list3.addNode(temp);
				
			} else if(temp.x.compare(pivot.x) == 0){
				
				//number is equal to the pivot
				list2.addNode(temp);
				
			} else{
				
				//number is less than the pivot
				list1.addNode(temp);
			}
			
			//iterate to the next node
			temp = temp.next;
		}
		
		//sort lists 1 and 3
		list1.quickSort(level + 1);
		list3.quickSort(level + 1);
		
		//put the lists together in order
		if(list1.n == 0 && list3.n == 0){
			
			//case where lists 1 and 3 are empty
			start = list2.start;
			rear = list2.rear;
			rear.next = null;
			
		} else if(list1.n == 0){
			
			//case where list1 is empty
			start = list2.start;
			list2.rear.next = list3.start;
			rear = list3.rear;
			rear.next = null;
			
		} else if(list3.n == 0){
			
			//case where list3 is empty
			start = list1.start;
			list1.rear.next = list2.start;
			rear = list2.rear;
			rear.next = null;
			
		} else{
			
			//case where all 3 lists have at least one element
			start = list1.start;
			list1.rear.next = list2.start;
			list2.rear.next = list3.start;
			rear = list3.rear;
			rear.next = null;
		}
		
		
	}
	
	private void addNode(BigIntegerNode x){
		
		if(n == 0){
			//case for adding the first node
			start = x;
			rear = start;
			n++;
		} else {
			//adds nodes to the end of the list
			rear.next = x;
			rear = rear.next;
			n++;
		}
	}
}
