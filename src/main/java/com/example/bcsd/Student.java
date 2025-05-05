package com.example.bcsd;

public class Student {
    private String name;
    private int age;
    private int id;

    public Student(String name, int age, int id) { // 생성자
        this.name = name;
        this.age = age;
        this.id = id;
    }

    // getter,setter 설정
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
