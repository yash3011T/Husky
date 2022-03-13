package controllers;

import play.mvc.*;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import views.html.*;
import play.data.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import models.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.api.libs.json.*;
import play.mvc.Http.Request;
import play.api.libs.json.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
	
	final String base_url="https://www.freelancer.com/api/projects/0.1/projects/active?query=";
	String finalQuery;
	String suffix = "&compact=false&job_details=true";
	ObjectMapper objmap = new ObjectMapper();

	@Inject
	FormFactory formFactory;
	
	ArrayList<String> owner_ids = new ArrayList<String>();

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
	public CompletionStage<Result> index() {
		
        return CompletableFuture.completedFuture(ok(views.html.index.render(owner_ids," ")));
        
	}
	
	public CompletionStage<Result> Search(Http.Request request) {

		
		final String query = formFactory.form().bindFromRequest(request).get("query");

		StringBuilder sb = new StringBuilder();
		String output = "";
		
		try {
		URL url = new URL(base_url.concat(query.trim().replaceAll(" ", "")).concat(suffix));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			output = "Failed";
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

		conn.disconnect();

		JsonNode jsonNode = objmap.readTree(sb.toString());
		
		int i = 0;
		
		while(i<jsonNode.get("result").get("projects").size() && i<10) {
			
			owner_ids.add(jsonNode.get("result").get("projects").get(i).get("owner_id").asText());
			i++;
		}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return CompletableFuture.completedFuture(ok(views.html.index.render(owner_ids,query)));
		
    }
		
}
	



