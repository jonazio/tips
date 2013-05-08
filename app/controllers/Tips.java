package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.test;
import views.html.fileupload;
import views.html.tipscoupon;

public class Tips extends Controller {
	
	public static Result testoutput() {
    	return ok(test.render("Det funkar!"));
    }
	
	public static Result fileupload() {
		return ok(fileupload.render());
	}
	
	public static Result correctRow() {
		return ok(tipscoupon.render("1231231231231", 13));
	}

}
