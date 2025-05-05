package com.example.bcsd;
public class Characteristic {
    private int age;
    private String name;
    public Characteristic() {}
    public Characteristic(int age, String name) { this.age = age; this.name = name; }
    public int getAge() { return age; }
    public String getName() { return name; }
    public void setAge(int age) { this.age = age; }
    public void setName(String name) { this.name = name; }
}