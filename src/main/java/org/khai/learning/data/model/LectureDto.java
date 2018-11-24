package org.khai.learning.data.model;

public class LectureDto extends AbstractDto {

    private int themeId;
    private String name;
    private String content;

    public LectureDto() {
    }

    public LectureDto(int id, int themeId, String name, String content) {
        super(id);
        this.themeId = themeId;
        this.name = name;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
