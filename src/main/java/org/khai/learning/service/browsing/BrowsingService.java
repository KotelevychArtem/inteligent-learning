package org.khai.learning.service.browsing;

import org.khai.learning.data.dao.*;
import org.khai.learning.data.model.StepDto;
import org.khai.learning.data.model.ThemeDto;
import org.khai.learning.service.model.*;
import org.khai.learning.service.util.converter.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrowsingService {
    private final ThemeDao themeDao;
    private final StepDao lectureStepDao;

    @Autowired
    public BrowsingService(ThemeDao themeDao, StepDao lectureStepDao) {
        this.themeDao = themeDao;
        this.lectureStepDao = lectureStepDao;
    }

    public List<ThemeModel> getThemeList() {
        List<ThemeDto> allThemes = themeDao.getAllThemes();
        return allThemes.stream()
                .map(Converters::convertThemePreview)
                .collect(Collectors.toList());
    }

    public ThemeModel getLecture(int themeId) {
        ThemeModel themeModel = Converters.convertThemePreview(themeDao.get(themeId));
        List<StepDto> steps = lectureStepDao.getSteps(themeId);
        List<StepModel> stepModels = steps.stream()
                .map(Converters::convertStep)
                .collect(Collectors.toList());
        themeModel.setSteps(stepModels);
        return themeModel;
    }

}
