package models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DisplayTest {

	@Test
	void testGetOwner_id() {
		Display dis = new Display();
		dis.setOwner_id(61329632);
		assertEquals(61329632,dis.getOwner_id());
	}

	@Test
	void testSetOwner_id() {
		Display dis = new Display();
		dis.setOwner_id(61329632);
		assertEquals(61329632,dis.getOwner_id());
	}

	@Test
	void testGetTitle() {
		Display dis = new Display();
		dis.setTitle("English to Spanish translation");
		assertEquals("English to Spanish translation",dis.getTitle());
	}

	@Test
	void testSetTitle() {
		Display dis = new Display();
		dis.setTitle("English to Spanish translation");
		assertEquals("English to Spanish translation",dis.getTitle());
	}

	@Test
	void testGetSkill_id() {
		Display dis = new Display();
		dis.setSkill_id(22);
		assertEquals(22,dis.getSkill_id());
	}

	@Test
	void testSetSkill_id() {
		Display dis = new Display();
		dis.setSkill_id(22);
		assertEquals(22,dis.getSkill_id());
	}

	@Test
	void testGetTime_submitted() {
		Display dis = new Display();
		dis.setTime_submitted(1647804899);
		assertEquals(1647804899,dis.getTime_submitted());
	}

	@Test
	void testSetTime_submitted() {
		Display dis = new Display();
		dis.setTime_submitted(1647804899);
		assertEquals(1647804899,dis.getTime_submitted());
	}

	@Test
	void testGetType() {
		Display dis = new Display();
		dis.setType("fixed");
		assertEquals("fixed",dis.getType());
	}

	@Test
	void testSetType() {
		Display dis = new Display();
		dis.setType("fixed");
		assertEquals("fixed",dis.getType());
	}

	@Test
	void testGetSkills() {
		Display dis = new Display();
		dis.setSkills("Translation");
		assertEquals("Translation",dis.getSkills());
	}

	@Test
	void testSetSkills() {
		Display dis = new Display();
		dis.setSkills("Translation");
		assertEquals("Translation",dis.getSkills());
	}

	@Test
	void testGetDescription() {
		Display dis = new Display();
		dis.setDescription("Our share is looking for a freelancer to take on a writing and translation project from English to S");
		assertEquals("Our share is looking for a freelancer to take on a writing and translation project from English to S",dis.getDescription());
	}

	@Test
	void testSetDescription() {
		Display dis = new Display();
		dis.setDescription("Our share is looking for a freelancer to take on a writing and translation project from English to S");
		assertEquals("Our share is looking for a freelancer to take on a writing and translation project from English to S",dis.getDescription());
	}

}
