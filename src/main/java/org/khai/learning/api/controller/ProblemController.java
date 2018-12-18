package org.khai.learning.api.controller;

import org.khai.learning.service.browsing.ProblemService;
import org.khai.learning.service.model.TransitionalContextModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/problem")
public class ProblemController {
    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @CrossOrigin
    @GetMapping("/transient")
    public TransitionalContextModel getTransient() {
        return problemService.getNew();
    }

}
