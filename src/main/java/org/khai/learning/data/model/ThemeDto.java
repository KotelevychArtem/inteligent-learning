package org.khai.learning.data.model;

public class ThemeDto extends AbstractDto {

    private int subjectId;
    private String name;
    private String description;

    public ThemeDto() {
    }

    public ThemeDto(int id, int subjectId, String name, String description) {
        super(id);
        this.subjectId = subjectId;
        this.name = name;
        this.description = description;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
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
}
