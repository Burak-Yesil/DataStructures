import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TreapTest {

	@Test
	void test() {
		 Treap<Integer> testTree = new Treap <Integer>();
		 testTree.add(4,19); 
		 testTree.add(2,31);
		 testTree.add(6,70); 
		 testTree.add(1,84);
		 testTree.add(3,12); 
		 testTree.add(5,83);
		 testTree.add(7,26);
		 
		 Assertions.assertEquals(testTree.find(4),true);
		 Assertions.assertEquals(testTree.find(2),true);
		 Assertions.assertEquals(testTree.find(6),true);
		 Assertions.assertEquals(testTree.find(1),true);
		 Assertions.assertEquals(testTree.find(3),true);
		 Assertions.assertEquals(testTree.find(5),true);
		 Assertions.assertEquals(testTree.find(7),true);

		 testTree.delete(4);
		 Assertions.assertEquals(testTree.find(4),false);
//		 
		 testTree.delete(2);
		 Assertions.assertEquals(testTree.find(2),false);
//		 
		 testTree.delete(6);
		 Assertions.assertEquals(testTree.find(6),false);
		 
		 testTree.delete(1);
		 Assertions.assertEquals(testTree.find(1),false);
//		 
		 testTree.delete(3);
		 Assertions.assertEquals(testTree.find(3),false);
//		 
		 testTree.delete(5);
		 Assertions.assertEquals(testTree.find(5),false);
//		 
		 testTree.delete(7);
		 Assertions.assertEquals(testTree.find(7),false);
	}

}
