package models.results;

public class Tipsrow {
	
	public String tipsrow;
	public int correctMatches;
	
	public Tipsrow(String tipsrow){
		this.tipsrow = tipsrow.replace(",", "").replace("E", "");	
		correctMatches = 0;
	}

}
