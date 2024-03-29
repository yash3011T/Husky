package controllers;
import play.mvc.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

import static java.util.Comparator.reverseOrder;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;


/**
 * @author Yashvi Pithadia
 *
 */
public class WordStat extends Controller{
	
	final String base_url="https://www.freelancer.com/api/projects/0.1/projects/";
	String query = "empty";
	String statistics = "empty";
	String suffix = "&compact=false&job_details=true";
	
	ArrayList<Stats> statsList= new ArrayList<Stats>();
	
	ObjectMapper objmap = new ObjectMapper();
	
	public void clear_list() {
		
		statsList.clear();
	}
	
	/**
	 * Function for word count
	 * @param keyword: Query
	 * @param title: Project Title
	 * @param request: HTTP request
	 * @return CompletionStage of stats
	 */
	public CompletionStage<Result> stats(String keyword, String title, Http.Request request) {
		
		clear_list();
		statistics = " ";
		
		StringBuilder sb = new StringBuilder();
		String output = "";
		query = keyword;
			
		System.out.println("Query    " + query);
		System.out.println("title    " + title);

		
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
		
		while(i<jsonNode.get("result").get("projects").size()) {
			
			if(title.equals("global")) {

				statistics = statistics.concat(" ").concat(jsonNode.get("result").get("projects").get(i).get("preview_description").asText().replaceAll("\\s{2,}", " "));
			}
			
			else {
				
				if(jsonNode.get("result").get("projects").get(i).get("title").asText().replaceAll("/"," ").replaceAll("\\s+","").equals(title.replaceAll("\\s+",""))) {
					statistics = statistics.concat(" ").concat(jsonNode.get("result").get("projects").get(i).get("preview_description").asText().replaceAll("\\s{2,}", " "));
					
				}
			}
			i++;
		}
		
		
		if(title.equals("global")) {
		title = "Global Stats";
		}
		
		List<String> words = Arrays.asList(statistics.split(" "));
		
		
		Map<String,Long> collect = words.stream()
			    .collect( Collectors.groupingBy( Function.identity(), Collectors.counting() ));
		
		LinkedHashMap<String, Long> countByWordSorted = collect.entrySet()
	            .stream()
	            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
	            .collect(Collectors.toMap(
	                    Map.Entry::getKey,
	                    Map.Entry::getValue,
	                    (v1, v2) -> {
	                        throw new IllegalStateException();
	                    },
	                    LinkedHashMap::new
	            ));

		
		
		Set<String> keys = countByWordSorted.keySet();
			
			for (String key : keys) {
				
				if(!key.equals("")) {
				
				Stats stat = new Stats();
				
				stat.setWord(key);
                stat.setCount(countByWordSorted.get(key));
                
                statsList.add(stat);
				}

			}	
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		

		
		return CompletableFuture.completedFuture(ok(views.html.stats.render(query,title,statsList)));
			
}
	
	/**
	 * To fetch word stats data through owner
	 * @param id: Owner ID
	 * @param title: Project Title
	 * @param request: HTTP Request
	 * @return CompletionStage of statsID
	 */
	public CompletionStage<Result> statsID(long id, String title, Http.Request request) {
		
		clear_list();
		statistics = " ";
		
		StringBuilder sb = new StringBuilder();
		String output = "";
		
		try {
		URL url = new URL(base_url.concat("?owners[]=").concat(String.valueOf(id)).concat(suffix));
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
		
		while(i<jsonNode.get("result").get("projects").size()) {
			
			if(title.equals("global")) {

				statistics = statistics.concat(" ").concat(jsonNode.get("result").get("projects").get(i).get("preview_description").asText().replaceAll("\\s{2,}", " "));
			}
			
			else {
				
				if(jsonNode.get("result").get("projects").get(i).get("title").asText().replaceAll("/"," ").replaceAll("\\s+","").equals(title.replaceAll("\\s+",""))) {
					statistics = statistics.concat(" ").concat(jsonNode.get("result").get("projects").get(i).get("preview_description").asText().replaceAll("\\s{2,}", " "));
					
				}
			}
			i++;
		}
		
		
		if(title.equals("global")) {
		title = "Global Stats";
		}
		
		
	
		List<String> words = Arrays.asList(statistics.split(" "));
	
		Map<String,Long> collect = words.stream()
			    .collect( Collectors.groupingBy( Function.identity(), Collectors.counting() ));
		
		LinkedHashMap<String, Long> countByWordSorted = collect.entrySet()
	            .stream()
	            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
	            .collect(Collectors.toMap(
	                    Map.Entry::getKey,
	                    Map.Entry::getValue,
	                    (v1, v2) -> {
	                        throw new IllegalStateException();
	                    },
	                    LinkedHashMap::new
	            ));

		
		
		Set<String> keys = countByWordSorted.keySet();
			
			for (String key : keys) {
				
				if(!key.equals("")) {
				
				Stats stat = new Stats();
				
				stat.setWord(key);
                stat.setCount(countByWordSorted.get(key));
                
                statsList.add(stat);
				}

			}	
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		

		
		return CompletableFuture.completedFuture(ok(views.html.stats.render(String.valueOf(id),title,statsList)));
			
}
	
	
}
