package controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FleschReadabilityTest {

	@Test
	void testcalculateScore() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/freelancelot/readability/user/61332188/Chinese%20Translation");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
	
	@Test
	void testcalculateScore2() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/freelancelot/readability/user/abcd/Testfailing");

        Result result = route(app, request);
        assertEquals(404, result.status());
    }
}
