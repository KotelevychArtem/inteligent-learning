package org.khai.learning.data.dao.impl;

import org.khai.learning.data.dao.ThemeDao;
import org.khai.learning.data.model.ThemeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ThemeDaoImpl extends AbstractDaoImpl<ThemeDto> implements ThemeDao {
    private static final String TABLE_NAME = "theme";
    private static final String SUBJECT_ID_COLUMN = "subjectId";
    private static final String NAME_COLUMN = "name";
    private static final String DESCRIPTION_COLUMN = "description";

    private static final String INSERT_THEME_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)",
            TABLE_NAME, SUBJECT_ID_COLUMN, NAME_COLUMN, DESCRIPTION_COLUMN);

    private static final String SELECT_BY_SUBJECT_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s = ?",
            TABLE_NAME, SUBJECT_ID_COLUMN);

    @Autowired
    public ThemeDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, TABLE_NAME);
    }

    @Override
    protected ThemeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ThemeDto(
                rs.getInt(ID_COLUMN),
                rs.getInt(SUBJECT_ID_COLUMN),
                rs.getString(NAME_COLUMN),
                rs.getString(DESCRIPTION_COLUMN)
        );
    }

    @Override
    protected PreparedStatement prepareInsertStmt(Connection connection, ThemeDto entry) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_THEME_QUERY);
        ps.setInt(1, entry.getSubjectId());
        ps.setString(2, entry.getName());
        ps.setString(3, entry.getDescription());
        return ps;
    }

    @Override
    public List<ThemeDto> getThemesBySubjectId(int subjectId) {
        return jdbcTemplate.query(SELECT_BY_SUBJECT_ID_QUERY, this::mapRow, subjectId);
    }
}
