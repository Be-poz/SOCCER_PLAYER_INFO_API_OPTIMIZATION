package apiPjt.soccerAPI.api.queryDto;

import apiPjt.soccerAPI.domain.Sponsor;
import lombok.Data;

@Data
public class SponsorQueryDto {
    private String sponsor_name;

    public SponsorQueryDto(String sponsor_name){
        this.sponsor_name=sponsor_name;
    }
}
