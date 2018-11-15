/*
	David Wiebe
	V00875342
	Jan 21, 2018
	
	Stock.java
*/
import java.util.Scanner;
import java.io.File;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class Stock{

	public static int[] CalculateSpan(int[] p){
		
		int[] s = new int[p.length];
		//array to contain the spans of prices with the same index
		
		Stack<Integer> st = new Stack<Integer>();
		//stack to hold indexes
		
		for(int x = 0; x < p.length; x++){
			//iterate through the array from start to end
			
			s[x] = 1; //the minimum value for any span is 1
			
			while(!st.isEmpty() && p[x] >= p[st.peek()]){
				//while the stack is not empty and the current price
				//is greater than the previous price
				
				s[x] = s[x] + s[st.pop()];
				//the span of the current price is greater than the span
				//of all prices smaller than it
				
			}
			
			st.push(x);
			//the index is pushed onto the stack so the next price can be compared
		}
		
		return s;
	}
	public static int[] readInput(Scanner s){
		Queue<Integer> q = new LinkedList<Integer>();
		int n=0;
		if(!s.hasNextInt()){
			return null;
		}
		int temp = s.nextInt();
		while(temp>=0){
			q.offer(temp);
			temp = s.nextInt();
			n++;
		}
		int[] inp = new int[q.size()];
		for(int i=0;i<n;i++){
			inp[i]= q.poll();
		}
		return inp;
	}
	public static void main(String[] args){
		Scanner s;
        Stock m = new Stock();
        if (args.length > 0){
        	try{
        		s = new Scanner(new File(args[0]));
        	} catch(java.io.FileNotFoundException e){
        		System.out.printf("Unable to open %s\n",args[0]);
        		return;
        	}
        	System.out.printf("Reading input values from %s.\n", args[0]);
        }else{
        	s = new Scanner(System.in);
        	System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
        }
            
        int[] price = m.readInput(s);
        System.out.println("The stock prices are:");
        for(int i=0;i<price.length;i++){
        	System.out.print(price[i]+ (((i+1)==price.length)? ".": ", "));
        }

        if(price!=null){
        	int[] span = m.CalculateSpan(price);
        	if(span!=null){
        		System.out.println("The spans are:");
        		for(int i=0;i<span.length;i++){
        			System.out.print(span[i]+ (((i+1)==span.length)? ".": ", "));
        		}
        	}
        }
    }
}
