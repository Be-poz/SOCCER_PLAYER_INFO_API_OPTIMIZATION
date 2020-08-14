package apiPjt.soccerAPI.repository.club;

import apiPjt.soccerAPI.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club,Long>, ClubRepositoryCustom{
}
