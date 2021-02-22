package mr.mbti.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class UserDataTest {

    @Autowired
    EntityManager em;

    @Test
    public void testUserData() {
        UserData userData1 = new UserData("농담곰", 1, 2, 3);
        UserData userData2 = new UserData("농담곰", 1, 2, 3);
        em.persist(userData1);
        em.persist(userData2);

        em.flush();
        em.clear();

        List<UserData> userResults = em.createQuery("select u from UserData u", UserData.class)
                .getResultList();

        for (UserData userResult : userResults) {
            System.out.println("userResult = " + userResult);
        }
    }

}