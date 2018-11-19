package org.khai.learning.service.model;

import java.util.List;

public class SubjectModel {

    private int id;
    private String name;
    private List<ThemeModel> themeModels;

    public SubjectModel() {
    }

    public SubjectModel(int id, String name, List<ThemeModel> themeModels) {
        this.id = id;
        this.name = name;
        this.themeModels = themeModels;
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

    public List<ThemeModel> getThemeModels() {
        return themeModels;
    }

    public void setThemeModels(List<ThemeModel> themeModels) {
        this.themeModels = themeModels;
    }
}
