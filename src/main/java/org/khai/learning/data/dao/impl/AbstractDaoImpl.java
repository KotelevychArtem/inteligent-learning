package org.khai.learning.data.dao.impl;

import org.khai.learning.data.dao.GenericDao;
import org.khai.learning.data.model.AbstractDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDaoImpl<T extends AbstractDto> implements GenericDao<T> {
    static final String ID_COLUMN = "id";

    private final String selectByIdQuery;
    private final String deleteByIdQuery;

    protected final JdbcTemplate jdbcTemplate;
    protected final String tableName;

    public AbstractDaoImpl(JdbcTemplate jdbcTemplate, String tableName) {
        this.jdbcTemplate = jdbcTemplate;
        this.tableName = tableName;

        selectByIdQuery = "SELECT * FROM " + tableName + " WHERE id = ?";
        deleteByIdQuery = "DELETE FROM " + tableName + " WHERE id = ?";
    }

    protected abstract T mapRow(ResultSet rs, int rowNum) throws SQLException;

    protected abstract PreparedStatement prepareInsertStmt(Connection connection, T entry) throws SQLException;

    @Override
    public int add(T entry) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> prepareInsertStmt(connection, entry), keyHolder);
        int id = (int) keyHolder.getKey();
        entry.setId(id);
        return id;
    }

    @Override
    public T get(int id) {
        return jdbcTemplate.queryForObject(selectByIdQuery, new Object[] {id}, this::mapRow);
    }

    @Override
    public void remove(int id) {
        jdbcTemplate.update(deleteByIdQuery, id);
    }
}
