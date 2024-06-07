package com.example.RestTemplate.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThreadPoolConfig {
    
    @Bean
    public ExecutorService getThreadPool() {
        return Executors.newCachedThreadPool();
    }
}
