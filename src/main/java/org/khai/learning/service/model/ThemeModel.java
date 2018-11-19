package org.khai.learning.service.model;

import java.util.List;

public class ThemeModel {

    private int id;
    private String name;
    private List<TestModel> testModels;
    private List<EducationModel> educationModels;

    public ThemeModel() {
    }

    public ThemeModel(int id, String name, List<TestModel> testModels, List<EducationModel> educationModels) {
        this.id = id;
        this.name = name;
        this.testModels = testModels;
        this.educationModels = educationModels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TestModel> getTestModels() {
        return testModels;
    }

    public void setTestModels(List<TestModel> testModels) {
        this.testModels = testModels;
    }

    public List<EducationModel> getEducationModels() {
        return educationModels;
    }

    public void setEducationModels(List<EducationModel> educationModels) {
        this.educationModels = educationModels;
    }
}
