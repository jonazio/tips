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
	
	public void correctMatrix (String correctRow){
		
	}

}
