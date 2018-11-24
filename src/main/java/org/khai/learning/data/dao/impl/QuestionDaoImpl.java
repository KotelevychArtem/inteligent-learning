package org.khai.learning.data.dao.impl;

import org.khai.learning.data.dao.QuestionDao;
import org.khai.learning.data.model.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class QuestionDaoImpl extends AbstractDaoImpl<QuestionDto> implements QuestionDao {
    private static final String TABLE_NAME = "question";
    private static final String TEST_ID_COLUMN = "testId";
    private static final String POSITION_COLUMN = "position";
    private static final String NAME_COLUMN = "name";
    private static final String CONDITION_COLUMN = "condition";
    private static final String RIGHT_ANSWER_COLUMN = "rightAnswer";

    private static final String INSERT_QUESTION_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?)",
            TABLE_NAME, TEST_ID_COLUMN, POSITION_COLUMN, NAME_COLUMN, CONDITION_COLUMN, RIGHT_ANSWER_COLUMN);

    private static final String SELECT_BY_TEST_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s = ?",
            TABLE_NAME, TEST_ID_COLUMN);

    @Autowired
    public QuestionDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, TABLE_NAME);
    }

    @Override
    protected QuestionDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new QuestionDto(
                rs.getInt(ID_COLUMN),
                rs.getInt(TEST_ID_COLUMN),
                rs.getInt(POSITION_COLUMN),
                rs.getString(NAME_COLUMN),
                rs.getString(CONDITION_COLUMN),
                rs.getString(RIGHT_ANSWER_COLUMN)
        );
    }

    @Override
    protected PreparedStatement prepareInsertStmt(Connection connection, QuestionDto entry) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_QUESTION_QUERY);
        ps.setInt(1, entry.getTestId());
        ps.setInt(2, entry.getPosition());
        ps.setString(3, entry.getName());
        ps.setString(4, entry.getCondition());
        ps.setString(5, entry.getRightAnswer());
        return ps;
    }

    @Override
    public List<QuestionDto> getQuestionsByTestId(int testId) {
        return jdbcTemplate.query(SELECT_BY_TEST_ID_QUERY, this::mapRow, testId);
    }
}
