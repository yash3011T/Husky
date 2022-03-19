package controllers;

import play.mvc.*;
import views.html.*;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import models.Display;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

	ArrayList<Display> displayList1 = new ArrayList<Display>();

//     */
	public CompletionStage<Result> index() {
		
		return CompletableFuture.completedFuture(ok(index.render("",0.0,0.0,displayList1)));

	}
	
}
		

	
