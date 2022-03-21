package views;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import models.SearchObj;

class indexTest {

	@Test
	public void renderTemplate() {
		ArrayList<SearchObj> searchlist= new ArrayList<SearchObj>();
	    Content html = views.html.index.render("",searchList);
	  assertEquals("text/html", html.contentType());
	  assertTrue(contentAsString(html).contains("Welcome to Freelancelot"));
	}

}
