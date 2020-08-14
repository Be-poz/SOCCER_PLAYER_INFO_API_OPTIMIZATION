package apiPjt.soccerAPI.repository.country;

import apiPjt.soccerAPI.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long>, CountryRepositoryCustom {
}
