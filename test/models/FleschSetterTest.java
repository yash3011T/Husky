package models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FleschSetterTest {

	@Test
	void testGetFleshReadability() {
		FleschSetter fs = new FleschSetter();
		fs.setFleshReadability(81.0);
		assertEquals(81.0,fs.getFleshReadability());
	}

	@Test
	void testSetFleshReadability() {
		FleschSetter fs = new FleschSetter();
		fs.setFleshReadability(81.0);
		assertEquals(81.0,fs.getFleshReadability());
	}

	@Test
	void testGetFleschKincade() {
		FleschSetter fs = new FleschSetter();
		fs.setFleschKincade(10.0);
		assertEquals(10.0,fs.getFleschKincade());
	}

	@Test
	void testSetFleschKincade() {
		FleschSetter fs = new FleschSetter();
		fs.setFleschKincade(10.0);
		assertEquals(10.0,fs.getFleschKincade());
	}

	@Test
	void testGetTitle() {
		FleschSetter fs = new FleschSetter();
		fs.setTitle("English to Spanish translation");
		assertEquals("English to Spanish translation",fs.getTitle());
	}

	@Test
	void testSetTitle() {
		FleschSetter fs = new FleschSetter();
		fs.setTitle("English to Spanish translation");
		assertEquals("English to Spanish translation",fs.getTitle());
	}

}
