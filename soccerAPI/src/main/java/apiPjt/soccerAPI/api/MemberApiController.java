package apiPjt.soccerAPI.api;

import apiPjt.soccerAPI.domain.Member;
import apiPjt.soccerAPI.domain.Victorylist;
import apiPjt.soccerAPI.repository.member.MemberRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/memberapi")
public class MemberApiController {
    private final MemberRepository memberRepository;

    @GetMapping("/querymember")
    public MemberDto querymember(@RequestBody String name) {
        Member members=memberRepository.findMember(name);
        if(members!=null) log.info("member not null");
        return new MemberDto(members);
    }

    @Data
    static class MemberDto {
        private Long member_id;
        private String member_name;
        private int member_age;
        private String member_position;
        private String country;
        private String club;
        private List<VictoryListDto> victorylist;

        MemberDto(Member member) {
            this.member_id=member.getMember_id();
            this.member_name=member.getMember_name();
            this.member_age=member.getMember_age();
            this.member_position=member.getPosition();
            this.country=member.getCountry().getCountry_name();
            this.club=member.getClub().getClub_name();
            victorylist = member.getVictorylists().stream()
                    .map(o -> new VictoryListDto(o))
                    .collect(Collectors.toList());
        }

    }

    @Data
    static class VictoryListDto {
        private String cup_name;
        private LocalDate year;

        VictoryListDto(Victorylist victorylist) {
            this.cup_name=victorylist.getVictory().getCup_name();
            this.year=victorylist.getVictory().getYear();
        }
    }
}
