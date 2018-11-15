//David Wiebe
//V00875342


import java.util.*;
import java.io.*;
public class LinkedList
{
   int n;
   ListNode start;
   ListNode rear;

// You can set debug=true when you are debugging and want
// to print out what your program is doing.
// Please leave your debugging messages in your code when
// you submit it. Change debug back to false before
// submitting your code.
   static final boolean debug= false;

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
   public void reverse(int level)
   {
       // Insert your code from assignment #1 here
       //  for credit on question 1 of assignment #2.
	   
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

/*
    This method returns:
    -1 if the bigInteger associated with the method is less than y
     0 if the bigInteger associated with the method is equal to y
    +1 if the bigInteger associated with the method is greater than y
*/
   public int compare(LinkedList y)
   {
		// Insert your code for question 2 here.
	   
	   
		//keeps track of the result as the program traverses the bigIntegers
		int result = 0;
		//keeps track of the x and y place values (x being the list associated with the method)
		ListNode tx = start;
		ListNode ty = y.start;
	   
		//loop will run until a value is returned
		while(true){
		   
			if(tx.data > ty.data){
			   
				//case where x is greater
				result = 1;
			   
			} else if(tx.data < ty.data){
			   
				//case where y is greater
				result = -1;
			   
			} 
			   
			   
		    if(tx.next != null && ty.next != null){
				
				//both lists have a next node so the traversal continues normally
				tx = tx.next;
				ty = ty.next;
			   
		    } else if(tx.next == null && ty.next == null){
			   
				//both lists have no more nodes, so return the result
				return result;
		   
		    } else if(tx.next != null){
				//only list x has a next node
			   
				//traverse the rest of list x 
				while(tx.next != null){
					
					//move to the next node in list x
					tx = tx.next;
				    
					//if at least one node is greater than 0, x is greater than y
				    if(tx.data > 0){
						result = 1;
						return result;
				    }
				   
				    
			    }
				
				return result;
				
		    } else if(ty.next != null){
			    //only list y has a next node
			   
			    //traverse the rest of list y 
			    while(ty.next != null){
					
					//move to the next node in list y
					ty = ty.next;
					
					//if at least one node is greater than 0, y is greater than x
				    if(ty.data > 0){
					   result = -1;
					   return result;
					}					   
				    
			    }
				return result;
		    }
		   
		   
		}

       
	}

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

// You can use this in order to get nicer output
// (lined up in columns).

   public void printBigInteger(int nDigit)
   {
        boolean leadingZero;
        ListNode current;
        int i;
        int n_extra;

        reverse(0);
        leadingZero= true;
        if (n < nDigit)
        {
            for (i=n; i < nDigit; i++)
                System.out.print(" ");
        }
        n_extra= n- nDigit;
        current= start;
        while (current != null)
        {
            if (leadingZero)
            {
                if (current.data != 0)
                   leadingZero= false;
            }
            if (leadingZero)
            {
               if (current.next == null) // value is 0.
                   System.out.print(current.data);
               else if (n_extra <= 0)
                   System.out.print(" ");
            }
            else
            {
                System.out.print(current.data);
            }
            n_extra--;
            current= current.next;
        }
        reverse(0);
   }
} 
