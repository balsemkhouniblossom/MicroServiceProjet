package com.esprit.ms.job;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobRestApi {
    private String title="Hello, i'm the Job Micro-Service";

    @RequestMapping("/hello") public String sayHello(){
        System.out.println(title); return title;
    }

}
