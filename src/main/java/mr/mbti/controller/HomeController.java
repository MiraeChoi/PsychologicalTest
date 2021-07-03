package mr.mbti.controller;

import com.querydsl.core.QueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mr.mbti.entity.UserData;
import mr.mbti.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@Transactional
public class HomeController {

    private final UserJpaRepository userJpaRepository;
    QueryFactory queryFactory;

    @Autowired
    EntityManager em;

    @RequestMapping("/")
    public String home() {
        log.info("Main View");
        return "home";
    }

    @RequestMapping("/types")
    public String types(Model model) {
        log.info("전체 유형 보기");

        UserData userData1 = new UserData("USER000000234234", 2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 2, 1, 2, "INFP");
        UserData userData2 = new UserData("USER000000123456", 1, 2, 3, 4, 2, 1, 2, 3, 4, 3, 2, 1, 1, "INFP");
        UserData userData3 = new UserData("USER000000654321", 4, 3, 2, 1, 4, 3, 2, 1, 4, 3, 2, 1, 1, "ENTJ");
        em.persist(userData1);
        em.persist(userData2);
        em.persist(userData3);

        em.flush();
        em.clear();

        List<UserData> userList = em.createQuery("select u from UserData u", UserData.class)
                .getResultList();
        long userCount = em.createQuery("select COUNT(*) from UserData u", Long.class)
                .getSingleResult();
//        em.createQuery("select result from UserData u where username like '%INFP%' group by result", String.class);
        long infp = em.createQuery("select COUNT(*) from UserData u where result like '%INFP%'", Long.class)
                .getSingleResult();
        long entj = em.createQuery("select COUNT(*) from UserData u where result like '%ENTJ%'", Long.class)
                .getSingleResult();

        DecimalFormat form = new DecimalFormat("#.##");
        float infp2 = infp/userCount;
        float entj2 = entj/userCount;

        model.addAttribute("userCount", userCount);
        model.addAttribute("infp", form.format(infp2));
        model.addAttribute("entj", form.format(entj2));
        log.info("infp : " + infp2);
        log.info("entj : " + entj2);

        return "types";
    }

    @RequestMapping(value = "/types/ESTP", produces = "application/json;charset=utf8")
    public String typesESTP() {
        return "types/estp";
    }
    @RequestMapping(value = "/types/ESTJ", produces = "application/json;charset=utf8")
    public String typesESTJ() {
        return "types/estj";
    }
    @RequestMapping(value = "/types/ESFP", produces = "application/json;charset=utf8")
    public String typesESFP() {
        return "types/esfp";
    }
    @RequestMapping(value = "/types/ESFJ", produces = "application/json;charset=utf8")
    public String typesESFJ() {
        return "types/esfj";
    }
    @RequestMapping(value = "/types/ENFP", produces = "application/json;charset=utf8")
    public String typesENFP() {
        return "types/enfp";
    }
    @RequestMapping(value = "/types/ENFJ", produces = "application/json;charset=utf8")
    public String typesENFJ() {
        return "types/enfj";
    }
    @RequestMapping(value = "/types/ENTP", produces = "application/json;charset=utf8")
    public String typesENTP() {
        return "types/entp";
    }
    @RequestMapping(value = "/types/ENTJ", produces = "application/json;charset=utf8")
    public String typesENTJ() {
        return "types/entj";
    }

    @RequestMapping(value = "/types/ISTJ", produces = "application/json;charset=utf8")
    public String typesISTJ() {
        return "types/istj";
    }
    @RequestMapping(value = "/types/ISTP", produces = "application/json;charset=utf8")
    public String typesISTP() {
        return "types/istp";
    }
    @RequestMapping(value = "/types/ISFJ", produces = "application/json;charset=utf8")
    public String typesISFJ() {
        return "types/isfj";
    }
    @RequestMapping(value = "/types/ISFP", produces = "application/json;charset=utf8")
    public String typesISFP() {
        return "types/isfp";
    }
    @RequestMapping(value = "/types/INFJ", produces = "application/json;charset=utf8")
    public String typesINFJ() {
        return "types/infj";
    }
    @RequestMapping(value = "/types/INFP", produces = "application/json;charset=utf8")
    public String typesINFP() {
        return "types/infp";
    }
    @RequestMapping(value = "/types/INTJ", produces = "application/json;charset=utf8")
    public String typesINTJ() {
        return "types/intj";
    }
    @RequestMapping(value = "/types/INTP", produces = "application/json;charset=utf8")
    public String typesINTP() {
        return "types/intp";
    }

    @RequestMapping(value = "/test1", produces = "application/json;charset=utf8")
    public String test1() {
        log.info("Q1");
        return "question/q01";
    }
    @RequestMapping(value = "/test2", produces = "application/json;charset=utf8")
    public String test2() {
        log.info("Q2");
        return "question/q02";
    }
    @RequestMapping(value = "/test3", produces = "application/json;charset=utf8")
    public String test3() {
        log.info("Q3");
        return "question/q03";
    }
    @RequestMapping(value = "/test4", produces = "application/json;charset=utf8")
    public String test4() {
        log.info("Q4");
        return "question/q04";
    }
    @RequestMapping(value = "/test5", produces = "application/json;charset=utf8")
    public String test5() {
        log.info("Q5");
        return "question/q05";
    }
    @RequestMapping(value = "/test6", produces = "application/json;charset=utf8")
    public String test6() {
        log.info("Q6");
        return "question/q06";
    }
    @RequestMapping(value = "/test7", produces = "application/json;charset=utf8")
    public String test7() {
        log.info("Q7");
        return "question/q07";
    }
    @RequestMapping(value = "/test8", produces = "application/json;charset=utf8")
    public String test8() {
        log.info("Q8");
        return "question/q08";
    }
    @RequestMapping(value = "/test9", produces = "application/json;charset=utf8")
    public String test9() {
        log.info("Q9");
        return "question/q09";
    }
    @RequestMapping(value = "/test10", produces = "application/json;charset=utf8")
    public String test10() {
        log.info("Q10");
        return "question/q10";
    }
    @RequestMapping(value = "/test11", produces = "application/json;charset=utf8")
    public String test11() {
        log.info("Q11");
        return "question/q11";
    }
    @RequestMapping(value = "/test12", produces = "application/json;charset=utf8")
    public String test12() {
        log.info("Q12");
        return "question/q12";
    }
    @RequestMapping(value = "/test13", produces = "application/json;charset=utf8")
    public String test13() {
        log.info("Q13");
        return "question/q13";
    }

    @RequestMapping(value = "/result", produces = "application/json;charset=utf8")
    public String result(HttpServletRequest request) {
        String result = request.getParameter("type");

//        int q1 = Integer.parseInt(request.getParameter("q1"));
        int q1 = 0, q2 = 0, q3 = 0, q4 = 0, q5 = 0, q6 = 0, q7 = 0, q8 = 0, q9 = 0, q10 = 0, q11 = 0, q12 = 0, q13 = 0;

        String ip = request.getRemoteAddr();
        ip = ip.replaceAll(":", "");
        int realIp = Integer.parseInt(ip);
        log.info("클라이언트 IP 주소: " + realIp);

//        String username = "USER" + String.format("%05d", new Random().nextInt(99999 - 0 + 1));
        String username = "USER" + String.format("%012d", realIp);

        UserData userData = new UserData(username, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, result);
        userJpaRepository.save(userData);

        log.info(userData.getUsername());

        return "types/" + result;
    }
}

