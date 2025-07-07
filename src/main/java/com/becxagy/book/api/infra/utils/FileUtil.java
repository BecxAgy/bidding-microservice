package com.becxagy.book.api.infra.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

public class FileUtil {

    public static File convertMultipartFileToFile(final MultipartFile multipartFile) {
        try {
            BufferedImage originalImage = ImageIO.read(multipartFile.getInputStream());
            File file = new File(multipartFile.getOriginalFilename());
            ImageIO.write(originalImage, "jpg", file);
            return file;

        } catch (IOException e) {
            throw new RuntimeException("Error converting MultipartFile to File: " + e.getMessage());
        }
    }


}