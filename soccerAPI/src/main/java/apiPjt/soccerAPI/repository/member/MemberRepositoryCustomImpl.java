package apiPjt.soccerAPI.repository.member;

import apiPjt.soccerAPI.domain.Member;
import apiPjt.soccerAPI.domain.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.util.List;

import static apiPjt.soccerAPI.domain.QCountry.country;
import static apiPjt.soccerAPI.domain.QClub.club;
import static apiPjt.soccerAPI.domain.QMember.member;
import static apiPjt.soccerAPI.domain.QVictorylist.victorylist;



public class MemberRepositoryCustomImpl implements MemberRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    public MemberRepositoryCustomImpl(EntityManager em){
        this.queryFactory=new JPAQueryFactory(em);
    }

    @Override
    public Member findMember(String name) {
        return queryFactory
                .selectFrom(member).distinct()
                .join(member.club, club).fetchJoin()
                .join(member.country,country).fetchJoin()
                .where(member.member_name.eq(name))
                .fetchOne();
    }
}
