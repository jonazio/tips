package models.results;

import java.awt.List;


public class TipsResult {
	
	public Tipsrow correctRow;
	
	public MatchResult[] matchResults = new MatchResult[13];
	
	public long payout13;
	public long payout12;
	public long payout11;
	public long payout10;
	
	public TipsResult(Tipsrow correctRow, long payout13, long payout12, long payout11, long payout10){
		this.correctRow = correctRow;
		this.payout13 = payout13;
		this.payout12 = payout12;
		this.payout11 = payout11;
		this.payout10 = payout10;
		for (int a = 0; a < matchResults.length; a++){
			matchResults[a] = new MatchResult();
		}
	}
}