package mr.mbti.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Slf4j
public class HomeController {

    HttpSession session;

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

    @PostMapping("/items/{itemId}/delete")
    public String result(@PathVariable("Q03_A") Long itemId, HttpSession session, HttpServletRequest request, HttpServletResponse reponse){
//    @RequestMapping(value = "/result", produces = "application/json;charset=utf8")
//    public String result(HttpSession session, HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {
        session = request.getSession();
        log.info("들어왔단다");

        String result02 = (String)session.getAttribute("Q02_A");
        String result03 = (String)session.getAttribute("Q03_A");
        System.out.println("result = " + result03);
        log.info("값 : " + result03);

        //여기서 결과에 따라 return값이 달라짐
        if(result03 != null) {
            log.info("값이 들어옴");

            if(result03 == "1") {
                log.info("1 : intj");
                return "type/intj";
            } else if(result03 == "2") {
                log.info("2 : estp");
                return "type/estp";
            } else if(result03 == "3") {
                log.info("3 : result");
                return "우하하3";
            } else if(result03 == "4") {
                log.info("4 : result");
                return "우하하4";
            }

            return "result";
        } else {
            return "result";
        }
    }
}

