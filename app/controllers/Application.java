package controllers;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import models.TipsCoupon;
import models.TipsCouponMatches;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;

import models.results.Tipsrow;
import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
        TipsCoupon tipsCoupon = new TipsCoupon();
        tipsCoupon.productId = 14L;
        tipsCoupon.week = 12L;
        tipsCoupon.save();
        TipsCouponMatches matches;
        matches = new TipsCouponMatches();
        matches.homeTeam = "Gefle";
        matches.awayTeam = "IFK";
        tipsCoupon.matches.add(matches);
        tipsCoupon.update();
        TipsCoupon find = tipsCoupon.find(201L);
        if (find.matches.isEmpty()){
            System.out.println("TOMT!!!!");

        } else {
            System.out.println(find.matches.get(0).homeTeam);
        }

        return ok(index.render("111XXX2221111"));
    }
    
    public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
            Routes.javascriptRouter("jsRoutes",
            
                // Routes for Tips
                //controllers.routes.javascript.Tips.correctRow(),
                controllers.routes.javascript.Tips.summary(),
                //controllers.routes.javascript.Tips.matches(),
                controllers.routes.javascript.Tips.upload()
                
            )
        );
    }
  
}
