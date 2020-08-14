package apiPjt.soccerAPI.repository.member;

import apiPjt.soccerAPI.domain.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    Member findMember(String name);
}
