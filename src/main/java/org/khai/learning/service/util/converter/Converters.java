package org.khai.learning.service.util.converter;

import org.khai.learning.data.model.*;
import org.khai.learning.service.model.*;

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

    public static SubjectModel convertSubjectPreview(SubjectDto subjectDto) {
        return new SubjectModel(
                subjectDto.getId(),
                subjectDto.getName(),
                Collections.emptyList());
    }

    public static SubjectModel convertSubject(SubjectDto subjectDto, List<ThemeModel> themeModels) {
        return new SubjectModel(
                subjectDto.getId(),
                subjectDto.getName(),
                themeModels);
    }

    public static ThemeModel convertThemePreview(ThemeDto themeDto) {
        return new ThemeModel(
                themeDto.getId(),
                themeDto.getName(),
                Collections.emptyList(),
                Collections.emptyList());
    }

    public static ThemeModel convertTheme(ThemeDto themeDto, List<LectureModel> lectureModels, List<TestModel> testModels) {
        return new ThemeModel(
                themeDto.getId(),
                themeDto.getName(),
                testModels,
                lectureModels);
    }

    public static TestModel convertTestPreview(TestDto testDto) {
        return new TestModel(
                testDto.getId(),
                testDto.getName(),
                Collections.emptyList());
    }

    public static TestModel convertTest(TestDto testDto, List<QuestionModel> questionModels) {
        return new TestModel(
                testDto.getId(),
                testDto.getName(),
                questionModels);
    }

    public static QuestionModel convertQuestion(QuestionDto questionDto) {
        return new QuestionModel(
                questionDto.getId(),
                questionDto.getPosition(),
                questionDto.getName(),
                questionDto.getCondition(),
                questionDto.getRightAnswer());
    }

    public static LectureModel convertLection(LectureDto lectureDto) {
        return new LectureModel(
                lectureDto.getId(),
                lectureDto.getName(),
                lectureDto.getTutorialImageUrls());
    }
}
