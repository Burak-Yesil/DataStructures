import java.util.Random;
import java.util.Stack;


public class Treap <E extends Comparable<E>> {

	
	
	private static class Node <E>{
		public E data; 
		public int priority; 
		public Node <E> right;
		public Node <E> left;
			
		public Node(E data, int priority) {
			//Node Constructor
			if (data == null) throw new IllegalArgumentException("Node: data is Null");
			this.data = data;
			this.priority = priority;
			right = null;
			left = null;
		}
		
		public Node<E> rotateRight(){
			//Rotates Node to the Right	
			Node<E> newRoot = this.left;
			this.left = this.left.right;
			newRoot.right = this;
			return newRoot;
		 } 
		 
		public Node<E> rotateLeft(){
			//Rotates Node to the Left
			 Node<E> newRoot = this.right;
			 this.right = this.right.left;
			 newRoot.left = this;
			 return newRoot;
		 }
		 
		public String toString() {
			//Provides a string representation of each node in the form (data, priority)
			 StringBuilder s = new StringBuilder();
			 s.append("(" + "key = " + data + " , Priority = " + priority + ")");
			 return s.toString();
		 } 
	}
	
	
	//Variables
	private Random priorityGenerator;
	private Node <E> root;
	
	
	//Constructors
	 public Treap() {
		 root = null;
		 priorityGenerator = new Random();
	 }
	 
	 public Treap(long seed) {
		 root = null;
		 priorityGenerator = new Random(seed);
	 }
	 
	 
	 //Add functions
	 boolean add(E key) {
		 return this.add(key, priorityGenerator.nextInt());
	 }
	 
	 int addNum = 1;
	 
