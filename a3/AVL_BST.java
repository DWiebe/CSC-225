//David Wiebe
//V00875342

public class AVL_BST{
	
	public static void main(String[] args){
		
		int[] A = {78};
		//int[] A = {10, 5, 15, 2, 7, 12, 20, 1, 3, 6, 8, 11, 13, 16, 21};
		BST b = createBST(A);
		System.out.println(checkAVL(b));
	}
	
	
	
	
	
	
	public static boolean checkAVL(BST b){
		//since the BST operations maintain sorted order,
		//the only thing to check is the height property
		
		boolean result = true;
		
		
		if(b.left() != null && b.right() != null){
			System.out.println("left: " + b.left().value() + ", " + b.left().height() + ", " + b.left().size() + " right: " + b.right().value() + ", " + b.right().height() + ", " + b.right().size());
		}
		
		
		//recursively check left and right subtrees
		if(b.left() != null){
			result = checkAVL(b.left());
			
		}else if(b.right() != null){
			result = checkAVL(b.right());
			
		}
		if(!result){
			return result;
		}
		//check the current tree
		if(b.left() == null && b.right() == null){
			result = true;
		} else if(b.left() != null && b.right() != null){
			if(b.left().height() - b.right().height() > 1 || b.left().height() - b.right().height() < -1){
				result = false;
			}
		} else if(b.left() != null){
			if(b.left().height() > 1){
				result = false;
			}
		} else if(b.right() != null){
			if(b.right().height() > 1){
				result = false;
			}
		}
		
		return result;
	}
	
	public static BST createBST(int[] a){
		
		//insert each element of the array into a BST
		BST result = new BST();
		for(int x = 0; x < a.length; x++){
			result.insert(a[x]);
		}
		
		return result;
	}
	
	
	
}

class BST{
	
	private int value;
	private int size;
	private BST left;
	private BST right;
	private int height;
	
	public BST(int x){
		value = x;
		size = 1;
	}
	
	public BST(){
		
	}
	
	public int insert(int x){
		
		//returns the height of the subtree
		//so the parent can update its height if necessary
		
		int temp = 0;
		
		if(size == 0){
			//if there are no nodes in the tree
			value = x;
			
		}else if(x >= value && right != null){
			temp = right.insert(x);
			if(height < temp + 1){
				height = temp + 1;
			}
		
		} else if(x < value && left != null){
			temp = left.insert(x);
			if(height < temp + 1){
				height = temp + 1;
			}
		
		} else if(x >= value){
			right = new BST(x);
			if(left == null){
				height++;
			}
			
		} else if(x < value){
			left = new BST(x);
			if(right == null){
				height++;
			}
			
			
		}
		size++;
		return height;
	}
	
	public int value(){
		return value;
	}
	
	public BST right(){
		return right;
	}
	
	public BST left(){
		return left;
	}
	
	public int height(){
		return height;
	}
	
	public int size(){
		return size;
	}
	
}

