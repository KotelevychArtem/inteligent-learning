package org.khai.learning.data.dao;

import org.khai.learning.data.model.SubjectDto;

import java.util.List;

public interface SubjectDao extends GenericDao<SubjectDto> {
    List<SubjectDto> getSubjectsByDepartmentId(int departmentId);
    SubjectDto getSubject(int subjectId);
}
