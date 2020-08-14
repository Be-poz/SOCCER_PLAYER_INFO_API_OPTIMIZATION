package apiPjt.soccerAPI.repository.club;

import apiPjt.soccerAPI.api.queryDto.ClubQueryDto;
import apiPjt.soccerAPI.domain.Club;

import java.util.List;

public interface ClubRepositoryCustom {
    List<Club> searchAll();

    List<Club> searchAll_paging(int offset,int limit);

    List<ClubQueryDto> findClubQueryDtos();

    List<ClubQueryDto> findClubQueryDtos2();

    Club findOneClub(String name);
}
