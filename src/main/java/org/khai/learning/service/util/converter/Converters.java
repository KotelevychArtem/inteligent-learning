package org.khai.learning.service.util.converter;

import org.khai.learning.data.model.ThemeDto;
import org.khai.learning.service.model.StepModel;
import org.khai.learning.service.model.ThemeModel;

import java.util.List;

public abstract class Converters {
    private Converters() {}

    public static ThemeModel convertThemePreview(ThemeDto themeDto) {
        return new ThemeModel(
                themeDto.getId(),
                themeDto.getCode(),
                themeDto.getName(),
                themeDto.getDescription());
    }

}
