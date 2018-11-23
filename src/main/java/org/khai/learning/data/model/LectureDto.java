package org.khai.learning.data.model;

import java.util.List;

public class LectureDto {

    private int id;
    private String name;
    private List<String> tutorialImageUrls;

    public LectureDto() {
    }

    public LectureDto(int id, String name, List<String> tutorialImageUrls) {
        this.id = id;
        this.name = name;
        this.tutorialImageUrls = tutorialImageUrls;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTutorialImageUrls(List<String> tutorialImageUrls) {
        this.tutorialImageUrls = tutorialImageUrls;
    }

    public List<String> getTutorialImageUrls() {
        return tutorialImageUrls;
    }
}
