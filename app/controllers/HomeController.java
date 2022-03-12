package controllers;

import play.mvc.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import views.html.*;
import play.data.*;

import javax.inject.Inject;
import play.mvc.Http.Request;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

	@Inject
	FormFactory formFactory;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
	public CompletionStage<Result> index() {
		
        return CompletableFuture.completedFuture(ok(views.html.index.render(" ")));
    }
	
	public CompletionStage<Result> Search(Http.Request request) {
		
		final String query = formFactory.form().bindFromRequest(request).get("query");
		return CompletableFuture.completedFuture(ok(views.html.index.render(query)));
		
		
    }
		
}


