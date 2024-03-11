package assignment04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;
//import org.junit.jupiter.api.Test;

public class TestAssignment04Student {
	

	MyBinarySearchTree tree;

	@Test
	public void testFind() {
		tree = new MyBinarySearchTree();
		assertEquals(0, tree.size());
		tree.insert(5, "5"); 	assertEquals(1, tree.size());
		tree.insert(18, "18"); 	assertEquals(2, tree.size());
		tree.insert(1, "1"); 	assertEquals(3, tree.size());
		tree.insert(8, "8"); 	assertEquals(4, tree.size());
		tree.insert(14, "14");	assertEquals(5, tree.size());
		tree.insert(16, "16"); 	assertEquals(6, tree.size());
		tree.insert(13, "13"); 	assertEquals(7, tree.size());
		tree.insert(3, "3"); 	assertEquals(8, tree.size());
		
		assertEquals("5", tree.find(Integer.valueOf(5)));
		assertEquals("18", tree.find(Integer.valueOf(18)));
		assertEquals("1", tree.find(Integer.valueOf(1)));
		assertEquals("8", tree.find(Integer.valueOf(8)));
		assertEquals("14", tree.find(Integer.valueOf(14)));
		assertEquals("16", tree.find(Integer.valueOf(16)));
		assertEquals("13", tree.find(Integer.valueOf(13)));
		assertEquals("3", tree.find(Integer.valueOf(3)));
		assertEquals(null, tree.find(Integer.valueOf(2)));
	}
	
	@Test
	public void testRemove() {
		tree = new MyBinarySearchTree();
		assertEquals(0, tree.size());
		tree.insert(5, "5"); assertEquals(1, tree.size());
		tree.insert(18, "18"); assertEquals(2, tree.size());
		tree.insert(1, "1"); assertEquals(3, tree.size());
		tree.insert(8, "8"); assertEquals(4, tree.size());
		tree.insert(14, "14"); assertEquals(5, tree.size());
		tree.insert(16, "16"); assertEquals(6, tree.size());
		tree.insert(13, "13"); assertEquals(7, tree.size());
		tree.insert(3, "3"); assertEquals(8, tree.size());
		
		tree.remove(5);
		assertEquals(tree.getRoot().elem, tree.toArrayPreOrder()[0]);
		assertEquals(tree.isInternal(14), true);
		assertEquals(tree.isInternal(16), false);
		assertEquals(tree.isInternal(13), false);
		tree.remove(13);
		tree.remove(16);
		assertEquals(tree.isInternal(14), false);
		assertEquals(tree.isInternal(18), true);
		tree.remove(18);
		assertEquals(tree.getRoot().right.elem, "14");
				
		try {
			tree.remove(null);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		assertEquals(4, tree.size());
	}
	
	@Test
	public void testGetParent() {
		tree = new MyBinarySearchTree();
		tree.insert(10, "10");
		tree.insert(5, "5");
		tree.insert(12, "12");
		tree.insert(3, "3");
		
		assertEquals(Integer.valueOf(10), tree.getParent(5));
		assertEquals(Integer.valueOf(10), tree.getParent(12));
		assertEquals(Integer.valueOf(5), tree.getParent(3));
		
		// test root
		assertEquals(Integer.valueOf(10), tree.getParent(10));
		
		// test not existing node
		assertEquals(null, tree.getParent(20));
	}
	
	@Test
	public void testIsRoot() {
		tree = new MyBinarySearchTree();
		tree.insert(5, "5"); 	assertEquals(1, tree.size());
		tree.insert(18, "18"); 	assertEquals(2, tree.size());
		tree.insert(1, "1"); 	assertEquals(3, tree.size());
			
		assertTrue(tree.isRoot(Integer.valueOf(5)));
		assertFalse(tree.isRoot(Integer.valueOf(18)));
	}
	
	@Test
	public void testReturnMinKey() {
		tree = new MyBinarySearchTree();
		
		
		tree.insert(13, "");
		tree.insert(5, "5");
		tree.insert(18, "18"); 
		tree.insert(1, "1"); 
		tree.insert(8, "8"); 
		tree.insert(14, "14");
		tree.insert(16, "16");
		tree.insert(13, "13");
		tree.insert(3, "3"); 
		
		String ret = tree.returnMinKey();
		assertEquals("1", ret);
	}
	
	@Test
	public void testIsInternal() {
		tree = new MyBinarySearchTree();
		assertEquals(0, tree.size());
		tree.insert(5, "5"); assertEquals(1, tree.size());
		tree.insert(18, "18"); assertEquals(2, tree.size());
		tree.insert(1, "1"); assertEquals(3, tree.size());
		tree.insert(8, "8"); assertEquals(4, tree.size());
		tree.insert(14, "14"); assertEquals(5, tree.size());
		tree.insert(16, "16"); assertEquals(6, tree.size());
		tree.insert(13, "13"); assertEquals(7, tree.size());
		tree.insert(3, "3"); assertEquals(8, tree.size());
		
		assertTrue(tree.isInternal(Integer.valueOf(5)));
		assertTrue(tree.isInternal(Integer.valueOf(18)));
		assertTrue(tree.isInternal(Integer.valueOf(1)));
		// ...
		assertFalse(tree.isInternal(Integer.valueOf(3)));
		// ...
	}
	
	@Test
	public void testRuntimeComparison() {
		ArrayList<Integer> testList = new ArrayList<Integer>();
		testList.add(8);
		testList.add(1);
		
		// start with an empty tree as runtimeComparison() shall create a binary search tree based on the given list.
		tree = new MyBinarySearchTree();
		
		int key = 3;	// key is not in the list
		Exception caughtException = null;
		try {
			Result result = tree.runtimeComparison(testList, key);
			assertNotNull(result);
			assertEquals("runtimeComparison() returns wrong number of comparisons for searching key '3' in a list based on the sequence: 8,1", 2, result.getListNumOfComparisons());	// check list
			assertEquals("runtimeComparison() returns wrong number of comparisons for searching key '3' in a BST based on the sequence: 8,1", 4, result.getBstNumOfComparisons());	// check BST
		}catch(Exception ex) {
			caughtException = ex;
		}
		assertTrue("runtimeComparison() for correct given list+key caused "+caughtException,caughtException == null);
	}
}