package mr.mbti.controller;

import com.querydsl.core.QueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mr.mbti.entity.UserData;
import mr.mbti.repository.UserJpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Random;

@RestController
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    HttpSession session;
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

    @RequestMapping("/types/INTJ")
    public String typesINTJ() {
        return "type/intj";
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

    @RequestMapping(value = "/result", produces = "application/json;charset=utf8")
    public String result(int q1, int q2, int q3) {
        log.info("result");
        String username = "U000" + String.format("%10d", new Random().nextInt(Integer.MAX_VALUE - 0 + 1));

        UserData userData = new UserData(username, q1, q2, q3);
        userJpaRepository.save(userData);

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

