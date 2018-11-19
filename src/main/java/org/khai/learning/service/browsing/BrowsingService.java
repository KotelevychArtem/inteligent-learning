package org.khai.learning.service.browsing;

import org.khai.learning.data.dao.*;
import org.khai.learning.service.model.*;
import org.khai.learning.service.util.converter.Converters;

import java.util.List;
import java.util.stream.Collectors;

//@Service // app won't start without actual daos
public class BrowsingService {
    private final DepartmentDao departmentDao;
    private final QuestionDao questionDao;
    private final SubjectDao subjectDao;
    private final TestDao testDao;
    private final ThemeDao themeDao;
    private final LectureDao lectureDao;

//    @Autowired
    public BrowsingService(DepartmentDao departmentDao, QuestionDao questionDao, SubjectDao subjectDao, TestDao testDao, ThemeDao themeDao, LectureDao lectureDao) {
        this.departmentDao = departmentDao;
        this.questionDao = questionDao;
        this.subjectDao = subjectDao;
        this.testDao = testDao;
        this.themeDao = themeDao;
        this.lectureDao = lectureDao;
    }

    /**
     * @return Departments with id, name and empty subject list.
     */
    public List<DepartmentModel> getAllDepartmentsPreview() {
        return departmentDao.getAllDepartments().stream()
                .map(Converters::convertDepartmentPreview)
                .collect(Collectors.toList());
    }

    /**
     * @return Department with id, name and subject list.
     */
    public DepartmentModel getDepartment(int id) {
        List<SubjectModel> subjectModels = subjectDao.getSubjectsByDepartmentId(id).stream()
                .map(Converters::convertSubjectPreview)
                .collect(Collectors.toList());
        return Converters.convertDepartment(departmentDao.getDepartment(id), subjectModels);
    }

    /**
     * @return Subject with id, name and theme list.
     */
    public SubjectModel getSubject(int id) {
        List<ThemeModel> themeModels = themeDao.getThemesBySubjectId(id).stream()
                .map(Converters::convertThemePreview)
                .collect(Collectors.toList());
        return Converters.convertSubject(subjectDao.getSubject(id), themeModels);
    }

    /**
     * @return Theme with id, name, tests and lectures list.
     */
    public ThemeModel getTheme(int id) {
        List<TestModel> testModels = testDao.getTestByThemeId(id).stream()
                .map(Converters::convertTestPreview)
                .collect(Collectors.toList());
        List<LectureModel> lectureModels = lectureDao.getLecturesByThemeId(id).stream()
                .map(Converters::convertLection)
                .collect(Collectors.toList());
        return Converters.convertTheme(themeDao.getTheme(id), lectureModels, testModels);
    }

    /**
     * @return Test with id, name and questions list.
     */
    public TestModel getTest(int id) {
        List<QuestionModel> questionModels = questionDao.getQuestionsByTestId(id).stream()
                .map(Converters::convertQuestion)
                .collect(Collectors.toList());
        return Converters.convertTest(testDao.getTest(id), questionModels);
    }

    /**
     * @return Question with id, position, name, condition and right answer.
     */
    public QuestionModel getQuestion(int id) {
        return Converters.convertQuestion(questionDao.getQuestion(id));
    }

    /**
     * @return Lecture with id, name and image urls list.
     */
    public LectureModel getLecture(int id) {
        return Converters.convertLection(lectureDao.getLecture(id));
    }

}
