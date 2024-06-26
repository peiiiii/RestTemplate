package com.example.RestTemplate.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.RestTemplate.entity.University;
import com.example.RestTemplate.service.UniversityService;


@Service
public class UniversityServiceImpl1 implements UniversityService {

    private final RestTemplate restTemplate;
    private final ExecutorService pool;

    public UniversityServiceImpl1(RestTemplate restTemplate, ExecutorService pool) {
        this.restTemplate = restTemplate;
        this.pool = pool;
    }

    @Value("${university.url}")
    private String endpoint;

    public University[] getAll(){
        return restTemplate.getForObject(endpoint, University[].class);
    }

    public List<University> getByCountries(List<String> countries){
        List<CompletableFuture<List<University>>> futures = new ArrayList<>();
        for (String country : countries) {
            try {
                String encodedString = URLEncoder.encode(country, "UTF-8");
                String url = endpoint + "?country=" + encodedString;
                System.out.println(url);

                CompletableFuture<List<University>> future = CompletableFuture.supplyAsync(() -> {
                    University[] response = restTemplate.getForObject(url, University[].class);
                    return Arrays.asList(response);
                }, pool).whenComplete((r,e) -> {
                    if(e == null) System.out.println("Completed. Response length = " + r.size());
                    else System.out.println("Error: " + e);
                });
                futures.add(future);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        // Combine all CompletableFuture results into a single list
        List<University> allUniversities = futures.stream()
                .map(CompletableFuture::join) // Wait for each CompletableFuture to complete and get the result
                .flatMap(List::stream) // Flatten lists of List<University> to stream of University
                .collect(Collectors.toList()); // Collect all universities into a List

        return allUniversities;
    }
}