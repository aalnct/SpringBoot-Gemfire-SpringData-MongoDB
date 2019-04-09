package com.spring.boot.learning.dataservices;

import org.apache.catalina.util.CustomObjectInputStream;

import javax.persistence.*;

/**
 * Created by AmitAgarwal on 4/3/19.
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    protected Customer(){

    }

    public Customer(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }


}
