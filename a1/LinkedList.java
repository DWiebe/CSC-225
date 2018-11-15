//David Wiebe
//V00875342

import java.util.*;
import java.io.*;
public class LinkedList
{
   int n;
   ListNode start;
   ListNode rear;

   public LinkedList()
   {
       n= 0;
       start= null;
       rear= null;
   }
   public LinkedList(int size, ListNode first, ListNode last)
   {
       n= size;
       start= first;
       rear= last;
   }

//This routine reads in one big integer and returns its linked list 
//representation. The readBigInteger method should return null if there 
//is no further input remaining. The way that a big integer is given for 
//input is that first, an integer n_digit representing the number of 
//digits is specified followed by n_digit digits which should be in the 
//range 0 to 9. For example, the integer 2385 is input as 4 2 3 8 5 
//Do not make any assumptions about the number of spaces between the 
//integers or about how the input is arranged on lines. 
//You can use readInteger in LinkedList.java. 
   public static LinkedList readBigInteger(Scanner in)
   {	
		//creates a new linked list with length of the first read integer	
		LinkedList x = new LinkedList(in.nextInt(), null, null); 
		
		//loop creates a new ListNode n times
		for(int i = 0; i < x.n; i++){ 	
			
			//because of the code specifications, the new node will
			//always be the first node in the linked list
			ListNode newNode = new ListNode(in.nextInt(), x.start); 
			
			x.start = newNode;										
			if(i == 0){ 
				
				//case where there are no nodes in the list, 
				//so the new node is the first and last in the linked list
				x.rear = newNode; 
			}
		}
		
		

       return(x);
   }
   
   
   //The printBigInteger method should reverse the list using the reverse method, then traverse the list 
   //to print the big integer without leading zeroes and without spaces between the digits, and then should 
   //restore the original list by calling reverse again.
   public void printBigInteger()
   {
		//reverse the linked list according to code specifications
		reverse(0); 
		
		//ListNode i will be used to traverse the linked list
		ListNode i = start; 
		
		//used to determine if meaningful output has started (doesn't print leading 0's)
		boolean started = false; 
		
		//loop iterates until there are no remaining nodes
		while(i != null){ 
			
			if(i.next == null){ 

				//case where the linked list is entirely 0's so a single 0 will be printed
				//(also prints the last value of all other linked lists)
				System.out.print(i.data);    
			
			}else if(i.data == 0 && !started){ 
				//case where there are leading 0's, so nothing is printed
			
			}else {
				
				//default case where data is printed
				//since data has been printed, meanigful output has started
				//(0's after this value have actual meaning)
				System.out.print(i.data); 
				started = true;			  
			}							  
			
			//i traverses to the next node in the linked list
			i = i.next; 
		}
		
		//reverse the linked list to original state after all operation is complete
		reverse(0); 
   }
   
   
   //The reverse must operate recursively. First the list should be split into two lists, one with the first 
   //floor of n/2 entries and the other with the rest. You can use two new LinkedList objects each time you 
   //split the list in half but do not make any new ListNode objects. Then each of these lists should be 
   //reversed recursively. Then concatenate together the answers appropriately to get the final answer. The 
   //variable level is for debugging and represents the level of the recursive call. The initial call should 
   //be at level 0. If reverse is called from level k then the resulting invocation is at level k+1.
   public void reverse(int level)
   {
	   
        Test.checkList(this); // Do not remove or move this statement.
				
		if(n <= 1){ 
			
			//case where there is only 1 node, so no reversal is necessary
			return;
	    
		} else {
			
			//create 2 new lists
			LinkedList list1 = new LinkedList(); 
		    LinkedList list2 = new LinkedList();
		    
			//length of list k dependant on even/odd case 
		    if(n % 2 == 0){ 
			
				//even case, so both lists are the same length
				list2.n = n / 2; 
				
		    } else{
				
				//odd case, so k is longer by 1	
				list2.n = n / 2 + 1; 
		    }
			
			//ListNode used to traverse the linked list
			ListNode traverse = start; 
			
			//after this loop, traverse will point to the last node in list j (the first list)
			for(int i = 0; i < n / 2 - 1; i++){ 
				traverse = traverse.next;			 
			}
			
			//set length of list1
			list1.n = n / 2; 			
			
			//first node of list1 is same as start
			list1.start = start;  			
			
			//last node of list1 is same as traverse
			list1.rear = traverse;			
			
			//first node of list2 is after the last node of list1 (traverse)
			list2.start = traverse.next;	
			
			//last node of list2 is same as rear
			list2.rear = rear;				
			
			//set last.next to null
			list1.rear.next = null;
			
			//reverse the 2 new lists
		    list1.reverse(level++);			
			list2.reverse(level++);
			
			//join the lists together in reversed order	
			list2.rear.next = list1.start;	
			start = list2.start;
			rear = list1.rear;
			rear.next = null;
	    } 
		
		
   }
   
