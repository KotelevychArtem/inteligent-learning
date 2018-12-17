package org.khai.learning.data.dao.impl;

import org.khai.learning.data.dao.ImageDao;
import org.khai.learning.data.model.ImageDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class ImageDaoImpl extends AbstractDao<ImageDto> implements ImageDao {
    private static final String TABLE_NAME = "image";
    private static final String TITLE_COLUMN = "title";
    private static final String HASH_COLUMN = "hash";
    private static final String BYTES_COLUMN = "bytes";


    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

    private static final String DELETE_BY_ID_QUERY = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";

    private static final String INSERT_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)" +
                    "ON CONFLICT DO NOTHING",
            TABLE_NAME, TITLE_COLUMN, HASH_COLUMN, BYTES_COLUMN);

    private static final String UPSERT_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)" +
                    "ON CONFLICT ON CONSTRAINT image_hash_title_key DO UPDATE SET bytes = EXCLUDED.bytes",
            TABLE_NAME, TITLE_COLUMN, HASH_COLUMN, BYTES_COLUMN);

    private static final String SELECT_BY_TITLE_QUERY =
            "SELECT * FROM " + TABLE_NAME +
                    " WHERE hash = ? AND title = ?";

    private static final String SELECT_ALL_TITLES = String.format(
            "SELECT %s, %s, FROM %s",
            ID_COLUMN, TITLE_COLUMN, TABLE_NAME);

    public ImageDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, TABLE_NAME);
    }

    @Override
    public Integer insert(ImageDto entry) {
        Integer id = super.insert(entry);
        entry.setId(id);
        return id;
    }

    @Override
    public Integer insertOrUpdate(ImageDto image) {
        Integer id = doUpdate(con -> prepareInsertStmt(con, image, UPSERT_QUERY));
        image.setId(id);
        return id;
    }


    @Override
    public ImageDto getImageByName(String name) {
        return jdbcTemplate.queryForObject(SELECT_BY_TITLE_QUERY, this::mapRow, name.hashCode(), name);
    }

    @Override
    public List<ImageDto> getAllTitles() {
        return jdbcTemplate.query(
                SELECT_ALL_TITLES,
                (rs, i) -> {
                    ImageDto imageDto = new ImageDto();
                    imageDto.setId(rs.getInt(ID_COLUMN));
                    imageDto.setTitle(rs.getString(TITLE_COLUMN));
                    return imageDto;
                });
    }
    @Override
    protected ImageDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ImageDto imageDto = new ImageDto();
        imageDto.setId(rs.getInt(ID_COLUMN));
        imageDto.setTitle(rs.getString(TITLE_COLUMN));
        imageDto.setBytes(rs.getBytes(BYTES_COLUMN));
        return imageDto;
    }

    @Override
    protected PreparedStatement prepareInsertStmt(Connection connection, ImageDto entry) throws SQLException {
        return prepareInsertStmt(connection, entry, INSERT_QUERY);
    }

    private PreparedStatement prepareInsertStmt(Connection connection, ImageDto entry, String query) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, entry.getTitle());
        ps.setInt(2, entry.getTitle().hashCode());
        ps.setBytes(3, entry.getBytes());
        return ps;
    }
}
