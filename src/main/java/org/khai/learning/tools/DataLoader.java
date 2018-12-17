package org.khai.learning.tools;

import org.apache.commons.io.IOUtils;
import org.khai.learning.data.dao.ImageDao;
import org.khai.learning.data.dao.StepDao;
import org.khai.learning.data.dao.ThemeDao;
import org.khai.learning.data.model.ImageDto;
import org.khai.learning.data.model.StepDto;
import org.khai.learning.data.model.ThemeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@SpringBootApplication
@ComponentScan({"org.khai.learning.data*"})
public class DataLoader implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

    private static final String IMAGE_DIR = "img";
    private static final String THEME_FILE = "theme.";
    private static final String LECTURE_FILE = "lecture.steps";
    private static final String GUIDE_FILE = "guide.steps";
    private static final String TEST_FILE = "test.steps";

    private static final String STEP_TAG = "#step";

    private enum ThemeTag {
        CODE_TAG("#code", ThemeDto::setCode),
        NAME_TAG("#name", ThemeDto::setName),
        DESC_TAG("#desc", ThemeDto::setDescription);

        private final String tag;
        private final BiConsumer<ThemeDto, String> fieldSetter;

        ThemeTag(String tag, BiConsumer<ThemeDto, String> fieldSetter) {
            this.tag = tag;
            this.fieldSetter = fieldSetter;
        }

        public static ThemeTag parseTag(String s) {
            for (ThemeTag themeTag : values()) {
                if (s.startsWith(themeTag.tag)) return themeTag;
            }
            return null;
        }
    }

    @Autowired
    private ImageDao imageDao;
    @Autowired
    private ThemeDao themeDao;
    @Autowired
    private StepDao lectureStepDao;
    @Autowired
    private StepDao guideStepDao;
    @Autowired
    private StepDao testStepDao;

    private File[] files;

    public static void main(String[] args) {
        SpringApplication.run(DataLoader.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        if (strings.length < 1) {
            LOGGER.error("Pass data directory as argument!");
            System.exit(1);
        }
        File baseDir = new File(strings[0]);
        if (!baseDir.exists()) {
            LOGGER.error("Given directory does not exist");
            System.exit(1);
        }
        files = baseDir.listFiles();

        ThemeDto themeDto = loadTheme();
        LOGGER.info("New theme added: id = {}, name = '{}'", themeDto.getId(), themeDto.getName());
        loadImages();
        loadSteps(LECTURE_FILE, themeDto.getId(), lectureStepDao);
        loadSteps(GUIDE_FILE, themeDto.getId(), guideStepDao);
        loadSteps(TEST_FILE, themeDto.getId(), testStepDao);

        System.exit(0);
    }

    private ThemeDto loadTheme() throws Exception {
        File themeFile = findFile(THEME_FILE);
        if (themeFile == null) {
            throw new RuntimeException("Theme file is missing");
        }

        ThemeDto theme = new ThemeDto();
        StringBuilder data = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(themeFile))) {
            String s;
            ThemeTag currentTag = null;
            while ((s = in.readLine()) != null) {
                ThemeTag t = ThemeTag.parseTag(s);
                if (t != null) {
                    if (currentTag != null) {
                        currentTag.fieldSetter.accept(theme, data.toString());
                        data = new StringBuilder();
                    }
                    currentTag = t;
                    continue;
                }
                data.append(s);
                data.append("\n");
            }
            if (currentTag != null) {
                currentTag.fieldSetter.accept(theme, data.toString());
            }
        }

        themeDao.insert(theme);
        return theme;
    }

    private void loadImages() throws IOException {
        File imgDir = findFile(IMAGE_DIR);
        if (imgDir != null) {
            File[] images = imgDir.listFiles();
            if (images == null) return;
            for (File img : images) {
                ImageDto image = new ImageDto();
                int dotPos = img.getName().indexOf('.');
                if (dotPos == 0) {
                    LOGGER.warn("Image '{}' is not loaded", img.getName());
                    continue;
                }
                if (dotPos < 0) dotPos = img.getName().length();
                image.setTitle(img.getName().substring(0, dotPos));
                image.setBytes(IOUtils.toByteArray(img.toURI()));
                imageDao.insert(image);
            }
        }
    }

    private void loadSteps(String stepFile, int themeId, StepDao dao) throws Exception {
        File file = findFile(stepFile);
        if (file == null) {
            throw new RuntimeException("Provide '" + stepFile + "' file");
        }
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String s;
            StringBuilder data = new StringBuilder();
            StepDto step = null;
            List<StepDto> steps = new ArrayList<>();
            while ((s = in.readLine()) != null) {
                if (s.startsWith(STEP_TAG)) {
                    if (step != null) {
                        step.setContent(data.toString());
                        steps.add(step);
                        data = new StringBuilder();
                    }
                    step = new StepDto();
                    int stepNumber = Integer.valueOf(s.substring(STEP_TAG.length()).trim());
                    step.setThemeId(themeId);
                    step.setStep(stepNumber);
                    continue;
                }
                data.append(s);
                data.append("\n");
            }
            if (step != null) {
                step.setContent(data.toString());
                steps.add(step);
            }
            dao.batchInsertOrUpdate(steps);
        }
    }

    private File findFile(String name) {
        for (File file : files) {
            if (file.getName().startsWith(name)) {
                return file;
            }
        }
        return null;
    }
}
