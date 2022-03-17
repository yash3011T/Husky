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
	
	final String base_url="https://www.freelancer.com/api/projects/0.1/projects/";
	String query = "empty";
	String suffix = "&compact=false&job_details=true";
	ObjectMapper objmap = new ObjectMapper();
	ArrayList<Display> displayList = new ArrayList<Display>();
	ArrayList<FleschSetter> setterList = new ArrayList<FleschSetter>();
	FleschOther flesch= new FleschOther();

	double fleschAvg=0;
	double kincadAvg=0;
	double fleschSum=0;
	double kincadSum=0;
	
	@Inject
	FormFactory formFactory;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
	public CompletionStage<Result> index() {
		
		return CompletableFuture.completedFuture(ok(views.html.index.render("",fleschSum,kincadAvg,displayList)));
        
	}
	
	public CompletionStage<Result> Search(Http.Request request) {
		
		displayList.clear();
		double[] values = new double[100];
	
		query = formFactory.form().bindFromRequest(request).get("query");
		
		final String load = formFactory.form().bindFromRequest(request).get("reload");
		
		StringBuilder sb = new StringBuilder();
		String output = "";
		
		try {
		URL url = new URL(base_url.concat("active?query=").concat(query.trim().replaceAll(" ", "%20")).concat(suffix));
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
			display.setType(jsonNode.get("result").get("projects").get(i).get("type").asText());		
			display.setSkills(jsonNode.get("result").get("projects").get(i).get("jobs").get(0).get("name").asText());
			display.setSkill_id(Long.parseLong(jsonNode.get("result").get("projects").get(i).get("jobs").get(0).get("id").asText()));	

			displayList.add(display);
			
			int c = 0;
			values = flesch.calculateScore(jsonNode.get("result").get("projects").get(i).get("preview_description").asText()); 
			
			FleschSetter setter = new FleschSetter();
			
			setter.setTitle(jsonNode.get("result").get("projects").get(i).get("title").asText());
			
			if(values[c] != Double.POSITIVE_INFINITY || values[c] != Double.NEGATIVE_INFINITY) {
				
				setter.setFleshReadability(values[0]);
				setter.setFleschKincade(values[1]);
				
			
			}else {
				setter.setFleshReadability(0);
				setter.setFleschKincade(0);
			}
			
			
			setterList.add(setter);
			
			i++;
			
		}
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		for(int j=0; j<setterList.size(); j++) {
			
			kincadSum = kincadSum + setterList.get(j).getFleschKincade();
			fleschSum = fleschSum + setterList.get(j).getFleshReadability();

		}
		
		fleschAvg = fleschSum/setterList.size();
		kincadAvg = kincadSum/setterList.size();
		
		return CompletableFuture.completedFuture(ok(views.html.index.render(query, fleschAvg , kincadAvg , displayList)));
		
}

	
	public CompletionStage<Result> Flesch(String keyword, String title, Http.Request request) {
		
		setterList.clear();
		double[] values = new double[100];
		
		StringBuilder sb = new StringBuilder();
		String output = "";
		
		FleschSetter current = new FleschSetter();
		
		try {
		URL url = new URL(base_url.concat("active?query=").concat(keyword.trim().replaceAll(" ", "%20")).concat(suffix));
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

		while(i<jsonNode.get("result").get("projects").size() && i<250) {
			
			int c = 0;
			values = flesch.calculateScore(jsonNode.get("result").get("projects").get(i).get("preview_description").asText()); 
			
			FleschSetter setter = new FleschSetter();
			
			setter.setTitle(jsonNode.get("result").get("projects").get(i).get("title").asText());
			
			if(values[c] != Double.POSITIVE_INFINITY || values[c] != Double.NEGATIVE_INFINITY) {
				
				setter.setFleshReadability(values[0]);
				setter.setFleschKincade(values[1]);
				
			
			}else {
				setter.setFleshReadability(0);
				setter.setFleschKincade(0);
			}
			

			if(jsonNode.get("result").get("projects").get(i).get("title").asText().replaceAll("/"," ").replaceAll("\\s+","").equals(title.replaceAll("\\s+",""))) { 
				current = setter;
			}
			
			i++;
			
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return CompletableFuture.completedFuture(ok(views.html.flesch.render(keyword,title,current)));
	}
	
public CompletionStage<Result> FleschID(long id, String title, Http.Request request) {
		
		setterList.clear();
		double[] values = new double[100];
		
		StringBuilder sb = new StringBuilder();
		String output = "";
		
		FleschSetter current = new FleschSetter();
		
		try {
		URL url = new URL(base_url.concat("?owners[]=").concat(String.valueOf(id)).concat(suffix));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		
		System.out.println(base_url.concat("?owners[]=").concat(String.valueOf(id)).concat(suffix));

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

		while(i<jsonNode.get("result").get("projects").size() && i<250) {
			
			int c = 0;
			values = flesch.calculateScore(jsonNode.get("result").get("projects").get(i).get("preview_description").asText()); 
			
			FleschSetter setter = new FleschSetter();
			
			setter.setTitle(jsonNode.get("result").get("projects").get(i).get("title").asText());
			
			if(values[c] != Double.POSITIVE_INFINITY || values[c] != Double.NEGATIVE_INFINITY) {
				
				setter.setFleshReadability(values[0]);
				setter.setFleschKincade(values[1]);
				
			
			}else {
				setter.setFleshReadability(0);
				setter.setFleschKincade(0);
			}
			

			if(jsonNode.get("result").get("projects").get(i).get("title").asText().replaceAll("/"," ").replaceAll("\\s+","").equals(title.replaceAll("\\s+",""))) { 
				current = setter;
			}
			
			i++;
			
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return CompletableFuture.completedFuture(ok(views.html.flesch.render(String.valueOf(id),title,current)));
	}
	
}
		

	
