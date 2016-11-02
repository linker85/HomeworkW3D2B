package com.example.com.starwarsretrofit.models;

import com.orm.SugarRecord;

/**
 * Created by raul on 01/11/2016.
 */

public class Student extends SugarRecord {
    private String name;
    private String age;
    private String grade;

    public Student() {
    }

    public Student(String name, String age, String grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
