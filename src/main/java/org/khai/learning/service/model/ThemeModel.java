package org.khai.learning.service.model;

import java.util.List;

public class ThemeModel {

    private int id;
    private String name;
    private List<TestModel> testModels;
    private List<LectureModel> lectureModels;

    public ThemeModel() {
    }

    public ThemeModel(int id, String name, List<TestModel> testModels, List<LectureModel> lectureModels) {
        this.id = id;
        this.name = name;
        this.testModels = testModels;
        this.lectureModels = lectureModels;
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

    public List<LectureModel> getLectureModels() {
        return lectureModels;
    }

    public void setLectureModels(List<LectureModel> lectureModels) {
        this.lectureModels = lectureModels;
    }
}
