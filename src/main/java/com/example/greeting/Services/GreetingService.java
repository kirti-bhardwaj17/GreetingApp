package com.example.greeting.Services;

import com.example.greeting.model.Greeting;
import com.example.greeting.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class GreetingService {

    @Autowired
    private GreetingRepository greetingRepository;

    // Save a new greeting message
    public Greeting addGreeting(Greeting greeting) {
        return greetingRepository.save(greeting);
    }

    // Generate greeting message based on user input
    public Greeting getGreetingMessage(String firstName, String lastName) {
        String message;

        if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()) {
            message = "Hello, " + firstName + " " + lastName + "!";
        } else if (firstName != null && !firstName.isEmpty()) {
            message = "Hello, " + firstName + "!";
        } else if (lastName != null && !lastName.isEmpty()) {
            message = "Hello, " + lastName + "!";
        } else {
            message = "Hello World";
        }

        return addGreeting(new Greeting(message));  // Save the message in the DB
    }

    // Retrieve a greeting by ID
    public Greeting getGreetingById(long id) {
        Optional<Greeting> greeting = greetingRepository.findById(id);
        return greeting.orElse(null);
    }
}
