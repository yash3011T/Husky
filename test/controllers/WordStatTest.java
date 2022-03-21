package controllers;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class WordStat extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testWordStat() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/freelancelot/stats/AI/PS%20Laboratory");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
    
	@Test
	void testWordStat2() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/freelancelot/stats/123");

        Result result = route(app, request);
        assertEquals(404, result.status());
    }
	
    @Test
    public void testWordStat() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/freelancelot/stats/user/61332059/PS%20Laboratory");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
}
