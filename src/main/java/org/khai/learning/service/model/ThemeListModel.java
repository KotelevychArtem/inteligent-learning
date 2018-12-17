package org.khai.learning.service.model;

import java.util.List;

public class ThemeListModel {
    private List<ThemeModel> themes;

    public ThemeListModel(List<ThemeModel> themes) {
        this.themes = themes;
    }

    public List<ThemeModel> getThemes() {
        return themes;
    }

    public void setThemes(List<ThemeModel> themes) {
        this.themes = themes;
    }
}
