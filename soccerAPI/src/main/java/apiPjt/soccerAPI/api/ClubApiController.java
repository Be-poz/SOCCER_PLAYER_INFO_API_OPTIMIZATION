package apiPjt.soccerAPI.api;

import apiPjt.soccerAPI.api.queryDto.ClubQueryDto;
import apiPjt.soccerAPI.domain.Club;
import apiPjt.soccerAPI.domain.Member;
import apiPjt.soccerAPI.domain.Sponsor;
import apiPjt.soccerAPI.domain.dto.ClubsDto;
import apiPjt.soccerAPI.repository.club.ClubRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/clubapi")
public class ClubApiController {
    private final ClubRepository clubRepository;

    /**
     * 페치 조인 이용
     */
    @GetMapping("/v1/clubs")
    public List<ClubsDto> clubsV1() {
        List<Club> clubs = clubRepository.searchAll();
        return clubs.stream()
                .map(o -> new ClubsDto(o))
                .collect(toList());
    }

    /**
     * batch_fetch_size 설정을 통해 페이징 극복
     */
    @GetMapping("/v2/clubs")
    public List<ClubsDto> clubsV2(@RequestParam(value="offset",defaultValue = "0")int offset,
                                  @RequestParam(value="limit",defaultValue = "2")int limit){
        List<Club> clubs=clubRepository.searchAll_paging(offset,limit);
        return clubs.stream()
                .map(o->new ClubsDto(o))
                .collect(toList());
    }

    @GetMapping("/v3/clubs")
    public List<ClubQueryDto> clubsV3() {
        return clubRepository.findClubQueryDtos();
    }

    @GetMapping("/v4/clubs")
    public List<ClubQueryDto> clubsV4(){
        return clubRepository.findClubQueryDtos2();
    }


    @GetMapping("/queryclub")
    public OneClubDto queryclub(@RequestBody String name){
        Club club=clubRepository.findOneClub(name);
        if(club==null) log.info("club null");
        return new OneClubDto(club);
    }


    @Data
    static class OneClubDto{
        private Long club_id;
        private String club_name;
        private List<String> member_name=new ArrayList<>();
        private List<String> sponsor_names=new ArrayList<>();
        public OneClubDto(Club c){
            this.club_id=c.getClub_id();
            this.club_name=c.getClub_name();
            for (Member members : c.getMember()) {
                this.member_name.add(members.getMember_name());
            }
            for (Sponsor sponsors : c.getSponsor()) {
                this.sponsor_names.add(sponsors.getSponsor_name());
            }
        }
    }

}
