package com.example.bcsd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntroduceJohnson {

    @GetMapping("/json")
    public Person getJson() {
        return new Person("임현희", 22);
    }

    static class Person {
        private String name;
        private int age;

        public Person() {}

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
