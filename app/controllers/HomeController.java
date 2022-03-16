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

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
	
	final String base_url="https://www.freelancer.com/api/projects/0.1/projects/active?query=";
	String query = "empty";
	String suffix = "&compact=false&job_details=true";
	ObjectMapper objmap = new ObjectMapper();
	ArrayList<Display> displayList = new ArrayList<Display>();
	Flesch flesch= new Flesch();
	int FleschReadability = 0;
	int FleschKincaid = 0;
	int fleschAvg=0;
	int kincadAvg=0;
	@Inject
	FormFactory formFactory;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
	public CompletionStage<Result> index() {
		
		return CompletableFuture.completedFuture(ok(views.html.index.render("",FleschReadability,FleschKincaid,displayList)));
        
	}
	
	public void clear_list() {
		
		displayList.clear();
	}
	
	public CompletionStage<Result> Search(Http.Request request) {
		
		clear_list();
		double[] values = new double[100];
	


		query = formFactory.form().bindFromRequest(request).get("query");


		StringBuilder sb = new StringBuilder();
		String output = "";
		
		try {
		URL url = new URL(base_url.concat(query.trim().replaceAll(" ", "%20")).concat(suffix));
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
			display.setTime_submitted(Long.parseLong(jsonNode.get("result").get("projects").get(i).get("time_submitted").asText()));
			display.setTitle(jsonNode.get("result").get("projects").get(i).get("title").asText().replaceAll("/"," "));
			display.setType(jsonNode.get("result").get("projects").get(i).get("type").asText());
			display.setDescription(jsonNode.get("result").get("projects").get(i).get("preview_description").asText());
			
			String skill[] = new String[3];
			
			skill[0] = jsonNode.get("result").get("projects").get(i).get("jobs").get(0).get("name").asText();
			
			display.setSkills(skill);
			int c = 0;
			values = flesch.Index(jsonNode.get("result").get("projects").get(i).get("preview_description").asText()); 
			if(values[c] != Double.POSITIVE_INFINITY || values[c] != Double.NEGATIVE_INFINITY) {
			FleschReadability=(int) values[0];
			FleschKincaid=(int) values[1];
			
			}else {
				FleschReadability = 0;
				FleschKincaid=0;
			}
			displayList.add(display);
			i++;
			
		}

			
			
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return CompletableFuture.completedFuture(ok(views.html.index.render(query, FleschReadability,FleschKincaid, displayList)));
		
    }
		
}
	



