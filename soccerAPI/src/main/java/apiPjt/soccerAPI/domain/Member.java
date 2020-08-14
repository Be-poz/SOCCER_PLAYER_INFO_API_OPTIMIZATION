package apiPjt.soccerAPI.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    private Long member_id;

    private String member_name;
    private int member_age;
    private String position;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="country_id")
    private Country country;


    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="club_id")
    private Club club;

    @OneToMany(mappedBy = "member")
    private List<Victorylist> victorylists=new ArrayList<>();



    public Member(String member_name, int member_age,String position) {
        this.member_name = member_name;
        this.member_age = member_age;
        this.position=position;
    }

    public void clubSetting(Club club){
        this.club=club;
        club.getMember().add(this);
    }

    public void countrySetting(Country country) {
        this.country=country;
        country.getMember().add(this);
    }

    public void victorylistSetting(Victorylist victorylist) {
        victorylists.add(victorylist);
        victorylist.memberSetting(this);
    }
}
