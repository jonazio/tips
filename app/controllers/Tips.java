package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import views.html.index;
import views.html.test;
import views.html.fileupload;
import views.html.tipscoupon;
import views.html.tipscoupon2;
import views.html.summary;
import models.TipsCoupon;

public class Tips extends Controller {
	
	public static TipsCoupon tipsCoupon;
	
	public static Result testoutput() {
    	return ok(test.render("Det funkar!"));
    }
	
	public static Result fileupload() {
		return ok(fileupload.render());
	}
	
	public static Result correctRow() {
		return ok(tipscoupon.render("10", 13));
	}
	
	public static Result correctRow2() {
		return ok(tipscoupon2.render("10", 13));
	}
	
	public static Result summary(String correctRow) {
		if (tipsCoupon != null ){
			return ok(summary.render(tipsCoupon.correctMatrix(correctRow)));
		}
		return ok(correctRow);
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
            tipsCoupon = new TipsCoupon(sc);
            sc.close();
            tipsCoupon.printOut();
            // redirect to tipspage TODO
            return ok(index.render("0"));
        } else {
            flash("error", "Missing file");
            return redirect(routes.Application.index()); 
        }
    }

}
