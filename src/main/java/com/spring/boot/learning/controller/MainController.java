package com.spring.boot.learning.controller;

import com.spring.boot.learning.dataservices.User;
import com.spring.boot.learning.dataservices.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * Created by AmitAgarwal on 4/3/19.
 */
@Controller
@RequestMapping(path = "/demo")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/add") //only GET request is accepted
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email){ //@ResponseBody --> return is a String and not a view
        User user = new User();
        user.setName(name);
        user.setEmail(email);

        userRepository.save(user);
        return "Information Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
}
