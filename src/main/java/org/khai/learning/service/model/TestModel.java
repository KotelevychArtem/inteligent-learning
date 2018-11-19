package org.khai.learning.service.model;

import java.util.List;

public class TestModel {

    private int id;
    private String name;
    private List<QuestionModel> questionModels;

    public TestModel() {
    }

    public TestModel(int id, String name, List<QuestionModel> questionModels) {
        this.id = id;
        this.name = name;
        this.questionModels = questionModels;
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

    public List<QuestionModel> getQuestionModels() {
        return questionModels;
    }

    public void setQuestionModels(List<QuestionModel> questionModels) {
        this.questionModels = questionModels;
    }
}
