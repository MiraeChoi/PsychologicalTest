package mr.mbti.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping({"/", "main"})
    public String test1() {
        return "hihi 한글도 잘 나오나";
    }
}
