package apiPjt.soccerAPI.domain.dto;

import apiPjt.soccerAPI.domain.Country;
import lombok.Data;

@Data
public class CountrysDto {
    private Long country_id;
    private String country_name;

    public CountrysDto(Country country) {
        this.country_id = country.getCountry_id();
        this.country_name = country.getCountry_name();
    }
}
