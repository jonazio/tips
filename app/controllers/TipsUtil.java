package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import play.mvc.*;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.WS;
import org.apache.commons.io.IOUtils;

public class TipsUtil extends Controller{
	
	private static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
	}

	public static int computeLevenshteinDistance(CharSequence str1,
                CharSequence str2) {
        int[][] distance = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++)
                distance[i][0] = i;
        for (int j = 1; j <= str2.length(); j++)
                distance[0][j] = j;

        for (int i = 1; i <= str1.length(); i++)
                for (int j = 1; j <= str2.length(); j++)
                        distance[i][j] = minimum(
                                        distance[i - 1][j] + 1,
                                        distance[i][j - 1] + 1,
                                        distance[i - 1][j - 1]
                                                        + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0
                                                                        : 1));

        return distance[str1.length()][str2.length()];
	}

	
	public static Result fetchCorrectRow() {
		// fetch url for stryktipset (551) or europatipset (553)
		//static for the moment TODO
		String url = "http://www.svt.se/svttext/web/pages/553.html";
		return async(
			      WS.url(url).get().map(
			    		  new Function<WS.Response, Result>() {
			    	          public Result apply(WS.Response response) throws IOException {
			    	        	  StringWriter writer = new StringWriter();
			    	        	  InputStream test = response.getBodyAsStream();
			    	        	  IOUtils.copy(test, writer);
			    	        	  String theString = writer.toString();
			    	        	  //System.out.println(theString);
			    	        	  System.out.println(theString.indexOf("1. "));
			    	        	  findData(theString);
			    	        	  
						return ok("Test");
			    	          }
			    	        }
			    	      )
			    	    );
			    	  }
		
	public static void findData(String inString) {
		System.out.println("findData");
		Pattern MY_PATTERN = Pattern.compile("<span class=\"G\">\\d{1,2}\\. </span>\\s*<span class=\"G\">\\w* </span>");
		Matcher m = MY_PATTERN.matcher(inString);
		while (m.find()){
			//System.out.println("hittade förekomster: " + m.groupCount());
			System.out.println(m.group(0));
			
		}
	}
	
	
	
	
}
