package apiPjt.soccerAPI.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Club {

    @Id @GeneratedValue
    private Long club_id;

    private String club_name;

    @OneToMany(mappedBy = "club")
    private List<Member> member = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<Sponsor> sponsor = new ArrayList<>();


    public Club(String club_name) {
        this.club_name = club_name;
    }

    public void sponsorSetting(Sponsor sponsor){
        this.sponsor.add(sponsor);
        sponsor.clubSetting(this);
    }
}
