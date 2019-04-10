package com.spring.boot.learning.service;

import com.spring.boot.learning.model.DogDto;
import com.spring.boot.learning.applicationexception.SpringBootException;
import com.spring.boot.learning.dataservices.Dog;
import com.spring.boot.learning.dataservices.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by AmitAgarwal on 4/8/19.
 */
@Service
public class DogService {

    @Autowired
    private DogRepository dogRepository;

    public void add(DogDto dogDto){
        dogRepository.save(toEntity(dogDto));
    }

    public void delete(long id){
        dogRepository.deleteById(id);
    }

    public List<Dog> getDogs(){
        return (List<Dog>) dogRepository.findAll();
    }

    public Dog getDogById(long id) throws SpringBootException {
        Optional<Dog> optionalDog = dogRepository.findById(id);
        return optionalDog.orElseThrow( () -> new SpringBootException("Could Not find Dog with ID"));
    }

    private Dog toEntity(DogDto dogDto){
        Dog entity = new Dog();
        entity.setName(dogDto.getName());
        entity.setAge(dogDto.getAge());
        return entity;
    }
}
