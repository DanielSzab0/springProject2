package com.example.springproject2.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class HelloWorldController2 {
    @RequestMapping(method = RequestMethod.GET, path = "/api/hello/world2")
    public ResponseEntity<String> helloWorld() { //endpoint
        return ResponseEntity.ok("Hello world2");

//        return ResponseEntity.status(HttpStatus.ACCEPTED)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body("Hello world2");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/hello/world3")
    public ResponseEntity<String> helloWorldWithHeaders() { //endpoint

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("header1", "value1");
        httpHeaders.add("header2", "value2");

        return new ResponseEntity<>("Hello world3", httpHeaders, HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/hello/world4")
    public ResponseEntity<String> helloWorldWithHeaders2() { //endpoint

        MultiValueMap map = new LinkedMultiValueMap<>(Map.of("header1", List.of("value1", "value2", "value3", "value4")));

        return new ResponseEntity<>("Hello world4", map, HttpStatus.ACCEPTED);

    }
}
