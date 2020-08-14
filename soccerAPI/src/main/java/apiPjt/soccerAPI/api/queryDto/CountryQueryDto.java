package apiPjt.soccerAPI.api.queryDto;

import apiPjt.soccerAPI.domain.Country;
import lombok.Data;

@Data
public class CountryQueryDto {
    private Long country_id;
    private String country_name;

    public CountryQueryDto(Long id,String name) {
        country_id=id;
        country_name=name;
    }
}
