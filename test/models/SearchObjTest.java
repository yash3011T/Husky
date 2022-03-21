package models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


public class SearchObjTest {
	
	@Test
	void testgetQuery() {
		SearchObj obj = new SearchObj();
		obj.setQuery("Python");
		assertEquals("Python",obj.getQuery());
	}


	@Test
	void testgetFlesch() {
		SearchObj obj = new SearchObj();
		obj.setFlesch(24.5);
		assertEquals(24.5,obj.getFlesch());
	}
	
	@Test
	void testgetFkgl() {
		SearchObj obj = new SearchObj();
		obj.setFkgl(4.5);
		assertEquals(4.5,obj.getFkgl());
	}

	@Test
	void testgetDisplat() {
		SearchObj obj = new SearchObj();
		ArrayList<Display> dis = new ArrayList<Display>();
		obj.setDisplay(dis);
		assertEquals(dis,obj.getDisplay());
	}

}
