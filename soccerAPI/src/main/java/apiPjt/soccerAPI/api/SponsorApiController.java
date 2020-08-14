package apiPjt.soccerAPI.api;

import apiPjt.soccerAPI.domain.Sponsor;
import apiPjt.soccerAPI.domain.dto.SponsorDto;
import apiPjt.soccerAPI.repository.sponsor.SponsorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sponsorapi")
public class SponsorApiController {
    private final SponsorRepository sponsorRepository;

    @GetMapping("/sponsors")
    public List<SponsorDto> sponsor_search(){
        List<Sponsor> sponsors = sponsorRepository.findAll();
        return sponsors.stream()
                .map(o->new SponsorDto(o))
                .collect(Collectors.toList());
    }
}
