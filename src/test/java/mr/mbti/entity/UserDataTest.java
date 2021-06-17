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
    public void testUserData(UserData userData) {
        UserData userData1 = new UserData(
                userData.getUsername(), userData.getQ01(), userData.getQ02(), userData.getQ03(),
                userData.getQ04(), userData.getQ05(), userData.getQ06(),
                userData.getQ07(), userData.getQ08(), userData.getQ09(),
                userData.getQ10(), userData.getQ11(), userData.getQ12(), userData.getQ13(), "result"
                );
//        UserData userData2 = new UserData("말레이곰", 2, 4, 1);
        em.persist(userData1);
//        em.persist(userData2);

        em.flush();
        em.clear();

        List<UserData> userResults = em.createQuery("select u from UserData u", UserData.class)
                .getResultList();

        for (UserData userResult : userResults) {
            System.out.println("userResult = " + userResult);
        }
    }

    @Test
    public void resultData() {

    }

}

