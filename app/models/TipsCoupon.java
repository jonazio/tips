package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.Model;


// database support not necessary right now TODO
//@Entity
public class TipsCoupon extends Model{
	
	//@Id
	public Long id; 
	
	//@OneToMany(cascade = CascadeType.REMOVE)
	public List<Tipsrow> tipsrows = new ArrayList<Tipsrow>();
	
	public TipsCoupon (List<Tipsrow> tipsrows) {
		this.tipsrows = tipsrows;
	}
	
	public TipsCoupon (Scanner scanner){
		while (scanner.hasNextLine()){
			addTipsrow(scanner.nextLine());
		}
	}
	
	public void addTipsrow(String tipsrow){
		tipsrows.add(new Tipsrow(tipsrow));
	}
	
	public void correctMatrix (String correctRow){
		
	}
	
	public void printOut() {
		for (int a = 0; a < tipsrows.size(); a++){
			System.out.println(tipsrows.get(a).tipsrow);
		}
	}

}
