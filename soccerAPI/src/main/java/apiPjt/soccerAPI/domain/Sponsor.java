package apiPjt.soccerAPI.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sponsor {

    @Id @GeneratedValue
    private Long sponsor_id;

    private String sponsor_name;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="club_id")
    private Club club;

    public Sponsor(String sponsor_name) {
        this.sponsor_name = sponsor_name;
    }

    public void clubSetting(Club club){
        this.club=club;
    }
}
