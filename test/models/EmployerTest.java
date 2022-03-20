package models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployerTest {

	@Test
	void testGetOwner_id() {
		Employer emp = new Employer();
		emp.setOwner_id(5411241);
		assertEquals(5411241,emp.getOwner_id());
	}

	@Test
	void testSetOwner_id() {
		Employer emp = new Employer();
		emp.setOwner_id(5411241);
		assertEquals(5411241,emp.getOwner_id());
	}

	@Test
	void testGetUsername() {
		Employer emp = new Employer();
		emp.setUsername("vw1525394vw");
		assertEquals("vw1525394vw",emp.getUsername());
	}

	@Test
	void testSetUsername() {
		Employer emp = new Employer();
		emp.setUsername("vw1525394vw");
		assertEquals("vw1525394vw",emp.getUsername());
	}

	@Test
	void testGetCountry() {
		Employer emp = new Employer();
		emp.setCountry("Vietnam");
		assertEquals("Vietnam",emp.getCountry());
	}

	@Test
	void testSetCountry() {
		Employer emp = new Employer();
		emp.setCountry("Vietnam");
		assertEquals("Vietnam",emp.getCountry());
	}

	@Test
	void testGetRole() {
		Employer emp = new Employer();
		emp.setRole("employer");
		assertEquals("employer",emp.getRole());
	}

	@Test
	void testSetRole() {
		Employer emp = new Employer();
		emp.setRole("employer");
		assertEquals("employer",emp.getRole());
	}

	@Test
	void testGetReg_date() {
		Employer emp = new Employer();
		emp.setReg_date(1114658966);
		assertEquals(1114658966,emp.getReg_date());
	}

	@Test
	void testSetReg_date() {
		Employer emp = new Employer();
		emp.setReg_date(1114658966);
		assertEquals(1114658966,emp.getReg_date());
	}

	@Test
	void testSetLimited_acc() {
		Employer emp = new Employer();
		emp.setLimited_acc("false");
		assertEquals("false",emp.getLimited_acc());
	}

	@Test
	void testGetLimited_acc() {
		Employer emp = new Employer();
		emp.setLimited_acc("false");
		assertEquals("false",emp.getLimited_acc());
	}

	@Test
	void testSetDisplay_name() {
		Employer emp = new Employer();
		emp.setDisplay_name("vw1525394vw");
		assertEquals("vw1525394vw",emp.getDisplay_name());
	}

	@Test
	void testGetDisplay_name() {
		Employer emp = new Employer();
		emp.setDisplay_name("vw1525394vw");
		assertEquals("vw1525394vw",emp.getDisplay_name());
	}

	@Test
	void testSetChosen_role() {
		Employer emp = new Employer();
		emp.setChosen_role("both");
		assertEquals("both",emp.getChosen_role());
	}

	@Test
	void testGetChosen_role() {
		Employer emp = new Employer();
		emp.setChosen_role("both");
		assertEquals("both",emp.getChosen_role());
	}

	@Test
	void testSetEmail_ver() {
		Employer emp = new Employer();
		emp.setEmail_ver("true");
		assertEquals("true",emp.getEmail_ver());
	}

	@Test
	void testGetEmail_ver() {
		Employer emp = new Employer();
		emp.setEmail_ver("true");
		assertEquals("true",emp.getEmail_ver());
	}

	@Test
	void testSetPri_curr_name() {
		Employer emp = new Employer();
		emp.setPri_curr_name("US Dollar");
		assertEquals("US Dollar",emp.getPri_curr_name());
	}

	@Test
	void testGetPri_curr_name() {
		Employer emp = new Employer();
		emp.setPri_curr_name("US Dollar");
		assertEquals("US Dollar",emp.getPri_curr_name());
	}

}
