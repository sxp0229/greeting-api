package com.example.greetings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.greetings.model.Greeting;



// what we need to do to run the application
// how we add endpoints
	// what kind of endpoints can we add

// NOTE
// we have added a SQL dependency to our application in order to connect to a database... in the future
// rn we don't have a database, or want to try and connect to one!
// we need to add an temp annotation to ignore the dependency
@SpringBootApplication
@ComponentScan({"com.example.greetings"})
public class GreetingsApplication {
	public static void main(String[] args) {
		SpringApplication.run(GreetingsApplication.class, args);
	}
}
