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
import java.util.*;

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
        UserData userData4 = new UserData("USER000000145728", 1, 3, 2, 4, 1, 3, 2, 4, 1, 3, 2, 4, 1, "ENFP");
        UserData userData5 = new UserData("USER000000145728", 3, 3, 3, 2, 3, 3, 2, 3, 3, 3, 2, 3, 2, "ISTJ");
        UserData userData6 = new UserData("USER000000666666", 1, 4, 1, 4, 1, 4, 1, 4, 1, 4, 1, 4, 1, "ENTJ");
        em.persist(userData1);
        em.persist(userData2);
        em.persist(userData3);
        em.persist(userData4);
        em.persist(userData5);
        em.persist(userData6);

        em.flush();
        em.clear();

        List<UserData> userList = em.createQuery("select u from UserData u", UserData.class)
                .getResultList();
        long userCount = em.createQuery("select COUNT(*) from UserData u", Long.class)
                .getSingleResult();
        //        long infp = em.createQuery("select COUNT(*) from UserData u where result like '%INFP%'", Long.class).getSingleResult();

        Map<Integer, Long> typesCount = new HashMap<>();
        Map<Integer, String> typesMap = new HashMap<>();

        long estpCount = 0, estjCount = 0, esfpCount = 0, esfjCount = 0, entpCount = 0, entjCount = 0, enfpCount = 0, enfjCount = 0;
        long istpCount = 0, istjCount = 0, isfpCount = 0, isfjCount = 0, intpCount = 0, intjCount = 0, infpCount = 0, infjCount = 0;

        for(int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getResult().equals("ESTP")) estpCount++;
            if (userList.get(i).getResult().equals("ESTJ")) estjCount++;
            if (userList.get(i).getResult().equals("ESFP")) esfpCount++;
            if (userList.get(i).getResult().equals("ESFJ")) esfjCount++;
            if (userList.get(i).getResult().equals("ENTP")) entpCount++;
            if (userList.get(i).getResult().equals("ENTJ")) entjCount++;
            if (userList.get(i).getResult().equals("ENFP")) enfpCount++;
            if (userList.get(i).getResult().equals("ENFJ")) enfjCount++;

            if (userList.get(i).getResult().equals("ISTP")) istpCount++;
            if (userList.get(i).getResult().equals("ISTJ")) istjCount++;
            if (userList.get(i).getResult().equals("ISFP")) isfpCount++;
            if (userList.get(i).getResult().equals("ISFJ")) isfjCount++;
            if (userList.get(i).getResult().equals("INTP")) intpCount++;
            if (userList.get(i).getResult().equals("INTJ")) intjCount++;
            if (userList.get(i).getResult().equals("INFP")) infpCount++;
            if (userList.get(i).getResult().equals("INFJ")) infjCount++;
        }

        typesCount.put(0, estpCount);
        typesCount.put(1, estjCount);
        typesCount.put(2, esfpCount);
        typesCount.put(3, esfjCount);
        typesCount.put(4, entpCount);
        typesCount.put(5, entjCount);
        typesCount.put(6, enfpCount);
        typesCount.put(7, enfjCount);

        typesCount.put(8, istpCount);
        typesCount.put(9, istjCount);
        typesCount.put(10, isfpCount);
        typesCount.put(11, isfjCount);
        typesCount.put(12, intpCount);
        typesCount.put(13, intjCount);
        typesCount.put(14, infpCount);
        typesCount.put(15, infjCount);

        typesMap.put(0, "ESTP");
        typesMap.put(1, "ESTJ");
        typesMap.put(2, "ESFP");
        typesMap.put(3, "ESFJ");
        typesMap.put(4, "ENTP");
        typesMap.put(5, "ENTJ");
        typesMap.put(6, "ENFP");
        typesMap.put(7, "ENFJ");

        typesMap.put(8, "ISTP");
        typesMap.put(9, "ISTJ");
        typesMap.put(10, "ISFP");
        typesMap.put(11, "ISFJ");
        typesMap.put(12, "INTP");
        typesMap.put(13, "INTJ");
        typesMap.put(14, "INFP");
        typesMap.put(15, "INFJ");

        long temp1 = Long.MIN_VALUE, temp2 = Long.MIN_VALUE, temp3 = Long.MIN_VALUE;
        String gold = "", silver = "", bronze = "";
        int[] rank = new int[16];

        for(int i = 0; i < typesCount.size(); i++) {
            rank[i] = 0;

            for(int j = 0; j < typesCount.size(); j++) {
                if(typesCount.get(i) < typesCount.get(j)) rank[i]++;
                else if(typesCount.get(i) == typesCount.get(j)) rank[j]++;
            }
        }

        for(int i = 0; i < typesCount.size(); i++) {
            log.info(typesCount.get(i) + " / " + rank[i] + "등");
        }

        int first = 0, second = 0, third = 0;
        log.info("typesCount.size() : " + typesCount.size());

        for(int i = 0; i < typesCount.size(); i++) {
            if(rank[i] == 1) {
                first = i;
            } else if(rank[i] == 2) {
                second = i;
            } else if(rank[i] == 3) {
                third = i;
            }
        }

        log.info("first : " + first);
        log.info("second : " + second);
        log.info("third : " + third);

        gold = typesMap.get(first);
        silver = typesMap.get(second);
        bronze = typesMap.get(third);

        log.info("gold : " + gold);
        log.info("silver : " + silver);
        log.info("bronze : " + bronze);

        DecimalFormat form = new DecimalFormat("#.##");

        model.addAttribute("userCount", userCount);
        model.addAttribute("estp", form.format((float)(estpCount)/userCount * 100));
        model.addAttribute("estj", form.format((float)(estjCount)/userCount * 100));
        model.addAttribute("esfp", form.format((float)(esfpCount)/userCount * 100));
        model.addAttribute("esfj", form.format((float)(esfjCount)/userCount * 100));
        model.addAttribute("entp", form.format((float)(entpCount)/userCount * 100));
        model.addAttribute("entj", form.format((float)(entjCount)/userCount * 100));
        model.addAttribute("enfp", form.format((float)(enfpCount)/userCount * 100));
        model.addAttribute("enfj", form.format((float)(enfjCount)/userCount * 100));

        model.addAttribute("istp", form.format((float)(istpCount)/userCount * 100));
        model.addAttribute("istj", form.format((float)(istjCount)/userCount * 100));
        model.addAttribute("isfp", form.format((float)(isfpCount)/userCount * 100));
        model.addAttribute("isfj", form.format((float)(isfjCount)/userCount * 100));
        model.addAttribute("intp", form.format((float)(intpCount)/userCount * 100));
        model.addAttribute("intj", form.format((float)(intjCount)/userCount * 100));
        model.addAttribute("infp", form.format((float)(infpCount)/userCount * 100));
        model.addAttribute("infj", form.format((float)(infjCount)/userCount * 100));

        model.addAttribute("gold", gold);
        model.addAttribute("silver", silver);
        model.addAttribute("bronze", bronze);

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

