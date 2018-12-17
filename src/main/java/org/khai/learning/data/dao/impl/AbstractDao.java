package org.khai.learning.data.dao.impl;

import org.khai.learning.data.dao.GenericDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDao<T> implements GenericDao<T> {
    protected static final String ID_COLUMN = "id";

    protected final JdbcTemplate jdbcTemplate;
    private final String selectByIdQuery;
    private final String deleteByIdQuery;


    public AbstractDao(JdbcTemplate jdbcTemplate, String tableName) {
        this.jdbcTemplate = jdbcTemplate;
        selectByIdQuery = "SELECT * FROM " + tableName + " WHERE id = ?";
        deleteByIdQuery = "DELETE FROM " + tableName + " WHERE id = ?";
    }

    protected abstract PreparedStatement prepareInsertStmt(Connection connection, T entry) throws SQLException;

    protected abstract T mapRow(ResultSet rs, int i) throws SQLException;

    @Override
    public Integer insert(T entry) {
        return doUpdate(con -> prepareInsertStmt(con, entry));
    }

    @Override
    public T get(int id) {
        return jdbcTemplate.queryForObject(selectByIdQuery, this::mapRow, id);
    }

    @Override
    public void remove(int id) {
        jdbcTemplate.update(deleteByIdQuery, id);
    }

    protected Integer doUpdate(PreparedStatementCreator psc) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        return (Integer) keyHolder.getKeys().get(ID_COLUMN);
    }
}
