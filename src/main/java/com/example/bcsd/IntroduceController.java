package com.example.bcsd;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntroduceController {

//    @GetMapping("/introduce")
//    public String showIntroducePage() {
//        return "안녕하세요 제 이름은 길현우 입니다.";
//    }

    @GetMapping("/introduce")
    public String introduce(@RequestParam String name) {
        return "안녕하세요 제 이름은 " + name + "입니다.";
    }

    @GetMapping("/json")
    public User getJson() {
        return new User("길현우", 24);
    }
}

