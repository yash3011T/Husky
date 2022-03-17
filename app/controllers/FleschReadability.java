package controllers;

import play.mvc.*;

import java.util.ArrayList;
import java.util.List;
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

public class FleschReadability extends Controller{
	
	final String base_url="https://www.freelancer.com/api/projects/0.1/projects/";
	String suffix = "&compact=false&job_details=true";
	ObjectMapper objmap = new ObjectMapper();
	ArrayList<FleschSetter> setterList = new ArrayList<FleschSetter>();
	FleschCalculator flesch= new FleschCalculator();
	
	
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
