package models;

import controllers.Tips;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import javax.persistence.*;
import java.security.Timestamp;

@Table(name="TipsCoupon_Matches")
@Entity
public class TipsCouponMatches extends Model {

    @Id
    @Column(name="match_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="match_id_seq")
    @SequenceGenerator(name="match_id_seq", sequenceName="match_id_seq", allocationSize=21)
    public long matchId;

    @Required
    @Column(name="tips_id")
    public long tipsId;

    @Column(name="match_no")
    public long matchNo;

    @Column(name="league_id")
    public long leagueId;

    @Column(name="home_team")
    public String homeTeam;

    @Column(name="away_team")
    public String awayTeam;

    @Column(name="match_start")
    public Timestamp matchStart;

    @ManyToOne
    @JoinColumn(name="tips_id")
    public TipsCoupon tipsCoupon;
}
