package com.example.greeting.controller;

import com.example.greeting.model.Greeting;
import com.example.greeting.Services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/greet")
public class GreetingController {

    private final AtomicLong counter = new AtomicLong();
    private static final String template = "Hello, %s!";

    @Autowired
    private GreetingService greetingService;

    // ✅ Get Greeting using firstName & lastName (Saves it to DB)
    @GetMapping("/service")
    public Greeting getServiceGreeting(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName) {

        return greetingService.getGreetingMessage(firstName, lastName);  // ✅ No need to wrap in a new Greeting object
    }

    // ✅ Default Greeting
    @GetMapping
    public Greeting getDefaultGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    // ✅ Add Greeting to Database
    @PostMapping("/add")
    public Greeting addGreeting(@RequestBody Greeting greeting) {
        return greetingService.addGreeting(greeting);
    }
    @GetMapping("/all")
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    // ✅ Get Greeting by ID
    @GetMapping("/{id}")
    public Greeting getGreetingById(@PathVariable long id) {
        return greetingService.getGreetingById(id);
    }


    // ✅ Create Greeting (Returns JSON)
    @PostMapping
    public Greeting createGreeting(@RequestBody Greeting greeting) {
        return new Greeting(counter.incrementAndGet(), "Welcome, " + greeting.getMessage());
    }

    // ✅ Update Greeting
    @PutMapping
    public Greeting updateGreeting(@RequestBody Greeting greeting) {
        return new Greeting(counter.incrementAndGet(), "Updated Greeting: " + greeting.getMessage());
    }

    // ✅ Delete Greeting (Returns Confirmation)
    @DeleteMapping
    public Greeting deleteGreeting() {
        return new Greeting(0, "Greeting deleted successfully!");
    }

}
