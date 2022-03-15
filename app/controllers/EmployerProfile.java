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


public class EmployerProfile extends Controller{
	
	ObjectMapper objmap = new ObjectMapper();
	Employer empl = new Employer();

	public CompletionStage<Result> employer(long id, Http.Request request) {
		
		final String base_url="https://www.freelancer.com/api/users/0.1/users/";
		
		StringBuilder sb = new StringBuilder();
		String output = "";
		
		try {
		URL url = new URL(base_url.concat(String.valueOf(id)));
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
		
				
		empl.setOwner_id(Long.parseLong(jsonNode.get("result").get("id").asText()));
		empl.setUsername(jsonNode.get("result").get("username").asText());
		empl.setRole(jsonNode.get("result").get("role").asText());
		empl.setCountry(jsonNode.get("result").get("location").get("country").get("name").asText());
		empl.setTime_zone(jsonNode.get("result").get("timezone").get("timezone").asText());

		
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return CompletableFuture.completedFuture(ok(views.html.user.render(empl)));
	
}
}
