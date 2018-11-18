package org.khai.learning.api.model;

public class QuestionModel {

    private int id;
    private int position;
    private String name;
    private String condition;
    private String actualAnswer;
    private String rightAnswer;

    public QuestionModel(int id, int position, String name, String condition, String rightAnswer) {
        this.id = id;
        this.position = position;
        this.name = name;
        this.condition = condition;
        this.rightAnswer = rightAnswer;
    }

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public String getCondition() {
        return condition;
    }

    public String getActualAnswer() {
        return actualAnswer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }
}
