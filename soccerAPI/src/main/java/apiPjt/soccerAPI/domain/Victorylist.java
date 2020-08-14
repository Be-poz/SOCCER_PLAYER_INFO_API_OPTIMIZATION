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
@NoArgsConstructor
public class Victorylist {

    @Id @GeneratedValue
    private Long victorylist_id;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="victory_id")
    private Victory victory;

    public void victorySetting(Victory victory) {
        this.victory=victory;
    }

    public void memberSetting(Member member){
        this.member=member;
    }
}
