package com.example.greetings;

import com.example.greetings.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController //  you have to annotate the class in order to annotate the methods
@CrossOrigin(origins  =  "http://localhost:3000")
public class GreetingController {

    // in order to create an endpoint we need
    // an annotation to detail...
    // the location of the endpoint and,
    // the associated HTTP method
    // a method that runs when that endpoint is "hit"/when a request is made to that endpoint

    @GetMapping("/welcome")
    public String sayHi() {
        return "Welcome to SpringBoot!";
    }

    // path variables & request parameters
    // why would we need these?
    // pass information on GET requests, from the client (REACT) to the server (Spring Boot)

    // challenge
    // add 2 more endpoints
    // one for getting a greeting by its id
    // one for saying hello back to someone

    //	// looks for a query sign, a ?, and read from after that
    //	@GetMapping("/hello")
    //	public String sayHello(@RequestParam String name) {
    //		return "Hello " + name;
    //	}

    // challenge
    // make an endpoint that returns a List of all the greetings
    // looks for a query sign, a ?, and read from after that

    // GET all greetings
    // GET a random greeting
    // GET a specific greeting by ID
    // POST a new greeting
    private List<Greeting> greetings = new ArrayList<>();
    private int id = 0;

    // dependency injection
        // avoids us needing to make a new instance
    @Autowired
    GreetingRepository repository;

    // INDEX route (GET ALL)
    @GetMapping  ("/greetings")
    public ResponseEntity<List<Greeting>> getAllGreetings () {
//        return greetings;
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }
    //
//	// CHALLENGE - extra to the controller
	@GetMapping  ("/random")
	public ResponseEntity<Greeting> getRandomGreeting () {
		// initializing random class
		Random r = new Random();

		// generating random index with the help of
		// nextInt() method
//		int index =
        // refactor to get random greeting from database, not greetings array
            // .count to find number of entries in repository
            // then do .findById with repository.count() as argument
//        int index = 1 + r.nextInt((int) (repository.count()));
//        System.out.println(index);
//        return ResponseEntity.status(HttpStatus.OK).body(repository.findByid(index));

        // .findAll (already written ^^) to get all of the existing greetings
         List<Greeting> allGreetings = getAllGreetings().getBody();
         Greeting randomGreeting = allGreetings.get(r.nextInt(allGreetings.size()));
         return ResponseEntity.status(HttpStatus.OK).body(randomGreeting);
	}
//
//	// GET route
    @GetMapping("/greetings/{id}")
    public ResponseEntity<Greeting> getGreetingById(@PathVariable int id) {
        // what made up my response
            // status code
            // our actual greeting (body)
            // headers
        return ResponseEntity.status(HttpStatus.OK).body(repository.findByid(id));
//        return repository.findByid(id);
//        return greetings.get(id);
    }

    // CREATE route
    @PostMapping("/greetings")
    public ResponseEntity<String> createGreeting(@RequestBody Greeting greeting) {
//        greetings.add(greeting);
        repository.save(greeting);
//        greeting.setId(id);
//        greeting.setCreatedBy("Sheena");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        greeting.setDateCreated(timestamp);
//        id++;
        return ResponseEntity.status(HttpStatus.OK).body(greeting + " added");
    }

    // UPDATE route
    @PutMapping("/greetings/{id}")
    public Greeting updateGreeting (@PathVariable int id, @RequestBody Greeting newGreeting) {
        // change the greeting with the new info - getting the existing ID
        Greeting greeting = repository.findByid(id);
        if (newGreeting.getGreeting() != null) {
            greeting.setGreeting(newGreeting.getGreeting());
        }
        if (newGreeting.getCreatedBy() != null) {
            greeting.setCreatedBy(newGreeting.getCreatedBy());
        }
        if (newGreeting.getOriginCountry() != null) {
            greeting.setOriginCountry(newGreeting.getOriginCountry());
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        greeting.setDateCreated(timestamp);
        return greeting;
    }

//    // DELETE route
//    @DeleteMapping("/greetings/{id}")
//    @Transactional
//    public String deleteGreeting(@PathVariable String id) {
////		greetings.remove(greetings.get(id));
////        greetings.remove(greetings.stream().filter(greeting -> greeting.getId()==id).findFirst().get());
//        repository.deleteById(id);
//        return "Deleted";
//
//    }
    @DeleteMapping("/greetings/{id}")
    @Transactional
    public String deleteGreetingById(@PathVariable int id){
//        repository.deleteById(Integer.toString(id));
        repository.delete(repository.findByid(id));

        return "Greeting with id: " + id + " deleted";
    }
//

}
