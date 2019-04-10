package com.spring.boot.learning.applicationexception;

/**
 * Created by AmitAgarwal on 4/8/19.
 */
public class SpringBootException extends Exception{

    private String message;

    public SpringBootException(String message){
        this.message = message;
    }

}
