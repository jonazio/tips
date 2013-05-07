package controllers;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;

import models.Tipsrow;
import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render("0"));
    }

    public static Result upload() throws FileNotFoundException {
        MultipartFormData body = request().body().asMultipartFormData();
        FilePart tips = body.getFile("tips");
        if (tips != null) {
            // String fileName = tips.getFilename();
            // String contentType = tips.getContentType(); 
            File file = tips.getFile();
            Scanner sc = new Scanner(file);
            String output = sc.nextLine();
            output = sc.nextLine();
            sc.close();
            return ok(output);
        } else {
            flash("error", "Missing file");
            return redirect(routes.Application.index()); 
        }
        
        // make sure that the uploaded-file is a txt-file
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
