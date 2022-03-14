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

import model.Flesch;
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
	ArrayList<Display> displayList = new ArrayList<Display>();
	Flesch flesch= new Flesch();

	@Inject
	FormFactory formFactory;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
	public CompletionStage<Result> index() {
		
		return CompletableFuture.completedFuture(ok(views.html.index.render("",displayList)));
        
	}
	
	public CompletionStage<Result> Search(Http.Request request) {

		int[] index = new int[100];
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
			
			Display display = new Display();
			
			display.setOwner_id(Long.parseLong(jsonNode.get("result").get("projects").get(i).get("owner_id").asText()));
			//display.setSkills(jsonNode.get("result").get("projects").get(i).get("skills").asText());
			display.setTime_submitted(Long.parseLong(jsonNode.get("result").get("projects").get(i).get("time_submitted").asText()));
			display.setTitle(jsonNode.get("result").get("projects").get(i).get("title").asText());
			display.setType(jsonNode.get("result").get("projects").get(i).get("type").asText());
		    index[i] = flesch.Index(jsonNode.get("result").get("projects").get(i).get("preview_description").asText()); 
			if(index[i] == 0) {
			System.out.println("NULL");
			}else {
				System.out.println("NOT NULL");
			}
			displayList.add(display);
			i++;
			
		}

			
			
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return CompletableFuture.completedFuture(ok(views.html.index.render(query, displayList)));
		
    }
		
}
	



