package org.khai.learning.api.model;

import java.util.ArrayList;

public class TestModel {

    public TestModel(int id, String name, ArrayList<QuestionModel> questionModels) {
        this.id = id;
        this.name = name;
        this.questionModels = questionModels;
    }

    private int id;
    private String name;
    private ArrayList<QuestionModel> questionModels;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<QuestionModel> getQuestionModels() {
        return questionModels;
    }
}
