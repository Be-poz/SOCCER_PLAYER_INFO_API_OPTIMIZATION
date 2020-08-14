package apiPjt.soccerAPI.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Country {
    @Id @GeneratedValue
    private Long country_id;

    private String country_name;

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    List<Member> member = new ArrayList<>();

    public Country(String country_name) {
        this.country_name = country_name;
    }
}
