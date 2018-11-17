package org.khai.learning.api.controller;

import org.khai.learning.api.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
public class BrowsingController {

    ArrayList<DepartmentModel> departmentModels = new ArrayList<>();

    BrowsingController() {
        departmentModels.add(new DepartmentModel(301, "301 Dich", new ArrayList<>()));
        departmentModels.add(new DepartmentModel(302, "302 sss", new ArrayList<>()));
        departmentModels.add(new DepartmentModel(303, "303 Gusi", new ArrayList<>()));
        departmentModels.add(new DepartmentModel(304,"304 Shitshow", new ArrayList<>()));
    }

    @GetMapping("/departments")
    public ArrayList<DepartmentModel> getDeparments() { return departmentModels; }

    @GetMapping("/departments/{id}")
    public DepartmentModel getDepartmentDetails(@PathVariable int id) {
        DepartmentModel resultDepartmentModel = null;
        for(DepartmentModel departmentModel: departmentModels) {
            if (departmentModel.getId() == id)
                resultDepartmentModel = departmentModel;
        }
        return resultDepartmentModel;
    }

    @GetMapping("/subject/{id}")
    public SubjectModel getSubjectDetails(@PathVariable int id) {
        //TODO Find subject by id
        return new SubjectModel(1, new ArrayList<>());
    }

    @GetMapping("/theme/{id}")
    public ThemeModel getThemeDetails(@PathVariable int id) {
        //TODO Find theme by id
        return new ThemeModel(1,"Theme name", new ArrayList<>(), new ArrayList<>());
    }

    @GetMapping("/test/{id}")
    public TestModel getTestDetails(@PathVariable int id) {
        //TODO Find test by id
        return new TestModel(1, "Test name", new ArrayList<>());
    }

    @GetMapping("/question/{id}")
    public QuestionModel getQuestionDetails(@PathVariable int id) {
        //TODO Find question by id
        return new QuestionModel(1, 1, "Question name", "gaayy?","yes");
    }


}
