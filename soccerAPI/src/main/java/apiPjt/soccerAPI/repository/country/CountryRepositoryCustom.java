package apiPjt.soccerAPI.repository.country;

import apiPjt.soccerAPI.api.queryDto.CountryQueryDto;
import apiPjt.soccerAPI.domain.Country;

import java.util.List;

public interface CountryRepositoryCustom{
    Country searchOneCountry(String country_name);

    List<CountryQueryDto> findCountryQueryDtos();
}
