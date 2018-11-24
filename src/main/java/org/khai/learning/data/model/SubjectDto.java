package org.khai.learning.data.model;

public class SubjectDto extends AbstractDto {

    private int departmentId;
    private String name;

    public SubjectDto() {
    }

    public SubjectDto(int id, int departmentId, String name) {
        super(id);
        this.departmentId = departmentId;
        this.name = name;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
