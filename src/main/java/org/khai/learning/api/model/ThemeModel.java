package org.khai.learning.api.model;

import java.util.ArrayList;

public class ThemeModel {

    public ThemeModel(int id, String name, ArrayList<TestModel> testModels, ArrayList<EducationModel> educationModels) {
        this.id = id;
        this.name = name;
        this.testModels = testModels;
        this.educationModels = educationModels;
    }

    private int id;
    private String name;
    private ArrayList<TestModel> testModels;
    private ArrayList<EducationModel> educationModels;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<TestModel> getTestModels() {
        return testModels;
    }

    public ArrayList<EducationModel> getEducationModels() {
        return educationModels;
    }
}
