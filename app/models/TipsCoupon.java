package models;

import java.util.*;

import javax.persistence.*;

import controllers.TipsUtil;

import play.db.ebean.Model;


// database support not necessary right now TODO
//@Entity
public class TipsCoupon extends Model{
	
	//@Id
	public Long id; 
	
	public String tipsType;
	
	
	//@OneToMany(cascade = CascadeType.REMOVE)
	public List<Tipsrow> tipsrows = new ArrayList<Tipsrow>();
	
	public TipsCoupon (List<Tipsrow> tipsrows) {
		this.tipsrows = tipsrows;
	}
	
	public TipsCoupon (Scanner scanner){
		// first row in the file determines the type of tips (euro/stryk etc)
		if (scanner.hasNextLine()){
			tipsType = scanner.nextLine();
		}
		while (scanner.hasNextLine()){
			addTipsrow(scanner.nextLine());
		}
	}
	
	public void addTipsrow(String tipsrow){
		tipsrows.add(new Tipsrow(tipsrow));
	}
		
	public int[] correctMatrix (String correctRow){
		System.out.println("correctMatrix");
		// should not be a static value TODO
		int matrix[] = new int[14]; //{0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		if (tipsrows == null) {
			return matrix;
		}
		for (int a = 0; a < tipsrows.size(); a++){
			System.out.println("lev-dist: " + TipsUtil.computeLevenshteinDistance(correctRow, tipsrows.get(a).tipsrow));
			System.out.println(correctRow);
			System.out.println(tipsrows.get(a).tipsrow);
			matrix[TipsUtil.computeLevenshteinDistance(correctRow, tipsrows.get(a).tipsrow)]++;
		}
		for (int a = 0; a < matrix.length; a++){
			System.out.println(matrix[a]);
		}
		return matrix;
	}
	
	public void printOut() {
		System.out.println("Rader för " + tipsType);
		for (int a = 0; a < tipsrows.size(); a++){
			System.out.println(tipsrows.get(a).tipsrow);
		}
	}

}
