package models.results;

public class MatchResult {
	
	public String homeTeam;
	public String awayTeam;
	
	// 1,X,2
	public String result;
	
	public String score;
	
	public int homeScore;
	public int awayScore;
	
	public MatchResult(){
		this.homeTeam = "Lag 1";
		this.awayTeam = "Lag 2";
		this.score = "0-0";
		this.homeScore = 0;
		this.awayScore = 0;
		this.result = "X";
	}
	
	public MatchResult (String homeTeam, String awayTeam, String result, String score){
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.score = score;
		this.result = result;
		setScores(score);
	}
	
	public String toString(){
		return homeTeam + " - " + awayTeam + "\t" + result;
	}
	
	public void printOut(){
		System.out.println(toString());
	}
	
	public void setScores(String score){
		// if string contains - then we're gonna assume it's a score
		if (score.contains("-")){
			homeScore = new Integer(score.substring(0, score.indexOf("-")));
			awayScore = new Integer(score.substring(score.indexOf("-")+1, score.length()-score.indexOf("-")+1));
		}
	}
	
}