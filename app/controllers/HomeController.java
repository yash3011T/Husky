package controllers;

import play.mvc.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import views.html.*;
import play.data.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.*;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.inject.Inject;
import play.mvc.Http.Request;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
	
	final String base_url="https://www.freelancer.com/api/projects/0.1/projects/active?query=";
	String finalQuery;
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
		
		String output;
		
		try {
		URL url = new URL(base_url.concat(query.trim().replaceAll(" ", "")));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			output = "Failed";
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		output = "Output from Server .... \n";
		// while ((output = br.readLine()) != null) {
			// System.out.println(output);
		// }

		conn.disconnect();
		}
		catch(Exception e) {
			output = e.getCause().toString();
		}
		return CompletableFuture.completedFuture(ok(views.html.index.render(output)));
		
		
    }
		
}


