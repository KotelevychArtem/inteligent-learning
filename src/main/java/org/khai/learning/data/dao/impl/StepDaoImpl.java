package org.khai.learning.data.dao.impl;

import org.khai.learning.data.dao.StepDao;
import org.khai.learning.data.model.StepDto;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StepDaoImpl implements StepDao {
    static final String THEME_ID_COLUMN = "themeId";
    static final String STEP_COLUMN = "step";
    static final String CONTENT_COLUMN = "content";


    private final JdbcTemplate jdbcTemplate;
    private final String tableName;
    private final String selectStepQuery;
    private final String selectByThemeIdQuery;
    private final String upsertQuery;

    public StepDaoImpl(JdbcTemplate jdbcTemplate, String stepTableName) {
        this.jdbcTemplate = jdbcTemplate;
        this.tableName = stepTableName;

        selectStepQuery = "SELECT * FROM " + stepTableName + " WHERE themeId = ? AND step = ?";
        selectByThemeIdQuery = "SELECT * FROM " + stepTableName + " WHERE themeId = ? ORDER BY step";
        upsertQuery = String.format(
                "INSERT INTO %1$s (%2$s, %3$s, %4$s) VALUES (?, ?, ?)" +
                        " ON CONFLICT ON CONSTRAINT %1$s_pkey DO UPDATE SET content = EXCLUDED.content",
                stepTableName, THEME_ID_COLUMN, STEP_COLUMN, CONTENT_COLUMN);
    }

    @Override
    public void insertOrUpdate(StepDto stepDto) {
        jdbcTemplate.update(upsertQuery,
                stepDto.getThemeId(),
                stepDto.getStep(),
                stepDto.getContent());
    }

    @Override
    public void batchInsertOrUpdate(List<StepDto> stepDtos) {
        jdbcTemplate.batchUpdate(upsertQuery, new BatchSetter(stepDtos));
    }

    @Override
    public List<StepDto> getSteps(int themeId) {
        return jdbcTemplate.query(selectByThemeIdQuery, this::mapRow, themeId);
    }

    @Override
    public StepDto getStep(int themeId, int step) {
        return jdbcTemplate.queryForObject(selectStepQuery, this::mapRow, themeId, step);
    }

    private StepDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        StepDto step = new StepDto();
        step.setThemeId(rs.getInt(THEME_ID_COLUMN));
        step.setStep(rs.getInt(STEP_COLUMN));
        step.setContent(rs.getString(CONTENT_COLUMN));
        return step;
    }

    private static class BatchSetter implements BatchPreparedStatementSetter {
        private final List<StepDto> steps;

        public BatchSetter(List<StepDto> steps) {
            this.steps = steps;
        }

        @Override
        public void setValues(PreparedStatement ps, int i) throws SQLException {
            StepDto step = steps.get(i);
            ps.setInt(1, step.getThemeId());
            ps.setInt(2, step.getStep());
            ps.setString(3, step.getContent());
        }

        @Override
        public int getBatchSize() {
            return steps.size();
        }
    }
}
