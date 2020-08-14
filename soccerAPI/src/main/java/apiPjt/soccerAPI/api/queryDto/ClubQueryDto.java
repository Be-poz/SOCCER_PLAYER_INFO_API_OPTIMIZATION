package apiPjt.soccerAPI.api.queryDto;

import lombok.Data;

import java.util.List;

@Data
public class ClubQueryDto {
    private Long club_id;
    private String club_name;
    private List<SponsorQueryDto> sponsor_names;

    public ClubQueryDto(Long id,String name){
        club_id=id;
        club_name=name;
    }

}
