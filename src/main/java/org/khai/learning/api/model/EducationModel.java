package org.khai.learning.api.model;

import java.util.List;

public class EducationModel {

    private List<String> tutorialImageUrls;

    public EducationModel(List<String> tutorialImageUrls) {
        this.tutorialImageUrls = tutorialImageUrls;
    }

    public List<String> getTutorialImageUrls() {
        return tutorialImageUrls;
    }

}
