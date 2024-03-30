package com.example.springproject2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.GET, path = "/api/hello/world")
    public String helloWorld() { //endpoint
//        return ResponseEntity.ok("Hello world");

//        return ResponseEntity.status(HttpStatus.ACCEPTED)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body("Hello world2");

        return "Hello world"; //acest mesaj este de fapt resursa returnata de backend-ul
    }
}
