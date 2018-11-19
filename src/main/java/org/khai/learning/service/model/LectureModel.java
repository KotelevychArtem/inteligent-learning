package org.khai.learning.service.model;

import java.util.List;

public class LectureModel {

    private int id;
    private String name;
    private List<String> tutorialImageUrls;

    public LectureModel(int id, String name, List<String> tutorialImageUrls) {
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
