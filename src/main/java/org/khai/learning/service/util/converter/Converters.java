package org.khai.learning.service.util.converter;

import org.khai.learning.data.model.DepartmentDto;
import org.khai.learning.service.model.DepartmentModel;
import org.khai.learning.service.model.SubjectModel;

import java.util.Collections;
import java.util.List;

public abstract class Converters {
    private Converters() {}

    public static DepartmentModel convertDepartmentPreview(DepartmentDto departmentDto) {
        return new DepartmentModel(
                departmentDto.getId(),
                departmentDto.getName(),
                Collections.emptyList());
    }

    public static DepartmentModel convertDepartment(DepartmentDto departmentDto, List<SubjectModel> subjectModels) {
        return new DepartmentModel(
                departmentDto.getId(),
                departmentDto.getName(),
                subjectModels);
    }
}
