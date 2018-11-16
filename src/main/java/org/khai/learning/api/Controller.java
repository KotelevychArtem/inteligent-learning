package org.khai.learning.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    public Controller() {
        System.out.println("controller########");
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<String> getQuestion(@PathVariable int id) {
        return ResponseEntity.ok("Question #" + id);
    }
}
