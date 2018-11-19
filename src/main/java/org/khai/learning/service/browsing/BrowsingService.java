package org.khai.learning.service.browsing;

import org.khai.learning.service.model.DepartmentModel;
import org.khai.learning.data.dao.*;
import org.khai.learning.service.util.converter.Converters;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Service // app won't start without actual daos
public class BrowsingService {
    private final DepartmentDao departmentDao;
    private final QuestionDao questionDao;
    private final SubjectDao subjectDao;
    private final TestDao testDao;
    private final ThemeDao themeDao;

//    @Autowired
    public BrowsingService(DepartmentDao departmentDao, QuestionDao questionDao, SubjectDao subjectDao, TestDao testDao, ThemeDao themeDao) {
        this.departmentDao = departmentDao;
        this.questionDao = questionDao;
        this.subjectDao = subjectDao;
        this.testDao = testDao;
        this.themeDao = themeDao;
    }

    /**
     * @return Departments with id, name and empty subject list.
     */
    public List<DepartmentModel> getAllDepartmentsPreview() {
        return departmentDao.getAllDepartments().stream()
                .map(Converters::convertDepartmentPreview)
                .collect(Collectors.toList());
    }

}
