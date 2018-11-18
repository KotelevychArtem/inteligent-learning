package org.khai.learning.api.model;

import java.util.List;

public class TestModel {

    private int id;
    private String name;
    private List<QuestionModel> questionModels;

    public TestModel(int id, String name, List<QuestionModel> questionModels) {
        this.id = id;
        this.name = name;
        this.questionModels = questionModels;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<QuestionModel> getQuestionModels() {
        return questionModels;
    }
}
