package com.spring.boot.learning.dataservices;

import org.springframework.data.gemfire.repository.query.annotation.Trace;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by AmitAgarwal on 4/3/19.
 */
public interface PersonRepository extends CrudRepository<Person,String>{

    @Trace//to enable Gemfire query debugging
    Person findByName(String name);

    @Trace
    Iterable<Person> findByAgeGreaterThan(int age);

    @Trace
    Iterable<Person> findByAgeLessThan(int age);

    @Trace
    Iterable<Person> findByAgeGreaterThanAndAgeLessThan(int greaterAgeThan, int lessThanAge);
}
