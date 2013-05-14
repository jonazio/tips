package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.Model;

@Entity
public class TipsCoupon extends Model{
	
	@Id
	public Long id; 
	
	@OneToMany(cascade = CascadeType.REMOVE)
	public List<Tipsrow> tipsrows = new ArrayList<Tipsrow>();
	
	public TipsCoupon (List<Tipsrow> tipsrows) {
		this.tipsrows = tipsrows;
	}
	
	public TipsCoupon (Scanner scanner){
		while (scanner.hasNextLine()){
			addTipsrow("test");
		}
	}
	
	public void addTipsrow(String tipsrow){
		tipsrows.add(new Tipsrow(tipsrow));
	}
	
	public void correctMatrix (String correctRow){
		
	}

}
