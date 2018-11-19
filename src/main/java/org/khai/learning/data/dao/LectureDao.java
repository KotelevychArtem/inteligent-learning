package org.khai.learning.data.dao;

import org.khai.learning.data.model.LectureDto;
import org.khai.learning.data.model.QuestionDto;

import java.util.List;

public interface LectureDao extends GenericDao<QuestionDto> {
        List<LectureDto> getLecturesByThemeId(int themeId);
        LectureDto getLecture(int id);
}

