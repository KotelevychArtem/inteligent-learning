package org.khai.learning.data.dao.impl;

import org.khai.learning.data.dao.LectureDao;
import org.khai.learning.data.model.LectureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class LectureDaoImpl extends AbstractDaoImpl<LectureDto> implements LectureDao {
    private static final String TABLE_NAME = "lecture";
    private static final String THEME_ID_COLUMN = "themeId";
    private static final String NAME_COLUMN = "name";
    private static final String CONTENT_COLUMN = "content";

    private static final String INSERT_LECTURE_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)",
            TABLE_NAME, THEME_ID_COLUMN, NAME_COLUMN, CONTENT_COLUMN);

    private static final String SELECT_BY_THEME_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s = ?",
            TABLE_NAME, THEME_ID_COLUMN);

    @Autowired
    public LectureDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, TABLE_NAME);
    }

    @Override
    protected LectureDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LectureDto(
                rs.getInt(ID_COLUMN),
                rs.getInt(THEME_ID_COLUMN),
                rs.getString(NAME_COLUMN),
                rs.getString(CONTENT_COLUMN)
        );
    }

    @Override
    protected PreparedStatement prepareInsertStmt(Connection connection, LectureDto entry) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_LECTURE_QUERY);
        ps.setInt(1, entry.getThemeId());
        ps.setString(2, entry.getName());
        ps.setString(3, entry.getContent());
        return ps;
    }

    @Override
    public List<LectureDto> getLecturesByThemeId(int themeId) {
        return jdbcTemplate.query(SELECT_BY_THEME_ID_QUERY, this::mapRow, themeId);
    }
}
