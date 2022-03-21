package controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FleschCalculatorTest {

	@Test
	void testFleschID() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/freelancelot/readability/AI/PS%20Laboratory");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
	
	@Test
	void testFleschID2() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/freelancelot/readability/dance/45861541856");

        Result result = route(app, request);
        assertEquals(404, result.status());
    }
}

