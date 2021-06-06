package mr.mbti.controller;

import com.querydsl.core.QueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mr.mbti.entity.UserData;
import mr.mbti.repository.UserJpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final UserJpaRepository userJpaRepository;
    QueryFactory queryFactory;

    @RequestMapping("/")
    public String home() {
        log.info("Home Controller!!! 와아앙ㅇ");
        return "home";
    }

    @RequestMapping("/types")
    public String types() {
        log.info("전체 유형 보기");
        return "types";
    }

    @RequestMapping("/types/ESTP")
    public String typesESTP() {
        return "type/estp";
    }
    @RequestMapping("/types/ESTJ")
    public String typesESTJ() {
        return "type/estj";
    }
    @RequestMapping("/types/ESFP")
    public String typesESFP() {
        return "type/esfp";
    }
    @RequestMapping("/types/ESFJ")
    public String typesESFJ() {
        return "type/esfj";
    }
    @RequestMapping("/types/ENFP")
    public String typesENFP() {
        return "type/enfp";
    }
    @RequestMapping("/types/ENFJ")
    public String typesENFJ() {
        return "type/enfj";
    }

    @RequestMapping("/types/ISTJ")
    public String typesISTJ() {
        return "type/istj";
    }
    @RequestMapping("/types/INFJ")
    public String typesINFJ() {
        return "type/infj";
    }
    @RequestMapping("/types/INFP")
    public String typesINFP() {
        return "type/infp";
    }
    @RequestMapping("/types/INTJ")
    public String typesINTJ() {
        return "type/intj";
    }
    @RequestMapping("/types/INTP")
    public String typesINTP() {
        return "type/intp";
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
        int q1 = Integer.parseInt(request.getParameter("q1"));
        int q2 = Integer.parseInt(request.getParameter("q2"));
        int q3 = Integer.parseInt(request.getParameter("q3"));
        int q4 = 0, q5 = 0, q6 = 0, q7 = 0, q8 = 0, q9 = 0, q10 = 0, q11 = 0, q12 = 0, q13 = 0;

        log.info("result :" + q1 + "/" + q2 + "/" + q3);
        String ip = request.getRemoteAddr();
        ip = ip.replaceAll(":", "");
        int realIp = Integer.parseInt(ip);
        log.info("클라이언트 IP 주소: " + realIp);

//        String username = "USER" + String.format("%05d", new Random().nextInt(99999 - 0 + 1));
        String username = "USER" + String.format("%012d", realIp);

        UserData userData = new UserData(username, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13);
        userJpaRepository.save(userData);

        log.info(userData.getUsername());

        if(q3 == 1 || q3 == 2) return "type/estp";
        else if(q3 == 3 || q3 == 4) return "type/intj";

        return "result";
    }

    /*
//    @PostMapping("/result/{result}")
//    public String result(@PathVariable("Q03_A") String result, HttpSession session, HttpServletRequest request, HttpServletResponse reponse){
    @RequestMapping(value = "/result/{result}", produces = "application/json;charset=utf8")
    public String result(@PathVariable("Q03_A") Object result, HttpSession session, HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {
        session = request.getSession();
        log.info("들어왔단다");

        String result02 = (String)session.getAttribute("Q02_A");
        Object result1 = session.getAttribute("Q02_A");
        String result03 = (String)session.getAttribute("Q03_A");
        System.out.println("result = " + result1);
        log.info("값 : " + result1);

        //여기서 결과에 따라 return값이 달라짐
        if(result03 != null) {
            log.info("값이 들어옴");

            if(result == "1") {
                log.info("1 : intj");
                return "type/intj";
            } else if(result == "2") {
                log.info("2 : estp");
                return "type/estp";
            } else if(result == "3") {
                log.info("3 : result");
                return "우하하3";
            } else if(result == "4") {
                log.info("4 : result");
                return "우하하4";
            }

            return "result";
        } else {
            return "result";
        }
    }
     */
}

