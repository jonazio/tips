package models;

public class Tipsrow {
	
	public String tipsrow;
	public int correctMatches;
	
	public Tipsrow(String tipsrow){
		this.tipsrow = tipsrow;	
		correctMatches = 0;
	}

	public int countMatchingCharacters (String tipsrad){
		int count = 0;
		if (tipsrad.length() != this.tipsrow.length()) {
			return 0;
		}
		for (int a = 0; a < this.tipsrow.length(); a++){
			if (this.tipsrow.charAt(a) == tipsrad.charAt(a)){
				count++;
			}
		}
		return count;
	}
	
	public boolean compare(String tipsrad) {
		if (this.tipsrow.equals(tipsrad)) {
			return true;
		} else {
			return false;
		}		
	}

}
