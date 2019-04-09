package com.spring.boot.learning.controller;

import com.spring.boot.learning.DogDto;
import com.spring.boot.learning.dataservices.Dog;
import com.spring.boot.learning.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by AmitAgarwal on 4/8/19.
 */
@RestController
@RequestMapping("/dogs")
public class DogsController {

    @Autowired
    private DogService dogService;

    @GetMapping
    public List<Dog> getDogs(){
        return dogService.getDogs();
    }

    @PostMapping //http://localhost:8080/dogs?name=test&age=10
    public void postDogs(@RequestParam (name = "name") String name, @RequestParam(name = "age") int age){
        DogDto dogDto = new DogDto();
        dogDto.setName(name);
        dogDto.setAge(10);
        dogService.add(dogDto);
    }

    /*@GetMapping
    public Dog getById(@PathVariable(required = true) long id) throws SpringBootException {
        return dogService.getDogById(id);
    }*/

    @DeleteMapping("/{id}")
    public void delete(@PathVariable (required = true) long id){
        dogService.delete(id);
    }
}
