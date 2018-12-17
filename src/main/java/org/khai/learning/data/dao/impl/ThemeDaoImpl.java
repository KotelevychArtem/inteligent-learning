package org.khai.learning.data.dao.impl;

import org.khai.learning.data.dao.ThemeDao;
import org.khai.learning.data.model.ThemeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class ThemeDaoImpl extends AbstractDao<ThemeDto> implements ThemeDao {
    private static final String TABLE_NAME = "theme";
    private static final String CODE_COLUMN = "code";
    private static final String NAME_COLUMN = "name";
    private static final String DESC_COLUMN = "description";

    private static final String INSERT_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)",
            TABLE_NAME, CODE_COLUMN, NAME_COLUMN, DESC_COLUMN);

    private static final String SELECT_ALL_QUERY = "SELECT * FROM " + TABLE_NAME;

    @Autowired
    public ThemeDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, TABLE_NAME);
    }

    @Override
    public Integer insert(ThemeDto entry) {
        Integer id = super.insert(entry);
        entry.setId(id);
        return id;
    }

    @Override
    protected PreparedStatement prepareInsertStmt(Connection connection, ThemeDto entry) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, entry.getCode());
        ps.setString(2, entry.getName());
        ps.setString(3, entry.getDescription());
        return ps;
    }

    @Override
    protected ThemeDto mapRow(ResultSet rs, int i) throws SQLException {
        ThemeDto theme = new ThemeDto();
        theme.setId(rs.getInt(ID_COLUMN));
        theme.setCode(rs.getString(CODE_COLUMN));
        theme.setName(rs.getString(NAME_COLUMN));
        theme.setDescription(rs.getString(DESC_COLUMN));
        return theme;
    }

    @Override
    public List<ThemeDto> getAllThemes() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, this::mapRow);
    }
}
