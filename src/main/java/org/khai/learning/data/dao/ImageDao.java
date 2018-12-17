package org.khai.learning.data.dao;

import org.khai.learning.data.model.ImageDto;

import java.util.List;

public interface ImageDao extends GenericDao<ImageDto> {
    Integer insertOrUpdate(ImageDto image);
    ImageDto getImageByName(String name);
    List<ImageDto> getAllTitles();
}
