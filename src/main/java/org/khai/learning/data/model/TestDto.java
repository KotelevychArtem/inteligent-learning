package org.khai.learning.data.model;

public class TestDto extends AbstractDto {

    private int themeId;
    private String name;

    public TestDto() {
    }

    public TestDto(int id, int themeId, String name) {
        super(id);
        this.themeId = themeId;
        this.name = name;
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
