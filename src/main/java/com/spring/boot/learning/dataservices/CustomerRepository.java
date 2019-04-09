package com.spring.boot.learning.dataservices;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by AmitAgarwal on 4/3/19.
 */
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    public List<Customer> findByLastName(String lastName);
}
