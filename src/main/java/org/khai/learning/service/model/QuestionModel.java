package org.khai.learning.service.model;

public class QuestionModel {

    private int id;
    private int position;
    private String name;
    private String condition;
    private String actualAnswer;
    private String rightAnswer;

    public QuestionModel() {
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getActualAnswer() {
        return actualAnswer;
    }

    public void setActualAnswer(String actualAnswer) {
        this.actualAnswer = actualAnswer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
