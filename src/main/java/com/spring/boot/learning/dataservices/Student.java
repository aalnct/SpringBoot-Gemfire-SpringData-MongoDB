package com.spring.boot.learning.dataservices;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by AmitAgarwal on 4/7/19.
 */
public class Student implements Serializable{

    @Id
    private String id;

    private String firsName;

    private String lastName;

    public Student(){

    }

    public Student(String firstName, String lastName){
        this.firsName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", firsName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
