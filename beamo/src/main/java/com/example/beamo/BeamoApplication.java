package com.example.beamo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BeamoApplication {

    public static void main(String[] args) {
        System.out.println("hello-beamo");
        SpringApplication.run(BeamoApplication.class, args);
    }
}