   //This method operates in-place and has the effect of adding one to the current integer value. 
   //You should not create any new list nodes except in the case that one more list node is required to 
   //represent the answer. For example, if 99 is incremented, the original list has two cells, each with 
   //a digit value of 9. After the increment, the data values are changed to 0 in the first two cells and 
   //a new cell with data value 1 is added to the end of the list. 
   public void plus_plus()
   {
	    //tracks the operation's completion
	    boolean done = false;  
	    
		//pointer to keep track of place values
		ListNode temp = start; 
		
		//loop will run until the opertation has completed
		while(!done){ 
			
			
			if(temp.data == 9){ 
				
				//if the number to be iterated is 9, it will be changed to 0,
				//and the loop will run again, this time iterating the next place value
			    temp.data = 0;  	

			    if(temp.next != null){  
					temp = temp.next;
					
			    }else {	
				
					//case where there is no next place value, so a new node must be added	
				    ListNode newNode = new ListNode(1, null);
				    temp.next = newNode;
				    rear = newNode;
					n++;
					done = true;
			    }
		    }else { 
			
				//default case where the value can be iterated normally
				temp.data++;
				done = true;
			}
	    }
		
		
		
    }
	
	//If x, y, and z are LinkedList objects representing big integers, then calling this routine like this: 
	//z = x.plus(y) 
	//sets z to be big integer which is the sum of the big integers stored in x and y.
   public LinkedList plus(LinkedList y)
   {
		//resulting list, z, will be the sum of lists x and y
		LinkedList z;
		z= new LinkedList();
		z.start = new ListNode(0, null);
		z.rear = z.start;		
		z.n = 1;
		
		//keeps track of wether the operation is complete or not
		boolean done = false;
		
		//pointers for lists x and y palce values
		ListNode tx = start;   
		ListNode ty = y.start;
		
		//loop runs until operation is complete
	    while(!done){ 
			
			//if the sum of x, y and z is greater than 9
			//effectively the same operation as carrying in long addition
			if(tx.data + ty.data + z.rear.data >= 10){
				
				//a new node is created with the 10s value of the previous sum
				z.rear.next = new ListNode((tx.data + ty.data + z.rear.data) / 10, null);
				
				//current node's value is the 1s value of the previous sum
				z.rear.data = ((tx.data + ty.data + z.rear.data) % 10);
				
				z.n++;
				
			} else {
				//case where the sum is less than 10
				z.rear.data = (tx.data + ty.data + z.rear.data);
			}
			
			//iterates x, y and z place values
			//if x and y both have next values
			if(tx.next != null && ty.next != null){
				tx = tx.next;
				ty = ty.next;
				
			//if x has no next value, set it to 0
			} else if(tx.next != null){
				tx = tx.next;
				ty = new ListNode(0, null);
				
			//same if y has no next value 
			} else if(ty.next != null){
				ty = ty.next;
				tx = new ListNode(0, null);
				
			//case where x and y have no next value, so the operation is complete
			} else {
				done = true;
			}
			
			//creates a new node in z if there was not one already made
			if(z.rear.next == null){
				z.rear.next = new ListNode(0, null);
				z.rear = z.rear.next;
				z.n++;
			} else {
				z.rear = z.rear.next;
			}
			
	    }

		return(z);
   }

// You can use these routines for this assignment:

// Tries to read in a non-negative integer from the input stream.
// If it succeeds, the integer read in is returned. 
// Otherwise the method returns -1.
   public static int readInteger(Scanner in)
   {
       int n;

       try{
           n= in.nextInt();
           if (n >=0) return(n);
           else return(-1);
       }
       catch(Exception e)
       {
//        We are assuming legal integer input values are >= zero.
          return(-1);
       }
   }

// Use this for debugging only.

   public void printList()
   {
       ListNode current;

       int count=0;

       current= start;

       while (current != null)
       {
           count++;
           
           System.out.println("Item " + count + " in the list is " 
                            + current.data);
           current= current.next;
       }
   }
}