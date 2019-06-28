package com.coinceres.checktest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@EnableDiscoveryClient
@SpringBootApplication
@RestController
@EnableFeignClients(basePackages = {"com.ceres","com.coinceres"})
public class CheckTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckTestApplication.class, args);
    }

    @GetMapping
    public String check() {
        return "I`m OK. --CheckTest";
    }
}
