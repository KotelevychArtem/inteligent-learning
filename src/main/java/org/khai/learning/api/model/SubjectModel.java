package org.khai.learning.api.model;

import java.util.ArrayList;
import java.util.List;

public class SubjectModel {

    private int id;
    private List<ThemeModel> themeModels;

    public SubjectModel(int id, List<ThemeModel> themeModels) {
        this.id = id;
        this.themeModels = themeModels;
    }

    public int getId() {
        return id;
    }

    public List<ThemeModel> getThemeModels() {
        return themeModels;
    }
}
