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


public class WordStat extends Controller{
	
	final String base_url="https://www.freelancer.com/api/projects/0.1/projects/active?query=";
	String query = "empty";
	String statistics = "empty";
	String suffix = "&compact=false&job_details=true";
	
	ArrayList<Stats> statsList= new ArrayList<Stats>();
	
	ObjectMapper objmap = new ObjectMapper();
	
	public void clear_list() {
		
		statsList.clear();
	}
	
	public CompletionStage<Result> stats(String keyword, String title, Http.Request request) {
		
		clear_list();
		statistics = " ";
		
		StringBuilder sb = new StringBuilder();
		String output = "";
		query = keyword;
		
		
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
		
		System.out.println(title);
		
		int i = 0;
		
		while(i<jsonNode.get("result").get("projects").size()) {
			
			if(title.equals("global")) {

				statistics = statistics.concat(" ").concat(jsonNode.get("result").get("projects").get(i).get("preview_description").asText().replaceAll("\\s{2,}", " "));
				title = "Global Stats";
			}
			
			else {
				
				if(jsonNode.get("result").get("projects").get(i).get("title").asText().replaceAll("/"," ").replaceAll("\\s+","").equals(title.replaceAll("\\s+",""))) {
					statistics = statistics.concat(" ").concat(jsonNode.get("result").get("projects").get(i).get("preview_description").asText().replaceAll("\\s{2,}", " "));
					
				}
			}
			i++;
		}
		
		List<String> words = Arrays.asList(statistics.split(" "));
		
		System.out.println("UNIQUE WORDS  " + words.size());
		System.out.println("______________________________");
		
		
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
			
			//countByWordSorted.clear();		
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		

		
		return CompletableFuture.completedFuture(ok(views.html.stats.render(query,title,statsList)));
			
}
}
