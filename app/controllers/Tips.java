package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
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
		return ok(tipscoupon.render("10", 13));
	}


}
