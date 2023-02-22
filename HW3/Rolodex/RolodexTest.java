package rolodex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RolodexTest {

	@Test
	void test() {
		Rolodex r = new Rolodex();
		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");
		r.addCard("Cris", "5");
		
		//Testing addCard function
		Assertions.assertEquals(r.contains("Maddie"), false);
		r.addCard("Maddie", "23");
		Assertions.assertEquals(r.contains("Maddie"), true);

		
		//Testing out the contains function
		Assertions.assertEquals(r.contains("Chloe"),true);
		Assertions.assertEquals(r.contains("Chad"),true);
		Assertions.assertEquals(r.contains("Cris"),true);
		Assertions.assertEquals(r.contains("Maddie"),true);
		
		
		//Testing out the size function
		Assertions.assertEquals(r.size(),6);
		
		
		//Testing out the removeCard function
		r.removeCard("Chloe", "123");
		Assertions.assertEquals(r.contains("Chloe"),false);

		
		//Testing out the removeAllCards function, and lookup function
		//at the same time since removeAllCards uses lookup.
		r.removeAllCards("Cris");
		Assertions.assertEquals(r.contains("Cris"),false);
		
		//Testing out the initializeCursor function
		r.initializeCursor();
		Assertions.assertEquals(r.currentEntryToString(),"Separator A");
		
		//Testing out the nextEntry function
		r.nextEntry();
		Assertions.assertEquals(r.currentEntryToString(),"Separator B");

		//Testing out the nextSeparator
		r.nextSeparator();
		Assertions.assertEquals(r.currentEntryToString(),"Separator C");

		
		
		
		
		
	}

}
