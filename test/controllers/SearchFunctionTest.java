package controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SearchFunctionTest {

	@Test
	void testSearchFunction() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/freelancelot");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
	
	@Test
	void testSearchFunction2() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/xyz");

        Result result = route(app, request);
        assertEquals(404, result.status());
    }
}
