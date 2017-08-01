package AVLTree;

import static org.junit.Assert.*;

import org.junit.Test;

public class AVLTreeTest {

	@Test
	public void test() {
		AVLTree tree = new AVLTree();
		String[] vals= new String[] {"www.nowtoshare.com","www.nowtoshare.edu","www.nowtoshare.com/index","www.nottoshare.com", "www.google.com","www.yahoo.com","www.helloworld.edu","http://www.nowtoshare.com/index/page/123"};
		
		for(int i = 0; i < vals.length; i++) {
			tree.insert(vals[i]);
		}
		
		assertTrue(tree.existUrl("www.google.com"));
		assertFalse(tree.existUrl("www.google1.com"));
		assertTrue(tree.existUrl("www.yahoo.com"));
	}

}
