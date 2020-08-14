package apiPjt.soccerAPI.domain.dto;

import apiPjt.soccerAPI.domain.Club;
import lombok.Data;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
public class ClubsDto {
    private Long club_id;
    private String club_name;
    private List<SponsorDto> sponsors;

    public ClubsDto(Club c) {
        club_id=c.getClub_id();
        club_name=c.getClub_name();
        sponsors=c.getSponsor().stream()
                .map(sponsor->new SponsorDto(sponsor))
                .collect(toList());
    }
}