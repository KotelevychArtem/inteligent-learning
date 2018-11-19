package org.khai.learning.data.model;

public class QuestionDto {

    private int id;
    private int testId;
    private int position;
    private String name;
    private String condition;
    private String rightAnswer;

    public QuestionDto() {
    }

    public QuestionDto(int id, int testId, int position, String name, String condition, String rightAnswer) {
        this.id = id;
        this.testId = testId;
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

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
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

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
