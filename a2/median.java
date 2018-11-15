//David Wiebe
//V00875342



import java.util.Scanner;
public class median{
	static minHeap min;
	static maxHeap max;
	
	public median(){
		min = new minHeap();
		max = new maxHeap();
	}
	
	public static int calculateMedian(int x){
		
		//the root of max will always be the median
		//so it can be found in O(C) time
		
		//this also means that the sizes of min and max will be 
		//different assuming odd numbered input sizes
		
		if(min.size() == 0 && max.size() == 0){
			//min and max are both empty
			//add to max
			max.insert(x);
		} else if(x <= max.peek()){
			//if the number to be added is less than the median
			//store it in max because it will guarantee that
			//the median stays at the root of max
			max.insert(x);
		} else if(x > max.peek()){
			//if the number to be added is greater than the median
			//store it in min because it keeps all of the numbers
			//somewhat sorted where all numbers larger than the median
			//are in min and all numbers less than the median are in max
			min.insert(x);
		} 
		
		//after inserting, ensure that the size of max
		//is greater than the size of min by 1
		if(max.size() - min.size()  > 1){
			//max is larger than min by more than 1 so 
			//move the number at the root of max into min
			min.insert(max.removeMax());
		} else if(max.size() - min.size() < 1){
			//max is not larger than min by 1 so move
			//the number from the top of min into max
			max.insert(min.removeMin());
		}
		//because of how the numbers are inserted, when
		//a number is moved from one heap to another, it
		//will always end up at the top of that heap
		
		//the root of max is always the median
		return max.peek();
	}
	
	public static void main(String[] args){
		median m = new median();
		
		System.out.println("Enter a list of non negative integers. To end enter a negative integers.");
		Scanner s = new Scanner(System.in);
		int current = s.nextInt();

		while(current >=0){
			System.out.println("current median:" + m.calculateMedian(current));
			current = s.nextInt();
			if(current<0)break;
			m.calculateMedian(current);
			current = s.nextInt();
			
		}	
	}
}

class minHeap{
	private int[] heap;
	private int size;
	
	public minHeap(){
		//for the purposes of simplicity,
		//we will act like the array begins at index 1
		heap=new int[10000];
		size=0;
	}
	
	public boolean isEmpty(){
		return (size==0);
	}
	
	public int size(){
		return size;
	}
	
	public void insert(int x){
		
		//insert into the next unused spot in the array
		heap[size + 1] = x;
		
		//bubble up to maintain heap properties
		bubbleup(size + 1);
		
		size++;	
	}
	
	public void bubbleup(int k){
		
		//for any node at location k in the array,
		//it's parent is at location k/2
		if(heap[k] < heap[k / 2] && k > 1){
			//if the parent is greater than the current node
			//and the current node is not the root
			//swap the 2 nodes
			exchange(k, k / 2);
			
			//bubble up on the parent node
			bubbleup(k / 2);
		}
	}
	public void exchange(int i,int j){
		//swap 2 values locations in the array
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
		
	}
	public void bubbledown(int k){
		//since this code will swap numbers if the child is less than
		//the parent, we must make sure that meaningless 0s are swapped
		//so check using the size that the numbers have actual meaning
		if(size >= k * 2 + 1){
			//this is the case where both children have meaningful values
			if(heap[k] > heap[k * 2] || heap[k] > heap[k * 2 + 1]){
				//if both children are less than the parent
				//find the larger child and swap with the parent
				//then continue to bubble down
				if(heap[k * 2] < heap[k * 2 + 1]){
					exchange(k, k * 2);
					bubbledown(k * 2);
				} else{
					exchange(k, k * 2 + 1);
					bubbledown(k * 2 + 1);
				}
			}
		}else if(size >= k * 2){
			//this is the case where only the first child has a meaningful value
			if(heap[k] > heap[k * 2]){
				//if the child is less than the parent,
				//swap them and continue to bubble down
				exchange(k, k * 2);
				bubbledown(k * 2);
			}
		}
	}
	public int peek(){
		//return the number at the top of the heap
		//it has been decided that the heap will
		//start at index 1
		return heap[1];
	}
	
	public int removeMin(){
		//save the value to return it later
		int temp = heap[1];
		
		//change the value of the root to 0
		heap[1] = 0;
		//swap it with the last element
		exchange(1, size);
		//the size is now less by 1
		size--;
		//bubble down the root node to maintain heap properties
		bubbledown(1);
		//return the value that was at the root
		return temp;
	}
}

class maxHeap{
	private int[] heap;
	private int size;
	
	public maxHeap(){
		//for the purposes of simplicity,
		//we will act like the array begins at index 1
		heap=new int[10000];
		size=0;
	}
	
	public boolean isEmpty(){
		return (size==0);
	}
	
	public int size(){
		return size;
	}
	
	public void insert(int x){
		//insert to the next open spot in the array
		heap[size + 1] = x;
		//bubble up to maintain heap properties
		bubbleup(size + 1);
		
		size++;
	}
	
	public void bubbleup(int k){
		//for any node at location k in the array,
		//it's parent is at location k/2
		if(heap[k] > heap[k / 2] && k > 1){
			//if the parent node is less than the current node,
			//and the current node is not the root,
			//swap the two nodes and continue to bubble up
			exchange(k, k / 2);
			bubbleup(k / 2);
		}
	}
	public void exchange(int i,int j){
		//swap 2 values locations in an array
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
		
	}
	public void bubbledown(int k){
		//meaningless 0s need not be considered in this case
		//since 0 will never be greater than any meaningful values
		if(heap[k] < heap[k * 2] && heap[k] < heap[k * 2 + 1]){
			//if both children are greater than the parent,
			//find the larger child and swap it with the parent
			//then continue to bubble down
			if(heap[k * 2] >= heap[k * 2 + 1]){
				exchange(k, k * 2);
				bubbledown(k * 2);
			} else{
				exchange(k, k * 2 + 1);
				bubbledown(k * 2 + 1);
			}
		//when only one child is larger than the parent
		//immediately swap them and continue to bubble down
		}else if(heap[k] < heap[k * 2]){
			exchange(k, k * 2);
			bubbledown(k * 2);
		} else if(heap[k] < heap[k * 2 + 1]){
			exchange(k, k * 2 + 1);
			bubbledown(k * 2 + 1);
		}
	}
	public int peek(){
		//return the value at the root
		//previously decided to be at index 1
		return heap[1];
	}
	
	public int removeMax(){
		//save the value at the root to return later
		int temp = heap[1];
		//set the root to 0
		heap[1] = 0;
		//swap the root with the last value
		exchange(1, size);
		//bubble down to maintain heap properties
		bubbledown(1);
		//the size is now less by 1
		size--;
		//return the value that was at the root
		return temp;
	}
}