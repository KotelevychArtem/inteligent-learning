package org.khai.learning.data.dao.impl;

import org.khai.learning.data.dao.TestDao;
import org.khai.learning.data.model.TestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class TestDaoImpl extends AbstractDaoImpl<TestDto> implements TestDao {
    private static final String TABLE_NAME = "test";
    private static final String THEME_ID_COLUMN = "themeId";
    private static final String NAME_COLUMN = "name";

    private static final String INSERT_TEST_QUERY = String.format(
            "INSERT INTO %s (%s, %s) VALUES (?, ?)",
            TABLE_NAME, THEME_ID_COLUMN, NAME_COLUMN);

    private static final String SELECT_BY_THEME_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s = ?",
            TABLE_NAME, THEME_ID_COLUMN);

    @Autowired
    public TestDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, TABLE_NAME);
    }

    @Override
    protected TestDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TestDto(
                rs.getInt(ID_COLUMN),
                rs.getInt(THEME_ID_COLUMN),
                rs.getString(NAME_COLUMN)
        );
    }

    @Override
    protected PreparedStatement prepareInsertStmt(Connection connection, TestDto entry) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_TEST_QUERY);
        ps.setInt(1, entry.getThemeId());
        ps.setString(2, entry.getName());
        return ps;
    }

    @Override
    public List<TestDto> getTestByThemeId(int themeId) {
        return jdbcTemplate.query(SELECT_BY_THEME_ID_QUERY, this::mapRow, themeId);
    }
}
