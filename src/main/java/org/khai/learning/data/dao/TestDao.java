package org.khai.learning.data.dao;

import org.khai.learning.data.model.TestDto;

import java.util.List;

public interface TestDao extends GenericDao<TestDto> {
    List<TestDto> getTestByThemeId(int themeId);
    TestDto getTest(int id);

}
