package com.jaj.project.radix;

//import java.util.Iterator;

public class RadixTree {

	private Node root;
	
	public RadixTree(){
		init();
	}
	
	private void init(){
		root = new Node();
		root.setParent(null);
	}
	
	public Node getRoot(){
		return root;
	}
	
	/** Adds a Node to the RadixTree. 
	 *  @return The added Node.
	 */
	public Node addNode(Node n, char data){
		Node returnNode = n.findChild(data);
		if(null == returnNode){
			return ( n.appendChild(data) );
		} else { return returnNode;	}
	}
	
	public void traverseInOrder(){
		
	}
	public void traversePreOrder(){
		
	}
	public void traversePostOrder(){
		
	}
	
	public void mergesort(){
		System.out.println("Sorting Radix Tree");
		long start = System.currentTimeMillis();
		mergesortRecursively(root);
		long end = System.currentTimeMillis();
		System.out.format( "Sorting Complete. Time Of Sort = %d",(end - start) );
		System.out.println();
	}
	
	/** For each node that has more than 2 children, call
	 *  mergesort recursively
	 */
	private void mergesortRecursively(Node node){
		if(node.numOfChildren() > 1){
			Node[] helper = new Node[Node.mostChildren];
			mergesort(node, helper, 0, node.numOfChildren()-1);
			for(int i = 0; i < node.numOfChildren(); i++){
				mergesortRecursively(node.getChild(i));
			}			
		}
	}
	
	private void mergesort(Node node, Node[] helper, int low, int high){
		if(low < high){
			//System.out.println(high);
			int middle = (low + high) / 2;
			mergesort(node, helper, low, middle);
			mergesort(node, helper, middle+1, high);
			merge(node, helper, low, middle, high);
		}
	}
	
	private void merge(Node node, Node[] helper, int low, int middle, int high){
		for(int i = low; low <= high; i++){
			System.out.println(high);
			helper[i] = node.getChild(i);
		}
		
		int iLeft = low;
		int iRight = middle + 1;
		int current = low;
		
		// iterate through helper array
		//compare the l and r half, copying back the smaller element from the two halves to the original
		
		while(iLeft <= middle && iRight <= high){
			if(helper[iLeft].getData() <= helper[iRight].getData()){
				node.setChild(helper[iLeft], current);
				iLeft++;
			} else {
				node.setChild(helper[iRight], current);
				iRight++;
			}
			current++;
		}
		
		int remaining = middle - iLeft;
		for(int i = 0; i < remaining; i++){
			node.setChild(helper[iLeft+1], current);
		}
		
	}
	
	public void searchBreadth(){
		
	}
	
	public void searchDepth(){
		
	}
	
	public void print(){
		System.out.println("Printing Radix Tree:");
		printRecurisely(root, new StringBuilder());
		System.out.println();
	}
	
	private void printRecurisely(Node node, StringBuilder word){
		if(node.numOfChildren() == 0 && word.toString().length() > 0){
			System.out.println(word.toString());
		}
		else{
			for(int i = 0; i < node.numOfChildren(); i++){
				StringBuilder strb = new StringBuilder(word);
				strb.append(node.getChild(i).getData());
				printRecurisely(node.getChild(i), strb);
			}
		}
	}
}

class Node{
	Node parent;
	Node child[];
	boolean visited;
	char data;
	int children;
	int sizeOfArray;

	final static private int INITIAL_SIZE = 7;
	static public int mostChildren = INITIAL_SIZE;
	
	/*  Node Constructors
	 */
	protected Node(){
		initChildren();
	}
	public Node(Node p, char c){
		setParent(p);
		setData(c);
		initChildren();
	}
	
	/*  Each nodes will have 1 parent. The
	 *  root node will have a null parent.
	 */
	public boolean setParent(Node p){
		parent = p;
		return true; 
	}
	public Node getParent(){
		return parent;
	}
	
	/*  Each child can have n children. For
	 *  LyricSort, the maximum number of 
	 *  children is set by INITIAL_SIZE.
	 */
	private void initChildren(){
		sizeOfArray = INITIAL_SIZE;
		children = 0;
		child = new Node[sizeOfArray];
		for(int i = 0; i < size(); i++){
			child[i] = null;
		}
	}
	private void expandChildArray(){
		sizeOfArray*=2;
		if(sizeOfArray > mostChildren){
			mostChildren = sizeOfArray;
		}
		Node[] newArray = new Node[sizeOfArray];
		for(int i = 0; i < size(); i++){
			if(i < numOfChildren()){
				newArray[i] = child[i];
			} else {
				newArray[i] = null;
			}
		}
		child = newArray;
	}	
	public boolean setChild(Node node, int offset){
		if(offset > -1 && offset < numOfChildren() && null == node){
			child[offset] = node;
			return true;
		} else { return false; }		
	}
	public Node getChild(int offset){
		if(offset > -1 && offset < numOfChildren()){
			return child[offset];
		} else { return null; }
	}
	public Node findChild(char data){
		if(numOfChildren() == 0){
			return null;
		} else {
			for(int i = 0; i < numOfChildren(); i++){
				if(child[i].getData() == data){
					return child[i];
				}
			}
		}
		return null;
	}
	/*  Appending Node n with char c to the current Node
	 */
	public Node appendChild(char c){
		if(numOfChildren() == size()){
			expandChildArray();
		}
		int offset = numOfChildren();
		child[offset] = new Node(this, c);
		//System.out.println("appendChild " + offset + " withData " + c);
		children++;
		return child[offset];
	}
	
	/*  For LyricSort, data will be single
	 *  lower-case alphabetic characters. This
	 *  could be generalized for any data type
	 */
	private boolean setData(char c){
		if(Character.isLetter(c)){
			data = c;
			return true;
		} else{ return false; }		
	}
	public char getData(){
		return data;
	}
	public int numOfChildren(){
		return children;
	}
	private int size(){
		return sizeOfArray;
	}
	public void resetVisits(){
		
	}

}