package com.example.greetings;
import com.example.greetings.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//         Repository
//              |
//        CrudRepository
//              |
//         JpaRepository
// we have access to all the CRUD operations inherited from CrudRepository

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, String> {
    // we have access to built in methods like save() for POST, findAll for GET
    // we can also write our own custom methods (later)
        // Jpa will parse the names of these custom methods to look for "find" "by"
    Greeting findByid(int id);
        // find - looking for something
        // By - the condition of what's being looked for
        // id - the variable
}
