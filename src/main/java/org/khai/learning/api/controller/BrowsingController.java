package org.khai.learning.api.controller;

import org.khai.learning.service.browsing.BrowsingService;
import org.khai.learning.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class BrowsingController {

    private final BrowsingService browsingService;

    @Autowired
    BrowsingController(BrowsingService browsingService) {
        this.browsingService = browsingService;
    }

    @GetMapping("/departments")
    public List<DepartmentModel> getDeparments() {
        return browsingService.getAllDepartmentsPreview();
    }

    @GetMapping("/departments/{id}")
    public DepartmentModel getDepartmentDetails(@PathVariable int id) {
        return browsingService.getDepartmentPreview(id);
    }

    @GetMapping("/subject/{id}")
    public SubjectModel getSubjectDetails(@PathVariable int id) {
        return browsingService.getSubjectPreview(id);
    }

    @GetMapping("/theme/{id}")
    public ThemeModel getThemeDetails(@PathVariable int id) {
        return browsingService.getThemePreview(id);
    }

    @GetMapping("/test/{id}")
    public TestModel getTestDetails(@PathVariable int id) {
        return browsingService.getTest(id);
    }

    @GetMapping("/question/{id}")
    public QuestionModel getQuestionDetails(@PathVariable int id) {
        return browsingService.getQuestion(id);
    }

    @GetMapping("/lecture/{id}")
    public LectureModel getLectureDetails(@PathVariable int id) {
        return browsingService.getLecture(id);
    }

}
