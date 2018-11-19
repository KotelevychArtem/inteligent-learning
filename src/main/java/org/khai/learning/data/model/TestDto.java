package org.khai.learning.data.model;

public class TestDto {

    private int id;
    private int themeId;
    private String name;

    public TestDto() {
    }

    public TestDto(int id, int themeId, String name) {
        this.id = id;
        this.themeId = themeId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
