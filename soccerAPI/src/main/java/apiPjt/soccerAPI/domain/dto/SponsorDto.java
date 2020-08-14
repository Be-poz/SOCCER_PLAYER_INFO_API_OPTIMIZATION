package apiPjt.soccerAPI.domain.dto;

import apiPjt.soccerAPI.domain.Sponsor;
import lombok.Data;

@Data
public class SponsorDto{
    private String sponsor_name;

    public SponsorDto(Sponsor sponsor) {
        this.sponsor_name=sponsor.getSponsor_name();
    }
}