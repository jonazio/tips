package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.results.*;
import models.results.TipsResult;

public class StryktipsParseResultsImpl implements ParseResults {

	private static final String teamPattern = "([A-Za-zÅÄÖåäö]*[\\s\\.]{0,1}[A-Za-zÅÄÖåäö]*\\.{0,1})";
	private static final String spanPattern = "<span class=\"[CGW]{1}\">";
	private static final String repeatingPattern = "(\\s*"+spanPattern+"\\s* </span>)*";
	private static final String repeatingPattern2 = "(\\s*<span class=\"W\">\\s* </span>)*";
	private static final String matchNoPattern = spanPattern+"(\\d{1,2})\\. </span>";
	private static final String teamOnePattern = "\\s*"+spanPattern+teamPattern+" </span>";
	private static final String teamTwoPattern = spanPattern+"-"+teamPattern+" </span>";
	private static final String scoresPattern = spanPattern+"(\\d{1,2}-\\d{1,2}|Lottad) </span>";
	private static final String resultPattern = spanPattern+"([1X2]{1})\\s{1,2}</span>";
	private static final String teamsPattern = "\\s*"+spanPattern+teamPattern+"-"+teamPattern+" </span>";
	
	private static final String correctRowsPattern = "\\s*<span class=\"W\">(\\d{2}) (</span>\\s*<span class=\"W\">)*rätt: </span>";
    private static final String payoutPattern = "\\s*<span class=\"W\">\\s*(((\\d{1,3}\\.)*\\d{1,3})(\\s{1}|</span>\\s*<span class=\"W\">\\s*)kr|Ingen utd) </span>";

	public void findData(String inString, TipsResult tipsResult) {
		
		char[] results = new char[13];
		
		int endPoint = inString.indexOf("TOPPTIPSET");
		if (endPoint != -1){
			inString = inString.substring(0,endPoint);
		}
		
		Pattern matchResults = Pattern.compile(matchNoPattern+teamOnePattern+repeatingPattern+teamTwoPattern+repeatingPattern+scoresPattern+repeatingPattern+resultPattern);
		Matcher m = matchResults.matcher(inString);
		while (m.find()){
			System.out.println(m.group(1) + ". "+ m.group(2) + "- " + m.group(4) + " " + m.group(6) + " " + m.group(8));	
			results[new Integer(m.group(1))-1] = m.group(8).charAt(0);
			tipsResult.matchResults[new Integer(m.group(1))-1] = new MatchResult(m.group(2), m.group(4), m.group(8), m.group(6));
		}
		
		Pattern matchResults2 = Pattern.compile(matchNoPattern+teamsPattern+repeatingPattern+scoresPattern+repeatingPattern+resultPattern);
		m = matchResults2.matcher(inString);
		while (m.find()){
			System.out.println(m.group(1) + ". "+  m.group(2) + " - " + m.group(3) + " " + m.group(5) + " " + m.group(7));	
			results[new Integer(m.group(1))-1] = m.group(7).charAt(0);
			tipsResult.matchResults[new Integer(m.group(1))-1] = new MatchResult(m.group(2), m.group(3), m.group(7), m.group(5));
		}
		
		Pattern matchResults3 = Pattern.compile(correctRowsPattern+repeatingPattern2+payoutPattern);
		m = matchResults3.matcher(inString);
		while (m.find()){
			if (m.group(5) != null){
				System.out.println(m.group(1) + " " + m.group(5));		
				savePayout(m.group(1), m.group(5), tipsResult);
			} else {
				System.out.println(m.group(1) + " " + m.group(4));	
				savePayout(m.group(1), m.group(4), tipsResult);
			}
		}
		
		String result = "";
		for (int a = 0; a < 13; a++){
			result += results[a];
		}
		
		tipsResult.correctRow.tipsrow = result;

	}
	
	private static void savePayout(String noOfCorrectRows, String payout, TipsResult tipsResult){
		Integer pay = new Integer(payout.replace(".", "").replace(",", "").replace("Ingen utd", "0"));
		
		if (noOfCorrectRows.equals("13")){
			tipsResult.payout13 = pay;
		} else if (noOfCorrectRows.equals("12")){
			tipsResult.payout12 = pay;
		} else if (noOfCorrectRows.equals("11")){
			tipsResult.payout11 = pay;
		} else if (noOfCorrectRows.equals("10")){
			tipsResult.payout10 = pay;
		}	
	}

}