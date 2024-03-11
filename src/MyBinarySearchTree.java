package assignment04 ;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MyBinarySearchTree implements BinarySearchTreeIF { 
	public int numOfComparisons = 0;
	/*
		A list for inserting the values in order-methods
		Its easier to use List because of the add-method
	 */
	private final List values = new ArrayList();
	/**
	 * Root of the binary search tree.
	 */
	protected BinaryTreeNode treeRoot;
	
	/**
	 * Number of elements stored in the binary search tree.
	 */
	protected int treeSize;
	
	/**
	 * Initialization of the phone book based on a BST.
	 */
	public MyBinarySearchTree() {
		treeRoot = null;
		treeSize = 0;
	}
	
	// This constructor is used for corrections - DO NOT CHANGE IT!
	public MyBinarySearchTree(BinaryTreeNode root, int size) {
		treeRoot = root;
		treeSize = size;
	}
	
		
	@Override
	public BinaryTreeNode getRoot() {
		return this.treeRoot;
	}
	
	@Override
	public void insert(Integer key, String elem) throws IllegalArgumentException{
		if (key == null || elem == null) throw new IllegalArgumentException();
		
		BinaryTreeNode newNode = new BinaryTreeNode(key,elem);
		if (!insert(treeRoot, newNode)) {
			treeRoot = newNode;
			treeSize++;
		}
		
	}

	@Override
	public String find(Integer key) throws IllegalArgumentException {
		BinaryTreeNode p = treeRoot;
		while(p != null){
			if(key.equals(p.key)){
				return p.key.toString();
			}else if(key < p.key){
				p = p.left;
				numOfComparisons++;
			}else{
				p = p.right;
				numOfComparisons++;
			}
			numOfComparisons++;
		}
		return null;
	}


	@Override
	public boolean remove(Integer key) throws IllegalArgumentException {
		BinaryTreeNode current = treeRoot;
		BinaryTreeNode parent = treeRoot;
		boolean isLeftChild = false;

		if(key == null){
			throw new IllegalArgumentException();
		}

		while(!current.key.equals(key)){
			parent = current;
			if(key < current.key){
				current = current.left;
				isLeftChild = true;
			}
			else{
				current = current.right;
				isLeftChild = false;
			}
			if(current == null){
				return false;
			}
		}
		//current.key == key
		//Case 1:
		//node without any children
		if(current.left == null && current.right == null){
			// if root node is to be deleted
			if(current == treeRoot){
				treeRoot = null;
			}else if(isLeftChild){
				parent.left = null;
			}else{
				parent.right = null;
			}

		}
		// Case 2:
		// Node with one child
		//right child
		else if(current.left == null){
			if(current == treeRoot){
				treeRoot = current.right;
			}else if(isLeftChild){
				parent.left = current.right;
			}else{
				parent.right = current.right;
			}
		}
		//left child
		else if(current.right == null){
			if(current == treeRoot){
				treeRoot = current.left;
			}else if(isLeftChild){
				parent.left = current.left;
			}else{
				parent.right = current.left;
			}
		}
		//Case 3:
		//Node with two children
		else{
			// find in-order successor of the node to be deleted
			BinaryTreeNode successor = findSuccessor(current);
			if(current == treeRoot){
				treeRoot = successor;
			}else if(isLeftChild){
				parent.left = successor;
			}else{
				parent.right = successor;
			}
			successor.left = current.left;
		}
		treeSize--;
		return true;
	}

	@Override
	public int size() {
		return treeSize;
	}

	@Override
	public Object[] toArrayPostOrder() {
		BinaryTreeNode p = treeRoot;
		Object[] postOrder = new Object[treeSize];
		values.clear();
		postOrder(p);

		for(int i = 0; i < values.size(); i++){
			postOrder[i] = values.get(i).toString();
		}
		return postOrder;
	}

	@Override
	public Object[] toArrayInOrder() {
		BinaryTreeNode p = treeRoot;
		Object[] inOrder = new Object[treeSize];
		values.clear();
		inOrder(p);

		for(int i = 0; i < values.size(); i++){
			inOrder[i] = values.get(i).toString();
		}
		return inOrder;
	}

	@Override
	public Object[] toArrayPreOrder() {
		BinaryTreeNode p = treeRoot;
		Object[] preOrder = new Object[treeSize];
		values.clear();
		preOrder(p);

		for(int i = 0; i < values.size(); i++){
			preOrder[i] = values.get(i).toString();
		}
		return preOrder;
	}

	@Override
	public Integer getParent(Integer key) throws IllegalArgumentException {
		BinaryTreeNode p = treeRoot;
		Integer parent = null;

		//if the first key is the parent key
		if(key.equals(p.key)){
			return p.key;
		}
		//search for parent key
		while(p != null){
			if (key.equals(p.key)) {
				return parent;
			} else if (key < p.key) {
				parent = p.key;
				p = p.left;
			} else {
				parent = p.key;
				p = p.right;
			}
		}
		return null;
	}

	@Override
	public boolean isRoot(Integer key) throws IllegalArgumentException {
		return key.equals(treeRoot.key);
	}

	@Override
	public boolean isInternal(Integer key) throws IllegalArgumentException {
		BinaryTreeNode p = treeRoot;
		while(p != null) {
			if (key.equals(p.key)) {
				//at least one child
				return p.left != null || p.right != null;
			} else if (key < p.key) {
				p = p.left;
			} else {
				p = p.right;
			}
		}
		return false;
	}

	@Override
	public boolean isExternal(Integer key) throws IllegalArgumentException {
		BinaryTreeNode p = treeRoot;
		while(p != null) {
			if (key.equals(p.key)) {
				//no children
				return p.left == null && p.right == null;
			} else if (key < p.key) {
				p = p.left;
			} else {
				p = p.right;
			}
		}
		return false;
	}
	
		
	@Override
	public Result runtimeComparison(ArrayList<Integer> list, Integer key) throws IllegalArgumentException{
		numOfComparisons = 0;
		int listNumOfComparison = 0;

		//BST
		//insert elements in binary tree
		for(int i = 0; i < list.size(); i++){
			insert(i, String.valueOf(i));
		}
		find(3);

		//List
		for (Integer integer : list) {
			if (integer.equals(key)) {
				break;
			}
			listNumOfComparison++;
		}
		return new Result(numOfComparisons, listNumOfComparison);
	}

	
	public boolean isBST(BinarySearchTreeIF bst) throws IllegalArgumentException{
		BinaryTreeNode node = bst.getRoot();
		return isBST(node,null ,null);
	}
	
	public String returnMinKey() {
		BinaryTreeNode p = treeRoot;
		if(p == null){
			return null;
		}
		while(p.left != null){
			p = p.left;
		}
		return p.key.toString();
	}
	
	public int getDepth(Integer key) throws IllegalArgumentException {
		int depth = 0;
		BinaryTreeNode p = treeRoot;

		while(p != null) {
			if (key.equals(p.key)) {
				return depth;
			} else if (key < p.key) {
				p = p.left;
				depth++;
			} else {
				p = p.right;
				depth++;
			}
		}
		return 0;
	}
	
	public boolean isTreeComplete() {
		BinaryTreeNode p = treeRoot;
		return isTreeComplete(p, 0, treeSize);
	}
	
	//------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------
	//----- PRIVATE METHODS --------------------------------------------------------------------
	//------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------
	
	private boolean insert(BinaryTreeNode node, BinaryTreeNode x) {
		while (node != null) {
			int compare = node.key.compareTo(x.key);
			if (compare == 0) { 
				return true;
			} else if (compare > 0) {
				if (node.left == null) {
					node.left = x;
					x.parent = node;
					treeSize++;
					return true; 
				} else
					node = node.left;
			} else {
				if (node.right == null) {
					node.right = x;
					x.parent = node;
					treeSize++;
					return true;
				} else
					node = node.right;
			}
		}
		return false;
	}

	private boolean isTreeComplete(BinaryTreeNode node, int index, int size){
		if(node == null){
			return true;
		}
		if(index >= size){
			return false;
		}
		return (isTreeComplete(node.left, 2*index+1, size)
				&& isTreeComplete(node.right, 2*index+2, size));
	}

	private boolean isBST(BinaryTreeNode node, Integer min, Integer max){
		if(node == null){
			return true;
		}
		if(min != null && node.key.compareTo(min) <= 0){
			return false;
		}else if(max != null && node.key.compareTo(max) >= 0){
			return false;
		}
		return isBST(node.left, min, node.key) &&isBST(node.right, node.key, max);
	}

	private void preOrder(BinaryTreeNode node){
		if(node == null){
			return;
		}
		values.add(node.key);
		preOrder(node.left);
		preOrder(node.right);
	}

	private void inOrder(BinaryTreeNode node){
		if(node == null){
			return;
		}
		inOrder(node.left);
		inOrder(node.right);
		values.add(node.key);
	}

	private void postOrder(BinaryTreeNode node){
		if(node == null){
			return;
		}
		postOrder(node.left);
		values.add(node.key);
		postOrder(node.right);
	}

	private BinaryTreeNode findSuccessor(BinaryTreeNode node){
		BinaryTreeNode successor = node;
		BinaryTreeNode parent = node;
		BinaryTreeNode current = node.right;

		while(current != null){
			parent = successor;
			successor = current;
			current = current.left;
		}

		if(successor != node.right){
			parent.left = successor.right;
			successor.right = node.right;
		}
		return successor;
	}
}