package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	
	private static final String teamPattern = "([A-Za-zÅÄÖåäö]*[\\s\\.]{0,1}[A-Za-zÅÄÖåäö]*\\.{0,1})";
	private static final String repeatingPattern = "(s*<span class=\"G\">\\s* </span>)*";
	private static final String matchNoPattern = "<span class=\"G\">(\\d{1,2})\\. </span>";
	private static final String teamOnePattern = "\\s*<span class=\"G\">"+teamPattern+" </span>";
	private static final String teamTwoPattern = "<span class=\"G\">-"+teamPattern+" </span>";
	private static final String scoresPattern = "<span class=\"G\">(\\d{1,2}-\\d{1,2}) </span>";
	private static final String resultPattern = "<span class=\"G\">([1X2]{1})\\s{1,2}</span>";
	private static final String teamsPattern = "\\s*<span class=\"G\">"+teamPattern+"-"+teamPattern+" </span>";
	
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
			    	        	  InputStreamReader encoding = new InputStreamReader(test, "UTF-8");
			    	        	  IOUtils.copy(encoding, writer);
			    	        	  String theString = writer.toString();
			    	        	  //System.out.println(theString);
			    	        	  findData(theString);
			    	        	  
						return ok("Test");
			    	          }
			    	        }
			    	      )
			    	    );
			    	  }
		
	public static void findData(String inString) {
		Pattern matchResults = Pattern.compile(matchNoPattern+teamOnePattern+repeatingPattern+teamTwoPattern+repeatingPattern+scoresPattern+repeatingPattern+resultPattern);
		Matcher m = matchResults.matcher(inString);
		while (m.find()){
			System.out.println(m.group(1) + ". "+ m.group(2) + "- " + m.group(4) + " " + m.group(6) + " " + m.group(8));	
		}
		
		Pattern matchResults2 = Pattern.compile(matchNoPattern+teamsPattern+repeatingPattern+scoresPattern+repeatingPattern+resultPattern);
		m = matchResults2.matcher(inString);
		while (m.find()){
			System.out.println(m.group(1) + ". "+  m.group(2) + " - " + m.group(3) + " " + m.group(5) + " " + m.group(7));			
		}
	}
	
	
	
	
}
