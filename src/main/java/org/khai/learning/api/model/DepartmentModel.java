package org.khai.learning.api.model;

import java.util.List;

public class DepartmentModel {

    private int id;
    private String name;
    private List<SubjectModel> subjectModels;

    public DepartmentModel(int id, String name, List<SubjectModel> subjectModels) {
        this.id = id;
        this.name = name;
        this.subjectModels = subjectModels;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<SubjectModel> getSubjectModels() {
        return subjectModels;
    }
}
