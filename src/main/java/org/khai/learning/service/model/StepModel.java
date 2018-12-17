package org.khai.learning.service.model;

public class StepModel {
    private int themeId;
    private int step;
    private String content;

    public StepModel() {
    }

    public StepModel(int themeId, int step, String content) {
        this.themeId = themeId;
        this.step = step;
        this.content = content;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
