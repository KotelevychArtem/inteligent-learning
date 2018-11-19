package org.khai.learning.data.dao;

import org.khai.learning.data.model.ThemeDto;

import java.util.List;

public interface ThemeDao extends GenericDao<ThemeDto> {
    List<ThemeDto> getThemesBySubjectId(int subjectId);
    ThemeDto getTheme(int id);
}
