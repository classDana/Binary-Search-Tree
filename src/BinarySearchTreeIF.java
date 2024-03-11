package assignment04;

import java.util.ArrayList;


public interface BinarySearchTreeIF {
	/**
	 * Returns the root of your binary search tree, or null if the tree is empty.
	 * 
	 * @return root of the binary search tree
	 */
	public BinaryTreeNode getRoot();
	
	/**
	 * Insert the element elem into the binary search tree.
 	 * Null-objects (key, elem) are not allowed, in this case an exception is thrown.
 	 * 
	 * @param key, elem
	 * @throws IllegalArgumentException if key or elem is null
	 */
	public void insert(Integer key, String elem) throws IllegalArgumentException;
	
	/**
	 * Returns the element of the node found with the given key, or null if key was not found.
	 * 
	 * @param key
	 * @return The corresponding element of the node with the given key, or null if key was not found.
	 * @throws IllegalArgumentException if key is null
	 */
	public String find(Integer key) throws IllegalArgumentException;
	
	/**
	 * Removes the node with the given key, and returns true if node was found AND removed, false otherwise.
	 * 
	 * @param key
	 * @return true if node was found and removed, false otherwise.
	 * @throws IllegalArgumentException if key is null.
	 */
	public boolean remove(Integer key) throws IllegalArgumentException;
	
	/**
	 * @return Returns the number of nodes stored in the tree.
	 */
	public int size();	
	
	/**
	 * @return Returns an array-representation of the stored elements (Postorder traversal).
	 */
	public Object[] toArrayPostOrder();
	
	/**
	 * @return Returns an array-representation of the stored elements (Inorder traversal).
	 */
	public Object[] toArrayInOrder();
	
	/**
	 * @return Returns an array-representation of the stored elements (Preorder traversal).
	 */
	public Object[] toArrayPreOrder();
	
	/**
	 * Search the parent node of the node with the given key,
     * and return its key (or null if not found). If the node with the given key is the root,
     * then return the key of the root.
     * 
	 * @param key
	 * @return key of parent
	 * @throws IllegalArgumentException if key is null
	 */
	public Integer getParent(Integer key) throws IllegalArgumentException;
	
	/**
	 * @param key
	 * @return true if the node with the given key is the root, false otherwise.
	 * @throws IllegalArgumentException if key is null
	 */
	public boolean isRoot(Integer key) throws IllegalArgumentException;
	
	/**
	 * @param key
	 * @return Return true if the node with the given key is an internal node, otherwise return false.
	 * @throws IllegalArgumentException key is null
	 */
	public boolean isInternal(Integer key) throws IllegalArgumentException;
	
	/**
	 * @param key
	 * @return Return true if the node with the given key is an external node, otherwise return false.
	 * @throws IllegalArgumentException if key is null
	 */
	public boolean isExternal(Integer key) throws IllegalArgumentException;
	
	/**
	 * This method verifies the given bst, if it is a correct Binary Search Tree.
	 * 
	 * @param bst represents a binary search tree
	 * @return true if the tree passed as a parameter is a valid Binary Search Tree, false otherwise. 
	 * @throws IllegalArgumentException if bst is null
	 */
	public boolean isBST(BinarySearchTreeIF bst) throws IllegalArgumentException;
	
	/**
	 * Search the node with the minimum key in the entire binary search tree and return its element.
	 * 
	 * @return the element of the minimum key
	 */
	public String returnMinKey();
	
	/**
	 * Creates a binary search tree based on the given (linear) list and then determines the number of 
	 * comparisons necessary to search a key in both, the internal BST (key comparison + comparison for branch decision) 
	 * and in the list (key comparison). This numbers shall be returned in the class Result.
	 * 
	 * @param list 
	 * @param key The key to be searched in list and BST.
	 * @return The number of comparisons needed (see description above) to find the given key in the BST and list in form 
     *         of the class Result. 
	 * @throws IllegalArgumentException if one of the parameter is null.
	 */
	public Result runtimeComparison(ArrayList<Integer> list, Integer key) throws IllegalArgumentException;
	
	/**
	 * Determines the depth of a node in the tree. 
	 * 
	 * @param key The key of the node of which the depth should be determined.
	 * @return The depth of the node with given key. If the key is not found return -1.
	 * @throws IllegalArgumentException if key is null.
	 */
	public int getDepth(Integer key) throws IllegalArgumentException;
	
	/**
	 * This method analyses the tree and determines if it is a complete binary tree.
	 * 
	 * @return True if the tree is complete, false otherwise. In case the tree is empty return false as well.
	 */
	public boolean isTreeComplete();
}
