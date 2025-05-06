package com.example.bcsd;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/json")
    @JsonView(User.WithAgeView.class)
    public User getUser() {
        return new User(22, "서유정");
    }


}
