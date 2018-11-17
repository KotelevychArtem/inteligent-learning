package org.khai.learning.api.model;

import java.util.ArrayList;

public class DepartmentModel {

    public DepartmentModel(int id, String name, ArrayList<SubjectModel> subjectModels) {
        this.id = id;
        this.name = name;
        this.subjectModels = subjectModels;
    }

    private int id;
    private String name;
    private ArrayList<SubjectModel> subjectModels;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public ArrayList<SubjectModel> getSubjectModels() {
        return subjectModels;
    }
}
