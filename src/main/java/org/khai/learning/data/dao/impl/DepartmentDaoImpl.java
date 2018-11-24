package org.khai.learning.data.dao.impl;

import org.khai.learning.data.dao.DepartmentDao;
import org.khai.learning.data.model.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class DepartmentDaoImpl extends AbstractDaoImpl<DepartmentDto> implements DepartmentDao {
    private static final String TABLE_NAME = "department";
    private static final String NAME_COLUMN = "name";

    private static final String INSERT_DEPARTMENT_QUERY = String.format(
            "INSERT INTO %s (%s) VALUES (?)",
            TABLE_NAME, NAME_COLUMN);
    private static final String SELECT_ALL_QUERY = String.format(
            "SELECT * FROM %s",
            TABLE_NAME);

    @Autowired
    public DepartmentDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, TABLE_NAME);
    }

    @Override
    protected DepartmentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DepartmentDto(
                rs.getInt(ID_COLUMN),
                rs.getString(NAME_COLUMN)
        );
    }

    @Override
    protected PreparedStatement prepareInsertStmt(Connection connection, DepartmentDto entry) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_DEPARTMENT_QUERY);
        ps.setString(1, entry.getName());
        return ps;
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, this::mapRow);
    }
}
