package com.spring.boot.learning.dataservices;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by AmitAgarwal on 4/3/19.
 */

//this will be auto implemented by Spring into a bean called userrepository
//CRUD --> Create, Read, Update, Delete
public interface UserRepository extends CrudRepository<User,Integer>{
}
