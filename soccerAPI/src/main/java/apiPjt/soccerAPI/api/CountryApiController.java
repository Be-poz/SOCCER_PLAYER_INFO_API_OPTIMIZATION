package apiPjt.soccerAPI.api;

import apiPjt.soccerAPI.api.queryDto.CountryQueryDto;
import apiPjt.soccerAPI.domain.Country;
import apiPjt.soccerAPI.domain.Member;
import apiPjt.soccerAPI.domain.dto.CountrysDto;
import apiPjt.soccerAPI.repository.country.CountryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/countryapi")
public class CountryApiController {
    private final CountryRepository countryRepository;

    /**
     * 페치 조인 최적화
     */
    @GetMapping("/v1/countries")
    public List<CountrysDto> countrysV1(){
        List<Country> countrys = countryRepository.findAll();
        return countrys.stream()
                .map(o->new CountrysDto(o))
                .collect(Collectors.toList());
    }

    @GetMapping("/v2/countries")
    public List<CountryQueryDto> countrysV2(){
        return countryRepository.findCountryQueryDtos();
    }

    @GetMapping("/querycountry")
    public OneCountryDto oneCountryDto(@RequestBody String country_name){
        Country findCountry = countryRepository.searchOneCountry(country_name);
        return new OneCountryDto(findCountry);
    }

    @Data
    private class OneCountryDto {
        private Long country_id;
        private String country_name;
        private List<MemberDto> members;

        public OneCountryDto(Country country) {
            this.country_id = country.getCountry_id();
            this.country_name = country.getCountry_name();
            members=country.getMember().stream()
                    .map(o->new MemberDto(o))
                    .collect(Collectors.toList());
        }
    }

    @Data
    private class MemberDto {
        private Long member_id;
        private String member_name;
        private String member_position;
        private String club_name;

        public MemberDto(Member member) {
            this.member_id=member.getMember_id();
            this.member_name=member.getMember_name();
            this.member_position=member.getPosition();
            this.club_name=member.getClub().getClub_name();
        }
    }
}
