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

public class SkillsSearch extends Controller{
	
	ObjectMapper objmap = new ObjectMapper();
	
	public CompletionStage<Result> Skill(String query,long skill_id, Http.Request request) {
		
		final String base_url="https://www.freelancer.com/api/";
		final String project_url="projects/0.1/projects/active?jobs[]=";
		String suffix = "&compact=false&job_details=true";
		
		ArrayList<Display> displayList = new ArrayList<Display>();
		StringBuilder sb2 = new StringBuilder();
		String output = "";
		
		try {
			
			URL url = new URL(base_url.concat(project_url).concat(String.valueOf(skill_id).concat(suffix)));
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
	            sb2.append(line + "\n");
	        }
	        br.close();

			conn.disconnect();
			
			JsonNode jsonNode = objmap.readTree(sb2.toString());
			
			int i = 0;
			
			while(i<10 && i<jsonNode.get("result").get("projects").size()) {	
				
				Display sk = new Display();
					
				sk.setOwner_id(Long.parseLong(jsonNode.get("result").get("projects").get(i).get("owner_id").asText()));
				sk.setTime_submitted(Long.parseLong(jsonNode.get("result").get("projects").get(i).get("time_submitted").asText()));
				sk.setTitle(jsonNode.get("result").get("projects").get(i).get("title").asText());
				sk.setType(jsonNode.get("result").get("projects").get(i).get("type").asText());			
				sk.setSkills(jsonNode.get("result").get("projects").get(i).get("jobs").get(0).get("name").asText());
				sk.setSkill_id(Long.parseLong(jsonNode.get("result").get("projects").get(i).get("jobs").get(0).get("id").asText()));
				
				displayList.add(sk);
			
				i++;
				
				
				
			}
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(displayList);
		
		return CompletableFuture.completedFuture(ok(views.html.skills.render(query,skill_id,displayList)));
	
}


}
