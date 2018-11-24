package org.khai.learning.data.dao.impl;

import org.khai.learning.data.dao.SubjectDao;
import org.khai.learning.data.model.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class SubjectDaoImpl extends AbstractDaoImpl<SubjectDto> implements SubjectDao {
    private static final String TABLE_NAME = "subject";
    private static final String DEPARTMENT_ID_COLUMN = "departmentId";
    private static final String NAME_COLUMN = "name";

    private static final String INSERT_SUBJECT_QUERY = String.format(
            "INSERT INTO %s (%s, %s) VALUES (?, ?)",
            TABLE_NAME, DEPARTMENT_ID_COLUMN, NAME_COLUMN);

    private static final String SELECT_BY_DEPARTMENT_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s = ?",
            TABLE_NAME, DEPARTMENT_ID_COLUMN);

    @Autowired
    public SubjectDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, TABLE_NAME);
    }

    @Override
    protected SubjectDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SubjectDto(
                rs.getInt(ID_COLUMN),
                rs.getInt(DEPARTMENT_ID_COLUMN),
                rs.getString(NAME_COLUMN)
        );
    }

    @Override
    protected PreparedStatement prepareInsertStmt(Connection connection, SubjectDto entry) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_SUBJECT_QUERY);
        ps.setInt(1, entry.getDepartmentId());
        ps.setString(2, entry.getName());
        return ps;
    }

    @Override
    public List<SubjectDto> getSubjectsByDepartmentId(int departmentId) {
        return jdbcTemplate.query(SELECT_BY_DEPARTMENT_ID_QUERY, this::mapRow, departmentId);
    }
}
