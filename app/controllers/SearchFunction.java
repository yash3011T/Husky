package controllers;
import play.mvc.*;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
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
import play.cache.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.api.libs.json.*;
import play.mvc.Http.Request;

public class SearchFunction extends Controller{
	
	final String base_url="https://www.freelancer.com/api/projects/0.1/projects/";
	String query = "empty";
	String suffix = "&compact=false&job_details=true";
	ObjectMapper objmap = new ObjectMapper();
	
	ArrayList<ArrayList<Display>> displayObject = new ArrayList<ArrayList<Display>>();
	ArrayList<FleschSetter> setterList = new ArrayList<FleschSetter>();
	FleschCalculator flesch= new FleschCalculator();

	double fleschAvg=0;
	double kincadAvg=0;
	
	
	FormFactory formFactory;
	
	
	@NamedCache("Session-cache")
	SyncCacheApi synCache;
	
	@Inject
	public SearchFunction(SyncCacheApi syncApi,FormFactory formfactory){
		this.synCache = syncApi;
		this.formFactory=formfactory;
		
	}
	
public CompletionStage<Result> Search(Http.Request request) {
		
		
		ArrayList<Display> displayList = new ArrayList<Display>();
		
		
	
		
		double[] values = new double[100];
	
		query = formFactory.form().bindFromRequest(request).get("query");
		
		final String load = formFactory.form().bindFromRequest(request).get("reload");
		
		
		
		StringBuilder sb = new StringBuilder();
		String output = "";
		
		try {
		if(synCache.get("query").isPresent() && synCache.get("query").get().equals(query)) {
		
				Optional<ArrayList<Display>> display_cached = synCache.get("display");
				System.out.println(display_cached);
				
				displayList.addAll(display_cached.get());
			
		}else {
		
			
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
			display.setTime_submitted(Long.parseLong(jsonNode.get("result").get("projects").get(i).get("time_submitted").asText())*1000);
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
		
		synCache.set("display",displayList);
		synCache.set("query",query);
		
		
		displayObject.add(displayList);
			
		
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
			
		List <Double> kincadSum = new ArrayList <Double>();
		List <Double> fleschSum = new ArrayList <Double>();
		
		for(int j=0; j<setterList.size(); j++) {
			
			kincadSum.add(setterList.get(j).getFleschKincade());
			fleschSum.add(setterList.get(j).getFleshReadability());

		}		
		
		fleschAvg = calculateAverage(fleschSum);
		kincadAvg = calculateAverage(kincadSum);
		
		return CompletableFuture.completedFuture(ok(views.html.index.render(query, fleschAvg , kincadAvg , displayList, displayObject)));
		
}

	private double calculateAverage(List <Double> score) {
	    return score.stream()
	                .mapToDouble(d -> d)
	                .average()
	                .orElse(0.0);
	}
	

}
