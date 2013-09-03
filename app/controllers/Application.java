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
    
    public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
            Routes.javascriptRouter("jsRoutes",
            
                // Routes for Tips
                controllers.routes.javascript.Tips.testoutput(),
                controllers.routes.javascript.Tips.fileupload(),
                controllers.routes.javascript.Tips.correctRow(),
                controllers.routes.javascript.Tips.correctRow2(),
                controllers.routes.javascript.Tips.upload()
                
            )
        );
    }
  
}
