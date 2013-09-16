package models;

public class Tipsrow {
	
	public String tipsrow;
	public int correctMatches;
	
	public Tipsrow(String tipsrow){
		this.tipsrow = tipsrow.replace(",", "").replace("E", "");	
		correctMatches = 0;
	}

	// not used, delete? TODO 
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
	
	// same here, probably not used TODO
	public boolean compare(String tipsrad) {
		if (this.tipsrow.equals(tipsrad)) {
			return true;
		} else {
			return false;
		}		
	}

}
