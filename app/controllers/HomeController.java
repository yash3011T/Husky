package controllers;

import play.mvc.*;
import views.html.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import models.Display;
import models.SearchObj;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

	ArrayList<SearchObj> searchList = new ArrayList<SearchObj>();

			
	public CompletionStage<Result> index() {
		
		return CompletableFuture.completedFuture(ok(index.render("",searchList)).withNewSession());

	}
	
}
		

	
