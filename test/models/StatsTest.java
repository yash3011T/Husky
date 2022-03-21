package models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StatsTest {

	@Test
	void testGetWord() {
		Stats st = new Stats();
		st.setWord("the");
		assertEquals("the", st.getWord());
	}

	@Test
	void testSetWord() {
		Stats st = new Stats();
		st.setWord("the");
		assertEquals("the", st.getWord());
	}

	@Test
	void testGetCount() {
		Stats st = new Stats();
		st.setCount((long) 21);
		assertEquals((long)21, st.getCount());
	}

	@Test
	void testSetCount() {
		Stats st = new Stats();
		st.setCount((long) 21);
		assertEquals((long)21, st.getCount());
	}

}
