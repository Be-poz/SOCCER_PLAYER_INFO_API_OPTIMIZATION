package apiPjt.soccerAPI.repository.club;

import apiPjt.soccerAPI.api.queryDto.ClubQueryDto;
import apiPjt.soccerAPI.api.queryDto.SponsorQueryDto;
import apiPjt.soccerAPI.domain.Club;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static apiPjt.soccerAPI.domain.QClub.club;
import static apiPjt.soccerAPI.domain.QMember.member;
import static apiPjt.soccerAPI.domain.QSponsor.sponsor;


public class ClubRepositoryCustomImpl implements ClubRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    public ClubRepositoryCustomImpl(EntityManager em){
        this.queryFactory=new JPAQueryFactory(em);
    }

    /**
     * sponsor는 join 되어있지 않았다. 컬렉션 페치 조인은 1개만 사용해야 한다. 둘 이상 사용시에 데이터가 부정합하게 조회돌 수 있다.
     * 2개 이상 사용 시 컴파일하면서 오류를 내준다.
     * distinct 사용하지 않으면 일대다 매핑이 되기 때문에 결과가 뻥튀기 된다. 그래서 같은 club 이 중복으로 나와버린다. 이를 위해 club에
     * distinct를 붙여서 중복을 걸러준다. 사실 sql 안에서는 distinct는 모든 행의 값이 다 같아야 중복으로 처리하기 때문에 결과가 같다.
     * 하지만, jpa 넘어오면서 알아서 distinct를 건 곳의 id를 통해 중복제거를 해주기 때문에 사용가능하다.
     */
    @Override
    public List<Club> searchAll() {
        return queryFactory
                .selectFrom(club).distinct()
                .join(club.sponsor,sponsor).fetchJoin()
                .fetch();
    }

    @Override
    public List<Club> searchAll_paging(int offset,int limit) {
        return queryFactory.selectFrom(club)
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Override
    public List<ClubQueryDto> findClubQueryDtos() {
        List<ClubQueryDto> result=findClubs();
        result.forEach(o->{
            List<SponsorQueryDto> sponsor_name=findSponsor_name(o.getClub_id());
            o.setSponsor_names(sponsor_name);
        });
        return result;
    }

    @Override
    public List<ClubQueryDto> findClubQueryDtos2() {
        List<ClubQueryDto> result = findClubs();

        List<Long> club_ids=result.stream()
                .map(o->o.getClub_id())
                .collect(Collectors.toList());

        List<SponsorQueryDto> withsponsor = queryFactory
                .select(Projections.constructor(SponsorQueryDto.class,
                        sponsor.sponsor_name))
                .from(club)
                .join(club.sponsor, sponsor)
                .where(club.club_id.in(club_ids))
                .fetch();

        Map<String, List<SponsorQueryDto>> collect = withsponsor.stream()
                .collect(Collectors.groupingBy(SponsorQueryDto::getSponsor_name));
        result.forEach(o -> o.setSponsor_names(collect.get(o.getClub_id())));
        return result;
    }

    @Override
    public Club findOneClub(String name) {
        return queryFactory
                .selectFrom(club)
                .join(club.member,member).fetchJoin()
                .where(club.club_name.eq(name))
                .fetchOne();
    }

    private List<ClubQueryDto> findClubs() {
        return queryFactory
                .select(Projections.constructor(ClubQueryDto.class,
                        club.club_id,club.club_name))
                .from(club)
                .fetch();
    }

    private List<SponsorQueryDto> findSponsor_name(Long id){
        return queryFactory
                .select(Projections.constructor(SponsorQueryDto.class,
                        sponsor.sponsor_name))
                .from(club)
                .join(club.sponsor,sponsor)
                .where(club.club_id.eq(id))
                .fetch();
    }
}
