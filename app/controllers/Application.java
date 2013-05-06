package controllers;

import models.Tipsrow;
import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render("0"));
    }
    
    public static Result testoutput() {
    	return ok(test.render("Testing"));
    }

    public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
            Routes.javascriptRouter("jsRoutes",
            
                // Routes for Projects
                controllers.routes.javascript.Application.testoutput()
                
            )
        );
    }
  
}
