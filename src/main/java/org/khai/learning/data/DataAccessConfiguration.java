package org.khai.learning.data;

import org.khai.learning.data.dao.StepDao;
import org.khai.learning.data.dao.impl.StepDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataAccessConfiguration {
    @Bean
    public StepDao lectureStepDao(JdbcTemplate jdbcTemplate) {
        return new StepDaoImpl(jdbcTemplate, "lecture");
    }

    @Bean
    public StepDao guideStepDao(JdbcTemplate jdbcTemplate) {
        return new StepDaoImpl(jdbcTemplate, "guide");
    }

    @Bean
    public StepDao testStepDao(JdbcTemplate jdbcTemplate) {
        return new StepDaoImpl(jdbcTemplate, "test");
    }
}
