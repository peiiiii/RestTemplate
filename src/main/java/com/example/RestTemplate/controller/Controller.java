package com.example.RestTemplate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.RestTemplate.entity.University;
import com.example.RestTemplate.service.UniversityService;

@RestController
@RequestMapping(value = "api/v1/university")
public class Controller {

    private final UniversityService universityService;

    @Autowired
    public Controller(UniversityService universityService){
        this.universityService = universityService;
    }

    // send request to "http://universities.hipolabs.com/search"
    @GetMapping
    public ResponseEntity<List<University>> getAllUni(){
        return new ResponseEntity<>(universityService.getAll(), HttpStatus.OK);
    }

    // Accept list of countries and use multithreading to send request to 
    // "http://universities.hipolabs.com/search?country=United+Kingdom"
    @GetMapping(params = "countries")
    public ResponseEntity<List<University>> getUniByCountries(@RequestParam List<String> countries){
        return new ResponseEntity<>(universityService.getByCountries(countries), HttpStatus.OK);
    }
}
