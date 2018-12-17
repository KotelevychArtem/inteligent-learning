package org.khai.learning.data.model;

public class ImageDto {
    private Integer id;
    private String title;
    private byte[] bytes;

    public ImageDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public ImageDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public ImageDto setBytes(byte[] bytes) {
        this.bytes = bytes;
        return this;
    }
}
