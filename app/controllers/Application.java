package controllers;

import models.Tipsrow;
import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render("0"));
    }

  
}
