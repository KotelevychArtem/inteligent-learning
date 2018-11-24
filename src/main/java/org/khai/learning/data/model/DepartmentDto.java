package org.khai.learning.data.model;

public class DepartmentDto extends AbstractDto {

    private String name;

    public DepartmentDto() {
    }

    public DepartmentDto(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
