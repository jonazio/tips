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

    public static Result upload() throws FileNotFoundException {
        MultipartFormData body = request().body().asMultipartFormData();
        FilePart tips = body.getFile("tips");
        if (tips != null) {     
            // make sure that the uploaded-file is a txt-file TODO
            //String fileName = tips.getFilename();
            // String contentType = tips.getContentType(); 
            File file = tips.getFile();
            Scanner sc = new Scanner(file);
            String output = sc.nextLine();
            output = sc.nextLine();
            sc.close();
            return ok("File uploaded");
        } else {
            flash("error", "Missing file");
            return redirect(routes.Application.index()); 
        }
    }

}
