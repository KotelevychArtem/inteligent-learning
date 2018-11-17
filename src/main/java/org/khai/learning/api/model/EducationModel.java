package org.khai.learning.api.model;

import java.util.ArrayList;

public class EducationModel {

    public EducationModel(ArrayList<String> tutorialImageUrls) {
        this.tutorialImageUrls = tutorialImageUrls;
    }

    private ArrayList<String> tutorialImageUrls;

    public ArrayList<String> getTutorialImageUrls() {
        return tutorialImageUrls;
    }

    //TODO Add functionality for smart education
    //private SmartEducation smartEducation;

}
