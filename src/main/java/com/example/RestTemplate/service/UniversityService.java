package com.example.RestTemplate.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.RestTemplate.entity.University;


@Service
public interface UniversityService {
    University[] getAll();
    List<University> getByCountries(List<String> countries);
}