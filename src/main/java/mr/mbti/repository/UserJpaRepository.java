package mr.mbti.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import mr.mbti.entity.UserData;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
public class UserJpaRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public UserJpaRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public void save(UserData userData) {
        em.persist(userData);
        log.info("저장" + userData.getUsername());
    }
}
