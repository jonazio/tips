package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name="Tipscoupon")
@Entity public class TipsCoupon extends Model {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="tips_seq")
    @SequenceGenerator(name="tips_seq", sequenceName="tipscoupon_seq", allocationSize=21)
    @Column(name="tips_id")
    public Long id;

    @Column(name="product_type")
    public Long productType;

    @Column(name="product_id")
    public Long productId;

    @Column(name="start_date")
    public Date startDate;

    @Column(name="deadline_date")
    public Date deadlineDate;

    @Column(name="week")
    public Long week;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "tipsCoupon")
    public List<TipsCouponMatches> matches;

    public static Finder<Long, TipsCoupon> find = new Finder(Long.class, TipsCoupon.class);

    public static TipsCoupon find (Long id){
        return find.byId(id);
    }

    public static void create (TipsCoupon tipsCoupon){
        tipsCoupon.save();
    }

    public static void update(Long tipsId, TipsCoupon tipsCoupon){
        tipsCoupon.update(tipsId, tipsCoupon);
    }

    public static void delete(Long id){
        find.ref(id).delete(id);
    }

}
