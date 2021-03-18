package mr.mbti.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

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
        return "estp";
    }

    @RequestMapping("/types/INTJ")
    public String typesINTJ() {
        return "intj";
    }

    @RequestMapping(value = "/test", produces = "application/json;charset=utf8")
    public String test() {
        log.info("테스트 중이염");
        return "question";
    }
}