	 boolean add(E key, int priority) {
		 Stack<Node <E>> s = new Stack<>();
		 Node <E> current = root;
		 int iterations = 1;
		 boolean added = false;
		 
		 Node <E> temp = new Node(key, priority);
		 
		 //Checks if Treap is empty
		 if (root == null) {
			 root = temp;
			 return true;
		 }
		
	
		 while (true) { 
			 
			//If current is null we end the while loop
			if (current == null) break;
			
			// Gets compare to number
			int result = current.data.compareTo(key);
			
			//checks if key exists
			if (result == 0) { 
				return false;
			}
			
			if (iterations == 1) {
				s.push(current);
				iterations ++;
			} 
			
			
			//checks right option
			if (result < 0) {
				if (current.right != null) { 
					current = current.right;
					s.push(current);

						}
				else {
					current.right = temp;
					current = current.right;
					added = true;
					break;
				}
			}
		
			
			//checks left option
			if (result > 0) {
				if (current.left != null) { 
					current = current.left;
					s.push(current);
				}
				else {
					current.left = temp;
					current = current.left;
					added = true;
					break;
				}
				
			}	
		 }

		
		 
		 
		 //Reheaping starts here
		 while(true) {
	     if(s.empty()) break;
   
         Node<E> parent = s.pop();
         Node<E> grandparent;

         if (parent.priority > current.priority) break;
  
         if(s.empty()) {
             this.root = current;
         }
         
         if(current.equals(parent.left)) parent.rotateRight();
         else parent.rotateLeft();
         
         if(!s.empty()) {
             grandparent = s.peek();
             if(parent.equals(grandparent.left)) grandparent.left = current;
           
             else grandparent.right = current;
             
         }
		 }
		 
		 return added;
	 }
	 
	 
	 boolean delete(E key) {
		 
		 
		 
		 
		 
		 if (!find(key)) throw new IllegalArgumentException("delete: The Argument Does Not Exist");

		 Node<E> current = root;
		 
		 Stack<Node <E>> S = new  Stack<>();
		 

		 //Finds the location of KEY and stores it in current
		 while(current.data.compareTo(key) != 0) {
			 int result = current.data.compareTo(key);
			 
			 	//checks right option
				if (result < 0) {
					if (current.right != null) { 
						S.add(current);
						current = current.right;
							}
				}
				//checks left option
				if (result > 0) {
					if (current.left != null) { 
						S.add(current);
						current = current.left;
					}
				}
		 } 

		 
		 //deletes root node if it has no root nodes, completing the function
		 if (current == root && current.left == null && current.right == null) {
			 root = null;
			 return true;
		 }
		
		 Node<E> parent = null; 
		 if (!S.isEmpty()) parent = S.pop();
		 
		 //rotates and brings node to the bottom of the tree
		 while (current.right != null || current.left != null) {			 
			 //ROOT NODE
			 //The code below runs if the current node is a root with at least one child node.
			 if (current == root) {

				 //If right is null it rotates right
				 if (current.right == null) {
					 root = current.rotateRight();
					 parent = root;
					 continue;
				 }
				 
				 //If left is null it rotates left
				 else if (current.left == null) {
					 root = current.rotateLeft();
					 parent = root;
					 continue;
				 }
				 
				 //If both left and right are not null, It checks and rotates accordingly
				 else if (current.left != null && current.right != null) {
					 int leftOption = Integer.compare(current.priority, current.left.priority);				 
				 	 int rightOption = Integer.compare(current.priority, current.right.priority);
				 	 
				 	 //If the left has a higher priority it rotates right and makes the left the new root
				 	 if (leftOption < 0) {
				 		 root = current.rotateRight();
				 		 parent = root;
				 	 }
				 	 
				 	 //If the right has a higher priority it rotates left and makes the right the new root
				 	 if (rightOption < 0) {
				 		 root = current.rotateLeft();
				 		 parent = root;
				 	 }
				 
				 }
			 }
			 
			 //Not Root Node
			 

			 
			 
			
			 //The code below runs if the current node's right is null
			 if (current.right == null) {

				 //Decides on whether to added rotated sub tree  to the left or right of the parent.
				 
				 //If current is the right child of its parent, the rotated subtree is added to the right of the parent.
				 if (parent.right == current) {

					 parent.right = current.rotateRight();
					 parent = parent.right;
					 continue;
				 }
				 
				 //If current is the left child of its parent, the rotated subtree is added to the left of the parent.
				 else {

					 parent.left = current.rotateRight();
					 parent = parent.left;
					 continue;
				 }
			 }
			 
			 
			 
			 
			 else if (current.left == null) {
				 //Decides on whether to added rotated sub tree  to the left or right of the parent.
				 
				 //If current is the right child of its parent, the rotated subtree is added to the right of the parent.
				 if (parent.right == current) {

					 parent.right = current.rotateLeft();
					 parent = parent.right;
					 continue;
				 }
				 
				 //If current is the left child of its parent, the rotated subtree is added to the left of the parent.
				 else {
					 parent.left = current.rotateLeft();
					 parent = parent.left;
					 continue;
				 }
			 }
			 
			 
			 
			 
			 else if (current.left != null && current.right != null) {
				 
				 if (current.left.priority > current.right.priority) {
					 if (parent.right == current) {
						 parent.right = current.rotateRight();
						 parent = parent.right;
					 }
					 
					 else {
						 parent.left = current.rotateRight();
						 parent = parent.left;
					 }
				 }
				 
				 else {
					 if (parent.right == current) {
						 parent.right = current.rotateLeft();
						 parent = parent.right;
					 }
					 
					 else {
						 parent.left = current.rotateLeft();
						 parent = parent.left;
					 }
				 }
			 	 

			 	 

			 	 
			 	 
			 
			 }
			 
			 
		 }
		 
			 if(parent.left == current) parent.left = null;
			 if(parent.right == current) parent.right = null;

		 return true;
	 }
	 
	 
	 
	 
	 
	 
	 
	 private boolean find(Node<E> root, E key) {
		 Node<E> current = root;
		 boolean x = false;

		 while(true) {
			 
			 if (current == null) {
				 x = false;
				 break;
			 }
			 
			 
			 int result = current.data.compareTo(key);
			 	
			 	if (result == 0) {
					 x = true;		
					 break;
				}	
				
				if (result < 0) {
						current = current.right;
						continue;
					
				}
		
				//checks left option
				if (result > 0) {
						current = current.left;
				}
		} 
		 

		return x;
	 }
	 
	 
	 
	 public boolean find(E key) {
		 return find(this.root, key);
	 }
	 
	 
	 private String toString(Node<E> current, int level) {
			StringBuilder s = new StringBuilder();
			for (int i=0; i<level;i++) {
				s.append("   ");
			}
			if (current==null) {
				s.append("null\n");
			} else {
				s.append(current.toString()+"\n");
				s.append(toString(current.left, level+1));
				s.append(toString(current.right,level+1));
			}
			return s.toString();
			
		}
	 
	 public String toString() {
		 return toString(root,0);
	 }
	 
	 public static void main (String [] args) {
		 Treap testTree = new Treap <Integer>();
		 testTree.add(4,19); 
		 testTree.add(2,31);
		 testTree.add(6,70); 
		 testTree.add(1,84);
		 testTree.add(3,12); 
		 testTree.add(5,83);
		 testTree.add(7,26);
		 
		 
		 System.out.println(testTree);
		 



		 
	 }
	 
}
