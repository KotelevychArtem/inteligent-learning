package org.khai.learning.data.dao;

import org.khai.learning.data.model.StepDto;

import java.util.List;

public interface StepDao {
    void insertOrUpdate(StepDto stepDto);
    List<StepDto> getSteps(int themeId);
    StepDto getStep(int themeId, int step);
}
