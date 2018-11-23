package org.khai.learning.data.dao;

import org.khai.learning.data.model.DepartmentDto;
import org.khai.learning.service.model.DepartmentModel;

import java.util.List;

public interface DepartmentDao extends GenericDao<DepartmentDto> {
    List<DepartmentDto> getAllDepartments();
}
