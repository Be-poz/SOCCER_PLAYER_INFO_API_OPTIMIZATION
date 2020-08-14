package apiPjt.soccerAPI.repository.country;

import apiPjt.soccerAPI.api.queryDto.CountryQueryDto;
import apiPjt.soccerAPI.domain.Country;
import apiPjt.soccerAPI.domain.QCountry;
import apiPjt.soccerAPI.domain.QMember;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.util.List;

import static apiPjt.soccerAPI.domain.QCountry.country;
import static apiPjt.soccerAPI.domain.QMember.member;

public class CountryRepositoryCustomImpl implements CountryRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    public CountryRepositoryCustomImpl(EntityManager entityManager) {
        jpaQueryFactory=new JPAQueryFactory(entityManager);
    }

    @Override
    public Country searchOneCountry(String country_name) {
        Country findCountry = jpaQueryFactory
                .selectFrom(country)
                .join(country.member, member).fetchJoin()
                .where(country.country_name.eq(country_name))
                .fetchOne();
        return findCountry;
    }

    @Override
    public List<CountryQueryDto> findCountryQueryDtos() {
        return jpaQueryFactory
                .select(Projections.constructor(CountryQueryDto.class,
                        country.country_id, country.country_name))
                .from(country)
                .fetch();
    }
}
