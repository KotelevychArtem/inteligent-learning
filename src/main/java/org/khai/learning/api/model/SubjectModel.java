package org.khai.learning.api.model;

import java.util.ArrayList;

public class SubjectModel {

    public SubjectModel(int id, ArrayList<ThemeModel> themeModels) {
        this.id = id;
        this.themeModels = themeModels;
    }

    private int id;
    private ArrayList<ThemeModel> themeModels;

    public int getId() {
        return id;
    }

    public ArrayList<ThemeModel> getThemeModels() {
        return themeModels;
    }
}
