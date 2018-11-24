package org.khai.learning.data.model;

public abstract class AbstractDto {
    protected int id;

    public AbstractDto() {
    }

    public AbstractDto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
