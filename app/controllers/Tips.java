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
import util.*;
import views.html.*;
import models.results.TipsCoupon;
import models.results.TipsResult;
import models.results.Tipsrow;
import views.html.fileupload.fileupload;

public class Tips extends Controller {
	
	public static TipsCoupon tipsCoupon;
	
	public static Result fileUpload() {
		return ok(fileupload.render());
	}
	
	//anropas 8 ggr ... varför?!
	public static Result correctRow() {
		// read default file for TipsCoupon
		File file = new File("E:\\projekt\\tipssite\\2013.euro.v50.jonasokaka.v2-Egnarader.txt");
		try {
		    Scanner scanner = new Scanner(file);
		    tipsCoupon = new TipsCoupon(scanner);
		    scanner.close();
		} catch (IOException e) {
		}
		return ok(tipscoupon.render("111XXX2221111", null, null));
	}
	
	public static Result summary(final String correctRow) {
		if (tipsCoupon != null ){
			// fetch url for stryktipset (551) or europatipset (553)
			//static for the moment TODO
			String url = "http://www.svt.se/svttext/web/pages/553.html";
			// move WS call to another class TODO
			return async(
				      WS.url(url).get().map(
				    		  new Function<WS.Response, Result>() {
				    	          public Result apply(WS.Response response) throws IOException {
				    	        	  // initialize tipsResult object
				    	        	  TipsResult tipsResult = new TipsResult(new Tipsrow("111222111XXXX"), 1000, 100, 10, 1);
				    	        	  StringWriter writer = new StringWriter();	
				    	        	  InputStream test = response.getBodyAsStream();
				    	        	  InputStreamReader encoding = new InputStreamReader(test, "UTF-8");
				    	        	  IOUtils.copy(encoding, writer);
				    	        	  String theString = writer.toString();
				    	        	  // should not call a static method TODO
				    	        	  StryktipsParseResultsImpl parseResults = new StryktipsParseResultsImpl();
				    	        	  parseResults.findData(theString, tipsResult);
				    	        	  return ok(tipscoupon.render(tipsResult.correctRow.tipsrow, tipsResult.matchResults, tipsCoupon.correctMatrix(tipsResult.correctRow.tipsrow)));
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
            return ok(tipscoupon.render("Tipshjälpen", null, null));
        } else {
            flash("error", "Missing file");
            return redirect(routes.Application.index()); 
        }
    }

}
