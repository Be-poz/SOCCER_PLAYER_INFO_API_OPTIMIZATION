package apiPjt.soccerAPI.domain;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class MemberTest {

    @Autowired
    EntityManager em;

    @Test
    public void memberTest() throws Exception{
        //given
        Member member=new Member("kang",23);
        //when
        em.persist(member);
        //then
        assertThat(member.getMember_name()).isEqualTo("kang");
    }
}