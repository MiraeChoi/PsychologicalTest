package mr.mbti.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import mr.mbti.entity.UserData;

import javax.persistence.EntityManager;
import java.util.List;

public class UserJpaRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public UserJpaRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public void save(UserData userData) {
        em.persist(userData);
    }
}
