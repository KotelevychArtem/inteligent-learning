package org.khai.learning.data.dao;

import org.khai.learning.data.model.QuestionDto;

import java.util.List;

public interface QuestionDao extends GenericDao<QuestionDto> {
    List<QuestionDto> getQuestionsByTestId(int testId);
}
