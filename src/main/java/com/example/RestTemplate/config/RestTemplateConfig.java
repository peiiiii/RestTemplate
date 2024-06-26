package com.example.RestTemplate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    
    // to perform HTTP request like calling external API
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
