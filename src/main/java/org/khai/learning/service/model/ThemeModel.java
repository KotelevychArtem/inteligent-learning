package org.khai.learning.service.model;

import java.util.List;

public class ThemeModel {

    private int id;
    private String code;
    private String name;
    private String description;
    private List<StepModel> steps;
    private Object data;

    public ThemeModel() {
    }

    public ThemeModel(int id, String code, String name, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSteps(List<StepModel> steps) {
        this.steps = steps;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<StepModel> getSteps() {
        return steps;
    }
}
