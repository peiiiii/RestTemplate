package com.example.RestTemplate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.RestTemplate.entity.University;
import com.example.RestTemplate.service.UniversityService;

@RestController
public class Controller {

    @Autowired
    private UniversityService universityService;

    // send request to "http://universities.hipolabs.com/search"
    @GetMapping("/university")
    public ResponseEntity<List<University>> search(){
        List<University> universities = universityService.searchAll();
        if (universities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(universities, HttpStatus.OK);
    }

    // Accept list of countries and use multithreading to send request to 
    // "http://universities.hipolabs.com/search?country=United+Kingdom"
    @PostMapping("/university")
    public ResponseEntity<List<University>> searchByList(@RequestBody List<String> countries){
        List<University> universities = universityService.searchByCountries(countries);
        return new ResponseEntity<>(universities, HttpStatus.OK);
    }
}
