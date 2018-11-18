package org.khai.learning.api.model;

import java.util.ArrayList;
import java.util.List;

public class ThemeModel {

    private int id;
    private String name;
    private List<TestModel> testModels;
    private List<EducationModel> educationModels;

    public ThemeModel(int id, String name, List<TestModel> testModels, List<EducationModel> educationModels) {
        this.id = id;
        this.name = name;
        this.testModels = testModels;
        this.educationModels = educationModels;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<TestModel> getTestModels() {
        return testModels;
    }

    public List<EducationModel> getEducationModels() {
        return educationModels;
    }
}
