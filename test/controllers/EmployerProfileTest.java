package controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployerProfileTest {

	@Test
	void testEmployer() {
	        Http.RequestBuilder request = new Http.RequestBuilder()
	                .method(GET)
	                .uri("/freelancelot/react/employer/12024423 ");

	        Result result = route(app, request);
	        assertEquals(OK, result.status());
	    }
	@Test
	void testEmployer2() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/freelancelot/abc/employer/randomstring");

        Result result = route(app, request);
        assertEquals(404, result.status());
    }
	}


