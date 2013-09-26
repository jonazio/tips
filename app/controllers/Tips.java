package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

import play.libs.WS;
import play.libs.F.Function;
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
import views.html.matches;
import models.TipsCoupon;
import models.TipsResult;
import models.Tipsrow;

public class Tips extends Controller {
	
	public static TipsCoupon tipsCoupon;
		
	public static Result testoutput() {
    	return ok(test.render("Det funkar!"));
    }
	
	public static Result fileupload() {
		return ok(fileupload.render());
	}
	
	public static Result correctRow() {
		return ok("Ok!");
		//return ok(tipscoupon.render("111XXX2221111", 13));
	}
	
	//anropas 8 ggr ... varför?!
	public static Result correctRow2() {
		// read default file for TipsCoupon
		File file = new File("E:\\projekt\\tipssite\\2013.euro.v39.jonasokaka.12r-Egnarader.txt");
		try {
		    Scanner scanner = new Scanner(file);
		    tipsCoupon = new TipsCoupon(scanner);
		    scanner.close();
		} catch (IOException e) {
		}
		return ok(tipscoupon2.render("111XXX2221111", null));
	}
	
	/*public static Result matches(String correctRow, int noOfMatches) {
		return ok(matches.render(correctRow, noOfMatches));
	}*/
	
	public static Result summary(final String correctRow) {
		if (tipsCoupon != null ){
			// fetch url for stryktipset (551) or europatipset (553)
			//static for the moment TODO
			String url = "http://www.svt.se/svttext/web/pages/553.html";
			return async(
				      WS.url(url).get().map(
				    		  new Function<WS.Response, Result>() {
				    	          public Result apply(WS.Response response) throws IOException {
				    	        	  TipsResult tipsResult = new TipsResult(new Tipsrow("111222111XXXX"), 1000, 100, 10, 1);
				    	        	  StringWriter writer = new StringWriter();	
				    	        	  InputStream test = response.getBodyAsStream();
				    	        	  InputStreamReader encoding = new InputStreamReader(test, "UTF-8");
				    	        	  IOUtils.copy(encoding, writer);
				    	        	  String theString = writer.toString();
				    	        	  TipsUtil.findData(theString, tipsResult);
				    	        	  //return ok(summary.render(tipsCoupon.correctMatrix(tipsResult.correctRow.tipsrow)));
				    	        	  return ok(tipscoupon2.render(tipsResult.correctRow.tipsrow, tipsResult.matchResults));
				    	          }
				    	        }
				    	      )
				    	    );		
		}
		return ok("Ingen kupong inladdad.");
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
            return ok(index.render("111XXX2221111"));
        } else {
            flash("error", "Missing file");
            return redirect(routes.Application.index()); 
        }
    }

}
