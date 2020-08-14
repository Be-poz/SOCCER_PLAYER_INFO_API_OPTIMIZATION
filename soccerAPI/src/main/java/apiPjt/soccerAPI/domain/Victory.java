package apiPjt.soccerAPI.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Victory {

    @Id @GeneratedValue
    private Long victory_id;

    private LocalDate year;
    private String cup_name;

    public Victory(LocalDate year, String cup_name) {
        this.year = year;
        this.cup_name = cup_name;
    }
}
