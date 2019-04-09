package com.spring.boot.learning.dataservices;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AmitAgarwal on 4/8/19.
 */
@Repository
public interface DogRepository extends CrudRepository<Dog,Long>{
}
