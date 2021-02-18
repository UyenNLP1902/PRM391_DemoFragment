package uyennlp.demo.demofragment.dtos;

import java.io.Serializable;

public class StudentDTO implements Serializable {
    private static final int TEMP_ID = -1;
    private int id;
    private String name;
    private int age;

    public StudentDTO(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public StudentDTO(String name, int age) {
        this.id = TEMP_ID;
        this.name = name;
        this.age = age;
    }

    public StudentDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
