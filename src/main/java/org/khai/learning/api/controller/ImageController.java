package org.khai.learning.api.controller;

import org.apache.commons.io.IOUtils;
import org.khai.learning.data.dao.ImageDao;
import org.khai.learning.data.model.ImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

@RestController
public class ImageController {
    private final ImageDao imageDao;

    @Autowired
    public ImageController(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @GetMapping("/image/{title}")
    public void getImage(@PathVariable String title, HttpServletResponse httpServletResponse) throws Exception {
        ImageDto imageDto = imageDao.getImageByName(title);
        httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(new ByteArrayInputStream(imageDto.getBytes()), httpServletResponse.getOutputStream());
    }
}
