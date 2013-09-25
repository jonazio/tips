package models;

public class MatchResult {
	
	public String homeTeam;
	public String awayTeam;
	
	// 1,X,2
	public String result;
	
	public int homeScore;
	public int awayScore;
	
	public MatchResult (String homeTeam, String awayTeam, String result, int homeScore, int awayScore){
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.result = result;
	}
	
	public void printOut(){
		System.out.println(homeTeam + " - " + awayTeam + "\t" + result);
	}
	
}