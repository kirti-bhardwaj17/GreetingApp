package com.example.greeting.controller;

import com.example.greeting.model.Greeting;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/greet")
public class GreetingController {

    private final AtomicLong counter = new AtomicLong();
    private static final String template = "Hello, %s!";

    @GetMapping
    public Greeting getGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @PostMapping
    public Greeting createGreeting(@RequestBody Greeting greeting) {
        return new Greeting(counter.incrementAndGet(), "Welcome, " + greeting.getMessage());
    }

    @PutMapping
    public Greeting updateGreeting(@RequestBody Greeting greeting) {
        return new Greeting(counter.incrementAndGet(), "Updated Greeting: " + greeting.getMessage());
    }

    @DeleteMapping
    public Greeting deleteGreeting() {
        return new Greeting(0, "Greeting deleted successfully!");
    }
}
