package org.khai.learning.service.model;

import java.util.List;

public class DepartmentModel {

    private int id;
    private String name;
    private List<SubjectModel> subjectModels;

    public DepartmentModel() {
    }

    public DepartmentModel(int id, String name, List<SubjectModel> subjectModels) {
        this.id = id;
        this.name = name;
        this.subjectModels = subjectModels;
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

    public List<SubjectModel> getSubjectModels() {
        return subjectModels;
    }

    public void setSubjectModels(List<SubjectModel> subjectModels) {
        this.subjectModels = subjectModels;
    }
}
