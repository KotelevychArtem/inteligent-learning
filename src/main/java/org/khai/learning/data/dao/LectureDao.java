package org.khai.learning.data.dao;

import org.khai.learning.data.model.LectureDto;

import java.util.List;

public interface LectureDao extends GenericDao<LectureDto> {
        List<LectureDto> getLecturesByThemeId(int themeId);
}

