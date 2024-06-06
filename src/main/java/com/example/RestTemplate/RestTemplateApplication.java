package com.example.RestTemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// 作业：  创建2个restapi ， 第一个api 发送request 到http://universities.hipolabs.com/search。   
// 第二个api 接受list of countries， 然后用multithreading 发送request 到http://universities.hipolabs.com/search?country=United+Kingdom
// 要求1： 创建的2个rest api 要用同样的endpoint path, 不同的method

@SpringBootApplication
public class RestTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestTemplateApplication.class, args);
	}

}
