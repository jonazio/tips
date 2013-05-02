package models;

public class Tipsrow {
	
	public String tipsrad;
	
	public Tipsrow(String tipsrad){
		this.tipsrad = tipsrad;		
	}

	public int countMatchingCharacters (String tipsrad){
		int count = 0;
		if (tipsrad.length() != this.tipsrad.length()) {
			return 0;
		}
		for (int a = 0; a < this.tipsrad.length(); a++){
			if (this.tipsrad.charAt(a) == tipsrad.charAt(a)){
				count++;
			}
		}
		return count;
	}
	
	public boolean compare(String tipsrad) {
		if (this.tipsrad.equals(tipsrad)) {
			return true;
		} else {
			return false;
		}		
	}

}
